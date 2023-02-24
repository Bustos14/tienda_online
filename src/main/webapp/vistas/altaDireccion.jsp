<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dar alta a una direccion</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
	<h3>Mensajes : ${mensaje }</h3>

	<h1>Alta Direccion</h1>
	<form action="/direccion/alta" method="post">
		<div class="form-group">
			<label for="localidad">Localidad:</label> 
		<input type="text"
				class="form-control" 
				id="localidad" 
				name="localidad"
				minlength="3" 
				required>
		</div>
		<div class="form-group">
			<label for="codigoPostal">Codigo Postal:</label> 
			<input
				type="text" 
				class="form-control" 
				id="codigoPostal"
				name="codigoPostal"
				pattern="\d{5}" 
				title="El codigo postal tiene que tener 5 dígitos"
				required>
		</div>
		<div class="form-group">
			<label for="calle">Calle:</label> 
			<input
				type="text" 
				class="form-control" 
				id="calle"
				name="calle" 
				minlength="3"
				required>
		</div>
		<div class="form-group">
			<label for="numero">Numero:</label> 
			<input type="number"
				class="form-control" 
				id="numero" 
				name="numero" 
				pattern="[0-9]+.*"
				required>
		</div>
		<div class="form-group">
			<label for="piso">Piso:</label> 
			<input type="text"
				class="form-control" 
				id="piso" 
				name="piso">
		</div>
		<div class="form-group">
			<label for="letra">Letra:</label> 
			<input
				type="text" 
				class="form-control" 
				id="letra"
				name="letra"
				pattern="[A-Za-z]">
		</div>
		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
		<a href="/usuario/perfil" class="btn btn-primary">Volver</a>
	</form>
</div>

	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>