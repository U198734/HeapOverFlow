<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
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
        background-color: #fff; /* Fondo blanco */
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        border-radius: 10px; /* Bordes redondeados */
        text-align: center; /* Centrar contenido */
    }
    h2 {
        color: #ff8c00; /* Color naranja */
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Fuente profesional */
        font-size: 2.5em; /* Tamaño grande */
        letter-spacing: 1px; /* Espaciado entre letras */
        margin-top: 0; /* Eliminar margen superior */
    }
    .error {
        color: #ff6347; /* Texto de error en rojo anaranjado */
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
        border: 1px solid #ffa500; /* Borde naranja */
        border-radius: 5px;
    }
    .form-group input[type="submit"] {
        background-color: #ffa500; /* Fondo naranja */
        color: white;
        border: none;
        cursor: pointer;
    }
    .form-group input[type="submit"]:hover {
        background-color: #ff8c00; /* Hover naranja más oscuro */
    }
    .error-list {
        padding: 0;
    }
    .error-list li {
        margin-bottom: 5px;
    }
</style>

<body>

<br><br><br><br>
<div class="container">
    <h2>Welcome to HeapOverFlow!!</h2>
    
    <!-- Mostrar errores de UserName y Email -->
    <ul class="error-list">
        <c:if test="${not empty user.errors}">
            <c:forEach var="error" items="${user.errors}">
                <li class="error">${error.value}</li>
            </c:forEach>
        </c:if>
    </ul>

    <form action="RegisterController" method="POST">
        <div class="form-group">
            <label for="user"><b>User Name</b></label>
            <input type="text" id="user" name="user_name" placeholder="Name" value="${model.user_name}" required pattern="[a-zA-Z0-9]+" title="The username can only contain letters and numbers.">
        </div>
        
        <div class="form-group">
            <label for="mail"><b>Mail address</b></label>
            <input type="email" id="mail" name="mail" placeholder="usermail@domain" value="${user.mail}" required>
        </div>
        
        <div class="form-group">
            <label for="gender"><b>Gender</b></label>
            <select id="gender" name="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="non-binary">Non-binary</option>
            </select>
        </div>
        
        <div class="form-group">
            <label for="pwd"><b>Password</b></label>
            <input type="password" id="pwd" name="pwd" placeholder="Password" value="${user.pwd}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$" title="Please enter at least 6 characters, including 1 uppercase, 1 lowercase, and 1 digit">
        </div>
        
        <div class="form-group">
            <label for="pwdc"><b>Confirm Password</b></label>
            <input type="password" id="pwdc" name="pwdc" placeholder="Password" value="${user.pwd}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$" title="Please enter at least 6 characters, including 1 uppercase, 1 lowercase, and 1 digit">
        </div>
        
        <hr>
        
        <div class="form-group">
            <label for="programming_language"><b>Programming Language</b></label>
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
           		<!-- Tenia toc ho havia de posar recte -->
           		
            </select>
        </div>
        
         <div class="form-group">
            <label for="professional_field"><b>Profession/Interest</b></label>
            <select id="professional_field" name="professional_field">
            
            <option value="Other"                       ${user.professional_field == 'Other' ? 'selected' : ''}>Other</option>
            <option value="Machine Learning"            ${user.professional_field == 'Machine Learning' ? 'selected' : ''}>Machine Learning</option>
            <option value="Natural Language Processing" ${user.professional_field == 'Natural Language Processing' ? 'selected' : ''}>Natural Language Processing</option>
            <option value="Computer Vision"             ${user.professional_field == 'Computer Vision' ? 'selected' : ''}>Computer Vision</option>
            <option value="Reinforcement Learning"      ${user.professional_field == 'Reinforcement Learning' ? 'selected' : ''}>Reinforcement Learning</option>
            <option value="Deep Learning"               ${user.professional_field == 'Deep Learning' ? 'selected' : ''}>Deep Learning</option>
         </select>
      </div>
        
        <div class="form-group">
            <input type="submit" class="w3-btn w3-orange" name="submit" value="Submit">
        </div>
	</form> 
</div>

<script>
    const form = document.getElementById("regform");
    //const user_name = document.getElementById("user");
    const pwd = document.getElementById("pwd");
    const pwdc = document.getElementById("pwdc");

    var checkPasswordValidity = function() {
        if (pwdc.value !== pwd.value) {
            pwdc.setCustomValidity("Password must be the same");
        } else {
            pwdc.setCustomValidity("");
            pwdc.classList.add('valid');
        }
    } 
    
    pwdc.addEventListener("input", checkPasswordValidity, false);

    form.addEventListener("submit", function (event) {
        checkPasswordValidity();
        if (!this.checkValidity()) {
            this.reportValidity();
            event.preventDefault();
        }
    }, false);
</script>






