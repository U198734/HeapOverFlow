package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;


/**
 * Servlet implementation class MenuController
 */
@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String view = "ViewMenuNotLogged.jsp"; 
		System.out.print("MenuController: ");
		HttpSession session = request.getSession();
		
		System.out.println(session.getAttribute("roll"));
		System.out.println(session.getAttribute("roll"));
		System.out.println(session.getAttribute("roll"));
		System.out.println(session.getAttribute("roll"));
		
        
		if (session.getAttribute("userName")!= null) {
			if(session.getAttribute("roll").equals("Admin")) {
				view = "ViewMenuLoggedAdmin.jsp";	
				System.out.println("P");

			}
			else {
				System.out.println("forwarding to ViewMenuLogged");
				view = "ViewMenuLogged.jsp";
			}

		}
		else {
			
			System.out.println("forwarding to ViewMenuNotLogged ");
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
