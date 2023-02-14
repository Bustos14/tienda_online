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

	<h3>Mensajes : ${mensaje }</h3>

	<h1>Estas editando la tarjeta</h1>
<div class="container">
	<form action="/tarjeta/editar" method="post">
		<div class="form-group">
			<label for="idTarjetaBancaria">Id de la tarjeta:</label> <input
				type="text" class="form-control" id="idTarjetaBancaria"
				name="idTarjetaBancaria" value="${tarjetaBancaria.idTarjetaBancaria}" >
		</div>
		<div class="form-group">
			<label for="numeroTarjeta">Número de tarjeta:</label> <input
				type="text" class="form-control" id="numeroTarjeta"
				name="numeroTarjeta" value="${tarjetaBancaria.numeroTarjeta}" >
		</div>
		<div class="form-group">
			<label for="nombreTitular">Nombre del titular:</label> <input
				type="text" class="form-control" id="nombreTitular"
				name="nombreTitular" value="${tarjetaBancaria.nombreTitular}">
		</div>
		<div class="form-group">
			<label for="fechaCaducidad">Fecha caducidad:</label> <input
				type="date" class="form-control" id="fechaCaducidad"
				name="fechaCaducidad" value="${tarjetaBancaria.fechaCaducidad}" >
		</div>
		<div class="form-group">
			<label for="cvv">CVV:</label> <input type="number"
				class="form-control" id="cvv" name="cvv" value="${tarjetaBancaria.cvv}" >
		</div>
		<div class="form-group">
			<label for="usuario">ID usuario:</label> <input type="number"
				class="form-control" id="usuario" name="usuario" value="${tarjetaBancaria.usuario.idUsuario}">
		</div>
		<button type="submit" class="btn btn-primary">Actualizar</button>
	</form>
</div>
	
</body>
</html>