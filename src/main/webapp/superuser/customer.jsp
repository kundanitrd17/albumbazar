<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html>

    <head>
        <title>Album Bazaar</title>


        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="_csrf" content="${_csrf.token}" />
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        <!-- ... -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


        <link rel="stylesheet" href="/superuser/css/super-admin.css">
        <style type="text/css">

        </style>

    </head>

    <body>

        <%@include file="sidebar.jsp" %>


            <section id="contents">
                <%@include file="header.jsp" %>



                    <div class="container">
                        <div class="row">
                            <div class="panel panel-primary filterable table-responsive">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Customers</h3>
                                    <div class="pull-right" style="position: relative;top: -20px;"><button
                                            class="btn btn-default btn-xs btn-filter"><span
                                                class="glyphicon glyphicon-filter"></span>
                                            Filter</button></div>
                                </div>
                                <table class="table " style="font-size: 12px;">
                                    <thead>
                                        <tr class="filters">
                                            <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                                            <th><input type="text" class="form-control" placeholder="Name" disabled>
                                            </th>
                                            <th><input type="text" class="form-control" placeholder="Phone" disabled>
                                            </th>
                                            <th><input type="text" class="form-control" placeholder="Email" disabled>
                                            </th>
                                            <th><input type="text" class="form-control" placeholder="Address" disabled>
                                            </th>
                                            <th><input type="text" class="form-control" placeholder="ReferralCode"
                                                    disabled>
                                            </th>
                                            <th><input type="text" class="form-control" placeholder="Discount" disabled>
                                            </th>
                                            <th><input type="text" class="form-control" placeholder="Status" disabled>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${customers}" var="customer">

                                            <tr>
                                                <td id="customerId${customer.id}" class="customerId"
                                                    data-customerId="${customer.id}">${customer.getCustomerId()}</td>
                                                <td>${customer.name}</td>
                                                <td>${customer.contactNo}</td>
                                                <td>${customer.email}</td>
                                                <td><a href="#">Address</a></td>
                                                <td>${customer.referralCode}</td>
                                                <td>${customer.discount}</td>

                                                <td>
                                                    <!-- Active  -->
                                                    <c:if test="${customer.active}">
                                                        <a href="#" class="btn btn-success act">Active</a>
                                                        <a href="#" class="btn btn-warning de-act"
                                                            style="display: none;">Deactive</a>


                                                    </c:if>
                                                    <!-- Deactive  -->
                                                    <c:if test="${!customer.active}">
                                                        <a href="#" class="btn btn-success act"
                                                            style="display: none;">Active</a>
                                                        <a href="#" class="btn btn-warning de-act">Deactive</a>

                                                    </c:if>
                                                </td>
                                            </tr>

                                        </c:forEach>


                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

            </section>





            <script type="text/javascript">

                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");

                // Deactivate an employee
                $('.table tbody tr td').on('click', '.act', function () {

                    var customerId = $(this).parent().siblings('.customerId').data();

                    console.log(customerId);
                    console.log(customerId.customerid);

                    $(this).hide();
                    $(this).siblings('.de-act').show();
                    $(this).parent().prevAll().css("background-color", "gray").focus();
                    $(this).parent().next().hide();


                    var xhr = new XMLHttpRequest();
                    var url = '/api/superuser/customer-delete';
                    xhr.open("DELETE", url, true);
                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () { // Call a function when the state changes.
                        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                            // console.log(typeof (JSON.parse(this.response)));
                            console.log(this.response);
                            // console.log(JSON.parse(this.response));
                        }
                    }
                    xhr.send(JSON.stringify(customerId.customerid))

                });

                // activate an employee
                $('.table tbody tr td').on('click', '.de-act', function () {

                    var customerId = $(this).parent().siblings('.customerId').data();

                    console.log(customerId);
                    console.log(customerId.customerid);

                    $(this).hide();
                    $(this).siblings().show();
                    $(this).parent().prevAll().css("background-color", "white").focus();
                    $(this).parent().next().show();


                    var xhr = new XMLHttpRequest();
                    var url = '/api/superuser/customer-restore';
                    xhr.open("PUT", url, true);
                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () { // Call a function when the state changes.
                        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                            // console.log(typeof (JSON.parse(this.response)));
                            console.log(this.response);
                            // console.log(JSON.parse(this.response));
                        }
                    }
                    xhr.send(JSON.stringify(customerId.customerid))

                });

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