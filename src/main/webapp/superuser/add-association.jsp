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
    <link rel="stylesheet" type="text/css" href="css/form.css" />
    <style type="text/css"></style>
  </head>
  <body>
    <section class="form-box">
      <div class="container">
        <div class="row">

           <div class="col-md-3 col-lg-3 col-xl-3"></div>

          <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 form-wizard">
            <!-- Form Wizard -->
            <form role="form" action="add-association" method="post">
              <input
                type="hidden"
                name="${_csrf.parameterName}"
                value="${_csrf.token}"
              />

              <h3>Sign Up Association Account</h3>
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
                  <p>Association Detail</p>
                </div>
                <!-- Step 1 -->

                <!-- Step 2 -->
           
                <!-- Step 2 -->
              </div>
              <!-- Form progress -->

              <!-- Form Step 1 -->
              <fieldset>
                <h4>Branch Information: <span>Step 1 - 2</span></h4>
                <div class="form-group">
                  <label>Association Name <span>*</span></label>
                  <input
                    type="text"
                    name="name"
                    placeholder="Enter Asociation Name"
                    class="form-control required"
                  />
                </div>
                <div class="form-group">
                  <label>Contact 1 <span>*</span></label>
                  <input
                    type="text"
                    name="contact1"
                    placeholder="Enter Branch Code "
                    class="form-control required"
                  />
                  <div class="form-group">
                    <label>Contact 2  <span>*</span></label>
                    <input
                      type="text"
                      name="contact2"
                      placeholder="Phone"
                      class="form-control required"
                    />
                  </div>
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


                <div class="form-group">
                  <label>Re-Password <span>*</span></label>
                  <input
                    type="password"
                    name="rePassword"
                    placeholder="Password "
                    class="form-control required"
                  />
                </div>

                <div class="container-fluid">
                  <div class="row form-inline">
                    <div class="form-group col-md-5 col-xs-5">
                      <label>Contract Start Date </label>
                      <input
                        type="date"
                        name="startDate"
                        placeholder="Contract Start Date "
                        class="form-control"
                      />
                    </div>
                  </div>
                </div>
                <div class="form-wizard-buttons">
                  <button type="submit" class="btn btn-submit">Submit</button>
                </div>
              </fieldset>
              <!-- Form Step 1 -->

              <!-- Form Step 2 -->
             
              <!-- Form Step 2 -->
            </form>
            <!-- Form Wizard -->
          </div>
        </div>
      </div>
    </section>
    <script type="text/javascript" src="js/add-branch.js"></script>
    <script type="text/javascript">
      // image uploader scripts
    </script>
  </body>
</html>
