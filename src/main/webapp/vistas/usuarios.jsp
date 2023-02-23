<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuarios de la app</title>
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
<br>
<jsp:include page="inicio.jsp"></jsp:include>

<br>

<div class="container p-5" style="background-color: rgba(51, 51, 51, 0.6); color: white; border-radius: 25px">
	<h3 class="text-danger">${mensaje }</h3>
    <h1 class="text-primary">Lista de usuarios</h1>
    <a href="/registro" class="btn btn-primary btn-sm">Nuevo Usuario</a>
    <br><br>
    <table class="table table-striped table-sm">
        <th>Username</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Fecha nacimiento</th>
        <th>Fecha Registro</th>
        <th>Eliminar</th>
        <th></th>
        
        <c:forEach var="ele" items="${todosUsuarios}">
            <tr>
                <td>${ele.username}</td>
                <td>${ele.nombre}</td>			
                <td>${ele.apellidos}</td>				
                <td>${ele.fechaNacimiento}</td>				
                <td>${ele.fechaRegistro}</td>	
                <td><a href="/usuario/eliminar/${ele.username}" class="btn btn-danger btn-sm">Eliminar</a></td>
                <td><a href="/pedidos/verTotalPedido/${ele.username}" class="btn btn-primary btn-sm">Total pedidos</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

	
		<jsp:include page="footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>