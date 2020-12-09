package dao;

import domain.Account;
import domain.GenericObject;
import domain.Manipulation;
import domain.OnlineTransaction;
import domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

import java.util.Comparator;
import java.util.List;

public class ManipulationDao {
    public List<Manipulation> getManipulationsOfAccount(int id){
        Transaction transaction = null;
        List<Manipulation> resultsManipulations = null;
        List<Manipulation> resultsTransactionDestionation = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String q = "FROM Manipulation m WHERE m.manipulatedAccount = " + id;
            String q2 = "FROM OnlineTransaction m WHERE m.destinationAccount = "+ id;

            resultsManipulations = (List<Manipulation>) session.createQuery(q, Manipulation.class).getResultList();
            resultsTransactionDestionation = (List<Manipulation>) session.createQuery(q2, Manipulation.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        resultsManipulations.addAll(resultsTransactionDestionation);
        System.out.println("returning following transactions: " + resultsManipulations);



        return resultsManipulations;

    }



}

//        for (Manipulation m :  results) {
//            if (m instanceof OnlineTransaction){
//                System.out.println("found transaction where it is sender");
//                System.out.println(((OnlineTransaction) m).getDestinationAccount());
//            }
//
//            else{
//                System.out.println("found card payable");
////                        System.out.println( ((CardObject) m).getCardPaymenttype());
//            }



