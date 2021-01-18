<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html>

    <head>
        <title></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style type="text/css">
            .login {
                margin-left: auto;
                margin: 12px auto;
                font-size: 16px;
            }

            /* Reset top and bottom margins from certain elements */
            .login-header,
            .login p {
                margin-top: 0;
                margin-bottom: 0;
            }

            /* The triangle form is achieved by a CSS hack */


            .login-header {
                background: #28d;
                padding: 14px 0px;
                font-size: 1.4em;
                font-weight: normal;
                text-align: center;
                text-transform: uppercase;
                color: #fff;
            }


            /* Every row inside .login-container is defined with p tags */
            .login p {
                padding: 12px;
            }

            .login input {
                box-sizing: border-box;
                display: block;
                width: 90%;
                border-width: 1px;
                border-style: solid;
                padding: 14px 0px 14px 10px;
                margin: 0px;
                outline: 0;
                font-family: inherit;
                font-size: 0.95em;
                margin-top: 10px;
            }

            .login input[type="email"],
            .login input[type="text"] {
                background: #fff;
                border-color: #bbb;
                color: #012F5B;
            }

            .section15 h2,
            .section15 h3 {
                color: #012F5B;
                font-weight: bold;
            }

            .section15 p {
                color: #A4A4A4;
                line-height: 25px;
                font-weight: bold;
            }

            /* Text fields' focus effect */
            .login input[type="email"]:focus,
            .login input[type="text"]:focus {
                border-color: #888;
            }

            .login input[type="submit"] {
                background: #FDE21C;
                border-color: #FDE21C;
                color: #012F5B;
                cursor: pointer;
                font-size: 15px;
                font-weight: bold;
            }

            .login input[type="submit"]:hover {
                background: white;
                -webkit-box-shadow: 0px 3px 15px -2px rgba(240, 240, 8, 1);
                -moz-box-shadow: 0px 3px 15px -2px rgba(240, 240, 8, 1);
                box-shadow: 0px 3px 15px -2px rgba(240, 240, 8, 1);
            }

            /* Buttons' focus effect */
            .login input[type="submit"]:focus {
                border-color: #05a;
            }
        </style>
    </head>

    <body>


        <!-- Navbar Section -->
        <%@include file="navbar.jsp" %>
            <!-- End of Navbar section -->


            <section>
                <div class="container-fluid" style="z-index: 2;">
                    <div class="row section15" style="margin-top: 100px;padding: 100px 0px;background-color: #F7F7F7; ">
                        <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12">

                            <div class="container">
                                <div class="row">
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6"
                                        style="padding: 30px 0px;">
                                        <h2>The best way to manage expenses. </h2>
                                        <h2>Speak to a finance consultant today.</h2>
                                        <hr style="border-top: 2px solid red;width:5%;">
                                        <p>Understanding your requirements and objectives is important to us. We listen
                                            and
                                            work together to
                                            create a truly unique and unforgettable experience.</p>
                                    </div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-2 col-lg-2">

                                    </div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                        <div class="login">

                                            <h3>Request a call back</h3>
                                            <form class="login-container">
                                                <input type="text" placeholder="Your Name">
                                                <input type="email" placeholder="Email">

                                                <input type="text" placeholder="Contact no.">
                                                <input type="submit" value="Submit">
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </section>



            <%@include file="footer.jsp" %>

    </body>

    </html>