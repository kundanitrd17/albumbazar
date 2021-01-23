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

        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">


        <link rel="stylesheet" href="/superuser/css/super-admin.css">

        <style type="text/css">
            #orderProductViewDetails table tr th {
                text-align: center;
            }

            #orderProductViewDetails table tr th h6 {

                font-size: 13px;
            }

            #orderProductViewDetails table tr:nth-child(odd) {
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

            #orderProductViewDetails table tr:nth-child(even) {
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




                <div class="container" style="margin-top: 20px;" id="root">
                    <div class="row">

                        <div class="panel panel-primary filterable table-responsive">
                            <div class="panel-heading">
                                <h3 class="panel-title">Accepted Order</h3>
                                <div class="pull-right" style="position: relative;
top: -20px;"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                                        Filter</button></div>
                            </div>
                            <table class="table table-responsive" style="font-size: 12px;">
                                <thead>
                                    <tr class="filters">
                                        <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="CustomerId" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Product" disabled></th>

                                        <th><input type="text" class="form-control" placeholder="OrderTime" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Description" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder=" Address" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Orientation" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Product View" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Images" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="Status" disabled></th>
                                        <th colspan="2" style="text-align: center;"><a class="btn btn-success"
                                                href="order-pool">Order Pool</a></th>
                                    </tr>
                                </thead>
                                <tbody>


                                    <c:forEach items="${allOrders}" var="eachOrder">

                                        <tr>
                                            <td class="orderId" data-order-id="${eachOrder.id}">${eachOrder.id}</td>
                                            <td class="customerId" data-order-id="${eachOrder.customer.id}">
                                                <button class="btn"
                                                    onclick="fetchCustomerDetails('${eachOrder.customer.id}')"
                                                    data-toggle="modal"
                                                    data-target="#customerDetailModal">${eachOrder.customer.getCustomerId()}</button>
                                            </td>
                                            <td>${eachOrder.productName}</td>
                                            <td>${eachOrder.orderTime}</td>
                                            <td>
                                                <a href="" data-toggle="modal"
                                                    data-target="#orderDescriptionDialog${eachOrder.id}">
                                                    click
                                                </a>
                                                <div class="modal" tabindex="-1" role="dialog"
                                                    id="orderDescriptionDialog${eachOrder.id}">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title">Order Description</h5>
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                    aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="form-group">
                                                                    <label>Example textarea</label>
                                                                    <input class="form-control"
                                                                        id="updatableOrderDescription${eachOrder.id}"
                                                                        rows="3" value="${eachOrder.description}">
                                                                </div>

                                                            </div>
                                                            <div class="modal-footer">
                                                                <button
                                                                    onclick="changeOrderDescription('${eachOrder.id}')"
                                                                    type="button" class="btn btn-primary">Save
                                                                    changes</button>
                                                                <button type="button" class="btn btn-secondary"
                                                                    data-dismiss="modal">Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td><a href="" data-toggle="modal" data-target="#addressModal"
                                                    id="link_address"
                                                    onclick="fetchDeliveryAddressFromServer('${eachOrder.id}')">Address
                                                </a></td>
                                            <td>${eachOrder.orientation}</td>
                                            <td><a type="button" href="" id="link_adminId" data-toggle="modal"
                                                    data-target="#orderProductViewDetails"
                                                    onclick="orderProductView('${eachOrder.id}')">View Product</a></td>
                                            <td><a target="_blank"
                                                    href="${eachOrder.photoFolderGoogleDriveLink}">Images</a></td>

                                            <td>
                                                <!-- <a class="btn btn-danger change-status-icon">a> -->
                                                <button class="btn btn-danger"
                                                    disabled>${eachOrder.orderStatus}</button>
                                            </td>
                                            <c:if
                                                test="${eachOrder.isForwardedToAssociation == null || !eachOrder.isForwardedToAssociation}">

                                                <td><button onclick="forwardOrderToAssociation('${eachOrder.id}')"
                                                        class="btn btn-primary">forward</button></td>

                                            </c:if>

                                        </tr>

                                    </c:forEach>


                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>



            </section>








            <div class="modal" id="orderProductViewDetails">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">Company Name</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>


                        <div class="modal-body table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="5">Cover Info</th>
                                    </tr>
                                    <tr style="background-color: none;color:black;">
                                        <th>Product Name</th>
                                        <th>Product Size</th>
                                        <th>CoverName</th>
                                        <th>Cover Size</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td id="productName">album</td>
                                        <td id="productSize">90*09</td>
                                        <td id="coverName">John</td>
                                        <td id="coverSize">Doe</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>


                        <div class="modal-body table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="5">Paper Info</th>
                                    </tr>
                                    <tr style="background-color: none;color:black;">
                                        <th>Quality</th>
                                        <th>Size</th>
                                        <th>No. of Sheets</th>
                                    </tr>
                                </thead>
                                <tbody id="paperListRow">
                                    <tr>
                                        <td id="pageQuality">John</td>
                                        <td id="pageSize">Doe</td>
                                        <td id="numberOfSheets">200</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>

                        </div>
                    </div>
                </div>
            </div>


            <div class="modal fade" id="largeModal" tabindex="-1" role="dialog" aria-labelledby="basicModal"
                aria-hidden="true">

            </div>



            <!-- Customer detail modal -->
            <div class="modal" id="customerDetailModal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Customer</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <table class="table table-borderless">
                                <tbody>
                                    <tr>
                                        <th>Name: </th>
                                        <td id="name"></td>
                                    </tr>
                                    <tr>
                                        <th>Contact: </th>
                                        <td id="contact"></td>
                                    </tr>
                                    <tr>
                                        <th>Email: </th>
                                        <td id="email"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>

                    </div>
                </div>
            </div>

            <!-- End of customer Detail modal -->





            <!-- Address Modal -->
            <div class="modal" id="addressModal" tabindex="-1" role="dialog">
                <form action="/admin/order/address/change" method="POST">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <!-- Modal Header -->

                            <div class="modal-header">
                                <h5 class="modal-title">Address</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <!-- End of Modal Header -->
                            <div class="modal-body">
                                <input type="hidden" name="orderId" value="">
                                <input type="text" value="" name="id" hidden>
                                <div class="form-group">
                                    <label for="exampleInputName">Name</label>
                                    <input name="name" type="name" class="form-control" id="exampleInputName" value="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputMobile">Mobile</label>
                                    <input name="contactNo" type="text" class="form-control" id="exampleInputMobile"
                                        value="">
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="exampleInputLandmark">Landmark</label>
                                    <input name="landmark" type="text" class="form-control" id="exampleInputLandmark"
                                        value="" placeholder="Landmark">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputAddress1">Address</label>
                                    <input name="line1" type="text" class="form-control" id="exampleInputAddress1"
                                        value="" placeholder="Address line1">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputAddress2">Address</label>
                                    <input name="line2" type="text" class="form-control" id="exampleInputAddress2"
                                        value="" placeholder="Address line2">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputCity">City</label>
                                    <input name="city" type="text" class="form-control" id="exampleInputCity" value=""
                                        placeholder="City">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputDistrict">District</label>
                                    <input name="district" type="text" class="form-control" id="exampleInputDistrict"
                                        value="" placeholder="District">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputState">State</label>
                                    <input name="state" type="text" class="form-control mx-200" value=""
                                        placeholder="State">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputPIN">Pin-Code</label>
                                    <input name="pincode" type="text" class="form-control mx-200" value=""
                                        placeholder="PIN">
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">Save
                                    changes</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>

                        </div>
                    </div>
                </form>
            </div>

            <!-- End of address modal -->



            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

            <script type="text/javascript" src="/customercare/js/data-table.js"></script>
            <script type="text/javascript" src="/customercare/js/order_status.js"></script>
            <script type="text/javascript" src="/admin/js/admin_order.js"></script>




            <script type="text/javascript">

                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");

                window.onload = function () {

                    token = $("meta[name='_csrf']").attr("content");
                    header = $("meta[name='_csrf_header']").attr("content");

                }

            </script>

            <script type="text/javascript">

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


            <script>
                function forwardOrderToAssociation(order_id) {

                    const conf = confirm("Confirm Forward to Association");
                    if (conf == null || !conf) {
                        return false;
                    }

                    const xhr = new XMLHttpRequest();

                    const url = "/api/secured/order/forward/association";

                    xhr.open('PUT', url, true);

                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");

                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            console.log("done");
                        } else if (this.readyState === 4 && this.status === 405) {
                            const alertMsg = document.createElement('div');
                            alert("Invalid Deliver");

                            alertMsg.className = "alert alert-danger";
                            alertMsg.innerHTML = "Delivery Address is Empty...!";

                            document.getElementById("root").prepend(alertMsg);

                        }
                        else if (this.readyState === 4) {
                            alert("Something went Wrong... Please try again");
                        }
                    }

                    xhr.send(JSON.stringify(order_id));

                }
            </script>

    </body>

    </html>