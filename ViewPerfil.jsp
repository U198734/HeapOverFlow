<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Usuario</title>
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
        h3 {
            color: #bf360c; /* Color de texto naranja más oscuro */
            text-align: center;
            margin-bottom: 30px;
        }
        .user-card {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            position: relative;
        }
        .user-card p {
            margin: 10px 0;
            color: #333;
        }
        .user-card span {
            font-weight: bold;
            color: #e65100;
        }
        .edit-button {
            padding: 12px 24px;
            background-color: #e65100; /* Color naranja oscuro para el botón */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            display: block;
            margin: auto;
            margin-top: 20px;
        }
        .edit-button:hover {
            background-color: #bf360c; /* Color naranja más oscuro al pasar el ratón */
        }
        .edit-form {
            display: none;
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
<script type="text/javascript">
function editUser(user_name) {
            $.ajax({
                url: 'UsersRegisteredController',
                type: 'POST',
                data: {
                    action: 'edit',
                    user_name: user_name
                },
                success: function(response) {
                    $('body').html(response);
                },
                error: function(xhr, status, error) {
                    alert('Error editing user: ' + error);
                }
            });
        }
</script>
<body>
    <div class="container">
        <h2>Perfil</h2>
        <h3>Hola ${user.user_name}!</h3>
        <div class="user-card">
            <p><span>Email:</span> ${user.mail}</p>
            <p><span>Gender:</span> ${user.gender}</p>
            <p><span>Programming Language:</span> ${user.programming_language}</p>
            <p><span>Professional Field:</span> ${user.professional_field}</p>
            <button class="w3-button w3-red" onclick="editUser('${user.user_name}')">Edit</button>
        </div>
    </div>
</body>
</html>
