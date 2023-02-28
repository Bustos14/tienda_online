<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle de tu tarjeta</title>
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

<jsp:include page="inicio.jsp"></jsp:include>

<div class="container p-3 mt-3" style="background-color: rgba(51, 51, 51, 0.6); color: white; border-radius: 25px">
	<h3 class="text-danger">${mensaje }</h3>
	<h1>Detalle del Pedido</h1>
	<form action="/" method="get">
		<div class="form-group">
			<label for="idTarjetaBancaria">Id del pedido:</label> <input
				type="text" class="form-control" id="idTarjetaBancaria"
				name="idTarjetaBancaria" value="${pedido.idPedido}" readonly>
		</div>
		<div class="form-group">
			<label for="numeroTarjeta">Fecha de realización:</label> <input
				type="text" class="form-control" id="numeroTarjeta"
				name="numeroTarjeta" value="${pedido.fechaRealizacion}" readonly>
		</div>
		<div class="form-group">
			<label for="nombreTitular">Precio total del pedido:</label> <input
				type="text" class="form-control" id="nombreTitular"
				name="nombreTitular" value="${pedido.precioTotal}" readonly>
		</div>
		<a href="/" class="btn btn-primary"> Volver</a>
	<table class="table table-striped">
		<br><br>
		<h1>Los articulos del Pedido</h1>
	    <thead>
	        <tr>
	            <th>Artículo</th>
	            <th>Cantidad</th>
	            <th>Precio unitario</th>
	        </tr>
	    </thead>
	    <tbody>
		    <c:forEach var="ele" items="${articulosPedido}">
		      <tr>
		        <td>${ele.producto.nombre}</td>
		        <td>${ele.cantidad}</td>
		        <td>${ele.producto.price}</td>
		      </tr>
	   		</c:forEach>
		    <c:if test="${not empty error}">
		        <tr>
		            <p style="text-align:center; color:red;">${error}</p>
		        </tr>
		    </c:if>
		</tbody>
</table>
	</form>
</div>
		<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>