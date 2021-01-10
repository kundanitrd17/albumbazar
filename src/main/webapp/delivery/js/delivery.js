

function setDeliveryUnderProcess(order_id) {
    console.log(order_id);

    var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
    var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");


    const xhr = new XMLHttpRequest();
    const url = `http://localhost:8080/api/secured/delivery/order/${order_id}/under-process`;
    xhr.open('PUT', url, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.DONE) {
            alert("Order sent under Processing");
            animationRemoveDelivery(order_id);
        }
    }


    xhr.send(null);

}


function setOrderDelivered(order_id) {
    console.log(order_id);
    var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
    var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");


    const xhr = new XMLHttpRequest();
    const url = `http://localhost:8080/api/secured/delivery/order/${order_id}/delivered`;
    xhr.open('PUT', url, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.DONE) {
            alert("Order sent under delivered");
            animationRemoveDelivery(order_id);
        }
    }

    xhr.send(null);

}



function animationRemoveDelivery(order_id) {


    let row = document.getElementById(`deliveryRow${order_id}`);

    console.log(order_id, row);


    row.style.animationPlayState = 'running';
    row.addEventListener('animationend', () => {
        row.remove();
    });

}