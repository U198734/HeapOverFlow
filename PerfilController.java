package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.UserManager;
import models.User;

/**
 * Servlet implementation class MenuController
 */
@WebServlet("/PerfilController")
public class PerfilController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfilController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String username =  (String) session.getAttribute("user_name");
		User user = null;
		
		if (session != null) {
			UserManager userManager = new UserManager();
			user = userManager.getUserInfo(username);
			userManager.finalize();
			request.setAttribute("user",user);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewPerfil.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        UserManager manager = new UserManager();
        
		if ("edit".equals(action)) {
            String user_name = request.getParameter("user_name");
            if (user_name != null) {
                User user = manager.getUserInfo(user_name);
                request.setAttribute("user", user);
                request.setAttribute("menu", "ViewMenuLoggedAdmin.jsp");
                request.setAttribute("content", "ViewEditUser.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
		}
	}



	private boolean isUpdateValid(User newUser, User oldUser) {
	    UserManager manager = new UserManager();
	    boolean isValid = true;

	    if (!newUser.getUser_name().equals(oldUser.getUser_name()) && manager.isUserNameTaken(newUser.getUser_name())) {
	        newUser.setError("user_name", "WARNING - Provided user name already exists in our database.");
	        isValid = false;
	    }

	    if (!newUser.getMail().equals(oldUser.getMail()) && manager.isEmailTaken(newUser.getMail())) {
	        newUser.setError("mail", "WARNING - Provided mail already exists in our database.");
	        isValid = false;
	    }

	    return isValid;
	}





}
