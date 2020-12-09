package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/arg")
public class DemoTagServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameFromServlet = "passedArg";

        request.setAttribute("myParam" , nameFromServlet);
        RequestDispatcher rqd = request.getRequestDispatcher("display.jsp");
        rqd.forward(request,response);
    }
}
