<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Usuario</title>
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
        
        .user-card p {
            margin: 5px 0;
        }
        .user-card span {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>PERFIL</h2>
        <h3>Hola  ${user.userName}!</h3>
        <div class="user-card">
            <p><span>Email:</span> ${user.mail}</p>
            <p><span>Gender:</span> ${user.gender}</p>
            <p><span>Programming Language:</span> ${user.lang}</p>
            <p><span>Personal Field:</span> ${user.personalField}</p>
        </div>
    </div>
</body>

