<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Page Title - SB Admin</title>
    <link href="./css/login_logout.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
        crossorigin="anonymous"></script>
</head>

<body class="bg-primary">
    <div id="layoutAuthentication">
        <div id="layoutAuthentication_content">
            <main>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-5">
                            <div class="card shadow-lg border-0 rounded-lg mt-5">
                                <div class="card-header">
                                    <h3 class="text-center font-weight-light my-4">Password Recovery</h3>
                                </div>
                                <div class="card-body">
                                    <div class="small mb-3 text-muted">Enter your email address and we will send you an
                                        OTP to reset your password.</div>
                                    <form action="/forgot-password" method="POST">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        <div class="form-group">
                                            <label class="small mb-1" for="inputEmailAddress">Email</label>
                                            <input name="email" class="form-control py-4" id="inputEmailAddress"
                                                type="email" aria-describedby="emailHelp"
                                                placeholder="Enter email address" required />
                                        </div>
                                        <div class="form-group d-flex align-items-center justify-content-end mb-0">
                                            <button id="OTPSendButton" type="button" class="btn btn-primary">Send
                                                OTP</button>
                                        </div>

                                        <div class="form-group">
                                            <label class="small mb-1" for="inputOTP">OTP</label>
                                            <input name="OTP" class="form-control py-4" id="inputOTP" type="text"
                                                aria-describedby="OTPHelp" placeholder="Enter OTP" required />
                                        </div>

                                        <div class="form-group">
                                            <label class="small mb-1" for="password">Password</label>
                                            <input name="password" class="form-control py-4" id="password" type="text"
                                                aria-describedby="OTPHelp" placeholder="Password" required />
                                        </div>

                                        <div class="form-group">
                                            <label class="small mb-1" for="retype-password">Retype-password</label>
                                            <input name="rePassword" class="form-control py-4" id="retype-password"
                                                type="text" aria-describedby="OTPHelp" placeholder="Re-Type Password"
                                                required />
                                        </div>



                                        <div
                                            class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                            <a class="small" href="/">Return to login</a>
                                            <button type="submit" class="btn btn-primary">Reset Password</button>
                                        </div>

                                    </form>



                                </div>
                                <div class="card-footer text-center">
                                    <div class="small"><a href="/">Need an account? Sign up!</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <div id="layoutAuthentication_footer">
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; Your Website 2020</div>
                        <div>
                            <a href="#">Privacy Policy</a>
                            &middot;
                            <a href="#">Terms &amp; Conditions</a>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>

    <script>

        document.addEventListener('DOMContentLoaded', function () {

            document.getElementById('OTPSendButton').addEventListener('click', () => {
                const inputEmail = document.getElementById('inputEmailAddress').value;
                // Valid email value
                if (inputEmail == null || inputEmail.length < 0) {
                    alert("Enter Email valid address!");
                    return false;
                }
                let flag = false;
                for (let index = 0; index < inputEmail.length; index++) {
                    if (inputEmail[index] === '@') {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    alert("Enter Email valid address!");
                    return false;
                }

                // Send OTP
                console.log(inputEmail);


                // After a success response
                document.querySelector('main').innerHTML = `<div class="alert alert-danger" role="alert">OTP Sent</div>` + document.querySelector('body').innerHTML;

            });

        });

    </script>

</body>

</html>