<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear mi Cuenta</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />
<script src=https://code.jquery.com/jquery-3.7.1.min.js></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="sweetalert2.all.min.js"></script>

<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
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
			<li class="nav-item"><a class="nav-link active fs-2 text-light"
					aria-current="page" href="servletInicioUser">Inicio</a></li>
			<li class="nav-item"><a class="nav-link fs-2 text-light"
					href="Transferencias.jsp">Transferencias</a></li>
			<li class="nav-item"><a class="nav-link fs-2 text-light"
					href="Prestamos.jsp">Solicitar Cuenta</a></li>
			<li class="nav-item"><a class="nav-link fs-2 text-light"
					href="Prestamos.jsp">Préstamos</a></li>
			<li class="nav-item"><a class="nav-link fs-2 text-light"
					href="servletMiPerfil">Mi Perfil</a></li>
		</ul>
	</div>
</div>
<div class="parteDer border border-dark border-3 rounded-end d-block ml-5">	
		<div class =mb-5 style="margin-left:25%">   
		 <div class="card-caja">       
		  <h5 class="text-caja">CAJA DE AHORRO PESOS</h5>
		  <form action="servletSolicitarCuenta" method="GET">           
		  	<input type="submit" name="btnQuiero1" value="Quiero" id="btn1" class="btn btn-primary" />
		  </form>
		 </div>
		</div>
		<div style="margin-left:25%">
		   <div class="card-caja">          
		   	<h5 class="text-caja">CUENTA CORRIENTE PESOS</h5>
		   	<form action="servletSolicitarCuenta" method="GET">       
		    	<input type="submit" name="btnQuiero2" value="Quiero" class="btn btn-primary" />		        
			</form>             
		   </div>
		</div>
	<!-- Mensaje de confirmacion agregar-->
	<%
	    int filas = -1;
	    if (request.getAttribute("CantFilas") != null) {
	        filas = (int) request.getAttribute("CantFilas");
	    }
	%>
	
	<div class="position-absolute top-50 start-50" style="z-index: 1050;">
	    <% if (filas != -1) { %> 
	        <% if (filas == 1) { %>
	            <!-- Card de éxito -->
	            <div class="alert alert-success alert-dismissible card-alerQuiero fade show text-center fs-3" role="alert">
	            	<i class="bi bi-check-circle"></i>
	                    Tienes una nueva cuenta- chequeala en inicio!
	                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	                
	            </div>
	        <% } else if (filas == 2) { %>
	            <!-- Card de advertencia -->
	         
	            <div class="alert alert-warning alert-dismissible card-alert fade show py-50 px-50" role="alert">
					<i class="bi bi-exclamation-triangle"></i>
							Ups! Contacte a su asesor
	                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	            </div>
	           
	            
	        <% } else { %>
	            <!-- Card de error -->
	            <div class="alert alert-danger alert-dismissible fade show text-center fs-3" role="alert">
	                   Ups! Acción no válida
	                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	               
	            </div>
	        <% } %>
	    <% } %>
		</div>
	</div>   
 
 
<script>
	$('#btn1').click(function(){
		
		Swal.fire({
			  title: "The Internet?",
			  text: "That thing is still around?",
			  icon: "question"
			})
	)};
</script>
</body>
</html>