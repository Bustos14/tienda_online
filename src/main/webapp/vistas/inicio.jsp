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
<style type="text/css">.hover-3d:hover {box-shadow: 0 0 10px rgba(0,0,0,.2);}</style>
</head>
<body style="margin-top:0px">
<div class="">
  <sec:authentication property="name"/>
  <sec:authorize access="hasAuthority('ROLE_CLIENTE')">Eres un CLIENTE</sec:authorize>
  <sec:authorize access="hasAuthority('ROLE_ADMIN')">Eres un ADMINISTRADOR</sec:authorize>
  <nav class="navbar navbar-expand-lg navbar-dark  bg-dark">
    <a class="navbar-brand" href="/">
      <img src="${pageContext.request.contextPath}/img/logo.png" style="width:120px;height:100px;" class="float-start">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container collapse navbar-collapse" id="navbarNav" >
      <ul class="navbar-nav ml-auto">
        <sec:authorize access="hasAuthority('ROLE_CLIENTE')">
          <li class="nav-item active">
            <a class="nav-link" href="/direccion/direcciones">Direcciones</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="/tarjeta/tarjetas">Tarjetas</a>
          </li>
        </sec:authorize>
        <li class="nav-item">
          <a class="nav-link" href="/">Catálogo</a>
        </li>
        <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
          <li class="nav-item">
            <a class="nav-link" href="/usuario/usuarios">Usuarios</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="producto/altaProducto">Nuevo producto</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/rol/roles">Roles</a>
          </li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
          <li class="nav-item"><a class="nav-link" href="/login">Iniciar Sesión</a></li>
          <li class="nav-item"><a class="nav-link" href="/registro">Registrarse</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
          <li class="nav-item"><a class="nav-link" href="/logout">Cerrar Sesión</a></li>
        </sec:authorize>
       </ul>
       <form class="d-flex flex-grow-1 ms-auto" action="/producto/search" method="get">
      		<input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Buscar" name="nombre">
      		<button class="btn btn-outline-success ms-3 me-3" type="submit">Buscar</button>
    	</form>
    </div>
  </nav>
</div>

<body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
	</html>
	