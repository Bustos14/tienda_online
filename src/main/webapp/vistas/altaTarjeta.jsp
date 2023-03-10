<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dar de alta a una tarjeta</title>
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
	
	<h3 class="text-danger">${mensaje }</h3>

	<h1>Alta Tarjeta</h1>

	<form action="/tarjeta/alta" method="post">
		<div class="form-group">
			<label for="numeroTarjeta">N?mero de tarjeta:</label> 
			<input
				type="text" 
				class="form-control" 
				id="numeroTarjeta"
				name="numeroTarjeta" 
				pattern="^[0-9]{16}" 
				title="Por favor ingrese un n?mero de tarjeta Visa v?lido de 16 d?gitos, todos juntos"
				maxlength ="16"
				required>
		</div>
		<div class="form-group">
			<label for="nombreTitular">Nombre del titular:</label> 
			<input
				type="text" 
				class="form-control" 
				id="nombreTitular"
				name="nombreTitular" 
				minlength="3"
				required>
		</div>
		<div class="form-group">
			<label for="fechaCaducidad">Fecha caducidad:</label> 
			<input
				type="date" 
				class="form-control" 
				id="fechaCaducidad"
				name="fechaCaducidad" 
				required>
		</div>
		<div class="form-group">
			<label for="cvv">CVV:</label> 
			<input type="number"
				class="form-control" 
				id="cvv" 
				name="cvv" 
				pattern="[0-9]{3}"
				required>
		</div>		
		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
		<a href="/usuario/perfil" class="btn btn-primary">Volver</a>
	</form>
</div>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>