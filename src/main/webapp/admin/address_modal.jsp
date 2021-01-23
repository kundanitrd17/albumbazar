<!-- Address Modal -->
<div class="modal" id="addressModal" tabindex="-1" role="dialog">
    <form action="#">

        <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->

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
                    <input type="hidden" name="orderId" value="">
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
                        <input name="city" type="text" class="form-control" id="exampleInputCity" value=""
                            placeholder="City">
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
                    <button type="button" class="btn btn-primary disabled">Save
                        changes</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </form>
</div>

<!-- End of address modal -->