<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    body {
        font-family: "Arial", sans-serif;
        background-color: #f0f0f0;
    }
    .container {
        max-width: 600px;
        margin: auto;
        padding: 20px;
        background-color: white;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
        color: #333;
    }
    .error {
        color: blue;
        list-style-type: none;
    }
    .form-group {
        margin-bottom: 15px;
    }
    .form-group label {
        font-weight: bold;
        color: #333;
    }
    .form-group input,
    .form-group select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .form-group input[type="submit"] {
        background-color: #d9534f;
        color: white;
        border: none;
        cursor: pointer;
    }
    .form-group input[type="submit"]:hover {
        background-color: #c9302c;
    }
</style>
<body>
<br><br><br>
    <div class="container">
        <h2>Edit User</h2>
        
		<form action="UsersRegisteredController" method="POST">
            <input type="hidden" name="action" value="update">
            <div class="form-group">
                <label for="user" class="w3-text-blue"><b>User id</b></label>
                <input type="text" id="user" name="user_name" placeholder="User Name" value="${user.user_name}" required pattern="[a-zA-Z0-9]+" title="The username can only contain letters and numbers." readonly>
            </div>
            
            <div class="form-group">
                <label for="mail" class="w3-text-blue"><b>Mail address</b></label>
                <input type="email" id="mail" name="mail" placeholder="usermail@domain" value="${user.mail}" required>
            </div>
            
            <div class="form-group">
                <label for="gender" class="w3-text-blue"><b>Gender</b></label>
                <select id="gender" name="gender">
                    <option value="male" ${user.gender == 'male' ? 'selected' : ''}>Male</option>
                    <option value="female" ${user.gender == 'female' ? 'selected' : ''}>Female</option>
                    <option value="non-binary" ${user.gender == 'non-binary' ? 'selected' : ''}>Non-binary</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="pwd" class="w3-text-blue"><b>Password</b></label>
                <input type="password" id="pwd" name="pwd" placeholder="Password" value="${user.pwd}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$" title="Please enter at least 6 characters, including 1 uppercase, 1 lowercase, and 1 digit">
            </div>
            
            <div class="form-group">
                <label for="pwdc" class="w3-text-blue"><b>Confirm Password</b></label>
                <input type="password" id="pwdc" name="pwdc" placeholder="Password" value="${user.pwd}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$" title="Please enter at least 6 characters, including 1 uppercase, 1 lowercase, and 1 digit">
            </div>
            
            <hr>
            
        <div class="form-group">
            <label for="lang" class="w3-text-blue"><b>Programming Language</b></label>
            <select id="programming_language" name="programming_language">
                <option value="Other"      ${user.programming_language == 'Other' ? 'selected' : ''}>Other</option>
                <option value="Python"     ${user.programming_language == 'Python' ? 'selected' : ''}>Python</option>
                <option value="Java"       ${user.programming_language == 'Java' ? 'selected' : ''}>Java</option>
                <option value="C++"        ${user.programming_language == 'C++' ? 'selected' : ''}>C++</option>
                <option value="C#"         ${user.programming_language == 'C#' ? 'selected' : ''}>C#</option>
                <option value="TypeScript" ${user.programming_language == 'TypeScript' ? 'selected' : ''}>TypeScript</option>
                <option value="PHP"        ${user.programming_language == 'PHP' ? 'selected' : ''}>PHP</option>
                <option value="Swift"      ${user.programming_language == 'Swift' ? 'selected' : ''}>Swift</option>
                <option value="Ruby"       ${user.programming_language == 'Ruby' ? 'selected' : ''}>Ruby</option>
                <option value="Go"         ${user.programming_language == 'Go' ? 'selected' : ''}>Go</option>
                <option value="JavaScript" ${user.programming_language == 'JavaScript' ? 'selected' : ''}>JavaScript</option>
           		
            </select>
        </div>
            
        <div class="form-group">
            <label for="professional_field" class="w3-text-blue"><b>Profession/Interest</b></label>
            <select id="professional_field" name="personalField">
                <option value="Other" 			 			${user.professional_field == 'Other' ? 'selected' : ''}>Other</option>
                <option value="Machine Learning" 			${user.professional_field == 'Machine Learning' ? 'selected' : ''}>Machine Learning</option>
                <option value="Natural Language Processing" ${user.professional_field == 'Natural Language Processing' ? 'selected' : ''}>Natural Language Processing</option>
                <option value="Computer Vision" 			${user.professional_field == 'Computer Vision' ? 'selected' : ''}>Computer Vision</option>
                <option value="Reinforcement Learning" 		${user.professional_field == 'Reinforcement Learning' ? 'selected' : ''}>Reinforcement Learning</option>
                <option value="Deep Learning" 				${user.professional_field == 'Deep Learning' ? 'selected' : ''}>Deep Learning</option>
            </select>
        </div>
            
         
            
            <div class="form-group">
                <input type="submit" value="update">
            </div>
        </form>
    </div>
</body>
