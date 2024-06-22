
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
		 first_dummy_post.setUsername("iv97n");
		 first_dummy_post.setDescription("Marcelo putero");
		 first_dummy_post.setPostDateTime(new Timestamp(1687441845000L));
		 
		 Post second_dummy_post = new Post();
		 second_dummy_post.setParentId(1);
		 second_dummy_post.setPostId(1);
		 second_dummy_post.setUsername("iv97n");
		 second_dummy_post.setDescription("Pablo Barrios seleccion");
		 second_dummy_post.setPostDateTime(new Timestamp(1687441845000L));
		 
		 l.add(first_dummy_post);
		 l.add(second_dummy_post);

		return  l;
	}
	
	
	
}