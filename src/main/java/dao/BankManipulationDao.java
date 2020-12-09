package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import domain.Account;
import domain.BankManipulation;
import domain.BankObject;
import domain.GenericObject;
import domain.OnlineTransaction;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;
import utilities.MongoUtil;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;

public class BankManipulationDao {
    public boolean synchronizationMongoBankManipulation(int transactionId, int sourceAccountId,  int sum, int newBalanceSource,  String dateString, boolean isDeposit){
        System.out.println("dotiahol som to az na mongo bank manipulation");
        MongoClient mongoClient = MongoUtil.getMongoClient();
        Document transactionNew = new Document("transactionId", transactionId)
                .append("isIN", isDeposit)
                .append("isTransaction", false)
                .append("sum", sum)
                .append("date", dateString)
                .append("dateAggregateFormat",  Integer.parseInt( dateString.substring(0, 4) + dateString.substring(5, 7) ))
                .append("bankName", "New York Bank");


        MongoCollection accountCollection = mongoClient.getDatabase("db1").getCollection("Accounts");
        // SOURCE / OUT updated sum and add new transaction
        Bson filterSource = eq("accountId", sourceAccountId);
        Bson updateOperationSource = set("balance", newBalanceSource);
        Bson updateOperationAddManipulation = push("accountManipulations", transactionNew);
        UpdateResult updateResult1 = accountCollection.updateOne(filterSource,   updateOperationAddManipulation );
        UpdateResult updateResult2 = accountCollection.updateOne(filterSource,  updateOperationSource );

        return true;

    }

    public Account updateSumsCreateTransactionReturnAccount(int sourceAccountId, boolean isDeposit, int sum) {
        System.out.println("zacinam update .... (prva fcia called)");
        Account sourceAccount = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("spojenie naviazane na hibernate");
            Transaction transaction = session.beginTransaction();
            //get accounts
            sourceAccount = session.get(Account.class, sourceAccountId);
            BankManipulation bankManipulation = new BankManipulation();
            // create transaction & set params

            bankManipulation.setManipulatedAccount(sourceAccount);
            bankManipulation.setSum(sum);

            BankObject b2 = (BankObject) session.get(GenericObject.class , 2);
            bankManipulation.setBank(b2);
            bankManipulation.setDeposit(isDeposit);

            session.save(bankManipulation);
            System.out.println("prave som session savenul manipulaciu");
            // get id of new transaction for mongo
            int newBankManipulationId = bankManipulation.getId();


            // get date for mongo format
            String mongoDate = bankManipulation.getTimeasString();

            // update sums
            int newBalanceSource ;
            if (bankManipulation.isDeposit()){
                newBalanceSource =  sourceAccount.getBalance() + sum;
            }

            else{
                newBalanceSource =  sourceAccount.getBalance() - sum;
            }
            sourceAccount.setBalance(newBalanceSource);


            System.out.println("idem smer mongo s params: ");
            System.out.println(newBankManipulationId);
            System.out.println(sourceAccountId);
            System.out.println(sum);
            System.out.println(newBalanceSource);
            System.out.println(mongoDate);
            System.out.println(isDeposit);
            if(            this.synchronizationMongoBankManipulation(
                    newBankManipulationId,
                    sourceAccountId,
                    sum,
                    newBalanceSource,
                    mongoDate,
                    isDeposit)){
                transaction.commit();
            }
            ;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return sourceAccount;
    }
}
