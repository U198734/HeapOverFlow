
// GESTIONAR LAS PETICIONES DEPENDIENDO DE SI ESTAN LOGEADOS O NO. CLASE DE REFERENCIA EN EL TEMPLATE: GetUserTweets.java

package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import models.Post;
import managers.PostManager;

/**
 * Servlet implementation class MenuController
 */
@WebServlet("/PostsController")
public class PostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostsController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PostManager postManager = new PostManager();
		List<Post> posts = postManager.get_dummy_posts();
		request.setAttribute("posts", posts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnPosts.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
