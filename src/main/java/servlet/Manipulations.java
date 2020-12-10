package servlet;

import dao.AccountDao;
import dao.ManipulationDao;
import domain.Account;
import domain.Manipulation;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/Manipulations")
public class Manipulations extends HttpServlet implements Comparator<Manipulation> {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ManipulationDao manipulationDao = new ManipulationDao();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int accountId = account.getId();

        List<Manipulation> manipulations = manipulationDao.getManipulationsOfAccount(accountId);
//        for (Manipulation m : manipulations) {
//            System.out.println(m.getId());
//        }

        Collections.sort(manipulations, this::compare);
//        for (Manipulation m : manipulations) {
//            System.out.println(m.getId());
//        }

        Document accountMongo = AccountDao.getAccountMongo(accountId);
        session.setAttribute("manipulations", manipulations);
        session.setAttribute("accountMongo", accountMongo);
        response.sendRedirect("manipulations.jsp");


    }

    @Override
    public int compare(Manipulation o1, Manipulation o2) {
        return o1.getId() > o2.getId() ? -1 : 1;
    }
}
