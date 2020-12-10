package dao;

import domain.Manipulation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

import java.util.List;

public class ManipulationDao {
    public List<Manipulation> getManipulationsOfAccount(int id) {
        Transaction transaction = null;
        List<Manipulation> resultsManipulations = null;
        List<Manipulation> resultsTransactionDestionation = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String q = "FROM Manipulation m WHERE m.manipulatedAccount = " + id;
            String q2 = "FROM OnlineTransaction m WHERE m.destinationAccount = " + id;

            resultsManipulations = session.createQuery(q, Manipulation.class).getResultList();
            resultsTransactionDestionation = session.createQuery(q2, Manipulation.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        resultsManipulations.addAll(resultsTransactionDestionation);
        return resultsManipulations;
    }


}
