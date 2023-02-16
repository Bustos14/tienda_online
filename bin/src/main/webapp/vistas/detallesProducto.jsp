<%@page import="com.edix.grupo.tienda.full.stack.java.entitybeans.Producto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="2">
	<tr>
	<th>Nombre</th> <th> Descrip&oacurate;n </th> <th> Precio </th> <th> Stock </th> <th> Estado actual </th>
	</tr>
	<tr>
	<td><c:out value="${producto.nombre}" /></td>
	<td> <c:out value="${producto.descripcion}"/></td>
	<td><c:out value="${producto.price}"/>&euro; </td>
	<td><c:out value="${producto.stock}" /></td>
	<td><c:out value="${producto.estado}" /></td>
	</tr>
	</table>
	<a href="/"> Volver</a>
</body>
</html>