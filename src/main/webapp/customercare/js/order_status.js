

document.querySelectorAll(".orderStatusDropDown").forEach(item => {
    item.addEventListener('change', function () {

        // Get order id
        var orderId = $(this).parent().siblings('.orderId').data().orderId;
        console.log(orderId);

        // Get new Order status
        var newOrderStatus = this.value;
        console.log(newOrderStatus);

        var data = { "id": orderId, "status": newOrderStatus };

        console.log(data);
        // Make API call
        var xhr = new XMLHttpRequest();
        var url = 'http://localhost:8080/api/secured/order/status';
        xhr.open("POST", url, true);
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.setRequestHeader(header, token);

        xhr.onreadystatechange = function () { // Call a function when the state changes.
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                // console.log(typeof (JSON.parse(this.response)));
                console.log(this.response);
            }
        }
        xhr.send(JSON.stringify(data))




    });
});

