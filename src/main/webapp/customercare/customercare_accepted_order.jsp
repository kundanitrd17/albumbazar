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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="http://localhost:8080/customercare/css/dashboard-superuser.css">


    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
    <section id="contents"style="background-color: #A09EA2;">
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
                                    <td>

                                        <c:if test="${eachOrder.photoFolderGoogleDriveLink!=null || eachOrder.photoFolderGoogleDriveLink.length() > 5 }">
                                            <a target="_blank"
                                            href="${eachOrder.photoFolderGoogleDriveLink}">Images</a>
                                        </c:if>
                                        <c:if test="${eachOrder.photoFolderGoogleDriveLink==null || eachOrder.photoFolderGoogleDriveLink.length() <= 5}">
                                            <button type="button" class="btn btn-primary" disabled
                                            >No photos</button>
                                        </c:if>
                                    </td>

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



    


    <!-- Order product view -->
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
                <form action="/customer-care/order/address/change" method="POST">

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






    <script type="text/javascript" src="/customercare/js/data-table.js"></script>
    <script type="text/javascript" src="/customercare/js/order_status.js"></script>
    <script src="/customercare/js/order_utility.js"></script>

    <script type="text/javascript">

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        window.onload = function() {

            token = $("meta[name='_csrf']").attr("content");
            header = $("meta[name='_csrf_header']").attr("content");

        }




        // Editable 
        $('.table tbody tr td').on('click', '.e-icon', function () {

            $(this).hide();
            $(this).siblings().show();
            $(this).parent().siblings("#associationName").attr("contenteditable", "true").focus();
            $(this).parent().siblings("#associationPhone1").attr("contenteditable", "true").focus();
            $(this).parent().siblings("#associationPhone2").attr("contenteditable", "true").focus();
            $(this).parent().siblings("#associationEmail").attr("contenteditable", "true").focus();

        });




        // Save Edits

        $('.table tbody tr td').on('click', '.s-icon', function () {

            $(this).hide();
            $(this).siblings('.e-icon').show();
            $(this).parent().siblings("").attr("contenteditable", "false");

        });



        // $('.table tbody tr td').on('click', '.d-icon', function () {

        //     $(this).closest('tr').remove();

        // });


        $('.table tbody tr td').on('click', '.u-icon', function () {

            $(this).hide();
            $(this).siblings().show();
            $(this).parent().parent().prevAll().children('td').attr("contenteditable", "true").focus().css({ "background-color": "#eeeeee", "width": "200px" });

        });

        //view Branch Address

        function addrLink(id) {
            $('#branchAddress #hidden_address_id').val(id);

            console.log("Address");
            // $.Post("url", { id: id }, function (data, status) {

            //     var addr = JSON.parse(data);
            //     $('#landmark').text("kundan").css("text-tranform", "capitalize");
            //     $('#street1').text("upper Kulti").css("text-tranform", "capitalize");
            //     $('#street2').text().css("text-tranform", "capitalize");
            //     $('#postoffice').text().css("text-tranform", "capitalize");
            //     $('#city').text().css("text-tranform", "capitalize");
            //     $('#pincode').text().css("text-tranform", "capitalize");
            //     $('#district').text().css("text-tranform", "capitalize");
            //     $('#state').text().css("text-tranform", "capitalize");

            // })
        }

        //update branch address
        function u_address(id) {
            var id = $('#branchAddress #hidden_address_id').val();

            var lendmark = $('#branchAddress #landmark').text("kundan");
            var street1 = $('#branchAddress #street1').text("upper Kulti");
            var street2 = $('#branchAddress #street2').text();
            var postoffice = $('#branchAddress #postoffice').text();
            var city = $('#branchAddress #city').text();
            var pincode = $('#branchAddress #pincode').text();
            var district = $('#branchAddress #district').text();
            var state = $('#branchAddress #state').text();

            alert(id);
        }
        //show association details
        function associationProductView(id) {
            $('#adminDetails #hidden_admin_id').val(id);
            console.log("Admin");
            // var url = "";
            // $.Post("url", { id: id }, function (data, status) {

            //     $('#adminDetails #Name').text("kundan").css("text-tranform", "capitalize");
            //     $('#adminDetails #Branch').text("upper Kulti").css("text-tranform", "capitalize");
            //     $('#adminDetails #contact').text().css("text-tranform", "capitalize");
            //     $('#adminDetails #email').text().css("text-tranform", "capitalize");
            //     $('#adminDetails #salary').text().css("text-tranform", "capitalize");
            //     $('#adminDetails #jd').text().css("text-tranform", "capitalize");

            // });

        }

        //save Branch
        function saveBranch(id) {
            var branchId = id;
            var branchName = $("table tbody tr td#branchName").text();
            console.log(branchName);

        }



    </script>

    <!--Edit Page-->

    <script type="text/javascript">
        // Delete Association Product
        $('.table tbody tr td').on('click', '.deletePage', function () {
            $(this).parent().parent().remove();
            event.preventDefault();
            const content = $(this).parent().prevAll().toArray();
            const data = {};
            content.forEach(item => {
                if (item.id === "branchId") {
                    data["id"] = item.innerText;
                }
                if (item.id === "branchName") {
                    data["name"] = item.innerText;
                }
                if (item.id === "branchContact") {
                    data["phone"] = item.innerText;
                }
            })

            console.log(JSON.stringify(data));
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var xhr = new XMLHttpRequest();
            var url = '/api/product/post';
            xhr.open("POST", url, true);
            xhr.setRequestHeader('Content-type', 'application/json');
            xhr.setRequestHeader(header, token);

            xhr.onreadystatechange = function () { // Call a function when the state changes.
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                    // console.log(typeof (JSON.parse(this.response)));
                    console.log(JSON.parse(this.response)["name"]);
                }
            }
            xhr.send(JSON.stringify(data))

        })

        // Edit Association Product 
        $('.table tbody tr td').on('click', '.editPage', function () {

            $(this).hide();
            $(this).siblings().show();
            $(this).parent().siblings("#pageQuality").attr("contenteditable", "true").focus();
            $(this).parent().siblings("#pageSize").attr("contenteditable", "true").focus();
            $(this).parent().siblings("#pagePrice").attr("contenteditable", "true").focus();
        });
        //save And Edit Display Button
        $('.table tbody tr td').on('click', '.savePage', function () {

            $(this).hide();
            $(this).siblings('.editPage').show();
            $(this).parent().siblings("").attr("contenteditable", "false");

        });
    </script>
    <!--End Edit Page-->


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