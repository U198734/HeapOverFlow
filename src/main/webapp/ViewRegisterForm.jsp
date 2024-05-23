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
        .error-list {
            padding: 0;
        }
        .error-list li {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>

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
            <label for="user" class="w3-text-blue"><b>User id</b></label>
            <input type="text" id="user" name="userName" placeholder="Name" value="${model.userName}" required pattern="[a-zA-Z0-9]+" title="The username can only contain letters and numbers.">
        </div>
        
        <div class="form-group">
            <label for="mail" class="w3-text-blue"><b>Mail address</b></label>
            <input type="email" id="mail" name="mail" placeholder="usermail@domain" value="${user.mail}" required>
        </div>
        
        <div class="form-group">
            <label for="gender" class="w3-text-blue"><b>Gender</b></label>
            <select id="gender" name="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="non-binary">Non-binary</option>
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
            <select id="lang" name="lang">
                <option value="Other" ${model.lang1 == 'Other' ? 'selected' : ''}>Other</option>
                <option value="Python" ${model.lang1 == 'Python' ? 'selected' : ''}>Python</option>
                <option value="Java" ${model.lang1 == 'Java' ? 'selected' : ''}>Java</option>
                <option value="C++" ${model.lang1 == 'C++' ? 'selected' : ''}>C++</option>
                <option value="C#" ${model.lang1 == 'C#' ? 'selected' : ''}>C#</option>
                <option value="TypeScript" ${model.lang1 == 'TypeScript' ? 'selected' : ''}>TypeScript</option>
                <option value="PHP" ${model.lang1 == 'PHP' ? 'selected' : ''}>PHP</option>
                <option value="Swift" ${model.lang1 == 'Swift' ? 'selected' : ''}>Swift</option>
                <option value="Ruby" ${model.lang1 == 'Ruby' ? 'selected' : ''}>Ruby</option>
                <option value="Go" ${model.lang1 == 'Go' ? 'selected' : ''}>Go</option>
                <option value="JavaScript" ${model.lang1 == 'JavaScript' ? 'selected' : ''}>JavaScript</option>
            </select>
        </div>
        
        <div class="form-group">
            <label for="personalField" class="w3-text-blue"><b>Profession/Interest</b></label>
            <select id="personalField" name="personalField">
                <option value="Other" ${model.personalField == 'Other' ? 'selected' : ''}>Other</option>
                <option value="Machine Learning" ${model.personalField == 'Machine Learning' ? 'selected' : ''}>Machine Learning</option>
                <option value="Natural Language Processing" ${model.personalField == 'Natural Language Processing' ? 'selected' : ''}>Natural Language Processing</option>
                <option value="Computer Vision" ${model.personalField == 'Computer Vision' ? 'selected' : ''}>Computer Vision</option>
                <option value="Reinforcement Learning" ${model.personalField == 'Reinforcement Learning' ? 'selected' : ''}>Reinforcement Learning</option>
                <option value="Deep Learning" ${model.personalField == 'Deep Learning' ? 'selected' : ''}>Deep Learning</option>
            </select>
        </div>
        
        <div class="form-group">
    		<input class="w3-btn w3-blue" type="submit" name="sumbit" value="Submit">
		</div>
	</form> 
</div>

<script>
    const form = document.getElementById("regform");
    const userName = document.getElementById("user");
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






