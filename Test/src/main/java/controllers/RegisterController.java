package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
		
		   if (manager.isRegisterComplete(user)) {
           		System.out.println("PRINT 1");
			    List<String> errors = manager.validate(user);
           		System.out.println("PRINT 2");
           		int longitud = errors.size();
                if (longitud == 0) {
			    // Afegir user en el cas que no estigui a la base de dades
                	 manager.addUser(user);
				     System.out.println(" user ok, forwarding to ViewLoginForm");
				     view = "ViewLoginForm.jsp";
                } 
                else {
                	// Tornem a ensenyar la vista del formulari
                	System.out.println(" forwarding to ViewRegisterForm");
                	// Per tenir els errors i mostrar en el front 
            		request.setAttribute("error", errors);
            		// Per no haver de posar tots els parametres de nou
                	request.setAttribute("user",user);
                }	  
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
