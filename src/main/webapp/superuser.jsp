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

    <form action="/superuser/resetpassword" method="post">
      <input type="text" name="new_password" value="" placeholder="new Password"> <br>
      <input type="text" name="re_new_password" value="" placeholder="RE-type Password"> <br>
        <button type="submit" name="button">Reset</button>
    </form>

</body>
</html>
