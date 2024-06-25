package managers;


import java.sql.Connection;
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
    public boolean isUserNameTaken(String user_name) {
        String query = "SELECT COUNT(*) AS count FROM usuaris WHERE user_name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, user_name);
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
        String query = "INSERT INTO usuaris (user_name, mail, gender, pwd, programming_language, professional_field, role) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = null; // para evitar ataques de SQL
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, user.getUser_name());
            statement.setString(2, user.getMail());
            statement.setString(3, user.getGender());
            statement.setString(4, user.getPwd());
            statement.setString(5, user.getProgramming_language());
            statement.setString(6, user.getProfessional_field());
            statement.setString(7, user.getRole());

            
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

        if (!hasValue(user.getUser_name())) {
            user.setError("user_name", "ERROR - User name is required.");
            isComplete = false;
        } else if (isUserNameTaken(user.getUser_name())) {
            user.setError("user_name", "WARNING - Provided user name already exists in our database.");
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
    public boolean validateLogin(String user_name, String pwd) {
        String query = "SELECT pwd FROM usuaris WHERE user_name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, user_name);
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
    
    
    // Funcio per agafar el role de la base de datos
    
    public String getUserRole(String user_name) {
        String query = "SELECT role FROM usuaris WHERE user_name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, user_name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
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
        if (!hasValue(user.getUser_name())) {
            user.setError("user_name", "ERROR - User name is required.");
            isComplete = false;
        }

        if (!hasValue(user.getPwd())) {
            user.setError("pwd", "ERROR - Password is required.");
            isComplete = false;
        }
        // Cridem a la funcio que hem creat per aixi validar que hi ha un user amb aquella contra
        if (isComplete) {
            if (!validateLogin(user.getUser_name(), user.getPwd())) {
                user.setError("login", "Invalid username or password.");
                isComplete = false;
            }
            else {
            	user.setRole(getUserRole(user.getUser_name()));
                System.out.println(getUserRole(user.getUser_name()));

            }
        }

        return isComplete;
    }
    
    
 // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        String query = "SELECT user_name, mail, gender, programming_language, professional_field, role FROM usuaris ORDER BY user_name ASC";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = db.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUser_name(rs.getString("user_name"));
                user.setMail(rs.getString("mail"));
                user.setGender(rs.getString("gender"));
                user.setProgramming_language(rs.getString("programming_language"));
                user.setProfessional_field(rs.getString("professional_field"));
                user.setRole(rs.getString("role"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total users fetched: " + userList.size());
        return userList;
    }

    
 // Nuevo método para eliminar usuario por username
    public void deleteUserByUserName(String user_name) {
        String query = "DELETE FROM usuaris WHERE user_name = ?";
        PreparedStatement statement = null;
        try {
            statement = db.prepareStatement(query);
            statement.setString(1, user_name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	
	/* INFO DEL USUARI*/
	public User getUserInfo(String user_name) {
	    String query = "SELECT user_name, mail, gender, programming_language, professional_field FROM usuaris WHERE user_name = ?;";
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    User user = null;
	    try {
	        statement = db.prepareStatement(query);
	        statement.setString(1, user_name);
	        rs = statement.executeQuery();
	        if (rs.next()) {
	            user = new User();
	            user.setUser_name(rs.getString("user_name"));
	            user.setMail(rs.getString("mail"));
	            user.setGender(rs.getString("gender"));
	            user.setProgramming_language(rs.getString("programming_language"));
	            user.setProfessional_field(rs.getString("professional_field"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return user;
	}
	
	public boolean updateUser(User user) {
        DBManager dbManager = new DBManager();
        String query = "UPDATE usuaris SET mail = ?, gender = ?, programming_language = ?, professional_field = ? WHERE user_name = ?";
        
        try {
            PreparedStatement stmt = dbManager.prepareStatement(query);
            stmt.setString(1, user.getMail());
            stmt.setString(2, user.getGender());
            stmt.setString(3, user.getProgramming_language());
            stmt.setString(4, user.getProfessional_field());
            stmt.setString(5, user.getUser_name());

            int rowsUpdated = stmt.executeUpdate();
            stmt.close();
            dbManager.finalize();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

	public void deleteUserByUserName(int user_name) {
	    String query = "DELETE FROM usuaris WHERE user_name = ?";
	    DBManager dbManager = new DBManager();
	    try (PreparedStatement statement = dbManager.prepareStatement(query)) {
	        statement.setInt(1, user_name);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbManager.finalize();
	    }
	}
	
	public User getUserById(int userId) {
	    String query = "SELECT * FROM usuaris WHERE user_id = ?";
	    DBManager dbManager = new DBManager();
	    try (PreparedStatement statement = dbManager.prepareStatement(query)) {
	        statement.setInt(1, userId);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                User user = new User();
	                user.setUser_id(resultSet.getInt("user_id"));
	                user.setUser_name(resultSet.getString("user_name"));
	                user.setMail(resultSet.getString("mail"));
	                user.setGender(resultSet.getString("gender"));
	                user.setPwd(resultSet.getString("pwd"));
	                user.setProgramming_language(resultSet.getString("programming_language"));
	                user.setProfessional_field(resultSet.getString("professional_field"));
	                return user;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbManager.finalize();
	    }
	    return null;
	}
	
	
	public void changeUserRoleToAdmin(String userName) {
        String query = "UPDATE usuaris SET role = 'Admin' WHERE user_name = ? AND role = 'Regular'";
        DBManager dbManager = new DBManager();
	    try (
	    	PreparedStatement statement = dbManager.prepareStatement(query)){ 
            
	    	statement.setString(1, userName);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new IllegalArgumentException("User not found or already an Admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void changeUserRoleToRegular(String userName) {
		String query = "UPDATE usuaris SET role = 'Regular' WHERE user_name = ? AND role = 'Admin'";
        DBManager dbManager = new DBManager();
	    try (
	    	PreparedStatement statement = dbManager.prepareStatement(query)){ 
            
	    	statement.setString(1, userName);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new IllegalArgumentException("User not found or already an Regular");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public int getUserIdByUsername(String userName) throws SQLException {
        int userId = -1;
        String query = "SELECT user_id FROM usuaris WHERE user_name = ?";
        DBManager dbManager = new DBManager();

        try (
        	PreparedStatement statement = dbManager.prepareStatement(query)) {
        	statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving user ID for username: " + userName, e);
        }
        
        return userId;
    }
	
}
