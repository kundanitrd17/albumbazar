

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