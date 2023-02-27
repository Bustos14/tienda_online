<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedidos para mandar hoy</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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

<div class="container mt-5" style="background-color: rgba(51, 51, 51, 0.6); color: white; border-radius: 25px">
    <h1 class="text-center mb-5">Pedidos realizados el ${fecha}</h1>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>ID Pedido</th>
          <th>Username</th>
          <th>Nombre</th>
          <th>Tipo</th>
          <th>Precio</th>
          <th>Dirección</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="pedido" items="${pedidos}">
          <tr>
            <td>${pedido.idPedido}</td>
            <td>${pedido.usuario.username}</td>
            <td>
              <c:forEach var="articulo" items="${pedido.aticulosPedidos}">
                ${articulo.producto.nombre}<br>
              </c:forEach>
            </td>
             <td>
              <c:forEach var="articulo" items="${pedido.aticulosPedidos}">
                ${articulo.producto.tipo.nombreTipo}<br>
              </c:forEach>
            </td>
             <td>
              <c:forEach var="articulo" items="${pedido.aticulosPedidos}">
                ${articulo.producto.price}<br>
              </c:forEach>
            </td>
            <td>
            Calle: ${pedido.direccione.calle}, Nº${pedido.direccione.numero}, Piso: ${pedido.direccione.piso}, Letra:  ${pedido.direccione.letra}
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</div>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>