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
 * Servlet implementation class HandlerLoginServlet
 */
@WebServlet("/HandlerLoginServlet")
public class HandlerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HandlerLoginServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	
	}

	public void destroy() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String passwordFormWeb = request.getParameter("password");
		DatabasesHandler databasesHandler = new DatabasesHandler();
		String password = databasesHandler.handlerLogin(username);
		 if(passwordFormWeb.equals(password)){
	    	    session.setAttribute("username",username );
	    		request.getRequestDispatcher("welcome.jsp").forward(request, response);//请求转发
	    	}else{
	    		response.sendRedirect("Login.jsp");
	    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
