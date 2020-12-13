package API;


import APIdomain.Acc;
import APIdomain.Response;
import APIdomain.Transaction;
import APIdomain.Trends;
import com.google.gson.Gson;
import dao.AccountDao;
import dao.ManipulationDao;
import dao.OnlineTransactionDao;
import domain.Account;
import org.bson.Document;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

// priklad:
//http://localhost:8080/mProj_war_exploded/accAccSumTransaction?from=1&to=2&sum=5
// response: {"result":"good"}

@WebServlet("/accAccSumTransaction")
public class AccAccSumTransactionP extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountId1 = Integer.parseInt(request.getParameter("from"));
        int accountId2 = Integer.parseInt(request.getParameter("to"));
        int sum = Integer.parseInt(request.getParameter("sum"));
        boolean isGoodAcc1 = AccountDao.validate(accountId1);
        boolean isGoodAcc2 = AccountDao.validate(accountId2);

        Gson gson = new Gson();
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (!isGoodAcc1) {
            Response response1 = new Response("wrong");
            String accJson = gson.toJson(response1);
            printWriter.write(accJson);
            printWriter.close();
            return;
        }

        if (!isGoodAcc2) {
            Response response1 = new Response("wrong");
            String accJson = gson.toJson(response1);
            printWriter.write(accJson);
            printWriter.close();
            return;
        }

        Account account = AccountDao.getAccount(accountId1);
        if (sum > account.getBalance()) {
            Response response1 = new Response("wrong");
            String accJson = gson.toJson(response1);
            printWriter.write(accJson);
            printWriter.close();
            return;
        }


        OnlineTransactionDao onlineTransactionDao = new OnlineTransactionDao();
        Account acc = onlineTransactionDao.updateSumsCreateTransactionReturnAccount(accountId1, accountId2, sum);


        Response response1 = new Response("good");
        String accJson = gson.toJson(response1);
        printWriter.write(accJson);
        printWriter.close();


    }
}

