<div class="modal fade" id="RegisterCustomerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
    aria-hidden="true">

    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content ">
            <!-- Header -->
            <div class="modal-header">
                <h3 class="mx-auto text-center font-weight-light my-4">Create Account</h3>
            </div>

            <!-- Body -->
            <div class="modal-body">
                <form action="/register" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="small mb-1" for="regInputFirstName">First Name</label>
                                <input name="firstName" class="form-control py-4" id="regInputFirstName" type="text"
                                    placeholder="Enter first name" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="small mb-1" for="regInputLastName">Last Name</label>
                                <input name="lastName" class="form-control py-4" id="regInputLastName" type="text"
                                    placeholder="Enter last name" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="small mb-1" for="regInputEmailAddress">Email</label>
                        <input name="email" class="form-control py-4" id="regInputEmailAddress" type="email"
                            aria-describedby="emailHelp" placeholder="Enter email address" />
                    </div>
                    <div class="form-group">
                        <label class="small mb-1" for="regInputContactNumber">Mobile</label>
                        <input name="contactNo" class="form-control py-4" id="regInputContactNumber" type="text"
                            placeholder="Enter mobile no." />
                    </div>

                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="small mb-1" for="regInputPassword">Password</label>
                                <input name="password" class="form-control py-4" id="regInputPassword" type="password"
                                    placeholder="Enter password" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="small mb-1" for="regInputConfirmPassword">Confirm
                                    Password</label>
                                <input name="rePassword" class="form-control py-4" id="regInputConfirmPassword"
                                    type="password" placeholder="Confirm password" />
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="small mb-1" for="regInputReferralCode">Referral Code</label>
                        <input name="referralCode" value="" class="form-control py-4" id="regInputReferralCode"
                            type="text" placeholder="Enter Referral Code (if any)" />
                    </div>
                    <div class="form-group mt-4 mb-0"><button class="btn btn-primary btn-block"
                            type="submit">Register</button></div>
                </form>

                <!-- Footer -->
                <div class="modal-footer">
                    <div class="mx-auto small"><a data-dismiss="modal" href="#" data-toggle="modal"
                            data-target="#LoginCustomerModal">Have an account? Go to login</a>

                    </div>
                </div>
            </div>



        </div>
    </div>


</div>