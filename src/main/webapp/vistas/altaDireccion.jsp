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


<div class="container">
	<h3>Mensajes : ${mensaje }</h3>

	<h1>Alta Direccion</h1>
	<form action="/direccion/alta" method="post">
		<div class="form-group">
			<label for="localidad">Localidad:</label> <input type="text"
				class="form-control" id="localidad" name="localidad">
		</div>
		<div class="form-group">
			<label for="codigoPostal">Codigo Postal:</label> <input
				type="text" class="form-control" id="codigoPostal"
				name="codigoPostal">
		</div>
		<div class="form-group">
			<label for="calle">Calle:</label> <input
				type="text" class="form-control" id="calle"
				name="calle">
		</div>
		<div class="form-group">
			<label for="numero">Numero:</label> <input type="number"
				class="form-control" id="numero" name="numero">
		</div>
		<div class="form-group">
			<label for="piso">Piso:</label> <input type="text"
				class="form-control" id="piso" name="piso">
		</div>
		<div class="form-group">
			<label for="letra">Letra:</label> <input
				type="text" class="form-control" id="letra"
				name="letra">
		</div>
		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
	</form>
</div>
	
</body>
</html>