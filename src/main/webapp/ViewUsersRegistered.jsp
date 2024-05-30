<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios Registrados</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: "Arial", sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
        }
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
                <p><span>Language:</span> ${user.lang}</p>
                <p><span>Field:</span> ${user.personalField}</p>
                <p><span>Role:</span> ${user.roll}</p>
            </div>
        </c:forEach>
    </div>

</body>
</html>
