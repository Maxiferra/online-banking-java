<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daoImpl.daoCuentaImpl"%>
<%@page import="entidades.Cuenta"%>
<%@page import="entidades.TipoCuenta"%>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	
		<jsp:include page="Nav.jsp"/>
	
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

     <%
                                ArrayList<Cuenta> listaCuentas = null;
                                if (request.getAttribute("listaCuentas") != null) {
                                    listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
                                }
                            %>
    <div class="container-fluid mt-4">
        <div class="row justify-content-between">
            <div class="card-encabezado p-2">
                <div class="d-flex justify-content-between">
                    <div>
                        <h3 class='text-start'>Gestionar Cuenta</h3>
                    </div>
                    <div>
                        <a href="ServletAgregarCuenta?openModal=true" class="btn btn-primary fw-bold fs-6">Crear Cuenta</a>
                        
                    </div>
                </div>
            </div>
        </div>

        <div class="row pt-5">
        
           <div class="card-encabezado pt-2">
             	<form action="ServletMostrarCuenta" method="get">
					<button type="submit" name="btnActualizar" class="btn btn-primary fw-bold fs-6">Actualizar</button>
					
					</form>
                
                   

                <div class="pt-5">
                    <table class="table table-striped text-center" id="dataTable">
                        <thead>
                            <tr>
                                <th scope="col" style="color:black">DNI de Cliente</th>
                                <th scope="col" style="color:black">Tipo de Cuenta</th>
                                <th scope="col" style="color:black">Numero de Cuenta</th>
                                <th scope="col" style="color:black">Saldo</th>
                                <th scope="col" style="color:black">Acciones</th>
                             
                                
                            </tr>
                        </thead>
                        <tbody>
                    

                            <% 
                            if (listaCuentas != null && !listaCuentas.isEmpty()) {
                                for (Cuenta cta : listaCuentas) {
                            %>
                                <tr>
                                    <td class= "text-center"><%= cta.getDNICliente() %></td>
                                    <td class= "text-center"><%= cta.getCuenta().getDescripcion() %></td>
                                    <td class= "text-center"><%= cta.getNumeroCuenta() %></td>
                                    <td class= "text-center"><%= cta.getSaldo() %></td>
                                 
                                    <td>
                                       
                             <button type="button" onclick="abrirModalEditar(
                             '<%= cta.getId() %>',
                             '<%= cta.getDNICliente() %>',
                             '<%= cta.getFechaCreacion() %>',
                             '<%= cta.getTipoCuenta() %>', 
                             '<%= cta.getNumeroCuenta()%>',
                             '<%= cta.getCuenta().getId() %>', 
                             '<%= cta.getCbu()%>', 
                             '<%= cta.getSaldo()%>', 
                             '<%= cta.getEliminado() %>')" 
                             name="btnEditar" value="Editar" class="btn btn-primary" 
                             data-bs-toggle="modal" data-bs-target="#ModalEditar">Editar</button>
								           
                               <%if(cta.getEliminado()== false){%> 
									<button type="button" onclick="abrirModalEliminar('<%= cta.getId()%>')" name="btnEliminar" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#ModalEliminar">Inactivar</button>
								<%}else{%>
									<button type="button" onclick="abrirModalActivar('<%= cta.getId()%>')" name="btnActivar" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ModalActivar">Activar</button>
								<% }%> </td>
                                                                                  
                                                                          
                                </tr>
                            <% 
                                }
                            } else {
                            %>
                                <tr>
                                    <td colspan="5" class="text-center">No hay cuentas disponibles.</td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div> 
    </div>
</div>


  <!-- Modal AGREGAR -->
  <div class="modal fade" id="modalCrearCuenta" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Datos Cuenta</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="container-adapt">
          
          <form action="ServletAgregarCuenta" method="post">  
           
           <div class="row">
             
             	<input type="hidden" id="idCuenta" name="idCuenta">     
              <div class="col-6 mb-3">
              <label for="dni" class="form-label">DNI</label>
                <input type="text" name="txtDNICliente" maxlength="8" required pattern="\d{8}" class="form-control" placeholder="DNI" aria-label="txtDNICliente" aria-describedby="basic-addon1" required>
              </div>
             
              <div class="col-6 mb-3">
               <label for="fecha" class="form-label">Fecha de Creación</label>
                <input type="date" name="txtFechaCreacion" class="form-control" placeholder="Fecha de Creación" aria-label="Fecha de creación" aria-describedby="basic-addon2" required>
              </div>
             
              <div class="col-12 mb-3">
                <label for="tipoCuenta" class="form-label">Tipo de Cuenta</label>	
                <select name="txtTipoCuenta" class="form-control" id="txtTipoCuenta" required>
                  <option value="">Seleccionar</option>
			       
			       <% ArrayList<TipoCuenta> listaTC = (ArrayList<TipoCuenta>) request.getAttribute("listaTC");
			         if (listaTC != null && !listaTC.isEmpty()) {
			                for (TipoCuenta cuentas : listaTC) { %>
			                    <option value="<%= cuentas.getId() %>"><%= cuentas.getDescripcion() %></option>
			        <%}} else { %>
			            <option value="">No disponible</option>
			        
			        <% } %>
                </select>
              </div>
          
          <input type="hidden" id="numeroCuenta" name="numeroCuenta">    
          <input type="hidden" id="CBU" name="CBU">    
          <input type="hidden" id="Saldo" name="Saldo">    
          
           </div>
            
            
            
             <div class="modal-footer">
         	 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
         	 <button type="submit" name="btnGuardar" class="btn btn-primary">Guardar</button>
               
      		</div>
          <div class="mt-3"> 
    	 <% 
   		String mensaje="";
   		 if (request.getAttribute("Mensaje") != null){
         mensaje=(String)request.getAttribute("Mensaje");
    	 %>
    	 
   		  <div class="alert alert-danger alert-dismissible fade show" role="alert">
 		 <label><%= mensaje %></label>
    	 <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
   		 </div>
   		<% } %>
	 	</div>
               
     </form>
          </div>
        </div>
       
      </div>
    </div>
  </div>
    

 
<!-- Modal Editar -->

  <div class="modal fade" id="ModalEditar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalEditar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Datos Cuenta</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      <div class="container-adapt">
	        
	 		<form action="servletEditarCuenta" method="post" >
		      <div class="row">
		      
		       <input type="text" class="form-control" id="id" name="id" hidden>
		       
		       <div class="col-6 mb-3">
				   <label for="dni" class="form-label">DNI</label>
				  <input type="text" name="txtDNICliente" id="txtDNICliente" class="form-control" readonly aria-label="txtDNICliente" aria-describedby="basic-addon1">
				</div>
				<div class="col-6 mb-3">
				<label for="Fecha" class="form-label">Fecha de Creación</label>				  
				 <input type="date" name="txtFechaCreacion" id="txtFechaCreacion" class="form-control" placeholder="Fecha de Creacion" aria-label="Fecha de creacoin" aria-describedby="basic-addon2">
				</div>
				<input type="text" class="form-control" id="txtTipoCuenta" name="txtTipoCuenta" hidden>
				
				<div class="col-6 mb-3">
				 <label for="numCuenta" class="form-label">Numero de Cuenta</label>	
				  <input type="text" name="txtNumeroCuenta" id="txtNumeroCuenta" class="form-control" readonly aria-label="Numero de Cuenta" aria-describedby="basic-addon2">
			 	</div>
				
				<div class="col-6 mb-3">
                <label for="tipoCuenta" class="form-label">Tipo de Cuenta</label>	
                <select class="form-select" name="descripcion" id="descripcion" disabled>
                  <option value="">Seleccionar</option>
			        <% if (listaTC != null && !listaTC.isEmpty()) {
			                for (TipoCuenta cuentas : listaTC) { %>
			                    <option value="<%= cuentas.getId() %>"><%= cuentas.getDescripcion() %></option>
			        <%}} else { %>
			            <option value="">No disponible</option>
			        
			        <% } %>
                </select>
              </div>
				<div class="col-6 mb-3">
				<label for="numCBU" class="form-label">Numero de CBU</label>	
				  <input type="text" name="txtCBU" id="txtCBU" class="form-control" readonly aria-label="CBU" aria-describedby="basic-addon2">
				</div>
				<div class="col-6 mb-3">
					<label for="saldo" class="form-label">Saldo</label>	
				  <input type="number" step="0.01" name="txtSaldo" id="txtSaldo" class="form-control" placeholder="Saldo" aria-label="Saldo" aria-describedby="basic-addon2" pattern="^\d+(\.\d{1,2})?$" title="Solo números, con hasta dos decimales permitidos">
				<input type="text" class="form-control" id="eliminado" name="eliminado" hidden>
				
				</div>
	      </div>
	      
	        <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <button type="submit" class="btn btn-primary" name="btnEditarCuenta">Aceptar</button>
	      </div>
	      
	   <div class="mt-3"> 
    	 <% 
   		String msjEditar="";
   		 if (request.getAttribute("MensajeEdit") != null){
   			msjEditar=(String)request.getAttribute("MensajeEdit");
    	 %>
    	 
   		  <div class="alert alert-danger alert-dismissible fade show" role="alert">
 		 <label><%= msjEditar %></label>
    	 <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
   		 </div>
   	<% } %>

   		 </div>
   	

	      
   
   	</form>
      	</div>
		       
	      </div>
	    
	    </div>
	  </div>
	</div>
</div>

 
 
  <!-- Modal Eliminar -->
 
  <div class="modal fade" id="ModalEliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalEliminar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Eliminar</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      
	      <form action="ServletMostrarCuenta" method="get">
	      <div class="modal-body">
	      <input type="text" class="form-control" id="idEliminar" name="idEliminar" hidden>
	       	<p>Seguro desea eliminar la cuenta?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	       <button type="submit" class="btn btn-danger" name="btnEliminarModal">Aceptar</button>
	      </div>
	      </form>
	      
	    </div>
	  </div>
	</div>
</div>
 
 
   <!-- Modal ACTIVAR -->
 
 <div class="modal fade" id="ModalActivar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalEliminar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Activar</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="ServletMostrarCuenta" method="get">
	      <div class="modal-body">
	      <input type="text" class="form-control" id="idActivar" name="idActivar" hidden>
	       	<p>Seguro desea activar la cuenta?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <button type="submit" class="btn btn-success" name="btnActivarModal">Aceptar</button>
	      </div>
	       </form>
	      
	    </div>
	  </div>
	</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>

<!-- Mensaje de confirmacion agregar/Editar-->
<%
    int filas = -1;
    if (request.getAttribute("cantFilas") != null) { 
        filas = (int) request.getAttribute("cantFilas");
    }
%>

<div class="position-fixed top-50 start-50 translate-middle" style="z-index: 1050;">
    <% if (filas != -1) { %> 
        <% if (filas == 1) { %>
            <div class="mensaje-exito alert alert-success card-alert alert-dismissible fade show text-center" role="alert">
                <i class="bi bi-check-circle"></i>
                Operación realizada con éxito
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else if (filas == 0) { %>
            <div class="mensaje-error alert alert-warning card-alert alert-dismissible fade show text-center" role="alert">
                <i class="bi bi-exclamation-triangle"></i>
                No se pudo realizar la operación
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else if (filas == 2) { %>
            <div class="mensaje-error alert alert-warning card-alert alert-dismissible fade show text-center" role="alert">
                <i class="bi bi-exclamation-triangle"></i>
                El cliente llegó al máximo de cuentas
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
              <% } else if (filas == -3) { %>
            <div class="mensaje-error alert alert-warning card-alertFecha alert-dismissible fade show text-center" role="alert">
                <i class="bi bi-exclamation-triangle"></i>
                   Fecha no valida, Debe ser menor a la fecha actual
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
         <% }  else if (filas == -4) { %>
	         <div class="mensaje-error alert alert-warning card-alert alert-dismissible fade show text-center h-100" role="alert">
	        	 <i class="bi bi-exclamation-triangle"></i>
	         		La cuenta tiene deuda, no es posible inactivarla
	         	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	     	</div>
	       <% } else { %>
            <div class="mensaje-error alert alert-warning card-alert alert-dismissible fade show text-center" role="alert">
                <i class="bi bi-exclamation-triangle"></i>
                DNI no Válido
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
<% } %>
    <% } %>
</div>





<!-- Script para modal carga-->
<%
    Boolean abrir = (Boolean) request.getAttribute("abrirModal");
    if (abrir != null && abrir) {
%>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var modalRegistrar = new bootstrap.Modal(document.getElementById("modalCrearCuenta"));
        modalRegistrar.show();
    });
</script>
<%
    }
%>


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
        }

    });

});

function abrirModalEditar(id,DNICliente, FechaCreacion, TipoCuenta,  numeroCuenta, descripcion, CBU, Saldo, eliminado) {
	document.getElementById("id").value = id;
    document.getElementById("txtDNICliente").value = DNICliente;
    document.getElementById("txtFechaCreacion").value = FechaCreacion;
    document.getElementById("txtTipoCuenta").value = TipoCuenta;
    document.getElementById("txtNumeroCuenta").value = numeroCuenta;
    document.getElementById("descripcion").value = descripcion;
    document.getElementById("txtCBU").value = CBU;
    document.getElementById("txtSaldo").value = Saldo;
    document.getElementById("eliminado").value = eliminado;
    
  
  }
  
function abrirModalEliminar(id) {
    document.getElementById("idEliminar").value = id;
}

function abrirModalActivar(id) {
    document.getElementById("idActivar").value = id;
}



</script>


<!-- Modal Editar -->
<div class="modal fade" id="ModalEditar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalEditar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Datos Cuenta</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="container-adapt">
          <form action="servletEditarCuenta" method="post" id="formEditarCuenta">
            <div class="row">
              <input type="text" class="form-control" id="id" name="id" hidden>
              <div class="col-6 mb-3">
                <label for="dni" class="form-label">DNI</label>
                <input type="text" name="txtDNICliente" id="txtDNICliente" class="form-control" readonly>
              </div>
              <div class="col-6 mb-3">
                <label for="Fecha" class="form-label">Fecha de Creación</label>
                <input type="date" name="txtFechaCreacion" id="txtFechaCreacion" class="form-control">
              </div>
              <input type="text" class="form-control" id="txtTipoCuenta" name="txtTipoCuenta" hidden>
              <div class="col-6 mb-3">
                <label for="numCuenta" class="form-label">Numero de Cuenta</label>
                <input type="text" name="txtNumeroCuenta" id="txtNumeroCuenta" class="form-control" readonly>
              </div>
              <div class="col-6 mb-3">
                <label for="tipoCuenta" class="form-label">Tipo de Cuenta</label>
                <select class="form-select" name="descripcion" id="descripcion" disabled>
                  <option value="">Seleccionar</option>
                  <% if (listaTC != null && !listaTC.isEmpty()) { 
                    for (TipoCuenta cuentas : listaTC) { %>
                    <option value="<%= cuentas.getId() %>"><%= cuentas.getDescripcion() %></option>
                  <% }} else { %>
                    <option value="">No disponible</option>
                  <% } %>
                </select>
              </div>
              <div class="col-6 mb-3">
                <label for="numCBU" class="form-label">Numero de CBU</label>
                <input type="text" name="txtCBU" id="txtCBU" class="form-control" readonly>
              </div>
              <div class="col-6 mb-3">
                <label for="saldo" class="form-label">Saldo</label>
                <input type="number" step="0.01" name="txtSaldo" id="txtSaldo" class="form-control" placeholder="Saldo" aria-label="Saldo" min="0" required>
                <input type="text" class="form-control" id="eliminado" name="eliminado" hidden>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="submit" class="btn btn-primary" name="btnEditarCuenta">Aceptar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  document.getElementById("formEditarCuenta").addEventListener("submit", function(event) {
    const saldo = parseFloat(document.getElementById("txtSaldo").value);
    
    // Verificar si el saldo es negativo
    if (isNaN(saldo) || saldo < 0) {
      alert("El saldo no puede ser negativo.");
      event.preventDefault(); // Evitar el envío del formulario
    }
  });

  // Para asegurar que no se pueda escribir números negativos en el campo de saldo
  document.getElementById("txtSaldo").addEventListener("input", function(event) {
    let value = this.value;
    // Si el valor es negativo, se borra el valor
    if (parseFloat(value) < 0) {
      this.value = 0;
    }
  });
</script>


</body>
</html>