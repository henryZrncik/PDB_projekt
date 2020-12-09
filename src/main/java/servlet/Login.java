package servlet;

import com.mongodb.client.AggregateIterable;
import dao.AccountDao;
import domain.Account;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Login")
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.printf("called scriptlet");
        int accountId =Integer.parseInt(request.getParameter("accountId"));

        AccountDao accountDao = new AccountDao();

        HttpSession session = request.getSession();

        if (accountDao.validate(accountId)){
            Account account = accountDao.getAccount(accountId);
            Document accountMongo = AccountDao.getAccountMongo(accountId);
            List<Document> trends = AccountDao.queryAggregateTrends(accountId);
            session.setAttribute("trendsMongo", trends);

            session.setAttribute("isLogged", "StringTrue");
            session.setAttribute("account", account);
            session.setAttribute("accountMongo", accountMongo);
            response.sendRedirect("welcome.jsp");
        }
        else {
            session.setAttribute("wrongAccount", true);
            response.sendRedirect("index.jsp");

        }




//
//        request.setAttribute("myParam" , nameFromServlet);
//        RequestDispatcher rqd = request.getRequestDispatcher("display.jsp");
//        rqd.forward(request,response);
    }
}
