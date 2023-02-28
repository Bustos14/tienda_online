<%@page import="com.edix.grupo.tienda.full.stack.java.entitybeans.Producto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle del producto</title>
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

<div class="container" style="background-color: rgba(51, 51, 51, 0.6); color: white; border-radius: 25px">
	<h3 class="text-danger">${mensaje }</h3>
	<h1>Detalle del producto</h1>
	<img style="width: 200px;" src="/recursos/${producto.img}" class="img-fluid" alt="${producto.img}" >
	<form action="/" method="get">
		<br><br>	
	<div class="form-group">
			<label for="idDireccion">Nombre:</label> <input
				type="text" class="form-control" id="nombre"
				name="nombre" value="${producto.nombre}" readonly>
		</div>	
				<div class="form-group">
			<label for="localidad">Descripcion:</label> <input
				type="text" class="form-control" id="descripcion"
				name="descripcion" value="${producto.descripcion}" readonly>
		</div>
		<div class="form-group">
			<label for="codigoPostal">Precio:</label> <input
				type="text" class="form-control" id="price"
				name="price" value="${producto.price}&euro;" readonly>
		</div>
		<div class="form-group">
			<label for="calle">Stock:</label> <input
				type="text" class="form-control" id="stock"
				name="stock" value="${producto.stock}" readonly>
		</div>
		<div class="form-group">
			<label for="numero">Estado actual:</label> <input type="text"
				class="form-control" id="estado" name="estado" value="${producto.estado}" readonly>
		</div>
				
		<div class="col text-center">
			<button type="submit" class="btn btn-primary" style="width: 200px;">Volver</button>
		</div>
	</form>
</div>
	
</body>
</html>
	<jsp:include page="footer.jsp"></jsp:include>

</html>