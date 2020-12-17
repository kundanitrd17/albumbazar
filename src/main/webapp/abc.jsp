<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">


    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->




    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
    <title>Document</title>
</head>

<body>
    Hello abc

    <form id="file_upload" method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <input id="photos" type="file" name="files" multiple>
        <button type="submit">Upload</button>
    </form>
    <hr>
    <div id="main">

    </div>

    <br><br><br><br><br><br>
    <div>
        <a href="/googlesignin"><button>signin</button></a>

    </div>

    <script>



    </script>

    <script>

        var form = document.getElementById('file_upload');

        form.addEventListener('submit', e => {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            e.preventDefault();

            var data = new FormData(form);

            var xhr = new XMLHttpRequest();
            var url = 'http://localhost:8080/api/secured/order/1/photos';
            xhr.open("POST", url, true);
            // xhr.setRequestHeader('Content-type', 'application/json');
            // xhr.setRequestHeader(header, token);

            xhr.onreadystatechange = function () { // Call a function when the state changes.
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                    // console.log(typeof (JSON.parse(this.response)));
                    console.log(this.response);

                    alert("file was uploaded successfully")

                }
            }
            xhr.send(data)
            console.log(data);


        });



    </script>



    <script src='http://code.jquery.com/jquery-latest.js'></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>

</body>

</html>