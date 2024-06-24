<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout Successful</title>
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
        .logout-container {
            text-align: center;
            background-color: #ffe0b2; /* Light orange background */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 90%;
            margin: 20px;
        }
        .logout-container h1 {
            color: #e65100; /* Dark orange color for the heading */
            margin-bottom: 10px;
        }
        .logout-container p {
            color: #bf360c; /* Darker orange text color */
            margin-bottom: 20px;
        }
        .logout-container a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #e65100; /* Dark orange button color */
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .logout-container a:hover {
            background-color: #bf360c; /* Darker orange on hover */
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

<div class="logout-container">
    <h1>Logout Successful</h1>
    <p>You have been logged out. Thank you for visiting!</p>
    <a href="MainController">Go to Homepage</a>
</div>

</body>
</html>
