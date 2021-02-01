<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
    <link rel="stylesheet" href="/superuser/css/super-admin.css">

    <link rel="stylesheet" href="/css/loading.css">
  </head>

  <body>
    <div class="loading" id="Loading">Loading&#8230;</div>

    <%@include file="sidebar.jsp" %>



      <section id="contents">
        <%@include file="header.jsp" %>


          <div class="welcome">
            <div class="container-fluid">
              <div class="row">
                <div class="col-md-12">
                  <div class="content">

                    <h2>Welcome</h2>

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
                      <h3 id="TotalCustomerCount">
                        <i class="fa fa-spinner fa-pulse" aria-hidden="true"></i>
                      </h3>
                      <span>Customers</span>
                    </div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="box">
                    <i class="fa fa-file fa-fw danger"></i>
                    <div class="info">
                      <h3 id="TotalOrdersCount">
                        <i class="fa fa-spinner fa-pulse" aria-hidden="true"></i>
                      </h3>
                      <span>Orders</span>

                    </div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="box">
                    <i class="fa fa-users fa-fw success"></i>
                    <div class="info">
                      <h3 id="TotalEmployeeCount">
                        <i class="fa fa-spinner fa-pulse" aria-hidden="true"></i>
                      </h3>
                      <span>Employees</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
          <!--
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
          </section> -->
          <section class="admins">
            <div class="container-fluid">
              <h3 style="color: aliceblue;">Admins:</h3>
              <div class="row" id="AdminListCardDiv"
                style="height: 400px; background-color: rgb(97, 95, 99);overflow: scroll; padding-top: 20px;">

                <div class="col-12 col-md-6">
                  <div class="box">
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
                  </div>
                </div>

                <div class="col-md-6">
                  <div class="box">
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
                  </div>
                </div>

                <div class="col-md-6">
                  <div class="box">
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
                <!-- <div class="col-md-6">
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
                </div> -->
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
      <script src='http://code.jquery.com/jquery-latest.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
      <script src='/superuser/js/super-admin.js'></script>

      <script src="/superuser/js/superuser_home.js"></script>

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

  </body>

  </html>