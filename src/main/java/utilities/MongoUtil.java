package utilities;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;


public class MongoUtil {
    private static MongoClient mongoClient;
    private static String connectionStr = "mongodb+srv://user1:password111@cluster0.kzu3f.mongodb.net/db1?retryWrites=true&w=majority";

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            try {
                MongoClient mongoClient = MongoClients.create(connectionStr);
                return mongoClient;
            } catch (Exception e) {
                return null;
            }
        }
        return mongoClient;
    }
}