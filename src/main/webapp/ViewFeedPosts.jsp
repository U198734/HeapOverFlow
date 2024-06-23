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
    <style>
        .fixed-link {
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 1000;
            background-color: #000;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 20px;
            font-size: 16px;
            text-decoration: none;
            display: flex;
            align-items: center;
            transition: background-color 0.3s ease;
        }

        .fixed-link:hover {
            background-color: #333;
        }

        .fixed-link i {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <c:forEach var="t" items="${posts}">      
        <div id="${t.postId}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity">
            <br>
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
                <button id="likePost" class="w3-button w3-small w3-theme-d1 w3-margin-right">
                    <i class="fa fa-thumbs-up"></i> Like
                </button>
                <button id="commentPost" class="commentPost w3-button w3-small w3-theme-d2 w3-margin-right">
                    <i class="fa fa-comment"></i> Comment
                </button>
            </div>
            <!-- Styled Repository Link -->
            <p style="margin-top: 10px; text-align: center;">
                <a href="${t.url}" target="_blank" class="w3-button w3-small w3-orange w3-round-large">
                    View Repository
                </a>
            </p>
        </div>
    </c:forEach>

    <!-- Fixed "Create Post" Link -->
    <a id="CreatePostController" class="fixed-link" href=#>
        <i class="fa fa-pencil"></i> Got something to share? Create a post
    </a>
  
</body>
</html>