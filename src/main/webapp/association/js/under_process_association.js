
function associationWorkCompleted(order_id) {

    const xhr = new XMLHttpRequest();

    const url = "http://localhost:8080/api/secured/association/order/completed";
    xhr.open('PUT', url, true);

    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");

    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);


    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log("done");
            document.getElementById("orderRow" + order_id).remove();
        }
    }

    xhr.send(JSON.stringify(order_id));



}

function fetchAddress(id) {
    const modal = document.querySelector("#AddressModal");
    modal.querySelectorAll('td').forEach(item => {

        item.value = "";
    });

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/secured/address/" + id;
    xhr.open('GET', url, true);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.response);

            const address = JSON.parse(this.response);


            modal.querySelector('#name').innerHTML = address["name"];
            modal.querySelector('#contact').innerHTML = address["contactNo"];
            modal.querySelector('#landmark').innerHTML = address["landmark"];
            modal.querySelector('#line1').innerHTML = address["line1"];
            modal.querySelector('#line2').innerHTML = address["line2"];
            modal.querySelector('#city').innerHTML = address["city"];
            modal.querySelector('#pincode').innerHTML = address["pincode"];
            modal.querySelector('#district').innerHTML = address["district"];
            modal.querySelector('#state').innerHTML = address["state"];


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


