<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>

        <meta name="_csrf" content="${_csrf.token}" />
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        <!-- ... -->


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">


        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
            integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
            crossorigin="anonymous" />
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->

        <!-- <link rel="stylesheet" href="http://localhost:8080/css/my_account.css">
    <link rel="stylesheet" href="http://localhost:8080/css/navbar.css"> -->


    </head>

    <body>

        <!-- Navbar Section -->
        <%@include file="navbar.jsp" %>
            <!-- End of Navbar section -->


            <main class="container-fluid" style="margin-top: 2rem; text-align: center;">

                <c:if test="${error != null}">
                    <div class="alert alert-danger" style="margin-top: 2rem;" role="alert">
                        ${error}
                    </div>
                </c:if>

                <div class="table-responsive-sm ">

                    <table class="table">
                        <caption>List of users</caption>
                        <thead>
                            <tr>
                                <th scope="col">UUID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Contact</th>
                                <th scope="col">Pickup Address</th>
                                <th scope="col">Delivery Address</th>
                                <th scope="col">Status</th>
                            </tr>
                        </thead>
                        <tbody>


                            <c:forEach items="${deliveries}" var="delivery">
                                <tr>
                                    <th scope="row">${delivery.UUID_CODE}</th>
                                    <td>${delivery.name}</td>
                                    <td>${delivery.contact}</td>
                                    <td>
                                        <button onclick="fetchAddress('${delivery.pickup_address}')" type="button"
                                            class="btn btn-info" data-toggle="modal" data-target="#AddressModal">
                                            Pickup Address
                                        </button>
                                    </td>
                                    <td>
                                        <button onclick="fetchAddress('${delivery.delivery_address}')" type="button"
                                            class="btn btn-info" data-toggle="modal" data-target="#AddressModal">
                                            Delivery Address
                                        </button>
                                    </td>
                                    <td>
                                        <button class="btn btn-success disabled" disabled>
                                            Done
                                            <i class="fas fa-check"></i>
                                        </button>
                                    </td>
                                </tr>

                            </c:forEach>

                        </tbody>
                    </table>
                </div>

            </main>


            <div class="modal" id="AddressModal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Modal Heading</h4>
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
                                        <th>Landmark: </th>
                                        <td id="landmark"></td>
                                    </tr>
                                    <tr>
                                        <th>Line1: </th>
                                        <td id="line1"></td>
                                    </tr>
                                    <tr>
                                        <th>Line2: </th>
                                        <td id="line2"></td>
                                    </tr>
                                    <tr>
                                        <th>City: </th>
                                        <td id="city"></td>
                                    </tr>
                                    <tr>
                                        <th>Pincode: </th>
                                        <td id="pincode"></td>
                                    </tr>
                                    <tr>
                                        <th>District: </th>
                                        <td id="district"></td>
                                    </tr>
                                    <tr>
                                        <th>State: </th>
                                        <td id="state"></td>
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







            <script src="http://localhost:8080/delivery/js/address.js"></script>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



            <!-- <script src="../js/manage_address.js"> -->

            </script>



    </body>

    </html>