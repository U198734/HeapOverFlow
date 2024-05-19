package managers;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import models.User;
import utils.DBManager;

public class UserManager {
		
	private DBManager db = null ;
	
	public UserManager() {
		db = new DBManager();
	}
	
	public void finalize() {
		db.finalize();
	}
	
	// Comprovem que el usuari no estigui agafat
    public boolean isUserNameTaken(String userName) {
        String query = "SELECT COUNT(*) AS count FROM usuaris WHERE userName = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    // Comprovem que el email no estigui repetit
    public boolean isEmailTaken(String email) {
        String query = "SELECT COUNT(*) AS count FROM usuaris WHERE mail = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public List<String> validate(User user)
    {

        List<String> errors = new ArrayList<String>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> cvs = validator.validate(user);

        if(isUserNameTaken(user.getUserName())) errors.add("WARNING - Provided user name already exists in our database.");
        if(isEmailTaken(user.getMail())) errors.add("WARNING - Provided mail already exists in our database.");

        if(!cvs.isEmpty()) {
            for (ConstraintViolation<User> cv : cvs) {
                errors.add(cv.getPropertyPath() + ": " + cv.getMessage());
            }
        }

        return errors;
    }
		
	// Add a new user
	public void addUser(User user) {
		    String query = "INSERT INTO usuaris (userName,mail,gender,pwd,lang,personalField) VALUES (?,?,?,?,?,?)";
	        PreparedStatement statement = null; // para evitar ataques de SQL
	        try {
	            statement = db.prepareStatement(query);
	            statement.setString(1, user.getUserName());
	            statement.setString(2, user.getMail());
	            statement.setString(3, user.getGender());
	            statement.setString(4, user.getPwd());
	            statement.setString(5, user.getLang());
	            statement.setString(6, user.getPersonalField());
	            
	            statement.executeUpdate();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}
	
	/*Check if all the fields are filled correctly */
	
	// Nomes comprovem aquells que siguin required 
	public boolean isRegisterComplete(User user) {
	    return(hasValue(user.getUserName()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd()));
	}
	
	// Nomes demanem UserName i contrasenya
	public boolean isLoginComplete(User user) {
	    return(hasValue(user.getUserName()) &&
	    		hasValue(user.getPwd())
	    		);
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
}
