package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Post;
import models.User;
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

    // Method to fetch all public posts from the database
    public List<Post> getAllPublicPosts() {
        String query = "SELECT * FROM posts WHERE professional_field = 'Other' ORDER BY post_datetime DESC";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("post_id"));
                post.setParentId(rs.getInt("parent_id"));
                post.setUsername(rs.getString("username"));
                post.setDescription(rs.getString("description"));
                post.setUrl(rs.getString("url"));
                post.setProgrammingLanguage(rs.getString("programming_language"));
                post.setProfessionalField(rs.getString("professional_field"));
                post.setPostDateTime(rs.getTimestamp("post_datetime"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // Method to fetch all private posts for a logged-in user
    public List<Post> getAllPrivatePostsForUser(int userId) {
        String query = "SELECT * FROM posts WHERE username = ? ORDER BY post_datetime DESC";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query)) {
            User user = getUserById(userId);
            if (user != null) {
                statement.setString(1, user.getUser_name());
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Post post = new Post();
                    post.setPostId(rs.getInt("post_id"));
                    post.setParentId(rs.getInt("parent_id"));
                    post.setUsername(rs.getString("username"));
                    post.setDescription(rs.getString("description"));
                    post.setUrl(rs.getString("url"));
                    post.setProgrammingLanguage(rs.getString("programming_language"));
                    post.setProfessionalField(rs.getString("professional_field"));
                    post.setPostDateTime(rs.getTimestamp("post_datetime"));
                    posts.add(post);
                }
            } else {
                System.err.println("User with id " + userId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // Method to add a new post to the database
    public void addPost(Post post) {
        String query = "INSERT INTO posts (parent_id, username, description, url, programming_language, professional_field, post_datetime) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, post.getParentId());
            statement.setString(2, post.getUsername());
            statement.setString(3, post.getDescription());
            statement.setString(4, post.getUrl());
            statement.setString(5, post.getProgrammingLanguage());
            statement.setString(6, post.getProfessionalField());
            statement.setTimestamp(7, post.getPostDateTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a post from the database
    public void deletePost(int postId) {
        String query = "DELETE FROM posts WHERE post_id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing post in the database
    public void updatePost(Post post) {
        String query = "UPDATE posts SET parent_id = ?, username = ?, description = ?, url = ?, programming_language = ?, professional_field = ?, post_datetime = ? WHERE post_id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, post.getParentId());
            statement.setString(2, post.getUsername());
            statement.setString(3, post.getDescription());
            statement.setString(4, post.getUrl());
            statement.setString(5, post.getProgrammingLanguage());
            statement.setString(6, post.getProfessionalField());
            statement.setTimestamp(7, post.getPostDateTime());
            statement.setInt(8, post.getPostId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to fetch user details by user ID
    private User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUser_name(rs.getString("user_name"));
                user.setMail(rs.getString("mail"));
                user.setGender(rs.getString("gender"));
                user.setPwd(rs.getString("pwd"));
                user.setProgramming_language(rs.getString("programming_language"));
                user.setProfessional_field(rs.getString("professional_field"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
