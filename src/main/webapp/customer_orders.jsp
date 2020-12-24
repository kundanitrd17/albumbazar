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

        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>

    <body>

        <header style="background-color: beige; width: 100%; height: 15vh; text-align: center;">
            Upcoming Navbar
            <nav class="nav nav-pills nav-fill">

            </nav>
        </header>


        <div class="hero container-fluid">
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
                                ${eachOrder.productName}
                            </li>
                            <li class="nav-item">
                                <Strong>${eachOrder.associationName}</Strong>
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
                                <strong>Total: ${eachOrder.totalAmount}</strong>
                            </li>
                            <li class="nav-item">
                                <strike>Discount: ${eachOrder.discount}</strike>
                            </li>
                            <li class="nav-item">
                                <strong>Delivery: 9000</strong>
                            </li>
                            <li class="nav-item">
                                <strong>Tax: 9000</strong>
                            </li>
                        </div>

                        <div class="flex-sm-column" style="padding: 20px; margin: auto;">
                            <li class="nav-item">
                                <strong>${eachOrder.status}</strong>
                            </li>
                            <p></p>
                            <li class="nav-item">
                                OrderTime: ${eachOrder.orderCreationTime}
                            </li>
                            <li>
                                Excepted: ${eachOrder.deliveryDate}
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

                            <form class="nav-item" action="/customer/my-order/pay-or-upload" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <input type="text" name="orderId" value="${eachOrder.id}" hidden>
                                <c:if test="${!eachOrder.paymentStatus}">
                                    <button class="btn btn-danger" type="submit">Upload Photo / pay</button>
                                </c:if>
                                <c:if test="${eachOrder.paymentStatus}">
                                    <button class="btn btn-success" type="submit">Upload Photo / pay</button>
                                </c:if>


                            </form>

                        </div>
                    </ul>
                </div>

            </c:forEach>

        </div>





        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
    </body>

    </html>