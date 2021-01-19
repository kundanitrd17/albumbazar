<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <html>

  <head>
    <title>Album Bazaar</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="/superuser/css/form.css" />
    <style type="text/css"></style>
  </head>

  <body>
    <section class="form-box">
      <div class="container">
        <div class="row">

          <div class="col-md-3 col-lg-3 col-xl-3"></div>

          <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 form-wizard">
            <!-- Form Wizard -->
            <form role="form" action="/superuser/employee/add" method="POST">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

              <h3>Sign Up Office Employee Account</h3>
              <p>Fill all form field to go next step</p>

              <!-- Form progress -->
              <div class="form-wizard-steps form-wizard-tolal-steps-4">
                <div class="form-wizard-progress">
                  <div class="form-wizard-progress-line" data-now-value="12.25" data-number-of-steps="4"
                    style="width: 12.25%"></div>
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
                <h4>Branch Information: <span>Step 1 - 2</span></h4>
                <div class="form-group">
                  <label>Employee Name <span>*</span></label>
                  <input type="text" name="name" placeholder="Employee Name" class="form-control required" />
                </div>
                <div class="form-group">
                  <label>Employee Branch <span>*</span></label>
                  <select class="form-control" name="branchId">
                    <c:forEach items="${active_branches}" var="branch">
                      <option value="${branch.id}">${branch.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <div class="form-group">
                  <label>Designation</label>
                  <select class="form-control" name="role">

                    <c:forEach items="${employee_roles}" var="role">
                      <option value="${role}">${role}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="form-group">
                  <label>Salary <span>*</span></label>
                  <input type="text" name="salary" placeholder="Salary " class="form-control" />
                </div>

                <div class="form-group">
                  <label>Gender : </label>
                  <input type="radio" name="Gender" value="Male" checked="checked" />
                  Male
                  <input type="radio" name="Gender" value="Female" /> Female
                </div>

                <div class="form-group col-md-5 col-xs-5">
                  <label>Date of Birth<span> *</span></label>
                  <input type="date" name="date_of_birth" placeholder="Branch Inougration Date " class="form-control" />
                </div>



                <div class="form-group">
                  <label>Phone: <span>*</span></label>
                  <input type="text" name="personal_contact" placeholder="Phone" class="form-control required" />
                </div>

                <div class="form-group">
                  <label>Email <span>*</span></label>
                  <input type="text" name="email" placeholder="E-mail " class="form-control" />
                </div>

                <div class="form-group">
                  <label>Password <span>*</span></label>
                  <input type="text" name="password" placeholder="Password " class="form-control" />
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
                  <input type="text" name="landmark" placeholder="Landmark" class="form-control required" />
                </div>
                <div class="form-group">
                  <label>Street1: <span>*</span></label>
                  <input type="text" name="street" placeholder="Street1" class="form-control required" />
                </div>
                <div class="form-group">
                  <label>Town: <span>*</span></label>
                  <input type="text" name="town" placeholder="Town" class="form-control required" />
                </div>
                <div class="form-group">
                  <label>Post Office <span>*</span></label>
                  <input type="text" name="postOffice" placeholder="Post Office " class="form-control required" />
                </div>


                <div class="form-group">
                  <label>Pin Code: <span>*</span></label>
                  <input type="text" name="pin" placeholder="Pin Code" class="form-control required" max=6; min=6; />
                </div>


                <div class="form-group">
                  <label>District: <span>*</span></label>
                  <input type="text" name="district" placeholder="District" class="form-control required" />
                </div>


                <div class="form-group">
                  <label>City: <span>*</span></label>
                  <input type="text" name="city" placeholder="City" class="form-control required" />
                </div>
                <div class="form-group">
                  <label>State: <span>*</span></label>
                  <input type="text" name="state" placeholder="State" class="form-control required" />
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
    <script type="text/javascript" src="/superuser/js/add-branch.js"></script>
    <script type="text/javascript">
      // image uploader scripts
    </script>
  </body>

  </html>