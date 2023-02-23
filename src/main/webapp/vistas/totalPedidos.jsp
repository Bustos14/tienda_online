<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado total de los pedidos</title>
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
		<h1 class="text-primary">Total de pedidos para el usuario: ${username}</h1>
		
		<!-- Tabla de pedidos -->
		<table class="table table-striped table-sm">
			<thead>
				<tr>
					<th>Cantidad de pedidos</th>
					<th>Precio total de pedidos</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${cantidadPedidos}</td>
					<td>${precioTotalPedidos} &euro;</td>
				</tr>
			</tbody>
			<td><a href="/usuario/usuarios" class="btn btn-primary btn-sm">Volver al listado</a></td>
		</table>
	</div>
	
	<!-- Importar jQuery, Popper.js y Bootstrap -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
