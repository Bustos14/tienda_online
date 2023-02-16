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
	<h1>Detalle de la direcció</h1>
	<form action="/" method="get">
	<div class="form-group">
			<label for="idDireccion">Id de la direccion:</label> <input
				type="text" class="form-control" id="idDireccion"
				name="idDireccion" value="${direccion.idDireccion}" readonly>
		</div>	
				<div class="form-group">
			<label for="localidad">Localidad:</label> <input
				type="text" class="form-control" id="localidad"
				name="localidad" value="${direccion.localidad}" readonly>
		</div>
		<div class="form-group">
			<label for="codigoPostal">Codigo Postal:</label> <input
				type="text" class="form-control" id="codigoPostal"
				name="codigoPostal" value="${direccion.codigoPostal}" readonly>
		</div>
		<div class="form-group">
			<label for="calle">Calle:</label> <input
				type="text" class="form-control" id="calle"
				name="calle" value="${direccion.calle}" readonly>
		</div>
		<div class="form-group">
			<label for="numero">Numero:</label> <input type="text"
				class="form-control" id="numero" name="numero" value="${direccion.numero}" readonly>
		</div>
		<div class="form-group">
			<label for="piso">Piso:</label> <input type="text"
				class="form-control" id="piso" name="piso" value="${direccion.piso}" readonly>
		</div>
		<div class="form-group">
			<label for="letra">Letra:</label> <input
				type="text" class="form-control" id="letra"
				name="letra" value="${direccion.letra}" readonly>
		</div>
		<button type="submit" class="btn btn-primary">Volver</button>
	</form>
</div>
	
</body>
</html>