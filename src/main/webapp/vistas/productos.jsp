<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Filtrado productos</title>
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
<div class="container">
	<h3 class="text-danger">${mensaje }</h3>
	<h1 class="text-primary">Lista de productos</h1>
	
	 <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
	<a href="/altaProducto" class="btn btn-primary btn-sm" >Nuevo Producto</a></td>
	<br><br>
	</sec:authorize>
	<div class="dropdown show">
		  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    ¿Qué te apetece?
		  </a>
		
		  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
		    		<a class="dropdown-item" href="/producto/tipo/Cerveza">Cerveza</a>
		    		<a class="dropdown-item" href="/producto/tipo/Vino">Vino</a>
		    		<a class="dropdown-item" href="/producto/tipo/Embutido">Embutido</a>
		  		</div>
		</div>
	<div class="row">
<c:forEach var="ele" items="${productos }">
					<c:choose>
						<c:when test="${ele.stock ne 0}">
							<div
								class="card mx-2 my-2 shadow-lg rounded border border-dark hover-3d"
								style="background-color: rgba(51, 51, 51, 0.6); color: white;">
								<img src="/recursos/${ele.img}" class="card-img-top border"
									alt="${ele.img}" style="height: 200px; width: 300px;">
								<div class="card-body">
									<h5 class="card-title">${ele.nombre}</h5>
									<p class="card-text">
										<span>Precio:</span> ${ele.price} &euro;
									</p>
									<p class="card-text">
										<small><span>Stock:</span></small> ${ele.stock}
									</p>

									<div class="d-flex">

										<div class="p-2">
											<a href="pedidos/modCarrito/${ele.idProducto}"
												class="btn btn-success btn-sm">Añadir carrito</a>
										</div>
										<div></div>

										<div class="p-2">
											<a href="/producto/detallesProducto/${ele.idProducto}"
												class="btn btn-success btn-sm">Detalle</a>
										</div>
									</div>
									<div class="d-flex p-2">
										<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
											<div class="dropdown ">
												<button class="btn btn-secondary dropdown-toggle"
													type="button" id="dropdownMenuButton"
													data-toggle="dropdown" aria-haspopup="true"
													aria-expanded="false">Acciones</button>
												<div class="dropdown-menu"
													aria-labelledby="dropdownMenuButton">
													<a class="dropdown-item"
														href="/producto/modificarProducto/${ele.idProducto}">Modificar</a>
													<a class="dropdown-item"
														href="/producto/eliminarProducto/${ele.idProducto}">Eliminar</a>
												</div>
											</div>
										</sec:authorize>

									</div>
									<c:if test="${ele.stock <= 3}">
										<p class="card-text text-danger">Solo queda ${ele.stock}
											en stock</p>
									</c:if>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
								<div
									class="card mx-2 my-2 shadow-lg rounded border border-dark hover-3d"
									style="background-color: rgba(51, 51, 51, 0.6); color: white;">
									<img src="/recursos/${ele.img}" class="card-img-top border"
										alt="${ele.img}" style="height: 200px; width: 300px;">
									<div class="card-body">
										<h5 class="card-title">${ele.nombre}</h5>
										<p class="card-text">
											<span>Precio:</span> ${ele.price} &euro;
										</p>
										<p class="card-text">
											<small><span>Stock:</span></small> ${ele.stock}											
										<c:if test="${ele.stock eq 0}">
    											<p class="card-text text-danger hoverable text-center">¡¡NECESARIO REPONER STOCK!!</p>
										</c:if>
										</p>

										<div class="d-flex">

											<div class="p-2">
												<a href="pedidos/modCarrito/${ele.idProducto}"
													class="btn btn-success btn-sm">Añadir carrito</a>
											</div>
											<div></div>

											<div class="p-2">
												<a href="/producto/detallesProducto/${ele.idProducto}"
													class="btn btn-success btn-sm">Detalle</a>
											</div>
										</div>
										<div class="d-flex p-2">
											<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
												<div class="dropdown ">
													<button class="btn btn-secondary dropdown-toggle"
														type="button" id="dropdownMenuButton"
														data-toggle="dropdown" aria-haspopup="true"
														aria-expanded="false">Acciones</button>
													<div class="dropdown-menu"
														aria-labelledby="dropdownMenuButton">
														<a class="dropdown-item"
															href="/producto/modificarProducto/${ele.idProducto}">Modificar</a>
														<a class="dropdown-item"
															href="/producto/eliminarProducto/${ele.idProducto}">Eliminar</a>
													</div>
												</div>
											</sec:authorize>
										</div>

									</div>
								</div>
							</sec:authorize>
						</c:otherwise>
					</c:choose>
				</c:forEach>
</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"> </script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>