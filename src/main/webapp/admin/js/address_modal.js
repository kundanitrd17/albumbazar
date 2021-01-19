
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


