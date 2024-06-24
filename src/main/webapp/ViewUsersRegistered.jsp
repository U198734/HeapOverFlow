<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #ff8c00; /* Naranja */
            text-align: center;
            margin-bottom: 20px;
            font-size: 2em; /* Tamaño grande */
        }

        .user-card {
            position: relative;
            background-color: #fff;
            border: 1px solid #ffa500; /* Borde naranja */
            border-radius: 5px;
            margin-bottom: 20px;
            padding: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .user-card h3 {
            margin-top: 0;
            color: #ff4500; /* Naranja oscuro */
        }

        .user-card p {
            margin: 5px 0;
            color: #555; /* Texto gris */
        }

        .user-card span {
            font-weight: bold;
            color: #333; /* Texto oscuro */
        }

        .user-card .actions {
            position: absolute;
            top: 15px;
            right: 15px;
        }

        .user-card .actions .w3-button {
            margin-left: 10px;
            cursor: pointer;
        }

        .w3-button.w3-red {
            background-color: #ff6347; /* Rojo anaranjado */
            color: #fff;
        }

        .w3-button.w3-red:hover {
            background-color: #ff4500; /* Rojo anaranjado más oscuro */
        }

        .w3-button.w3-blue {
            background-color: #007bff; /* Azul */
            color: #fff;
        }

        .w3-button.w3-blue:hover {
            background-color: #0056b3; /* Azul más oscuro */
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
	    function editUser(user_name) {
	        $.ajax({
	            url: 'UsersRegisteredController',
	            type: 'POST',
	            data: {
	                action: 'edit',
	                user_name: user_name
	            },
	            success: function(response) {
	                $('body').html(response);
	            },
	            error: function(xhr, status, error) {
	                alert('Error editing user: ' + error);
	            }
	        });
	    }
    

        function deleteUser(user_name) {
            if (confirm('Are you sure you want to delete user ' + user_name + '?')) {
                $.ajax({
                    url: 'UsersRegisteredController',
                    type: 'POST',
                    data: {
                        action: 'delete',
                        user_name: user_name
                    },
                    success: function(response) {
                        // Refresh the list of users
                        // $(content).html(response); // 'content' variable not defined in original code
                        console.log('User deleted successfully');
                    },
                    error: function(xhr, status, error) {
                        alert('Error deleting user: ' + error);
                    }
                });
            }
        }
    </script>
</head>

<body>
<br><br><br>
    <div class="container">
        <h2>Usuarios Registrados</h2>
        <c:forEach var="user" items="${users}">
            <div class="user-card">
                <div class="actions">
                    <button class="w3-button w3-red" onclick="editUser('${user.user_name}')">Editar</button>
                    <button class="w3-button w3-blue" onclick="deleteUser('${user.user_name}')">Eliminar</button>
                </div>
                <h3 style="color: #ff4500;">${user.user_name}</h3> <!-- Naranja oscuro -->
                <p><span>Email:</span> ${user.mail}</p>
                <p><span>Gender:</span> ${user.gender}</p>
                <p><span>Programming Language:</span> ${user.programming_language}</p>
                <p><span>Personal Field:</span> ${user.professional_field}</p>
                <p><span>Role:</span> ${user.role}</p>
            </div>
        </c:forEach>
    </div>
</body>

</html>
