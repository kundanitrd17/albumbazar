<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
    <title>Document</title>
</head>

<body>
    Hello abc

    <input type="text" name="name" id="name">
    <button id="btn-click">click Here to Load message</button>
    <hr>
    <div id="main">

    </div>



    <script>

        document.getElementById('btn-click').addEventListener('click', e => {
            var URL = "/api/hello";

            $.ajax({
                url: URL,
                success: function (result) {

                    $("#main").prepend("<p>" + result["name"] + "</p> <br>");
                }
            });


        });



    </script>



    <script src='http://code.jquery.com/jquery-latest.js'></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>

</body>

</html>