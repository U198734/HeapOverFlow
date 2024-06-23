package models;
import java.sql.Timestamp;


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

	 public Post() {
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
	 

}