package models;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	// Parametres
	private String user_name = "";
	private String mail = "";
	private String gender = "";
	private String pwd = "";
	private String programming_language = "";
    private String professional_field = "Other";
    private String role = "Regular";
    
    // Errors
	private HashMap<String, String> errors;
	
	
	public User() {
		errors = new HashMap<String, String>();
	}
	
	/* --- GETTERS ---*/
	// UserName
	public String getUser_name(){
		return user_name;
	}
	
	// Mail
	public String getMail() {
		return mail;
	}
	
	// Gender
	public String getGender() {
		return this.gender;
	}
	
	// Password 
	public String getPwd() {
		return this.pwd;
	}
	
	// Programming Language
	public String getProgramming_language() {
        return this.programming_language;
    }
	
	// Personal Field
	public String getProfessional_field() {
        return this.professional_field;	 
    }
	
	public String getRole() {
		return this.role;
	}
	   
	
	/*  --- SETTERS --- */
	
	// UserName 
	public void setUser_name(String user_name) {
	    // Validem que nomes tingui lletres amb numeros
	    String pattern = "^[a-zA-Z0-9]+$";
	    if (user_name.matches(pattern)) {
	        this.user_name = user_name;
	        System.out.println(user_name);
	    } else {
	        System.err.println("El nombre de usuario debe contener solo letras y números.");
	    }
	}

	// Mail 
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
			System.out.println(mail);
		} else {
			errors.put("mail","Error in the mail.");
			System.out.println(mail);
		}
	}
	// Gender
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	// Password
	public void setPwd(String pwd) {
	    String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
	    
	    if (pwd.matches(pattern)) {
	        this.pwd = pwd;
	        System.out.println(pwd);
	    } else {
	        System.err.println("La contraseña no cumple con los requisitos. Debe contener al menos una letra minúscula, una letra mayúscula, un dígito y tener al menos 6 caracteres de longitud.");
	    }
	}
	
	// Programming Language
	public void setProgramming_language(String programming_language) {
        this.programming_language = programming_language;
    }
	
	// Personal Field
    public void setProfessional_field(String professional_field) {
        this.professional_field = professional_field;
    }
    
    // Set roll
    public void setRole(String role) {
    	this.role = role;
	}
	
	// ERRORS 
	public HashMap<String,String> getErrors() {
		return errors;
	}
	

    // Setter para errors
    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    // Método para agregar un error específico
    public void setError(String key, String message) {
        this.errors.put(key, message);
    }

    // Método para obtener un error específico
    public String getError(String key) {
        return this.errors.get(key);
    }

}
