<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Profile</title>
        <meta name="_csrf" content="${_csrf.token}" />
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="/css/my_account.css">
        <link rel="stylesheet" href="/css/navbar.css">

        <link rel="stylesheet" href="/css/loading.css">

        <style type="text/css">
            .profile-pic {
                position: relative;
                display: inline-block;
                cursor: pointer;
            }

            .profile-pic:hover img {
                opacity: 0.5;

            }

            .profile-pic:hover .edit {
                display: block;

            }

            .profile-pic .edit i {
                transform: scale(1.8);
            }

            .edit {
                position: absolute;
                right: 0;
                top: 0;
                display: none;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                -ms-transform: translate(-50%, -50%);
                text-align: center;
            }

            .edit .icon {
                color: #000;
            }
        </style>
    </head>

    <body>

        <div class="loading" id="Loading" style="display: none;">Loading&#8230;</div>

        <!-- Navbar Section -->

        <%@include file="navbar.jsp" %>

            <!-- End of Navbar section -->
            <div class="container-fluid">
                <div class="row">
                    <%@include file="customer_account_base_template.jsp" %>

                        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

                            <!-- Header band -->

                            <input id="csrfInput" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                            <div
                                class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                                <h1 class="h2">Your Orders</h1>


                                <div class="profile-pic">

                                    <img src="./img/carasoul1.jpg" alt="Avatar" class="image"
                                        id="customerProfilePictureId"
                                        style="height: 80px; width: 80px; border-radius: 50%;">

                                    <div class="edit">
                                        <div class="icon" title="Edit Profile">
                                            <i class="fa fa-camera"></i>
                                        </div>
                                    </div>
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
                                                id="CustomerEmail" aria-describedby="emailHelp"
                                                placeholder="Enter email">
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
                                            <a href="/customer/change-password" class="btn btn-light w-75"
                                                style="background-color: antiquewhite;">Change
                                                Password</a>
                                        </div>
                                    </div>

                                    <!-- End Of rest info -->

                                </div>
                            </div>
                            <!-- End of COntent -->
                        </main>
                </div>
            </div>

            <%@include file="footer.jsp" %>


                <script>


                    document.querySelector('.profile-pic').addEventListener('click', openFileDialog);

                    function openFileDialog(accept) {  // this function must be called from  a user
                        // activation event (ie an onclick event)

                        console.log(accept);
                        // Create an input element
                        var inputElement = document.createElement("input");
                        inputElement.type = "file";
                        // inputElement.hidden = true; 
                        // document.querySelector(".profile-pic").append(inputElement);
                        // Set accept to the file types you want the user to select. 
                        // Include both the file extension and the mime type
                        inputElement.accept = accept;

                        // set onchange event to call selectedFiles when user has selected file
                        inputElement.addEventListener("change", () => {
                            console.log("selected files to upload");
                            // Append the selected files and keep them hidden

                            console.log(inputElement);

                            const file = inputElement.files[0];

                            changeProfilePic(file);

                        });

                        // dispatch a click event to open the file dialog
                        inputElement.dispatchEvent(new MouseEvent("click"));
                    }

                    function changeProfilePic(photo) {

                        //if the file isn't a image nothing happens.
                        //you are free to implement a fallback
                        if (!photo || !photo.type.match(/image.*/)) return;

                        console.log(photo);

                        const formData = new FormData();
                        formData.append("profile_photo", photo);

                        const xhr = new XMLHttpRequest();
                        const url = "/api/secured/customer/profile/image";

                        xhr.open('PUT', url, true);

                        const csrfEle = document.getElementById("csrfInput");
                        console.log(csrfEle.name, csrfEle.value);
                        formData.append(csrfEle.name, csrfEle.value);


                        xhr.onreadystatechange = function () {
                            if (this.readyState === 4 && this.status === 200) {
                                console.log(this.response);
                                document.getElementById('customerProfilePictureId').src = this.response;
                            }
                        }

                        xhr.send(formData);


                    }
                </script>


                <script src="/js/customer_account.js"></script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>

</html>