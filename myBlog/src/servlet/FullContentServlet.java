package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ericsson.MySQLconnection.DatabasesHandler;

/**
 * Servlet implementation class FullContentServlet
 */
@WebServlet("/FullContentServlet")
public class FullContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FullContentServlet() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {	
	}
	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		HttpSession session = request.getSession();
		session.setAttribute("title", title);
		DatabasesHandler databasesHandler =new DatabasesHandler();
		String fullContent = databasesHandler.getFullContent(title);
		session.setAttribute("fullContent", fullContent);
		request.getRequestDispatcher("showContent.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
