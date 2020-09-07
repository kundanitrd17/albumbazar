<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
    <link rel="stylesheet" href="./static/css/index.css">
</head>
<body>
    Welcome man
    ${2 + 7}

    <!-- <form action="/emp" method="POST">
        <input type="text" name="name"/> <br>
        <input type="text" name="landmark"> <br>
        <input type="text" name="city" id=""> <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">submit</button>
    </form> -->

    <!-- ////////////////////////////////////////////////////////// -->








    <form action="/emp" method="POST">
        <fieldset>
            <input type="text" name="name" id="">
        </fieldset>
        <fieldset>
            <input type="text" name="title" id="">
        </fieldset>
        <fieldset>
            <input type="text" name="work" id="">
        </fieldset>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">submit</button>
</form>




    <script src="./static/js/index.js"></script>
</body>
</html>