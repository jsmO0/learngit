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
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String title = (String) request.getSession().getAttribute("title");
		String afterEditContent = request.getParameter("afterEditContent");
		DatabasesHandler databasesHandler = new DatabasesHandler();
		final boolean checkEdit = databasesHandler.edit(title, afterEditContent);
		if(checkEdit) {
			request.getRequestDispatcher("ManageServlet").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
