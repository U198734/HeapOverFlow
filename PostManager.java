package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.Post;
import utils.DBManager;

public class PostManager {

    private DBManager db;

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

    // Method to fetch all public posts from the database
    public List<Post> getPublicPosts() {
        String query = "SELECT * FROM posts WHERE is_public = true ORDER BY postdatetime DESC";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("id"));
                post.setPublic(rs.getBoolean("is_public"));
                post.setUserId(rs.getInt("user_id"));
                post.setPostDateTime(rs.getTimestamp("postdatetime"));
                post.setDescription(rs.getString("post_description"));
                post.setUrl(rs.getString("url"));
                post.setProgrammingLanguage(rs.getString("programmingLanguage"));
                post.setProfessionalField(rs.getString("professionalField"));
                post.setParentId(rs.getInt("pid"));
                posts.add(post);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // Method to fetch all private posts from the database
    public List<Post> getPrivatePosts() {
        String query = "SELECT * FROM posts WHERE is_public = false ORDER BY postdatetime DESC";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("id"));
                post.setPublic(rs.getBoolean("is_public"));
                post.setUserId(rs.getInt("user_id"));
                post.setPostDateTime(rs.getTimestamp("postdatetime"));
                post.setDescription(rs.getString("post_description"));
                post.setUrl(rs.getString("url"));
                post.setProgrammingLanguage(rs.getString("programmingLanguage"));
                post.setProfessionalField(rs.getString("professionalField"));
                post.setParentId(rs.getInt("pid"));
                posts.add(post);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // Method to add a new post to the database
    public void addPost(Post post) {
        String query = "INSERT INTO posts (public, user_id, postdatetime, content, pid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setBoolean(1, post.isPublic());
            statement.setInt(2, post.getUserId());
            statement.setTimestamp(3, post.getPostDateTime());
            statement.setString(4, post.getDescription());
            statement.setString(5, post.getUrl());
            statement.setInt(6, post.getParentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a post from the database
    public void deletePost(int postId) {
        String query = "DELETE FROM posts WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing post in the database
    public void updatePost(Post post) {
        String query = "UPDATE posts SET public = ?, user_id = ?, postdatetime = ?, content = ?, pid = ? WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setBoolean(1, post.isPublic());
            statement.setInt(2, post.getUserId());
            statement.setTimestamp(3, post.getPostDateTime());
            statement.setString(4, post.getDescription());
            statement.setString(5, post.getUrl());
            statement.setInt(6, post.getParentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
