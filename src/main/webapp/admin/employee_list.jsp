<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <!DOCTYPE html>

  <html>

  <head>
    <title>Album Bazaar</title>
    <meta charset="utf-8">
    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="/superuser/css/super-admin.css">

  </head>
<style>

</style>
  <body>



    <%@include file="sidebar.jsp" %>


      <section id="contents">
    <%@include file="header.jsp" %>






        <div class="container">
          <div class="row">
            <div class="panel panel-primary filterable table-responsive">
              <div class="panel-heading">
                <h3 class="panel-title">Employees</h3>
                <div class="pull-right" style="position: relative;
top: -20px;"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                    Filter</button></div>
              </div>
              <table class="table " style="font-size: 12px;">
                <thead>
                  <tr class="filters">
                    <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Branch" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Name" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Account Detail" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Phone" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Address" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Salary" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Join Date" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Status" disabled></th>

                  </tr>
                </thead>
                <tbody>

                  <c:forEach items="${employees}" var="employee">

                    <tr>
                      <!-- It should be hidden and a UUID would be displaying here -->
                      <td id="empId${employee.id}" class="empId" data-empId="${employee.id}">${employee.email}</td>

                      <td>
                        <button onclick="fetchBranch('${employee.getBranch().getId()}')" type="button" class="btn btn-info"
                        data-toggle="modal" data-target="#BranchModal">
                        View Branch
                      </button>
                    </td>
                      <td>${employee.name}</td>
                      <td>Account_Id</td>
                      <td>${employee.personal_contact}</td>
                      <td> <button onclick="fetchAddress('${employee.getAddress().getId()}')" type="button"
                        class="btn btn-info" data-toggle="modal" data-target="#AddressModal">
                        Employee Address
                    </button></td>
                      <td> ${employee.salary}</td>
                      <td>${employee.joining_date}</td>

                      <td>
                        <!-- Active  -->
                        <c:if test="${employee.active}">
                          <button class="btn btn-success act disabled">Active</button>
                        </c:if>
                        <!-- Deactive  -->
                        <c:if test="${!employee.active}">
                          <button class="btn btn-warning de-act disabled">Deactive</button>
                        </c:if>
                      </td>

                      <!-- <td><a class="btn btn-danger d-icon">Delete</button></a></td> -->
                    </tr>

                  </c:forEach>

                </tbody>
              </table>
            </div>
          </div>
        </div>


      </section>

      <div class="modal" id="AddressModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Modal Heading</h4>
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
                                <th>Landmark: </th>
                                <td id="landmark"></td>
                            </tr>
                            <tr>
                                <th>Line1: </th>
                                <td id="line1"></td>
                            </tr>
                            <tr>
                                <th>Line2: </th>
                                <td id="line2"></td>
                            </tr>
                            <tr>
                                <th>City: </th>
                                <td id="city"></td>
                            </tr>
                            <tr>
                                <th>Pincode: </th>
                                <td id="pincode"></td>
                            </tr>
                            <tr>
                                <th>District: </th>
                                <td id="district"></td>
                            </tr>
                            <tr>
                                <th>State: </th>
                                <td id="state"></td>
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






    <div class="modal" id="BranchModal">
      <div class="modal-dialog">
        <div class="modal-content">
    
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">Modal Heading</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
    
          <!-- Modal body -->
          <div class="modal-body">
            <table class="table table-str">
              <tbody>
                <tr>
                  <th>Branch Name: </th>
                  <td id="name"></td>
                </tr>
                <tr>
                  <th>Branch Contact: </th>
                  <td id="contact"></td>
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


      <script type="text/javascript" src="/superuser/js/data-table.js"></script>


      <script src='http://code.jquery.com/jquery-latest.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
      <script src='/superuser/js/super-admin.js'></script>
      <script>
        function fetchAddress(id)
        {
            const modal = document.querySelector("#AddressModal");
          modal.querySelectorAll('td').forEach(item => {

            item.value = "";
          });
          const xhr=new XMLHttpRequest();
          const url="http://localhost:8080/api/secured/address/" + id;
          xhr.open("GET",url,true);
          xhr.onreadystatechange=function(){
            if (this.readyState === 4 && this.status === 200 )
            {
              console.log(this.response);
              
            const address = JSON.parse(this.response);

              modal.querySelector('#name').innerHTML = address["name"];
              modal.querySelector('#contact').innerHTML = address["contactNo"];
              modal.querySelector('#landmark').innerHTML = address["landmark"];
              modal.querySelector('#line1').innerHTML = address["line1"];
              modal.querySelector('#line2').innerHTML = address["line2"];
              modal.querySelector('#city').innerHTML = address["city"];
              modal.querySelector('#pincode').innerHTML = address["pincode"];
              modal.querySelector('#district').innerHTML = address["district"];
              modal.querySelector('#state').innerHTML = address["state"];

            }
          }


          xhr.send(null);
          
        }
      </script>

      <script>
                function fetchBranch(id) {
                    const modal = document.querySelector("#BranchModal");
                    modal.querySelectorAll('td').forEach(item => {

                      item.value = "";
                    });
                    const xhr = new XMLHttpRequest();
                    const url = "http://localhost:8080/api/secured/branch/" + id;
                    xhr.open("GET", url, true);
                    xhr.onreadystatechange = function () {
                      if (this.readyState === 4 && this.status === 200) {
                        console.log(this.response);

                        const address = JSON.parse(this.response);

                        modal.querySelector('#name').innerHTML = address["name"];
                        modal.querySelector('#contact').innerHTML = address["contactNo"];
                

                      }
                    }


                    xhr.send(null);

                  }
      </script>
  </body>

  </html>