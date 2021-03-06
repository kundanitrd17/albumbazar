<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <!-- <sec:authorize var="loggedIn" access="isAuthenticated()" /> -->
    <sec:authorize access="hasAnyAuthority('ROLE_USER')" var="loggedIn">
    </sec:authorize>
    <!-- Navbar Section -->
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark" style="position: relative;">

        <!-- Image and text -->
        <a class="navbar-brand" href="/">
            <img src="/img/logo2.png" width="120" height="60" class="d-inline-block align-top" alt="" style="position:absolute;
                top:0;
                left:0;                                
                ">
        </a>



        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <!-- <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a> -->
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item mx-2">
                    <a class="nav-link" href="#">
                        <strong>
                            <c:if test="${customer != null &&  customer.name != null}">
                                Hi ${customer.name}!
                            </c:if>
                            <c:if test="${$customer == null &&  customer.name == null}">
                                Hi User!
                            </c:if>

                        </strong>
                    </a>
                </li>
                <li class="nav-item mx-2">
                    <a class="nav-link" href="/" style="color: aliceblue;">Home</a>
                </li>
                <li class="nav-item mx-2">
                    <a class="nav-link" href="/contact_us" style="color: aliceblue;">Contact Us</a>
                </li>

                <c:choose>
                    <c:when test="${loggedIn}">
                        <li class="nav-item dropdown mx-2">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                style="color: aliceblue;">
                                Account
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/customer/my-account">Profile</a>
                                <a class="dropdown-item" href="/customer/my-order">Orders</a>
                                <a class="dropdown-item" href="#" data-toggle="modal"
                                    data-target="#walletModal">wallet</a>
                                <a class="dropdown-item" href="#" data-toggle="modal"
                                    data-target="#exampleModalCenter">Your
                                    Referral Code</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/customer/my-address">
                                    Manage Address</a>
                            </div>
                        </li>

                        <li class="nav-item mr-3 px-2">
                            <form action="/customer/logout" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <button class="btn btn-primary">Logout</button>
                            </form>

                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="nav-item mr-3 px-2">
                            <a class="btn btn-primary" href="#" data-toggle="modal"
                                data-target="#LoginCustomerModal">Sign
                                In</a>
                        </li>
                    </c:otherwise>
                </c:choose>


            </ul>

        </div>
    </nav>



    <!-- End of Navbar section -->

    <!-- Referall code -->


    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
        aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="text" value="${customer.referralCode}" id="customer_referral_code" hidden>
                    ${customer.referralCode}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="ButtonToCopyReferralCode">Click To
                        Copy</button>
                </div>
            </div>
        </div>
    </div>


    <!-- Wallet modal -->

    <div class="modal fade" id="walletModal" tabindex="-1" role="dialog" aria-labelledby="walletModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container">

                        <div class="form-group alert alert-primary">
                            <label>Your Referral Code</label>
                            <input type="text" class="form-control alert alert-primary" value="${customer.referralCode}"
                                disabled>
                        </div>

                        <div class="form-group alert alert-danger">
                            <label>Your Discount</label>
                            <input type="text" class="form-control alert alert-danger" value="${customer.discount}"
                                disabled>
                        </div>

                        <div class="form-group alert alert-warning">
                            <label>Your Balance</label>
                            <input type="text" class="form-control alert alert-warning" value="${customer.wallet}"
                                disabled>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- End of wallet modal -->

    <!-- login modal Section -->
    <%@include file="login-customer.jsp" %>
        <!-- End of login modal  section -->
        <!-- Register modal Section -->
        <%@include file="register-customer.jsp" %>
            <!-- End of Register modal  section -->