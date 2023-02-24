<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro usuario</title>
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

<form action="/registro" method="post">
	<div class="form-group">
			<label for="username">Usuario:</label> <input
				type="text" class="form-control" id="username"
				name="username" required>
		</div>
		<div class="form-group">
			<label for="nombre">Nombre:</label> <input
				type="text" class="form-control" id="nombre"
				name="nombre" required>
		</div>
		<div class="form-group">
			<label for="apellidos">Apellidos:</label> <input
				type="text" class="form-control" id="apellidos"
				name="apellidos" required>
		</div>
		<div class="form-group">
			<label for="fechaNacimiento">Fecha nacimiento:</label> <input
				type="date" class="form-control" id="fechaNacimiento"
				name="fechaNacimiento" required>
		</div>
		<div class="form-group">
			<label for="contrasena">Contraseña:</label> <input type="password"
				class="form-control" id="contrasena" name="contrasena" required>
		</div>
	  	<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
	  	<div class="form-group">
	  	<label for="rol">Selecciona un rol:</label>
	  	<select  name="rol" id="rol">
		 <c:forEach var="ele" items="${roles}">		 
		  <option value="${ele.idRol}">${ele.nombre}</option>
        </c:forEach>
         </select>
         </div>
		</sec:authorize>
		<button type="submit" class="btn btn-primary">Enviar</button>
		<button type="reset" class="btn btn-secondary">Borrar</button>
		<a href="/" class="btn btn-primary"> Volver</a>
	</form>
</div>
</body>
</html>