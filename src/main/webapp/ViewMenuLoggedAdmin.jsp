<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu with Subtle Orange Line</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .w3-bar {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            z-index: 1000;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px 20px;
            background-color: #ffffff;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .w3-bar:before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 1px;
            background-color: #ff8c00;
            z-index: -1;
        }
        .w3-bar-item {
            padding: 8px 16px;
            color: #333333;
        }
        .w3-bar-item img {
            height: 40px;
            margin-top: -5px;
        }
        .search-container {
            flex: 1;
            margin: 0 20px;
        }
        .search-container input[type=text] {
            width: 100%;
            padding: 8px;
            border: 1px solid #f0f0f0;
            border-radius: 4px;
            box-sizing: border-box;
            background-color: #f0f0f0;
            color: #333333;
        }
        .w3-button, .w3-button:hover {
            background-color: transparent;
            color: #333333;
            border: none;
            transition: background-color 0.3s, color 0.3s;
        }
        .w3-button:hover {
            background-color: #ffffff;
        }
    </style>
</head>
<body>

<div class="w3-bar">
    <!-- Custom Logo/Image on Top Left -->
    <a class="w3-bar-item w3-button" href="MainController">
        <img src="imgs/stack.png" alt="Logo">
    </a>

    <!-- Search Bar in the Middle -->
    <div class="search-container">
        <input type="text" placeholder="Search...">
    </div>

    <!-- Menu Links on Top Right -->
    <div class="w3-bar-item w3-right w3-hide-small">
        <a class="menu w3-bar-item w3-button" id="UsersRegisteredController" href="#">Users Registered</a>		
        <a class="menu w3-bar-item w3-button" id="PerfilController" href="#">Perfil</a>
        <a class="menu w3-bar-item w3-button" id="LogoutController" href="#">Logout</a>
    </div>

    <!-- Hamburger Menu Icon for Small Screens -->
    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
</div>

<!-- Dropdown Menu for Small Screens -->
<div id="stack" class="w3-bar-block w3-red w3-hide w3-hide-large w3-hide-medium">
    <a class="menu w3-bar-item w3-button" id="PostsController" href="#">Posts</a>
    <a class="menu w3-bar-item w3-button" id="UsersRegisteredController" href="#">Users Registered</a>
    <a class="menu w3-bar-item w3-button" id="PerfilController" href="#">Perfil</a>
    <a class="menu w3-bar-item w3-button" id="LogoutController" href="#">Logout</a>
    
</div>

<script>
    function stack() {
        var x = document.getElementById("stack");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }
</script>

</body>
</html>
