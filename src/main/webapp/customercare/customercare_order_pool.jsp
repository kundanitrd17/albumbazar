<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>

<html>

<head>
    <title>Album Bazaar</title>
    <meta charset="utf-8">
    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">



    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <style type="text/css">
        #associationProductViewDetails table tr th {
            text-align: center;
        }

        #associationProductViewDetails table tr th h6 {

            font-size: 13px;
        }

        #associationProductViewDetails table tr:nth-child(odd) {
            background: rgba(226, 226, 226, 1);
            background: -moz-linear-gradient(left, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
            background: -webkit-gradient(left top, right top, color-stop(0%, rgba(226, 226, 226, 1)), color-stop(1%, rgba(209, 209, 209, 1)), color-stop(51%, rgba(133, 128, 133, 1)), color-stop(100%, rgba(254, 254, 254, 1)));
            background: -webkit-linear-gradient(left, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
            background: -o-linear-gradient(left, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
            background: -ms-linear-gradient(left, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
            background: linear-gradient(to right, rgba(226, 226, 226, 1) 0%, rgba(209, 209, 209, 1) 1%, rgba(133, 128, 133, 1) 51%, rgba(254, 254, 254, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e2e2e2', endColorstr='#fefefe', GradientType=1);
            ;
            color: white;
            text-align: center;

        }

        #associationProductViewDetails table tr:nth-child(even) {
            background-color: #A09EA2;
            color: white;
            text-align: center;

        }
    </style>

</head>

<body>


    <div class="container">
        <div class="row">
            <div class="panel panel-primary filterable table-responsive">
                <div class="panel-heading">
                    <h3 class="panel-title">Order Pool</h3>
                    <div class="pull-right" style="position: relative;
top: -20px;"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                            Filter</button></div>
                </div>
                <table class="table table-responsive " style="font-size: 12px;">
                    <thead>
                        <tr class="filters">
                            <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                            <th><input type="text" class="form-control" placeholder=" Name" disabled></th>

                            <th><input type="text" class="form-control" placeholder="Contact 1" disabled></th>
                            <th><input type="text" class="form-control" placeholder="Contact 2" disabled></th>

                            <th><input type="text" class="form-control" placeholder="E-mail" disabled></th>


                            <th colspan="2" style="text-align: center;"><a class="btn btn-success"
                                    href="add-branch.html">View Accepted Order
                                </a></th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${recentlyReceivedOrders}" var="eachOrder">
                            <tr>
                                <td>${eachOrder.id}</td>
                                <td id="associationName">${eachOrder.associationName}</td>
                                <td id="associationPhone1">${eachOrder.orderStatus}</td>
                                <td id="associationPhone2">9832177025</td>

                                <td id="associationEmail">kundanitrd17@gmail.com</td>

                                <td class=""> <a href="#" class="btn btn-success s-icon " style="display: none;"
                                        onclick="saveBranch('${eachOrder.id}')">Save</a>

                                <td><a class="btn btn-danger d-icon">Accepted Order</a></td>
                            </tr>


                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>






    <script type="text/javascript" src="js/data-table.js"></script>

    <script type="text/javascript">


        // Delete Branch
        $('.table tbody tr td').on('click', '.d-icon', function () {
            $(this).parent().parent().remove();
            event.preventDefault();
            const content = $(this).parent().prevAll().toArray();
            const data = {};
            content.forEach(item => {
                if (item.id === "branchId") {
                    data["id"] = item.innerText;
                }
                if (item.id === "branchName") {
                    data["name"] = item.innerText;
                }
                if (item.id === "branchContact") {
                    data["phone"] = item.innerText;
                }
            })

            console.log(JSON.stringify(data));
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var xhr = new XMLHttpRequest();
            var url = 'http://localhost:8080/api/product/post';
            xhr.open("POST", url, true);
            xhr.setRequestHeader('Content-type', 'application/json');
            xhr.setRequestHeader(header, token);

            xhr.onreadystatechange = function () { // Call a function when the state changes.
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                    // console.log(typeof (JSON.parse(this.response)));
                    console.log(JSON.parse(this.response)["name"]);
                }
            }
            xhr.send(JSON.stringify(data))

        })















    </script>




</body>

</html>