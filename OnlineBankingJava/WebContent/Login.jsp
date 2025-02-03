<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<html lang="es">
	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<style type="text/css">
		<jsp:include page="css\StyleSheet.css"></jsp:include>
	</style>
	
	<!--Bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

</head>
<body>



	<div class="position-absolute top-0 end-0" >
		<div class="d-flex align-items-center mt-2">

			<a class="justify-content-md-end btn-lg btn-primary me-md-2 border border-white 
			rounded-pill px-4 ms-2" href="Home.jsp"><i class="bi bi-house-fill fs-5 m-2"></i>Inicio</a>

		</div>

	</div>
	

	<div class="container">
		<div class="online-banking">Online banking</div>

		<div class="image-login"></div>
		<div class="form-container">
			<div class="form-login">
				<h2>Iniciar Sesión</h2>

				<div>
		<%
                    String errorMessage = (String) request.getAttribute("error");
                    if (errorMessage != null) {
                %>
                    <div class="alert alert-danger" role="alert">
                        <%= errorMessage %>
                    </div>
                <%
                    }
                %>		
				
				
		<form action="servletLogin" method="post">
            <label for="DNI">Tu DNI</label> 
            <input type="text" maxlength="8" id="DNI" required name="dni"> 
            
            <label for="Usuario">Tu Usuario</label> 
            <input type="text" id="Usuario" required name="usuario"> 
            
            <label for="Clave">Tu Clave</label> 
            <input type="password" id="Clave" required name="clave"> 
            
            <input type="submit" name="btnlogearse" value="Iniciar Sesión">
        </form>
				</div>



				<span>¿No está registrado? <a href="servletCliente?openModal=true" 
   class="nav-link btn-info text-white border border-white rounded-pill px-1 py-1 ms-1">
   Registrese
</a></span>
				
			</div>
		</div>

	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>


</html>


