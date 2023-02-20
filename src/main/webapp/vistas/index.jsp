<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style type="text/css">
	body {
  background-image: url("/img/fondo.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}
    #footer {
      position: static !important;
    }
	
</style>
</head>
<body>
<br>
<jsp:include page="inicio.jsp"></jsp:include>
<br>
<section class="bg-darker bg-size-cover bg-position-center py-5 py-lg-10" style="background-image: url(${pageContext.request.contextPath}/img/hero2.jpg);background-repeat: no-repeat;background-size:100% 100%; margin:2%;">
        <div class="container pb-4">
          <div class="row justify-content-center pb-5">
            <div class="col-xl-15 col-lg-8 col-md-8 col-sm-10 text-center">
              <h5 class="text-black">Productos de importación</h5>
              <div class="h1 text-black mb-5 pb-5">Productos frescos y de calidad en la puerta de tu casa
              </div><a class="btn btn-primary" href="#productos" data-scroll="">Mira nuestro catálogo</a>
            </div>
          </div>
        </div>
      </section>
       <div class="row text-center" style="margin:10%;">
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                        <a href="/">
                        <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-duotone fa-wine-bottle fa-stack-1x fa-inverse"></i>
                            </a>
                        </span>
                        <h4 class="my-3" style="color:white">Catálogo</h4>
                        <p class="text-muted" >Mira nuestro catálogo y encuentra lo que buscas</p>
                    </div>
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                        <a href="/producto/destacados">
                        <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-duotone fa-star fa-stack-1x fa-inverse"></i>
                            </a>
                        </span>
                        <h4 class="my-3" style="color:white">Destacados</h4>
                        <p class="text-muted">Los mas destacados.</p>
                    </div>
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                          <a href="/producto/oferta">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-duotone fa-tags fa-stack-1x fa-inverse"></i>
                            </a>
                        </span>
                        <h4 class="my-3" style="color:white">Ofertas</h4>
                        <p class="text-muted">¡Añade una tarjeta para tu primera compra!</p>
                    </div>
                </div>
<div class="container">
	<h1 class="text-primary" id="productos">Productos</h1>
	 <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
	<a href="producto/altaProducto" class="btn btn-primary btn-sm" >Nuevo Producto</a></td>
	<br><br>
	</sec:authorize>
	
	<div class="row justify-content-center">
  <c:forEach var="ele" items="${productos }">
    <div class="card mx-2 my-2 shadow-lg rounded border border-dark hover-3d" style="background-color: rgba(51, 51, 51, 0.6); color: white;">
      <img src="/recursos/${ele.img}" class="card-img-top border" alt="${ele.img}" style="height: 200px;width: 300px;">
      <div class="card-body" >
        <h5 class="card-title">${ele.nombre}</h5>
        <p class="card-text"><span>Precio:</span> ${ele.price} &euro;</p>
        <p class="card-text"><small><span>Stock:</span></small> ${ele.stock}</p>
        
        <div class="d-flex">
          <div class="p-2"><a href="/producto/detallesProducto/${ele.idProducto}" class="btn btn-success btn-sm">Detalle</a></div>
          <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
            <div class="p-2"><a href="/producto/modificarProducto/${ele.idProducto}" class="btn btn-success btn-sm">Modificar</a></div>
            <div class="p-2"><a href="/producto/eliminarProducto/${ele.idProducto}" class="btn btn-danger btn-sm">Eliminar</a></div>
          </sec:authorize>
          
        </div>
        <c:if test="${ele.stock <= 3}">
   			<p class="card-text text-danger">Solo queda ${ele.stock} en stock</p>
		</c:if>
      </div>
    </div>
  </c:forEach>
</div>

	
	<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>
