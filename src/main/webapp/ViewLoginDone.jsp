<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>


<script type="text/javascript">
    $('#navigation').load('MenuController');
</script>

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
         background-color: #d4edda; /* Light green background */
         padding: 20px;
         border-radius: 5px;
         box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
         text-align: center;
     }
     .message {
         font-size: 18px;
         color: #155724; /* Dark green text color */
     }
     .container a {
         color: #155724; /* Match link color with the message text */
         text-decoration: none;
         font-weight: bold;
     }
     .container a:hover {
         text-decoration: underline;
     }
 </style>

<div class="container">
    <h2>Login Successful</h2>
    <p class="message">Login for user ${user.user_name} done!</p>
</div>



