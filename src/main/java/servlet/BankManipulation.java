package servlet;

import dao.AccountDao;
import dao.BankManipulationDao;
import domain.Account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/BankManipulation")
public class BankManipulation extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        // get destination id , sum
        int accountIworkWith = Integer.parseInt(request.getParameter("destinationAccountId"));
        int sumToSend = Integer.parseInt(request.getParameter("sum"));
        String isDeposit = request.getParameter("deposit");

        AccountDao accountDao = new AccountDao();

        if (!accountDao.validate(accountIworkWith)) {
            session.setAttribute("problem", "account does not exist");
            response.sendRedirect("displayBank.jsp");
            return;
        }

        Account manipulatedAcc = AccountDao.getAccount(accountIworkWith);
        if (isDeposit == null && (sumToSend > manipulatedAcc.getBalance())) {
            session.setAttribute("problem", "account doesn't have enough funds");
            response.sendRedirect("displayBank.jsp");
            return;
        }

        boolean isDeposittoBool = isDeposit != null;

        BankManipulationDao bankManipulationDao = new BankManipulationDao();
        bankManipulationDao.updateSumsCreateTransactionReturnAccount(
                accountIworkWith,
                isDeposittoBool,
                sumToSend
        );
        response.sendRedirect("bankCreate.jsp");
    }
}
