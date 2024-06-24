<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:forEach var="t" items="${posts}">
    <div id="${t.postId}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
        <div style="display: flex; align-items: center;">
            <img src="imgs/stack.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width: 40px;">
            <h4 style="margin: 0; font-size: 18px; color: #333; font-weight: bold;">${t.username}</h4>
        </div>
        <div style="text-align: right; margin-top: -10px;">
            <span class="w3-tag w3-round w3-light-grey">${t.programmingLanguage}</span>
            <span class="w3-tag w3-round w3-light-grey">${t.professionalField}</span>
        </div>
        <hr class="w3-clear">
        <p style="margin-top: -10px;">${t.description}</p>
        <div style="text-align: right;">
            <span class="w3-right w3-opacity">${t.postDateTime}</span>
        </div>
        <!-- Buttons Section -->
        <div style="margin-top: 10px;">
            <button class="delPost w3-button w3-small w3-red">
            <i class="fa fa-trash"></i> Delete</button>
        </div>
        <!-- Styled Repository Link -->
        <p style="margin-top: 10px; text-align: center;">
            <a href="${t.url}" target="_blank" class="w3-button w3-small w3-orange w3-round-large">
                View Repository
            </a>
        </p>
    </div>
</c:forEach>

