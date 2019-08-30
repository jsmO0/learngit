package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ericsson.MySQLconnection.DatabasesHandler;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      String username = (String)session.getAttribute("username");
      String title = (String)session.getAttribute("title");
      String comments = request.getParameter("comments");
      DatabasesHandler databasesHandler = new DatabasesHandler();
      boolean checkSubmitStatus = databasesHandler.submitComment(username,title, comments);
      if(checkSubmitStatus) {
    	  List<File> commentFilePaths = databasesHandler.getAllCommentsPath(title);
    	  List<String> commentsList = databasesHandler.readComments(commentFilePaths);
    	  request.setAttribute("commentsList", commentsList);
    	  request.getRequestDispatcher("showComment.jsp").forward(request, response);
      }else {
    	  response.sendRedirect("comment.jsp");
      }
	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
