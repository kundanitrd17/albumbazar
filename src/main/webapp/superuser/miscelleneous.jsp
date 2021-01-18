<!DOCTYPE html>
<html>
<head>
	<title>Cart</title>
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <style type="text/css">
  		.SuperUserBanner .card,.SuperUserBanner .card .card-header,.SuperUserBanner .card .card-footer,.SuperUserAddAlbum .card,.SuperUserAddAlbum .card .card-header,.SuperUserAddAlbum .card .card-footer
  		{
  			border:none;
  			background-color: white;
  		}
  		.SuperUserBanner .card,.SuperUserAddAlbum .card,.insertAnsQues
  		{
  				box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		-webkit-box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		-moz-box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		margin-top: 50px;
  		}
  	.SuperUserBanner .card .card-footer .banner-upload,.SuperUserAddAlbum .card .card-footer .banner-upload
  	{
  		overflow: hidden;
  		position: relative;
  		background-color: #09024B;
  		color: white;
  		font-size: 12px;
  		
  	}

  	.SuperUserBanner .card .card-footer .banner-delete:hover
  	{
  		box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		-webkit-box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		-moz-box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		background-color: white;
		color:#BE2632;
  	}
  	.SuperUserAddAlbum .card .card-footer .banner-delete:hover
  	{
  		box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		-webkit-box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		-moz-box-shadow: 10px 7px 24px -1px rgba(225,115,139,0.75),-10px -7px 24px -1px rgba(225,115,139,0.75);
		background-color: white;
		color:#BE2632;
  	}
  	.SuperUserBanner .card .card-footer .banner-upload:hover
  	{
  		box-shadow: 10px 7px 24px -1px rgba(94,130,227,0.75),-10px -7px 24px -1px rgba(94,130,227,0.75);
		-webkit-box-shadow: 10px 7px 24px -1px rgba(94,130,227,0.75),-10px -7px 24px -1px rgba(94,130,227,0.75);
		-moz-box-shadow: 10px 7px 24px -1px rgba(94,130,227,0.75),-10px -7px 24px -1px rgba(94,130,227,0.75);
		background-color: white;
		color:#0917BC;
		font-weight: bold;
  	}
  	.SuperUserAddAlbum .card .card-footer .banner-upload:hover
  	{
  		box-shadow: 10px 7px 24px -1px rgba(94,130,227,0.75),-10px -7px 24px -1px rgba(94,130,227,0.75);
		-webkit-box-shadow: 10px 7px 24px -1px rgba(94,130,227,0.75),-10px -7px 24px -1px rgba(94,130,227,0.75);
		-moz-box-shadow: 10px 7px 24px -1px rgba(94,130,227,0.75),-10px -7px 24px -1px rgba(94,130,227,0.75);
		background-color: white;
		color:#0917BC;
		font-weight: bold;
  	}
  	.SuperUserBanner .card .card-footer .banner-upload input,.SuperUserAddAlbum  .card .card-footer .banner-upload input
  	{
  		cursor: pointer;
  		position: absolute;
  		font-size: 50px;
  		opacity: 0;
  		right: 0;
  		top: 0;
  	}
  	.SuperUserBanner .card-header,.SuperUserAddAlbum .card-header
  	{
  		text-align: center;
  	}
  	.SuperUserBanner
  	{	margin-top: 30px;
  		padding:40px;
  		box-shadow: 10px 7px 24px -1px rgba(131,107,107,0.75);
		-webkit-box-shadow: 10px 7px 24px -1px rgba(131,107,107,0.75);
		-moz-box-shadow: 10px 7px 24px -1px rgba(131,107,107,0.75);
  	}
	.SuperUserBanner .card-body .banner-image,.SuperUserAddAlbum .card-body .banner-image
	{
		width: 100%;
		height: 100%;	
	}

	
.wyc-container {
	  max-width: 600px;
	  margin: 0 auto;
}

.wyc {
	  background-color: transparent;
	  border: 1px solid #9fa4a8;
	  border-radius: 10px;
	  margin: 20px 0;
	  padding: 30px;
	  position: relative;
	  overflow: hidden;
	  transition: 0.3 ease;
}

.wyc.active {
  background-color: #fff;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1), 0 3px 6px rgba(0, 0, 0, 0.1);
}

.wyc.active::before,
.wyc.active::after {
  content: "\f075";
  font-family: "Font Awesome 5 Free";
  color: #2ecc71;
  font-size: 7rem;
  position: absolute;
  opacity: 0.2;
  top: 20px;
  left: 20px;
  z-index: 0;
}

.wyc.active::before {
  color: #3498db;
  top: -10px;
  left: -30px;
  transform: rotateY(180deg);
}

.wyc-title {
  margin: 0 35px 0 0;
}

.wyc-text {
  display: none;
  margin: 30px 0 0;
}

.wyc.active .wyc-text {
  display: block;
}

.wyc-toggle {
  background-color: transparent;
  border: 0;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  padding: 0;
  position: absolute;
  top: 30px;
  right: 30px;
  height: 30px;
  width: 30px;
}

.wyc-toggle:focus {
  outline: 0;
}

.wyc-toggle .fa-times {
  display: none;
}

.wyc.active .wyc-toggle .fa-times {
  color: #fff;
  display: block;
}

.wyc.active .wyc-toggle .fa-chevron-down {
  display: none;
}

.wyc.active .wyc-toggle {
  background-color: #9fa4a8;
}



  </style>
<link rel="stylesheet" href="/superuser/css/super-admin.css">
</head>
<body style="background-color: white;">

    <%@include file="sidebar.jsp" %>

<section >
	<div class="container-fluid" >

		<div class="row SuperUserBanner">
			
			<div class=" col-12 banner-heading"><h3>Customise Banner</h3></div>
			<div class="container col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4">
				  	<div class=" container card">
					  <div class="card-header">Banner 1</div>
					  <div class="card-body">
					  	<img class="banner-image" src="../img/slide1.svg" style="">
					  </div>
					  <div class="card-footer">
							  	<div class="row">
							  		<div class="col-12 col-xs-12 col-sm-12 col-md-2 col-lg-2 col-xl-2">
							  			
							  		</div>

							  		<div class="col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">
							  			<a href="" class="btn btn-danger banner-delete"><i class=" fa fa-trash"></i></a>
							  		</div>
							  		<div class="col-9 col-xs-9 col-sm-9 col-md-6 col-lg-6 col-xl-6">
							  			<div class="file btn  btn-file banner-upload" style="">
		                        			Upload Banner
		                      			<input type="file" name="" style="">
		                    			</div>
							  		</div>
							  	<div class="col-12 col-xs-12 col-sm-12 col-md-1 col-lg-1 col-xl-1">
							  			
							  		</div>
							  		
							  	</div>
					  </div>
					</div>
			</div>
			<div class="container col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4">
				 	<div class="container card">
					  <div class="card-header">Banner 2</div>
					  <div class="card-body"><img class="banner-image" src="../img/slide1.svg" style=""></div>
						  <div class="card-footer">
							  	<div class="row">
							  		<div class="col-12 col-xs-12 col-sm-12 col-md-2 col-lg-2 col-xl-2">
							  			
							  		</div>

							  		<div class="col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">
							  			<a href="" class="btn btn-danger banner-delete"><i class=" fa fa-trash"></i></a>
							  		</div>
							  		<div class="col-9 col-xs-9 col-sm-9 col-md-6 col-lg-6 col-xl-6">
							  			<div class="file btn  btn-file banner-upload" style="">
		                        			Upload Banner
		                      			<input type="file" name="" style="">
		                    			</div>
							  		</div>
							  	<div class="col-12 col-xs-12 col-sm-12 col-md-1 col-lg-1 col-xl-1">
							  			
							  		</div>
							  		
							  	</div>
					  </div>
					</div>
			</div>
			<div class="container col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4">
					<div class="container card">
					  <div class="card-header">Banner 3</div>
					  <div class="card-body"><img class="banner-image" src="../img/slide1.svg" style=""></div>
					  <div class="card-footer">
							  	<div class="row">
							  		<div class="col-12 col-xs-12 col-sm-12 col-md-2 col-lg-2 col-xl-2">
							  			
							  		</div>

							  		<div class="col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">
							  			<a href="" class="btn btn-danger banner-delete"><i class=" fa fa-trash"></i></a>
							  		</div>
							  		<div class="col-9 col-xs-9 col-sm-9 col-md-6 col-lg-6 col-xl-6">
							  			<div class="file btn  btn-file banner-upload" style="">
		                        			Upload Banner
		                      			<input type="file" name="" style="">
		                    			</div>
							  		</div>
							  	<div class="col-12 col-xs-12 col-sm-12 col-md-1 col-lg-1 col-xl-1">
							  			
							  		</div>
							  		
							  	</div>
					  </div>
					</div>
			</div>
		</div>
		
	</div>
</section>	

<section>

	<div class="container-fluid">

		
		<div class="row SuperUserAddAlbum" id="AddNewSampleAlbum">

			<div class="col justify-content-start">
				<button  type="button" class="btn btn-info btn-lg my-4" data-toggle="modal" data-target="#myModal" onclick="addNewSampleAlbum();">
					Add New Sample Album ➕
				</button>
				<hr style="width: 70%;">
			</div>

			
			<div class="col-12 banner-heading"><h3>Customise Album</h3></div>

			<div class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4">
				<div class="card">
						  <div class="card-header">Albun 1</div>
						  <div class="card-body">
						  	<img class="banner-image" src="./../img/slide1.svg" style="">
						  </div>
					  	<div class="card-footer">
							  	<div class="row">

							  		<div class="col-1 col-xs-1 col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
							  		<div class="col-2 col-xs-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
							  			<a href="" class="btn btn-danger banner-delete"><i class=" fa fa-trash"></i></a>
							  		</div>
							  		<div class="col-8 col-xs-8 col-sm-8 col-md-8 col-lg-8 col-xl-8">
							  			<div class="file btn  btn-file banner-upload" style="">
		                        			Upload Album
		                      			<input type="file" name="" style="" >
		                    			</div>
							  		</div>
							  	</div>
							  	
							  	
							  		<div class="col-12" >
							  			<input type="text"  class="form-control" name="" placeholder="Input Album Title" style="margin:10px;">
							  		</div>
							
							  		
							  		<div class="col-12">
							  			<textarea  class="form-control" rows="8" cols="40" placeholder="Describe Album here..." style="margin-left: 10px;"></textarea>
							  		</div>
							  		<div class="col-12">
							  		<button type="button" class="btn btn-success btn-sm btn-block my-3 mx-2">Save Album</button >
							  		</div>
							
							  		
							  
					  </div>
					</div>
			</div>
				
			
		</div>
		
	</div>
</section>



 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
     
        </div>
        <div class="modal-body">
         	<section>

	<div class="container-fluid">

		
		<div class="row SuperUserAddAlbum" id="AddNewSampleAlbum">

			

			
		

			<div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
				<div class="card">
						  <div class="card-header">Albun 1</div>
						  <div class="card-body">
						  	<img class="banner-image" src="./../img/slide1.svg" style="">
						  </div>
					  	<div class="card-footer">
							  	<div class="row">

							  		<div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
							  			<div class="file btn  btn-file banner-upload" style="">
		                        			Upload Album
		                      			<input type="file" name="" style="" >
		                    			</div>
							  		</div>
							  	</div>
							  	
							  	
							  		<div class="col-12" >
							  			<input type="text"  class="form-control" name="" placeholder="Input Album Title" style="margin:10px;">
							  		</div>
							
							  		
							  		<div class="col-12">
							  			<textarea  class="form-control" rows="8" cols="40" placeholder="Describe Album here..." style="margin-left: 10px;"></textarea>
							  		</div>
							  		<div class="col-12">
							  		<button type="button" class="btn btn-success btn-sm btn-block my-3 mx-2">Save Album</button >
							  		</div>
							
							  		
							  
					  </div>
					</div>
			</div>
				
			
		</div>
		
	</div>
</section>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>










<section>
	<div class="container-fluid insertAnsQues">

		<div class="row" style="padding: 5%;">
			<div class="col-12" style="padding-bottom: 10px;"><h3>Insert Question And Answer</h3></div>

			<div class="col-12" >
				  <div class="form-group">
				      <label for="pwd">Insert Question:</label>
				      <input type="password" class="form-control" id="pwd" name="password">
				  </div>
			</div>
			<div class="col-12">
				<div class="form-group">
					  <label for="comment">Give Answer</label>
					  <textarea class="form-control" rows="5" id="comment"></textarea>
				</div>
			</div>
			<div class="col-12">
					<button type="button" class="btn btn-success btn-sm my-3 mx-2">Save Answer</button >
			</div>

			<div class="col-12 wyc-container" id="WhyChoose">



				 <div class="wyc ">

			        <h5 class="wyc-title"><span>Why shouldn't we trust atoms ?</span></h5>
			        <p class="wyc-text">They make up everything</p>
			        <button class="wyc-toggle">
			          <i class="fa fa-chevron-down"></i>
			          <i class="fa fa-times"></i>
			     
			        </button>
			             <button href="#" class="btn btn-danger question-delete" style="padding: 2px 8px;border-radius:20px;margin-left: 40px;position: absolute;top: 0px;left: -40px;" onclick="deleteQuestion()"><i class=" fa fa-trash"></i></button>

     			 </div>

			</div>
			
		</div>
	</div>
</section>

<section>
		<div class="container-fluid" style="z-index: 2;">
		<div class="row section15" style="margin-top: 300px;padding: 100px 0px;background-color: #F7F7F7; ">
		<div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12" >

			<div class="container">
				<div class="row">
					<div class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6" style="padding: 30px 0px;">
						<h2>The best way to manage expenses. </h2><h2>Speak to a finance consultant today.</h2>
						<hr style="border-top: 2px solid red;width:5%;">
						<p>Understanding your requirements and objectives is important to us. We listen and work together to create a truly unique and unforgettable experience.</p>
					</div>
					<div class="col-12 col-xs-12 col-sm-12 col-md-2 col-lg-2">
						
					</div>
					<div class="col-12 col-xs-12 col-sm-12 col-md-4 col-lg-4" >
						<div class="login">
  								
								<h3>Request a call back</h3>
 								 <form class="login-container">
 								 	<input type="text" placeholder="Your Name">
   								 		<input type="email" placeholder="Email">
   		 								
   		 								<input type="text" placeholder="Contact no.">
   									   <input type="submit" value="Submit">
 								 </form>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>
</section>


<!--
<script type="text/javascript">

	let albumCard = `
		
				  	<div class="card">
						  <div class="card-header">Banner 1</div>
						  <div class="card-body">
						  	<img class="banner-image" src="./../img/slide1.svg" style="">
						  </div>
					  	<div class="card-footer">
							  	<div class="row">

							  		<div class="col-1 col-xs-1 col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
							  		
							  		<div class="col-8 col-xs-8 col-sm-8 col-md-8 col-lg-8 col-xl-8">
							  			<div class="file btn  btn-file banner-upload" style="">
		                        			Upload Album
		                      			<input type="file" name="" style="" >
		                    			</div>
							  		</div>
							  	</div>
							  	
							  	
							  		<div class="col-12" >
							  			<input type="text"  class="form-control" name="" placeholder="Input Album Title" style="margin:10px;">
							  		</div>
							
							  		
							  		<div class="col-12">
							  			<textarea  class="form-control" rows="8" cols="40" placeholder="Describe Album here..." style="margin-left: 10px;"></textarea>
							  		</div>
							  		<div class="col-12">
							  		<button type="button" class="btn btn-success btn-sm btn-block my-3 mx-2">Save Album</button >
							  		</div>
							
							  		
							  
					  </div>
					</div>
				
	`;



	document.addEventListener('DOMContentLoaded', () => {
		addNewSampleAlbum()
	});

	function addNewSampleAlbum()
	{
		let element = document.getElementById('AddNewSampleAlbum');

		let newSample = document.createElement('div');
		newSample.className = "col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4";
		newSample.innerHTML += albumCard;

		element.appendChild(newSample);

	}
</script>-->
<script type="text/javascript">
	   	const toggles = document.querySelectorAll(".wyc-toggle");

toggles.forEach((toggle) => {
  toggle.addEventListener("click", () => {
    toggle.parentNode.classList.toggle("active");
  });
});

function deleteQuestion()
{
	const ques=document.getElementById('WhyChoose');
	ques.remove();
}
</script>		
<script src='/superuser/js/super-admin.js'></script>
</body>
</html>