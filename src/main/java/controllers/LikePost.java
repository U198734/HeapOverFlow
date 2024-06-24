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

import managers.PostManager;

import managers.ManageTweets;
import managers.ManageTweets;

/**
 * Servlet implementation class MenuController
 */
@WebServlet("/LikePost")
public class LikePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikePost() {
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
		 * - COGER LA ID DEL USUARIO QUE HA RELIZADO LA ACCI�N A PARTIR DE LA SESI�N
		 * - GUARDAR EN LA BASE DE DATOS LA INTERACCI�N "LIKE" ENTRE POST Y USUARIO
		 * - TEN EN CUENTA QUE SOLO PODRIAN REALIZAR POSTS A ESTE ENDPOINT USUARIOS CON SESION ACTIVA, ES DECIR, LOS POSTS DE UNREGISTERED USERS DEBES DE CAPTURARLOS I IGNORARLOS IF(...) PASS
		 */
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("user_id");
        ManageTweets manageTweets = new ManageTweets();


        if (postId != null && userId != null) {
        	manageTweets.addUpvote(Integer.parseInt(postId), userId);
            
            // Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Handle the error: either postId or userId is missing or session is inactive
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
