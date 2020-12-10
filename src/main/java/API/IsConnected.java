package API;

import APIdomain.Tracking;
import com.google.gson.Gson;
import dao.NeoDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/accConnected")
public class IsConnected extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int from = Integer.parseInt(request.getParameter("from"));
        int to = Integer.parseInt(request.getParameter("to"));

        int path = NeoDao.accountsShortestPath(from, to);
        boolean connected = NeoDao.areAccountsConnected(from, to);

        Gson g = new Gson();
        Tracking t = new Tracking(connected, path);
        String json = g.toJson(t);

        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        writer.write(json);
        writer.close();
    }
}
