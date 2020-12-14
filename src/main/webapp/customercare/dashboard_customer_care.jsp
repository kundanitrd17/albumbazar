<!DOCTYPE html>
<html>

<head>
  <title></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">

  <link rel="stylesheet" type="text/css" href="http://localhost:8080/customercare/css/add-branch.css">
  <link rel="stylesheet" type="text/css" href="http://localhost:8080/customercare/css/dashboard-superuser.css">

  <style type="text/css">

  </style>
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- SideBar insertion -->
  <%@include file="sidebar.jsp" %>
  <!-- End of sidebar -->
  <section id="contents">
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed show-side-btn" data-toggle="collapse" data-target=""
            aria-expanded="false">

          </button>
          <ul class="nav navbar-nav">
            <li id="bs-example-navbar-collapse-1"> <a href="#"><i data-show="show-side-navigation1"
                  class="fa fa-bars show-side-btn"></i></a></li>
          </ul>
        </div>
        <div class="collapse navbar-collapse navbar-right">

        </div>
      </div>
    </nav>

    <div class="welcome">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="content">
              <h2>Welcome to Dashboard</h2>
              <p>Write Something Here</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <section class="statistics">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-4">
            <div class="box">
              <i class="fa fa-envelope fa-fw bg-primary"></i>
              <div class="info">
                <h3>1,245</h3> <span>Today Orders</span>
                <p>Write Some Thing Here</p>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="box">
              <i class="fa fa-file fa-fw danger"></i>
              <div class="info">
                <h3>34</h3> <span>Completed Orders</span>
                <p>Write Some Thing Here</p>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="box">
              <i class="fa fa-users fa-fw success"></i>
              <div class="info">
                <h3>5,245</h3> <span>Accepted Orders</span>
                <p>Write Some Thing Here</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="charts">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-6">
            <div class="chart-container">
              <h3>Chart</h3>
              <canvas id="myChart"></canvas>
            </div>
          </div>
          <div class="col-md-6">
            <div class="chart-container">
              <h3>Chart2</h3>
              <canvas id="myChart2"></canvas>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="admins">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-6">
            <div class="box">
              <h3>Admins:</h3>
              <div class="admin">
                <div class="img">
                  <img class="img-responsive"
                    src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148906966/small/1501685402/enhance"
                    alt="admin">
                </div>
                <div class="info">
                  <h3>Joge Lucky</h3>
                  <p>Lorem ipsum dolor sit amet.</p>
                </div>
              </div>
              <div class="admin">
                <div class="img">
                  <img class="img-responsive"
                    src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148907137/small/1501685404/enhance"
                    alt="admin">
                </div>
                <div class="info">
                  <h3>Joge Lucky</h3>
                  <p>Lorem ipsum dolor sit amet.</p>
                </div>
              </div>
              <div class="admin">
                <div class="img">
                  <img class="img-responsive"
                    src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148907019/small/1501685403/enhance"
                    alt="admin">
                </div>
                <div class="info">
                  <h3>Joge Lucky</h3>
                  <p>Lorem ipsum dolor sit amet.</p>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="box">
              <h3>Moderators:</h3>
              <div class="admin">
                <div class="img">
                  <img class="img-responsive"
                    src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148907114/small/1501685404/enhance"
                    alt="admin">
                </div>
                <div class="info">
                  <h3>Joge Lucky</h3>
                  <p>Lorem ipsum dolor sit amet.</p>
                </div>
              </div>
              <div class="admin">
                <div class="img">
                  <img class="img-responsive"
                    src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148907086/small/1501685404/enhance"
                    alt="admin">
                </div>
                <div class="info">
                  <h3>Joge Lucky</h3>
                  <p>Lorem ipsum dolor sit amet.</p>
                </div>
              </div>
              <div class="admin">
                <div class="img">
                  <img class="img-responsive"
                    src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148907008/medium/1501685726/enhance"
                    alt="admin">
                </div>
                <div class="info">
                  <h3>Joge Lucky</h3>
                  <p>Lorem ipsum dolor sit amet.</p>
                </div>
              </div>
            </div>
          </div>
        </div>
    </section>
    <section class='statis text-center'>
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-3">
            <div class="box bg-primary">
              <i class="fa fa-eye"></i>
              <h3>5,154</h3>
              <p class="lead">Page views</p>
            </div>
          </div>
          <div class="col-md-3">
            <div class="box danger">
              <i class="fa fa-user-o"></i>
              <h3>245</h3>
              <p class="lead">User registered</p>
            </div>
          </div>
          <div class="col-md-3">
            <div class="box warning">
              <i class="fa fa-shopping-cart"></i>
              <h3>5,154</h3>
              <p class="lead">Product sales</p>
            </div>
          </div>
          <div class="col-md-3">
            <div class="box success">
              <i class="fa fa-handshake-o"></i>
              <h3>5,154</h3>
              <p class="lead">Transactions</p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="chrt3">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-9">
            <div class="chart-container">
              <canvas id="chart3" width="100%"></canvas>
            </div>
          </div>
          <div class="col-md-4">
            <div class="box">
            </div>
          </div>
        </div>
      </div>
    </section>


  </section>





  <script type="text/javascript" src="http://localhost:8080/customercare/js/add-branch.js"></script>
  <script type="text/javascript">
    // Other important pens.
    // Map: https://codepen.io/themustafaomar/pen/ZEGJeZq
    // Navbar: https://codepen.io/themustafaomar/pen/VKbQyZ

    $(function () {

      'use strict';

      var aside = $('.side-nav'),
        showAsideBtn = $('.show-side-btn'),
        contents = $('#contents'),
        _window = $(window)

      showAsideBtn.on("click", function () {
        $("#" + $(this).data('show')).toggleClass('show-side-nav');
        contents.toggleClass('margin');
      });

      if (_window.width() <= 767) {
        aside.addClass('show-side-nav');
      }

      _window.on('resize', function () {
        if ($(window).width() > 767) {
          aside.removeClass('show-side-nav');
        }
      });

      // dropdown menu in the side nav
      var slideNavDropdown = $('.side-nav-dropdown');

      $('.side-nav .categories li').on('click', function () {

        var $this = $(this)

        $this.toggleClass('opend').siblings().removeClass('opend');

        if ($this.hasClass('opend')) {
          $this.find('.side-nav-dropdown').slideToggle('fast');
          $this.siblings().find('.side-nav-dropdown').slideUp('fast');
        } else {
          $this.find('.side-nav-dropdown').slideUp('fast');
        }
      });

      $('.side-nav .close-aside').on('click', function () {
        $('#' + $(this).data('close')).addClass('show-side-nav');
        contents.removeClass('margin');
      });



    });
  </script>
</body>

</html>