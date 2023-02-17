<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="inicio.jsp"></jsp:include>
<div class="container">
	<h1 class="text-primary">Lista de productos</h1>
	 <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
	<a href="/altaProducto" class="btn btn-primary btn-sm" >Nuevo Producto</a></td>
	<br><br>
	</sec:authorize>
	
	<div class="row ">
  <c:forEach var="ele" items="${productos }">
    <div class="card mx-2 my-2 shadow-lg rounded border border-dark hover-3d">
      <img src="/recursos/${ele.img}" class="card-img-top border" alt="${ele.img}" style="height: 200px;width: 300px;">
      <div class="card-body">
        <h5 class="card-title">${ele.nombre}</h5>
        <p class="card-text"><span>Precio:</span> ${ele.price} &euro;</p>
        <p class="card-text"><small class="text-muted"><span>Stock:</span> ${ele.stock}</small></p>
        <c:if test="${ele.stock <= 3}">
   			<p class="card-text text-danger">Solo queda ${ele.stock} en stock</p>
		</c:if>
        <div class="d-flex">
          <div class="p-2"><a href="/producto/detallesProducto/${ele.idProducto}" class="btn btn-success btn-sm">Detalle</a></div>
          <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
            <div class="p-2"><a href="/producto/modificarProducto/${ele.idProducto}" class="btn btn-success btn-sm">Modificar</a></div>
            <div class="p-2"><a href="/producto/eliminarProducto/${ele.idProducto}" class="btn btn-danger btn-sm">Eliminar</a></div>
          </sec:authorize>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
</body>
</html>