
var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");


document.addEventListener('DOMContentLoaded', function () {
    document.querySelector("#addressModal").querySelectorAll('input').forEach(element => {
        element.disabled = true;
    });

    header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
    token = document.querySelector("meta[name='_csrf']").content;//.attr("content");



    $('#branchSelectDropdown').on('change', fetchBranchRelatedOrders);

});


// Function to get branch related orders

function fetchBranchRelatedOrders() {


    this.submit();


    // const branchCode = this.querySelector('select').value;

    // const xhr = new XMLHttpRequest();
    // const url = "/api/superuser/order/branch?code=" + "bid";
    // xhr.open("GET", url, true);
    // xhr.onreadystatechange = function () {

    //     if (this.DONE) {
    //         console.log(this.response);

    //         const order_details = JSON.parse(this.response);

    //         order_details.forEach(order_detail => {

    //             let orderRow = `
    //         <tr>
    //                   <td>${order_detail.id}</td>
    //                   <td class="customerId" data-order-id="${order_detail.customer.id}">
    //                     <button class="btn" onclick="fetchCustomerDetails('${order_detail.customer.id}')"
    //                       data-toggle="modal" data-target="#customerDetailModal">${order_detail.customer.id}</button>
    //                   </td>
    //                   <td>${order_detail.productName}</td>
    //                   <td>${order_detail.orderTime}</td>
    //                   <td>
    //                     <a href="" data-toggle="modal" data-target="#orderDescriptionDialog${order_detail.id}">
    //                       click
    //                     </a>
    //                     <div class="modal" tabindex="-1" role="dialog" id="orderDescriptionDialog${order_detail.id}">
    //                       <div class="modal-dialog" role="document">
    //                         <div class="modal-content">
    //                           <div class="modal-header">
    //                             <h5 class="modal-title">Order Description</h5>
    //                             <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    //                               <span aria-hidden="true">&times;</span>
    //                             </button>
    //                           </div>
    //                           <div class="modal-body">
    //                             <div class="form-group">
    //                               <label>Order Description</label>
    //                               <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"
    //                                 disabled>${order_detail.description}</textarea>
    //                             </div>
    //                           </div>
    //                           <div class="modal-footer">
    //                             <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
    //                           </div>
    //                         </div>
    //                       </div>
    //                     </div>
    //                   </td>
    //                   <td><a href="" data-toggle="modal" data-target="#addressModal" id="link_address"
    //                       onclick="fetchDeliveryAddressFromServer('${order_detail.id}')">Address
    //                       Id</a></td>
    //                   <td>${order_detail.orientation}</td>
    //                   <td><a type="button" href="" id="link_adminId" data-toggle="modal"
    //                       data-target="#orderProductViewDetails" onclick="orderProductView('${order_detail.id}')">View
    //                       Product</a></td>
    //                   <td><a target="_blank" href="${order_detail.photoFolderGoogleDriveLink}">Images</a></td>
    //                   <td><button class="btn btn-success">${order_detail.orderStatus}</button></td>

    //                 </tr>
    //         `;

    //             console.log("done");

    //         });


    //     }

    // }
    // xhr.send(null)



}

// End of branch related functions



function fetchDeliveryAddressFromServer(orderId) {
    const modal = document.querySelector("#addressModal");
    modal.querySelectorAll('input').forEach(item => {
        if (item.type === "hidden") return;
        if (item.hidden === true) return;
        item.value = "";
    });

    modal.querySelector('input[name="orderId"]').value = orderId;

    const xhr = new XMLHttpRequest();
    const url = "/api/secured/order/" + orderId + "/delivery/address";
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


        }
    }

    xhr.send(null);
}



// Show product details
function orderProductView(order_id) {
    const element = document.getElementById('orderProductViewDetails');

    // Clean previous stuff
    element.querySelectorAll('#paperListRow tr').forEach(tr => tr.remove());

    console.log("loading...");
    const xhr = new XMLHttpRequest();
    const url = "/api/secured/order/" + order_id + "/product/details";

    xhr.open('GET', url, true);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(this.response);
            console.log(data);


            element.querySelector("#productName").innerHTML = data["product_name"];
            element.querySelector("#productSize").innerHTML = data["product_size"];
            element.querySelector("#coverName").innerHTML = data["cover"]["coverName"];
            element.querySelector("#coverSize").innerHTML = data["cover"]["coverSize"];

            const paperList = data["sheet_detail_list"];

            paperList.forEach(paper => {

                console.log(paper);
                console.log(paper["sheets"]);
                var newRow = document.createElement('tr');
                let pageQuality = document.createElement('td');
                pageQuality.className = "pageQuality";
                pageQuality.innerHTML = paper["paperName"];
                let pageSize = document.createElement('td');
                pageSize.className = "pageSize";
                pageSize.innerHTML = paper["paperSize"];
                let sheets = document.createElement('td');
                sheets.className = "numberOfSheets";
                sheets.innerHTML = paper["sheets"];

                newRow.appendChild(pageQuality);
                newRow.appendChild(pageSize);
                newRow.appendChild(sheets);


                element.querySelector('#paperListRow').appendChild(newRow);

            });


        }

    }


    xhr.send(null);

}



function fetchCustomerDetails(id) {
    var customerDetailModal = document.getElementById('customerDetailModal');

    const xhr = new XMLHttpRequest();
    const url = "/api/secured/employee/customer/info/" + id;
    xhr.open('GET', url, true);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.DONE) {
            const data = JSON.parse(this.response);
            // console.log(data);
            customerDetailModal.querySelector("#name").innerHTML = data["name"];
            customerDetailModal.querySelector("#contact").innerHTML = data["contactNo"];
            customerDetailModal.querySelector("#email").innerHTML = data["email"];

        }
    }

    xhr.send(null)

}

