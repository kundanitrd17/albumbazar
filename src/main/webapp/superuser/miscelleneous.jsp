<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!DOCTYPE html>
	<html>

	<head>
		<title>Cart</title>

		<meta charset="utf-8">
		<meta name="_csrf" content="${_csrf.token}" />
		<!-- default header name is X-CSRF-TOKEN -->
		<meta name="_csrf_header" content="${_csrf.headerName}" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

		<style type="text/css">
			.SuperUserBanner .card,
			.SuperUserBanner .card .card-header,
			.SuperUserBanner .card .card-footer,
			.SuperUserAddAlbum .card,
			.SuperUserAddAlbum .card .card-header,
			.SuperUserAddAlbum .card .card-footer {
				border: none;
				background-color: white;
			}

			.SuperUserBanner .card,
			.SuperUserAddAlbum .card,
			.insertAnsQues {
				box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				-webkit-box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				-moz-box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				margin-top: 50px;
			}

			.SuperUserBanner .card .card-footer .banner-upload,
			#UploadNewBanner,
			.SuperUserAddAlbum .card .card-footer .banner-upload {
				overflow: hidden;
				position: relative;
				background-color: #09024B;
				color: white;
				font-size: 12px;

			}

			.SuperUserBanner .card .card-footer .banner-delete:hover {
				box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				-webkit-box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				-moz-box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				background-color: white;
				color: #BE2632;
			}

			.SuperUserAddAlbum .card .card-footer .banner-delete:hover {
				box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				-webkit-box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				-moz-box-shadow: 10px 7px 24px -1px rgba(225, 115, 139, 0.75), -10px -7px 24px -1px rgba(225, 115, 139, 0.75);
				background-color: white;
				color: #BE2632;
			}

			.SuperUserBanner .card .card-footer .banner-upload:hover {
				box-shadow: 10px 7px 24px -1px rgba(94, 130, 227, 0.75), -10px -7px 24px -1px rgba(94, 130, 227, 0.75);
				-webkit-box-shadow: 10px 7px 24px -1px rgba(94, 130, 227, 0.75), -10px -7px 24px -1px rgba(94, 130, 227, 0.75);
				-moz-box-shadow: 10px 7px 24px -1px rgba(94, 130, 227, 0.75), -10px -7px 24px -1px rgba(94, 130, 227, 0.75);
				background-color: white;
				color: #0917BC;
				font-weight: bold;
			}

			.SuperUserAddAlbum .card .card-footer .banner-upload:hover {
				box-shadow: 10px 7px 24px -1px rgba(94, 130, 227, 0.75), -10px -7px 24px -1px rgba(94, 130, 227, 0.75);
				-webkit-box-shadow: 10px 7px 24px -1px rgba(94, 130, 227, 0.75), -10px -7px 24px -1px rgba(94, 130, 227, 0.75);
				-moz-box-shadow: 10px 7px 24px -1px rgba(94, 130, 227, 0.75), -10px -7px 24px -1px rgba(94, 130, 227, 0.75);
				background-color: white;
				color: #0917BC;
				font-weight: bold;
			}

			.SuperUserBanner .card .card-footer .banner-upload input,
			#UploadNewBanner input,
			.SuperUserAddAlbum .card .card-footer .banner-upload input {
				cursor: pointer;
				position: absolute;
				font-size: 50px;
				opacity: 0;
				right: 0;
				top: 0;
			}

			.SuperUserBanner .card-header,
			.SuperUserAddAlbum .card-header {
				text-align: center;
			}

			.SuperUserBanner {
				margin-top: 30px;
				padding: 40px;
				box-shadow: 10px 7px 24px -1px rgba(131, 107, 107, 0.75);
				-webkit-box-shadow: 10px 7px 24px -1px rgba(131, 107, 107, 0.75);
				-moz-box-shadow: 10px 7px 24px -1px rgba(131, 107, 107, 0.75);
			}

			.SuperUserBanner .card-body .banner-image,
			.SuperUserAddAlbum .card-body .banner-image {
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



			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="mx-2">
					<a href="#" data-show="show-side-navigation1" class="btn fa fa-bars show-side-btn"></a>
				</div>
				<!-- Image and text -->
				<a class="navbar-brand" href="/">
					<img src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" width="30" height="30"
						class="d-inline-block align-top" alt="">
					AlbumBazaar
				</a>

				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
					aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarTogglerDemo02">

					<ul class="navbar-nav mr-auto">
						<li class="nav-item"></li>
					</ul>

					<ul class="navbar-nav justify-content-end mt-2 mt-lg-0">
						<li class="nav-item active mx-2">
							<a class="nav-link" href="/superuser">Home <span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item mx-2">
							<a class="nav-link active" href="/superuser/order-list?status=pending">Order</a>
						</li>
						<li class="nav-item mx-2">
							<a class="nav-link active" href="/superuser/customer">Customer</a>
						</li>
						<li class="nav-item mx-2">
							<a class="nav-link active" href="/customer-care/order-pool">CMR</a>
						</li>
						<li class="nav-item mx-2">
							<form action="/superuser/logout" method="POST">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<button class="btn nav-link active" type="submit">logout</button>
							</form>

						</li>
					</ul>

				</div>
			</nav>



			<section>
				<div class="container-fluid">

					<div class="row SuperUserBanner">

						<div class=" col-12 banner-heading">
							<h3>Customise Banner</h3>

							<div>
								<form action="/superuser/carasoul" method="post" enctype="multipart/form-data">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

									<div class="file btn btn-file banner-upload" style="" id="UploadNewBanner">
										Upload New Banner
										<input type="file" name="image" style="">
									</div>

									<button class="btn btn-primary" type="submit">create</button>
								</form>
							</div>

						</div>

						<c:forEach items="${carasouls}" var="carasoul">

							<div id="carasoul${carasoul.id}"
								class="container col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4">
								<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<input type="text" name="id" value="${carasoul.id}" hidden> -->
								<div class=" container card">
									<div class="card-header">Banner 1</div>
									<div class="card-body">
										<img class="banner-image" src="${carasoul.image}" style="">
									</div>
									<div class="card-footer">
										<div class="row">
											<div class="col-12 col-xs-12 col-sm-12 col-md-2 col-lg-2 col-xl-2">

											</div>

											<div class="col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">
												<button onclick="deleteCarasoul('${carasoul.id}')"
													class="btn btn-danger banner-delete"><i
														class=" fa fa-trash"></i></button>
											</div>

											<!-- <div class="col-9 col-xs-9 col-sm-9 col-md-6 col-lg-6 col-xl-6">
												<div class="file btn  btn-file banner-upload" style="">
													Upload Banner

													<input type="file" name="image" style="">
												</div>


											</div>

											<div>
												<button class="btn btn-primary">submit</button>
											</div> -->

											<!-- <div class="col-12 col-xs-12 col-sm-12 col-md-1 col-lg-1 col-xl-1">

											</div> -->

										</div>
									</div>
								</div>

							</div>

						</c:forEach>
						<!-- 
						<div class="container col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4">
							<div class="container card">
								<div class="card-header">Banner 2</div>
								<div class="card-body"><img class="banner-image" src="../img/slide1.svg" style=""></div>
								<div class="card-footer">
									<div class="row">
										<div class="col-12 col-xs-12 col-sm-12 col-md-2 col-lg-2 col-xl-2">

										</div>

										<div class="col-3 col-xs-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">
											<a href="" class="btn btn-danger banner-delete"><i
													class=" fa fa-trash"></i></a>
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
											<a href="" class="btn btn-danger banner-delete"><i
													class=" fa fa-trash"></i></a>
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
						 -->
					</div>

				</div>
			</section>

			<section>

				<div class="container-fluid">


					<div class="row SuperUserAddAlbum" id="AddNewSampleAlbum">

						<div class="col justify-content-start">
							<button type="button" class="btn btn-info btn-lg my-4" data-toggle="modal"
								data-target="#myModal" onclick="addNewSampleAlbum();">
								Add New Sample Album ➕
							</button>
							<hr style="width: 70%;">
						</div>


						<div class="col-12 banner-heading">
							<h3>Customise Album</h3>
						</div>


						<c:forEach items="${sample_albums}" var="each_sample_album">

							<form enctype="multipart/form-data" action="/superuser/sample-album" method="POST"
								id="sampleAlbum${each_sample_album.id}"
								class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<input type="hidden" name="id" value="${each_sample_album.id}" hidden>
								<div class="card">
									<div class="card-header">${each_sample_album.title}</div>
									<div class="card-body">
										<img class="banner-image" src="${each_sample_album.image}" style="">
									</div>
									<div class="card-footer">
										<div class="row">

											<div class="col-1 col-xs-1 col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
											<div class="col-2 col-xs-2 col-sm-2 col-md-2 col-lg-2 col-xl-2">
												<a onclick="deleteSampleAlbum('${each_sample_album.id}')" href="#"
													class="btn btn-danger banner-delete"><i
														class=" fa fa-trash"></i></a>
											</div>
											<div class="col-8 col-xs-8 col-sm-8 col-md-8 col-lg-8 col-xl-8">
												<div class="file btn  btn-file banner-upload" style="">
													Upload Album
													<input type="file" name="" style="">
												</div>
											</div>
										</div>


										<div class="col-12">
											<input type="text" class="form-control" name="title"
												value="${each_sample_album.title}" placeholder="Input Album Title"
												style="margin:10px;">
										</div>


										<div class="col-12">
											<textarea class="form-control" rows="8" cols="40" name="description"
												placeholder="Describe Album here..." style="margin-left: 10px;">
												${each_sample_album.description}
											</textarea>
										</div>
										<div class="col-12">
											<button type="submit"
												class="btn btn-success btn-sm btn-block my-3 mx-2">Save
												Album</button>
										</div>



									</div>
								</div>
							</form>


						</c:forEach>


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

										<form action="/superuser/sample-album" method="POST"
											enctype="multipart/form-data"
											class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											<div class="card">
												<div class="card-header">Sample Album</div>
												<div class="card-body">
													<img class="banner-image" src="./../img/slide1.svg" style="">
												</div>
												<div class="card-footer">
													<div class="row">

														<div
															class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
															<div class="file btn  btn-file banner-upload" style="">
																Upload Album
																<input type="file" name="image" style="">
															</div>
														</div>
													</div>


													<div class="col-12">
														<input type="text" class="form-control" name="title"
															placeholder="Input Album Title" style="margin:10px;">
													</div>


													<div class="col-12">
														<textarea name="description" class="form-control" rows="8"
															cols="40" placeholder="Describe Album here..."
															style="margin-left: 10px;"></textarea>
													</div>
													<div class="col-12">
														<button type="submit"
															class="btn btn-success btn-sm btn-block my-3 mx-2">Save
															Album</button>
													</div>



												</div>
											</div>
										</form>


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
						<div class="col-12" style="padding-bottom: 10px;">
							<h3>Insert Question And Answer</h3>
						</div>

						<form class="col-12" action="/superuser/frequent/question" method="POST">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<div class="col-12">
								<div class="form-group">
									<label for="pwd">Insert Question:</label>
									<input type="text" class="form-control" id="pwd" name="question">
								</div>
							</div>
							<div class="col-12">
								<div class="form-group">
									<label for="comment">Give Answer</label>
									<textarea name="answer" class="form-control" rows="5" id="comment"></textarea>
								</div>
							</div>
							<div class="col-12">
								<button type="submit" class="btn btn-success btn-sm my-3 mx-2">Save Answer</button>
							</div>


						</form>

						<c:forEach items="${frequent_questions}" var="each_question">

							<div class="col-12 wyc-container" id="frequentQuestion${each_question.id}">

								<div class="wyc ">

									<h5 class="wyc-title"><span>
											${each_question.question}
										</span></h5>
									<p class="wyc-text">${each_question.answer}</p>
									<button class="wyc-toggle">
										<i class="fa fa-chevron-down"></i>
										<i class="fa fa-times"></i>

									</button>
									<button href="#" class="btn btn-danger question-delete"
										style="padding: 2px 8px;border-radius:20px;margin-left: 40px;position: absolute;top: 0px;left: -40px;"
										onclick="deleteQuestion('${each_question.id}')"><i
											class=" fa fa-trash"></i></button>

								</div>

							</div>
						</c:forEach>





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


				var header = $("meta[name='_csrf_header']").attr("content");
				var token = $("meta[name='_csrf']").attr("content");


				document.addEventListener('DOMContentLoaded', () => {
					header = $("meta[name='_csrf_header']").attr("content");
					token = $("meta[name='_csrf']").attr("content");

				});


				function deleteCarasoul(id) {


					const xhr = new XMLHttpRequest();
					const url = "/api/superuser/carasoul";
					xhr.open('DELETE', url, true);
					xhr.setRequestHeader('Content-type', 'application/json');
					xhr.setRequestHeader(header, token);

					xhr.onreadystatechange = function () {
						if (this.readyState === 4 && this.status === 200) {
							alert("success");
							$('#carasoul' + id).remove();
						} else if (this.readyState === 4 && this.status === 500) {
							alert("Unable to delete... Try Again!");
						}
					}

					xhr.send(id);

				}

				function deleteSampleAlbum(id) {


					const xhr = new XMLHttpRequest();
					const url = "/api/superuser/sample-album";
					xhr.open('DELETE', url, true);
					xhr.setRequestHeader('Content-type', 'application/json');
					xhr.setRequestHeader(header, token);

					xhr.onreadystatechange = function () {
						if (this.readyState === 4 && this.status === 200) {
							alert("success");
							$('#sampleAlbum' + id).remove();
						} else if (this.readyState === 4 && this.status === 500) {
							alert("Unable to delete... Try Again!");
						}
					}

					xhr.send(id);

				}


				function deleteQuestion(id) {


					const xhr = new XMLHttpRequest();
					const url = "/api/superuser/frequent/question";
					xhr.open('DELETE', url, true);
					xhr.setRequestHeader('Content-type', 'application/json');
					xhr.setRequestHeader(header, token);

					xhr.onreadystatechange = function () {
						if (this.readyState === 4 && this.status === 200) {
							alert("success");
							$('#frequentQuestion' + id).remove();
						} else if (this.readyState === 4 && this.status === 500) {
							alert("Unable to delete... Try Again!");
						}
					}

					xhr.send(id);

				}


				const toggles = document.querySelectorAll(".wyc-toggle");



				toggles.forEach((toggle) => {
					toggle.addEventListener("click", () => {
						toggle.parentNode.classList.toggle("active");
					});
				});


			</script>
			<script src='/superuser/js/super-admin.js'></script>
			<script type="text/javascript">
				// Other important pens.
				// Map: https://codepen.io/themustafaomar/pen/ZEGJeZq
				// Navbar: https://codepen.io/themustafaomar/pen/VKbQyZ

				$(function () {

					'use strict';

					var aside = $('.side-nav'),
						showAsideBtn = $('.show-side-btn'),
						contents = $('#contents'),
						_window = $(window)

					showAsideBtn.on("click", function () {

						$(".side-nav .close-aside" + $(this).data('show')).toggleClass('show-side-nav');
						contents.toggleClass('margin');
					});

					if (_window.width() > 0) {
						aside.addClass('show-side-nav');
					}

					_window.on('resize', function () {
						if ($(window).width() > 767) {
							aside.removeClass('show-side-nav');
						}
					});

					// dropdown menu in the side nav
					var slideNavDropdown = $('.side-nav-dropdown');

					$('.side-nav .categories li').on('click', function () {

						var $this = $(this)

						$this.toggleClass('opend').siblings().removeClass('opend');

						if ($this.hasClass('opend')) {
							$this.find('.side-nav-dropdown').slideToggle('fast');
							$this.siblings().find('.side-nav-dropdown').slideUp('fast');
						} else {
							$this.find('.side-nav-dropdown').slideUp('fast');
						}
					});

					$('.side-nav .close-aside').on('click', function () {
						$('#' + $(this).data('close')).addClass('show-side-nav');
						contents.removeClass('margin');
					});



				});
			</script>
	</body>

	</html>