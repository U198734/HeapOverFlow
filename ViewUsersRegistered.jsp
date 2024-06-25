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
            background-color: #f9f9f9;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
            background-color: #fff3e0; /* Fondo naranja claro */
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        h2 {
            color: #e65100; /* Naranja oscuro */
        }
        .user-card {
            position: relative;
            background-color: #ffe0b2; /* Fondo naranja claro */
            border: 1px solid #ffcc80; /* Borde naranja más oscuro */
            border-radius: 10px;
            margin-bottom: 20px;
            padding: 15px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .user-card h3 {
            margin-top: 0;
            color: #e65100; /* Naranja oscuro */
        }
        .user-card p {
            margin: 5px 0;
            color: #333;
        }
        .user-card span {
            font-weight: bold;
        }
        .user-card .actions {
            position: absolute;
            top: 15px;
            right: 15px;
        }
        .user-card .actions .w3-button {
            margin-left: 10px;
        }
        .w3-button {
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .w3-red {
            background-color: #e53935; /* Rojo oscuro */
            color: white;
        }
        .w3-red:hover {
            background-color: #b71c1c; /* Rojo más oscuro */
        }
        .w3-blue {
            background-color: #1e88e5; /* Azul oscuro */
            color: white;
        }
        .w3-blue:hover {
            background-color: #0d47a1; /* Azul más oscuro */
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
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
                        $(content).html(response);
                    },
                    error: function(xhr, status, error) {
                        alert('Error deleting user: ' + error);
                    }
                });
            }
        }
        
        function promoteUser(user_name) {
            if (confirm('You want to promote' + user_name + '?')) {
                $.ajax({
                    url: 'UsersRegisteredController',
                    type: 'POST',
                    data: {
                        action: 'promote',
                        user_name: user_name
                    },
                    success: function(response) {
                        // Refresh the list of users
                        $(content).html(response);
                    },
                    error: function(xhr, status, error) {
                        alert('Error promoting user: ' + error);
                    }
                });
            }
        }
        
        function demoteUser(user_name) {
            if (confirm('You want to demote' + user_name + '?')) {
                $.ajax({
                    url: 'UsersRegisteredController',
                    type: 'POST',
                    data: {
                        action: 'demote',
                        user_name: user_name
                    },
                    success: function(response) {
                        // Refresh the list of users
                        $(content).html(response);
                    },
                    error: function(xhr, status, error) {
                        alert('Error demoting user: ' + error);
                    }
                });
            }
        }
        <!-- nou --> 
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
    </script>
</head>
<body>
    <br><br><br><br>
    <div class="container">
        <h2>Usuarios Registrados</h2>
        <c:forEach var="user" items="${users}">
            <div class="user-card">
                <div class="actions">
					<button class="w3-button w3-orange" onclick="promoteUser('${user.user_name}')">Promote</button>
					<button class="w3-button w3-green" onclick="demoteUser('${user.user_name}')">Demote</button>
                    <button class="w3-button w3-red" onclick="editUser('${user.user_name}')">Edit</button>
                    <button class="w3-button w3-blue" onclick="deleteUser('${user.user_name}')">Delete</button>
                </div>
                <h3>${user.user_name}</h3>
                <p><span>Email:</span> ${user.mail}</p>
                <p><span>Gender:</span> ${user.gender}</p>
                <p><span>Programming Language:</span> ${user.programming_language}</p>
                <p><span>Professional Field:</span> ${user.professional_field}</p>
                <p><span>Role:</span> ${user.role}</p>
            </div>
        </c:forEach>
    </div>
</body>
