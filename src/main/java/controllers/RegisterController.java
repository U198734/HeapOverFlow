package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import managers.UserManager;
import models.User;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("RegisterController: ");

        User user = new User();
        UserManager manager = new UserManager();
        String view = "ViewRegisterForm.jsp";

        try {
            BeanUtils.populate(user, request.getParameterMap());
            //User existingUser = manager.getUserInfo(user.getUserName());
            
        
        	// If user doesn't exist, register new user
            if (manager.isRegisterComplete(user)) {
                // Añadir user en el caso que no esté en la base de datos
                manager.addUser(user);
                System.out.println("User ok, forwarding to ViewLoginForm");
                view = "ViewLoginForm.jsp";
            } else {
                // Volver a mostrar la vista del formulario con los errores
                System.out.println("Forwarding to ViewRegisterForm");
                request.setAttribute("user", user);
	            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
