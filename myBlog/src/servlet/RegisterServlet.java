package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.MySQLconnection.DatabasesHandler;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		
	}

	public void destroy() {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String registerName=request.getParameter("registerName");
		String registerPassword=request.getParameter("registerPassword");
		String doubleCheckPassword=request.getParameter("doubleCheckPassword");
		String question=request.getParameter("question");
		String answer = request.getParameter("answer");
		String grade = request.getParameter("grade");
		if(registerName.equals(doubleCheckPassword)){
		  DatabasesHandler databasesHandler = new DatabasesHandler();
		  int result = databasesHandler.addUser(registerName, registerPassword, question, answer, grade);
		     if(result>0) {
		       response.sendRedirect("Login.jsp");
		           }else {
		        	   response.sendRedirect("register.jsp");   
		           }
		}
		else{
			response.sendRedirect("register.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
