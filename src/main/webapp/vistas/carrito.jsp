<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<style type="text/css">
#footer {
	position: static !important;
}
	body {
  background-image: url("/img/fondo.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
</head>
<body>
	<br>
	<jsp:include page="inicio.jsp"></jsp:include>
	<br>
<div class="container mt-4" style="background-color: rgba(51, 51, 51, 0.6); color: white; border-radius: 25px">
	<h3 class="text-danger">${mensaje }</h3>
	<h3>Carrito</h3>
	      <form id="cart-update-form" method="GET" action="/pedidos/efectuarCompra">
	<div class="row ">
	<div class="col-sm-8 mb-4">

        <div class="table-responsive">
          <table class="table" style="text-align: center;">
           <thead>
				<tr>
				<th>Imagen</th>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Precio</th>
				<th>Cantidad</th>
				<th>Subtotal</th>
				<th>Acción</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="carrito" items="${carrito}">
			<tr>
			<td><img style="width: 100px;" src="/recursos/${carrito.producto.img}" class="img-fluid" alt="${producto.img}" ></td>
			<td>${carrito.producto.nombre}</td>
			<td>${carrito.producto.descripcion}</td>
			<td>${carrito.producto.price}</td>
			<td>${carrito.cantidad}</td>
			<td>${carrito.producto.price * carrito.cantidad}</td>
			<td><a href="delete/${carrito.pedido.idPedido}/${carrito.producto.idProducto}"><i class="fa fa-trash" aria-hidden="true" style="color:red"></i></a></td>
			</tr>
			</c:forEach>
			</tbody></table>
        </div>
      <input type="hidden">
      <hr class="my-3">
    </div>
	<div class="col-sm-4">
		<div class="card">
			<div class="card-header">
				<h3 style="color:black;">Realizar pedido</h3>
			</div>
			<div class="card-body">
			<label style="color:black;">Subtotal:</label>
			<input type="text" readonly="true" class="form-control" value="${total}">
			<hr class="my-3">
			 <label style="color:black;">Tarjetas:</label><br>
			 <c:if test="${tarjetas == null}">
              <p class="card-text text-danger"> No tienes tarjetas asignadas </p> <a href="/usuario/misTarjetas/${userName}" class="btn btn-info btn-block" style="color:white; href="/usuario/misTarjetas">Añadir tarjeta <i class="fa fa-credit-card fa-inverse" aria-hidden="true"></i></a>       
             </c:if>
             <c:if test="${tarjetas != null}">
			 <select name="tarjetas" class="select form-control" >
			 <c:forEach var="ele" items="${tarjetas}">
          	<option selected="true" value="${ele.idTarjetaBancaria}">${ele.numeroTarjeta}</option>
        	</c:forEach>
        	</select>
  			</c:if>

			<hr class="my-3">
			<label style="color:black;">Dirección:</label>
			 <c:if test="${direcciones == null}">
              <p class="card-text text-danger">No tienes ninguna direcciónn</p>   
              <a href="/usuario/misDirecciones/${userName}" class="btn btn-info btn-block" style="color:white;">Añadir dirección <i class="fa fa-compass fa-inverse" aria-hidden="true"></i></a>       
             </c:if>
             <c:if test="${direcciones != null}">
			<select  name="dir" id="dir" class="select form-control" >
			 <c:forEach var="ele" items="${direcciones}">
          	<option name="dir" value="${ele.idDireccion}"  selected="true" >Calle: ${ele.calle}, Nº${ele.numero}, Piso: ${ele.piso}, Letra:  ${ele.letra}</option>
        	</c:forEach>
        	</select>
        	</c:if>

			</div>
			</div>
			
			<div class="card-footer">
			 <c:if test="${direcciones != null}">
			 <c:if test="${tarjetas != null}">
			 <c:if test="${carrito != null}">
			 <button type="submit" class="btn btn-info btn-block">Realizar pago</button>
			 </c:if>
			 </c:if>
			 </c:if>
			<a class="btn btn-danger btn-block" href="/">Seguir comprando</a>
			
			</div>
			</div>

		</div>
		</form>
	</div>






<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>