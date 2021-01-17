<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html>

    <head>
        <title>Album Bazaar</title>
        <meta charset="utf-8">
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
                                        <th><input type="text" class="form-control" placeholder="Name" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="contact1" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="contact2" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="email" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="Status" disabled></th>
                                        <th colspan="2" style="text-align: center;"><a class="btn btn-success"
                                                href="add-employee">Add
                                                Employee</a></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <!-- ${associations} -->
                                    <c:forEach items="${associations}" var="association">
                                        <tr>
                                            <td>${association.id}</td>
                                            <td>${association.name}</td>
                                            <td>${association.contact1}</td>
                                            <td>${association.contact2}</td>
                                            <td>${association.email}</td>
                                            <td><a href="#" class="btn btn-success act disabled">Active</a>
                                                <a href="#" class="btn btn-warning de-act"
                                                    style="display: none;">Deactive</a>
                                            </td>
                                            <!-- <td class=""><a href="#" class="btn btn-success s-icon "
                                                    style="display: none;">Save</a>
                                                <button class="btn btn-warning e-icon">Edit</button>
                                            </td> -->
                                            <td><a class="btn btn-danger d-icon">Delete</button></a></td>

                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>


            </section>






            <script type="text/javascript">



                $('.table tbody tr td').on('click', '.e-icon', function () {

                    $(this).hide();
                    $(this).siblings().show();
                    $(this).parent().prev().prevAll().attr("contenteditable", "true").focus();

                });


                // Edit an employee
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

                // Deactivate an employee
                $('.table tbody tr td').on('click', '.act', function () {

                    $(this).hide();
                    $(this).siblings('.de-act').show();
                    $(this).parent().prevAll().css("background-color", "gray").focus();
                    $(this).parent().next().hide();
                });

                // activate an employee
                $('.table tbody tr td').on('click', '.de-act', function () {

                    $(this).hide();
                    $(this).siblings().show();
                    $(this).parent().prevAll().css("background-color", "white").focus();
                    $(this).parent().next().show();

                });

            </script>



            <script src='http://code.jquery.com/jquery-latest.js'></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
            <script src='http://localhost:8080/superuser/js/super-admin.js'></script>
            <script type="text/javascript" src="http://localhost:8080/superuser/js/data-table.js"></script>
    </body>

    </html>