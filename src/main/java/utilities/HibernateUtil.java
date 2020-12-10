package utilities;

import domain.ATMObject;
import domain.ATMWithdrawl;
import domain.Account;
import domain.BankManipulation;
import domain.BankObject;
import domain.CardObject;
import domain.CardWithdrawl;
import domain.City;
import domain.Country;
import domain.GenericObject;
import domain.Manipulation;
import domain.OnlineTransaction;
import domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(City.class)
                        .addAnnotatedClass(Country.class)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Account.class)
                        .addAnnotatedClass(GenericObject.class)
                        .addAnnotatedClass(ATMObject.class)
                        .addAnnotatedClass(CardObject.class)
                        .addAnnotatedClass(BankObject.class)
                        .addAnnotatedClass(Manipulation.class)
                        .addAnnotatedClass(ATMWithdrawl.class)
                        .addAnnotatedClass(CardWithdrawl.class)
                        .addAnnotatedClass(BankManipulation.class)
                        .addAnnotatedClass(OnlineTransaction.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}