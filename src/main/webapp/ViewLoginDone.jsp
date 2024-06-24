<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Successful</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 90%;
            max-width: 500px;
            background-color: #ffe0b2; /* Light orange background */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin: 20px;
        }
        .container h1 {
            font-size: 32px; /* Larger header */
            color: #e65100; /* Dark orange color for the heading */
            margin-bottom: 10px;
        }
        .message {
            font-size: 18px;
            color: #bf360c; /* Darker orange text color */
            margin-bottom: 20px;
        }
        .container a {
            color: #e65100; /* Match link color with the heading */
            text-decoration: none;
            font-weight: bold;
            padding: 10px 20px;
            border: 1px solid #e65100;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
            display: inline-block;
            margin-top: 10px;
        }
        .container a:hover {
            background-color: #e65100;
            color: #ffffff;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#navigation').load('MenuController');
        });
    </script>
</head>
<body>

<div class="container">
    <h1>Login Successful</h1>
    <p class="message">Login done!</p>
    <a href="MainController">Go to Homepage</a>
</div>

</body>
</html>
