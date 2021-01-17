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
<!-- 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
        integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
        crossorigin="anonymous" /> -->



        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/customercare/css/dashboard-superuser.css">

        <style type="text/css">
            #associationProductViewDetails table tr th {
                text-align: center;
            }

            #associationProductViewDetails table tr th h6 {

                font-size: 13px;
            }

            #associationProductViewDetails table tr:nth-child(odd) {
                background: rgba(226, 226, 226, 1);
                background: -moz-linear-gradient(left, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
                background: -webkit-gradient(left top, right top, color-stop(0%, rgba(226, 226, 226, 1)), color-stop(1%, rgba(209, 209, 209, 1)), color-stop(51%, rgba(133, 128, 133, 1)), color-stop(100%, rgba(254, 254, 254, 1)));
                background: -webkit-linear-gradient(left, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
                background: -o-linear-gradient(left, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
                background: -ms-linear-gradient(left, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
                background: linear-gradient(to right, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e2e2e2', endColorstr='#fefefe', GradientType=1);
                ;
                color: white;
                text-align: center;

            }

            #associationProductViewDetails table tr:nth-child(even) {
                background-color: #A09EA2;
                color: white;
                text-align: center;

            }
        </style>

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
                                        <th><input type="text" class="form-control" placeholder="Description" disabled>
                                        </th>

                                        <th><input type="text" class="form-control" placeholder="Sheet Details"
                                                disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Cover" disabled>
                                        </th>

                                        <th><input type="text" class="form-control" placeholder="Images" disabled></th>


                                        <th colspan="2" style="text-align: center;"><a class="btn btn-success"
                                                href="/association/order-list/processing">View Accepted Order
                                            </a></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${allOrders}" var="eachOrder">
                                        <tr id="orderRow${eachOrder.id}">
                                            <td class="orderId" id="orderId${eachOrder.id}"
                                                data-orderId="${eachOrder.id}">${eachOrder.id}</td>

                                                

                                            <td id="OrderDescription">
                                                
                                                <button type="button" class="btn"
                                                    data-toggle="modal"
                                                    data-target="#DescriptionCard${eachOrder.id}">Show
                                                    Description</button>

                                                <div class="modal" id="DescriptionCard${eachOrder.id}">
                                                    <div class="modal-dialog modal-lg">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h4 class="modal-title" id="myModalLabel">Comapny Name
                                                                </h4>
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                    aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">

                                                                ${eachOrder.description}

                                                            </div>

                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-danger"
                                                                    data-dismiss="modal">Close</button>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>

                                            <td id="OrderPageDetails"><a class="btn">Show Pages Detail</a></td>
                                            <td id="OrderCoverPage">${eachOrder.cover.coverName}
                                            </td>

                                            <td id="OrderImages">
                                                <a class="btn" href="${eachOrder.photoFolderGoogleDriveLink}"
                                                    target="_blank">Show
                                                    Images</a>
                                            </td>

                                            <td><a class="btn btn-success disabled"
                                                    href="#">
                                                    Done ✔ </a></td>
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