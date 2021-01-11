<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->
    <title>Document</title>
</head>

<body>

    <form action="/upload" method="post">
        <input type="text" name="name" value="Harsh">
        <input type="file" name="photo">
        <input type="text" name="name2" value="Harsh">
        <input type="file" name="photo2">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">Upload</button>
    </form>

    <script>
        document.querySelector('form').addEventListener('submit', function (e) {
            e.preventDefault();
            console.log("hello");

            // var data = {
            //     "name": "harsha",
            //     "photo": document.querySelector('input[name="photo"]').files[0]
            // }
            var data = new FormData()
            data.append("id", "1212");

            let cv1 = { "name": "harsh", "photo": document.querySelector('input[name="photo"]').files[0] };
            let cv2 = { "name": "Nishi", "photo": document.querySelector('input[name="photo"]').files[0] };
            let cs

            data.append("covers", cv);

            var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
            var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");


            let xhr = new XMLHttpRequest();
            const url = "http://localhost:8080/upload";
            xhr.open('POST', url, true);
            // xhr.setRequestHeader('Content-type', 'multipart/form-data');
            xhr.setRequestHeader(header, token);

            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.DONE) {
                    // console.log(this.response)s;
                }
            }

            xhr.send((data));

            console.log(data);
        })
    </script>

</body>

</html>