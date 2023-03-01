<%@page import="java.text.SimpleDateFormat"%>
<%@page
	import="com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style type="text/css">
body {
	background-image: url("/img/fondo.jpg");
	background-repeat: no-repeat;
	background-size: cover;
}

#footer {
	position: static !important;
}

.hoverable:hover {
    transition: all 0.3s ease;
    transform: scale(1.1);
}

</style>
</head>
<body>
	<br>
	<jsp:include page="inicio.jsp"></jsp:include>
	<br>
	<section
		class="bg-darker bg-size-cover bg-position-center py-5 py-lg-10"
		style="background-image: url(${pageContext.request.contextPath}/img/hero2.jpg);background-repeat: no-repeat;background-size:100% 100%; margin:2%;">
		<div class="container pb-4">
			<div class="row justify-content-center pb-5">
				<div class="col-xl-15 col-lg-8 col-md-8 col-sm-10 text-center">
					<h5 class="text-black">Productos de importación</h5>
					<div class="h1 text-black mb-5 pb-5">Productos frescos y de
						calidad en la puerta de tu casa</div>
					<a class="btn btn-primary" href="#productos" data-scroll="">Mira
						nuestro catálogo</a>
				</div>
			</div>
		</div>
	</section>
	<div class="row text-center" style="margin: 10%;">
		<div class="col-md-6">
			<span class="fa-stack fa-4x"> <a href="/producto/destacados">
					<i class="fas fa-circle fa-stack-2x text-primary"></i> <i
					class="fas fa-duotone fa-star fa-stack-1x fa-inverse"></i>
			</a>
			</span>
			<h4 class="my-3" style="color: white">Destacados</h4>
			<p class="text-muted">Los mas destacados.</p>
		</div>
		<div class="col-md-6">
			<span class="fa-stack fa-4x"> <a href="/producto/oferta">
					<i class="fas fa-circle fa-stack-2x text-primary"></i> <i
					class="fas fa-duotone fa-tags fa-stack-1x fa-inverse"></i>
			</a>
			</span>
			<h4 class="my-3" style="color: white">Ofertas</h4>
			<p class="text-muted">¡Ofertas que te encantarán!</p>
		</div>
	</div>
	<div class="container">
		<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">Veamos los pedidos para hoy</button>
		</sec:authorize>
		<h1 class="text-primary" id="productos">Catálogo</h1>
		<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
			<a href="producto/altaProducto" class="btn btn-primary btn-sm">Nuevo
				Producto</a>
			</td>
			<!-- Agrega el botón que abrirá la ventana modal flotante -->
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
		
		<div class="row justify-content-center">
			<div class="row justify-content-center">
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
	</div>


	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-sm"
			role="document">
			<div class="modal-content">
				<div class="modal-body">
					<%
      	Date fechaHoy = new Date();
      	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
      	String fechaHoyStr = formateador.format(fechaHoy);
      %>
					<a class="btn btn-primary"
						href="/pedidos/pedidosPorDia?fecha=<%= fechaHoyStr %>">Pedidos
						de hoy</a>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</div>
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
