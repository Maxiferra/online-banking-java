<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daoImpl.daoCuentaImpl"%>
<%@page import="entidades.Cuenta"%>
<%@page import="entidades.TipoCuenta"%>	
<%@page import="entidades.Movimiento"%>
<!DOCTYPE html>
<html lang="es">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/3.0.3/css/responsive.bootstrap5.css">
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

.btn-light-shadow:hover {
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); /* Sombra suave */
    transition: box-shadow 0.3s ease; /* Suaviza el efecto */
}

.move-left {
            transition: margin-right 100s ease; /* Transición suave */
        }

        .move-left-left {
            margin-right: 100px; /* Desplazamiento hacia la izquierda */
        }


.modal-content {
        border-radius: 10px; /* Bordes redondeados */
    }

    .modal-header {
        background-color: #007bff; /* Color de fondo en el encabezado */
        color: white; /* Color del texto */
        font-weight: bold;
    }

    .modal-body {
        font-size: 16px; /* Tamaño de fuente de los textos */
    }

    .modal-body span {
        font-size: 14px; /* Tamaño de fuente más pequeño para los números */
        font-weight: normal; /* Quitar negrita */
        color: #333; /* Color oscuro para los números */
    }

    .modal-footer {
        background-color: #f1f1f1; /* Color de fondo para el pie del modal */
    }
	 table td, table th {
        color: black !important;  /* Forzamos el color negro */
    }


<jsp:include page="css\StyleSheet.css"></jsp:include>

</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Micuenta</title>
</head>



<body>

	<div class="encabezado-user">
	 
	     <div class="bg-light text-dark">
			
		
			<jsp:include page="Nav.jsp" />
	 </div>
	 
	 
</div>		
	<div class="parteIzq-detalle border border-dark border-3 rounded-end">

		<div class="mt-5 ps-3">

			<ul class="nav flex-column">
				<li class="nav-item"><a class="nav-link active fs-2 text-light"
					aria-current="page" href="servletInicioUser">Inicio</a></li>
				<li class="nav-item"><a class="nav-link fs-2 text-light"
					href="Transferencias.jsp">Transferencias</a></li>
				<li class="nav-item"><a class="nav-link fs-2 text-light"
					href="SolicitarCuenta.jsp">Solicitar Cuenta</a></li>
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

<div>

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger" role="alert">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>
    
      <% if (listaCuentas != null && listaCuentas.size() > 1) { %>
            <form action="servletInicioUser" method="GET">
                <div class="card shadow-lg" style="border-radius: 10px; background: linear-gradient(to right, #d3d3d3, #a9a9a9); border: none; color: black;">
                    <div class="card-body">
                        <h3 class="card-title text-center text-primary mb-4 ">Cuenta seleccionada</h3>
                        <div class="form-group mb-4">
                            <label for="cuentas" class="font-weight-bold" >Elige tu cuenta:</label>
                            <select id="cuentas" name="numeroCuenta" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" onchange="this.form.submit();" 
                                style="background-color: #f1f1f1; color: #333; border: 1px solid #ccc; border-radius: 5px;">
                                <option value="" id="opcionSeleccione" disabled selected>Seleccione...</option>
                                <% for (Cuenta cuenta : listaCuentas) { %>
                                    <option value="<%= cuenta.getNumeroCuenta() %>" 
                                            <%= cuentaDetalles != null && cuentaDetalles.getNumeroCuenta().equals(cuenta.getNumeroCuenta()) ? "selected" : "" %>>
                                        CTA$  <%= cuenta.getNumeroCuenta() %> / <%= cuenta.getCuenta().getDescripcion() %> - Saldo: $<%= cuenta.getSaldo() %> 
                                    </option>
                                <% } %>
                            </select>
                           			 <button type="button" class="btn btn-success rounded-pill" data-bs-toggle="modal" data-bs-target="#cbuModal">
								  				Ver CBU
									</button>
									
							
									
                        </div>
                                              
							                                                                              
                        <div id="saldoCuenta" class="mt-4 text-center" 
                            style="background-color: #fff; padding: 20px; border-radius: 20px; width: 60%; max-width: 600px; margin: 0 auto; font-size: 1.5rem; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); color: #000;">
                            <p class="lead">
                                <strong>Saldo:</strong> 
                                <span class="display-4 font-weight-bold text-dark">
                                    $<%= cuentaDetalles != null ? cuentaDetalles.getSaldo() : 0.0 %>
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
            </form>
        <% } else if (listaCuentas != null && listaCuentas.size() == 1) { %>
            
            <div class="card mb-4">
                <div class="card-body">
                
                     <button type="button" class="btn btn-success rounded-pill" data-bs-toggle="modal" data-bs-target="#cbuModal">
								  				Ver CBU
					 </button>
                
                    <h5 class="card-title">Cuenta: <%= listaCuentas.get(0).getNumeroCuenta() %> / <%= listaCuentas.get(0).getCuenta().getDescripcion() %> </h5>
                     
					 		<div id="saldoCuenta" class="mt-4 text-center" 
                            style="background-color: #fff; padding: 20px; border-radius: 20px; width: 60%; max-width: 600px; margin: 0 auto; font-size: 1.5rem; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); color: #000;">
                            <p class="lead">
                   			 <p class="lead">
                                <strong>Saldo:</strong> 
                                <span class="display-4 font-weight-bold text-dark">
                                    $<%= cuentaDetalles != null ? cuentaDetalles.getSaldo() : 0.0 %>
                                </span>
                            </p>
                  		</div>
                </div>
            </div>
        <% } else { %>
            
            <div style="width: 100%; text-align: center; position: fixed; bottom: 10px; left: 0; background-color: white; color: black; padding: 10px; border: 1px solid #ccc; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
                No tienes cuentas asociadas.
            </div>
        <% } %>
     
        <div class="row mt-5">
            <div class="col-12">
                <div class="card shadow">
                    <div class="card-header text-center bg-light">
                        <h3 class="fw-bold">Detalle de Movimientos</h3>
                    </div>
                    
                         <div class="d-flex justify-content-end mt-3">
                         
	 <button type="button" class="btn btn-light btn-light-shadow move-left me-5 d-flex align-items-center" data-bs-toggle="modal" data-bs-target="#filtroModal" style="border: 1px solid #ccc;">
      <i class="bi bi-filter me-2" style="color: #666;"></i> 
            									Filtro
     </button>
						</div>	
								                                       
                  <div class="card-body">
                        <table class="table table-striped text-center" id="TablaDetalle">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Detalle</th>
                                    <th scope="col">Importe</th>
                                    <th scope="col">Descripción</th>
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
        </div>
    </div>
    
    	</div>

</div>


<div class="modal fade" id="cbuModal" tabindex="-1" aria-labelledby="cbuModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="cbuModalLabel">CBU/TIPO DE CUENTA</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
   
        <span class="display-4 font-weight-bold text-dark">
         CBU : <%= cuentaDetalles != null ? cuentaDetalles.getCbu() : "No disponible" %>
          <br>
         Tipo de Cuenta :  <% if (cuentaDetalles != null && cuentaDetalles.getCuenta() != null) { %>
            <%= cuentaDetalles.getCuenta().getDescripcion() %>  
          <% } else { %>
            No disponible
          <% } %>
        </span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="filtroModal" tabindex="-1" aria-labelledby="filtroModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="filtroModalLabel">Filtrar Movimientos por Monto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
            
                <form action="servletInicioUser" method="GET">
                    <div class="form-group mb-3">
                        <label for="minMonto">Monto Mínimo</label>
                        <input 
                            type="number" 
                            id="minMonto" 
                            name="minMonto" 
                            class="form-control" 
                            step="0.1" 
                            placeholder="Desde $" 
                            value="${minMonto != null ? minMonto : ''}">
                    </div>
                    <div class="form-group mb-3">
                        <label for="maxMonto">Monto Máximo</label>
                        <input 
                            type="number" 
                            id="maxMonto" 
                            name="maxMonto" 
                            class="form-control" 
                            step="0.1" 
                            placeholder="Hasta $" 
                            value="${maxMonto != null ? maxMonto : ''}">
                    </div>
                    <button type="submit" name="aplicarFiltro" value="true" class="btn btn-primary w-100">Aplicar Filtro</button>
                </form>
              
                <form action="servletInicioUser" method="GET">
                    <button type="submit" name="limpiarFiltro" value="true" class="btn btn-secondary w-100 mt-2">Limpiar Filtro</button>
                </form>
            </div>
        </div>
    </div>
</div>         
                
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.bootstrap5.js"></script>
<script src="https://cdn.datatables.net/responsive/3.0.3/js/dataTables.responsive.js"></script>
<script src="https://cdn.datatables.net/responsive/3.0.3/js/responsive.bootstrap5.js"></script>


<script>

$(document).ready(function() {
    $('#TablaDetalle').DataTable({
        autoWidth: false, 
        columnDefs: [
            { 
                targets: "_all", 
                defaultContent: "" 
            }
        ],
        language: {
            search: "Buscar:", 
            lengthMenu: "Mostrar _MENU_ Registros por página",
            info: "Mostrando _START_ a _END_",
            entries: {
                _: 'Registros por página', 
                1: 'página'
            }
        }
    });
});
</script>

</body>
</html>