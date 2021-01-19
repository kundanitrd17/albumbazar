function deleteAddress(address_id) {

    var xhr = new XMLHttpRequest();


    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");


    // Replace 1 with current order id
    var url = '/api/secured/customer/address';
    xhr.open("DELETE", url, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            const modalElement = document.getElementById('AddressCardInfo' + address_id);
            modalElement.style.animationPlayState = 'running';
            modalElement.addEventListener('animationend', () => {
                modalElement.remove();
            });
            console.log(this.response);
        }
    }
    xhr.send(JSON.stringify(address_id));

}