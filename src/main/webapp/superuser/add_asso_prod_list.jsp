<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Dashboard</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
  <link rel="stylesheet" href="http://localhost:8080/superuser/css/super-admin.css">
  <style type="text/css">
    body {
      background-color: white;
    }

    table {
      background-color: white;
      margin-top: 50px;

      -webkit-box-shadow: 0px 3px 13px 0px rgba(204, 195, 240, 1);
      -moz-box-shadow: 0px 3px 13px 0px rgba(204, 195, 240, 1);
      box-shadow: 0px 3px 13px 0px rgba(204, 195, 240, 1);

      border: none;
    }

    table tr th {
      text-align: center;
      border: none;
    }

    table tr td {

      border: none;
    }
  </style>

  <link rel="stylesheet" href="./css/super-admin.css">
</head>

<body>


  <%@include file="sidebar.jsp" %>



  <section id="contents">
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <i class="fa fa-align-right"></i>
          </button>
          <a class="navbar-brand" href="#">my<span class="main-color">Dashboard</span></a>
        </div>
        <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                aria-expanded="false">My profile <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="fa fa-user-o fw"></i> My account</a></li>
                <li><a href="#"><i class="fa fa-envelope-o fw"></i> My inbox</a></li>
                <li><a href="#"><i class="fa fa-question-circle-o fw"></i> Help</a></li>
                <li role="separator" class="divider"></li>
                <li>

                  <form action="/superuser/logout-super" method="POST">
                    <i class="fa fa-sign-out"></i>
                    <input type="submit" value="logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  </form>
                </li>
              </ul>
            </li>
            <li><a href="#"><i class="fa fa-comments"></i><span>23</span></a></li>
            <li><a href="#"><i class="fa fa-bell-o"></i><span>98</span></a></li>
            <li><a href="#"><i data-show="show-side-navigation1" class="fa fa-bars show-side-btn"></i></a></li>
          </ul>
        </div>
      </div>
    </nav>


    <div class="container col-md-8">

      <div class="form-group">
        <label for="Association">Association select</label>
        <select class="form-control" id="Association">
          <option value="1">Association 1</option>
          <option value="2">Association 2</option>
          <option value="3">Association 3</option>
          <option value="4">Association 4</option>
          <option value="5">Association 5</option>
        </select>
      </div>
      <div class="table-responsive">
        <table id="test-table" class="table table-condensed table-bordered">
          <thead>
            <tr>
              <th colspan="4" style="text-align: left;padding-left: 20px;">
                Add Paper List
              </th>
            </tr>
            <tr>
              <th>Paper Type</th>
              <th>Paper Size</th>
              <th>Paper Price</th>
              <th> <input id='addPaper' class='btn btn-primary' type='button' value='Add' /></th>
            </tr>
          </thead>
          <form action="C:\Users\GFX-I\Desktop\springmvc_maven.txt">
            <tbody id="paperList">
              <!-- Association Id -->
              <input name="selectedAssociationId" type="text" class="selectedAssociationId" hidden>
              <tr id="row0">
                <td>
                  <input name='paperType' value='' type='text' class='form-control' placeholder="Paper Type" />
                </td>
                <td>
                  <input name='paperSize' value='' type='text' class='form-control input-md' placeholder="Paper Size" />
                </td>

                <td>
                  <input name='paperPrice' value='' type='number' class='form-control input-md'
                    placeholder="Paper Price" />
                </td>

                <td>
                  <input class='delete-row btn btn-danger' type='button' value='X' />
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <td colspan="4" align="center">
                  <button class='btn btn-success' id="paperListSubmit">Submit</button>
                </td>
              </tr>

            </tfoot>
          </form>
        </table>

      </div>


    </div>

    <div class="container col-md-8">


      <div class="table-responsive">
        <table id="test-table" class="table table-condensed table-bordered">
          <thead>
            <tr>
              <th colspan="4" style="text-align: left;padding-left: 20px;">
                Add Cover List
              </th>
            </tr>
            <tr>
              <th>Cover Type</th>
              <th>Cover Size</th>
              <th>Cover Price</th>
              <th> <input id='addCover' class='btn btn-primary' type='button' value='Add' /></th>
            </tr>
          </thead>
          <form action="C:\Users\GFX-I\Desktop\springmvc_maven.txt">
            <!-- Association Id -->
            <input name="selectedAssociationId" type="text" class="selectedAssociationId" hidden>
            <tbody id="coverList">
              <tr id="row0">
                <td>
                  <input name='coverType' value='' type='text' class='form-control' placeholder="Cover Type" />
                </td>
                <td>
                  <input name='coverSize' value='' type='text' class='form-control input-md' placeholder="Cover Size" />
                </td>

                <td>
                  <input name='coverPrice' value='' type='number' class='form-control input-md'
                    placeholder="Cover Price" />
                </td>

                <td>
                  <input class='delete-row btn btn-danger' type='button' value='X' />
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <td colspan="4" align="center">
                  <button class='btn btn-success' id="coverListSubmit" onclick="">Submit</button>
                </td>
              </tr>

            </tfoot>
          </form>
        </table>

      </div>


    </div>









  </section>
  <script src='http://code.jquery.com/jquery-latest.js'></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
  <script src='http://localhost:8080/superuser/js/super-admin.js'></script>

  <script type="text/javascript">

    $('#Association').on('change', function () {
      //var optionValue = $(this).val();
      //var optionText = $('#dropdownList option[value="'+optionValue+'"]').text();
      var optionText = $("#Association option:selected").text();
      console.log("Selected Option Text: " + optionText);
      $(".selectedAssociationId").val(optionText);
    });


    // Add paper row
    var row = 1;
    $(document).on("click", "#addPaper", function () {
      var new_row = '<tr id="row' + row + '"><td><input name="paperType' + row + '" type="text" placeholder="Paper Type" class="form-control" /></td><td><input name="paperSize' + row + '" type="text" class="form-control" placeholder="Paper Size" /></td><td><input name="paperPrice' + row + '" type="number" class="form-control" placeholder="Paper Price" /></td><td><input class="delete-row btn btn-danger" type="button" value="X" /></td></tr>';

      $('#paperList').append(new_row);
      row++;
      return false;
    });

  </script>

  <script type="text/javascript">
    // Add cover row
    var row = 1;
    $(document).on("click", "#addCover", function () {
      var new_row = '<tr id="row' + row + '"><td><input name="coverType' + row + '" type="text" placeholder="Cover Type" class="form-control" /></td><td><input name="coverSize' + row + '" type="text" class="form-control" placeholder="Cover Size" /></td><td><input name="coverPrice' + row + '" type="number" class="form-control" placeholder="Cover Price" /></td><td><input class="delete-row btn btn-danger" type="button" value="X" /></td></tr>';

      $('#coverList').append(new_row);
      row++;
      return false;
    });

    // Remove criterion
    $(document).on("click", ".delete-row", function () {
      //  alert("deleting row#"+row);
      if (row > 1) {
        $(this).closest('tr').remove();
        row--;
      }
      return false;
    });

  </script>



</body>

</html>