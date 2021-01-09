function fetchDeliveryAddressFromServer(orderId) {
    const modal = document.querySelector("#addressModal");
    modal.querySelectorAll('input').forEach(item => {
        if (item.type === "hidden") return;
        if (item.hidden === true) return;
        item.value = "";
    });

    modal.querySelector('input[name="orderId"]').value = orderId;

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/secured/order/" + orderId + "/delivery/address";
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

function changeOrderDescription(order_id) {
    const description = document.getElementById("updatableOrderDescription" + order_id).value;

    var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
    var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");


    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/admin/order/" + order_id + "/description";
    xhr.open('PUT', url, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log("Updated");
            $('#orderDescriptionDialog' + order_id).modal('hide')

        } else if (this.readyState === 4) {
            alert("Something went wrong...! Please try again...");
        }
    }

    console.log(description);
    xhr.send(description);

}


// Show product details
function orderProductView(order_id) {
    const element = document.getElementById('orderProductViewDetails');

    // Clean previous stuff
    element.querySelectorAll('#paperListRow tr').forEach(tr => tr.remove());

    console.log("loading...");
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/secured/order/" + order_id + "/product/details";

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