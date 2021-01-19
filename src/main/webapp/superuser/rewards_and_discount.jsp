<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="/superuser/css/super-admin.css">


    </head>

    <body>

        <div class="container-fluid">
            <div class="row">

                <%@include file="sidebar.jsp" %>

                    <div class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                        <div class="card-deck my-3 py-3 mx-3 ">

                            <div class="card text-center">
                                <h5 class="card-header">Set Discount For All</h5>
                                <!-- <div > -->
                                <form class="card-body" action="/superuser/reward/discount/global" method="POST">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">$</span>
                                            <span class="input-group-text">0.00</span>
                                        </div>
                                        <input name="amount" type="text" class="form-control"
                                            aria-label="Amount (to the nearest dollar)">
                                    </div>
                                    <button class="btn btn-primary" type="submit">Set Discount</button>
                                </form>
                                <!-- </div> -->

                            </div>
                            <form class="card text-center" action="/superuser/reward/discount/customer" method="POST">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <h5 class="card-header">Set Discount for customer</h5>
                                <div class="card-body">
                                    <div class="input-group mb-3">
                                        <select name="customerId" class="custom-select" id="inputGroupSelect01">
                                            <option selected>Choose...</option>
                                            <c:forEach items="${customers}" var="customer">
                                                <option value="${customer.id}">
                                                    ${customer.id} ${customer.name}
                                                </option>
                                            </c:forEach>

                                        </select>
                                    </div>

                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">$</span>
                                            <span class="input-group-text">0.00</span>
                                        </div>
                                        <input name="amount" type="text" class="form-control"
                                            aria-label="Amount (to the nearest dollar)">
                                    </div>
                                    <button class="btn btn-primary" type="submit">Set Discount</button>

                                </div>

                            </form>
                        </div>

                        <div class="card-deck my-3 py-3 mx-3">

                            <div class="card text-center mx-3" style="max-width: 50%;">
                                <h5 class="card-header">Set Referral award</h5>
                                <form class="card-body" action="/superuser/reward/referral" method="POST">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">$</span>
                                            <span class="input-group-text">0.00</span>
                                        </div>
                                        <input name="amount" type="text" class="form-control"
                                            aria-label="Amount (to the nearest dollar)">
                                    </div>
                                    <button class="btn btn-primary" type="submit">Set Reward</button>
                                </form>

                            </div>

                        </div>
                    </div>
            </div>
        </div>

        <script src='/superuser/js/super-admin.js'></script>
    </body>

    </html>