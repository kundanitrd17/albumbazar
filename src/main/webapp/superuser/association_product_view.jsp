<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="en">

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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <style type="">
            html{
  font-family: 'open sans';
}
h1{
  margin-top: 30px;
  margin-bottom: 15px;
}

.fancy-cards{
  text-align: center;
  margin: 20px auto;
}
.fancy-cards .fancy-card{
  display: inline-block;
  position: relative;
  top: 40px;
  left: 20px;
  box-sizing: border-box;
}

.fancy-card .top,
.fancy-card .middle,
.fancy-card .bottom{
  position: relative;
  border-radius: 3px;
  overflow: hidden;
  width: 200px;
  height: 250px;
  transition: transform 300ms linear-out;
}
.fancy-card .top{
  z-index: 3;
  transform: scale(1.0);
  transition: transform 300ms cubic-bezier(0.22, 0.61, 0.36, 1);
 
  background-size: cover;
  background-position: center;
  box-shadow: 0px 1px 3px rgba(25,25,25,0.30);
}
.fancy-card .middle{
  position: absolute;
  background: #aaa;
  top: 0px;
  z-index: 2;
  transform: rotate(0deg);
  transition: transform 250ms cubic-bezier(0.68, -0.55, 0.27, 1.55);
}
.fancy-card .bottom{
  position: absolute;
  background: #ccc;
  top: 0px;
  z-index: 1;
  transform: rotate(0deg);
  transition: transform 250ms cubic-bezier(0.68, -0.55, 0.27, 1.55);
}

.fancy-card .caption{
  overflow: hidden;
  background: rgba(255,255,255,0.75);
  padding: 15px 10px;
  position: absolute;
  bottom: 0px;
  left: 0px;
  width: 100%;
}
.fancy-card .caption .title{
  color: #222;
  margin: 0px 0px 15px 0px;
  font-size: 1.4rem;
}
.fancy-card .caption .button,.fancy-card .caption .button1{
  display: inline-block;
  color: #333;
  text-decoration: none;
  border: solid 1px #555;
  padding: 7px 13px;
  background-color: transparent;
  transition: all 300ms ease-in;
}


/*hovering*/
.fancy-card:hover .top{
  transform: scale(1.05);
}
.fancy-card:hover .middle{
 transform: rotate(-7deg);
 box-shadow: 1px 1px 2px rgba(74, 74, 74, 0.35);
}
.fancy-card:hover .bottom{
  transform: rotate(7deg);
  box-shadow: 1px 1px 2px rgba(113, 113, 113, 0.35);
}

.fancy-card .button:hover,.fancy-card .button1:hover{
  background: rgba(0,0,0,0.8);
  color: #fff;
  border: 0px;
}

input
{
  max-width: 200px;
}
table tr th
{
  text-align: center;
}

table tr th h6
{

font-size: 13px; 
}

#largeModal table tr:nth-child( odd )
{
 background: rgba(226,226,226,1);
background: -moz-linear-gradient(left, rgba(226,226,226,1) 0%, rgba(209,209,209,1) 1%, rgba(133,128,133,1) 51%, rgba(254,254,254,1) 100%);
background: -webkit-gradient(left top, right top, color-stop(0%, rgba(226,226,226,1)), color-stop(1%, rgba(209,209,209,1)), color-stop(51%, rgba(133,128,133,1)), color-stop(100%, rgba(254,254,254,1)));
background: -webkit-linear-gradient(left, rgba(226,226,226,1) 0%, rgba(209,209,209,1) 1%, rgba(133,128,133,1) 51%, rgba(254,254,254,1) 100%);
background: -o-linear-gradient(left, rgba(226,226,226,1) 0%, rgba(209,209,209,1) 1%, rgba(133,128,133,1) 51%, rgba(254,254,254,1) 100%);
background: -ms-linear-gradient(left, rgba(226,226,226,1) 0%, rgba(209,209,209,1) 1%, rgba(133,128,133,1) 51%, rgba(254,254,254,1) 100%);
background: linear-gradient(to right, rgba(226,226,226,1) 0%, rgba(209,209,209,1) 1%, rgba(133,128,133,1) 51%, rgba(254,254,254,1) 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e2e2e2', endColorstr='#fefefe', GradientType=1 );;
color:white;
text-align: center;
 
}
#largeModal table tr:nth-child( even )
{
background-color: #A09EA2  ;
color:white;
text-align: center;
 
}
</style>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
        <link rel="stylesheet" href="/superuser/css/super-admin.css">
    </head>

    <body>

        <%@include file="sidebar.jsp" %>

            <div class="container">
                <div class="row">


                    <c:forEach items="${associations}" var="association">
                        <div class="col-md-4 col-lg-4 col-xl-4">
                            <div class="fancy-cards">
                                <!-- <h1>Microinteraction on active</h1>
  <p>It triggers a subtle micro-interaction (scales and adjusts) when the user interacts with it.</p>-->
                                <div class="fancy-card">
                                    <div class="top" id="t">
                                        <div class="caption">
                                            <h3 class="title">${association.name}</h3>
                                            <input type="text" name="associationId" value="${association.id}" hidden>

                                            <a type="button" id="associationViewPrice"
                                                class="btn btn-primary button1 associationViewPrice" data-toggle="modal"
                                                data-target="#largeModal">
                                                View Products
                                            </a>
                                        </div>
                                    </div>
                                    <div class="middle" id="m"></div>
                                    <div class="bottom" id="b"></div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>




                </div>
                <!-- Button to Open the Modal -->






            </div>

            <!-- View Price List Of Association-->

            <div class="modal fade" id="largeModal" tabindex="-1" role="dialog" aria-labelledby="basicModal"
                aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel"></h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body table-responsive">
                            <input type="text" name="association_id" id="selected_association_id">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="5">CoverPage Price List</th>
                                    </tr>
                                    <tr style="background-color: none;color:black;">
                                        <th>Quality</th>
                                        <th>Size</th>
                                        <th>Price</th>
                                        <th>Delete</th>
                                        <th>Edit</th>
                                    </tr>
                                </thead>
                                <tbody id="coverDetails">
                                    <!-- <tr>
                                <td>John</td>
                                <td>Doe</td>
                                <td>john@example.com</td>
                                <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;"
                                        class="fa fa-trash"></i></td>
                            </tr> -->
                                </tbody>
                            </table>
                        </div>
                        <div class="modal-body table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="5">Paper Price List</th>
                                    </tr>
                                    <tr style="background-color: none;color:black;">
                                        <th>Quality</th>
                                        <th>Size</th>
                                        <th>Price</th>
                                        <th>Delete</th>
                                        <th>Edit</th>
                                    </tr>
                                </thead>
                                <tbody id="paperDetails">
                                    <!-- <tr>
                                <td>John</td>
                                <td>Doe</td>
                                <td>john@example.com</td>
                                <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;"
                                        class="fa fa-trash"></i></td>
                            </tr> -->
                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>

                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript">

                $(document).ready(function () {
                    $(".fancy-card #t").css("background-image", "url('https://static-1.gumroad.com/res/gumroad/assets/collections/food_and_cooking_thumb-34fb9ef316a7b01177529839891c3a03.jpg')");
                });
            </script>

            <script type="text/javascript">

                // State management variables
                var allProductCategories = [];
                var allCovers = [];
                var allPapers = [];



                var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
                var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");


                document.addEventListener('DOMContentLoaded', () => {

                    header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
                    token = document.querySelector("meta[name='_csrf']").content;//.attr("content");


                });

                // Select a particular company
                $(".associationViewPrice").click(function () {
                    $("#paperDetails").empty()
                    $("#coverDetails").empty()
                    // $('#largeModal').modal('show');

                    var associationId = $(this).siblings("input").val();
                    var associationName = $(this).siblings(".title").text();
                    console.log(associationId, associationName);
                    $("#largeModal #myModalLabel").text(associationName);
                    $('#largeModal #selected_association_id').val(associationId);


                    const URI = "http://localhost:8080/api/product/company/" + associationId;

                    $.ajax({
                        url: URI,
                        success: function (result) {
                            console.log(result);
                            allProductCategories = result["productCategories"];
                            allCovers = result["covers"];
                            allPapers = result["papers"];

                            console.log(allCovers, allPapers, allProductCategories);

                            // fill-in cover table 
                            var coverTable = document.getElementById("coverDetails");
                            allCovers.forEach(element => {
                                var row = document.createElement('tr');
                                row.id = "coverRow" + element.id;

                                var coverName = document.createElement('td');
                                coverName.innerHTML = element.coverName;

                                var coverSize = document.createElement('td');
                                coverSize.innerHTML = element.coverSize;

                                var coverPrice = document.createElement('td');
                                coverPrice.innerHTML = element.coverPrice;
                                coverPrice.id = "coverPriceTd" + element.id;




                                var edit_save_button = document.createElement('td');
                                var editBtn = document.createElement('i');
                                editBtn.id = "editCoverBtn" + element.id;
                                editBtn.onclick = () => makeCoverRowEditable(element.id);
                                editBtn.style.color = 'blue';
                                editBtn.style.fontSize = '20px';
                                editBtn.style.cursor = 'pointer';
                                editBtn.className = 'fa fa-pencil';

                                var saveBtn = document.createElement('i');
                                saveBtn.id = "saveCoverBtn" + element.id;
                                saveBtn.onclick = () => saveCoverRowDetail(element.id);
                                saveBtn.style.color = 'green';
                                saveBtn.style.fontSize = '20px';
                                saveBtn.style.display = "none";
                                saveBtn.style.cursor = 'pointer';
                                saveBtn.className = 'fa fa-check';


                                edit_save_button.appendChild(editBtn);
                                edit_save_button.appendChild(saveBtn);

                                // <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;" class="fa fa-trash"></i></td>
                                var deletebutton = document.createElement('td');
                                var deleteBtn = document.createElement('i');
                                deleteBtn.style.color = 'rgb(236, 34, 34)';
                                deleteBtn.style.fontSize = '20px';
                                deleteBtn.style.cursor = 'pointer';
                                deleteBtn.className = 'fa fa-trash';
                                deleteBtn.onclick = () => deleteCover(element.id);
                                deletebutton.appendChild(deleteBtn);


                                row.appendChild(coverName);
                                row.appendChild(coverSize);
                                row.appendChild(coverPrice);
                                row.appendChild(deletebutton);
                                row.appendChild(edit_save_button);

                                coverTable.appendChild(row);

                                // coverTable.append('<tr><td>' + element.coverName + '</td><td>' + element.coverSize + '</td><td>' + element.coverPrice + '</td> </tr>');


                            });

                            // fill-in Paper Table
                            var paperTable = document.getElementById("paperDetails");

                            allPapers.forEach(element => {

                                var row = document.createElement('tr');
                                row.id = "paperRow" + element.id;

                                var paperQuality = document.createElement('td');
                                paperQuality.innerHTML = element.paperQuality;

                                var paperSize = document.createElement('td');
                                paperSize.innerHTML = element.paperSize;

                                var paperPrice = document.createElement('td');
                                paperPrice.id = "paperPriceTd" + element.id;
                                paperPrice.innerHTML = element.paperPrice;

                                var edit_save_button = document.createElement('td');
                                var editBtn = document.createElement('i');
                                editBtn.id = "editPaperBtn" + element.id;
                                editBtn.onclick = () => makePaperRowEditable(element.id);
                                editBtn.style.color = 'blue';
                                editBtn.style.fontSize = '20px';
                                editBtn.style.cursor = 'pointer';
                                editBtn.className = 'fa fa-pencil';

                                var saveBtn = document.createElement('i');
                                saveBtn.id = "savePaperBtn" + element.id;
                                saveBtn.onclick = () => savePaperRowDetail(element.id);
                                saveBtn.style.color = 'green';
                                saveBtn.style.fontSize = '20px';
                                saveBtn.style.display = "none";
                                saveBtn.style.cursor = 'pointer';
                                saveBtn.className = 'fa fa-check';


                                edit_save_button.appendChild(editBtn);
                                edit_save_button.appendChild(saveBtn);




                                // <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;" class="fa fa-trash"></i></td>
                                var deletebutton = document.createElement('td');
                                var deleteBtn = document.createElement('i');
                                deleteBtn.style.color = 'rgb(236, 34, 34)';
                                deleteBtn.style.fontSize = '20px';
                                deleteBtn.style.cursor = 'pointer';
                                deleteBtn.className = 'fa fa-trash';
                                deleteBtn.onclick = () => deletePaper(element.id);
                                deletebutton.appendChild(deleteBtn);


                                row.appendChild(paperQuality);
                                row.appendChild(paperSize);
                                row.appendChild(paperPrice);
                                row.appendChild(deletebutton);
                                row.appendChild(edit_save_button);

                                paperTable.appendChild(row);


                            });

                        }
                    });

                })

                // Add row
                var row = 1;
                $(document).on("click", "#add-row", function () {
                    console.log(allCovers);
                    if (row > 4) {
                        return false;
                    }
                    var new_row = '<tr id="row' + row + '"><td> <select name="sheetType" class="form-control" style="width: 160px;"><option value="">Paper Type</option></select></td><td><input name="sheetRange" value="" type="number" class="form-control input-md"  placeholder=""  style="width: 80px;"  /></td><td><input name="sheetPrice"' + row + '" type="number" class="form-control" placeholder=""  style="width: 80px;" disabled/></td><td><input class="delete-row btn btn-danger" type="button" value="X" /></td></tr>';

                    $('#test-body').append(new_row);
                    row++;
                    return false;
                });

                // Remove criterion
                $(document).on("click", ".delete-row", function () {
                    //  alert("deleting row#"+row);
                    if (row > 1) {
                        $(this).closest('tr').remove();
                        row--;
                    }
                    return false;
                });



                function deleteCover(id) {
                    console.log(id);
                    let is_confirm = confirm("Sure to delete cover");
                    if (!is_confirm) {
                        return;
                    }


                    const xhr = new XMLHttpRequest();
                    const url = "http://localhost:8080/api/superuser/cover/" + id;
                    xhr.open('DELETE', url, true);
                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            document.getElementById("coverRow" + id).remove();

                        } else if (this.readyState === 4) {
                            alert("Unable to make changes... Try again!");
                        }
                    }

                    xhr.send(id);




                }

                function deletePaper(id) {

                    let is_confirm = confirm("Sure to delete paper");
                    if (!is_confirm) {
                        return;
                    }

                    console.log(id);

                    const xhr = new XMLHttpRequest();
                    const url = "http://localhost:8080/api/superuser/paper/" + id;
                    xhr.open('DELETE', url, true);
                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            document.getElementById("paperRow" + id).remove();

                        } else if (this.readyState === 4) {
                            alert("Unable to make changes... Try again!");
                        }
                    }

                    xhr.send(id);



                }

                function makePaperRowEditable(id) {
                    console.log(id);
                    document.getElementById("editPaperBtn" + id).style.display = "none";
                    document.getElementById("savePaperBtn" + id).style.display = "block";

                    $("#paperPriceTd" + id).attr("contenteditable", "true").focus();


                }

                function savePaperRowDetail(id) {
                    let paperPrice = $("#paperPriceTd" + id).text();
                    console.log(paperPrice);

                    const xhr = new XMLHttpRequest();
                    const url = "http://localhost:8080/api/superuser/paper/price/" + id;
                    xhr.open('PUT', url, true);
                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            document.getElementById("editPaperBtn" + id).style.display = "block";
                            document.getElementById("savePaperBtn" + id).style.display = "none";

                        } else if (this.readyState === 4) {
                            alert("Unable to make changes... Try again!");
                        }
                    }

                    xhr.send(paperPrice);


                }

                function makeCoverRowEditable(id) {

                    console.log(id);
                    document.getElementById("editCoverBtn" + id).style.display = "none";
                    document.getElementById("saveCoverBtn" + id).style.display = "block";

                    $("#coverPriceTd" + id).attr("contenteditable", "true").focus();
                }

                function saveCoverRowDetail(id) {
                    console.log(id);
                    let coverPrice = $("#coverPriceTd" + id).text();
                    const xhr = new XMLHttpRequest();
                    const url = "http://localhost:8080/api/superuser/cover/price/" + id;
                    xhr.open('PUT', url, true);
                    xhr.setRequestHeader('Content-type', 'application/json');
                    xhr.setRequestHeader(header, token);

                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            document.getElementById("editCoverBtn" + id).style.display = "block";
                            document.getElementById("saveCoverBtn" + id).style.display = "none";

                        } else if (this.readyState === 4) {
                            alert("Unable to make changes... Try again!");
                        }
                    }

                    xhr.send(coverPrice);
                }



            </script>

            <script src='http://code.jquery.com/jquery-latest.js'></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.bundle.js"></script>
            <script src='/superuser/js/super-admin.js'></script>




    </body>

    </html>