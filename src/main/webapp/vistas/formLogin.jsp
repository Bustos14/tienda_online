<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	body {
  background-image: url("/img/fondo.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}
</style></head>
<body style="background-color: #eee;">
<section class="h-100 gradient-form" >
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-10">
        <div class="card rounded-3 text-black">
              <div class="card-body p-md-5 mx-md-4">

                <div class="text-center">
                  <img src="${pageContext.request.contextPath}/img/logo.png" 
                    style="width: 185px;" alt="logo">
                  <h4 class="mt-1 mb-5 pb-1">Bienvenido</h4>
                </div>

                <form name="form" action="/login" method="POST">

                  <div class="form-outline mb-4">
                  <label class="form-label" for="username">Nombre de usuario</label>
                  <input type="text" name="username" class="form-control" id="username">
              
                  </div>

                  <div class="form-outline mb-4">
                     <label class="form-label" for="password">Contraseña</label>
                    <input type="password" id="password" class="form-control"  name="password"/>
              
                  </div>

                  <div class="d-flex align-items-center justify-content-center pb-4">
                    <p class="mb-0 me-2">¿No tienes cuenta aún? <a href="/registro" type="button">Regístrate </a></p>          
                  </div>
                   <div class="d-flex align-items-center justify-content-center pb-4">
                    <p class="mb-0 me-2">O accede como <a href="/" type="button">Invitado</a></p>           
                  </div>
                  <div class="text-center pt-1 mb-5 pb-1">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Inicia sesión</button>
                  </div>
                </form>
              </div>
            </div>
            </div>
          </div>
        </div>
  </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>