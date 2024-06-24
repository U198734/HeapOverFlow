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

import ManageTweets.java;


/**
 * Servlet implementation class MenuController
 */
@WebServlet("/DelPost")
public class DelPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelPost() {
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
		
		String postId = request.getParameter("id"); //COMRPOBADO QUE FUNCIONA, NO TOCAR
		
		/*
		 * YOUR CODE HERE
		 * - BORRAR POST DE LA BASE DE DATOS
		 * - COMPROBAR QUE EL USERID DE LA SESION CONCUERDA CON LA PERSONA QUE CRE� EL POST
		 */
		
		HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("user_id");
        ManageTweets manageTweets = new ManageTweets();
        

        if (postId != null && userId != null) {
        	Integer userIdFromPostId = manageTweets.getUserIdFromPostId(Integer.parseInt(postId));
        	if (userIdFromPostId == userId) {
        		manageTweets.deletePost(Integer.parseInt(postId));
        	}
        	else {
        		System.out.println("userIdFromPostId and userId from session do not match");
        	}
            // Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Handle the error: either postId or userId is missing or session is inactive
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

		
	}

}
