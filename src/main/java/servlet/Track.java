package servlet;

import dao.NeoDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Track")
public class Track extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        int acc1Id = Integer.parseInt(request.getParameter("acc1Id"));
        int acc2Id = Integer.parseInt(request.getParameter("acc2Id"));

        session.setAttribute("answer2", NeoDao.accountsShortestPath(acc1Id, acc2Id));
        session.setAttribute("answer", NeoDao.areAccountsConnected(acc1Id, acc2Id));
        response.sendRedirect("track.jsp");

    }


}
