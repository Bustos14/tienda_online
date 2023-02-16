<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
     integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Mensajes : ${mensaje }</h3>

	<h1>Alta Producto</h1>
<div class="container">
	<form action="/altaProducto" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="nombre">Nombre del producto:</label> <input
				type="text" class="form-control" id="nombre"
				name="nombre">
		</div>
		<div class="form-group">
			<label for="descripcion">Descripcion:</label> <input
				type="text" class="form-control" id="descripcion"
				name="descripcion">
		</div>
		<div class="form-group">
			<label for="price">Precio:</label> <input
				type="number" class="form-control" id="price"
				name="price">
		</div>
		<div class="form-group">
			<label for="stock">Stock:</label> <input type="number"
				class="form-control" id="stock" name="stock">
		</div>
		<div class="form-group">
		<label for="tipo">Tipo de producto:</label> 
		<Select id="tipo" name="tipo">
		<c:forEach var="ele" items="${tipos}">
			<Option value = "${ele.idTipo}"> ${ele.nombreTipo} </option>
			</c:forEach>
		</Select>
		</div>
		<div class="form-group">
		<label for="estado">Estado del producto:</label> 
		<Select id="estado" name="estado">
			<Option value = "Normal"> Normal </option>
			<Option value = "Oferta"> Oferta </option>
			<Option value = "Destacado"> Destacado </option>
		</Select>
		</div>
			<div class="form-group">
			<label for="imagen">Imagen:</label> 
			<input type="file" class="form-control" id="imagen" name="file">
		</div>

		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
	</form>
</div>
</body>
</html>