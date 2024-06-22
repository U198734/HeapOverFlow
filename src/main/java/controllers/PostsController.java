package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Post;
import managers.PostManager;

@WebServlet("/PostsController")
public class PostsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostsController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user_name") != null) {
            // User is logged in
            PostManager postManager = new PostManager();
            List<Post> posts = postManager.getAllPostsForLoggedInUser(); // Implement method to fetch private posts

            request.setAttribute("posts", posts);
            request.setAttribute("menu", "ViewMenuLogged.jsp");
            request.setAttribute("content", "ViewLoginDone.jsp");
        } else {
            // User is not logged in
            PostManager postManager = new PostManager();
            List<Post> posts = postManager.getAllPublicPosts(); // Implement method to fetch public posts

            request.setAttribute("posts", posts);
            request.setAttribute("menu", "ViewMenuNotLogged.jsp");
            request.setAttribute("content", "ViewRegisterForm.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
