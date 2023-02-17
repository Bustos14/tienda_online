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
<br>
<jsp:include page="inicio.jsp"></jsp:include>

<br>

<div class="container">
	<h1 class="text-primary">Lista de productos</h1>
	 <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
	<a href="/altaProducto" class="btn btn-primary btn-sm" >Nuevo Producto</a></td>
	<br><br>
	</sec:authorize>
	<table class="table table-striped table-sm" >
	<th>Nombre</th> <th> Stock </th> <th> Precio </th> <th> Detalles </th>
	<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
	<th>Modificar</th>
	<th>Eliminar</th>
	</sec:authorize>
	
	<c:forEach var="ele" items="${productos }" >
		<tr>
			<td>${ele.nombre}</td>
			<td>${ele.stock}</td>			
			<td>${ele.price} &euro;</td>
			<td><a href="/detallesProducto/${ele.idProducto}" class="btn btn-success btn-sm">Detalle</a></td>
			 <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
			<td><a href="/modificarProducto/${ele.idProducto}" class="btn btn-success btn-sm">Modificar</a> </td>
			 <td><a href="/eliminarProducto/${ele.idProducto}" class="btn btn-danger btn-sm">Eliminar</a></td>
			  </sec:authorize>
		</tr>
	</c:forEach>
	</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>