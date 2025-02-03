
<%@page import="java.util.ArrayList"%>
<%@page import="daoImpl.daoCuentaImpl"%>
<%@page import="entidades.Cuenta"%>
<%@page import="entidades.TipoCuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion Clientes</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />

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

<div class="parteIzq-ADM border border-dark border-3 rounded-end">
	<div class="mt-5 ps-3">
		<ul class="nav flex-column">
			<li class="nav-item">
				<a class="nav-link active fs-4 text-light" aria-current="page" href="ServletMostrarCuenta">
					Gestionar Cuentas
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fs-4 text-light"	aria-current="page" href="ServletMostrarClientes">
					Gestionar Clientes
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fs-4 text-light" href="servletAgregarAdmin?Param=list">
					Gestionar Administradores
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fs-4 text-light" href="ServletMostrarPrestamos">
					Gestionar Préstamos
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fs-4 text-light"	href="Reportes.jsp">
					Reportes
				</a>
			</li>
		</ul>
	</div>
</div>

<div class="parteDer-ADM border border-dark border-3 rounded-end">
    <div class="container-fluid mt-4">
        
          <div class="Text-Admin">
       
      
        <h1 >¡HOLA ADMINISTRADOR!
        <br>
       	<br>
        POR FAVOR SELECCIONE UNA OPCION</h1>
        
         </div>

  </div>
  </div>
</body>
</html>