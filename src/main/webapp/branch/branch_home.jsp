<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
<!DOCTYPE html>

<html>

<head>
    <title>Album Bazaar</title>

    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">

    <meta charset="utf-8">
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- <link rel="stylesheet" href="/superuser/css/super-admin.css"> -->
    <link rel="stylesheet" href="../css/index.css">
</head>

<body>


    <!-- Navbar Section -->
    <%@include file="navbar.jsp" %>
        <!-- End of Navbar section -->

        <div>

            <section id="OrderListSection" style="padding-top: 20px; margin-top: 20px;">
            </section>


            <section style="margin-bottom: 5rem; padding-top: 5px; padding-bottom: 50px;">
                <div class="container">
                    <div class="row">
                        <c:forEach items="${associations}" var="association">
                            <div class="col-md-4 col-lg-4 col-xl-4">
                                <div class="fancy-cards">

                                    <div class="fancy-card">
                                        <div class="top" id="t">
                                            <div class="caption">
                                                <h3 class="title">${association.name}</h3>
                                                <input type="text" name="associationId" value="${association.id}"
                                                    hidden>

                                                <button type="button" id=""
                                                    class="btn btn-primary button associationSelection"
                                                    data-toggle="modal" data-target="#myModal">
                                                    Select
                                                </button>
                                                <a type="button" id="associationViewPrice"
                                                    class="btn btn-primary button1 associationViewPrice"
                                                    data-toggle="modal" data-target="#largeModal">
                                                    View Price
                                                </a>
                                            </div>
                                        </div>
                                        <div class="middle" id="m"></div>
                                        <div class="bottom" id="b"></div>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </div>
            </section>
        </div>

       
            <!-- The Modal -->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Company Name</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">

                            <form class="" id="CreateOrderForm">
                                <input type="text" name="selectedAssociationId" id="selectedAssociationId" hidden>
                                <h4>Select Album</h4>
                                <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->

                                <div class="container">
                                    <div class="row">

                                        <input class="form-control col-md-4 col-xl-4 col-lg-4" type="email"
                                            name="customerIdentifier" placeholder="customer Email" required>

                                        <select name="productCategory" id="albumType"
                                            class="form-control col-md-4 col-xl-4 col-lg-4">
                                        </select>

                                        <select id="albumSize" name="productSize"
                                            class="form-control col-md-4 col-xl-4 col-lg-4">
                                        </select>
                                    </div>
                                    <hr>
                                    <h4>Cover Page</h4>

                                    <div class="form-group row">
                                        <div class="col-lg-4 col-xl-4 col-md-4 col-sm-12 col-xs-12 col-12">
                                            <label>cover</label>
                                            <select name="coverId" id="coverPageQuality" class="form-control">
                                                <option value="gold">gold</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-2 col-xl-2 col-md-4 col-sm-4 col-xs-4 col-4">
                                            <label>price</label>
                                            <input class="form-control" type="text" id="coverPrice" name="coverPrice"
                                                value="" disabled>
                                        </div>
                                        <div class="col-lg-2 col-xl-2 col-md-4 col-sm-4 col-xs-4 col-4">
                                            <label>Tax</label>
                                            <input class="form-control" type="text" id="coverGST" name="coverGST"
                                                value="" disabled>
                                        </div>
                                        <div class="col-lg-2 col-xl-2 col-md-4 col-sm-4 col-xs-4 col-4">
                                            <label>Total</label>
                                            <input class="form-control" type="text" id="coverTotal" name="coverTotal"
                                                value="" disabled>
                                        </div>
                                    </div>

                                    <!-- <div class="row">
                                        <select name="coverId" id="coverPageQuality"
                                            class="form-control col-md-4 col-xl-4 col-lg-4">
                                        </select>

                                        <input class="form-control col-md-4 col-xl-4 col-lg-4" type="text"
                                            id="coverPrice" name="coverPrice" value="" disabled>
                                    </div> -->

                                    <hr>
                                    <h4>Sheet Detail</h4>
                                    <div class="row table-responsive">
                                        <table>
                                            <tr>
                                                <th>
                                                    <h6>Type</h6>
                                                </th>

                                                <th>
                                                    <h6> sheet</h6>
                                                </th>
                                                <th>
                                                    <h6>Price</h6>
                                                </th>

                                                <th>
                                                    <h6>Tax</h6>
                                                </th>

                                            </tr>

                                            <tbody id="test-body">

                                            </tbody>
                                            <tfoot>

                                                <tr>
                                                    <td colspan="4">
                                                        <textarea name="description" cols="60" rows="5"
                                                            placeholder="description"></textarea>
                                                    </td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>

                                </div>


                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <span>Total Without Gst :</span><span id="orderTotalWithoutGST"></span>
                            <span>Total Gst :</span><span id="orderTotalTax"></span>
                            <span>Total With Gst :</span><span id="orderTotalWithGST"></span>

                            <button type="submit" class="btn btn-success">submit</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

                        </div>
                        </form>
                    </div>
                </div>
            </div>

        <!-- View Price List Of Association-->
        <div class="modal fade" id="largeModal" tabindex="-1" role="dialog" aria-labelledby="basicModal"
            aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel"></h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body table-responsive">
                        <input type="text" name="association_id" id="selected_association_id" hidden>
                        <table class="table table-bordered" id="coverDetails">
                            <thead>
                                <tr>
                                    <th colspan="4" class="bg-dark">Cover Price List</th>
                                </tr>
                                <tr style="background-color: none;color:black;">
                                    <th>Quality</th>
                                    <th>Size</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>John</td>
                                    <td>Doe</td>
                                    <td>john@example.com</td>
                                    <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;"
                                            class="fa fa-trash"></i>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-body table-responsive">
                        <table class="table table-bordered" id="paperDetails">
                            <thead>
                                <tr>
                                    <th class="bg-dark" colspan="4">Paper Price List</th>
                                </tr>
                                <tr style="background-color: none;color:black;">
                                    <th>Quality</th>
                                    <th>Size</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>John</td>
                                    <td>Doe</td>
                                    <td>john@example.com</td>
                                    <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;"
                                            class="fa fa-trash"></i>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>

                    </div>
                </div>
            </div>
        </div>


        <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->


        <script type="text/javascript">

            document.addEventListener('DOMContentLoaded', function () {
                document.querySelector(".fancy-card #t").style.background = "url('https://static-1.gumroad.com/res/gumroad/assets/collections/food_and_cooking_thumb-34fb9ef316a7b01177529839891c3a03.jpg')";
            });

        </script>

        <script src="branch/js/create_order.js"></script>
        <script src="branch/js/create_order_setup.js"></script>

        <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script> -->

</body>

</html>