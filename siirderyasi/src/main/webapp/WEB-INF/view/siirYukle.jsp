<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Şiir Yükleme Sayfası</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<br><br>
		<h1>Şiir Yükleyiniz...</h1>
		<br><br>
		<div class="col-8">
			<form action="${pageContext.request.contextPath}/siirIsle" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label>Şiir Adı :</label>
					<input type="text" class="form-control" id="siirAdi" name="siirAdi"/>
					<br><br>
					
					<label>Şiir Link Adı :</label>
					<input type="text" class="form-control" id="siirLinkAdi" name="siirLinkAdi"/>
					<br><br>
					
					<label>Müzik Dosyası Adresi :</label>
					<input type="text" class="form-control" id="muzik" name="muzik"  />
					<br><br>
				
				    <label for="dosya">Dosya seçin :</label>
				    <input type="file" class="form-control-file" id="dosya" name="dosya">
		  		
					<br><br>
					<input type="submit" value="Yükle"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>