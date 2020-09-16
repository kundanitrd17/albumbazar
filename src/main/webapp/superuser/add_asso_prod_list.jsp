<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
    <link rel="stylesheet" href="./css/super-admin.css">

















    <style type="text/css">
      body
      {
        background-color: white;
      }
table
{
  background-color: white;
  margin-top:50px;

-webkit-box-shadow: 0px 3px 13px 0px rgba(204,195,240,1);
-moz-box-shadow: 0px 3px 13px 0px rgba(204,195,240,1);
box-shadow: 0px 3px 13px 0px rgba(204,195,240,1);

border: none;
}
table tr th
{
  text-align: center;
  border: none;
}
table tr td
{
  
  border: none;
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
      <div class="search">
        <input type="text" placeholder="Type here"><i class="fa fa-search"></i>
      </div>
      <ul class="categories">
        <li><i class="fa fa-home fa-fw" aria-hidden="true"></i><a href="#"> Branch</a>
          <ul class="side-nav-dropdown">
            <li><a href="/superuser/add-branch">Add Branch</a></li>
            <li><a href="#">Update Branch</a></li>
            <li><a href="#">Branch Admin View</a></li>
            <li><a href="#">Branch Transection</a></li>
            <li><a href="#">Delete Branch</a></li>
            <li><a href="#">Branch Order</a></li>
          </ul>
        </li>
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
            <li><a href="#">ipsum dolor sit</a></li>
          </ul>
        </li>
        <li><i class="fa fa-users fa-fw"></i><a href="#"> Our Association</a>
          <ul class="side-nav-dropdown">
            <li><a href="#">Add Association</a></li>
            <li><a href="#">Update Association</a></li>
            <li><a href="#">Delete Association</a></li>
            <li><a href="#">Views Association List </a></li>
            <li><a href="#">Association Order List</a></li>
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
      <nav class="navbar navbar-default">
        <div class="container-fluid">
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


<div class="container col-md-8">
 
 
  <div class="table-responsive">
    <table id="test-table" class="table table-condensed table-bordered">
      <thead>
        <tr>
          <th colspan="4" style="text-align: left;padding-left: 20px;">
            Add Paper List
          </th>
        </tr>
        <tr>
          <th>Paper Type</th>
          <th>Paper Size</th>
          <th>Paper Price</th>
          <th> <input id='add-row' class='btn btn-primary' type='button' value='Add' /></th>
        </tr>
      </thead>
      <form action="C:\Users\GFX-I\Desktop\springmvc_maven.txt"> 
      <tbody id="test-body">
        <tr id="row0">
          <td>
            <input name='pt[]' value='' type='text' class='form-control' placeholder="Paper Type" />
          </td>
          <td>
            <input name='ps[]' value='' type='text' class='form-control input-md'  placeholder="Paper Size" />
          </td>

           <td>
            <input name='pp[]' value='' type='number' class='form-control input-md'  placeholder="Paper Price" />
          </td>

          <td>
            <input class='delete-row btn btn-danger' type='button' value='X' />
          </td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="4" align="center">
            <input class='btn btn-success' type='submit' value='Submit' />
          </td>
        </tr>
       
      </tfoot>
      </form>
    </table>
   
  </div>

 
 </div> 




  
      </section>
      <script src='http://code.jquery.com/jquery-latest.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
      <script src='./js/super-admin.js'></script>
 
      <script type="text/javascript">
  // Add row
  var row=1;
  $(document).on("click", "#add-row", function () {
  var new_row = '<tr id="row' + row + '"><td><input name="pt[]' + row + '" type="text" placeholder="Paper Type" class="form-control" /></td><td><input name="ps[]' + row + '" type="text" class="form-control" placeholder="Paper Size" /></td><td><input name="pp[]' + row + '" type="number" class="form-control" placeholder="Paper Price" /></td><td><input class="delete-row btn btn-danger" type="button" value="X" /></td></tr>';
    
  $('#test-body').append(new_row);
  row++;
  return false;
  });
  
  // Remove criterion
  $(document).on("click", ".delete-row", function () {
  //  alert("deleting row#"+row);
    if(row>1) {
      $(this).closest('tr').remove();
      row--;
    }
  return false;
  });

</script>

 </body>
    </html>
