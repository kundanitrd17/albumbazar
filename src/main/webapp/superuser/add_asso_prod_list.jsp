<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <!DOCTYPE html>

  <html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->
    <title>Dashboard</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
    <link rel="stylesheet" href="/superuser/css/super-admin.css">
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

      .loader {
        position: absolute;
        margin-top: 25%;
        margin-left: 30%;
        z-index: 1;

        border: 16px solid #f3f3f3;
        /* Light grey */
        border-top: 16px solid #3498db;
        /* Blue */
        border-radius: 50%;
        width: 120px;
        height: 120px;
        animation: spin 2s linear infinite;
      }

      @keyframes spin {
        0% {
          transform: rotate(0deg);
        }

        100% {
          transform: rotate(360deg);
        }
      }
    </style>

    <link rel="stylesheet" href="/superuser/css/super-admin.css">
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

        <main>
          <!-- Modal -->
          <div class="modal fade loader" id="exampleModal" tabindex="-1" role="dialog"
            aria-labelledby="exampleModalLabel" aria-hidden="true">
          </div>


          <div class="container col-md-8">

            <div class="form-group">
              <label for="Association">Association select</label>
              <select class="form-control" id="Association">
                <option value="null" selected>--Select--</option>

                <c:forEach items="${availableAssociations}" var="association">
                  <option value="${association.id}">${association.name}</option>
                </c:forEach>

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
                    <th>GST</th>
                    <th> <input id='addPaper' class='btn btn-primary' type='button' value='Add' /></th>
                  </tr>
                </thead>
                <form action="#" id="paperForm">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  <tbody id="paperList">

                    <!-- <tr id="paperRow0">
                      <td>
                        <input name='paperQuality' value='' type='text' class='form-control' placeholder="Paper Type" />
                      </td>
                      <td>
                        <input name='paperSize' value='' type='text' class='form-control input-md'
                          placeholder="Paper Size" />
                      </td>

                      <td>
                        <input name='paperPrice' value='' type='number' class='form-control input-md'
                          placeholder="Paper Price" />
                      </td>

                      <td>
                        <input name='GST' value='' type='number' class='form-control input-md' placeholder="GST" />
                      </td>

                      <td>
                        <input class='delete-paper-row btn btn-danger' type='button' value='X' />
                      </td>
                    </tr> -->
                  </tbody>
                  <tfoot>
                    <tr>
                      <td colspan="4" align="center">
                        <button type="submit" class='btn btn-success' id="paperListSubmit">Submit</button>
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
                    <th>Cover Image</th>
                    <th>Cover Type</th>
                    <th>Cover Size</th>
                    <th>Cover Price</th>
                    <th>GST</th>
                    <th> <input id='addCover' class='btn btn-primary' type='button' value='Add' /></th>
                  </tr>
                </thead>
                <form id="coverForm" action="#">
                  <!-- Association Id -->
                  <!-- <input name="selectedAssociationId" type="text" class="selectedAssociationId" hidden> -->
                  <tbody id="coverList">
                    <!-- <tr id="coverRow0">
                    <td>
                      <div class="image-upload text-center">
                        <label for="file-input">
                          <img src="https://icon-library.net/images/upload-photo-icon/upload-photo-icon-21.jpg"
                            style=" height: 30px; width: 30px;" />
                        </label>

                        <input id="file-input" name="image" type="file" style="display: none;" />
                      </div>
                    </td>
                    <td>
                      <input name='coverName' value='' type='text' class='form-control' placeholder="Cover Type" />
                    </td>
                    <td>
                      <input name='coverSize' value='' type='text' class='form-control input-md'
                        placeholder="Cover Size" />
                    </td>

                    <td>
                      <input name='coverPrice' value='' type='number' class='form-control input-md'
                        placeholder="Cover Price" />
                    </td>

                    <td>
                      <input class='delete-cover-row btn btn-danger' type='button' value='X' />
                    </td>
                  </tr> -->
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


        </main>






      </section>

      <script src='http://code.jquery.com/jquery-latest.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>


      <script src='/superuser/js/super-admin.js'></script>
      <script src="/superuser/js/add_paper_cover.js"></script>


      <script type="text/javascript">


      </script>



  </body>

  </html>