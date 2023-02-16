<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Estas editando un producto</h1>
<div class="container">
	<form action="/modificarProducto" method="post">
		<div class="form-group">
			<label for="idProducto">Id producto:</label> <input
				type="text" class="form-control" id="idProducto"
				name="idProducto" value="${productoEditable.idProducto}" readonly>
		</div>
		<div class="form-group">
			<label for="nombre">Nombre del producto:</label> <input
				type="text" class="form-control" id="nombre"
				name="nombre" value="${productoEditable.nombre}" placeholder ="${productoEditable.nombre}">
		</div>
		<div class="form-group">
			<label for="descripcion">Descripcion:</label> <input
				type="text" class="form-control" id="descripcion"
				name="descripcion" value="${productoEditable.descripcion}">
		</div>
		<div class="form-group">
			<label for="price">Precio:</label> <input
				type="number" class="form-control" id="price"
				name="price" value="${productoEditable.price}">
		</div>
		<div class="form-group">
			<label for="stock">Stock:</label> <input type="number"
				class="form-control" id="stock" name="stock" value="${productoEditable.stock}">
		</div>
		<div class="form-group">
		<Select id="estado" name="estado">
			<Option value = "Normal"> Normal </option>
			<Option value = "Oferta"> Oferta </option>
			<Option value = "Destacado"> Destacado </option>
		</Select>
		</div>
		<button type="submit" class="btn btn-primary">Actualizar</button>
	</form>
</div>
	
	
</body>
</html>