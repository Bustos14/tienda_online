<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editando producto</title>
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
	
<div class="container" style="background-color: rgba(51, 51, 51, 0.6); color: white; border-radius: 25px">
	<h1>Estas editando un producto</h1>
<div class="col text-center">
	<form action="/producto/modificarProducto" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="idProducto">Id producto:</label> <input
				type="text" class="form-control" id="idProducto"
				name="idProducto" value="${productoEditable.idProducto}" readonly>
		</div>
		<div class="form-group">
			<label for="nombre">Nombre del producto:</label> <input
				type="text" class="form-control" id="nombre"
				name="nombre" value="${productoEditable.nombre}" placeholder ="${productoEditable.nombre}">
		</div>
		<div class="form-group">
			<label for="descripcion">Descripcion:</label> <input
				type="text" class="form-control" id="descripcion"
				name="descripcion" value="${productoEditable.descripcion}">
		</div>
		<div class="form-group">
			<label for="price">Precio:</label> <input
				type="number" class="form-control" id="price" step="any"
				name="price" value="${productoEditable.price}">
		</div>
		<div class="form-group">
			<label for="stock">Stock:</label> <input type="number"
				class="form-control" id="stock" name="stock" value="${productoEditable.stock}">
		</div>
		<div class="form-group">
		<Select id="tipo" name="tipo">
		<c:forEach var="ele" items="${tipos}">
			<Option value = "${ele.idTipo}"> ${ele.nombreTipo} </option>
			</c:forEach>
		</Select>
		<Select id="estado" name="estado" >
			<Option selected="true" value = "Normal" > Normal </option>
			<Option value = "Oferta"> Oferta </option>
			<Option value = "Destacado"> Destacado </option>
		</Select>
		</div>
		<div class="form-group">
      		<label for="imagen" class="text-white">Imagen:</label> 
      		<input type="file" class="form-control" id="imagen" name="file">
   		</div>
		<div class="col text-center">
			<button type="submit" class="btn btn-primary" style="width: 200px;">Actualizar</button>	
            <button type="submit" class="btn btn-primary" style="width: 200px;">Volver</button>
        </div>	
	</form>
	</div>
</div>
	
		<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>