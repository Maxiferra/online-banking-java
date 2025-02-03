<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Prestamo" %>
<%@ page import="entidades.cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestión Préstamos</title>
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
   <jsp:include page="css/StyleSheet.css"></jsp:include>
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

<div class="parteIzq-ADM border border-dark border-3 rounded-end">
	<div class="mt-5 ps-3">
		<ul class="nav flex-column">
        	<li class="nav-item">
        		<a class="nav-link active fs-4 text-light" aria-current="page" href="ServletMostrarCuenta">
        			Gestionar Cuentas
        		</a>
        	</li>
            <li class="nav-item">
            	<a class="nav-link fs-4 text-light" aria-current="page" href="ServletMostrarClientes">
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
            	<a class="nav-link fs-4 text-light" href="Reportes.jsp">
            		Reportes
            	</a>
            </li>
		</ul>
	</div>
</div>

<div class="parteDer-ADM border border-dark border-3 rounded-end" >
	<div class="table-container ">
		<div class="card-encabezado p-2">	
        	<h3 class="text-center my-4">Solicitudes de Préstamos Pendientes</h3>
        	<table class="table table-striped table-bordered" id="dataTable">
            	<thead class="table-light">
                	<tr>
                    	<th scope="col" style="color:black">ID Solicitud</th>
                    	<th scope="col" style="color:black">Cliente</th>
                    	<th scope="col" style="color:black">Cuenta</th>
                    	<th scope="col" style="color:black">Fecha solicitud</th>
                    	<th scope="col" style="color:black">Monto Solicitado</th>
                    	<th scope="col" style="color:black">Cuotas</th>
                    	<th scope="col" style="color:black">Estado</th>
                    	<th scope="col" style="color:black">Acciones</th>
                	</tr>
            	</thead>
          		<tbody>
          		
          			<% 
					    ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
					    ArrayList<String> listaClientes = (ArrayList<String>) request.getAttribute("listaClientes");
					    ArrayList<String> listaCuentas = (ArrayList<String>) request.getAttribute("listaCuentas");
          				int counter=0;
          				if (listaPrestamos != null && !listaPrestamos.isEmpty()) {
					        for (Prestamo prestamo: listaPrestamos) {
					        	
					        	String nombreCliente= listaClientes.get(counter);
					        	String numeroCuenta= listaCuentas.get(counter);
					        	counter++;
					%>
					            <tr>
					                <td><%= prestamo.getId() %></td>
					                <td><%= nombreCliente %></td>
					                <td><%= numeroCuenta %></td>
					                <td><%= prestamo.getFechaAlta() %></td>
					                <td><%= prestamo.getImportePedido() %></td>
					                <td><%= prestamo.getCantidadCuotas() %></td>
					                <td>
									    <%
									        int estadoPrestamo = prestamo.getEstadoPrestamo();
									        String estadoLabel = "";
									        switch (estadoPrestamo) {
									            case 1:
									                estadoLabel = "Aprobado";
									                break;
									            case 2:
									                estadoLabel = "Pendiente";
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
				                         	if (prestamo.getEstadoPrestamo() == 2) {
				                        %>
							                <form action="ServletAutorizarPrestamo" onsubmit="return confirmarAccion()" method="post">
				                                <input type="hidden" name="idPrestamo" value="<%= prestamo.getId() %>">
				                                <button type="submit" name="accion" value="autorizar" class="btn btn-success btn-sm">Autorizar</button>
				                            </form>
				                            <form action="ServletRechazarPrestamo" onsubmit="return confirmarAccion()" method="post">
				                                <input type="hidden" name="idPrestamo" value="<%= prestamo.getId() %>">
				                                <button type="submit" name="accion" value="rechazar" class="btn btn-danger btn-sm">Rechazar</button>
			                           		</form>
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
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>
<script>
$(document).ready(function() {
    $('#dataTable').DataTable({
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
<script type="text/javascript">
    function confirmarAccion() {
        var respuesta = confirm("¿Desea proceder?");
        return respuesta;  
    }
</script>
</body>
</html>