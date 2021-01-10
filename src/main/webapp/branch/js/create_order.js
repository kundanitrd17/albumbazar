document.getElementById("CreateOrderForm").addEventListener('submit', createNewOrder);

function createNewOrder(e) {
    e.preventDefault();
    const createdOrderListElement = document.getElementById('OrderListSection');

    const orderForm = document.getElementById("CreateOrderForm");
    let orderFormData = new FormData(orderForm);

    console.log(orderForm);
    console.log(orderFormData);

    var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
    var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");


    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/secured/order/create";
    xhr.open('POST', url, true);
    // xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    // xhr.setRequestHeader('Content-type', 'multipart/form-data');
    // xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

            const data = JSON.parse(this.response);
            console.log(data);

            const order_id = data["id"];

            // New order Element 
            let element = `
            <div class="container-fluid">
                    <div class="input-group mb-3" id="OrderBoard${order_id}">
                        <input type="text" class="form-control" placeholder="Recipient's username"
                            aria-label="Recipient's username" aria-describedby="basic-addon2" disabled>
                    </div>
                    <form class="images-upload-form input-group" id="fileUploadForm${order_id}"
                        enctype="multipart/form-data">
                        <input name="order_selected" type="text" value="${order_id}"  hidden>
                        <input type="file" class="form-control" name="files" aria-describedby="inputGroupFileAddon04"
                            aria-label="Upload" multiple>
                        <button class="btn btn-outline-secondary" type="submit">Button</button>
                    </form>
                    <div class="progress" id="progressBar${order_id}" hidden>
                        <div class="progress-bar" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                        </div>
                    </div>
                    <hr>
                </div>
            `;
            createdOrderListElement.innerHTML += element;

            document.querySelector("#fileUploadForm" + order_id).addEventListener('submit', uploadImagesToServer);


            $('#myModal .btn-secondary').click();

        }
    }


    xhr.send(orderFormData);

}



function uploadImagesToServer(e) {
    e.preventDefault();
    const order_id = this.querySelector("input[name='order_selected']").value;
    console.log(order_id);

    console.log(order_id);
    if (order_id == null) {
        alert("Try again later");
        return false;
    }

    let fileUploadForm = document.getElementById("fileUploadForm" + order_id);
    let files = fileUploadForm.querySelector("input[type='file']").files;
    console.log(files);
    if (files.length <= 0) {
        alert("Select files first...!")
        return false;
    }

    var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
    var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");



    // Preparing to send data
    const uploadForm = new FormData(fileUploadForm);

    // Create folder API call
    var xhr_outer = new XMLHttpRequest();

    // url for creating folder
    var outer_url = 'http://localhost:8080/api/secured/order/' + order_id + '/create-folder';

    xhr_outer.open("GET", outer_url, true);

    xhr_outer.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            // Make API call
            console.log("Folder Created....!");
            var xhr = new XMLHttpRequest();

            var url = 'http://localhost:8080/api/secured/order/' + order_id + '/photos';
            xhr.open("POST", url, true);
            xhr.setRequestHeader(header, token);


            // Progress bar
            const progressbar = document.getElementById('progressBar' + order_id);
            progressbar.hidden = false;
            fileUploadForm.hidden = true;
            xhr.upload.addEventListener('progress', function (e) {
                let percent_complete = (e.loaded / e.total) * 100;
                progressbar.querySelector('.progress-bar').style.width = `${percent_complete}%`;
                console.log(percent_complete);
            });

            xhr.onreadystatechange = function () { // Call a function when the state changes.
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                    alert("Upload completed");
                    console.log(this.response);
                }
            }
            xhr.send(uploadForm)
        }
    }

    xhr_outer.send(null);

    return false;
}


