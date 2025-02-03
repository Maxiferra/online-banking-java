<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>   
   <%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.cliente" %>
<%@ page import="entidades.Usuario" %>
<%@ page import="entidades.Provincias" %>
<%@ page import="entidades.Localidades" %>
<%@ page import="entidades.Nacionalidades" %>
<!DOCTYPE html >
<html lang="es">
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
        <%ArrayList<cliente> listaClientes = (ArrayList<cliente>) request.getAttribute("listaClientes");%>
<div class="encabezado-user">
<div class="bg-light text-dark">		
	
			<jsp:include page="Nav.jsp" />
	 </div>
	</div>
	<div class="parteIzq-ADM border border-dark border-3 rounded-end">
		<div class="mt-5 ps-3">
			<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link active fs-4 text-light"
					aria-current="page" href="ServletMostrarCuenta">Gestionar Cuentas</a></li>
				<li class="nav-item"><a class="nav-link fs-4 text-light"
					aria-current="page" href="ServletMostrarClientes">Gestionar Clientes</a></li>
				<li class="nav-item"><a class="nav-link fs-4 text-light"
					href="servletAgregarAdmin?Param=list">Gestionar Administradores</a></li>
				<li class="nav-item"><a class="nav-link fs-4 text-light"
					href="ServletMostrarPrestamos">Gestionar Préstamos</a></li>
				<li class="nav-item"><a class="nav-link fs-4 text-light"
					href="Reportes.jsp">Reportes</a></li>
			</ul>
		</div>
	</div>

<div class="parteDer-ADM border border-dark border-3 rounded-end">
	<div class="container-fluid">
		<div class="row justify-content-between">
			<div class="card-admin2 p-2">	
				<div class="d-flex justify-content-between">
					<div>
						<h3 class="text-start">Gestionar Cliente</h3>
					</div>
					<div>
						<a href="servletRegistrarCliente?openModal=true" class="btn btn-primary fw-bold fs-6">Crear Usuario</a>
					</div>	
				</div>
			</div>	
		</div>	 	 

		<div class="row mt-3">
			<div class="d-flex justify-content-end mb-3">			
			</div>
			<div class="card-encabezado">
			<form action="ServletMostrarClientes" method="get">
					<button type="submit" name="btnActualizar" class="btn btn-primary fw-bold fs-6 mt-2">Actualizar</button>					
				</form>
				<table class="table table-striped text-center" id="dataTable">
				    <thead>
				        <tr>
				            <th scope="col" style="color:black">DNI</th>
				            <th scope="col" style="color:black">CUIL</th>
				            <th scope="col" style="color:black">Nombre</th>
				            <th scope="col" style="color:black">Apellido</th>
				            <th scope="col" style="color:black">Usuario</th>           
				            <th scope="col" style="color:black">Acciones</th>
				        </tr>
				    </thead>
				    <tbody>
				        <%				
				if (listaClientes != null && !listaClientes.isEmpty()) {
				    for (cliente cliente : listaClientes) {
				%>
				        <tr>
				            <td><%= cliente.getDni() %></td>
				            <td><%= cliente.getCuil() %></td>
				            <td><%= cliente.getNombre() %></td>
				            <td><%= cliente.getApellido() %></td>
				            <td><%= cliente.getUsuario() != null ? cliente.getUsuario().getTxtUsuario() : "Sin usuario" %></td>          
				            <td>
				                <button type="button" class="btn btn-primary" 
								    onclick="abrirModalEditar(
								        '<%= cliente.getId() %>',
								        '<%= cliente.getDni() %>',
								        '<%= cliente.getCuil() %>',
								        '<%= cliente.getNombre() %>',
								        '<%= cliente.getApellido() %>',
								        '<%= cliente.getSexo() %>',
								        '<%= cliente.getNacionalidad().getId() %>',
								        '<%= cliente.getFechaNacimiento() %>',
								        '<%= cliente.getDireccion() %>',
								        '<%= cliente.getLocalidad().getId() %>',
								        '<%= cliente.getProvincia().getId() %>',
								        '<%= cliente.getEmail() %>',
								        '<%= cliente.getTelefono() %>',								        
								        '<%= cliente.getUsuario() != null ? cliente.getUsuario().getTxtUsuario() : "" %>',
								        '<%= cliente.getUsuario() != null ? cliente.getUsuario().getPassword() : "" %>',
        								'<%= cliente.getEliminado() %>'						        
								    )" data-bs-toggle="modal" data-bs-target="#ModalEditar">
								    Editar
								</button>          
				                <% if (!cliente.getEliminado()) { %> 
				                    <button type="button" onclick="abrirModalEliminar('<%= cliente.getId() %>')" name="btnEliminar" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#ModalEliminar">Inactivar</button>
				                <% } else { %>
				                    <button type="button" onclick="abrirModalActivar('<%= cliente.getId() %>')" name="btnActivar" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ModalActivar">Activar</button>
				                <% } %>
				            </td>                                      
				        </tr>
				        <%
				            }
				        } else {
				        %>
				        <tr>
				            <td colspan="6">Presiona "Actualizar" para cargar los datos</td>
				        </tr>
				        <%
				        }
				        %>
				    </tbody>
				</table>
			</div>
		</div>		
	</div>
</div>

<!-- Modal Registrar al Cliente-->
<div class="modal fade" id="modalRegistrar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Datos Cliente</h5> 
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="container-fluid">
          <form action="servletRegistrarCliente" method="post">
                    <div class="mb-3">
              <label for="dni" class="form-label">DNI</label>
              <input type="text" class="form-control" id="dni" maxlength="8" name="dni" required pattern="\d{8}" title="El DNI debe ser de 8 numeros">
            </div>
            <div class="mb-3">
              <label for="cuil" class="form-label">CUIL</label>
              <input type="text" class="form-control" id="cuil" maxlength="13" name="cuil" required pattern="\d{2}-\d{8}-\d{1}" title="Solo Numeros y Formato de cuil -">
            </div>
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombres</label>
              <input type="text" class="form-control" id="nombre" name="nombre" required pattern="[A-Za-z\s]+" title="El nombre no puede contener numeros">
            </div>
            <div class="mb-3">
              <label for="apellido" class="form-label">Apellidos</label>
              <input type="text" class="form-control" id="apellido" name="apellido" required pattern="[A-Za-z\s]+" title="El apellido no puede contener numeros">
            </div>
            <div class="mb-3">
              <label for="genero" class="form-label">Género</label>
              <select class="form-select" id="genero" name="genero" required>
                <option value="">Seleccionar</option>
                <option value="masculino">Masculino</option>
                <option value="femenino">Femenino</option>
                <option value="otro">Otro</option>
              </select>
            </div>
            <div class="mb-3">
			    <label for="nacionalidad" class="form-label">Nacionalidad</label>
			    <select class="form-select" id="nacionalidad" name="nacionalidad" required>
			        <option value="">Seleccionar</option>
			        <% ArrayList<Nacionalidades> listaNacionalidades = (ArrayList<Nacionalidades>) request.getAttribute("listaNacionalidades");
			            if (listaNacionalidades != null && !listaNacionalidades.isEmpty()) {
			                for (Nacionalidades nacionalidad : listaNacionalidades) {%>
			                    <option value="<%= nacionalidad.getId() %>"><%= nacionalidad.getNombre() %></option>
			        <%}} else {%>
			            <option value="">No hay Nacionalidades disponibles</option>
			        <%}%>
			    </select>
			</div>
            <div class="mb-3">
              <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
              <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
            </div>
            <div class="mb-3">
              <label for="direccion" class="form-label">Dirección</label>
              <input type="text" class="form-control" id="direccion" name="direccion" required pattern="[A-Za-z0-9\s]+" title="Solo letras y numeros">
            </div>
            <div class="mb-3">
    <label for="localidad" class="form-label">Localidad</label>
    <select class="form-select" id="localidad" name="localidad" required onchange="actualizarProvincia()">
        <option value="">Seleccionar</option>
        <% 
        ArrayList<Localidades> listaLocalidades = (ArrayList<Localidades>) request.getAttribute("listaLocalidades");
        if (listaLocalidades != null && !listaLocalidades.isEmpty()) {
            for (Localidades localidad : listaLocalidades) { 
        %>
            <option value="<%= localidad.getId() %>" data-provincia="<%= localidad.getIdProvincia() %>">
                <%= localidad.getNombre() %>
            </option>
        <% 
            }
        } else { 
        %>
            <option value="">No hay Localidades disponibles</option>
        <% 
        } 
        %>
    </select>
	</div>
            <div class="mb-3">
		    <label for="provincia" class="form-label">Provincia</label>
			<select class="form-select" id="provincia" name="provincia" required>
			    <option value="">Seleccionar</option>
			    <% ArrayList<Provincias> listaProvincias = (ArrayList<Provincias>) request.getAttribute("listaProvincias");
			        if (listaProvincias != null && !listaProvincias.isEmpty()) {
			            for (Provincias provincia : listaProvincias) { %>
			                <option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>
			    <% }} else { %>
			        <option value="">No hay provincias disponibles</option>
			    <% } %>
			</select>
		</div>
            <div class="mb-3">
              <label for="correo" class="form-label">Correo Electrónico</label>
              <input type="email" class="form-control" id="correo" name="correo" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Ingrese un E-mail válido">
            </div>
            <div class="mb-3">
              <label for="telefono" class="form-label">Teléfono</label>
              <input type="text" class="form-control" id="telefono" name="telefono" required pattern="\d+" title="Solo numeros">
            </div>
            <div class="mb-3">
              <label for="usuario" class="form-label">Nombre de Usuario</label>
              <input type="text" class="form-control" id="usuario" name="usuario" required pattern="[A-Za-z0-9\.\-]+" title="Solo letras, numeros,.-">
            </div>
            <div class="mb-3">
              <label for="clave" class="form-label">Contraseña</label>
              <input type="password" class="form-control" id="clave" name="clave" required pattern="[A-Za-z0-9\.\-]+" title="Solo letras, numeros,.-">
            </div>
            <div class="mb-3">
              <label for="confirmarClave" class="form-label">Confirmar Contraseña</label>
              <input type="password" class="form-control" id="confirmarClave" name="confirmarClave" required pattern="[A-Za-z0-9\.\-]+" title="Solo letras, numeros,.-">
            </div>
            <div class="d-grid">
              <button type="submit" class="btn btn-primary" name="btnCrearCuenta">Crear Cuenta</button>
            </div> 
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

 <!-- Modal Editar al Cliente-->
<div class="modal fade" id="ModalEditar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Editar Cliente</h5> 
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="container-fluid">
          <form id="formEditarCliente" action="ServletEditarCliente" method="post">
    <input type="hidden" id="idCliente" name="idCliente">         
            <div class="mb-3">
              <label for="dniEditar" class="form-label">DNI</label>
              <input type="text" class="form-control" id="dniEditar" maxlength="8" name="dniEditar" readonly pattern="\d{8}" title="Solo numeros">
            </div>
            <div class="mb-3">
              <label for="cuilEditar" class="form-label">CUIL</label>
              <input type="text" class="form-control" id="cuilEditar" maxlength="13" name="cuilEditar"  pattern="\d{2}-\d{8}-\d{1}" title="Formato: ##-########-#" required>
            </div>
            <div class="mb-3">
              <label for="nombreEditar" class="form-label">Nombres</label>
              <input type="text" class="form-control" id="nombreEditar" name="nombreEditar" pattern="[A-Za-z\s]+" title="Solo letras" required>
            </div>
             <div class="mb-3">
              <label for="apellidoEditar" class="form-label">Apellidos</label>
              <input type="text" class="form-control" id="apellidoEditar" name="apellidoEditar" pattern="[A-Za-z\s]+" title="Solo letras" required>
            </div>
            <div class="mb-3">
              <label for="generoEditar" class="form-label">Género</label>
              <select class="form-select" id="generoEditar" name="generoEditar" required >
                <option value="">Seleccionar</option>
                <option value="masculino">Masculino</option>
                <option value="femenino">Femenino</option>
                <option value="otro">Otro</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="nacionalidadEditar" class="form-label">Nacionalidad</label>
               <select class="form-select" id="nacionalidadEditar" name="nacionalidadEditar" required>
			        <option value="">Seleccionar</option>
			        <%if (listaNacionalidades != null && !listaNacionalidades.isEmpty()) {
			                for (Nacionalidades nacionalidad : listaNacionalidades) { %>
			                    <option value="<%= nacionalidad.getId() %>"><%= nacionalidad.getNombre() %></option>
			        <% }} else { %>
			            <option value="">No hay Nacionalidades disponibles</option>
			        <% } %>
			    </select>
            </div>
            <div class="mb-3">
              <label for="fechaNacimientoEditar" class="form-label">Fecha de Nacimiento</label>
              <input type="date" class="form-control" id="fechaNacimientoEditar" name="fechaNacimientoEditar" required>
            </div>
             <div class="mb-3">
              <label for="direccionEditar" class="form-label">Dirección</label>
              <input type="text" class="form-control" id="direccionEditar" name="direccionEditar" required pattern="[A-Za-z0-9áéíóúÁÉÍÓÚñÑ\s\.]+" title="Solo letras y números">
            </div>
          <div class="mb-3">
    <label for="localidadEditar" class="form-label">Localidad</label>
    <select class="form-select" id="localidadEditar" name="localidadEditar" required onchange="actualizarProvincia()">
        <option value="">Seleccionar</option>
        <% 
        if (listaLocalidades != null && !listaLocalidades.isEmpty()) {
            for (Localidades localidad : listaLocalidades) { 
        %>
            <option value="<%= localidad.getId() %>" data-provincia="<%= localidad.getIdProvincia() %>">
                <%= localidad.getNombre() %>
            </option>
        <% 
            }
        } else { 
        %>
            <option value="">No hay Localidades disponibles</option>
        <% 
        } 
        %>
   		 </select>
			</div>
            <div class="mb-3">
              <label for="provinciaEditar" class="form-label">Provincia</label>
              <select class="form-select" id="provinciaEditar" name="provinciaEditar" required>
			    <option value="">Seleccionar</option>
			    <% if (listaProvincias != null && !listaProvincias.isEmpty()) {
			            for (Provincias provincia : listaProvincias) {%>		            
			                <option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>              
			    <% }} else { %>
			        <option value="">No hay provincias disponibles</option>
			    <%}%>
			</select>
            </div>
            <div class="mb-3">
              <label for="correoEditar" class="form-label">Correo Electrónico</label>
              <input type="email" class="form-control" id="correoEditar" name="correoEditar" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Ingrese un E-mail válido">
            </div>
            <div class="mb-3">
              <label for="telefonoEditar" class="form-label">Teléfono</label>
              <input type="text" class="form-control" id="telefonoEditar" name="telefonoEditar" required pattern="\d+" title="Solo numeros">
            </div>
             <div class="mb-3">
              <label for="usuarioEditar" class="form-label">Nombre de Usuario</label>
              <input type="text" class="form-control" id="usuarioEditar" name="usuarioEditar" readonly >
            </div>
            <div class="mb-3">
              <label for="claveEditar" class="form-label">Contraseña</label>
              <input type="text" class="form-control" id="claveEditar" name="claveEditar" required pattern="[A-Za-z0-9\.\-]+" title="Solo letras, numeros,.-">
            </div>  
            <div class="mb-3">
              <label for="confirmarClaveEditar" class="form-label">Confirmar Contraseña</label>
              <input type="text" class="form-control" id="confirmarClaveEditar" name="confirmarClaveEditar" required pattern="[A-Za-z0-9\.\-]+" title="Solo letras, numeros,.-">
            </div> 
            <div class="mb-3">
              <label for="estadoEditar" class="form-label">Estado</label>
              <input type="text" class="form-control" id="estadoEditar" name="estadoEditar" readonly>
            </div>       
            <div class="d-grid">
              <button type="submit" class="btn btn-primary" name="btnGuardarCambios">Guardar Cambios</button>
            </div>     
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
 

<!-- Modal Eliminar al Cliente-->
 <div class="modal fade" id="ModalEliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalEliminar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Eliminar</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="ServletMostrarClientes" method="get">
		      <div class="modal-body">
		      
			      	<input type="text" class="form-control" id="idUsuarioDelete" name="idUsuarioDelete" hidden>
			       	<p>Seguro desea eliminar usuario</p>
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

<!-- Modal Activar al Cliente-->
<div class="modal fade" id="ModalActivar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalModalActivar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Activar</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="ServletMostrarClientes" method="get">
		      <div class="modal-body">
		      	<input type="text" class="form-control" id="idUsuarioActivar" name="idUsuarioActivar" hidden>
			       	<p>Seguro desea Activar usuario</p>
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

<!-- Mensaje de confirmacion agregar/eliminar-->
<%
    int filas = -1;
    if (request.getAttribute("cantFilas") != null) {
        filas = (int) request.getAttribute("cantFilas");
    }
%>

<div class="position-fixed top-50 start-50 translate-middle" style="z-index: 1050;">
    <% if (filas != -1) { %> 
        <% if (filas > 0) { %>
            <div class="mensaje-exito alert alert-success card-alert alert-dismissible fade show text-center" role="alert">
                <i class="bi bi-check-circle"></i>
                Operación realizada con éxito
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else if (filas == -2) { %>
            <div class="mensaje-error alert alert-warning card-alert alert-dismissible fade show text-center" role="alert">
                 <i class="bi bi-exclamation-triangle"></i>
                El cliente está inactivo y no puede ser editado
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else if (filas == -3) { %>
            <div class="mensaje-error alert card-alert alert-danger alert-dismissible fade show text-center" role="alert">
                 <i class="bi bi-exclamation-triangle"></i>
                <%= request.getAttribute("errorMessage") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else { %>
            <div class="mensaje-error alert alert-danger card-alert alert-dismissible fade show text-center" role="alert">
               <i class="bi bi-exclamation-triangle"></i>
                No se pudo completar la operación
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
        var modalRegistrar = new bootstrap.Modal(document.getElementById("modalRegistrar"));
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

function abrirModalEditar(id, dni, cuil, nombre, apellido, genero, nacionalidadNombre, fechaNacimiento, direccion, localidadNombre, provinciaNombre, correo, telefono, usuario, clave,estado) {   
	document.getElementById("idCliente").value = id;
    document.getElementById("dniEditar").value = dni;
    document.getElementById("cuilEditar").value = cuil;
    document.getElementById("nombreEditar").value = nombre;
    document.getElementById("apellidoEditar").value = apellido;
    document.getElementById("generoEditar").value = genero.toLowerCase();
    document.getElementById("nacionalidadEditar").value = nacionalidadNombre; 
    document.getElementById("fechaNacimientoEditar").value = fechaNacimiento;
    document.getElementById("direccionEditar").value = direccion;
    document.getElementById("localidadEditar").value = localidadNombre;
    document.getElementById("provinciaEditar").value = provinciaNombre; 
    document.getElementById("correoEditar").value = correo;
    document.getElementById("telefonoEditar").value = telefono;
    document.getElementById("usuarioEditar").value = usuario;
    document.getElementById("claveEditar").value = clave;
    document.getElementById("confirmarClaveEditar").value = clave;
    document.getElementById("estadoEditar").value = estado;
}

function abrirModalEliminar(id) {
	console.log("ID para eliminar:", id);
    document.getElementById("idUsuarioDelete").value = id;
}

function abrirModalActivar(id) {
    document.getElementById("idUsuarioActivar").value = id;
}

function actualizarProvincia() {
	const localidadSeleccionada = event.target;//Se obtiene la seleccion de localidad del despegable de Registrar o Editar
	const idProvinciaAsociada = localidadSeleccionada.id ===  'localidad' ? 'provincia' : 'provinciaEditar'; //Usa el Id de acuerdo a la localidad elegida
	const provinciaSeleccionada = document.getElementById(idProvinciaAsociada);
    // Obtengo el ID de la provincia asociada a la eleccion
    const idProvincia = localidadSeleccionada.options[localidadSeleccionada.selectedIndex].getAttribute('data-provincia');
    // De acuerdo al ID busco la provincia que corresponde
    for (var indice = 0; indice < provinciaSeleccionada.options.length; indice++) {
        if (provinciaSeleccionada.options[indice].value === idProvincia) {
            provinciaSeleccionada.selectedIndex = indice;
            break;
        }
    }
}
</script>

</body>
</html>