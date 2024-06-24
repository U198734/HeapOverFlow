package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class MenuController
 */
@WebServlet("/AddPost")
public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPost() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        
        String content = request.getParameter("content"); // TESTED, DO NOT MODIFY
        String url = request.getParameter("url"); // TESTED, DO NOT MODIFY
        String programmingLanguage = request.getParameter("programmingLanguage"); // TESTED, DO NOT MODIFY
        String professionalField = request.getParameter("professionalField"); // TESTED, DO NOT MODIFY

        /*
         * YOUR CODE HERE
         * - ADD A NEW POST TO THE DATABASE USING THE SPECIFIED PARAMETERS
         */
	}

}