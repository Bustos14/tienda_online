<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle de tu tarjeta</title>
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

    <h1>Pedidos realizados el ${fecha}</h1>
    <table>
        <tr>
            <th>Cliente</th>
            <th>Dirección de envío</th>
            <th>Producto</th>
            <th>Cantidad</th>
        </tr>
        <c:forEach var="detalle" items="${detallesPedidos}">
            <tr>
                <td>${detalle[0]}</td>
                <td>${detalle[1]}</td>
                <td>${detalle[2]}</td>
                <td>${detalle[3]}</td>
            </tr>
        </c:forEach>
    </table>
		<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>