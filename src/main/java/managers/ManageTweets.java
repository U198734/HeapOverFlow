package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
import utils.DBManager;


public class ManageTweets {
	
	private DBManager db = null ;
	
	public ManageTweets() {
		try {
			db = new DBManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/* Add a tweet */
	public void addTweet(Tweet tweet) {
		String query = "INSERT INTO tweets (id,postdatetime,content) VALUES (?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tweet.getId());
			statement.setTimestamp(2,tweet.getPostDateTime());
			statement.setString(3,tweet.getContent());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing tweet */
	public void deleteTweet(Integer id,Integer uid) {
		String query = "DELETE FROM tweets WHERE id = ? AND uid=?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			statement.setInt(2,uid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Get tweets from a user given start and end*/
	public List<Tweet> getUserTweets(Integer uid,Integer start, Integer end) {
		 String query = "SELECT tweets.id,tweets.uid,tweets.postdatetime,tweets.content,users.name FROM tweets INNER JOIN users ON tweets.uid = users.id where tweets.uid = ? ORDER BY tweets.postdatetime DESC LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,uid);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
       		     tweet.setId(rs.getInt("id"));
				 // tweet.setUid(rs.getInt("uid")); --> Mirar que fer
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setUname(rs.getString("name"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
    public void addUpvote(Integer post_id, Integer user_id) {

        String query = "INSERT INTO upvotes (user_id, post_id) VALUES (?, ?)";

        PreparedStatement statement = null;
        try {
            statement = db.prepareStatement(query);
            statement.setInt(1, post_id);
            statement.setInt(2, user_id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addFollows(Integer user_id, Integer fuser_id) {
        String query = "INSERT INTO follows (user_id, fuser_id) VALUES (?, ?)";
        PreparedStatement statement = null;
        try {
            statement = db.prepareStatement(query);
            statement.setInt(1, user_id);
            statement.setInt(2, user_id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void addComment(Integer user_id, String comment, Integer pid) {
        String query = "INSERT INTO posts (is_public, user_id, postdatetime, post_description, url, pid) VALUES (?, ?, NOW(), ?, NULL, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setBoolean(1, true); // si se puede añadir un comment es porque son públicos, por tanto todos son true
            statement.setInt(2, user_id);
            statement.setString(3, comment);
            statement.setInt(4, pid);
            
            System.out.println("Comment added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public void addPost(Integer user_id, String postDescription, String url, String programming_language, String professional_field, Integer pid, boolean isPublic) {
        String query = "INSERT INTO posts (is_public, user_id, postdatetime, post_description, url, programming_language, professional_field, pid) VALUES (?, ?, NOW(), ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setBoolean(1, isPublic);
            statement.setInt(2, user_id);
            statement.setString(3, postDescription);
            statement.setString(4, url);
            statement.setString(5, programming_language);
            statement.setString(6, professional_field);
            statement.setInt(7, pid);
            
            statement.executeUpdate();
            System.out.println("Post added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void deletePost(Integer post_id) {
        String query = "DELETE FROM posts WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, post_id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Post deleted successfully!");
            } else {
                System.out.println("No post found with post_id " + post_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Integer getUserIdFromPostId(Integer post_id) {
        String query = "SELECT user_id FROM posts WHERE id = ?";
        PreparedStatement statement = null;
        
        try {
        	statement = db.prepareStatement(query);
        	statement.setInt(1, post_id);
            
        	ResultSet rs = statement.executeQuery();

        	
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return rs;
    }



}
