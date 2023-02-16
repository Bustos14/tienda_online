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
<div class="container">
<form action="/registro" method="post">
	<div class="form-group">
			<label for="username">Usuario:</label> <input
				type="text" class="form-control" id="username"
				name="username">
		</div>
		<div class="form-group">
			<label for="nombre">Nombre:</label> <input
				type="text" class="form-control" id="nombre"
				name="nombre">
		</div>
		<div class="form-group">
			<label for="apellidos">Apellidos:</label> <input
				type="text" class="form-control" id="apellidos"
				name="apellidos">
		</div>
		<div class="form-group">
			<label for="fechaNacimiento">Fecha nacimiento:</label> <input
				type="date" class="form-control" id="fechaNacimiento"
				name="fechaNacimiento">
		</div>
		<div class="form-group">
			<label for="contrasena">Contraseña:</label> <input type="password"
				class="form-control" id="contrasena" name="contrasena">
		</div>
	
		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
	</form>
</div>
</body>
</html>