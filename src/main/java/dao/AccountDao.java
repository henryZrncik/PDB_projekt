package dao;

import com.google.gson.Gson;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.UpdateResult;
import domain.Account;
import domain.OnlineTransaction;
import domain.User;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;
import utilities.MongoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;

public class AccountDao {
    public static void saveAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }






        MongoClient mongoClient = MongoUtil.getMongoClient();

        Document owner = new Document("ownerId", account.getOwner().getId())
                .append("ownerFirstName", account.getOwner().getFirstName())
                .append("ownerLastName", account.getOwner().getLastName())
                .append("isFromCity", account.getOwner().getFromCity().getName());

        Document acc = new Document("accountId", account.getId())
                .append("owner", owner)
                .append("balance", account.getBalance())
                .append("AccountManipulations", null);

        Gson g = new Gson();
        String gacc = g.toJson(acc);

        MongoCollection accountCollection = mongoClient.getDatabase("db1").getCollection("Accounts");
        // SOURCE / OUT updated sum and add new transaction
        accountCollection.insertOne(acc);
    }

    public static Account updateSumReturnAccount(int id, int sum) {
        Transaction transaction = null;
        OnlineTransaction onlineTransaction = null;
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            account = session.get(Account.class, id);
            account.setBalance(account.getBalance() - sum);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return account;
    }

    public static boolean validate(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, id);
            if (account != null) {
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

    public static Account getAccount(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, id);
            if (account != null) {
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

    public static Document getAccountMongo(int id) {
        MongoClient mongoClient = MongoUtil.getMongoClient();
        MongoCollection aCollection = mongoClient.getDatabase("db1").getCollection("Accounts");
        Document accountDocument = (Document) aCollection.find(new Document("accountId", id)).first();


        return accountDocument;

    }

    public static List<Document> queryAggregateTrends( int id) {
        MongoClient mongoClient = MongoUtil.getMongoClient();
        MongoCollection aCollection = mongoClient.getDatabase("db1").getCollection("Accounts");

        AggregateIterable<Document> iterable = aCollection.aggregate(Arrays.asList(
                match(Filters.eq("accountId", id)),
                project(Projections.include("accountId", "accountManipulations")),
                unwind("$accountManipulations"),
                group("$accountManipulations.dateAggregateFormat", Accumulators.sum("total", "$accountManipulations.sum"))
        ));
        System.out.println(iterable);
        List<Document> documents = new ArrayList<>();
        for (Document d : iterable) {
            documents.add(d);
        }
        return documents;
    }

}
