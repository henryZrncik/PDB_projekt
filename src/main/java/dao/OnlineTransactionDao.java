package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import domain.Account;
import domain.OnlineTransaction;
import domain.User;
import domain.neo.Send;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;
import utilities.MongoUtil;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;

public class OnlineTransactionDao {
    public boolean synchronizationMongoTransaction(int transactionId, int sourceAccountId, int destinationAccountId, int sum, int newBalanceSource, int newBalanceDestination, String dateString){
        System.out.println("dotiahol som to az na mongoTransaction");
        MongoClient mongoClient = MongoUtil.getMongoClient();
        Document transactionOut = new Document("transactionId", transactionId)
                .append("isIN", false)
                .append("isTransaction", true)
                .append("sum", sum)
                .append("date", dateString)
                .append("dateAggregateFormat",  Integer.parseInt( dateString.substring(0, 4) + dateString.substring(5, 7) ))
                .append("destinationAccountId", destinationAccountId);
        Document transactionIn = new Document("transactionId", transactionId)
                .append("isIN", true)
                .append("isTransaction", true)
                .append("sum", sum)
                .append("date", dateString)
                .append("dateAggregateFormat",  Integer.parseInt( dateString.substring(0, 4) + dateString.substring(5, 7) ))
                .append("sourceAccountId", sourceAccountId);

        MongoCollection accountCollection = mongoClient.getDatabase("db1").getCollection("Accounts");
        // SOURCE / OUT updated sum and add new transaction
        Bson filterSource = eq("accountId", sourceAccountId);
        Bson updateOperationSource = set("balance", newBalanceSource);
        Bson updateOperationAddManipulation = push("accountManipulations", transactionOut);
        UpdateResult updateResult1 = accountCollection.updateOne(filterSource,   updateOperationAddManipulation );
        UpdateResult updateResult2 = accountCollection.updateOne(filterSource,  updateOperationSource );

        // Destination / In updated sum and add new transaction
        Bson filterDestination = eq("accountId", destinationAccountId);
        Bson updateOperationDestination = set("balance", newBalanceDestination);
        Bson updateOperationAddManipulation2 = push("accountManipulations", transactionIn);
        UpdateResult updateResult3 = accountCollection.updateOne(filterDestination,   updateOperationAddManipulation2 );
        UpdateResult updateResult4 = accountCollection.updateOne(filterDestination,  updateOperationDestination );

        return true;

    }

    public Account updateSumsCreateTransactionReturnAccount(int sourceAccountId, int destinationAccountId, int sum) {

        Account sourceAccount = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            //get accounts
            sourceAccount = session.get(Account.class, sourceAccountId);
            Account destinationAccount = session.get(Account.class, destinationAccountId);

            // create transaction & set params
            OnlineTransaction onlineTransaction = new OnlineTransaction();
            onlineTransaction.setManipulatedAccount(sourceAccount);
            onlineTransaction.setDestinationAccount(destinationAccount);
            onlineTransaction.setSum(sum);
            session.save(onlineTransaction);

            // get id of new transaction for mongo
            int newTransactionId = onlineTransaction.getId();
            // get date for mongo format
            String mongoDate = onlineTransaction.getTimeasString();

            // update sums
            int newBalanceSource =  sourceAccount.getBalance() - sum;
            int newBalanceDestination = destinationAccount.getBalance() + sum;
            sourceAccount.setBalance(newBalanceSource);
            destinationAccount.setBalance(newBalanceDestination);


            if(            this.synchronizationMongoTransaction(
                    newTransactionId,
                    sourceAccountId,
                    destinationAccountId,
                    sum,
                    newBalanceSource,
                    newBalanceDestination,
                    mongoDate)){

                Send s = new Send(newTransactionId, sum,sourceAccountId,destinationAccountId );
                NeoDao.createTransaction(s);
                transaction.commit();
            }
;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return sourceAccount;
    }

    public boolean validate(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, id );
            if (account != null ) {
                return true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    public Account getAccount(int id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, id );
            if (account != null ) {
                return account;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
