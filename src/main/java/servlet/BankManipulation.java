package servlet;

import com.mongodb.client.AggregateIterable;
import dao.AccountDao;
import dao.BankManipulationDao;
import dao.ManipulationDao;
import dao.OnlineTransactionDao;
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
import java.util.List;

@WebServlet("/BankManipulation")
public class BankManipulation extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        System.out.println("i started");
        // get destination id , sum
        int accountIworkWith =Integer.parseInt(request.getParameter("destinationAccountId"));
        int sumToSend =Integer.parseInt(request.getParameter("sum"));
        String isDeposit = request.getParameter("deposit");
        System.out.println("isDeposit: " + isDeposit);
        System.out.println("accountIworkWith: " +  accountIworkWith);
        System.out.println("BLA BLA");

        AccountDao accountDao = new AccountDao();

        if (! accountDao.validate(accountIworkWith)) {
            System.out.println("account i work with does nto exist");
            Account account = accountDao.getAccount(accountIworkWith);
            session.setAttribute("problem", "account does not exist");
            response.sendRedirect("displayBank.jsp");
            return;
        }

        Account manipulatedAcc = AccountDao.getAccount(accountIworkWith);
        if ( isDeposit == null && (sumToSend > manipulatedAcc.getBalance() )){
            System.out.println("i want to take out many adn do not have enugh");
            Account account = accountDao.getAccount(accountIworkWith);
            session.setAttribute("problem", "account doesn't have enough funds");
            response.sendRedirect("displayBank.jsp");
            return;
        }

        boolean isDeposittoBool = isDeposit != null ;
        System.out.println("i passed throw all and goes on with following params:");
        System.out.println("accountIworkWith: " + accountIworkWith);
        System.out.println("isDeposittoBool " + isDeposittoBool);
        System.out.println("sumToSend " + sumToSend);
        // UPDATE bilance on account
        // CREATE DEPOSIT
        // CREATE WITHDRAWL
        BankManipulationDao bankManipulationDao = new BankManipulationDao();
        bankManipulationDao.updateSumsCreateTransactionReturnAccount(
                accountIworkWith,
                isDeposittoBool,
                sumToSend
                );


//        Document accountMongo = AccountDao.getAccountMongo(accountIworkWith);
//        session.setAttribute("accountMongo", accountMongo);

        response.sendRedirect("bankCreate.jsp");

        System.out.println("ales gut");




    }


}
