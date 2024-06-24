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

        .container h2 {
            text-align: center;
            color: #ff8c00; /* Color naranja */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Fuente profesional */
            font-size: 2.5em; /* Tamaño grande */
            letter-spacing: 1px; /* Espaciado entre letras */
            margin-bottom: 20px; /* Espacio inferior */
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            color: #333; /* Color de etiquetas */
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ffa500; /* Borde naranja */
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px; /* Tamaño de fuente */
        }

        .error {
            color: #ff6347; /* Color de error */
            font-size: 12px;
            margin-top: 5px;
        }

        .btn-submit {
            background-color: #ffa500; /* Fondo naranja */
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-transform: uppercase; /* Convertir texto a mayúsculas */
        }

        .btn-submit:hover {
            background-color: #ff8c00; /* Hover naranja más oscuro */
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Login</h2>
    <form action="LoginController" method="POST">
        <!-- Campo de usuario -->
        <div class="form-group">
            <label for="user">User name</label>
            <input type="text" id="user" name="user_name" placeholder="Name" value="${user.user_name}" required>
            <!-- Mostrar error de usuario -->
            <c:if test="${not empty user.errors and not empty user.errors['user_name']}">
                <div class="error">${user.errors['user_name']}</div>
            </c:if>
        </div>

        <!-- Campo de contraseña -->
        <div class="form-group">
            <label for="pwd">Password</label>
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
        <input type="submit" class="btn-submit" name="submit" value="Submit">
    </form>
</div>
