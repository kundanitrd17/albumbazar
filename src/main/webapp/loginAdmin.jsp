<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./static/css/index.css">
</head>
<body>
    <p>Admin login page</p>
    <form action="superuser/superlogin" method="POST">
        <input type="text" name="username"> <br>
        <input type="text" name="password" > <br>
        <input type="submit" value="login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <script src="./static/js/index.js"></script>
</body>
</html>