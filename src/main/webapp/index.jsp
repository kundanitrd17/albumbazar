<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="_csrf" content="${_csrf.token}" />
  <!-- default header name is X-CSRF-TOKEN -->
  <meta name="_csrf_header" content="${_csrf.headerName}" />
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <title>Document</title>
</head>

<body>
  Hello User
  <form onsubmit="load(event)" id="process">
    <input type="text" name="company" id="company" />
    <input type="text" name="dob" id="dob" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf_token" />
    <button type="submit">submit</button>
  </form>

  <script>
    function load(event) {
      event.preventDefault();
      console.log(this.dob.value);
      $.ajax({
        url: "api/product/company/nishi",
        success: function (data) {
          console.log(data);
        },
      });
    }


  </script>
</body>

</html>