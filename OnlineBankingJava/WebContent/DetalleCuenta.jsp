<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.ArrayList"%>
<%@page import="daoImpl.daoCuentaImpl"%>
<%@page import="entidades.Cuenta"%>
<%@page import="entidades.TipoCuenta"%>	
<%@page import="entidades.Movimiento"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle Cuenta</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<style type="text/css">

<jsp:include page="css\StyleSheet.css"></jsp:include>

</style>
</head>
<body>

	<div class="encabezado-user">
	 
	     <div class="bg-light text-dark">
			
			<div class="position-absolute ms-5 mb-4 top-1 start-3">
			
	<img src="https://img2.freepnges.com/20180905/wgh/kisspng-computer-icons-merchant-clip-art-portable-network--1713943890607.webp"  alt="Foto de banco"
		class="rounded-circle me-3" style="width: 80px; height: 80px;">
	

		</div>
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
					href="Prestamos.jsp">Préstamos</a></li>
				<li class="nav-item"><a class="nav-link fs-2 text-light"
					href="servletMiPerfil">Mi Perfil</a></li>
			</ul>




		</div>

	</div>
<div class="parteDer-detalle border border-dark border-3 rounded-end">

<div class="container-fluid">

<%
    ArrayList<Cuenta> listaCuentas = null;
    if (request.getAttribute("listaCuentas") != null) {
        listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
    }

    Cuenta cuentaDetalles = (Cuenta) request.getAttribute("cuentaDetalles");
    
    // Obtener los movimientos de la cuenta seleccionada
    ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) request.getAttribute("movimientos");
%>

      <div class="row">
        <div class="row"> 
    	<div class="card-cuenta2">
            
              <h5 class="text-caja mt-2">Cuenta: </h5>
              <p class="card-text text-star fs-4 fw-bold">Saldo: $</p>
             <p class="card-text text-star fs-5 fw-bold">CBU: </p>
     
      	</div>
 
  </div>
 	
			<div class="card-cuenta3 mt-3">
			<form action="" method="get">
					<button type="submit" name="btnActualizar" class="btn btn-primary">Actualizar</button>					
				</form>
				
			</div>
				<table class="table table-striped text-center id="dataTable">
				    <thead>
				        <tr>
				   
				    <th scope="col" style="color:black">#</th>
                    <th scope="col" style="color:black">Fecha</th>
                    <th scope="col" style="color:black">Detalle</th>
                    <th scope="col" style="color:black">Importe</th>
                    <th scope="col" style="color:black">Descripción</th>

				        </tr>
				    </thead>
				    <tbody>
				  <% 
                                    if (movimientos != null && !movimientos.isEmpty()) { 
                                        int i = 1; 
                                        for (Movimiento movimiento : movimientos) { 
                                %>
                                    <tr>
                                        <th scope="row"><%= i++ %></th>
                                        <td><%= movimiento.getFecha() %></td>
                                        <td><%= movimiento.getDetalle() %></td>
                                        <td>$<%= movimiento.getImporte() %></td>
                                        <td><%= movimiento.getTipoMovimiento().getDescripcion() %></td>
                                    </tr>
                                <% 
                                        } 
                                    } else { 
                                %>
                                    <tr>
                                        <td colspan="5">No hay movimientos disponibles para esta cuenta.</td>
                                    </tr>
                                <% } %>
             
				    </tbody>
				</table>
			
		

	</div>

</div>

</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>


<script>
$(document).ready(function() {
    $('#dataTable').DataTable({
        autoWidth: false, 
        columnDefs: [
            { targets: "_all", defaultContent: "" } 
        ]
    });
});
</script>

</body>
</html>