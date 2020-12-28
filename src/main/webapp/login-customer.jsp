<div class="modal" id="LoginCustomerModal" tabindex="-1" role="dialog">
    <form action="/customer/address/info" method="POST">
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
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <!-- <input type="text" value="${address.id}" name="id" hidden> -->
                    <div class="form-group">
                        <label for="exampleInputName${address.id}">Name</label>
                        <input name="name" type="name" class="form-control" id="exampleInputName${address.id}"
                            value="${address.name}" placeholder="name">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputMobile${address.id}">Mobile</label>
                        <input name="contactNo" type="text" class="form-control" id="exampleInputMobile${address.id}"
                            value="${address.contactNo}" placeholder="mobile">
                    </div>
                    <hr>
                    <div class="form-group">
                        <label for="exampleInputLandmark${address.id}">Landmark</label>
                        <input name="landmark" type="text" class="form-control" id="exampleInputLandmark${address.id}"
                            value="${address.landmark}" placeholder="Landmark">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputAddress1${address.id}">Address</label>
                        <input name="line1" type="text" class="form-control" id="exampleInputAddress1${address.id}"
                            value="${address.line1}" placeholder="Address line1">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputAddress2${address.id}">Address</label>
                        <input name="line2" type="text" class="form-control" id="exampleInputAddress2${address.id}"
                            value="${address.line2}" placeholder="Address line2">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputCity${address.id}">City</label>
                        <input name="city" type="text" class="form-control" id="exampleInputCity${address.id}"
                            value="${address.city}" placeholder="City">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputDistrict${address.id}">District</label>
                        <input name="district" type="text" class="form-control" id="exampleInputDistrict${address.id}"
                            value="${address.district}" placeholder="District">
                    </div>

                    <div class="row">
                        <div class="col">
                            <label for="exampleInputState${address.id}">State</label>
                            <input name="state" type="text" class="form-control" value="${address.state}"
                                placeholder="State">
                        </div>
                        <div class="col">
                            <label for="exampleInputPIN${address.id}">Pin-Code</label>
                            <input name="pincode" type="text" class="form-control" value="${address.pincode}"
                                placeholder="PIN">
                        </div>
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