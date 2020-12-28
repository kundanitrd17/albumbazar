

var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");




document.addEventListener('DOMContentLoaded', (event) => {
    header = $("meta[name='_csrf_header']").attr("content");
    token = $("meta[name='_csrf']").attr("content");

    document.getElementById("customerDetailUpdateForm").addEventListener('submit', updateProfileInfo);
})

function updateProfileInfo(event) {
    event.preventDefault();
    console.log("form submitted!");

    const customerDetailInfo = {};
    let customerId = document.getElementById('CustomerId').value;
    let customerName = document.getElementById('CustomerName').value;
    let customerEmail = document.getElementById('CustomerEmail').value;
    let customerDOB = document.getElementById('CustomerDOB').value;
    let customerAlternateContact = document.getElementById('CustomerAlternateContact').value;
    let customerGender;
    document.querySelectorAll(".gender-checkbox-input").forEach(each => {
        if (each.checked) { customerGender = each; }
    });
    if (customerGender != null)
        console.log(customerGender.value);

    customerDetailInfo["id"] = customerId;
    customerDetailInfo["name"] = customerName;
    customerDetailInfo["email"] = customerEmail;

    console.log(customerDetailInfo);

    var URL = 'http://localhost:8080/api/secured/customer/info';
    var xhr = new XMLHttpRequest();
    xhr.open("PUT", URL, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            console.log("Success: ");
            console.log(this.response);
        }
    }
    xhr.send(JSON.stringify(customerDetailInfo))


}