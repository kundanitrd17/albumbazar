
function loadDeliveryAddressCards() {

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/secured/customer/address";
    xhr.open('GET', url, true);

    xhr.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            let addresses = JSON.parse(this.response);
            console.log(addresses);

            // Load all cards into the delivery address modal
            const addressModalBody = document.querySelector('#deliveryAddressSelectorModal .modal-body .card-content');
            addressModalBody.innerHTML = null;

            addresses.forEach(address => {
                console.log(address);
                let addressCard = `
                <div class="card text-white bg-primary mb-3" style="max-width: 18rem;" onclick="selectedDeliveryLocation(${address.id})">
                    <div class="card-header">${address.name}</div>
                    <div class="card-body">
                        <h5 class="card-title">${address.contactNo}</h5>
                        <p class="card-text">${address.landmark}, ${address.line1}, ${address.city}, ${address.pincode}</p>
                    </div>
                </div>
                `;

                addressModalBody.innerHTML += addressCard;
            });



        }

    }


    xhr.send(null);


}

function selectedDeliveryLocation(addressId) {

    document.querySelector("#myModal #selectedDeliveryAddressId").value = addressId;

    //click prev

    $('#deliveryAddressSelectorModal').modal('hide');
    $('#myModal').modal('show');



}