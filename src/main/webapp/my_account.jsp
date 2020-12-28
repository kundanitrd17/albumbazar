<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="./css/my_account.css">
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
                            <h1 class="h2">Profile Information</h1>

                            <div>
                                <img src="./img/slide1.svg" alt=""
                                    style="height: 80px; width: 80px; border-radius: 50%;">
                            </div>
                        </div>
                        <!-- End of Header band -->



                        <!-- Content -->
                        <div class="content mb-3">
                            <div class=" editable-profile-card d-flex justify-content-center flex-column mb-3"
                                style="padding-bottom: 4rem; align-items: center;">

                                <!-- Mobile number  -->
                                <div class=" mobile-verified-card my-3 py-3" style="width: 100%;">
                                    <div class="d-flex justify-content-center flex-md-nowrap flex-row">
                                        <div class="my-1">
                                            <span>${customer.contactNo}</span>
                                            <i class=" fa fa-check fa-fw " aria-hidden="true"></i>
                                        </div>
                                        <div>
                                            <a class="mx-3 px-3 btn-light btn">
                                                Change
                                            </a>
                                        </div>
                                    </div>

                                </div>
                                <!-- End of mobile number -->


                                <!-- Rest of the info -->

                                <form id="customerDetailUpdateForm"
                                    class=" profile-info-editable-form w-100 mb-3 pb-3 border-bottom">
                                    <input type="text" value="${customer.id}" id="CustomerId" hidden>
                                    <div class="form-group">
                                        <label for="CustomerName">Name</label>
                                        <input value="${customer.name}" type="name" class="form-control"
                                            id="CustomerName" placeholder="Name">
                                    </div>
                                    <div class="form-group">
                                        <label for="CustomerEmail">Email address</label>
                                        <input value="${customer.email}" type="email" class="form-control"
                                            id="CustomerEmail" aria-describedby="emailHelp" placeholder="Enter email">
                                    </div>



                                    <div class="form-group">
                                        <label for="CustomerDOB">DOB</label>
                                        <input value="2013-01-08" type="date" class="form-control" id="CustomerDOB"
                                            placeholder="Date-of-birth">
                                    </div>

                                    <div class="form-group">
                                        <label for="CustomerAlternateContact">Alternate Mobile</label>
                                        <input value="808876897" type="text" class="form-control"
                                            id="CustomerAlternateContact" placeholder="Alternate Mobile">
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input gender-checkbox-input" type="radio"
                                            name="inlineRadioOptions" id="inlineRadio1" value="option1">
                                        <label class="form-check-label" for="inlineRadio1">Male</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input gender-checkbox-input" type="radio"
                                            name="inlineRadioOptions" id="inlineRadio2" value="option2">
                                        <label class="form-check-label" for="inlineRadio2">Female</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input gender-checkbox-input" type="radio"
                                            name="inlineRadioOptions" id="inlineRadio3" value="option3">
                                        <label class="form-check-label" for="inlineRadio3">Other</label>
                                    </div>

                                    <div class=" py-3 my-3" style="text-align: center;">
                                        <button type="submit" class="btn btn-danger w-75">Save
                                            Details</button>
                                    </div>
                                </form>


                                <div class="w-100">
                                    <div class="my-3" style="text-align: center;">
                                        <button class="btn btn-light w-75"
                                            style="background-color: antiquewhite;">Change
                                            Password</button>
                                    </div>
                                </div>

                                <!-- End Of rest info -->

                            </div>
                        </div>
                        <!-- End of COntent -->
                    </main>
            </div>
        </div>

        <script src="./js/customer_account.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>