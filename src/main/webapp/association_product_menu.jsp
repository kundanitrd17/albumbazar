<style>
    #sidebar_navigation {
        height: 90vh;
        padding-top: 2rem;
    }

    .sidebar-sticky .nav .nav-item {
        margin-top: 1rem;
    }


    .sidebar-sticky .nav .nav-item .nav-link {
        color: black;
    }

    .card .address-card-info {
        margin: 20px;
        padding: 20px;
    }

    .card {
        box-shadow: 0 6px 10px -6px black;
    }

    .card:hover {
        box-shadow: 0 10px 10px -6px black;
    }

    .sidebar-sticky ul li .nav-link {
        transition: all .2s ease-in-out;
    }

    .sidebar-sticky ul li .nav-link:hover {
        overflow: hidden;
        background-color: lightblue;
        transform: scale(1.1);

    }
</style>

<!-- View Price List Of Association-->
<div class="modal fade" id="largeModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

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
                                lasdjsakdj
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
                                <strong>Total: ${eachOrder.orderBill.totalAmount}</strong>
                            </li>
                            <li class="nav-item">
                                <strike>Discount: ${eachOrder.orderBill.discount}</strike>
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
                                <strong>${eachOrder.orderStatus}</strong>
                            </li>
                            <p></p>
                            <li class="nav-item">
                                OrderTime: ${eachOrder.orderTime}
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


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>

            </div>
        </div>
    </div>
</div>