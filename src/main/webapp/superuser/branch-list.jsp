<!DOCTYPE html>
<%@ page import="java.util.List, com.albumbazaar.albumbazar.model.Branch" %>
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

  </style>

</head>

<body>
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
              <th><input type="text" class="form-control" placeholder="Name" disabled></th>
              <th><input type="text" class="form-control" placeholder="Code" disabled></th>
              <th><input type="text" class="form-control" placeholder="Admin" disabled></th>
              <th><input type="text" class="form-control" placeholder="Phone" disabled></th>
              <th><input type="text" class="form-control" placeholder="Address" disabled></th>
              <th><input type="text" class="form-control" placeholder="Inaugration Date" disabled></th>
              <th><input type="text" class="form-control" placeholder="Order" disabled></th>
              <th colspan="2" style="text-align: center;"><a class="btn btn-success" href="add-branch.html">Add
                  Branch</a></th>
            </tr>
          </thead>
          <tbody>


            <% List<Branch> branches = (List<Branch>) request.getAttribute("branches");
            int index = 0; 
            for(Branch branch : branches) { 
              
          %>
            <tr id="row<%= branch.getId() %>">

              <td id="branchId"><%= branch.getId() %></td>
              <td id="branchName"><%= branch.getName() %></td>
              <td><%= branch.getId() %></td>
              <td> <a href="http://www.google.com">12</a> </td>
              <td>1001</td>
              <td id="branchContact"><%= branch.getContactNo() %></td>
              <!-- <td> <a href="http://www.google.com?=<%= branch.getAddress().getId() %>"><%= branch.getAddress().getId() %></a> </td> -->
              <td><%= branch.getId() %></td>
              <td> <a href="api/order?branchId=<%=branch.getId()%>">click</a> </td>

              <td class="">
                <button id="save_row" class="btn btn-success s-icon " style="display: none;">Save</button>
                <button class="btn btn-warning e-icon">Edit</button>
              </td>

              <td><button id="deleteBranch" class="btn btn-danger d-icon">Delete</button></td>


            </tr>

            <%}%>
        </tbody>
      </table>

      

    </div>
  </div>
</div>

<script type="text/javascript" src="js/data-table.js"></script>

<script type="text/javascript">


  $('.table tbody tr td').on('click', '#deleteBranch', function () {
    console.log('hi');
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
        console.log(typeof (JSON.parse(this.response)));
        console.log(JSON.parse(this.response)["name"]);
      }
    }
    xhr.send(JSON.stringify(data))

  })



  $('.table tbody tr td').on('click', '.e-icon', function () {

    $(this).hide();
    $(this).siblings().show();
    $(this).parent().prevAll().attr("contenteditable", "true").focus();

  });



  $('.table tbody tr td').on('click', '.s-icon', function () {

    $(this).hide();
    $(this).siblings().show();
    $(this).parent().prevAll().attr("contenteditable", "false");

  });

//  $('.table tbody tr td').on('click','.d-icon',function(){

//   $(this).closest('tr').remove();

// });


</script>

</script>
</body>
</html>