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
        <title>Price List</title>

        <!-- CSS only -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- <link rel="stylesheet" href="/css/my_account.css"> -->
        <!-- <link rel="stylesheet" href="/css/navbar.css"> -->

        <link rel="stylesheet" href="/css/loading.css">

        <style>
            .image-cover {
                position: relative;
                transition: transform .2s;
                margin: 0;
            }

            .image-cover:hover {
                z-index: 2;
                transform: scale(1.2);
            }
        </style>

    </head>

    <body>


        <div class="loading" id="Loading">Loading&#8230;</div>
        <!-- Navbar Section -->



        <!-- End of Navbar section -->

        <div class="container">



            <div class="hero col-md-9 ml-sm-auto col-lg-10 pt-3">
                <!-- Header band -->
                <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                    <h1 class="h2">Cover</h1>
                </div>
                <!-- End of Header band -->

                <c:forEach items="${covers}" var="cover">


                    <div class="card" style="max-width: 97%; margin: 1rem; padding: 5px;">
                        <ul class="nav nav-pills nav-fill">
                            <div style="margin: auto; padding: 0.5rem;">
                                <div class="nav-item">
                                    <img class="image-cover" src="${cover.image}" alt="Cover image ">
                                </div>
                            </div>
                            <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                <li class="nav-item">
                                    ${cover.coverName}
                                </li>

                            </div>

                            <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                <li class="nav-item" style="color: red;">
                                    <span>Size: </span> <strong>${cover.coverSize}</strong>
                                </li>
                            </div>

                            <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                <li class="nav-item">
                                    <span>Price: </span> <strong>${cover.coverPrice}</strong>
                                </li>
                            </div>
                            <div class="flex-sm-column" style="padding: 20px; margin: auto;">

                                <li class="nav-item">
                                <li class="nav-item">
                                    <span>GST: </span> <strong>${cover.GST}</strong>
                                </li>
                                </li>

                            </div>
                        </ul>
                    </div>

                </c:forEach>

            </div>
        </div>


        <div class="container">



            <div class="hero col-md-9 ml-sm-auto col-lg-10 pt-3">
                <!-- Header band -->
                <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                    <h1 class="h2">Paper</h1>
                </div>


                <c:forEach items="${papers}" var="paper">


                    <div class="card" style="max-width: 97%; margin: 1rem; padding: 5px;">
                        <ul class="nav nav-pills nav-fill">
                            <div style="margin: auto; padding: 0.5rem;">
                                <div class="nav-item">
                                    <img src="https://www.transparenttextures.com/patterns/lyonnette.png"
                                        alt="Cover image ">
                                </div>
                            </div>
                            <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                <li class="nav-item">
                                    ${paper.paperQuality}
                                </li>

                            </div>

                            <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                <li class="nav-item" style="color: red;">
                                    <span>Size: </span> <strong>${paper.paperSize}</strong>
                                </li>
                            </div>

                            <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                <li class="nav-item">
                                    <span>Price: </span> <strong>${paper.paperPrice}</strong>
                                </li>
                            </div>
                            <div class="flex-sm-column" style="padding: 20px; margin: auto;">

                                <li class="nav-item">
                                <li class="nav-item">
                                    <span>GST: </span> <strong>${paper.GST}</strong>
                                </li>
                                </li>

                            </div>
                        </ul>
                    </div>

                </c:forEach>

            </div>
        </div>




        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


        <script>
            function onReady(callback) {
                var intervalId = window.setInterval(function () {
                    if (document.getElementsByTagName('body')[0] !== undefined) {
                        window.clearInterval(intervalId);
                        callback.call(this);
                    }
                }, 500);
            }

            function setVisible(selector, visible) {
                document.querySelector(selector).style.display = visible ? 'block' : 'none';
            }

            onReady(function () {
                // setVisible('body', true);
                setVisible('#Loading', false);
            });
        </script>

    </body>

    </html>