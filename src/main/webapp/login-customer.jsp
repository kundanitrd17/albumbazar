<!-- Login Page Modal -->

<style>
    .modal-header {
        padding: 0.75rem 1.25rem;
        margin-bottom: 0;
        background-color: rgba(0, 0, 0, 0.03);
        border-bottom: 1px solid rgba(0, 0, 0, 0.125);
    }

    .modal-header:first-child {
        border-radius: calc(0.25rem - 1px) calc(0.25rem - 1px) 0 0;
    }


    .modal-footer,
    .modal-header {
        padding: 0.75rem 1.25rem;
        background-color: rgba(0, 0, 0, 0.03);
        border-top: 1px solid rgba(0, 0, 0, 0.125);
    }
</style>

<div class="modal fade" id="LoginCustomerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
    aria-hidden="true">

    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content ">
            <!-- Header -->
            <div class="modal-header">
                <h3 class="mx-auto text-center font-weight-light my-4">Login</h3>
            </div>

            <!-- Body -->
            <div class="modal-body">
                <form action="/customer/login" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="form-group">
                        <label class="small mb-1" for="inputEmailAddress">Email</label>
                        <input name="username" class="form-control py-4" id="inputEmailAddress" type="email"
                            aria-describedby="emailHelp" placeholder="Enter email address" />
                    </div>
                    <div class="form-group">
                        <label class="small mb-1" for="inputPassword">Password</label>
                        <input name="password" class="form-control py-4" id="inputPassword" type="password"
                            aria-describedby="passwordHelp" placeholder="Enter password" />
                    </div>
                    <div class="form-group mt-4 mb-0"><button class="btn btn-primary btn-block" type="submit"">Login</button>
                    </div>
                </form>
            </div>

            <!-- Footer -->
            <div class=" modal-footer">
                            <div class="mx-auto small"><a data-dismiss="modal" href="#" data-toggle="modal"
                                    data-target="#RegisterCustomerModal">Don't have an account? Register here</a>
                            </div>
                    </div>

            </div>
        </div>


    </div>

    <!-- End of Login Page Modal -->