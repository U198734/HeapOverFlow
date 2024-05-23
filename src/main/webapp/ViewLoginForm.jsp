<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


 <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 50%;
            margin: 100px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .error {
            color: #ff0000;
            font-size: 12px;
            margin-top: 5px;
        }

        .btn-submit {
            background-color: #ff4d4d;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn-submit:hover {
            background-color: #ff3333;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Login</h2>
    <form action="LoginController" method="POST">
        <!-- Campo de usuario -->
        <div class="form-group">
            <label for="user" class="w3-text-red">User id</label>
            <input type="text" id="user" name="userName" placeholder="Name" value="${user.userName}" required>
            <!-- Mostrar error de usuario -->
            <c:if test="${not empty user.errors and not empty user.errors['userName']}">
                <div class="error">${user.errors['userName']}</div>
            </c:if>
        </div>

        <!-- Campo de contraseña -->
        <div class="form-group">
            <label for="pwd" class="w3-text-red">Password</label>
            <input type="password" id="pwd" name="pwd" placeholder="Password" value="${user.pwd}" required>
            <!-- Mostrar error de contraseña -->
            <c:if test="${not empty user.errors and not empty user.errors['pwd']}">
                <div class="error">${user.errors['pwd']}</div>
            </c:if>
        </div>

        <!-- Mostrar error de inicio de sesión -->
        <c:if test="${not empty user.errors and not empty user.errors['login']}">
            <div class="error">${user.errors['login']}</div>
        </c:if>

        <!-- Botón de enviar -->
        <button type="submit" class="btn-submit" name="submit">Submit</button>
    </form>
</div>