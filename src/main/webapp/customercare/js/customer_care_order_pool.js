var stompClient = null;
var connectedStatus = false;
var employee_id = null;

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");


$('.table tbody tr td').on('click', '.accept-order-icon', function () {

    var orderId = $(this).parent().siblings('.orderId').data();

    console.log(orderId);
    console.log(orderId.orderid);

    $(this).hide();
    // $(this).siblings('.de-act').show();
    $(this).parent().prevAll().css("background-color", "gray").focus();
    $(this).parent().next().hide();


    var data = { "customerCareId": employee_id, "orderId": orderId.orderid };

    publishDataToServer(data);


});

function subscribeDataFromServer(data) {

    console.log(data);
    var parsedData = JSON.parse(data.body);
    var row = document.getElementById("orderRow" + parsedData.orderId);
    console.log(row);
    row.remove();

}

function publishDataToServer(data) {
    console.log("data: ", data);
    stompClient.send("/app/customer-care/publish/order-pool", {}, JSON.stringify(data));
}


// Connect to the web socket after the window is loaded
window.onload = function (event) {
    console.log("connecting");
    connect();

};

// Connect to the webSocket 
function connect() {
    console.log("check 1");
    var socket = new SockJS('/customer-care/order-pool-websocket-connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        employee_id = document.getElementById('employee_id_hidden').value;
        console.log("check 2");
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/customer-care/subscribe/order-pool', function (data) {
            // console.log("received a message back", greeting.body, typeof (greeting.body));
            // console.log(greeting);
            // let data = JSON.parse(data.body);
            // console.log(data);

            subscribeDataFromServer(data);

            // showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function setConnected(connected) {
    connectedStatus = connected;
}
