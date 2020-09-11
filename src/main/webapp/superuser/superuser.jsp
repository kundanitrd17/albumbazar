
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Superuser</title>
    <link rel="stylesheet" href="./static/css/index.css">
</head>
<body>

    Hii Superuser

    <form action="/superuser/api/resetsuperuser" method="POST">
      <input type="text" name="password1" value="" placeholder="new Password"> <br>
      <input type="text" name="password2" value="" placeholder="RE-type Password"> <br>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" name="button">Reset</button>
    </form> 

    <div>
        <form action="/superuser/logout-super" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit">logout~admin</button>
        </form>
    
    </div>

    <div>
        <% 
            
        %>
    </div>

</body>
</html>
