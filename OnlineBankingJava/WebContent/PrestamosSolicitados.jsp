<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Prestamo" %>
<%@ page import="entidades.cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<title>Prestamos solicitados</title>

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
.card-encabezado {
	max-width: 100%;
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
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="Prestamos.jsp">Prestamos</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="servletMiPerfil">Mi Perfil</a></li>
        </ul>
    </div>
</div>

<div class="parteDer-ADM border border-dark border-3 rounded-end">
    <div class="table-container">
      <div class="card-encabezado p-2">	
        <h3>Préstamos Solicitados</h3>
        	<table class="table table-striped table-bordered" id="prestamosTable">
            	<thead class="table-light">
                	<tr>
                    	<th>ID Solicitud</th>
                    	<th>Cuenta</th>
                    	<th>Fecha solicitud</th>
                    	<th>Monto Solicitado</th>
                    	<th>Cuotas restantes</th>
                    	<th>Estado</th>
                    	<th>Acciones</th>
                	</tr>
            	</thead>
          		<tbody>
          		
          			<% 
					    ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
					    ArrayList<String> listaClientes = (ArrayList<String>) request.getAttribute("listaClientes");
					    ArrayList<String> listaCuentas = (ArrayList<String>) request.getAttribute("listaCuentas");
          				int counter=0;
          				int id;
          				if (listaPrestamos != null && !listaPrestamos.isEmpty()) {
					        for (Prestamo prestamo: listaPrestamos) {
					        	
					        	String nombreCliente= listaClientes.get(counter);
					        	String numeroCuenta= listaCuentas.get(counter);
					        	counter++;
					%>
					            <tr>
					                <td><%= prestamo.getId() %></td>
					                <td><%= numeroCuenta %></td>
					                <td><%= prestamo.getFechaAlta() %></td>
					                <td><%= prestamo.getImportePedido() %></td>
					                <td><%= prestamo.getCantidadCuotas() %></td>
					                <%id=prestamo.getId(); %>
					                <td>
									    <%
									        int estadoPrestamo = prestamo.getEstadoPrestamo();
									        String estadoLabel = "";
									        switch (estadoPrestamo) {
									            case 1:
									            	if(prestamo.getCantidadCuotas()==0){
									            		estadoLabel = "Prestamo abonado por completo";
									            	}
									            	else{
									            		estadoLabel = "Aprobado. Cuotas impagas";
									            	}
									                break;
									            case 2:
									                estadoLabel = "Pendiente de aprobacion";
									                break;
									            case 3:
									                estadoLabel = "Rechazado";
									                break;
									            default:
									                estadoLabel = "Desconocido";
									        }
									    %>
									    <%= estadoLabel %>
									</td>
					                <td>
						                <div>
						                <%
				                         	if (prestamo.getEstadoPrestamo() == 1) {
				                        %>
				                        	<form action="ServletCuotas" method="post" id="prestamoForm">
				                                <input type="hidden" name="prestamoDetalles" value="<%= prestamo.getId() %>">
				                                <button type="submit" name="accion" value="autorizar" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Ver detalles</button>
			                           		</form>
			                           	<%
			                                }
				                         	else{
				                         		String acciones = "No hay acciones disponibles";
				                         		%>
				                         		<%= acciones %>
				                         		<%
				                         	}
			                            %>
			                           	</div>
					                </td>
					            </tr>
					<% 
					        }
					    } else { 
					%>
					        <tr>
					            <td colspan="6">No hay prestamos disponibles.</td>
					        </tr>
					<% 
					    } 
					%>
           		</tbody>
       		</table>
    </div>
  </div>
 <div class="text-center mt-3">
     <a class="btn btn-info text-white fw-bold fs-5" href="Prestamos.jsp">Regresar</a>
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
    order: [[2, 'desc']]
    });
});
</script>
</body>
</html>