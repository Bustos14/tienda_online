<%@page import="com.edix.grupo.tienda.full.stack.java.entitybeans.Producto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="inicio.jsp"></jsp:include>

<div class="container">
	<h1>Detalle de la direcci�n</h1>
	<form action="/" method="get">
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
		
		<img src="/recursos/${producto.img}"alt="${producto.img}"style="max-width:25%;">
		
		<br>
		<button type="submit" class="btn btn-primary">Volver</button>
	</form>
</div>
	
</body>
</html>


</html>