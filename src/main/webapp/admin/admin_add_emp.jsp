<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
    <link rel="stylesheet" href="../superuser/css/super-admin.css">
 <link rel="stylesheet" type="text/css" href="../superuser/css/form.css" />
    <style type="text/css">
      body
      {
        background-color: white;
      }
  .side-nav {

  background-color: white;
  -webkit-box-shadow: 3px 0px 5px 0px rgba(0,0,0,0.75);
-moz-box-shadow: 3px 0px 5px 0px rgba(0,0,0,0.75);
box-shadow: 3px 0px 5px 0px rgba(0,0,0,0.75);

}
.side-nav .heading {
  background-color: seagreen;

}
.side-nav ul li
{
  color: green;
}

.welcome {
  color: black;
}
.welcome .content {
  background-color: white;
 
  margin-top: 25px;
}
.welcome h2 {
  font-family: Calibri;
  font-weight: 100;
  margin-top: 0
}
.welcome p {
  color: #999;
}
#contents
{
  background-color: white;
}

    </style>
  </head>

  <body>
    <aside class="side-nav" id="show-side-navigation1">
      <i class="fa fa-bars close-aside hidden-sm hidden-md hidden-lg" data-close="show-side-navigation1"></i>
      <div class="heading">
        <img src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148907008/medium/1501685726/enhance" alt="">
        <div class="info">
          <h3><a href="#">Album Bazar</a></h3>
          <p>Lorem ipsum dolor sit amet consectetur.</p>
        </div>
      </div>

      <ul class="categories">
        <li><i class="fa fa-support fa-fw"></i><a href="#"> Branch Order</a>
          <ul class="side-nav-dropdown">
            <li><a href="#">Order Under Process</a></li>
            <li><a href="#">Completed Order</a></li>
            <li><a href="#">Today Accept Order</a></li>
            <li><a href="#">Today Order</a></li>
            <li><a href="#">Order Pending</a></li>
            <li><a href="#">View Order List</a></li>
          </ul>
        </li>
        <li><i class="fa fa-envelope fa-fw"></i><a href="#"> Employees</a>
          <ul class="side-nav-dropdown">
            <li><a href="#">Add Employee</a></li>
            <li><a href="#">Delete Employee</a></li>
            <li><a href="#">Update Employee</a></li>
            <li><a href="#">Employee Attendance</a></li>
            <li><a href="#">Other</a></li>
          </ul>
        </li>
        <li><i class="fa fa-bolt fa-fw"></i><a href="#"> Transaction</a>
          <ul class="side-nav-dropdown">
            <li><a href="#">Lorem ipsum</a></li>
            <li><a href="#">ipsum dolor</a></li>
            <li><a href="#">dolor ipsum</a></li>
            <li><a href="#">amet consectetur</a></li>
            <li><a href="#">ipsum dolor sit</a></li>
          </ul>
        </li>
        <p>Example:</p>
        <li><i class="fa fa-envelope-open-o fa-fw"></i><a href="#"> Messages <span class="num dang">56</span></a></li>
        <li><i class="fa fa-wrench fa-fw"></i><a href="#"> Settings <span class="num prim">6</span></a>
          <ul class="side-nav-dropdown">
            <li><a href="#">Lorem ipsum</a></li>
            <li><a href="#">ipsum dolor</a></li>
            <li><a href="#">dolor ipsum</a></li>
            <li><a href="#">amet consectetur</a></li>
            <li><a href="#">ipsum dolor sit</a></li>
          </ul>
        </li>
        <li><i class="fa fa-laptop fa-fw"></i><a href="#"> About UI &amp; UX <span class="num succ">43</span></a></li>
        <li><i class="fa fa-comments-o fa-fw"></i><a href="#"> Something else</a></li>
      </ul>
    </aside>
    <section id="contents">
      <nav class="navbar navbar-default" >
        <div class="container-fluid" style="background-color: seagreen;">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <i class="fa fa-align-right"></i>
            </button>
            <a class="navbar-brand" href="#">my<span class="main-color">Dashboard</span></a>
          </div>
          <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">My profile <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#"><i class="fa fa-user-o fw"></i> My account</a></li>
                  <li><a href="#"><i class="fa fa-envelope-o fw"></i> My inbox</a></li>
                  <li><a href="#"><i class="fa fa-question-circle-o fw"></i> Help</a></li>
                  <li role="separator" class="divider"></li>
                  <li>
                  
                    <form action="/superuser/logout-super" method="POST">
                      <i class="fa fa-sign-out"></i> 
                      <input type="submit" value="logout">
                      <input
                      type="hidden"
                      name="${_csrf.parameterName}"
                      value="${_csrf.token}"
                    />
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
                <h2>Welcome to Branch</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor.</p>
              </div>
            </div>
          </div>
        </div>
      </div>




 <section class="form-box">
      <div class="container">
        <div class="row" style="background-color: white;padding: 0px;">

          

          <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 form-wizard">
            <!-- Form Wizard -->
            <form role="form" action="" method="post">
              <input
                type="hidden"
                name="${_csrf.parameterName}"
                value="${_csrf.token}"
              />

              <h3>Sign Up Office Employee Account</h3>
              <p>Fill all form field to go next step</p>

              <!-- Form progress -->
              <div class="form-wizard-steps form-wizard-tolal-steps-4">
                <div class="form-wizard-progress">
                  <div
                    class="form-wizard-progress-line"
                    data-now-value="12.25"
                    data-number-of-steps="4"
                    style="width: 12.25%"
                  ></div>
                </div>
                <!-- Step 1 -->
                <div class="form-wizard-step active">
                  <div class="form-wizard-step-icon">
                    <i class="fa fa-user" aria-hidden="true"></i>
                  </div>
                  <p>Branch Detail</p>
                </div>
                <!-- Step 1 -->

                <!-- Step 2 -->
                <div class="form-wizard-step">
                  <div class="form-wizard-step-icon">
                    <i class="fa fa-location-arrow" aria-hidden="true"></i>
                  </div>
                  <p>Branch Address</p>
                </div>
                <!-- Step 2 -->
              </div>
              <!-- Form progress -->

              <!-- Form Step 1 -->
              <fieldset>
                <h4>Employee Information: <span>Step 1 - 2</span></h4>
                <div class="form-group">
                  <label>Employee Name <span>*</span></label>
                  <input
                    type="text"
                    name="ename"
                    placeholder="Employee Name"
                    class="form-control required"
                  />
                </div>
               <div class="form-group">
                  <label>Employee Branch <span>*</span></label>
                  <select class="form-control">
                    <option value="">Asnmk</option>
                   </select>
                </div>

              <div class="form-group">
                  <label>Designation</label>
                  <select class="form-control">
                    <option value="">Admin</option>
                   </select>
                </div>
                <div class="form-group">
                  <label>Salary <span>*</span></label>
                  <input
                    type="text"
                    name="salary"
                    placeholder="Salary "
                    class="form-control"
                  />
                </div>

                    <div class="form-group">
                  <label>Gender : </label>
                  
                    <input
                      type="radio"
                      name="Gender"
                      value="option1"
                      checked="checked"
                    />
                    Male
                  
          
                    <input type="radio" name="Gender" value="option2" /> Female
           
                </div>

                    <div class="form-group ">
                      <label>Date of Birth<span> *</span></label>
                      <input
                        type="date"
                        name="binougration_date"
                        placeholder="Branch Inougration Date "
                        class="form-control required"
                      />
                    </div>
                 
               
                  <div class="form-group">
                    <label>Phone: <span>*</span></label>
                    <input
                      type="text"
                      name="Phone"
                      placeholder="Phone"
                      class="form-control required"
                    />
                  </div>
              
                <div class="form-group">
                  <label>Email <span>*</span></label>
                  <input
                    type="text"
                    name="email"
                    placeholder="Branch E-mail "
                    class="form-control"
                  />
                </div>

                <div class="form-group">
                  <label>Password <span>*</span></label>
                  <input
                    type="password"
                    name="password"
                    placeholder="Password "
                    class="form-control required"
                  />
                </div>

               

                <div class="form-wizard-buttons">
                  <button type="button" class="btn btn-next">Next</button>
                </div>
              </fieldset>
              <!-- Form Step 1 -->

              <!-- Form Step 2 -->
              <fieldset>
                <h4>Branch Address : <span>Step 2 - 2</span></h4>

                <div class="form-group">
                  <label>Landmark: <span>*</span></label>
                  <input
                    type="text"
                    name="landmark"
                    placeholder="Landmark"
                    class="form-control required"
                  />
                </div>
                <div class="form-group">
                  <label>Street1: <span>*</span></label>
                  <input
                    type="text"
                    name="street1"
                    placeholder="Street1"
                    class="form-control required"
                  />
                </div>
                <div class="form-group">
                  <label>Town: <span>*</span></label>
                  <input
                    type="text"
                    name="town"
                    placeholder="Town"
                    class="form-control required"
                    
                  />
                </div>
                  <div class="form-group">
                  <label>Post Office <span>*</span></label>
                  <input
                    type="text"
                    name="po"
                    placeholder="Post Office "
                    class="form-control required"
                  />
                </div>


                 <div class="form-group">
                  <label>Pin Code: <span>*</span></label>
                  <input
                    type="text"
                    name="pin"
                    placeholder="Pin Code"
                    class="form-control required"
                    max=6;
                    min=6;
                  />
                </div>


                <div class="form-group">
                  <label>District: <span>*</span></label>
                  <input
                    type="text"
                    name="dist"
                    placeholder="District"
                    class="form-control required"
                  />
                </div>


                <div class="form-group">
                  <label>City: <span>*</span></label>
                  <input
                    type="text"
                    name="city"
                    placeholder="City"
                    class="form-control required"
                  />
                </div>
                <div class="form-group">
                  <label>State: <span>*</span></label>
                  <input
                    type="text"
                    name="state"
                    placeholder="State"
                    class="form-control required"
                  />
                </div>

                <div class="form-wizard-buttons">
                  <button type="button" class="btn btn-previous">
                    Previous
                  </button>
                  <button type="submit" class="btn btn-submit">Submit</button>
                </div>
              </fieldset>
              <!-- Form Step 2 -->
            </form>
            <!-- Form Wizard -->
          </div>
        </div>
      </div>
    </section>




    
      </section>
      <script src='http://code.jquery.com/jquery-latest.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
      <script src='../superuser/js/super-admin.js'></script>
      <script type="text/javascript" src="../superuser/js/add-branch.js"></script>

      </body>
    </html>
