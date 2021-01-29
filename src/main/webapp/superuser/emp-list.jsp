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
                      <th><input type="text" class="form-control" placeholder="Designation" disabled></th>
                      <th><input type="text" class="form-control" placeholder="Branch" disabled></th>
                      <th><input type="text" class="form-control" placeholder="Name" disabled></th>
                      <th><input type="text" class="form-control" placeholder="Account Detail" disabled></th>

                      <th><input type="text" class="form-control" placeholder="Phone" disabled></th>
                      <th><input type="text" class="form-control" placeholder="Address" disabled></th>
                      <th><input type="text" class="form-control" placeholder="Salary" disabled></th>
                      <th><input type="text" class="form-control" placeholder="Join Date" disabled></th>
                      <th><input type="text" class="form-control" placeholder="Status" disabled></th>
                      <th colspan="2" style="text-align: center;"><a class="btn btn-success" href="add-employee">Add
                          Employee</a></th>
                    </tr>
                  </thead>
                  <tbody>

                    <c:forEach items="${employees}" var="employee">

                      <tr>
                        <!-- It should be hidden and a UUID would be displaying here -->
                        <td id="empId${employee.id}" class="empId employee-email" data-empId="${employee.id}">
                          ${employee.email}</td>

                        <td>
                          ${employee.role.substring(5).toUpperCase()}
                        </td>
                        <td>

                          <button onclick="fetchBranch('${employee.getBranch().getId()}')" type="button"
                            class="btn btn-info" data-toggle="modal" data-target="#BranchModal">
                            View Branch
                          </button>
                        </td>
                        <td class="employee-name">${employee.name}</td>
                        <td>Account_Id</td>

                        <td class="employee-phone">${employee.personal_contact}</td>
                        <td><a href="" data-toggle="modal" data-target="#addressModal" id="link_address"
                            onclick="fetchAddressFromServer('${employee.id}', '${employee.getAddress().getId()}')">Address
                            Id</a>
                        </td>
                        <td class="employee-salary">${employee.salary}</td>
                        <td>${employee.joining_date}</td>



                        <td>
                          <!-- Active  -->
                          <c:if test="${employee.active}">
                            <a href="#" class="btn btn-success act">Active</a>
                            <a href="#" class="btn btn-warning de-act" style="display: none;">Deactive</a>

                            <!-- edit button visible -->
                        <td class="">
                          <a href="#" class="btn btn-success s-icon " style="display: none;">Save</a>
                          <button class="btn btn-warning e-icon">Edit</button>
                        </td>
                        </c:if>
                        <!-- Deactive  -->
                        <c:if test="${!employee.active}">
                          <a href="#" class="btn btn-success act" style="display: none;">Active</a>
                          <a href="#" class="btn btn-warning de-act">Deactive</a>

                          <!-- edit button hidden -->
                          <td class="" style="display: none;">
                            <a href="#" class="btn btn-success s-icon " style="display: none;">Save</a>
                            <button class="btn btn-warning e-icon">Edit</button>
                          </td>
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


      <!-- Address Modal -->
      <div class="modal" id="addressModal" tabindex="-1" role="dialog">
        <form action="/superuser/employee/address/update" method="POST">

          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

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
                <input name="employeeId" value="" hidden>
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
                  <input name="city" type="text" class="form-control" id="exampleInputCity" value="" placeholder="City">
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
                <button type="submit" class="btn btn-primary">Save
                  changes</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>

            </div>
          </div>
        </form>
      </div>

      <!-- End of address modal -->



      <!-- Branch Modal -->

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


      <!-- End of Branch Modal -->


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
              const branch = JSON.parse(this.response);

              modal.querySelector('#name').innerHTML = branch["name"];
              modal.querySelector('#contact').innerHTML = branch["contactNo"];
            }
          }
          xhr.send(null);
        }


        function fetchAddressFromServer(employeeId, id) {
          const modal = document.querySelector("#addressModal");
          modal.querySelectorAll('input').forEach(item => {
            if (item.type === "hidden") return;
            if (item.hidden === true) return;
            item.value = "";
          });

          modal.querySelector('input[name="employeeId"]').value = employeeId;

          const xhr = new XMLHttpRequest();
          const url = "http://localhost:8080/api/secured/address/" + id;
          xhr.open('GET', url, true);

          xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
              console.log(this.response);
              if (this.responseText.length <= 0) {
                alert("No address found");
                return false;
              }
              const address = JSON.parse(this.response);


              modal.querySelector('input[name="name"]').value = address["name"];
              modal.querySelector('input[name="contactNo"]').value = address["contactNo"];
              modal.querySelector('input[name="landmark"]').value = address["landmark"];
              modal.querySelector('input[name="line1"]').value = address["line1"];
              modal.querySelector('input[name="line2"]').value = address["line2"];
              modal.querySelector('input[name="city"]').value = address["city"];
              modal.querySelector('input[name="pincode"]').value = address["pincode"];
              modal.querySelector('input[name="district"]').value = address["district"];
              modal.querySelector('input[name="state"]').value = address["state"];
            } else if (this.readyState === 4) {
              alert("Address Not found");
            }
          }

          xhr.send(null);
        }
      </script>

      <script type="text/javascript">



        $('.table tbody tr td').on('click', '.e-icon', function () {

          $(this).hide();
          $(this).siblings().show();
          $(this).parent().siblings(".employee-name").attr("contenteditable", "true").focus().css("background-color", "#e1e1ea");
          $(this).parent().siblings(".employee-phone").attr("contenteditable", "true").focus().css("background-color", "#e1e1ea");
          $(this).parent().siblings(".employee-salary").attr("contenteditable", "true").focus().css("background-color", "#e1e1ea");

          $(this).parent().siblings(".employee-email").attr("contenteditable", "true").focus().css("background-color", "#e1e1ea");

        });


        // Edit an employee
        $('.table tbody tr td').on('click', '.s-icon', function () {

          $(this).hide();
          $(this).siblings().show();
          $(this).parent().siblings(".employee-name").attr("contenteditable", "false").focus().css("background-color", "white");
          $(this).parent().siblings(".employee-phone").attr("contenteditable", "false").focus().css("background-color", "white");
          $(this).parent().siblings(".employee-salary").attr("contenteditable", "false").focus().css("background-color", "white");
          $(this).parent().siblings(".employee-email").attr("contenteditable", "false").focus().css("background-color", "white");


          var empId = $(this).parent().siblings('.empId').data();
          console.log(empId);
          console.log(empId.empid);

          // Get details of the employee from here and send the json data to a rest endpoint
          var data = {
            "id": empId.empid,
            "name": $(this).parent().siblings('.employee-name').text(),
            "personal_contact": $(this).parent().siblings('.employee-phone').text(),
            "salary": $(this).parent().siblings('.employee-salary').text(),
            "email": $(this).parent().siblings('.employee-email').text().trim(),
          };

          const xhr = new XMLHttpRequest();
          const url = "/api/superuser/employee/info";

          xhr.open('PUT', url, true);
          xhr.setRequestHeader('Content-type', 'application/json');
          xhr.setRequestHeader(header, token);


          xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
              alert("Updated Successfully");
            } else if (this.readyState === 4) {
              alert("Something wrong... Try again!");
            }
          }

          xhr.send(JSON.stringify(data));

          console.log(data);

        });

          // $('.table tbody tr td').on('click', '.d-icon', function () {

          //   $(this).closest('tr').remove();

          // });




      </script>

      <script type="text/javascript">

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        // Deactivate an employee
        $('.table tbody tr td').on('click', '.act', function () {

          var empId = $(this).parent().siblings('.empId').data();

          console.log(empId);
          console.log(empId.empid);

          $(this).hide();
          $(this).siblings('.de-act').show();
          $(this).parent().prevAll().css("background-color", "gray").focus();
          $(this).parent().next().hide();


          var xhr = new XMLHttpRequest();
          var url = '/api/superuser/employee-delete';
          xhr.open("DELETE", url, true);
          xhr.setRequestHeader('Content-type', 'application/json');
          xhr.setRequestHeader(header, token);

          xhr.onreadystatechange = function () { // Call a function when the state changes.
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
              // console.log(typeof (JSON.parse(this.response)));
              console.log(this.response);
              console.log(JSON.parse(this.response));
            }
          }
          xhr.send(JSON.stringify(empId.empid))

        });

        // activate an employee
        $('.table tbody tr td').on('click', '.de-act', function () {

          var empId = $(this).parent().siblings('.empId').data();

          console.log(empId);
          console.log(empId.empid);

          $(this).hide();
          $(this).siblings().show();
          $(this).parent().prevAll().css("background-color", "white").focus();
          $(this).parent().next().show();


          var xhr = new XMLHttpRequest();
          var url = '/api/superuser/employee-restore';
          xhr.open("PUT", url, true);
          xhr.setRequestHeader('Content-type', 'application/json');
          xhr.setRequestHeader(header, token);

          xhr.onreadystatechange = function () { // Call a function when the state changes.
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
              // console.log(typeof (JSON.parse(this.response)));
              console.log(this.response);
              console.log(JSON.parse(this.response));
            }
          }
          xhr.send(JSON.stringify(empId.empid))

        });

      </script>



      <script type="text/javascript" src="/superuser/js/data-table.js"></script>


      <script src='http://code.jquery.com/jquery-latest.js'></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
      <script src='/superuser/js/super-admin.js'></script>
  </body>

  </html>