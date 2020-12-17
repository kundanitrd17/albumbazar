<!DOCTYPE html>
<html>

<head>

  <meta name="_csrf" content="${_csrf.token}" />
  <!-- default header name is X-CSRF-TOKEN -->
  <meta name="_csrf_header" content="${_csrf.headerName}" />
  <!-- ... -->


  <title>Hello WebSocket</title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
  <!--    libs for stomp and sockjs-->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  <!--    end libs for stomp and sockjs-->
</head>

<body>

  <div id="main-content" class="container">
    <div class="row">
      <div class="col-md-6">
        <form class="form-inline">
          <div class="form-group">
            <label for="connect">WebSocket connection:</label>
            <button id="connect" class="btn btn-default" type="submit">Connect</button>
            <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
            </button>
          </div>
        </form>
      </div>
      <div class="col-md-6">
        <form class="form-inline">
          <div class="form-group">
            <label for="name">What is your name?</label>
            <input type="text" id="name" class="form-control" placeholder="Your name here...">
          </div>
          <button id="send" class="btn btn-default" type="submit">Send</button>
        </form>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <table id="conversation" class="table table-striped">
          <thead>
            <tr>
              <th>Greetings</th>
            </tr>
          </thead>
          <tbody id="greetings">
          </tbody>
        </table>
      </div>
    </div>
  </div>


  <div>
    <button id="test-click">click</button>
  </div>v



  <script>
    var stompClient = null;


    function setConnected(connected) {
      $("#connect").prop("disabled", connected);
      $("#disconnect").prop("disabled", !connected);
      if (connected) {
        $("#conversation").show();
      }
      else {
        $("#conversation").hide();
      }
      $("#greetings").html("");
    }

    function connect() {
      console.log("check 1");
      var socket = new SockJS('/order-pool-websocket-connect');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        console.log("check 2");
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/customer-care/subscribe/order-pool', function (greeting) {
          // console.log("received a message back", greeting.body, typeof (greeting.body));
          let data = JSON.parse(greeting.body);
          console.log(data);

          showGreeting(JSON.parse(greeting.body));
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

    function sendName() {
      stompClient.send("/app/customer-care/publish/order-pool", {}, JSON.stringify({ 'orderId': 20, "customerCareId": 3 }));
    }

    function showGreeting(message) {
      console.log(message);
      $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }

    document.querySelectorAll("form").forEach(ele => {
      ele.addEventListener("submit", e => {
        e.preventDefault();
      });
    });


    document.getElementById("connect").addEventListener("click", (e) => {
      console.log("connecting");
      connect();

    });
    document.getElementById("disconnect").addEventListener("click", (e) => {
      disconnect();
    });
    document.getElementById("send").addEventListener("click", (e) => {
      sendName();
    });

  </script>




  <script>
    document.getElementById('test-click').addEventListener('click', function () {
      var token = $("meta[name='_csrf']").attr("content");
      var header = $("meta[name='_csrf_header']").attr("content");

      var xhr = new XMLHttpRequest();
      var url = 'http://localhost:8080/emp';
      xhr.open("POST", url, true);
      xhr.setRequestHeader('Content-type', 'application/json');
      // xhr.setRequestHeader(header, token);

      xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
          // console.log(typeof (JSON.parse(this.response)));
          console.log(this.response);

        }
      }
      xhr.send(JSON.stringify(892))
    })
  </script>













  <script src='http://code.jquery.com/jquery-latest.js'></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
</body>

</html>