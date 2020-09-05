<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User</title>
    <link rel="stylesheet" href="./static/css/index.css">
</head>
<body>
    Hii User

    
    <div>
        <form action="/user/logout-user" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit">logout~admin</button>
        </form>
    
    </div>


    <script src="./static/js/index.js"></script>
</body>
</html>