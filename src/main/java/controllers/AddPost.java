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

import managers.ManageTweets;



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
        
		HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("user_id");
        ManageTweets manageTweets = new ManageTweets();
        
        if (userId != null) {
        	manageTweets.addPost(userId,content,url,programmingLanguage, professionalField, null, true); // la última variable es is_public, he puesto que todas lo son porque solo puedes comentar en public posts
        
            // Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Handle the error: either postId or userId is missing or session is inactive
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
	}

}
