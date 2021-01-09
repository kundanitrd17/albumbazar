<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->

    <title>index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="./css/navbar.css">
    <link rel="stylesheet" href="./css/index.css">

  </head>

  <body>

    <!-- Navbar Section -->
    <%@include file="navbar.jsp" %>
      <!-- End of Navbar section -->

      <!-- Slides -->
      <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <!-- slide show -->
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img class="d-block w-100" src="./img/slide1.svg" alt="First slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-100" src="./img/slide2.svg" alt="Second slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-100" src="./img/slide3.svg" alt="Third slide">
          </div>
        </div>

        <!-- prev Next Control -->
        <a class="carousel-control-prev" href="#carouselExampleSlidesOnly" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleSlidesOnly" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>

      </div>
      <!-- End of Slides -->


      <section style=" margin-bottom: 5rem; padding-top: 50px; padding-bottom: 50px;">

        <div class="container">
          <div class="row">

            <div class="col-md-4 col-lg-4 col-xl-4">
              <div class="fancy-cards">
                <!-- <h1>Microinteraction on active</h1>
      <p>It triggers a subtle micro-interaction (scales and adjusts) when the user interacts with it.</p>-->
                <div class="fancy-card">
                  <div class="top" id="t">
                    <div class="caption">
                      <h3 class="title">Company Name</h3>
                      <input type="text" name="associationId" value="1" hidden>

                      <a type="button" id="" class="btn btn-primary button associationSelection" data-toggle="modal"
                        data-target="#myModal">
                        Select
                      </a>
                      <a type="button" id="associationViewPrice" class="btn btn-primary button1 associationViewPrice"
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



            <c:forEach begin="0" end="4" varStatus="loop">
              <div class="col-md-4 col-lg-4 col-xl-4">
                <div class="fancy-cards">
                  <!-- <h1>Microinteraction on active</h1>
        <p>It triggers a subtle micro-interaction (scales and adjusts) when the user interacts with it.</p>-->
                  <div class="fancy-card">
                    <div class="top" id="t">
                      <div class="caption">
                        <h3 class="title">Company Name</h3>
                        <input type="text" name="associationId" value="3" hidden>
                        <a type="button" id="associationSelection" class="btn btn-primary button associationSelection"
                          data-toggle="modal" data-target="#myModal">
                          Select
                        </a>
                        <a type="button" id="associationViewPrice" class="btn btn-primary button1 associationViewPrice"
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
          <!-- Button to Open the Modal -->

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

                <form class="" action="customer/order" method="POST">
                  <input type="text" name="selectedAssociationId" id="selectedAssociationId">
                  <h4>Select Album</h4>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  <div class="container">
                    <div class="row">

                      <select name="productCategory" id="albumType" class="form-control col-md-4 col-xl-4 col-lg-4">
                      </select>

                      <select id="albumSize" name="productSize" class="form-control col-md-4 col-xl-4 col-lg-4">

                      </select>
                    </div>
                    <hr>
                    <h4>Cover Page</h4>
                    <div class="row">


                      <select name="coverId" id="coverPageQuality" class="form-control col-md-4 col-xl-4 col-lg-4">
                        <option value="gold">gold</option>
                      </select>

                      <input class="form-control col-4" type="text" id="coverPrice" name="coverPrice" value="" disabled>
                      <!-- <select name="coverPrice" id="coverPrice" class="form-control col-4">
                        <option value="">Price</option>
                      </select> -->
                    </div>

                    <hr>
                    <h4>Photo Pages</h4>
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

                        </tr>

                        <tbody id="test-body">

                          <!-- <tr id="row0">
                            <td>
                              <select id="sheetType" name="paperId" class="form-control " style="width: 160px;">
                                <option value="paper type1">Paper Type</option>
                              </select>
                            </td>

                            <td>
                              <input name="numberOfSheet" id="pr" type="number" class="form-control input-md"
                                placeholder="" style="width: 80px;" />
                            </td>

                            <td>
                              <input name="sheetPrice" type="number" class="form-control input-md" placeholder=""
                                style="width: 80px;" disabled />
                            </td>
                            <td><input id="add-row" class="btn btn-primary" type="button" value="+" /></td>
                          </tr> -->



                        </tbody>
                        <tfoot>

                          <tr>
                            <td colspan="4">
                              <textarea name="description" cols="60" rows="5" placeholder="description"></textarea>
                            </td>
                          </tr>
                        </tfoot>
                      </table>
                    </div>

                  </div>


              </div>

              <!-- Modal footer -->
              <div class="modal-footer">
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
                      <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;" class="fa fa-trash"></i>
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
                      <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;" class="fa fa-trash"></i>
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
      </section>

      <section class="w-100 py-3 my-3">

        <div class="jumbotron d-flex flex-column align-items-center">
          <h1 class="display-4">Hello, world!</h1>
          <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra
            attention
            to featured content or information.</p>
          <hr class="my-4">
          <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
          <p class="lead">
            <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
          </p>
        </div>



      </section>



      <!-- Footer Section -->
      <nav class="navbar sticky-bottom navbar-dark bg-dark">
        <a class="navbar-brand" href="#" style="height: 40vh; font-size: medium;"> All rights reserved
          &#169; </a>
      </nav>

      <!-- End of Footer section -->






      <script src="./js/index.js"></script>

      <script type="text/javascript">



        $(document).ready(function () {
          var custjs = '${customer}';
          console.log(custjs);

          $(".fancy-card #t").css("background-image", "url('https://static-1.gumroad.com/res/gumroad/assets/collections/food_and_cooking_thumb-34fb9ef316a7b01177529839891c3a03.jpg')");
        });


      </script>
      <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->

  </body>

  </html>