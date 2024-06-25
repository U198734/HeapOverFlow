<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Perfil</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            width: 90%;
            max-width: 1400px; /* Ancho máximo ajustado según sea necesario */
            margin: 20px;
            padding: 40px;
            background-color: #fff3e0; /* Fondo naranja claro */
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #e65100; /* Color naranja oscuro para el encabezado */
            text-align: center;
            margin-bottom: 20px;
        }
        .edit-form {
            background-color: #ffe0b2; /* Fondo naranja claro para el formulario */
            padding: 20px;
            border-radius: 5px;
            margin-top: 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .edit-form label {
            display: block;
            margin-bottom: 10px;
            color: #333;
        }
        .edit-form input,
        .edit-form select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .edit-form button {
            padding: 12px 24px;
            background-color: #e65100; /* Color naranja oscuro para el botón */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .edit-form button:hover {
            background-color: #bf360c; /* Color naranja más oscuro al pasar el ratón */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Editar Perfil</h2>
        <form action="PerfilController" method="POST">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="user_id" value="${user.user_id}">
            
            <label for="user_id">User_id:</label>
            <input type="text" id="user_id" name="user_id" value="${user.user_id}">
            
            <label for="user_name">Username:</label>
            <input type="text" id="user_name" name="user_name" value="${user.user_name}">
            
            <label for="mail">Email:</label>
            <input type="email" id="mail" name="mail" value="${user.mail}" required>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
                <option value="Non-binary" ${user.gender == 'Non-binary' ? 'selected' : ''}>Non-binary</option>
                <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
            </select>

            <label for="programming_language">Programming Language:</label>
            <select id="programming_language" name="programming_language">
                <option value="Other" ${user.programming_language == 'Other' ? 'selected' : ''}>Other</option>
                <option value="Python" ${user.programming_language == 'Python' ? 'selected' : ''}>Python</option>
                <option value="Java" ${user.programming_language == 'Java' ? 'selected' : ''}>Java</option>
                <option value="C++" ${user.programming_language == 'C++' ? 'selected' : ''}>C++</option>
                <option value="C#" ${user.programming_language == 'C#' ? 'selected' : ''}>C#</option>
                <option value="TypeScript" ${user.programming_language == 'TypeScript' ? 'selected' : ''}>TypeScript</option>
                <option value="PHP" ${user.programming_language == 'PHP' ? 'selected' : ''}>PHP</option>
                <option value="Swift" ${user.programming_language == 'Swift' ? 'selected' : ''}>Swift</option>
                <option value="Ruby" ${user.programming_language == 'Ruby' ? 'selected' : ''}>Ruby</option>
                <option value="Go" ${user.programming_language == 'Go' ? 'selected' : ''}>Go</option>
                <option value="JavaScript" ${user.programming_language == 'JavaScript' ? 'selected' : ''}>JavaScript</option>
            </select>

            <label for="professional_field">Professional Field:</label>
            <select id="professional_field" name="professional_field">
                <option value="Other" ${user.professional_field == 'Other' ? 'selected' : ''}>Other</option>
                <option value="Machine Learning" ${user.professional_field == 'Machine Learning' ? 'selected' : ''}>Machine Learning</option>
                <option value="Natural Language Processing" ${user.professional_field == 'Natural Language Processing' ? 'selected' : ''}>Natural Language Processing</option>
                <option value="Computer Vision" ${user.professional_field == 'Computer Vision' ? 'selected' : ''}>Computer Vision</option>
                <option value="Reinforcement Learning" ${user.professional_field == 'Reinforcement Learning' ? 'selected' : ''}>Reinforcement Learning</option>
                <option value="Deep Learning" ${user.professional_field == 'Deep Learning' ? 'selected' : ''}>Deep Learning</option>
            </select>

            <div class="form-group">
                <input type="submit" value="update">
            </div>
        </form>
    </div>
</body>
</html>
