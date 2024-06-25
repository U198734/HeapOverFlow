package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import models.User;
import managers.UserManager;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/UpdateUserInfoController")
public class UpdateUserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfoController () {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  String action = request.getParameter("action");
          UserManager manager = new UserManager();
  		  HttpSession session = request.getSession();

  		  if ("update".equals(action)) {
              User newUser = new User();
              try {
                  BeanUtils.populate(newUser, request.getParameterMap());

                  String userName = request.getParameter("user_name");
                  if (userName != null && !userName.isEmpty()) {
                      User oldUser = manager.getUserInfo(userName);

                      if (isUpdateValid(newUser, oldUser)) {
                          manager.updateUser(newUser);  
                          request.getSession().setAttribute("user_name", newUser.getUser_name());  
                          response.sendRedirect("ViewPerfil.jsp");  // Redirige a la vista del perfil
                      } else {
                          request.setAttribute("user", newUser);
                          if(session.getAttribute("role").equals("Admin")) {
	                          request.setAttribute("menu", "ViewMenuLoggedAdmin.jsp");
	                          request.setAttribute("content", "ViewEditUser.jsp");
	                          RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	                          dispatcher.forward(request, response);
                          }else {
                        	  request.setAttribute("menu", "ViewMenuLogged.jsp");
	                          request.setAttribute("content", "ViewEditUser.jsp");
	                          RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	                          dispatcher.forward(request, response);
                        	  
                          }
                      }
                  } else {
                      // Manejar el caso en que el user_name no se envió correctamente
                      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing user_name");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
    }

    private boolean isUpdateValid(User newUser, User oldUser) {
        boolean isValid = true;

        if (newUser.getUser_name() == null || newUser.getUser_name().isEmpty()) {
            isValid = false;
        }
        
        return isValid;
    }

}




