<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        
        .container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
        .user-card {
            position: relative;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 20px;
            padding: 15px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .user-card h3 {
            margin-top: 0;
            color: #007bff;
        }
        .user-card p {
            margin: 5px 0;
        }
        .user-card span {
            font-weight: bold;
        }
        .user-card .actions {
            position: absolute;
            top: 15px;
            right: 15px;
        }
        .user-card .actions .fa {
            margin-left: 10px;
            cursor: pointer;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function deleteUser(userName) {
            if (confirm('Are you sure you want to delete user ' + userName + '?')) {
                $.ajax({
                    url: 'UsersRegisteredController',
                    type: 'POST',
                    data: {
                        action: 'delete',
                        userName: userName
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
    </script>
</head>
<body>
    <div class="container">
        <h2>Usuarios Registrados</h2>
        <c:forEach var="user" items="${users}">
            <div class="user-card">
                <div class="actions">
                    <i class="fas fa-edit" title="Edit User" onclick="editUser('${user.userName}')"></i>
                    <i class="fas fa-trash" title="Delete User" onclick="deleteUser('${user.userName}')"></i>
                </div>
                <h3>${user.userName}</h3>
                <p><span>Email:</span> ${user.mail}</p>
                <p><span>Gender:</span> ${user.gender}</p>
                <p><span>Programming Language:</span> ${user.lang}</p>
                <p><span>Personal Field:</span> ${user.personalField}</p>
                <p><span>Role:</span> ${user.role}</p>
            </div>
        </c:forEach>
    </div>
</body>

