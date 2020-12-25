<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

</head>

<body>
    <div class="wrapper">
        <div class="container super-login">
            <h1>Welcome</h1>
            <form class="form" action="/delivery/login" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-group">
                    <input name="username" type="text" placeholder="Username" class="" />
                </div>
                <div class="form-group">
                    <input name="password" type="password" placeholder="Password" />
                </div>
                <button type="submit" id="login-button">Login</button><br />
                <a href="#">Forgot Password ?</a>
            </form>
        </div>


</body>

</html>