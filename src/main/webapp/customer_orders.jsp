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
        <title>My Orders</title>

        <!-- CSS only -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="/css/my_account.css">
        <link rel="stylesheet" href="/css/navbar.css">

        <link rel="stylesheet" href="/css/loading.css">
    </head>

    <body>


        <div class="loading" id="Loading">Loading&#8230;</div>
        <!-- Navbar Section -->

        <%@include file="navbar.jsp" %>

            <!-- End of Navbar section -->

            <div class="container-fluid">
                <div class="row">

                    <%@include file="customer_account_base_template.jsp" %>



                        <div class="hero col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                            <!-- Header band -->
                            <div
                                class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                                <h1 class="h2">Your Orders</h1>

                                <div>
                                    <img src="/img/slide1.svg" alt=""
                                        style="height: 80px; width: 80px; border-radius: 50%;">
                                </div>

                            </div>
                            <!-- End of Header band -->


                            <div
                                class="d-flex justify-content-end flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">

                                <c:if test="${allOrdersForCustomer != null && allOrdersForCustomer.size() > 0}">
                                    <div>
                                        <a href="/customer/my-order?page=${currentPage-1}"
                                            class="btn btn-outline-secondary">Prev</a>
                                        <a href="/customer/my-order?page=${currentPage+1}"
                                            class="btn btn-outline-secondary">Next</a>
                                    </div>
                                </c:if>

                                <c:if test="${allOrdersForCustomer == null || allOrdersForCustomer.size() == 0}">
                                    <div>
                                        <a href="/customer/my-order?page=${currentPage-1}"
                                            class="btn btn-outline-secondary">Prev</a>
                                    </div>
                                </c:if>

                            </div>

                            <c:if
                                test="${allOrdersForCustomer == null || allOrdersForCustomer.size() == 0 || error != null}">
                                <div class="alert alert-danger" role="alert">

                                    ${error}

                                    <c:if test="${error == null}">
                                        No Orders Found
                                    </c:if>

                                </div>
                            </c:if>

                            <c:forEach items="${allOrdersForCustomer}" var="eachOrder">



                                <div class="card" style="max-width: 97%; margin: 1rem; padding: 5px;">
                                    <ul class="nav nav-pills nav-fill">
                                        <div style="margin: auto; padding: 0.5rem;">
                                            <div class="nav-item">
                                                <img src="https://www.transparenttextures.com/patterns/lyonnette.png"
                                                    alt="Card image cap">
                                            </div>
                                        </div>
                                        <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                            <li class="nav-item">
                                                <span style="margin-right: 5px; font-weight: bold;">OrderId:</span>
                                                ${eachOrder.uuid}
                                            </li>
                                            <li class="nav-item">
                                                Product: ${eachOrder.productName}
                                            </li>
                                            <li class="nav-item">
                                                Association: <Strong>${eachOrder.associationName}</Strong>
                                            </li>
                                            <li class="nav-item">
                                                ${eachOrder.coverName}
                                            </li>
                                            <li class="nav-item">
                                                <small style="font-size: smaller;">${eachOrder.productSize}</small>
                                            </li>
                                            <li class="nav-item">
                                                <small style="font-size: small;">${eachOrder.orientation}</small>
                                            </li>
                                        </div>

                                        <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                            <li class="nav-item" style="color: red;">
                                                <strong>ToPay: ${eachOrder.orderBill.amountToPay}</strong>
                                            </li>
                                            <li class="nav-item">
                                                <strike>Discount: ${eachOrder.orderBill.discount}</strike>
                                            </li>
                                            <li class="nav-item">
                                                <strike>Wallet used: ${eachOrder.orderBill.wallet}</strike>
                                            </li>
                                            <li class="nav-item">
                                                <strong>Total: ${eachOrder.orderBill.totalAmount}</strong>
                                            </li>
                                            <!-- <li class="nav-item">
                                                <strong>Delivery: </strong>
                                            </li> -->

                                        </div>

                                        <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                                            <li class="nav-item">
                                                <strong>${eachOrder.orderStatus}</strong>
                                            </li>
                                            <p></p>
                                            <li class="nav-item">
                                                OrderTime: ${eachOrder.orderTime}
                                            </li>
                                            <li>
                                                Delivery: ${eachOrder.deliveryDate}
                                            </li>

                                        </div>
                                        <div class="flex-sm-column" style="padding: 20px; margin: auto;">

                                            <li class="nav-item">
                                                <c:if test="${!eachOrder.paymentStatus}">
                                                    <a class="nav-link" style="color: rgb(202, 35, 35);">
                                                        Un-Paid <i class="fa fa-credit-card" aria-hidden="true"></i>
                                                    </a>
                                                </c:if>
                                                <c:if test="${eachOrder.paymentStatus}">
                                                    <a class="nav-link" style="color: rgb(30, 186, 30);">
                                                        Paid <i class="fa fa-check" aria-hidden="true"></i>
                                                    </a>
                                                </c:if>

                                            </li>

                                            <form class="nav-item" action="/customer/my-order/pay-or-upload"
                                                method="post">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <input type="text" name="orderId" value="${eachOrder.id}" hidden>
                                                <c:if test="${!eachOrder.paymentStatus}">
                                                    <button class="btn btn-danger" type="submit">Upload Photo /
                                                        pay</button>
                                                </c:if>
                                                <c:if test="${eachOrder.paymentStatus}">
                                                    <button class="btn btn-success" type="submit">Upload Photo /
                                                        pay</button>
                                                </c:if>


                                            </form>

                                        </div>
                                    </ul>
                                </div>

                            </c:forEach>

                        </div>
                </div>
            </div>


            <%@include file="footer.jsp" %>




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