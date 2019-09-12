package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.MySQLconnection.DatabasesHandler;
import com.ericsson.MySQLconnection.UserInfo;

/**
 * Servlet implementation class UserHandlerServlet
 */
@WebServlet("/UserHandlerServlet")
public class UserHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserHandlerServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		
	}

	public void destroy() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * DatabasesHandler databasesHandler = new DatabasesHandler(); List<UserInfo>
		 * userList = databasesHandler.getAllUsers(); if(!userList.isEmpty()) {
		 * request.setAttribute("userList",userList);
		 * request.getRequestDispatcher("handlerUser.jsp").forward(request, response); }
		 */
		int currentPage = 1;
		String sCurrentPage = (String) request.getParameter("currentPage");
		System.out.println(sCurrentPage);
		if(sCurrentPage != null) {
		 currentPage = Integer.parseInt(sCurrentPage)	;
		}
		System.out.println(currentPage);
		DatabasesHandler databasesHandler = new DatabasesHandler();
	    int onePageNumber = 5;
	    List<UserInfo> userList = databasesHandler.getAllUsersByPaging(currentPage, onePageNumber);
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("handlerUser.jsp").forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
