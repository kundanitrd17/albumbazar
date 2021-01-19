<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">


    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="_csrf" content="${_csrf.token}" />
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        <!-- ... -->
        <title>Document</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <link rel="stylesheet" href="/css/upload_file.css">
        <!-- <link rel="stylesheet" href="./css/upload_file.css"> -->




    </head>

    <body>
        <!-- Navbar Section -->
        <%@include file="navbar.jsp" %>
            <!-- End of Navbar section -->

            <!-- Hero contains all the code -->
            <div class="hero py-3" style="min-height: 80vh;">

                <div id="AuthenticateGoogle" class=" my-3 py-3"
                    style="display: flex; justify-content: center; margin-top: 100px; ">
                    <div id="signInToGoogle" class="my-3 py-3 wrapper" style="text-align: center;">
                        <a href="#" class="btn btn-danger">Sign-In to Google</a>
                    </div>
                </div>


                <!-- === File Upload ===
Design a file upload element. Is it the loading screen and icon? A progress element? Are folders being uploaded by flying across the screen like Ghostbusters? ;)  
-->

                <!-- file upload model -->
                <div id="FileUpload" ondrop="dropHandler(event);" ondragover="dragOverHandler(event);" hidden>

                    <div class="wrapper">

                        <div class="upload">
                            <p>Drag files here or <span class="upload__button">Browse</span></p>
                        </div>

                        <div class="upload_to_server " style="text-align: center;">
                            <button id="upload_to_server_btn" class="btn btn-success">Upload</button>
                        </div>

                        <!-- selected files -->

                        <form id="selectedFilesForUpload" enctype="multipart/form-data" hidden>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <input type="text" name="order_id" id="current_order_id" value="${order_id}">
                            <input id="files_to_upload_input_element" type="file" name="files" multiple>

                        </form>
                        <!-- End of selected files -->

                        <!-- Sample files demo -->
                        <div class="uploaded uploaded--one">
                            <i class="far fa-file-pdf"></i>
                            <div class="file">
                                <div class="file__name">
                                    <p>lorem_ipsum.pdf</p>
                                    <i class="fas fa-times"></i>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar bg-success progress-bar-striped progress-bar-animated"
                                        style="width:100%"></div>
                                </div>
                            </div>
                        </div>
                        <div class="uploaded uploaded--two">
                            <i class="far fa-file-pdf"></i>
                            <div class="file">
                                <div class="file__name">
                                    <p>dolor_sit.pdf</p>
                                    <i class="fas fa-times"></i>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar bg-success progress-bar-striped progress-bar-animated"
                                        style="width:80%"></div>
                                </div>
                            </div>
                        </div>
                        <div class="uploaded uploaded--three">
                            <i class="far fa-file-pdf"></i>
                            <div class="file">
                                <div class="file__name">
                                    <p>amet_consectetur.pdf</p>
                                    <i class="fas fa-times"></i>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar bg-success progress-bar-striped progress-bar-animated"
                                        style="width:60%"></div>
                                </div>
                            </div>
                        </div>
                        <!-- End of sample -->
                    </div>
                </div>

                <!-- End of File upload -->

                <!-- Payment section -->

                <div class="" style="display: flex; justify-content: center;" id="PaymentSectionDiv">

                    <div class="wrapper">

                        <div class="payment">
                            <p> <span class="generate-bill">Generate bill</span></p>
                        </div>

                        <!-- Razor pay pay btn -->
                        <div class="razorpay-payment-box-container" style="text-align: center;" hidden>

                            <div class="order_payment_info">


                            </div>

                            <button id="rzp-button" class="btn btn-danger w-25">pay</button>
                            <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->
                            <!-- <script src="https://checkout.razorpay.com/v1/checkout.js" data-key="rzp_test_oO5Nlz03qtxInq"
                            data-amount="${amount}" data-currency="INR" data-order_id="${order_id}"
                            data-buttontext="Pay with Razorpay" data-name="Acme Corp"
                            data-description="A Wild Sheep Chase is the third novel by Japanese author Haruki Murakami"
                            data-image="https://example.com/your_logo.jpg" data-prefill.name="Ranjit Kumar"
                            data-prefill.email="harshTiwari@example.com" data-theme.color="#F37254"></script><input
                            type="hidden" custom="Hidden Element" name="hidden"> -->

                        </div>
                    </div>


                </div>

                <!-- End of payment section -->

            </div>



            <!-- Footer section -->
            <%@include file="footer.jsp" %>


                <script src="https://checkout.razorpay.com/v1/checkout.js"></script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


                <script src="/js/upload_file.js"></script>






    </body>

    </html>