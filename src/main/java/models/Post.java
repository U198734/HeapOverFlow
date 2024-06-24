package models;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


// ACABAR DE IMPLEMENTAR GETTERS Y SETTERS. CLASE DE REFERENCIA EN EL TEMPLATE Tweet.java



public class Post implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;

	 private boolean is_public;
	 private int userId;
	 private int parentId;
	 private int postId; 
	 private String username;
	 private String description;
	 private String url;
	 private String programmingLanguage;
	 private String professionalField;
	 private Timestamp postDateTime;
	 private List<Post> comments;

	 public Post() {
		 this.comments = new ArrayList<>();
	 }
	 
     // Getter para is_public
     public boolean getIs_public() {
         return is_public;
     }

     // Setter para is_public
     public void setIs_public(boolean is_public) {
         this.is_public = is_public;
     }

     // Getter para userId
     public int getUserId() {
         return userId;
     }

     // Setter para userId
     public void setUserId(int userId) {
         this.userId = userId;
     }
    
	 public Integer getParentId() {
		 return this.parentId;
	 }
	 
	 public void setParentId(Integer parentId) {
		 this.parentId = parentId;
	 }

	 public Integer getPostId() {
		 return this.postId;
	 }
	 
	 public void setPostId(Integer postId) {
		 this.postId = postId;
	 }
	 
	 public String getUsername() {
		 return this.username;
	 }
	 
	 public void setUsername(String username) {
		 this.username = username;
	 }

	 public String getDescription() {
		 return this.description;
	 }
	 
	 public void setDescription(String description) {
		 this.description = description;
	 }
	 
	 
	 public Timestamp getPostDateTime() {
		 return this.postDateTime;
	 }
	 public void setPostDateTime(Timestamp postDateTime) {
		 this.postDateTime = postDateTime;
	 }
	 
	 public String getUrl() {
		 return this.url;
	 }
	 
	 public void setUrl(String url) {
		 this.url = url;
	 }
	 
	 public String getProgrammingLanguage() {
		 return this.programmingLanguage;
	 }
	 
	 public void setProgrammingLanguage(String programmingLanguage) {
		 this.programmingLanguage = programmingLanguage;
	 }
	 
	 public String getProfessionalField() {
		 return this.professionalField;
	 }
	 
	 public void setProfessionalField(String professionalField) {
		 this.professionalField = professionalField;
	 }
	 
	 public List<Post> getComments() {
		 return this.comments;
	 }
	 
	 public void setComments(List<Post> comments) {
		 this.comments = comments;
	 }
	 
	    // Add a comment to the list of comments
	 public void addComment(Post comment) {
	        this.comments.add(comment);
	    }
	 

}
