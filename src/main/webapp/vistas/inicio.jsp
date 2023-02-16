<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<sec:authentication property="name"/>
<sec:authorize access="hasAuthority('ROLE_CLIENTE')">Eres un CLIENTE</sec:authorize>
	<sec:authorize access="hasAuthority('ROLE_ADMIN')">Eres un ADMON</sec:authorize>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">TIENDA ONLINE</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
    <sec:authorize access="hasAuthority('ROLE_CLIENTE')">
      <li class="nav-item active">
        <a class="nav-link" href="/direcciones">Direcciones</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="/tarjetas">Tarjetas</a>
      </li>
      </sec:authorize>
      <li class="nav-item">
        <a class="nav-link" href="/">Productos</a>
      </li>
       <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
      <li class="nav-item">
        <a class="nav-link" href="/usuarios">Usuarios</a>
      </li> <li class="nav-item">
        <a class="nav-link" href="/altaProducto">Nuevo producto</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/roles">Roles</a>
      </li>
      
      </sec:authorize>
      <sec:authorize access="!isAuthenticated()">
      <li class="nav-item"><a class="nav-link"
							href="/login">Iniciar Sesión</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/registro">Registrarse</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<li class="nav-item"><a class="nav-link"
				href="/logout">Cerrar Sesión</a></li>
		</sec:authorize>
    </ul>
  </div>
</nav>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
	</html>
	