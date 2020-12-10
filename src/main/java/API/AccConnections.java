package API;

import APIdomain.Connections;
import APIdomain.Response;
import com.google.gson.Gson;
import dao.NeoDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/accConnections")
public class AccConnections extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        int connections = NeoDao.accountConnections(id);
        int neighbor = NeoDao.neighborConnections(id);

        Connections c = new Connections(connections, neighbor);

        Gson g = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String accJson = g.toJson(c);
        writer.write(accJson);
        writer.close();
    }
}
