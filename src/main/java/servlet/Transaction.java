package servlet;

import dao.AccountDao;
import dao.OnlineTransactionDao;
import domain.Account;
import org.bson.Document;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Transaction")
public class Transaction extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        // get destination id , sum
        int destinationAccountId = Integer.parseInt(request.getParameter("destinationAccountId"));
        int sumToSend = Integer.parseInt(request.getParameter("sum"));

        //get sourceId , using account from session
        Account sourceAccount = (Account) session.getAttribute("account");
        int sourceAccountId = sourceAccount.getId();


        // Do i have enough founds
        if (sumToSend > sourceAccount.getBalance()) {
            session.setAttribute("problem", "not enough funds on account to finish called transaction");
            response.sendRedirect("display.jsp");
            return;
        }

        // Does destiantion address exist, and is it different from sending id
        if (!isDestinationAccountValid(sourceAccountId, destinationAccountId)) {
            session.setAttribute("problem", "wrong destination account address");
            response.sendRedirect("display.jsp");
            return;

        }

        // DAO  override existing sum on account , create new transaction in SQL, add transaction to mongo
        OnlineTransactionDao onlineTransactionDao = new OnlineTransactionDao();
        Account accUpdated = onlineTransactionDao.updateSumsCreateTransactionReturnAccount(
                sourceAccountId,
                destinationAccountId,
                sumToSend);

        session.setAttribute("account", accUpdated);
        Document accountMongo = AccountDao.getAccountMongo(sourceAccountId);
        session.setAttribute("accountMongo", accountMongo);

        List<Document> trends = AccountDao.queryAggregateTrends(sourceAccountId);
        session.setAttribute("trendsMongo", trends);
        response.sendRedirect("Manipulations");
    }

    public boolean isDestinationAccountValid(int sourceId, int destinationAccountId) {
        AccountDao accountDao = new AccountDao();
        boolean destinationExist = accountDao.validate(destinationAccountId);
        return destinationExist && (sourceId != destinationAccountId);
    }

}
