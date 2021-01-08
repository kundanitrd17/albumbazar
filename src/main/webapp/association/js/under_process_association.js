
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