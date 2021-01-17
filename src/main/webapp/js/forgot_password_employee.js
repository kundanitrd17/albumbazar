
var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");

var resetForm = document.querySelector('form');

document.addEventListener('DOMContentLoaded', function() {

    document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
    token = document.querySelector("meta[name='_csrf']").content;

    resetForm = document.querySelector('form');

    document.getElementById('OTPSendButton').addEventListener('click', sendOTP);

});


function sendOTP(e) {
    e.preventDefault();
    
    const inputEmail = document.getElementById('inputEmailAddress').value;
    // Valid email value
    if (inputEmail == null || inputEmail.length < 0) {
        alert("Enter Email valid address!");
        return false;
    }
    let flag = false;
    for (let index = 0; index < inputEmail.length; index++) {
        if (inputEmail[index] === '@') {
            flag = true;
            break;
        }
    }
    if (!flag) {
        alert("Enter Email valid address!");
        return false;
    }

    // Send OTP
    console.log(inputEmail);

    const xhr = new XMLHttpRequest();
    const url = "/api/reset-code/generate/employee";

    xhr.open('POST', url, true);

    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            alert("OTP sent");
        } else if( this.readyState === 4)  {
            alert("Unable to send OTP");
        }
    }


    xhr.send(inputEmail);

    // After a success response
    // document.querySelector('main').innerHTML = `<div class="alert alert-danger" role="alert">OTP Sent</div>` + document.querySelector('body').innerHTML;


}