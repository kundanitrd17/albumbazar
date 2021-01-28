<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>

    <head>
        <title>Album Bazaar</title>
        <meta charset="utf-8">
        <meta name="_csrf" content="${_csrf.token}" />
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        <!-- ... -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> -->
        <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->



        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
        <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->


        <style type="text/css">

        </style>
        <link rel="stylesheet" href="/superuser/css/super-admin.css">


    </head>

    <body>

        <%@include file="sidebar.jsp" %>



            <section id="contents">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                <i class="fa fa-align-right"></i>
                            </button>
                            <a class="navbar-brand" href="#">my<span class="main-color">Dashboard</span></a>
                        </div>
                        <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                        aria-haspopup="true" aria-expanded="false">My profile <span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#"><i class="fa fa-user-o fw"></i> My account</a></li>
                                        <li><a href="#"><i class="fa fa-envelope-o fw"></i> My inbox</a></li>
                                        <li><a href="#"><i class="fa fa-question-circle-o fw"></i> Help</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li>
                                            <form action="/superuser/logout-super" method="POST">
                                                <div style="left: 200px;">
                                                    <i class="fa fa-sign-out"></i><button
                                                        style="outline: 0mm;">Logout</button>
                                                </div>
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                            </form>
                                        </li>
                                    </ul>
                                </li>
                                <li><a href="#"><i class="fa fa-comments"></i><span>23</span></a></li>
                                <li><a href="#"><i class="fa fa-bell-o"></i><span>98</span></a></li>
                                <li><a href="#"><i data-show="show-side-navigation1"
                                            class="fa fa-bars show-side-btn"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>




                <div class="container">
                    <div class="row">
                        <div class="panel panel-primary filterable table-responsive">
                            <div class="panel-heading">
                                <h3 class="panel-title">Branch Detail</h3>
                                <div class="pull-right" style="position: relative;
top: -20px;"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>
                                        Filter</button></div>
                            </div>
                            <table class="table " style="font-size: 12px;">
                                <thead>
                                    <tr class="filters">
                                        <th><input type="text" class="form-control" placeholder="Id" disabled></th>
                                        <th><input type="text" class="form-control" placeholder=" Name" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="Code" disabled></th>
                                        <th><input type="text" class="form-control" placeholder=" Admin" disabled></th>
                                        <th><input type="text" class="form-control" placeholder="Contact" disabled></th>
                                        <th><input type="text" class="form-control" placeholder=" Address" disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Inaugration Date"
                                                disabled>
                                        </th>
                                        <th><input type="text" class="form-control" placeholder="Order" disabled></th>
                                        <th colspan="2" style="text-align: center;"><a class="btn btn-success"
                                                href="/superuser/add-branch">Add
                                                Branch</a></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${branches}" var="branch">
                                        <c:if test="${branch.active}">
                                            <tr id="row${branch.id}">
                                        </c:if>
                                        <c:if test="${!branch.active}">
                                            <tr id="row${branch.id}" style="background-color: gray;">
                                        </c:if>


                                        <td id="branchId${branch.id}" class="branchId" data-branch-id="${branch.id}">
                                            ${branch.id}
                                        </td>
                                        <td id="branchName${branch.id}">
                                            ${branch.name}
                                        </td>
                                        <td id="branchCode">ASN-AB</td>
                                        <td id="adminId"><a href="" id="link_adminId" data-toggle="modal"
                                                data-target="#adminDetails" onclick="adminLink(2)">adminId</a>
                                        </td>
                                        <td id="contact${branch.id}">${branch.contactNo}</td>
                                        <td id="address">
                                            <a href="" data-toggle="modal" data-target="#addressModal" id="link_address"
                                                onclick="fetchAddressFromServer('${branch.id}', '${branch.getAddress().getId()}')">Address
                                                Id</a>
                                        </td>
                                        <td id="date">20/12/2004</td>
                                        <td> <a href="api/order?branchId=${branch.id}">click</a> </td>

                                        <c:if test="${branch.active}">
                                            <td class="">
                                                <a href="#" class="btn btn-success s-icon " style="display: none;"
                                                    onclick="saveBranch('${branch.id}')">Save</a>
                                                <button class="btn btn-warning e-icon">Edit</button>
                                            </td>
                                            <td><a class="btn btn-danger d-icon">Delete</button></a></td>
                                        </c:if>


                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </section>

            <!-- Address Modal -->
            <div class="modal" id="addressModal" tabindex="-1" role="dialog">
                <form action="/superuser/branch/address/change" method="POST">



                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <!-- Modal Header -->

                            <div class="modal-header">
                                <h5 class="modal-title">Address</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <!-- End of Modal Header -->
                            <div class="modal-body">
                                <input type="text" name="branchId" hidden>
                                <input type="text" value="" name="id" hidden>
                                <div class="form-group">
                                    <label for="exampleInputName">Name</label>
                                    <input name="name" type="name" class="form-control" id="exampleInputName" value="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputMobile">Mobile</label>
                                    <input name="contactNo" type="text" class="form-control" id="exampleInputMobile"
                                        value="">
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="exampleInputLandmark">Landmark</label>
                                    <input name="landmark" type="text" class="form-control" id="exampleInputLandmark"
                                        value="" placeholder="Landmark">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputAddress1">Address</label>
                                    <input name="line1" type="text" class="form-control" id="exampleInputAddress1"
                                        value="" placeholder="Address line1">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputAddress2">Address</label>
                                    <input name="line2" type="text" class="form-control" id="exampleInputAddress2"
                                        value="" placeholder="Address line2">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputCity">City</label>
                                    <input name="city" type="text" class="form-control" id="exampleInputCity" value=""
                                        placeholder="City">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputDistrict">District</label>
                                    <input name="district" type="text" class="form-control" id="exampleInputDistrict"
                                        value="" placeholder="District">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputState">State</label>
                                    <input name="state" type="text" class="form-control mx-200" value=""
                                        placeholder="State">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputPIN">Pin-Code</label>
                                    <input name="pincode" type="text" class="form-control mx-200" value=""
                                        placeholder="PIN">
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">Save
                                    changes</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>

                        </div>
                    </div>
                </form>
            </div>

            <!-- End of address modal -->

            <!-- Admin Detail Modal -->
            <div class="modal" id="adminDetails">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Modal Heading</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <table class="table table-borderless">
                                <tbody>
                                    <tr>
                                        <th>Name: </th>
                                        <td id="Name"></td>
                                    </tr>
                                    <tr>
                                        <th>Branch: </th>
                                        <td id="Branch"></td>
                                    </tr>
                                    <tr>
                                        <th>Contact: </th>
                                        <td id="contact"></td>
                                    </tr>
                                    <tr>
                                        <th>Email: </th>
                                        <td id="email"></td>
                                    </tr>
                                    <tr>
                                        <th>Salary: </th>
                                        <td id="salary"></td>
                                    </tr>
                                    <tr>
                                        <th>Joining Date: </th>
                                        <td id="jd"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>

                    </div>
                </div>
            </div>
            <!-- Admin Detail Modal END -->




            <script>




                function fetchAddressFromServer(branchId, id) {
                    const modal = document.querySelector("#addressModal");
                    modal.querySelectorAll('input').forEach(item => {
                        if (item.type === "hidden") return;
                        if (item.hidden === true) return;
                        item.value = "";
                    });

                    modal.querySelector('input[name="branchId"]').value = branchId;

                    const xhr = new XMLHttpRequest();
                    const url = "http://localhost:8080/api/secured/address/" + id;
                    xhr.open('GET', url, true);

                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            console.log(this.response);
                            if (this.responseText.length <= 0) {
                                alert("No address found");
                                return false;
                            }
                            const address = JSON.parse(this.response);


                            modal.querySelector('input[name="name"]').value = address["name"];
                            modal.querySelector('input[name="contactNo"]').value = address["contactNo"];
                            modal.querySelector('input[name="landmark"]').value = address["landmark"];
                            modal.querySelector('input[name="line1"]').value = address["line1"];
                            modal.querySelector('input[name="line2"]').value = address["line2"];
                            modal.querySelector('input[name="city"]').value = address["city"];
                            modal.querySelector('input[name="pincode"]').value = address["pincode"];
                            modal.querySelector('input[name="district"]').value = address["district"];
                            modal.querySelector('input[name="state"]').value = address["state"];
                        } else if (this.readyState === 4) {
                            alert("Address Not found");
                        }
                    }

                    xhr.send(null);
                }
            </script>
            <script type="text/javascript" src="js/data-table.js"></script>

            <script type="text/javascript">

                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");



                // Delete Branch
                $('.table tbody tr td').on('click', '.d-icon', function () {

                    if (!confirm("Confirm to delete this branch")) {
                        return;
                    }

                    $(this).hide();
                    $(this).parent().prev().hide();
                    $(this).parent().prevAll().css("background-color", "gray").focus();



                    event.preventDefault();
                    const content = $(this).parent().prevAll().toArray();
                    const data = {};
                    var branchId = $(this).parent().siblings('.branchId').data();
                    // console.log(branchId);
                    data["id"] = branchId.branchId;

                    // console.log(JSON.stringify(data));

                    var xhr = new XMLHttpRequest();
                    var url = '/api/superuser/branch-delete';
                    xhr.open("DELETE", url, true);
                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () { // Call a function when the state changes.
                        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                            // console.log(typeof (JSON.parse(this.response)));
                            console.log(this.response);
                            console.log(JSON.parse(this.response));


                        }
                    }
                    xhr.send(JSON.stringify(data["id"]))

                })



                // Editable 
                $('.table tbody tr td').on('click', '.e-icon', function () {

                    var d = $(this).parent().siblings('.branchId').data();
                    var id = d.branchId;
                    $(this).hide();
                    $(this).siblings().show();
                    $(this).parent().siblings("#branchName" + id).attr("contenteditable", "true").focus();
                    $(this).parent().siblings("#branchCode" + id).attr("contenteditable", "true").focus();
                    $(this).parent().siblings("#contact" + id).attr("contenteditable", "true").focus();

                });




                // Save Edits

                $('.table tbody tr td').on('click', '.s-icon', function () {

                    $(this).hide();
                    $(this).siblings('.e-icon').show();
                    $(this).parent().siblings("").attr("contenteditable", "false");

                });



                // $('.table tbody tr td').on('click', '.d-icon', function () {

                //     $(this).closest('tr').remove();

                // });


                $('.table tbody tr td').on('click', '.u-icon', function () {

                    $(this).hide();
                    $(this).siblings().show();
                    $(this).parent().parent().prevAll().children('td').attr("contenteditable", "true").focus().css({ "background-color": "#eeeeee", "width": "200px" });

                });

                //view Branch Address


                // //update branch address
                // function u_address(id) {
                //     var id = $('#branchAddress #hidden_address_id').val();

                //     var lendmark = $('#branchAddress #landmark').text("kundan");
                //     var street1 = $('#branchAddress #street1').text("upper Kulti");
                //     var street2 = $('#branchAddress #street2').text();
                //     var postoffice = $('#branchAddress #postoffice').text();
                //     var city = $('#branchAddress #city').text();
                //     var pincode = $('#branchAddress #pincode').text();
                //     var district = $('#branchAddress #district').text();
                //     var state = $('#branchAddress #state').text();

                //     alert(id);
                // }

                //show admin details
                function adminLink(id) {
                    $('#adminDetails #hidden_admin_id').val(id);
                    console.log("Admin", id);
                    url = "/api/superuser/admin-detail/" + id; yg
                    $.get(url, function (data) {

                        console.log("Here", data);
                    }).fail(function (data) {
                        console.log(data.responseText);
                    });
                    // var url = "";gggggg
                    // $.Post("url", { id: id }, function (data, status) {

                    //     $('#adminDetails #Name').text("kundan").css("text-tranform", "capitalize");
                    //     $('#adminDetails #Branch').text("upper Kulti").css("text-tranform", "capitalize");
                    //     $('#adminDetails #contact').text().css("text-tranform", "capitalize");
                    //     $('#adminDetails #email').text().css("text-tranform", "capitalize");
                    //     $('#adminDetails #salary').text().css("text-tranform", "capitalize");
                    //     $('#adminDetails #jd').text().css("text-tranform", "capitalize");

                    // });

                }

                //save Branch
                function saveBranch(id) {

                    var childs = $('#row' + id).children();
                    console.log();

                    var data = {
                        "id": id,
                        "name": childs.siblings('#branchName' + id).text(),
                        "code": "code",
                        "contactNo": childs.siblings('#contact' + id).text()
                    }
                    console.log(data);

                    var xhr = new XMLHttpRequest();
                    var url = '/api/superuser/branch-info';
                    xhr.open("PUT", url, true);
                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () { // Call a function when the state changes.
                        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                            // console.log(typeof (JSON.parse(this.response)));
                            console.log(this.response);
                        }
                    }
                    xhr.send(JSON.stringify(data))


                }



            </script>

            </script>


            <script src='./js/super-admin.js'></script>


            <script src='http://code.jquery.com/jquery-latest.js'></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>

    </body>

    </html>