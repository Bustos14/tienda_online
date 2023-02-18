<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dar de alta a una tarjeta</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="inicio.jsp"></jsp:include>
	
<div class="container">
	
	<h3>Mensajes : ${mensaje }</h3>

	<h1>Alta Tarjeta</h1>

	<form action="/tarjeta/alta" method="post">
		<div class="form-group">
			<label for="numeroTarjeta">Número de tarjeta:</label> <input
				type="text" class="form-control" id="numeroTarjeta"
				name="numeroTarjeta">
		</div>
		<div class="form-group">
			<label for="nombreTitular">Nombre del titular:</label> <input
				type="text" class="form-control" id="nombreTitular"
				name="nombreTitular">
		</div>
		<div class="form-group">
			<label for="fechaCaducidad">Fecha caducidad:</label> <input
				type="date" class="form-control" id="fechaCaducidad"
				name="fechaCaducidad">
		</div>
		<div class="form-group">
			<label for="cvv">CVV:</label> <input type="number"
				class="form-control" id="cvv" name="cvv">
		</div>		
		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
		<a href="/tarjeta/tarjetas" class="btn btn-primary"> Volver</a>
	</form>
</div>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>