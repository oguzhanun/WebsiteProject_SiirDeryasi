<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Şiir Deryasına Hoşgeldiniz</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
		<style type="text/css">
			body{
				box-sizing:border-box;
				/*background-color:gray;*/ 
			}
			.errors{
				color:red;
			}
		</style>
		<link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
	</head>
	<body style="background-color:black;">
	
		<!-- NAVBAR KISMI -->
		<div id="navbar" style=" line-height:35px;" class="container-fluid border mb-3 border-white sticky-top bg-success 
						 py-1  d-flex flex-row justify-content-start align-items-center"
				 role="navigation">
				 
			<div class="d-flex col flex-row" >
				<div style="width:30%; font-family:'Pacifico', cursive;  color:white; " 
					class="navbar-brand nav-item align-self-end mr-4 text-warning "><h1 style="font-size:30px;">Şiir Deryası</h1></div>
				<div style="width:20%; " class="nav-item  mr-3">
					<a class="nav-link text-align-center" style="color:black;" href="${pageContext.request.contextPath}/anasayfa">Anasayfa</a></div>
			
			<!--<div style="width:15%; " class="nav-item border-right border-dark mr-3">
					<a class="nav-link text-align-center " style="color:black;" href="#">Epik</a>
				</div>
				<div style="width:15%; " class="nav-item border-right border-dark mr-3">
					<a class="nav-link text-align-center " style="color:black;" href="#">Lirik</a>
				</div>
				<div style="width:15%; " class="nav-item border-right border-dark mr-3">
					<a class="nav-link  text-align-center " style="color:black;" href="#">Özgün</a>
				</div>
			-->
			
			</div>
			
			<div class="d-flex col flex-row-reverse">
				<div class="border border-dark p-2 bg-secondary rounded">
					<p class="d-inline text-white">Yeni şiirlerden haberdar olmak için</p>
					<button id="modalButton" class="btn btn-dark border border-white mr-1 text-white" >Kayıt Ol</button> <!-- data-toggle="modal" data-target="#exampleModal" -->
				</div>
				<c:if test="${kullaniciVar}"><small class="errors">Kullanıcı adı zaten kayıtlı !!! </small></c:if>
				<!-- <div><a class="btn btn-primary mr-3">Üye Girişi</a></div> -->
			</div>
			
		
		

		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Kayıt</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <div>
		        	<form action="${pageContext.request.contextPath}/uyeKayit" method="POST" autoComplete="off">
		        		<div class="form-group">
		        			<label for="email">Email :</label>
		        			<input type="text" autoComplete="off" name="email" id="email" class="form-control">
		        			
		        			<label for="isim">İsminiz :</label>
		        			<input type="text" autoComplete="off" name="isim" id="isim" class="form-control">
		        		</div>
		        		<div>
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">İptal</button>
					        <button type="submit" class="btn btn-primary">Tamam</button>
					        <br>
					        <small style="font-size:12px;">* Aktivasyon için mail adresinize gelen linke tıklamanız gerekmektedir.</small>
			     		</div>
		      		</form>
		      	</div>
		    </div>
		  </div>
		 </div>
		</div>
		
		<!-- HEAD KISMI -->
		<div id="head" class="container rounded d-flex bg-success border border-white " style="background-color:#336600; height:200px;">
			<img style="height:90%;width:20%;" class="d-flex  pt-3" src="${pageContext.request.contextPath}/resources/images/siir_kalem.png" alt="kalem ve şiir"/>
			<div class=" d-flex align-self-center text-dark mx-auto" style=""><p class="text-capitalize display-4" 
					style="font-family:'Pacifico', cursive;">Şiir Deryasına Hoşgeldiniz...</p></div>
			<!-- <div class="clearfix w-150 h-100 text-success">*******************</div> -->
		</div>
		
		<!-- İCERİK KISMI -->	
		<div id="icerik" class="container rounded mt-3 border-top border-right border-left border-bottom border-white ">
		
			<div class="row justify-content-center" style="background-color:black;">
				<div class="col-8 card my-4 border-0 p-0 text-white" >
					<div class="card-deck   " >
						<div class="card mr-3 " style=" background-color:black">
							<img class="card-img-top" style="height:350px;" src="https://images.pexels.com/photos/556416/pexels-photo-556416.jpeg?auto=compress&cs=tinysrgb&h=350">
							<button id="play" class="btn btn-success" style="position:absolute; z-index:1;" ><i class="fas fa-volume-up"></i></button>
							
							<div class="card-body ">
								<br>
								<h2 style="text-align:center" class="card-title">${siirAdi}</h2>
								<br>
								
								<!--
								<p class="card-text px-5 text-justify" >
									Lorem ipsum dolor sit amet, <br>
									consectetur adipisicing elit.<br> 
									Voluptates illo ratione officia perferendis, <br>
									veritatis dolorem beatae repellat commodi, <br>
									ex eaque sunt. Cupiditate animi excepturi <br>
									<br>
								</p>
								<p class="card-text  px-5 text-justify" id="dus">
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
								-->
								<!-- PARAGRAFA COLLAPSE CLASS INI VEREREK AÇ KAPA YAPTIRABİLİRİZ
								<a class="btn btn-warning" id="toggle" data-toggle="collapse" role="button" href="#dus" aria-expanded="false"
							 		aria-controls="dus" >
							 		<span style="text-color:black;" id="gosterKapa" data-text="Kapat">Devam Et</span>
							 	</a>
							 	 -->
							 	 
								 <div >
  									<p>	<embed src="${pageContext.request.contextPath}${siirAdres}" style="border:0;" height="1200" width="95%">
      									
      									</embed>
      									
      								</p>
								</div>
							</div>
						</div>
						
						
					</div>	
				</div>
						
				<div class="col-3 card bg-info my-4 ml-3 p-3 text-light">
					
					<div class="card-header"><h4>En Beğenilenler</h4></div>
					<div class="card-body text-light">
						<ul class="list-group ">
							<!-- 
								
							-->
							<c:forEach var="siir" items="${siirler}">
								<li class="list-group-item d-flex justify-content-between align-items-center">
							    	<a href="${pageContext.request.contextPath}/anasayfa/${siir.siirLinkAdi}">${siir.siirAdi}</a>
							    	<span class="badge badge-primary badge-pill">${siir.tiklama}</span>
							  	</li>
							</c:forEach>
							  
							  <!-- <li class="list-group-item d-flex justify-content-between align-items-center">
							    <a href="${pageContext.request.contextPath}/anasayfa/SensizOlmuyor">Sensiz Olmuyor</a>
							    <span class="badge badge-primary badge-pill">${tiklamaSayisi}</span>
							  </li>
							  <li class="list-group-item d-flex justify-content-between align-items-center">
							    <a href="#">Git Gidebildiğin Kadar</a>
							    <span class="badge badge-primary badge-pill">2</span>
							  </li>
							  <li class="list-group-item d-flex justify-content-between align-items-center">
							    <a href="${pageContext.request.contextPath}/brehBreh">Breh Breh</a>
							    <span class="badge badge-primary badge-pill">${count2}</span>
							  </li>
							  -->
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
		<div id="footer" class="container-fluid d-flex mt-3 bg-success  align-items-center justify-content-center" style="height:150px; 
				background-color:rgba(201, 76, 76, 0.3);">
			<div class="d-flex ">
				<p class=" text-white  " style="font-size:12px;"> © 2018 - Şiir Deryası</p>
			</div>
			
		</div>
		
			
			
			
		<!-- Optional JavaScript -->
	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	    
	    <!-- 
	    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	    -->
	    <script>
	    	
	    	$(document).ready(function() {
	    		
	    		$('#modalButton').click(function(){
	 				$('#exampleModal').modal('show');	
	 			});
	 	
	 	        $('#modalBox').on('show.bs.modal', function() {
	 	
	 				console.log('show');
	 			});
	 	
	 			$('#modalBox').on('hidden.bs.modal', function() {
	 	
	 				console.log('hidden');
	 			}); 
		    		
	    		var audioElement = document.createElement('audio');
		        
		        audioElement.setAttribute('src', '${muzikDosyasi}');
		        
		        audioElement.addEventListener('ended', function() {
		            this.play();
		        }, false);
		        
		        audioElement.addEventListener("canplay",function(){
		            $("#length").text("Duration:" + audioElement.duration + " seconds");
		            $("#source").text("Source:" + audioElement.src);
		            $("#status").text("Status: Ready to play").css("color","green");
		        });
		        
		        audioElement.addEventListener("timeupdate",function(){
		            $("#currentTime").text("Current second:" + audioElement.currentTime);
		        });
		        
		        var hopla = true;
		        
		        $('#play').click(function() {
		        	if(hopla){
			            audioElement.play();
			            $("#status").text("Status: Playing");
			            hopla=false;
		        	}else{
		        		audioElement.pause();
		        		hopla=true;
		        	}
	        	});
		        
		        
		        
		        $('#pause').click(function() {
		            audioElement.pause();
		            $("#status").text("Status: Paused");
		        });
		        
		        $('#restart').click(function() {
		            audioElement.currentTime = 0;
		        });
		    });
	    	
		   /* $(document).ready(function(){
	    		
	    		$("#gosterKapa").click(function(){
	    			
	    			var oldText = $("#gosterKapa").text();
	    			
	    			var newText = $(this).data('text');
	    			
	    			$("#gosterKapa").text(newText).data('text',oldText);
	    		});
	    	});
	    	*/
	    </script>
	    
	    
	</body>
</html>