<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html lang="en">


    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">



        <!--  -->

        <!--    libs for stomp and sockjs-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <!--    end libs for stomp and sockjs-->
    </head>

    <body>

        <div id="root">
            <button id="addCustomer">Add customer</button>

            <div class="users-container" style="display: none;">

                <c:forEach items="${users}" var="user">

                    <div id="customerBody${user.id}">
                        ${user.name}
                        <button onclick="lvlFun('${user.id}')">add</button>
                        <button onclick="deleteUser('${user.id}')">delete</button>

                        <div id="level1_id${user.id}" style="display: none;">
                            ${user.name}
                        </div>
                    </div>



                </c:forEach>





            </div>

        </div>


        <script>

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



        </script>


    </body>

    </html>