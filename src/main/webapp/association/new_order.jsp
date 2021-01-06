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
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">


        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/customercare/css/dashboard-superuser.css"> -->

        <link rel="stylesheet" href="http://localhost:8080/superuser/css/super-admin.css">

    </head>

    <body>

        <!-- SideBar insertion -->
        <%@include file="sidebar.jsp" %>
            <!-- End of sidebar -->

            <section id="contents" style="background-color: #A09EA2;">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed show-side-btn" data-toggle="collapse"
                                data-target="" aria-expanded="false">

                            </button>
                            <ul class="nav navbar-nav">
                                <li id="bs-example-navbar-collapse-1"> <a href="#"><i data-show="show-side-navigation1"
                                            class="fa fa-bars show-side-btn"></i></a></li>
                            </ul>
                        </div>
                        <div class="collapse navbar-collapse navbar-right">

                        </div>
                    </div>
                </nav>


                <div class="container">
                    <input type="text" id="employee_id_hidden" value="${employee_id}" hidden>
                    <div class="row">
                        <div class="panel panel-primary filterable table-responsive">
                            <div class="panel-heading">
                                <h3 class="panel-title">Order Pool</h3>
                                <div class="pull-right" style="position: relative;
top: -20px;"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                                        Filter</button></div>
                            </div>
                            <table class="table table-responsive " style="font-size: 12px;">
                                <thead>
                                    <tr class="filters">
                                        <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                                        <th><input type="text" class="form-control" placeholder=" Name" disabled></th>

                                        <th><input type="text" class="form-control" placeholder="Contact 1" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Contact 2" disabled>
                                        </th>

                                        <th><input type="text" class="form-control" placeholder="E-mail" disabled></th>


                                        <th colspan="2" style="text-align: center;"><a class="btn btn-success"
                                                href="/association/order-list/processing">View Accepted Order
                                            </a></th>
                                    </tr>
                                </thead>
                                <tbody>



                                    <c:forEach items="${recentlyReceivedOrders}" var="eachOrder">
                                        <tr id="orderRow${eachOrder.id}">
                                            <td class="orderId" id="orderId${eachOrder.id}"
                                                data-orderId="${eachOrder.id}">${eachOrder.id}</td>
                                            <td id="associationName">${eachOrder.associationName}</td>
                                            <td id="associationPhone1">${eachOrder.orderStatus}</td>
                                            <td id="associationPhone2">9832177025</td>

                                            <td id="associationEmail">kundanitrd17@gmail.com</td>

                                            <td><a class="btn btn-danger accept-order-icon">Accept Order</a></td>
                                        </tr>


                                    </c:forEach>





                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </section>


            <script type="text/javascript" src="http://localhost:8080/customercare/js/data-table.js"></script>


            <script type="text/javascript">
                // Other important pens.
                // Map: https://codepen.io/themustafaomar/pen/ZEGJeZq
                // Navbar: https://codepen.io/themustafaomar/pen/VKbQyZ

                $(function () {

                    'use strict';

                    var aside = $('.side-nav'),
                        showAsideBtn = $('.show-side-btn'),
                        contents = $('#contents'),
                        _window = $(window)

                    showAsideBtn.on("click", function () {
                        $("#" + $(this).data('show')).toggleClass('show-side-nav');
                        contents.toggleClass('margin');
                    });

                    if (_window.width() <= 767) {
                        aside.addClass('show-side-nav');
                    }

                    _window.on('resize', function () {
                        if ($(window).width() > 767) {
                            aside.removeClass('show-side-nav');
                        }
                    });

                    // dropdown menu in the side nav
                    var slideNavDropdown = $('.side-nav-dropdown');

                    $('.side-nav .categories li').on('click', function () {

                        var $this = $(this)

                        $this.toggleClass('opend').siblings().removeClass('opend');

                        if ($this.hasClass('opend')) {
                            $this.find('.side-nav-dropdown').slideToggle('fast');
                            $this.siblings().find('.side-nav-dropdown').slideUp('fast');
                        } else {
                            $this.find('.side-nav-dropdown').slideUp('fast');
                        }
                    });

                    $('.side-nav .close-aside').on('click', function () {
                        $('#' + $(this).data('close')).addClass('show-side-nav');
                        contents.removeClass('margin');
                    });



                });
            </script>


    </body>

    </html>