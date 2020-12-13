package API;

import APIdomain.Acc;
import APIdomain.Response;
import com.google.gson.Gson;
import dao.AccountDao;
import dao.NeoDao;
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

        domain.neo.Account neoa = new domain.neo.Account();
        neoa.setAccountId(a.getId());
        neoa.setOwnerFirstName(first);
        neoa.setOwnerSecondName(last);
        neoa.setOwnerIsFrom("New York");
        NeoDao.createAccount(neoa);

        Gson gson = new Gson();
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Acc acc = new Acc(first, last, balance, a.getId());

        Response response1 = new Response("good");
        String accJson = gson.toJson(acc);
        printWriter.write(accJson);
        printWriter.close();
    }
}
