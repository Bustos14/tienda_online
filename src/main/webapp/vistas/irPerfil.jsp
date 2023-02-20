<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perfil de Usuario</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="inicio.jsp"></jsp:include>
<div class="container col-4">
	<h1>Bienvenido, ${userName}</h1>
	<h3>${mensaje}</h3>
	<h2>Tus datos de usuario:</h2>
	<form action="/usuario/editar" method="post">
		<div class="form-group">
			<label for="username">Nombre de Usuario:</label> <input
				type="text" class="form-control" id="username"
				name="username" value="${userName}" readonly >
		</div>
		<div class="form-group">
			<label for="nombre">Nombre:</label>
		<input type="text"
		       class="form-control"
		       id="nombre"
		       name="nombre"
		       value="${nombre}"
		       minlength="3"
		       readonly>
		</div>
		<div class="form-group">
			<label for="apellidos">Apellidos:</label> 
		<input type="text"
				class="form-control" 
				id="apellidos" 
				name="apellidos"
				value="${apellidos}"				
		      	minlength="5"
				readonly>
		</div>
		<div class="form-group">
			<label for="fechaNacimiento">Fecha de Nacimiento:</label> <input
				type="text" class="form-control" id="fechaNacimiento"
				name="fechaNacimiento"
				value="${fechaNacimiento}" 
				pattern="\d{4}-\d{2}-\d{2}" 
				title="La fecha debe tener el formato AAAA-MM-DD"
				readonly>
		</div>
		<button type="submit" class="btn btn-primary" id="btnEnviar" disabled>Actualizar datos usuario</button>
		<br><br>
	</form>
	<a class="btn btn-success  btn-sm" id="btnModificar" onclick="document.getElementById('nombre').removeAttribute('readonly');document.getElementById('apellidos').removeAttribute('readonly');document.getElementById('fechaNacimiento').removeAttribute('readonly');" style="color:white">Modificar</a>

	<a class="btn btn-info btn-sm" id="verTarjetas" href="/tarjeta/tarjetas">Mis tarjetas</a>

	<a class="btn btn-info btn-sm" id="verDirecciones" href="/direccion/direcciones">Mis Direcciones</a>

	<a class="btn btn-info btn-sm" id="verPedidos" href="/">Mis Pedidos</a>
	
	
</div>

	<jsp:include page="footer.jsp"></jsp:include>
	
	
</body>
<script>
    const btnModificar = document.getElementById("btnModificar");
    const btnEnviar = document.getElementById("btnEnviar");

    btnModificar.addEventListener("click", () => {
        btnEnviar.removeAttribute("disabled");
    });
</script>
</html>
