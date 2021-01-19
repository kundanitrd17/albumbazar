<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <head>

    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">


    <link rel="stylesheet" href="/superuser/css/super-admin.css">
    <!-- <link rel="stylesheet" type="text/css" href="/superuser/css/form.css" /> -->

  </head>

  <body>
    <%@include file="sidebar.jsp" %>


      <section id="contents">
        <nav class="navbar navbar-default">
          <div class="container-fluid" style="background-color: seagreen;">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <i class="fa fa-align-right"></i>
              </button>
              <a class="navbar-brand" href="#">my<span class="main-color">Dashboard</span></a>
            </div>
            <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                    aria-expanded="false">My profile <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#"><i class="fa fa-user-o fw"></i> My account</a></li>
                    <li><a href="#"><i class="fa fa-envelope-o fw"></i> My inbox</a></li>
                    <li><a href="#"><i class="fa fa-question-circle-o fw"></i> Help</a></li>
                    <li role="separator" class="divider"></li>
                    <li>

                      <form action="/superuser/logout-super" method="POST">
                        <i class="fa fa-sign-out"></i>
                        <input type="submit" value="logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      </form>
                    </li>
                  </ul>
                </li>
                <li><a href="#"><i class="fa fa-comments"></i><span>23</span></a></li>
                <li><a href="#"><i class="fa fa-bell-o"></i><span>98</span></a></li>
                <li><a href="#"><i data-show="show-side-navigation1" class="fa fa-bars show-side-btn"></i></a></li>
              </ul>
            </div>
          </div>
        </nav>
        <div class="welcome">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-12">
                <div class="content">
                  <h2>All Orders</h2>
                  ${data}
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor.</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Table -->
        <div class="container">
          <div class="row">
            <div class="panel panel-primary filterable table-responsive">
              <div class="panel-heading">
                <h3 class="panel-title">Customers</h3>
                <div class="pull-right" style="position: relative;top: -20px;"><button
                    class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                    Filter</button></div>
              </div>
              <table class="table " style="font-size: 12px;">
                <thead>
                  <tr class="filters">
                    <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                    <th><input type="text" class="form-control" placeholder="CustomerId" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Product" disabled></th>
                    <th><input type="text" class="form-control" placeholder="OrderTime" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Description" disabled></th>
                    <th><input type="text" class="form-control" placeholder=" Address" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Orientation" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Product View" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Images" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Status" disabled></th>
                  </tr>
                </thead>
                <tbody>

                  <c:forEach items="${order_details}" var="order_detail">
                    <tr>
                      <td>${order_detail.id}</td>
                      <td class="customerId" data-order-id="${order_detail.customer.id}">
                        <button class="btn" onclick="fetchCustomerDetails('${order_detail.customer.id}')"
                          data-toggle="modal" data-target="#customerDetailModal">${order_detail.customer.id}</button>
                      </td>
                      <td>${order_detail.productName}</td>
                      <td>${order_detail.orderTime}</td>
                      <td>
                        <a href="" data-toggle="modal" data-target="#orderDescriptionDialog${order_detail.id}">
                          click
                        </a>
                        <div class="modal" tabindex="-1" role="dialog" id="orderDescriptionDialog${order_detail.id}">
                          <div class="modal-dialog" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title">Order Description</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                              </div>
                              <div class="modal-body">
                                <div class="form-group">
                                  <label>Order Description</label>
                                  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"
                                    disabled>${order_detail.description}</textarea>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </td>
                      <td><a href="" data-toggle="modal" data-target="#addressModal" id="link_address"
                          onclick="fetchDeliveryAddressFromServer('${order_detail.id}')">Address
                          Id</a></td>
                      <td>${order_detail.orientation}</td>
                      <td><a type="button" href="" id="link_adminId" data-toggle="modal"
                          data-target="#orderProductViewDetails" onclick="orderProductView('${order_detail.id}')">View
                          Product</a></td>
                      <td><a target="_blank" href="${order_detail.photoFolderGoogleDriveLink}">Images</a></td>
                      <td><button class="btn btn-success">${order_detail.orderStatus}</button></td>

                    </tr>
                  </c:forEach>


                </tbody>
              </table>
            </div>
          </div>
        </div>



        <div class="modal" id="orderProductViewDetails">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Company Name</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>


              <div class="modal-body table-responsive">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th colspan="5">Cover Info</th>
                    </tr>
                    <tr style="background-color: none;color:black;">
                      <th>Product Name</th>
                      <th>Product Size</th>
                      <th>CoverName</th>
                      <th>Cover Size</th>

                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td id="productName">album</td>
                      <td id="productSize">90*09</td>
                      <td id="coverName">John</td>
                      <td id="coverSize">Doe</td>
                    </tr>
                  </tbody>
                </table>
              </div>


              <div class="modal-body table-responsive">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th colspan="5">Paper Info</th>
                    </tr>
                    <tr style="background-color: none;color:black;">
                      <th>Quality</th>
                      <th>Size</th>
                      <th>No. of Sheets</th>
                    </tr>
                  </thead>
                  <tbody id="paperListRow">
                    <tr>
                      <td id="pageQuality">John</td>
                      <td id="pageSize">Doe</td>
                      <td id="numberOfSheets">200</td>
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


        <!-- Customer detail modal -->
        <div class="modal" id="customerDetailModal">
          <div class="modal-dialog">
            <div class="modal-content">

              <!-- Modal Header -->
              <div class="modal-header">
                <h4 class="modal-title">Customer</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
              </div>

              <!-- Modal body -->
              <div class="modal-body">
                <table class="table table-borderless">
                  <tbody>
                    <tr>
                      <th>Name: </th>
                      <td id="name"></td>
                    </tr>
                    <tr>
                      <th>Contact: </th>
                      <td id="contact"></td>
                    </tr>
                    <tr>
                      <th>Email: </th>
                      <td id="email"></td>
                    </tr>
                  </tbody>
                </table>
              </div>

              <!-- Modal footer -->
              <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
              </div>

            </div>
          </div>
        </div>

        <!-- End of customer Detail modal -->





        <!-- Address Modal -->
        <div class="modal" id="addressModal" tabindex="-1" role="dialog">
          <form action="#">

            <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->

            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <!-- Modal Header -->

                <div class="modal-header">
                  <h5 class="modal-title">Address</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <!-- End of Modal Header -->
                <div class="modal-body">
                  <input type="hidden" name="orderId" value="">
                  <input type="text" value="" name="id" hidden>
                  <div class="form-group">
                    <label for="exampleInputName">Name</label>
                    <input name="name" type="name" class="form-control" id="exampleInputName" value="">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputMobile">Mobile</label>
                    <input name="contactNo" type="text" class="form-control" id="exampleInputMobile" value="">
                  </div>
                  <hr>
                  <div class="form-group">
                    <label for="exampleInputLandmark">Landmark</label>
                    <input name="landmark" type="text" class="form-control" id="exampleInputLandmark" value=""
                      placeholder="Landmark">
                  </div>

                  <div class="form-group">
                    <label for="exampleInputAddress1">Address</label>
                    <input name="line1" type="text" class="form-control" id="exampleInputAddress1" value=""
                      placeholder="Address line1">
                  </div>

                  <div class="form-group">
                    <label for="exampleInputAddress2">Address</label>
                    <input name="line2" type="text" class="form-control" id="exampleInputAddress2" value=""
                      placeholder="Address line2">
                  </div>

                  <div class="form-group">
                    <label for="exampleInputCity">City</label>
                    <input name="city" type="text" class="form-control" id="exampleInputCity" value=""
                      placeholder="City">
                  </div>

                  <div class="form-group">
                    <label for="exampleInputDistrict">District</label>
                    <input name="district" type="text" class="form-control" id="exampleInputDistrict" value=""
                      placeholder="District">
                  </div>

                  <div class="form-group">
                    <label for="exampleInputState">State</label>
                    <input name="state" type="text" class="form-control mx-200" value="" placeholder="State">
                  </div>

                  <div class="form-group">
                    <label for="exampleInputPIN">Pin-Code</label>
                    <input name="pincode" type="text" class="form-control mx-200" value="" placeholder="PIN">
                  </div>

                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-primary disabled">Save
                    changes</button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>

              </div>
            </div>
          </form>
        </div>

        <!-- End of address modal -->






      </section>



      <script src="js/order_list.js"></script>

      <script src='http://code.jquery.com/jquery-latest.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
      <script src='/superuser/js/super-admin.js'></script>
      <script type="text/javascript" src="/superuser/js/data-table.js"></script>



  </body>

</html>