function forwardOrderToAssociation(order_id) {
    const conf = confirm("Confirm Forward to Association");
    if (conf == null || !conf) {
        return false;
    }

    const xhr = new XMLHttpRequest();

    const url = "/api/secured/order/forward/association";

    xhr.open("PUT", url, true);

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    xhr.setRequestHeader("Content-type", "application/json");
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const forwardBtn = document.getElementById(
                `forwardOrderBtn${order_id}`
            );
            forwardBtn.disabled = true;
        } else if (this.readyState === 4 && this.status === 405) {
            const alertMsg = document.createElement("div");
            alert("Invalid Deliver");

            alertMsg.className = "alert alert-danger";
            alertMsg.innerHTML = "Delivery Address is Empty...!";

            document.getElementById("root").prepend(alertMsg);
        } else if (this.readyState === 4) {
            alert("Something went Wrong... Please try again");
        }
    };

    xhr.send(JSON.stringify(order_id));
}
