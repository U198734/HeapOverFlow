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

	    if ("update".equals(action)) {
	        // Recuperar los parámetros del formulario
	        String mail = request.getParameter("mail");
	        String gender = request.getParameter("gender");
	        String programmingLanguage = request.getParameter("programming_language");
	        String professionalField = request.getParameter("professional_field");

	        // Obtener el objeto User de la sesión correctamente
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            User user = (User) session.getAttribute("user");
	            if (user != null) { // Verificar que el usuario recuperado no sea nulo
	                // Actualizar los datos del objeto User
	                user.setMail(mail);
	                user.setGender(gender);
	                user.setProgramming_language(programmingLanguage);
	                user.setProfessional_field(professionalField);

	                // Lógica para guardar el usuario actualizado en la base de datos o en la sesión
	                UserManager userManager = new UserManager();
	                boolean success = userManager.updateUser(user);

	                if (success) {
	                    // Actualizar el objeto User en la sesión
	                    session.setAttribute("user", user);

	                    // Redirigir de vuelta a la página de perfil
	                    response.sendRedirect("ViewPerfil.jsp");
	                } else {
	                	System.out.println("Dos");
	                	// Manejar el caso de fallo en la actualización
	                    response.sendRedirect("MainController"); // Otra página de error o manejo adecuado
	                }
	            } else {
	            	System.out.println("Uno");
	                // Manejar el caso donde el objeto User es nulo
	                response.sendRedirect("MainController"); // Otra página de error o manejo adecuado
	            }
	        } else {
	            // Manejar el caso donde la sesión no existe
	            response.sendRedirect("MainController"); // Redirigir a la página de inicio de sesión u otra página adecuada
	        }
	    } else {
	        // Manejar otros casos de acción (puede que no sea necesario en este contexto)
	        response.sendRedirect("MainController");
	    }
	}





}
