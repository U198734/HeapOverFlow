<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HeapOverflow</title>
    <link rel="icon" href="imgs/stack.png">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <c:forEach var="post" items="${posts}">
        <div id="${post.postId}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity">
            <br>
            <div style="display: flex; align-items: center;">
                <img src="imgs/stack.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width: 40px;">
                <h4 style="margin: 0; font-size: 18px; color: #333; font-weight: bold;">${post.username}</h4>
            </div>
            <div style="text-align: right; margin-top: -10px;">
                <span class="w3-tag w3-round w3-light-grey">${post.programmingLanguage}</span>
                <span class="w3-tag w3-round w3-light-grey">${post.professionalField}</span>
            </div>
            <hr class="w3-clear">
            <p style="margin-top: -10px;">${post.description}</p>
            <div style="text-align: right;">
                <span class="w3-right w3-opacity">${post.postDateTime}</span>
            </div>
            <!-- Buttons Section -->
            <div style="margin-top: 10px;">
                <button id="likePost" class="w3-button w3-small w3-theme-d1 w3-margin-right">
                    <i class="fa fa-thumbs-up"></i> Like
                </button>
                <button id="commentPost" class="commentPost w3-button w3-small w3-theme-d2 w3-margin-right">
                    <i class="fa fa-comment"></i> Comment
                </button>
            </div>
            <!-- Styled Repository Link -->
            <p style="margin-top: 10px; text-align: center;">
                <a href="${post.url}" target="_blank" class="w3-button w3-small w3-orange w3-round-large">
                    View Repository
                </a>
            </p>

            <!-- Comments Section -->
            <div class="commentSection">
                <c:forEach var="comment" items="${post.comments}">
                    <div id="comment_${comment.postId}" class="w3-container w3-card w3-section w3-light-grey w3-round w3-animate-opacity">
                        <div style="display: flex; align-items: center;">
                            <img src="imgs/stack.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width: 30px;">
                            <h5 style="margin: 0; font-size: 14px; color: #333; font-weight: bold;">${comment.username}</h5>
                        </div>
                        <p>${comment.description}</p>
                        <div style="text-align: right;">
                            <span class="w3-right w3-opacity">${comment.postDateTime}</span>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </c:forEach>
</body>
</html>