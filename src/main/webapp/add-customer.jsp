<html>
  <head>
    <title>Album Bazaar</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link rel="stylesheet" type="text/css" href="superuser/css/form.css" />
    <style type="text/css"></style>
  </head>
  <body>
    <section class="form-box">
      <div class="container">
        <div class="row">

           <div class="col-md-3 col-lg-3 col-xl-3"></div>

          <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 form-wizard">
            <!-- Form Wizard -->
            <form role="form" action="register" method="POST">
              <input
                type="hidden"
                name="${_csrf.parameterName}"
                value="${_csrf.token}"
              />

              <h3>Sign Up Customer Account</h3>
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
                  <p>Customer Detail</p>
                </div>
                <!-- Step 1 -->

                <!-- Step 2 -->
                <div class="form-wizard-step">
                  <div class="form-wizard-step-icon">
                    <i class="fa fa-location-arrow" aria-hidden="true"></i>
                  </div>
                  <p>Contact</p>
                </div>
                <!-- Step 2 -->
              </div>
              <!-- Form progress -->

              <!-- Form Step 1 -->
              <fieldset>
                <h4>Customer Information: <span>Step 1 - 2</span></h4>
                <div class="form-group">
                  <label> Name <span>*</span></label>
                  <input
                    type="text"
                    name="name"
                    placeholder="Customer Name"
                    class="form-control required"
                  />
                </div>
                   <div class="form-group">
                  <label>Gender : </label>
                    <input
                      type="radio"
                      name="gender"
                      value="option1"
                      checked="checked"
                    />
                    Male
                    <input type="radio" name="Gender" value="option2" /> Female
                </div>

                    <div class="form-group col-md-5 col-xs-5">
                      <label>Date of Birth<span> *</span></label>
                      <input
                        type="date"
                        name="dateOfBirth"
                        placeholder="Date of Birth "
                        class="form-control"
                      />
                    </div>
                 
               

                  <div class="form-group">
                    <label>Phone: <span>*</span></label>
                    <input
                      type="text"
                      name="phone"
                      placeholder="Phone"
                      class="form-control required"
                      max="10"
                    />
                  </div>
              
                <div class="form-group">
                  <label>Email <span>*</span></label>
                  <input
                    type="text"
                    name="email"
                    placeholder="E-mail "
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

                <div class="form-group">
                  <label>Re-Password <span>*</span></label>
                  <input
                    type="password"
                    name="rePassword"
                    placeholder="Re-Password "
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
               <h4>Customer Address : <span>Step 2 - 2</span></h4>

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
                   name="street"
                   placeholder="Street"
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
                   name="postOffice"
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
                   name="district"
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
    <script type="text/javascript" src="superuser/js/add-branch.js"></script>
    <script type="text/javascript">
      // image uploader scripts
    </script>
  </body>
</html>
