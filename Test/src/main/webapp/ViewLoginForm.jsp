<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="LoginController" method="POST">
	<p>           
    <label class="w3-text-red"><b> User id </b></label>
      <input class="w3-input w3-border w3-light-grey" type="text" id="user" name="userName" placeholder="Name" value="${model.userName}" required pattern="[a-zA-Z0-9]+" title="The username can only contain letters and numbers.">
    <p> 
    <p>
        <label class="w3-text-red"><b> Password </b></label>
        <input class="w3-input w3-border w3-light-grey" type="password" id="pwd" name="pwd" placeholder="Password" value="${user.pwd}"required
               pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"
               title="Please enter at least 6 characters, including 1 uppercase, 1 lowercase, and 1 digit">
               
     <p>
    <input class="w3-btn w3-red" type="submit" name="submit" value="Submit"></p>
</form>
