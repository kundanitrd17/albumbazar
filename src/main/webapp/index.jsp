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

    <link rel="stylesheet" href="/css/navbar.css">
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
      integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

    <link rel="stylesheet" href="/css/loading.css">

  </head>


  <body>

    <div class="loading" id="Loading">Loading&#8230;</div>

    <!-- Navbar Section -->
    <%@include file="navbar.jsp" %>
      <!-- End of Navbar section -->

      <!-- Carousel -->
      <!-- Slides -->
      <div id="carouselExampleSlidesOnly" class="carousel slide " data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">

          <li data-target="#carouselExampleIndicators" data-slide-to="0"></li>
          <c:forEach begin="1" end="${carasouls.size()}" varStatus="loop">
            <li data-target="#carouselExampleIndicators" data-slide-to="${loop.index}"></li>
          </c:forEach>

        </ol>
        <!-- slide show -->
        <div class="carousel-inner">


          <div class="carousel-item">
            <img class="d-block w-100" src="/img/slide1.svg" alt="First slide">
          </div>
          <c:forEach items="${carasouls}" var="carasoul">

            <div class="carousel-item">
              <img class="d-block w-100" src="${carasoul.image}" alt="First slide">
            </div>

          </c:forEach>

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
        <center>
          <h3>Lorem ipsum dolor sit, amet consectetur adipisicing elit.</h3>
          <p>Awamet consectetur adipisicing elit.P.</p>
          <hr style="width: 50%; 
          margin-top:50px; 
          margin-bottom:50px;">
        </center>

        <div class="container">
          <div class="row">

            <c:forEach items="${associations}" var="association">
              <div class="col-md-4 col-lg-4 col-xl-4">
                <div class="fancy-cards">
                  <!-- <h1>Microinteraction on active</h1>
      <p>It triggers a subtle micro-interaction (scales and adjusts) when the user interacts with it.</p>-->
                  <div class="fancy-card">
                    <div class="top" id="t">
                      <div class="caption">
                        <h3 class="title">${association.name}</h3>
                        <input type="text" name="associationId" value="${association.id}" hidden>

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
            </c:forEach>

          </div>
          <!-- Button to Open the Modal -->

        </div>



        <!-- The order Modal -->
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
                  <input type="text" name="selectedAssociationId" id="selectedAssociationId" hidden>
                  <input type="text" name="deliveryAddress" id="selectedDeliveryAddressId" hidden>
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

                <span> <strong>Total</strong> <span style="color: rgb(157, 157, 157);"> (gst included)</span> </span>
                <input id="orderTotalInput" type="number" class="form-control" style="width: 100px;" disabled>
                <button type=" button" class="btn btn-primary btn-order-next">
                  Select Address
                </button>
                <button type="submit" class="btn btn-success">Submit</button>


              </div>



              </form>
            </div>
          </div>
        </div>


        <!-- Delivery Address -->

        <div class="modal fade" id="deliveryAddressSelectorModal" tabindex="-1" role="dialog"
          aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Address</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>

              <div class="modal-body container-fluid">
                <a href="/customer/my-address" class="btn btn-primary">Create New Address</a>
                <hr>
                <div class="card-content clearfix">

                </div>

              </div>

              <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-order-prev">Confirm</button>
              </div>
            </div>
          </div>
        </div>

        <!-- ENd of delivery address -->



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
                      <th>Image</th>
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


      <section>
        <center>

          <div class="container">
            <div class="row">

              <c:forEach items="${sample_albums}" var="sample_album">
                <div class="col-sm">
                  <div class="card association-card" style="">
                    <img src="${sample_album.image}" class="card-img-top" style="" alt="BACKGROUND">
                    <div class="card-body">
                      <h5 class="card-title">${sample_album.title}</h5>
                      <button type="button" class="btn btn-primary" data-container="body" data-toggle="popover"
                        data-placement="bottom" data-content="${sample_album.description}">
                        Popover on bottom
                      </button>

                    </div>
                  </div>
                </div>

              </c:forEach>


              <!-- <div class="col-sm">
                <div class="card association-card">
                  <img
                    src="https://www.photojaanic.com/blog/wp-content/uploads/sites/2/2017/06/Wedding-photo-albums.jpg"
                    class="card-img-top" style="" alt="BACKGROUND">
                  <div class="card-body">
                    <h5 class="card-title">Photo Books</h5>
                    <p>Metallic album</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                  </div>
                </div>
              </div> -->
              <!-- 
              <div class="col-sm">
                <div class="card association-card">
                  <img
                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSt0py5TDsBZXnx9ELkcHF9bf4AFY9nN3XVhA&usqp=CAU"
                    class="card-img-top" style="" alt="BACKGROUND">
                  <div class="card-body">
                    <h5 class="card-title">Photo Books</h5>
                    <p>Metallic album</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                  </div>
                </div>
              </div>
               -->
            </div>
          </div>

        </center>
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



      <section>
        <center>
          <h3>Lorem ipsum dolor sit, amet consectetur adipisicing elit.</h3>
          <p>Awamet consectetur adipisicing elit.P.</p>
          <hr style="width: 50%; 
          margin-top:50px; 
          margin-bottom:50px;">
          <div class="container">
            <div class="row">
              <div class="col-12 col-xs-12 col-sm-12 col-md-5 col-lg-5 col-xl-5">
                <h4 style="text-align:left ;font-size: 18px; margin-bottom: 30px;">Why choose us.</h4>
                <div class="wyc-container">
                  <div class="wyc ">
                    <h4 class="wyc-title">Why shouldn't we trust atoms?</h4>
                    <p class="wyc-text">They make up everything</p>
                    <button class="wyc-toggle">
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                      <i class="fa fa-times"></i>
                    </button>
                  </div>
                  <div class="wyc">
                    <h3 class="wyc-title">
                      What do you call someone with no body and no nose?
                    </h3>
                    <p class="wyc-text">Nobody knows.</p>
                    <button class="wyc-toggle">
                      <i class="fas fa-chevron-down"></i>
                      <i class="fas fa-times"></i>
                    </button>
                  </div>

                  <div class="wyc">
                    <h3 class="wyc-title">
                      What's the object-oriented way to become wealthy?
                    </h3>
                    <p class="wyc-text">Inheritance.</p>
                    <button class="wyc-toggle">
                      <i class="fas fa-chevron-down"></i>
                      <i class="fas fa-times"></i>
                    </button>
                  </div>

                  <div class="wyc">
                    <h3 class="wyc-title">
                      How many tickles does it take to tickle an octopus?
                    </h3>
                    <p class="wyc-text">Ten-tickles!</p>
                    <button class="wyc-toggle">
                      <i class="fas fa-chevron-down"></i>
                      <i class="fas fa-times"></i>
                    </button>
                  </div>

                  <div class="wyc">
                    <h3 class="wyc-title">What is: 1 + 1?</h3>
                    <p class="wyc-text">Depends on who are you asking.</p>
                    <button class="wyc-toggle">
                      <i class="fas fa-chevron-down"></i>
                      <i class="fas fa-times"></i>
                    </button>
                  </div>
                </div>
              </div>
              <div class="col col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                <h4 style="text-align:left ;font-size: 18px; margin-bottom: 30px;">What customers say about us</h4>
                <div class="card">
                  <div class="card-body">
                    <blockquote class="blockquote mb-0">
                      <p style="font-size:12px; text-align : left;">I just wanted to say thank you very much to
                        VenusAlbum. I got 2 photo books last week and they are fabulous. Quality of photo books is
                        tremendous. My friends are inspired by seeing these photo books. Thanks again and I will
                        definitely be making more photo books too.</p>
                      <footer class="blockquote-footer" style="font-size:13px; text-align:right; margin-top : 10px;">
                        Ruby Bhatia <cite title="Source Title">Chandigarh</cite></footer>
                    </blockquote>
                  </div>
                </div>
                <div class="card mt-3">
                  <div class="card-body">
                    <blockquote class="blockquote mb-0">
                      <p style="font-size:12px; text-align : left;">Since 1976 venusalbum is in printing business and
                        that reflects in their products. Highest level of printing and quality. Your standards are
                        truly world class, and as a photographer for over 10 years I know great reproduction when I
                        see it. You do remarkable work. With great appreciation.</p>
                      <footer class="blockquote-footer" style="font-size:13px; text-align:right; margin-top : 10px;">
                        Sanjeev Mehta <cite title="Source Title">Ludhiana</cite></footer>
                    </blockquote>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </center>
      </section>




      <%@include file="footer.jsp" %>

        <!-- Footer Section -->
        <!-- <nav class="navbar sticky-bottom navbar-dark bg-dark">
        <a class="navbar-brand" href="#" style="height: 40vh; font-size: medium;"> All rights reserved
          &#169; </a>
      </nav> -->

        <!-- End of Footer section -->






        <script src="./js/index.js"></script>

        <script src="./js/delivery_address_handler.js"></script>

        <script>
          const toggles = document.querySelectorAll(".wyc-toggle");

          toggles.forEach((toggle) => {
            toggle.addEventListener("click", () => {
              toggle.parentNode.classList.toggle("active");
            });
          });
        </script>

        <script type="text/javascript">



          $(document).ready(function () {
            var custjs = '${customer}';
            console.log(custjs);

            $(".fancy-card #t").css("background-image", "url('https://static-1.gumroad.com/res/gumroad/assets/collections/food_and_cooking_thumb-34fb9ef316a7b01177529839891c3a03.jpg')");
          });




        </script>


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

        <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->

  </body>

  </html>