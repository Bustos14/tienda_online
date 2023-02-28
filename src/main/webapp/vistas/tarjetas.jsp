<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de tus tarjetas</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style type="text/css">
	body {
  background-image: url("/img/fondo.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}
    
	
</style>
</head>
<body>
<br>
<jsp:include page="inicio.jsp"></jsp:include>

<br>

<div class="container p-5" style="background-color: rgba(51, 51, 51, 0.6); color: white; border-radius: 25px">
	<h3 class="text-danger">${mensaje }</h3>
	<h1 class="text-primary">Lista de tarjetas</h1>
	<a href="/tarjeta/alta" class="btn btn-primary btn-sm">Nueva Tarjeta</a>
	<br><br>
	<table class="table table-striped table-sm">
		<th>Id de la tarjeta</th>
		<th>Nombre del titular</th>
		<th>Numero de la tarjeta</th>
		<th>Fecha caducidad</th>
		<th>Cvv</th>
		<th>Detalle</th>
		<th>Modificar</th>
		<th>Eliminar</th>
		
		<c:forEach var="ele" items="${todasTarjetas }">
			<tr>
				<td>${ele.idTarjetaBancaria}</td>		
				<td>${ele.nombreTitular}</td>				
				<td>${ele.numeroTarjeta}</td>				
				<td>${ele.fechaCaducidad}</td>		
				<td>${ele.cvv}</td>	
				<td><a href="/tarjeta/verTarjeta/${ele.idTarjetaBancaria}" class="btn btn-success btn-sm">Detalle</a></td>
				<td><a href="/tarjeta/editar/${ele.idTarjetaBancaria}" class="btn btn-success btn-sm">Modificar</a></td>
				<td><a href="/tarjeta/eliminar/${ele.idTarjetaBancaria}" class="btn btn-danger btn-sm">Eliminar</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

	
		<jsp:include page="footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>