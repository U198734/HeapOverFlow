<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h2>Welcome!! </h2>
   
    <ul>
    <!-- Checks of UserName i Email -->
        <c:forEach var="error" items="${error}">
            <li class="error">${error}</li>
        </c:forEach>
    </ul>
    
    <c:if test="${not empty errorMessage}">
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    </c:if>


<form action="RegisterController" method="POST">
	<p>      
    <label class="w3-text-red"><b> User id </b></label>
      <input class="w3-input w3-border w3-light-grey" type="text" id="user" name="userName" placeholder="Name" value="${model.userName}" required pattern="[a-zA-Z0-9]+" title="The username can only contain letters and numbers.">
    <p>      
    <label class="w3-text-red"><b> Mail address </b></label>
    <input class="w3-input w3-border w3-light-grey" type="email" name="mail" placeholder="usermail@domain" value = "${user.mail}" required></p>
    <p>
     <p>
        <label class="w3-text-red"><b> Gender </b></label>
        <select class="w3-input w3-border w3-light-grey" name="gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="non-binary">Non-binary</option>
        </select>
    </p>
     <p>
        <label class="w3-text-red"><b> Password </b></label>
        <input class="w3-input w3-border w3-light-grey" type="password" id="pwd" name="pwd" placeholder="Password" value="${user.pwd}"required
               pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"
               title="Please enter at least 6 characters, including 1 uppercase, 1 lowercase, and 1 digit">
               
     <p>
     	<label class="w3-text-red"><b> Confirm Password </b></label>
        <input class="w3-input w3-border w3-light-grey" type="password" id="pwdc" name="pwdc" placeholder="Password" required
               pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"
               title="Please enter at least 6 characters, including 1 uppercase, 1 lowercase, and 1 digit">
               
      <p>
      
       <hr>
      <p>
        <label class="w3-text-red" for="lang"><b>Programming Language</b></label>
        <select class="w3-input w3-border w3-light-grey" id="lang" name="lang">
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
      </p>
          
      <p>
        <label class="w3-text-red" for="personalField"><b>Profession/Interest</b></label>
        <select class="w3-input w3-border w3-light-grey" id="personalField" name="personalField">
            <option value="Other" ${model.personalField == 'Other' ? 'selected' : ''}>Other</option>
            <option value="Machine Learning" ${model.personalField == 'Machine Learning' ? 'selected' : ''}>Machine Learning</option>
            <option value="Natural Language Processing" ${model.personalField == 'Natural Language Processing' ? 'selected' : ''}>Natural Language Processing</option>
            <option value="Computer Vision" ${model.personalField == 'Computer Vision' ? 'selected' : ''}>Computer Vision</option>
            <option value="Reinforcement Learning" ${model.personalField == 'Reinforcement Learning' ? 'selected' : ''}>Reinforcement Learning</option>
            <option value="Deep Learning" ${model.personalField == 'Deep Learning' ? 'selected' : ''}>Deep Learning</option>
        </select>
      </p>   
          
      <p>
     
    
    <input class="w3-btn w3-red" type="submit" name="sumbit" value="Submit"></p>
</form>

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






