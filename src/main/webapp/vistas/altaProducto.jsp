<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
     integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta producto</title>
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

<div class="container p-5" style="background-color: rgba(51, 51, 51, 0.6); color: white; border-radius: 25px">

<h3 class="text-danger">${mensaje }</h3>

	<h1>Alta Producto</h1>
  <h1 class="text-primary">Formulario de alta de productos</h1>
  <form action="/producto/altaProducto" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="nombre" class="text-white">Nombre del producto:</label> 
      <input type="text" class="form-control" id="nombre" name="nombre" required>
    </div>
    <div class="form-group">
      <label for="descripcion" class="text-white">Descripción:</label> 
      <input type="text" class="form-control" id="descripcion" name="descripcion" required>
    </div>
    <div class="form-group">
      <label for="price" class="text-white">Precio:</label> 
      <input type="number" class="form-control" id="price" step="any" name="price" required>
    </div>
    <div class="form-group">
      <label for="stock" class="text-white">Stock:</label> 
      <input type="number" class="form-control" id="stock" name="stock" required>
    </div>
    <div class="form-group">
      <label for="tipo" class="text-white">Tipo de producto:</label> 
      <select id="tipo" name="tipo" class="form-control" required>
        <c:forEach var="ele" items="${tipos}">
          <option selected="true" value="${ele.idTipo}">${ele.nombreTipo}</option>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label for="estado" class="text-white">Estado del producto:</label> 
      <select id="estado" name="estado" class="form-control" required>
        <option selected="true" value="Normal">Normal</option>
        <option value="Oferta">Oferta</option>
        <option value="Destacado">Destacado</option>
      </select>
    </div>
    <div class="form-group">
      <label for="imagen" class="text-white">Imagen:</label> 
      <input type="file" class="form-control" id="imagen" name="file" required>
    </div>
    <button type="submit" class="btn btn-primary">Enviar</button>
    <button type="reset" class="btn btn-secondary">Borrar</button>
    <a href="/" class="btn btn-primary">Volver</a>
  </form>
</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>