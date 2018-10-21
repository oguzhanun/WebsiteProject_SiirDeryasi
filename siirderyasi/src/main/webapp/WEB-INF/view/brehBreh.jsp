<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Şiir Dünyam'a Hoşgeldiniz</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<style type="text/css">
			body{
				box-sizing:border-box;
				background-color:gray; 
			}
		</style>
	</head>
	<body>
		<!-- NAVBAR KISMI -->
		<div id="navbar" class="container-fluid sticky-top bg-secondary text-warning py-2 d-flex flex-row justify-content-start align-items-center"
				 role="navigation">
				 
			<div class="d-flex col flex-row">
				<div style="width:15%; " class="navbar-brand nav-item align-self-end mr-5">Şiir Dünyam</div>
				<div style="width:12%;" class="nav-item border-right border-warning mr-3"><a class="nav-link text-align-center text-warning " href="#">Epik</a></div>
				<div style="width:12%;" class="nav-item border-right border-warning mr-3"><a class="nav-link text-align-center text-warning" href="#">Lirik</a></div>
				<div style="width:12%;" class="nav-item border-right border-warning mr-3"><a class="nav-link text-align-center text-warning" href="#">Divan</a></div>
				<div style="width:12%;" class="nav-item border-right border-warning mr-3"><a class="nav-link  text-align-center text-warning" href="#">Özgün</a></div>
			</div>
			
			<div class="d-flex col flex-row-reverse">
				<div><a class="btn btn-success mr-3">Üye Ol</a></div>
				<div><a class="btn btn-primary mr-3">Üye Girişi</a></div>
			</div>
		</div>
		
		<!-- HEAD KISMI -->
		<div id="head" class="container-fluid" style="background-color:rgba(201, 76, 76, 0.3); height:200px;"></div>
		
		<!-- İCERİK KISMI -->	
		<div id="icerik" class="container ">
		
			<div class="row justify-content-center">
				<div class="col-8 card mt-4 mx-2 p-0 text-white" style="background-color:black;">
					
					<img class="card-img-top" src="https://images.pexels.com/photos/210186/pexels-photo-210186.jpeg?auto=compress&cs=tinysrgb&h=350">
			
					<div class="card-body ">
						<br>
						<h2 style="text-align:center" class="card-title">Breh Breh</h2>
						<br>
						<p class="px-5 text-justify">
							Lorem ipsum dolor sit amet, <br>
							consectetur adipisicing elit.<br> 
							Voluptates illo ratione officia perferendis, <br>
							veritatis dolorem beatae repellat commodi, <br>
							ex eaque sunt. Cupiditate animi excepturi <br>
							<br>
							dolore unde, quibusdam ducimus quae ullam<br>
							consequatur reprehenderit sint deleniti <br>
							<br>
							placeat est fugiat explicabo inventore, <br>
							harum illum aut facilis eum consequuntur <br>
							aperiam libero ipsum nulla laudantium.<br>
							<br>
							Lorem ipsum dolor sit amet, <br>
							consectetur adipisicing elit.<br> 
							Voluptates illo ratione officia perferendis, <br>
							veritatis dolorem beatae repellat commodi, <br>
							ex eaque sunt. Cupiditate animi excepturi <br>
							<br>
							dolore unde, quibusdam ducimus quae ullam<br>
							consequatur reprehenderit sint deleniti <br>
							<br>
							placeat est fugiat explicabo inventore, <br>
							harum illum aut facilis eum consequuntur <br>
							aperiam libero ipsum nulla laudantium.<br>
							<br>
							Lorem ipsum dolor sit amet, <br>
							consectetur adipisicing elit.<br> 
							Voluptates illo ratione officia perferendis, <br>
							veritatis dolorem beatae repellat commodi, <br>
							ex eaque sunt. Cupiditate animi excepturi <br>
							<br>
							dolore unde, quibusdam ducimus quae ullam<br>
							consequatur reprehenderit sint deleniti <br>
							<br>
							placeat est fugiat explicabo inventore, <br>
							harum illum aut facilis eum consequuntur <br>
							aperiam libero ipsum nulla laudantium.<br>
						</p>
					</div>	
				</div>
						
				<div class="col-3 card bg-light mt-4 p-3 text-dark">
					
					<div class="card-header"><h4>Diğer Şiirler</h4></div>
					<div class="card-body text-dark">
						<ul class="list-group">
							  <li class="list-group-item d-flex justify-content-between align-items-center">
							    <a href="${pageContext.request.contextPath}/sensizOlmuyor">Sensiz Olmuyor</a>
							    <span class="badge badge-primary badge-pill">${count}</span>
							  </li>
							  <li class="list-group-item d-flex justify-content-between align-items-center">
							    <a href="#">Git Gidebildiğin Kadar</a>
							    <span class="badge badge-primary badge-pill">2</span>
							  </li>
							  <li class="list-group-item d-flex justify-content-between align-items-center">
							    <a href=""${pageContext.request.contextPath}/brehBreh"">Breh Breh</a>
							    <span class="badge badge-primary badge-pill">${count2}</span>
							  </li>
						</ul>
						<br><br>
						dolore unde, quibusdam ducimus quae ullam<br>
						consequatur reprehenderit sint deleniti <br>
						<br>
						placeat est fugiat explicabo inventore, <br>
						harum illum aut facilis eum consequuntur <br>
						aperiam libero ipsum nulla laudantium.<br>
					</div>
				</div>
			  </div>
		</div>
		
		<!-- FOOTER KISMI -->
		<div id="footer" class="contianer-fluid bg-dark" style="height:150px;">
		</div>
		
			
			
			
		<!-- Optional JavaScript -->
	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	</body>
</html>