<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Prestamo" %>
<%@ page import="entidades.Cuota" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<title>Detalles de prestamo</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
	
<style type="text/css">

<jsp:include page="css\StyleSheet.css"></jsp:include>

.table-container {
    width: 100%;
   background-color: rgba(255, 255, 255, 0.9); 
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
    margin-top: 30px;
    margin-bottom: 30px;
	max-height: 800px;
    overflow-y: auto;
}

.table-container .table {
    background-color: rgba(255, 255, 255, 0.9); 
    width: 100%;
    border-collapse: separate; 
}

.table thead th, .table tbody td {
    background-color: rgba(255, 255, 255, 0.9); 
    color: #333; 
    border: 1px solid #ddd; 
    padding: 20px;
}

.table-container h3 {
    font-size: 24px;
    font-weight: bold;
    color: #333;
    text-align: center;
    margin-bottom: 20px;
}

.btn-primary, .btn-secondary {
    background-color: #007bff;
    color: #ffffff;
    font-weight: bold;
    font-size: 16px;
}

.modal-dialog {
   max-width: 600px; 
    
}

.modal .modal-content {
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.modal-header {
    background-color: #007bff;
    color: #ffffff;
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
}

.modal-title {
    font-weight: bold;
    font-size: 20px;
}

.modal-body .container {
    padding: 10px;
}

.modal-body span {
    font-weight: bold;
    color: #333;
}

.modal-footer .btn-close {
    background-color: #007bff;
    color: #ffffff;
    border-radius: 50%;
}

.postbody {
	display: flex;
	height: 800px;
	margin-left: 190px;
	margin-top: 2px
}

.card {
	max-width: 500px;
	height: 500px;
	width: 100%;
	border-radius: 10px;
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);
	margin-top: 30px
}

.card-header {
	background-color: rgba(255, 255, 255, 0.9);
	color: #ffffff;
	text-align: center;
}

.form-group {
	margin-bottom: 1rem;
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
            <li class="nav-item"><a class="nav-link active fs-2 text-light" aria-current="page" href="InicioUser.jsp">Inicio</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="Transferencias.jsp">Transferencias</a></li>
             <li class="nav-item"><a class="nav-link fs-2 text-light" href="SolicitarCuenta.jsp">Solicitar Cuenta</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="Prestamos.jsp">Prestamos</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="servletMiPerfil">Mi Perfil</a></li>
        </ul>
    </div>
</div>

<div class="parteDer-ADM border border-dark border-3 rounded-end">
    <div class="table-container">
        <h3>Préstamos Solicitados</h3>
        	<table class="table table-striped table-bordered" id="prestamosTable">
            	<thead class="table-light">
                	<tr>
                    	<th>Numero de cuota</th>
                    	<th>Monto</th>
                    	<th>Abonado</th>
                    	<th>Acciones</th>
                	</tr>
            	</thead>
          		<tbody>
          		
          			<% 
	          			String acciones = "No hay acciones disponibles";
    	  				String pendiente = "Pago pendiente";
    	  				
          				int idPrestamo=(int) request.getAttribute("idPrestamo");
          				int restantes=(int) request.getAttribute("restantes");
          				double monto=(double) request.getAttribute("montoAux");
          				ArrayList<Cuota> listaCuotas = (ArrayList<Cuota>) request.getAttribute("listaCuotas");
          				
          				if (listaCuotas != null && !listaCuotas.isEmpty()) {
					        for (Cuota cuota: listaCuotas) {
					%>
					            <tr>
					                <td><%= cuota.getNumeroCuota() %></td>
					                <td><%= "$"+cuota.getMonto() %></td>
					                <td><%= cuota.getFechaPago() %></td> 
					                <td><%= acciones %></td>
					            </tr>
					<% 
					        }
					        if (restantes > 0) {
					%>
					            <tr>
					                <td><%= listaCuotas.size()+1 %></td>
					                <td><%= "$"+monto %></td>
					                <td><%= pendiente %></td> 
					                <td>
						                <div>
				                        	<form action="ServletPagarCuota" onsubmit="return confirmarAccion()" method="post" id="cuotasForm">
				                                <input type="hidden" name="cuotaPago" value="<%= idPrestamo %>">
				                                <input type="hidden" name="cuotaMonto" value="<%= monto %>">
				                                <input type="hidden" name="cuotaNumero" value="<%= listaCuotas.size()+1 %>">  
				                                <button type="submit" name="accion" value="pagar" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Pagar cuota</button>
			                           		</form>
			                           	</div>
					                </td>
					            </tr>
			        <%
					    	}
          				} else {
          					double primerCuota=(double)request.getAttribute("primerCuota");
          			%>
	          				<tr>
				                <td><%= 1 %></td>
				                <td><%= "$"+primerCuota %></td>
				                <td><%= pendiente %></td> 
				                <td>
					                <div>
			                        	<form action="ServletPagarCuota" onsubmit="return confirmarAccion()" method="post" id="cuotasForm">
			                                	<input type="hidden" name="cuotaPago" value="<%= idPrestamo %>">
				                                <input type="hidden" name="cuotaMonto" value="<%= primerCuota %>">
				                                <input type="hidden" name="cuotaNumero" value="<%= 1 %>">  
			                                <button type="submit" name="accion" value="pagar" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Pagar cuota</button>
		                           		</form>
		                           	</div>
				                </td>
				            </tr>
          			<%
          				}
					%>
           		</tbody>
       		</table>
        <div class="text-center mt-3">
            <a class="btn btn-primary" href="ServletPrestamosCliente">Regresar</a>
        </div>
    </div>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script>
$(document).ready(function() {
    $('#prestamosTable').DataTable({
        autoWidth: false, 
        columnDefs: [
            { targets: "_all", defaultContent: "" } 
        ],
        language: {
            search: "Buscar:", 
            lengthMenu: "_MENU_ Registros por página",
            info: "Mostrando _START_ a _END_",
            entries: {
                _: 'Registros por página', 
                1: 'página'
            }
        },
    order: [[0, 'desc']]
    });
});
</script>
<script type="text/javascript">
    function confirmarAccion() {
        var respuesta = confirm("¿Desea proceder?");
        return respuesta;  
    }
</script>
</body>
</html>