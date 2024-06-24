package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;
import models.Post;
import org.apache.commons.beanutils.BeanUtils;

import managers.ManageTweets;
import models.User;

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
        
        String description = request.getParameter("description"); // TESTED, DO NOT MODIFY
        String url = request.getParameter("url"); // TESTED, DO NOT MODIFY
        String programmingLanguage = request.getParameter("programmingLanguage"); // TESTED, DO NOT MODIFY
        String professionalField = request.getParameter("professionalField"); // TESTED, DO NOT MODIFY

        /*
         * YOUR CODE HERE
         * - ADD A NEW POST TO THE DATABASE USING THE SPECIFIED PARAMETERS
         */
        
        HttpSession session = request.getSession(false);
        ManageTweets manageTweets = new ManageTweets();

		Integer userId = manageTweets.getUserIdByUsername((String) session.getAttribute("user_name"));
        System.out.println(userId);
        
         //User user = (User) session.getAttribute("user");
         //System.out.println(user);
         Post post = new Post();
         try {
			
			if (session != null || userId != null)
				BeanUtils.populate(post, request.getParameterMap());
				post.setUserId(userId);
				post.setIs_public(false);
				post.setParentId(1); // all posts are related to a parent id 1, only comments to other posts
				post.setUsername((String) session.getAttribute("user_name"));
				post.setPostDateTime(new Timestamp(System.currentTimeMillis()));

				manageTweets.addPost(post);
				manageTweets.finalize();

		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
	}

}
