package API;

import APIdomain.Acc;
import APIdomain.Response;
import APIdomain.Trends;
import com.google.gson.Gson;
import dao.AccountDao;
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

@WebServlet("/accTrend")
public class AccTrendS extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId =Integer.parseInt(request.getParameter("id"));
        System.out.println("API: Get/accTrend : param id: " + accountId);
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
        List<Document> accountMongo = AccountDao.queryAggregateTrends(accountId);
        List<Trends> trends = new LinkedList<>();


        for (Document d : accountMongo){
            int id = d.getInteger("_id", 0);
            int totalSum = d.getInteger("total", 0);
            trends.add(new Trends(id, totalSum));
        }

        String accJson = gson.toJson(trends);
        printWriter.write(accJson);
        printWriter.close();



    }
}
