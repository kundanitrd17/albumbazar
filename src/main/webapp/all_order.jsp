<html lang="en">
<%@ page import="java.util.List, com.albumbazaar.albumbazar.model.OrderDetail" %>

<head>

  <meta charset="UTF-8">
  <title>Dashboard</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
  <link rel="stylesheet" href="superuser/css/super-admin.css">
  <link rel="stylesheet" type="text/css" href="superuser/css/form.css" />

</head>

<body>
  <%@include file="/superuser/sidebar.jsp" %>


  <section id="contents">
    <nav class="navbar navbar-default">
      <div class="container-fluid" style="background-color: seagreen;">
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
    <div class="welcome">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="content">
              <h2>All Orders</h2>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor.</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="panel panel-primary filterable table-responsive">
          <div class="panel-heading">
            <h3 class="panel-title">Order Details</h3>
            <div class="pull-right" style="position: relative;
top: -20px;"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                Filter</button></div>
          </div>

          <div class="table-responsive">

            <table class="table " style="font-size: 12px;">
              <thead>
                <tr class="filters">
                  <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Order Type" disabled></th>
                  <th><input type="text" class="form-control" placeholder=" Cust Id" disabled></th>
                  <th><input type="text" class="form-control" placeholder=" Sheet Id" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Loc Id" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Date" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Album Type" disabled></th>
                  <th><input type="text" class="form-control" placeholder="contact" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Cover" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Cover Price" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Size" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Orientation" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Association" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Description" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Payment" disabled></th>
                  <th><input type="text" class="form-control" placeholder="Branch" disabled></th>

                  <th colspan="2" style="text-align: center;"><a class="btn btn-success" href="add-branch.html">Accepted
                      Order</a></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Order Id</td>
                  <td>Disgn/Printing</td>
                  <td><a href="">Cust Id</a></td>
                  <td><a href="">Sheet Id</a></td>
                  <td><a href="">Loc Id</a></td>
                  <td>10/12/2004</td>
                  <td>Note Book</td>
                  <td>112346767</td>
                  <td>Glossy</td>
                  <td>100</td>
                  <td>Landscape/potrait</td>


                  <td><a class="btn btn-danger d-icon">Accepted</a></td>
                  <td class=""><a href="#" class="btn btn-success s-icon " style="display: none;">Save</a>
                    <button class="btn btn-warning e-icon">Edit</button></td>
                </tr>

                <%
              final List<OrderDetail> orders = (List<OrderDetail>)request.getAttribute("data");
              for(final OrderDetail order: orders) {
          %>
                <tr>

                  <td><%= order.getId() %></td>
                  <td><%= order.getId() %></td>
                  <td><a href=""><%= order.getId() %></a></td>
                  <td><a href=""><%= order.getId() %></a></td>
                  <td><a href=""><%= order.getId() %></a></td>
                  <td><%= order.getOrderTime() %></td>
                  <td><%= order.getId() %></td>
                  <td><%= order.getId() %></td>
                  <td><%= order.getId() %></td>
                  <td><%= order.getId() %></td>
                  <td><%= order.getId() %></td>


                  <td><a class="btn btn-danger d-icon">Accepted</a></td>
                  <td class=""><a href="#" class="btn btn-success s-icon " style="display: none;">Save</a>
                    <button class="btn btn-warning e-icon">Edit</button></td>

                </tr>
                <% } %>

              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>



  </section>
  <script src='http://code.jquery.com/jquery-latest.js'></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
  <script src='superuser/js/super-admin.js'></script>
  <script type="text/javascript" src="superuser/js/data-table.js"></script>

  <script type="text/javascript">



    $('.table tbody tr td').on('click', '.e-icon', function () {

      $(this).hide();
      $(this).siblings().show();
      $(this).parent().prev().prevAll().attr("contenteditable", "true").focus();

    });



    $('.table tbody tr td').on('click', '.s-icon', function () {

      $(this).hide();
      $(this).siblings().show();
      $(this).parent().prev().prevAll().attr("contenteditable", "false");

    });

    $('.table tbody tr td').on('click', '.d-icon', function () {

      $(this).closest('tr').remove();

    });




  </script>

  <script type="text/javascript">

    $('.table tbody tr td').on('click', '.act', function () {

      $(this).hide();
      $(this).siblings('.de-act').show();
      $(this).parent().prevAll().css("background-color", "gray").focus();
      $(this).parent().next().hide();
    });

    $('.table tbody tr td').on('click', '.de-act', function () {

      $(this).hide();
      $(this).siblings().show();
      $(this).parent().prevAll().css("background-color", "white").focus();
      $(this).parent().next().show();

    });

  </script>


</body>

</html>