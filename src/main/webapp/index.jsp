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

    <style>
      #carouselExampleSlidesOnly {
        padding-top: 20px;
        padding-left: 50px;
        padding-right: 50px;
        height: 80vh !important;
        width: 100% !important;
      }

      /* .item, */
      .card img {
        /* padding: 50px; */
        height: 80vh !important;
        width: 100% !important;
      }

      .association-card {
        max-width: 20rem;
      }

      .association-card p {
        font-size: 12px;
      }

      .association-card .card-img-top {
        height: 250px !important;
        width: 500px;
      }

      .accordion-body {
        font-weight: 400;
        font-size: 13px;
        text-align: left;
      }

      .accordion-item h2 button {
        font-weight: 700;
        font-size: 14px;
      }
    </style>

  </head>


  <body>

    <!-- Navbar Section -->
    <%@include file="navbar.jsp" %>
      <!-- End of Navbar section -->

      <!-- Carousel -->
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
        <center>
          <h3>Lorem ipsum dolor sit, amet consectetur adipisicing elit.</h3>
          <p>Awamet consectetur adipisicing elit.P.</p>
          <hr style="width: 50%; 
          margin-top:50px; 
          margin-bottom:50px;">
        </center>

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
              <div class="col-sm">
                <div class="card association-card" style="">
                  <img
                    src="https://www.albumsremembered.com/wp-content/uploads/2018/10/AlbumsRemembered_Product-acrylic-sq.jpg"
                    class="card-img-top" style="" alt="BACKGROUND">
                  <div class="card-body">
                    <h5 class="card-title">Photo Books</h5>
                    <p>Metallic album</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                  </div>
                </div>
              </div>
              <div class="col-sm">
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
              </div>
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
              <div class="col">
                <h4 style="text-align:left ;font-size: 18px; margin-bottom: 30px;">Why choose us.</h4>
                <div class="accordion" id="accordionExample">
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="headingOne">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="">
                        <strong> Who we are ?</strong>
                      </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                      data-bs-parent="#accordionExample">
                      <div class="accordion-body" style="">
                        VENUS PHOTO ALBUM is India ‘s leading company 1976. We are market – driven and technology –
                        based organization serving millions of customers in India and Abroad. Over the years Venus
                        Photo Albums have reached 250 million homes scaling beyond the geo- political boundaries. We
                        have a well established market networking developed over three decades.
                      </div>
                    </div>
                  </div>
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTwo">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"
                        style="font-weight: 700; font-size: 14px ;">
                        <strong> What we do ?</strong>
                      </button>
                    </h2>
                    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                      data-bs-parent="#accordionExample">
                      <div class="accordion-body" style="">
                        Venus photo books believe in creating stories out of Images. We are passionate about
                        revolutionizing era of photo book, mini photo book, table calendar and wall calendar . All
                        options that preserve your cherished memories are being catered beautifully. How especially we
                        treat the choicest memories of your life and how perfectly we use advanced technology to liven
                        up valuable sentiments of life is a thing to see.
                      </div>
                    </div>
                  </div>
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="headingThree">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="">
                        <strong> Our Capabilities</strong>
                      </button>
                    </h2>
                    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                      data-bs-parent="#accordionExample">
                      <div class="accordion-body" style="">
                        We have the production units in Amritsar, Chandigarh and Ludhiana, (Punjab) which functions
                        through modern computerized technology. One and half lack albums roll out of these units every
                        month.
                        A workforce of 100 technical people and experts are working together to improve upon
                        international quality range and doing constant research to find out the latest market trends
                        to provide its customer with the best quality product.
                        There are some 11 main market agents working for us and 1200+ wholesellers for state based
                        sales throughout India. Apart from these are several thousand commercial photographers who buy
                        Venus Photo Albums throughout the year.
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col" style="margin-left: 70px;">
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




      <!-- Footer -->
      <footer class="footers text-center text-lg-start mt-5 bg-dark" style="color: white">
        <!-- Grid container -->
        <div class="container p-5">
          <!--Grid row-->
          <div class="row">
            <!--Grid column-->
            <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
              <h5 class="text-uppercase">Newsletter Sign Up</h5>

              <p style="color: gray;">
                Just register with us, and get updates and special offers for you by venusalbum.com
              </p>
              <button type="button" class="btn btn-primary">click here to sign up</button>
            </div>
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
              <h5 class="text-uppercase">Latest Updates</h5>

              <ul class="list-unstyled mb-0">
                <li>
                  <a href="#!" class="text-light">HP Awarded Venus album a Star Company</a>
                </li>
              </ul>
            </div>
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
              <h5 class="text-uppercase mb-0"> Quick Links</h5>

              <ul class="list-unstyled">
                <li>
                  <a href="#!" class="text-light">Home</a>
                </li>
                <li>
                  <a href="#!" class="text-light">About</a>
                </li>
                <li>
                  <a href="#!" class="text-light">Services</a>
                </li>
                <li>
                  <a href="#!" class="text-light">Contact us</a>
                </li>
              </ul>
            </div>
            <!--Grid column-->
          </div>
          <!--Grid row-->
        </div>
        <!-- Grid container -->

        <!-- Copyright -->
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
          © 2020 Copyright:
          <a class="text-light" href="#">Company Name</a>
        </div>
        <!-- Copyright -->
      </footer>
      <!-- Footer -->

      <!-- Footer Section -->
      <!-- <nav class="navbar sticky-bottom navbar-dark bg-dark">
        <a class="navbar-brand" href="#" style="height: 40vh; font-size: medium;"> All rights reserved
          &#169; </a>
      </nav> -->

      <!-- End of Footer section -->






      <script src="./js/index.js"></script>

      <script src="./js/delivery_address_handler.js"></script>

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