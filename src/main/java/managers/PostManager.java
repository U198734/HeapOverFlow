package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import models.Post;
import utils.DBManager;

public class PostManager {
	
	private DBManager db = null;
	
	public PostManager() {
		try {
			db = new DBManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void finalize() {
        try {
            if (db != null) {
                db.finalize();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    

    public List<Post> getMyOwnPosts(HttpSession session) { // esto necesitaría algo de la session
        String currentUsername = (String) session.getAttribute("user_name"); 
        
        String query = "SELECT * FROM posts WHERE user_id = (SELECT id FROM users WHERE username = ?) ORDER BY postdatetime DESC";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setString(1, currentUsername);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    
                    post.setParentId(rs.getInt("pid"));
                    post.setPostId(rs.getInt("id"));
                    post.setUsername(getUsername(rs.getInt("user_id")));
                    post.setDescription(rs.getString("post_description"));
                    post.setPostDateTime(rs.getTimestamp("postdatetime"));
                    post.setUrl(rs.getString("url"));
                    post.setProgrammingLanguage(rs.getString("programming_language"));
                    post.setProfessionalField(rs.getString("professional_field"));
                    
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
    public List<Post> getPublicPosts() {
        String query = "SELECT * FROM posts WHERE is_public = true ORDER BY postdatetime DESC";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Post post = new Post();
                
                post.setParentId(rs.getInt("pid"));
                post.setPostId(rs.getInt("id"));
                post.setUsername(getUsername(rs.getInt("user_id")));
                post.setDescription(rs.getString("post_description"));
                post.setPostDateTime(rs.getTimestamp("postdatetime"));
	   			post.setUrl(rs.getString("url"));
	   			post.setProgrammingLanguage(rs.getString("programming_language"));
	   			post.setProfessionalField(rs.getString("professional_field"));
	   			
                posts.add(post);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public List<Post> getPrivatePosts() {
        String query = "SELECT * FROM posts WHERE is_public = false ORDER BY postdatetime DESC";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();

                post.setParentId(rs.getInt("pid"));
                post.setPostId(rs.getInt("id"));
                post.setUsername(getUsername(rs.getInt("user_id")));
                post.setDescription(rs.getString("post_description"));
                post.setPostDateTime(rs.getTimestamp("postdatetime"));
	   			post.setUrl(rs.getString("url"));
	   			post.setProgrammingLanguage(rs.getString("programming_language"));
	   			post.setProfessionalField(rs.getString("professional_field"));
	   			
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public void addPost(Post post) {
        String query = "INSERT INTO posts (is_public, user_id, postdatetime, post_description, url, programmingLanguage, professionalField, pid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setBoolean(1, post.getIs_public());
            statement.setInt(2, post.getUserId());
            statement.setTimestamp(3, post.getPostDateTime());
            statement.setString(4, post.getDescription());
            statement.setString(5, post.getUrl());
            statement.setString(6, post.getProgrammingLanguage());
            statement.setString(7, post.getProfessionalField());
            statement.setInt(8, post.getParentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(int postId) {
        String query = "DELETE FROM posts WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePost(Post post) {
        String query = "UPDATE posts SET is_public = ?, user_id = ?, postdatetime = ?, post_description = ?, url = ?, programmingLanguage = ?, professionalField = ?, pid = ? WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setBoolean(1, post.getIs_public());
            statement.setInt(2, post.getUserId());
            statement.setTimestamp(3, post.getPostDateTime());
            statement.setString(4, post.getDescription());
            statement.setString(5, post.getUrl());
            statement.setString(6, post.getProgrammingLanguage());
            statement.setString(7, post.getProfessionalField());
            statement.setInt(8, post.getParentId());
            statement.setInt(9, post.getPostId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public List<Post> get_dummy_posts() {
        List<Post> posts = new ArrayList<>();

        // Main post
        Post mainPost = new Post();
        mainPost.setPostId(33);
        mainPost.setUsername("iv97n_admin");
        mainPost.setDescription("Welcome to our Software Engineering project, where we are developing a web application similar to Twitter, but specifically designed for programmers. This platform will allow developers to share ideas, code, and discuss programming-related topics in a more focused and technical manner. Our goal is to create an active and useful community for programmers of all levels, where they can learn from each other, collaborate on projects, and stay updated with the latest trends in technology and software development.\r\n"
                        + "\r\n"
                        + "Thank you for your interest in our project! Feel free to explore, contribute, and become a part of this exciting community of programmers.");
        mainPost.setPostDateTime(new Timestamp(1687441845000L));
        mainPost.setUrl("https://github.com/U198734/HeapOverFlow");
        mainPost.setProgrammingLanguage("Python");
        mainPost.setProfessionalField("Machine Learning");

        // Add comments (child posts)
        for (int i = 0; i < 2; i++) {
            Post comment = new Post();
            comment.setParentId(33);  // ParentId should match the postId of the main post
            comment.setPostId(i + 1);  // Unique postId for each comment
            comment.setUsername("iv97n_admin");
            comment.setDescription("This is a sample comment " + (i + 1));
            comment.setPostDateTime(new Timestamp(1687441845000L));

            // Add comment to main post's list of comments
            mainPost.addComment(comment);
        }

        // Add the main post to the list of posts
        posts.add(mainPost);

        return posts;
    }
	
	
    // Dummy method to get username by user_id, implement accordingly
    private String getUsername(int userId) {
        String query = "SELECT user_name FROM usuaris WHERE user_id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("user_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Unknown User";
    }
}
