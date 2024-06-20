package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.UserManager;
import models.User;

/**
 * Servlet implementation class MenuController
 */
@WebServlet("/UsersRegisteredController")
public class UsersRegisteredController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersRegisteredController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = Collections.emptyList();

        HttpSession session = request.getSession(false);
        //User currentUser = (User) session.getAttribute("user");
        

        try {
        	if (session != null) {
                UserManager userManager = new UserManager();
                
                users = userManager.getAllUsers();
                System.out.println("Users retrieved: " + users.size());
                userManager.finalize();
        		}
            } catch (Exception e) {
                e.printStackTrace();
            }
        

        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewUsersRegistered.jsp");
        dispatcher.forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        UserManager manager = new UserManager();

        if ("delete".equals(action)) {
            String userName = request.getParameter("user_name");
            if (userName != null) {
                manager.deleteUserByUserName(userName);
                response.sendRedirect("UsersRegisteredController");
                //RequestDispatcher dispatcher = request.getRequestDispatcher("UsersRegisteredController");
                //dispatcher.forward(request, response);
            }
        } 
        
    }

}