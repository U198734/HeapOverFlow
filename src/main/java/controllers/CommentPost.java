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
@WebServlet("/CommentPost")
public class CommentPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentPost() {
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
		
        String postId = request.getParameter("id"); // COMPROBADO QUE FUNCIONA, NO TOCAR
        String comment = request.getParameter("comment"); // COMPROBADO QUE FUNCIONA, NO TOCAR
        
        System.out.println(comment);
        
        /*
         * YOUR CODE HERE
         * - A�ADIR COMENTARIO A LA BASE DE DATOS COMO UN POST MAS
         * - EL PARENTID DEL NUEVO POST SERA EL POSTID DEL COMENTARIO ORIGINAL "postId"
         */
		HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("user_id");
        ManageTweets manageTweets = new ManageTweets();
        
        if (userId != null) {
        	manageTweets.addComment(userId,comment,Integer.parseInt(postId));
        
            // Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Handle the error: either postId or userId is missing or session is inactive
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
		
	}

}
