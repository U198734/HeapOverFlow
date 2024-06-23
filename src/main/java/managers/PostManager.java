
// IMPLEMENTAR TODOS LOS METODOS DE CONEXION CON LA BASE DE DATOS. CLASE DE REFERENCIA EN EL TEMPLATE: ManageTweets.java

package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import models.Post;
import utils.DBManager;


public class PostManager {
	
	private DBManager db = null ;
	
	public PostManager() {
		try {
			db = new DBManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Post> get_dummy_posts() {
		 List<Post> l = new ArrayList<Post>();
		 
		 Post first_dummy_post = new Post();
		 first_dummy_post.setParentId(1);
		 first_dummy_post.setPostId(1);
		 first_dummy_post.setUsername("iv97n_admin");
		 first_dummy_post.setDescription("Welcome to our Software Engineering project, where we are developing a web application similar to Twitter, but specifically designed for programmers. This platform will allow developers to share ideas, code, and discuss programming-related topics in a more focused and technical manner. Our goal is to create an active and useful community for programmers of all levels, where they can learn from each other, collaborate on projects, and stay updated with the latest trends in technology and software development.\r\n"
		 		+ "\r\n"
		 		+ "Thank you for your interest in our project! Feel free to explore, contribute, and become a part of this exciting community of programmers.");
		 first_dummy_post.setPostDateTime(new Timestamp(1687441845000L));
		 first_dummy_post.setUrl("https://github.com/U198734/HeapOverFlow");
		 first_dummy_post.setProgrammingLanguage("Python");
		 first_dummy_post.setProfessionalField("Machine Learning");
		 
		 l.add(first_dummy_post);
		 
		 for(int i=0; i<5; i++) {
			 Post second_dummy_post = new Post();
			 second_dummy_post.setParentId(1);
			 second_dummy_post.setPostId(1);
			 second_dummy_post.setUsername("iv97n_admin");
			 second_dummy_post.setDescription("This is a sample tweet");
			 second_dummy_post.setPostDateTime(new Timestamp(1687441845000L));
			 second_dummy_post.setUrl("https://www.youtube.com/watch?v=xm3YgoEiEDc");
			 second_dummy_post.setProgrammingLanguage("Python");
			 second_dummy_post.setProfessionalField("Machine Learning");
			 
			 l.add(second_dummy_post);
		 }
	
		return  l;
	}
	
	
	
}