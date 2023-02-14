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
<h3>Mensajes : ${mensaje }</h3>

	<h1>Alta Producto</h1>
<div class="container">
	<form action="/altaProducto" method="post">
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
		<Select id="estado" name="estado">
			<Option value = "Normal"> Normal </option>
			<Option value = "Oferta"> Oferta </option>
			<Option value = "Destacado"> Destacado </option>
		</Select>
		</div>
		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
	</form>
</div>
</body>
</html>