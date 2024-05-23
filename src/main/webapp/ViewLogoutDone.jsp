<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

<script type="text/javascript">
$('#navigation').load('MenuController');
</script>

<style>
    .logout-container {
        text-align: center;
        background-color: lightblue;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        font-family: Arial, sans-serif;
    }
    .logout-container h1 {
        color: #333;
        margin-bottom: 10px;
    }
    .logout-container p {
        color: #666;
        margin-bottom: 20px;
    }
    .logout-container a {
        display: inline-block;
        padding: 10px 20px;
        background-color: #007BFF;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        transition: background-color 0.3s;
    }
    .logout-container a:hover {
        background-color: #0056b3;
    }
</style>

<div class="logout-container">
    <h1>Logout Successful</h1>
    <p>You have been logged out. Thank you for visiting!</p>
</div>

