document.querySelector(".upload__button").addEventListener('click', openFileDialog);

var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");




var selected_files_boolean = false;
var currentOrderId = document.getElementById('current_order_id').value;


//  drop files
function dropHandler(ev) {
    console.log('File(s) dropped');

    // Prevent default behavior (Prevent file from being opened)
    ev.preventDefault();

    // Create an Element to hold the files 
    var inputElement = document.getElementById("files_to_upload_input_element");



    if (ev.dataTransfer.items) {
        // Use DataTransferItemList interface to access the file(s)
        for (var i = 0; i < ev.dataTransfer.items.length; i++) {
            // If dropped items aren't files, reject them
            if (ev.dataTransfer.items[i].kind === 'file') {
                var file = ev.dataTransfer.items[i].getAsFile();
                console.log('... file[' + i + '].name = ' + file.name);
            }
            inputElement.files = ev.dataTransfer.files;
        }

    } else {
        // Use DataTransfer interface to access the file(s)
        for (var i = 0; i < ev.dataTransfer.files.length; i++) {
            console.log('... file[' + i + '].name = ' + ev.dataTransfer.files[i].name);
        }
        inputElement.files = ev.dataTransfer.files;
    }
    var selectedForm = document.getElementById("selectedFilesForUpload");
    selectedForm.appendChild(inputElement);
    console.log(inputElement);
    selected_files_boolean = true;
}

// Prevent browser default drag
function dragOverHandler(ev) {
    console.log('File(s) in drop zone');

    // Prevent default behavior (Prevent file from being opened)
    ev.preventDefault();
}


function openFileDialog(accept) {  // this function must be called from  a user
    // activation event (ie an onclick event)

    // Create an input element
    var inputElement = document.getElementById("files_to_upload_input_element");
    // Set accept to the file types you want the user to select. 
    // Include both the file extension and the mime type
    inputElement.accept = accept;

    // set onchange event to call selectedFiles when user has selected file
    inputElement.addEventListener("change", () => {
        console.log("selected files to upload");
        // Append the selected files and keep them hidden

        selected_files_boolean = true;
        console.log(inputElement);

    });

    // dispatch a click event to open the file dialog
    inputElement.dispatchEvent(new MouseEvent("click"));
}


document.getElementById('upload_to_server_btn').addEventListener('click', uploadFilesToServer);

function uploadFilesToServer() {
    if (!selected_files_boolean) return;
    var form = document.getElementById('selectedFilesForUpload');
    console.log(form);
    var formData = new FormData(form);
    console.log(formData);

    const currentOrderId = document.getElementById('current_order_id').value;
    console.log(currentOrderId);


    // Create folder API call
    var xhr_outer = new XMLHttpRequest();

    // url for creating folder
    var outer_url = 'http://localhost:8080/api/secured/order/' + currentOrderId + '/create-folder';

    xhr_outer.open("GET", outer_url, true);

    xhr_outer.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {

            // Make API call
            var xhr = new XMLHttpRequest();

            // Replace 1 with current order id
            var url = 'http://localhost:8080/api/secured/order/' + currentOrderId + '/photos';
            xhr.open("POST", url, true);
            // xhr.setRequestHeader('Content-type', 'application/json');
            // xhr.setRequestHeader(header, token);

            // Progress bar
            xhr.upload.addEventListener('progress', function (e) {
                let percent_complete = (e.loaded / e.total) * 100;

                // percentage of upload completed
                console.log(percent_complete);
            });

            xhr.onreadystatechange = function () { // Call a function when the state changes.
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {

                    var btn_upload = document.getElementById('upload_to_server_btn');
                    btn_upload.innerHTML = "DONE✔";
                    btn_upload.disabled = true;


                    console.log(this.response);
                }
            }
            xhr.send(formData)

        }
    }


    xhr_outer.send(null);

}

// payment process js*******************/********************** */

var generate_bill_btn = document.querySelector(".payment .generate-bill");
generate_bill_btn.addEventListener('click', generateBillForOrder);

function generateBillForOrder() {
    const currentOrderId = document.getElementById('current_order_id').value;
    console.log("generating");

    var xhr = new XMLHttpRequest();

    var url = 'http://localhost:8080/api/secured/bill/generate';
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);


    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {

            console.log(this.response);
            var res_data = JSON.parse(this.response);
            let orderId = res_data["razorpayOrderId"];
            console.log(orderId);
            options["order_id"] = orderId;


            document.querySelector(".payment").hidden = true;
            document.querySelector('.razorpay-payment-box-container').hidden = false;

            var alert_message = document.querySelector(".order_payment_info");
            alert_message.innerHTML += '<div class="alert alert-secondary" role="alert">' + + '</div>';
            // <div class="alert alert-secondary" role="alert">                        
            //             </div>

            options.amount = res_data.applicationFee;
            options.key = res_data.secretKey;
            options.name = res_data.merchantName;
            options.prefill.name = res_data.customerName;
            options.prefill.contact = res_data.customerContact;
            options.prefill.email = res_data.customerEmail;
            options.theme.color = res_data.theme;
            options.notes.address = res_data.notes;
            options.image = res_data.imageURL;
            options.description = res_data.purchaseDescription;


            var alert_message = document.querySelector(".order_payment_info");
            alert_message.innerHTML = '<div class="alert alert-info" role="alert"> Payment Info </div>';
            if (options.prefill.name != null && options.prefill.name.length > 0)
                alert_message.innerHTML += '<div class="alert alert-secondary" role="alert"> name: ' + options.prefill.name + '</div>';
            if (options.prefill.contact != null && options.prefill.contact.length > 0)
                alert_message.innerHTML += '<div class="alert alert-secondary" role="alert"> email: ' + options.prefill.email + '</div>';
            if (options.prefill.contact != null && options.prefill.contact.length > 0)
                alert_message.innerHTML += '<div class="alert alert-secondary" role="alert"> contact: ' + options.prefill.contact + '</div>';
            if (options.amount != null && options.amount.length > 0)
                alert_message.innerHTML += '<div class="alert alert-secondary" role="alert"> amount: ' + options.amount + '</div>';

        }
    }
    console.log(currentOrderId);
    xhr.send(JSON.stringify(currentOrderId));

}


// Json data
const options = {
    "key": "rzp_test_oO5Nlz03qtxInq", // Enter the Key ID generated from the Dashboard
    "amount": "50000", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
    "currency": "INR",
    "name": "Acme Corp",
    "description": "Test Transaction",
    "image": "https://example.com/your_logo",
    "order_id": "order_GGTNTu7VscYhhZ", //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
    "handler": send_payment_info_to_server,
    "prefill": {
        "name": "Gaurav Kumar",
        "email": "gaurav.kumar@example.com",
        "contact": "9999999999"
    },
    "notes": {
        "address": "Razorpay Corporate Office"
    },
    "theme": {
        "color": "#3399cc"
    }
};


// Initiate payment
const rz_pay_btn = document.getElementById('rzp-button');
rz_pay_btn.onclick = function (e) {
    const currentOrderId = document.getElementById('current_order_id').value;
    console.log("js clicked pay");
    var rzp1 = new Razorpay(options);
    rzp1.on('payment.failed', function (response) {


        alert("Payment failed... Please Try again later");

        // alert(response.error.code);
        // alert(response.error.description);
        // alert(response.error.source);
        // alert(response.error.step);
        // alert(response.error.reason);
        // alert(response.error.metadata.order_id);
        // alert(response.error.metadata.payment_id);
    });

    console.log(options);
    rzp1.open();

    e.preventDefault();
}

// send_payment_info_to_server

function send_payment_info_to_server(response) {
    console.log(response);
    // console.log(response.razorpay_payment_id);
    // console.log(response.razorpay_order_id);
    // console.log(response.razorpay_signature);

    // var rz_payment_id = response.razorpay_payment_id;
    // var rz_order_id = response.razorpay_order_id;
    // var rz_signature = response.razorpay_signature;


    var xhr = new XMLHttpRequest();

    var url = 'http://localhost:8080/api/secured/bill/paid';
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);


    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {

            console.log("successfully paid the amount");
            rz_pay_btn.innerHTML = "Paid✔";
            rz_pay_btn.style.backgroundColor = "green";
            rz_pay_btn.disabled = true;
            rz_pay_btn.style.opacity = 0.5;

        }
    }
    xhr.send(JSON.stringify(response));




}