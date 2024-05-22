package models;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	// Parametres
	private String userName = "";
	private String mail = "";
	private String gender = "";
	private String pwd = "";
	private String lang = "";
    private String personalField = "";
    private String roll = "Regular";
    
    // Errors
	private HashMap<String, String> errors;
	
	
	public User() {
		errors = new HashMap<String, String>();
	}
	
	/* --- GETTERS ---*/
	// UserName
	public String getUserName(){
		return userName;
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
	public String getLang() {
        return this.lang;
    }
	
	// Personal Field
	public String getPersonalField() {
        return this.personalField;	 
    }
	
	public String getRoll() {
		return this.roll;
	}
	   
	
	/*  --- SETTERS --- */
	
	// UserName 
	public void setUserName(String user) {
	    // Validem que nomes tingui lletres amb numeros
	    String pattern = "^[a-zA-Z0-9]+$";
	    if (user.matches(pattern)) {
	        this.userName = user;
	        System.out.println(user);
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
	public void setLang(String lang) {
        this.lang = lang;
    }
	
	// Personal Field
    public void setPersonalField(String personalField) {
        this.personalField = personalField;
    }
    
    // Set roll
    public void setRoll(String roll) {
    	this.roll = roll;
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
