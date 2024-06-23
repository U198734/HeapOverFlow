package models;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    // Parameters
    private int user_id;
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

    /* --- GETTERS --- */
    
    // UserID
    public int getUser_id() {
        return user_id;
    }

    // UserName
    public String getUser_name() {
        return user_name;
    }

    // Mail
    public String getMail() {
        return mail;
    }

    // Gender
    public String getGender() {
        return gender;
    }

    // Password
    public String getPwd() {
        return pwd;
    }

    // Programming Language
    public String getProgramming_language() {
        return programming_language;
    }

    // Professional Field
    public String getProfessional_field() {
        return professional_field;
    }

    // Role
    public String getRole() {
        return role;
    }

    /* --- SETTERS --- */

    // UserID
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    // UserName
    public void setUser_name(String user_name) {
        // Validate that it only contains letters and numbers
        String pattern = "^[a-zA-Z0-9]+$";
        if (user_name.matches(pattern)) {
            this.user_name = user_name;
            System.out.println(user_name);
        } else {
            System.err.println("Username should only contain letters and numbers.");
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
            errors.put("mail", "Error in the mail.");
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
            System.err.println("Password does not meet requirements. It must contain at least one lowercase letter, one uppercase letter, one digit, and be at least 6 characters long.");
        }
    }

    // Programming Language
    public void setProgramming_language(String programming_language) {
        this.programming_language = programming_language;
    }

    // Professional Field
    public void setProfessional_field(String professional_field) {
        this.professional_field = professional_field;
    }

    // Role
    public void setRole(String role) {
        this.role = role;
    }

    // ERRORS
    public HashMap<String, String> getErrors() {
        return errors;
    }

    // Setter for errors
    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    // Method to add a specific error
    public void setError(String key, String message) {
        this.errors.put(key, message);
    }

    // Method to retrieve a specific error
    public String getError(String key) {
        return this.errors.get(key);
    }
}
