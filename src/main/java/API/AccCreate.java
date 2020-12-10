package API;

import APIdomain.Response;
import com.google.gson.Gson;
import dao.AccountDao;
import dao.UserDao;
import domain.Account;
import domain.City;
import domain.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/accCreate")
public class AccCreate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int balance = Integer.parseInt(request.getParameter("balance"));
        String first = request.getParameter("first");
        String last = request.getParameter("last");

        City c = new City();
        c.setId(1);

        User u = new User();
        u.setFirstName(first);
        u.setLastName(last);
        u.setFromCity(c);

        Account a = new Account();
        a.setOwner(u);
        a.setBalance(balance);

        UserDao.saveUser(u);
        AccountDao.saveAccount(a);

        Gson gson = new Gson();
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Response response1 = new Response("good");
        String accJson = gson.toJson(response1);
        printWriter.write(accJson);
        printWriter.close();
    }
}
