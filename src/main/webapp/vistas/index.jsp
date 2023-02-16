<%@page import="com.edix.grupo.tienda.full.stack.java.entitybeans.Producto"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
<div>
	<h1>${mensaje}</h1>
<a href ="/">Todos</a>
<a href ="/destacados">Destacados</a>
<a href ="/oferta">Oferta</a>
<a href ="/altaProducto">Nuevo producto</a>
<a class="nav-link" href="/registro">Registrarse</a>
<a class="nav-link" href="/logout ">Cerrar Sesión</a>
<a href ="/registro">Registrate</a>
</div>

<br>
<table border="2">
	<tr>
	<th>Nombre</th> <th> Stock </th> <th> Precio </th> <th> Detalles </th> <th> Modificar </th> <th> Eliminar </th>
	</tr>
	<c:forEach var="ele" items="${productos}">
	<tr>
	<td>${ ele.nombre} </td>
	<td> ${ele.stock} </td>
	<td>${ele.price} &euro; </td>
	<td><a href="/detallesProducto/${ele.idProducto}">Detalle</a></td>
	<td><a href="/modificarProducto/${ele.idProducto}">Modificar</a></td>
	<td><a href="/eliminarProducto/${ele.idProducto}">Eliminar</a></td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>