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

<jsp:include page="inicio.jsp"></jsp:include>

<div class="container">
	<h3>Mensajes : ${mensaje }</h3>

	<h1>Alta Rol</h1>
	<form action="/rol/alta" method="post">
		<div class="form-group">
			<label for="idRol">Id Rol:</label> <input type="number"
				class="form-control" id="idRol" name="idRol">
		</div>
		<div class="form-group">
			<label for="nombre">Nombre:</label> <input
				type="text" class="form-control" id="nombre"
				name="nombre">
		</div>
		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
		<a href="/rol/roles" class="btn btn-primary"> Volver</a>
	</form>
</div>
	
</body>
</html>