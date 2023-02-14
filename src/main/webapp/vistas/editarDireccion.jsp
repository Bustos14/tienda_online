<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>


	<h1>Estas editando la tarjeta</h1>
<div class="container">
	<form action="/direccion/editar" method="post">
		<div class="form-group">
			<label for="idDireccion">Id de la tarjeta:</label> <input
				type="text" class="form-control" id="idDireccion"
				name="idDireccion" value="${direccion.idDireccion}" readonly>
		</div>
		<div class="form-group">
			<label for="localidad">Localidad:</label> <input
				type="text" class="form-control" id="localidad"
				name="localidad" value="${direccion.localidad}">
		</div>
		<div class="form-group">
			<label for="codigoPostal">Codigo Postal:</label> <input
				type="text" class="form-control" id="codigoPostal"
				name="codigoPostal" value="${direccion.codigoPostal}">
		</div>
		<div class="form-group">
			<label for="calle">Calle:</label> <input
				type="date" class="form-control" id="calle"
				name="calle" value="${direccion.calle}">
		</div>
		<div class="form-group">
			<label for="numero">Numero:</label> <input type="number"
				class="form-control" id="numero" name="numero" value="${direccion.numero}">
		</div>
		<div class="form-group">
			<label for="piso">ID usuario:</label> <input type="number"
				class="form-control" id="piso" name="piso" value="${direccion.piso}">
		</div>
		<button type="submit" class="btn btn-primary">Actualizar</button>
	</form>
</div>
	
</body>
</html>