<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>

    <html>

    <head>
        <title>Album Bazaar</title>
        <meta charset="utf-8">
        <meta name="_csrf" content="${_csrf.token}" />
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        <!-- ... -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

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
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                        aria-haspopup="true" aria-expanded="false">My profile <span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#"><i class="fa fa-user-o fw"></i> My account</a></li>
                                        <li><a href="#"><i class="fa fa-envelope-o fw"></i> My inbox</a></li>
                                        <li><a href="#"><i class="fa fa-question-circle-o fw"></i> Help</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li>
                                            <form action="/superuser/logout-super" method="POST">
                                                <div style="left: 200px;">
                                                    <i class="fa fa-sign-out"></i><button
                                                        style="outline: 0mm;">Logout</button>
                                                </div>
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                            </form>
                                        </li>
                                    </ul>
                                </li>
                                <li><a href="#"><i class="fa fa-comments"></i><span>23</span></a></li>
                                <li><a href="#"><i class="fa fa-bell-o"></i><span>98</span></a></li>
                                <li><a href="#"><i data-show="show-side-navigation1"
                                            class="fa fa-bars show-side-btn"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>






                <div class="container">
                    <div class="row">
                        <div class="panel panel-primary filterable table-responsive">
                            <div class="panel-heading">
                                <h3 class="panel-title">Users</h3>
                                <div class="pull-right" style="position: relative;
top: -20px;"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                                        Filter</button></div>
                            </div>
                            <table class="table " style="font-size: 12px;">
                                <thead>
                                    <tr class="filters">
                                        <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="Branch" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="OrderId" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="Order Time" disabled>
                                        <th><input type="text" class="form-control" placeholder="Amount" disabled>
                                        </th>
                                        <th colspan="2" style="text-align: center;"><a class="btn btn-success"
                                                href="expense">
                                                Expense</a></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${incomes}" var="income">

                                        <tr>
                                            <!-- It should be hidden and a UUID would be displaying here -->
                                            <td>
                                                ${income.id}</td>

                                            <td>branch</td>
                                            <td>
                                                <!-- Button trigger modal -->
                                                <button onclick="loadOrderInfo('${income.order.id}')" type="button"
                                                    class="btn" data-toggle="modal" data-target="#orderDetailModal">
                                                    ${income.order.id}
                                                </button>
                                            </td>
                                            <td>${income.receivedTime}</td>
                                            <td>${income.amount}</td>

                                            <!-- <td><a class="btn btn-danger d-icon">Delete</button></a></td> -->
                                        </tr>

                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>


            </section>



            <!-- Modal -->
            <div class="modal fade" id="orderDetailModal" tabindex="-1" role="dialog"
                aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Order Info</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <!-- 
                            <table class="table">
                                <thead>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">Order Id</th>
                                        <td>2</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Association Id</th>
                                        <td>2</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Product Name</th>
                                        <td>Harshawa</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Product Size</th>
                                        <td>Harshawa</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Cover Name</th>
                                        <td>Harshawa</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Cover Price</th>
                                        <td>12</td>
                                    </tr>

                                    <tr>
                                        <th scope="row">orientation</th>
                                        <td>landsac</td>
                                    </tr>

                                    <tr>
                                        <th scope="row">Paper Name</th>
                                        <td>Paper Price</td>
                                    </tr>

                                    <tr>
                                        <th scope="row">Status</th>
                                        <td>staus</td>
                                    </tr>

                                    <tr>
                                        <th scope="row">Description</th>
                                        <td>staus</td>
                                    </tr>

                                    <tr>
                                        <th scope="row">Bill</th>
                                        <td>Amount</td>
                                        <td>Amount</td>
                                        <td>Amount</td>
                                    </tr>

                                </tbody>
                            </table> -->


                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>



            <script>
                function loadOrderInfo(order_id) {

                    const xhr = new XMLHttpRequest();
                    const url = 'http://localhost:8080/api/secured/order/' + order_id;
                    console.log(url);
                    xhr.open('GET', url, true);

                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            let data = JSON.parse(this.response);
                            console.log(data);
                            console.log(JSON.parse(data["paperDetailsWithNumberOfSheetsList"]));
                        }
                    }

                    xhr.send(null);
                }
            </script>


            <script type="text/javascript" src="/superuser/js/data-table.js"></script>


            <script src='http://code.jquery.com/jquery-latest.js'></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
            <script src='/superuser/js/super-admin.js'></script>
    </body>

    </html>