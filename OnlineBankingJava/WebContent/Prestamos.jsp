<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<style>

<jsp:include page="css\StyleSheet.css"></jsp:include>

.card {
    width: 400px; 
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border: 5px solid #0077b6;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 30px; 
}

.card-header {
    font-family: Roboto, sans-serif;
    font-size: 28px;
    font-weight: bold;
    color: #000000; 
    background-color: transparent; 
    text-align: center;
    width: 100%;
    padding: 15px;
}

.card-body {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px; 
    width: 100%;
}

.btn-primary1 {
    background-color: #007bff;
    color: #ffffff;
    font-weight: bold;
    font-size: 20px; 
    width: 100%;
    padding: 12px; 
    border-radius: 5px;
}
</style>
</head>

<body>

<div class="encabezado-user">
  
     <div class="bg-light text-dark">
			
			
			<jsp:include page="Nav.jsp" />
	 </div>
  
  
  
</div>

<div class="parteIzq border border-dark border-3 rounded-end">
    <div class="mt-5 ps-3">
        <ul class="nav flex-column">
            <li class="nav-item"><a class="nav-link active fs-2 text-light" aria-current="page" href="servletInicioUser">Inicio</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="Transferencias.jsp">Transferencias</a></li>
             <li class="nav-item"><a class="nav-link fs-2 text-light" href="SolicitarCuenta.jsp">Solicitar Cuenta</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="Prestamos.jsp">Préstamos</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="servletMiPerfil">Mi Perfil</a></li>
        </ul>
    </div>
</div>

<div class="parteDer border border-dark border-3 rounded-end">
    <div class="postbody">
        <div class="card">
            <div class="card-header">
                PRÉSTAMOS
            </div>
            <div class="card-body">
                <a class="btn btn-primary fw-bold fs-5" href="ServletPrestamoSolicitar">Solicitar préstamo</a>
                <a class="btn btn-primary fw-bold fs-5" href="ServletPrestamosCliente">Gestionar préstamos</a>
            </div>
			        <% String message=(String) request.getAttribute("message");
			        	if(message!=null){
			        %>
					<div class="mensaje-exito alert alert-success alert-dismissible fade show text-center" role="alert">
                		<%= message %>
                		<button type="button" class="btn-close btn-sm" data-bs-dismiss="alert" aria-label="Close"></button>
            		</div>
            		<%
			        	}
			        %>
        </div>
    </div>
</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>