<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <head>

    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">


    <link rel="stylesheet" href="http://localhost:8080/superuser/css/super-admin.css">
    <!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/superuser/css/form.css" /> -->

  </head>

  <body>
    <%@include file="sidebar.jsp" %>


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
                  ${data}
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor.</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Table -->
        <div class="container">
          <div class="row">
            <div class="panel panel-primary filterable table-responsive">
              <div class="panel-heading">
                <h3 class="panel-title">Customers</h3>
                <div class="pull-right" style="position: relative;top: -20px;"><button
                    class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                    Filter</button></div>
              </div>
              <table class="table " style="font-size: 12px;">
                <thead>
                  <tr class="filters">
                    <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                    <th><input type="text" class="form-control" placeholder="customer" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Association" disabled></th>
                    <th><input type="text" class="form-control" placeholder="product" disabled></th>
                    <th><input type="text" class="form-control" placeholder="cover" disabled></th>
                    <th><input type="text" class="form-control" placeholder="orientation" disabled></th>
                    <th><input type="text" class="form-control" placeholder="time" disabled></th>
                    <th><input type="text" class="form-control" placeholder="discount" disabled></th>
                    <th><input type="text" class="form-control" placeholder="amount" disabled></th>
                    <th><input type="text" class="form-control" placeholder="status" disabled></th>

                  </tr>
                </thead>
                <tbody>

                  <c:forEach items="${order_details}" var="order_detail">
                    <tr>
                      <td>${order_detail.id}</td>
                      <td><a href="#">${order_detail.customer.id}</a></td>
                      <td>${order_detail.associationName}</td>
                      <td>${order_detail.productName}</td>
                      <td>${order_detail.coverName}</td>
                      <td>${order_detail.orientation}</td>
                      <td>${order_detail.orderTime}</td>
                      <td>${order_detail.discount}</td>
                      <td>${order_detail.totalAmount}</td>
                      <td><button class="btn btn-success">${order_detail.orderStatus}</button></td>

                    </tr>
                  </c:forEach>


                </tbody>
              </table>
            </div>
          </div>
        </div>







      </section>
      <script src='http://code.jquery.com/jquery-latest.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
      <script src='http://localhost:8080/superuser/js/super-admin.js'></script>
      <script type="text/javascript" src="http://localhost:8080/superuser/js/data-table.js"></script>



  </body>

</html>