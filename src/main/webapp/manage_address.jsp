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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="http://localhost:8080/css/my_account.css">
        <link rel="stylesheet" href="http://localhost:8080/css/navbar.css">


    </head>

    <body>
        <!-- Navbar Section -->
        <%@include file="navbar.jsp" %>

            <!-- End of Navbar section -->
            <div class="container-fluid">
                <div class="row">
                    <%@include file="customer_account_base_template.jsp" %>

                        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

                            <!-- Header band -->
                            <div
                                class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                                <h1 class="h2">Your Addresses</h1>

                                <div>
                                    <img src="http://localhost:8080/img/slide1.svg" alt=""
                                        style="height: 80px; width: 80px; border-radius: 50%;">
                                </div>
                            </div>
                            <!-- End of Header band -->

                            <!-- Content -->
                            <div class="content mb-3">
                                <!-- Button trigger modal new address -->
                                <button type="button" class="card-link btn btn-primary" data-toggle="modal"
                                    data-target="#addressModal">
                                    <i class="fa fa-plus"></i>
                                    New Address
                                </button>
                                <div class="address-list d-flex flex-wrap">

                                    <c:forEach items="${addresses}" var="address">
                                        <div class="card address-card-info" id="AddressCardInfo${address.id}"
                                            style="width: 18rem; margin: 20px; padding: 20px;">
                                            <div class="card-body ">
                                                <h6 class="card-title">${address.name}</h6>
                                                <div class="card-text">
                                                    <div>landmark: ${address.landmark}</div>
                                                    <div>${address.line1}</div>
                                                    <div>${address.line2}</div>
                                                    <div>City: ${address.city}</div>
                                                    <c:if test="${address.district} != null">
                                                        <div>District: ${address.district}</div>
                                                    </c:if>
                                                    <div>${address.state}- ${address.pincode}</div>
                                                    <hr>
                                                    <div style="font-weight: 500;">Mobile-${address.contactNo}</div>
                                                </div>
                                            </div>
                                            <ul class="list-group list-group-flush">
                                            </ul>
                                            <div class="card-body">
                                                <!-- Button trigger modal -->
                                                <button type="button" class="card-link btn btn-primary"
                                                    data-toggle="modal" data-target="#addressModal${address.id}">
                                                    Edit
                                                </button>
                                                <a href="#" class="card-link btn btn-danger DeleteAddressBtn"
                                                    onclick="deleteAddress('${address.id}', '${customer.id}')">Delete</a>
                                            </div>
                                        </div>

                                        <!-- Address Modal -->
                                        <div class="modal" id="addressModal${address.id}" tabindex="-1" role="dialog">
                                            <form action="/customer/address/info" method="POST">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <!-- Modal Header -->

                                                        <div class="modal-header">
                                                            <h5 class="modal-title">Address</h5>
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <!-- End of Modal Header -->
                                                        <div class="modal-body">
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                value="${_csrf.token}" />
                                                            <input type="text" value="${address.id}" name="id" hidden>
                                                            <div class="form-group">
                                                                <label for="exampleInputName${address.id}">Name</label>
                                                                <input name="name" type="name" class="form-control"
                                                                    id="exampleInputName${address.id}"
                                                                    value="${address.name}">
                                                            </div>
                                                            <div class="form-group">
                                                                <label
                                                                    for="exampleInputMobile${address.id}">Mobile</label>
                                                                <input name="contactNo" type="text" class="form-control"
                                                                    id="exampleInputMobile${address.id}"
                                                                    value="${address.contactNo}">
                                                            </div>
                                                            <hr>
                                                            <div class="form-group">
                                                                <label
                                                                    for="exampleInputLandmark${address.id}">Landmark</label>
                                                                <input name="landmark" type="text" class="form-control"
                                                                    id="exampleInputLandmark${address.id}"
                                                                    value="${address.landmark}" placeholder="Landmark">
                                                            </div>

                                                            <div class="form-group">
                                                                <label
                                                                    for="exampleInputAddress1${address.id}">Address</label>
                                                                <input name="line1" type="text" class="form-control"
                                                                    id="exampleInputAddress1${address.id}"
                                                                    value="${address.line1}"
                                                                    placeholder="Address line1">
                                                            </div>

                                                            <div class="form-group">
                                                                <label
                                                                    for="exampleInputAddress2${address.id}">Address</label>
                                                                <input name="line2" type="text" class="form-control"
                                                                    id="exampleInputAddress2${address.id}"
                                                                    value="${address.line2}"
                                                                    placeholder="Address line2">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="exampleInputCity${address.id}">City</label>
                                                                <input name="city" type="text" class="form-control"
                                                                    id="exampleInputCity${address.id}"
                                                                    value="${address.city}" placeholder="City">
                                                            </div>

                                                            <div class="form-group">
                                                                <label
                                                                    for="exampleInputDistrict${address.id}">District</label>
                                                                <input name="district" type="text" class="form-control"
                                                                    id="exampleInputDistrict${address.id}"
                                                                    value="${address.district}" placeholder="District">
                                                            </div>

                                                            <div class="row">
                                                                <div class="col">
                                                                    <label
                                                                        for="exampleInputState${address.id}">State</label>
                                                                    <input name="state" type="text" class="form-control"
                                                                        value="${address.state}" placeholder="State">
                                                                </div>
                                                                <div class="col">
                                                                    <label
                                                                        for="exampleInputPIN${address.id}">Pin-Code</label>
                                                                    <input name="pincode" type="text"
                                                                        class="form-control" value="${address.pincode}"
                                                                        placeholder="PIN">
                                                                </div>
                                                            </div>

                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" class="btn btn-primary">Save
                                                                changes</button>
                                                            <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">Close</button>
                                                        </div>

                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <!-- End of address modal -->


                                    </c:forEach>

                                </div>
                                <!-- Address Modal -->
                                <div class="modal" id="addressModal" tabindex="-1" role="dialog">
                                    <form action="/customer/address/info" method="POST">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <!-- Modal Header -->

                                                <div class="modal-header">
                                                    <h5 class="modal-title">Address</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <!-- End of Modal Header -->
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="exampleInputName">Name</label>
                                                        <input name="name" type="name" class="form-control"
                                                            id="exampleInputName" value="" placeholder="name">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="exampleInputMobile">Mobile</label>
                                                        <input name="contactNo" type="text" class="form-control"
                                                            id="exampleInputMobile" value="" placeholder="mobile">
                                                    </div>
                                                    <hr>
                                                    <div class="form-group">
                                                        <label for="exampleInputLandmark">Landmark</label>
                                                        <input name="landmark" type="text" class="form-control"
                                                            id="exampleInputLandmark" value="" placeholder="Landmark">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputAddress1">Address</label>
                                                        <input name="line1" type="text" class="form-control"
                                                            id="exampleInputAddress1" value=""
                                                            placeholder="Address line1">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputAddress2">Address</label>
                                                        <input name="line2" type="text" class="form-control"
                                                            id="exampleInputAddress2" value=""
                                                            placeholder="Address line2">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputCity">City</label>
                                                        <input name="city" type="text" class="form-control"
                                                            id="exampleInputCity" value="" placeholder="City">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputDistrict">District</label>
                                                        <input name="district" type="text" class="form-control"
                                                            id="exampleInputDistrict" value="" placeholder="District">
                                                    </div>

                                                    <div class="row">
                                                        <div class="col">
                                                            <label for="exampleInputState">State</label>
                                                            <input name="state" type="text" class="form-control"
                                                                value="" placeholder="State">
                                                        </div>
                                                        <div class="col">
                                                            <label for="exampleInputPIN">Pin-Code</label>
                                                            <input name="pincode" type="text" class="form-control"
                                                                value="" placeholder="PIN">
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary">Save
                                                        changes</button>
                                                    <button type="button" class="btn btn-secondary"
                                                        data-dismiss="modal">Close</button>
                                                </div>

                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- End of address modal -->
                            </div>
                            <!-- End of COntent -->
                        </main>
                </div>
            </div>






            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



            <script src="/js/manage_address.js">

            </script>



    </body>

    </html>