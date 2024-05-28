package managers;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
*/
import java.util.List;

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
	
	// FUNCIONS PEL REGISTRE
	
	// Comprobamos que el usuario no esté ocupado
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

    // Comprobamos que el email no esté repetido
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

    // Añadir un nuevo usuario
    public void addUser(User user) {
        String query = "INSERT INTO usuaris (userName, mail, gender, pwd, lang, personalField, roll) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = null; // para evitar ataques de SQL
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getMail());
            statement.setString(3, user.getGender());
            statement.setString(4, user.getPwd());
            statement.setString(5, user.getLang());
            statement.setString(6, user.getPersonalField());
            statement.setString(7, user.getRoll());

            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Verificar si todos los campos requeridos están llenos y si el usuario o el correo están ocupados
    public boolean isRegisterComplete(User user) {
        boolean isComplete = true;

        // Limpiar errores previos
        user.getErrors().clear();

        if (!hasValue(user.getUserName())) {
            user.setError("userName", "ERROR - User name is required.");
            isComplete = false;
        } else if (isUserNameTaken(user.getUserName())) {
            user.setError("userName", "WARNING - Provided user name already exists in our database.");
            isComplete = false;
        }

        if (!hasValue(user.getMail())) {
            user.setError("mail", "ERROR - Mail is required.");
            isComplete = false;
        } else if (isEmailTaken(user.getMail())) {
            user.setError("mail", "WARNING - Provided mail already exists in our database.");
            isComplete = false;
        }

        if (!hasValue(user.getPwd())) {
            user.setError("pwd", "ERROR - Password is required.");
            isComplete = false;
        }

        return isComplete;
    }

	
    // FUNCIONS PEL LOGIN 
    // Comprovem que existeixi el UserName i que la contrasenya sigui la corresponent en el UserName
    public boolean validateLogin(String userName, String pwd) {
        String query = "SELECT pwd FROM usuaris WHERE userName = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPwd = resultSet.getString("pwd");
                return storedPwd.equals(pwd); // Compara la contraseña almacenada con la proporcionada
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
        return false; // Si no se encuentra el usuario o hay un error, devuelve false
    }
    
    
    // Funcio per agafar el roll de la base de datos
    
    public String getUserRole(String userName) {
        String query = "SELECT roll FROM usuaris WHERE userName = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("roll");
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
        return null; // Si no se encuentra el usuario o hay un error, devuelve null
    }

    
    
   
    
    
    public boolean isLoginComplete(User user) {
        boolean isComplete = true;

        user.getErrors().clear(); // Netegem la variable

        // Comprovacions del backend
        if (!hasValue(user.getUserName())) {
            user.setError("userName", "ERROR - User name is required.");
            isComplete = false;
        }

        if (!hasValue(user.getPwd())) {
            user.setError("pwd", "ERROR - Password is required.");
            isComplete = false;
        }
        // Cridem a la funcio que hem creat per aixi validar que hi ha un user amb aquella contra
        if (isComplete) {
            if (!validateLogin(user.getUserName(), user.getPwd())) {
                user.setError("login", "Invalid username or password.");
                isComplete = false;
            }
            else {
            	user.setRoll(getUserRole(user.getUserName()));
                System.out.println(getUserRole(user.getUserName()));

            }
        }

        return isComplete;
    }
    
    
 // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        String query = "SELECT userName, mail, gender, lang, personalField, roll FROM usuaris ORDER BY userName ASC";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("userName"));
                user.setMail(rs.getString("mail"));
                user.setGender(rs.getString("gender"));
                user.setLang(rs.getString("lang"));
                user.setPersonalField(rs.getString("personalField"));
                user.setRoll(rs.getString("roll"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total users fetched: " + userList.size());
        return userList;
    }

    
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
}
