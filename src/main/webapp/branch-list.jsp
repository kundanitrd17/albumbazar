<!DOCTYPE html>
<html>
<head>
<title>Album Bazaar</title>
	<meta charset="utf-8">
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
        <h3 class="panel-title">Users</h3>
        <div class="pull-right" style="position: relative;
top: -20px;"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> Filter</button></div>
      </div>
      <table class="table " style="font-size: 12px;">
        <thead >
          <tr class="filters">
            <th><input type="text" class="form-control" placeholder="Bran Id" disabled></th>
            <th><input type="text" class="form-control" placeholder="Bran Name" disabled></th>
            <th><input type="text" class="form-control" placeholder="Bran Code" disabled></th>
            <th><input type="text" class="form-control" placeholder="Bran Adm Id" disabled></th>
            <th><input type="text" class="form-control" placeholder=" Admin Name" disabled></th>
                <th><input type="text" class="form-control" placeholder="Bran Cont" disabled></th>
            <th><input type="text" class="form-control" placeholder="Bran Addr Id" disabled></th>
             <th><input type="text" class="form-control" placeholder="Bran Order" disabled></th>
             <th colspan="2" style="text-align: center;"><a class="btn btn-success" href="add-branch.html">Add Branch</a></th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>101</td>
            <td>Asansol Branch</td>
            <td>ASN-AB</td>
            <td>100</td>
            <td>Kundan Srivastava</td>
            <td>1234567890</td>
            <td>1</td>
            <td>Branch Order LINK</td>

          <td class="">  <a href="#" class="btn btn-success s-icon " style="display: none;">Save</a>
           <button class="btn btn-warning e-icon">Edit</button></td>
            <td><a class="btn btn-danger d-icon">Delete</button></a></td>
          </tr>
      
            <tr>
            <td>102</td>
            <td>Asansol Branch</td>
            <td>ASN-AB</td>
            <td>100</td>
            <td>Kundan Srivastava</td>
            <td>1234567890</td>
            <td>1</td>
            <td>Branch Order LINK</td>
          <td class="">  <a href="#" class="btn btn-success s-icon " style="display: none;">Save</a>
           <button class="btn btn-warning e-icon">Edit</button></td>
            <td><a class="btn btn-danger d-icon">Delete</button></a></td>
          </tr>
                   <tr>
            <td>103</td>
            <td>Asansol Branch</td>
            <td>ASN-AB</td>
            <td>100</td>
            <td>Kundan Srivastava</td>
            <td>1234567890</td>
            <td>1</td>
            <td>Branch Order LINK</td>
          <td class="">   <a href="#" class="btn btn-success s-icon " style="display: none;">Save</a>
           <button class="btn btn-warning e-icon">Edit</button></td>
            <td><a class="btn btn-danger d-icon">Delete</button></a></td>
          </tr>
                   <tr>
            <td>104</td>
            <td>Asansol Branch</td>
            <td>ASN-AB</td>
            <td>100</td>
            <td>Kundan Srivastava</td>
            <td>1234567890</td>
            <td>1</td>
            <td>Branch Order LINK</td>
          <td class="">   <a href="#" class="btn btn-success s-icon " style="display: none;">Save</a>
           <button class="btn btn-warning e-icon">Edit</button></td>
            <td><a class="btn btn-danger d-icon">Delete</button></a>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>

<script type="text/javascript" src="js/data-table.js"></script>

<script type="text/javascript">
  

  
  $('.table tbody tr td').on('click','.e-icon',function(){

    $(this).hide();
    $(this).siblings().show();
    $(this).parent().prevAll().attr("contenteditable","true").focus();

  });



  $('.table tbody tr td').on('click','.s-icon',function(){

    $(this).hide();
    $(this).siblings().show();
    $(this).parent().prevAll().attr("contenteditable","false");

  });

 $('.table tbody tr td').on('click','.d-icon',function(){

  $(this).closest('tr').remove();

});


</script>

</script>
</body>
</html>