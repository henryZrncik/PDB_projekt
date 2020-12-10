package API;

import APIdomain.Acc;
import APIdomain.Response;
import com.google.gson.Gson;
import dao.AccountDao;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/acc")

public class AccS extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountId =Integer.parseInt(request.getParameter("id"));
        System.out.println("API: Get/acc : param id: " + accountId);
        boolean isGoodAcc =  AccountDao.validate(accountId);

        Gson gson = new Gson();
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (! isGoodAcc){
            Response response1 = new Response("wrong");
            String accJson = gson.toJson(response1);
            printWriter.write(accJson);
            printWriter.close();
            return;
        }
        Document accountMongo = AccountDao.getAccountMongo(accountId);
        Document owner = (Document) accountMongo.get("owner");

        String name =  owner.getString("ownerFirstName");
        String lastName = owner.getString("ownerLastName");
        int balance = accountMongo.getInteger("balance",0);

        Acc a = new Acc(name, lastName, balance);
        String accJson = gson.toJson(a);
        printWriter.write(accJson);
        printWriter.close();
    }
}
