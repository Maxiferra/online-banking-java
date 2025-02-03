<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="daoImpl.daoAdminImpl"%>
<%@page import="entidades.Admin"%>
<!DOCTYPE html >
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion Administradores</title>

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
<%
	ArrayList<Admin> listaAdmins = new ArrayList<Admin>();
	if(request.getAttribute("listaAdmins")!= null)
	{
		listaAdmins =(ArrayList<Admin>)request.getAttribute("listaAdmins");
	}%>
<div class="encabezado-user">
	<div class="bg-light text-dark">		
	
			<jsp:include page="Nav.jsp" />
	 </div>
</div>

<div class="parteIzq-ADM border border-dark border-3 rounded-end">
	<div class="mt-5 ps-3">
		<ul class="nav flex-column">
			<li class="nav-item">
				<a class="nav-link active fs-4 text-light"	aria-current="page" href="ServletMostrarCuenta">
					Gestionar Cuentas
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fs-4 text-light" aria-current="page" href="ServletMostrarClientes">
					Gestionar Clientes
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link fs-4 text-light"	href="servletAgregarAdmin?Param=list">
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

<div class="parteDer-ADM border border-dark border-3 rounded-end">
	<div class="container-fluid">
		<div class="row justify-content-between">
			<div class="card-admin2 p-2">	
				<div class="d-flex justify-content-between">
					<div>
				 		<h4 class="text-start" >Gestionar Administrador</h4>
				 	</div>
				 	 <div>
				 	 	<button type="button" class="btn btn-primary fw-bold fs-6" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Crear Usuario</button>
				 	</div>
				 </div>			 
			</div>		 
		</div>	 	
		<div class="row pt-5">
			<div class="card-encabezado">	
				       <%String msjErrorRegistrar = (String) request.getAttribute("errorMessage");
	        if (msjErrorRegistrar != null) {
		    %>
		    <div class="alert alert-danger alert-dismissible fade show" role="alert">
		        <%= msjErrorRegistrar %>
		        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		    </div>
		    <% 
		        }
		    %>
      				
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
					<% if(listaAdmins!=null){
						for(Admin item : listaAdmins)
						{ 
					%>
						<tr>
							<td><%=item.getDni()%></td>
							<td><%=item.getCuil()%></td>
							<td><%=item.getNombre()%></td>
							<td><%=item.getApellido()%></td>
							<td><%=item.getUsuario() != null ? item.getUsuario().getTxtUsuario() : "Sin usuario" %></td>
							<td>
								<button type="button" onclick="abrirModalEditar('<%= item.getIdUsuario() %>','<%= item.getDni() %>', '<%= item.getCuil() %>', '<%= item.getNombre() %>', '<%= item.getApellido() %>','<%= item.getEmail() %>', '<%= item.getTelefono() %>', '<%= item.getUsuario().getTxtUsuario() %>', '<%= item.getUsuario().getPassword()%>')" name="btnEditar" value="Editar" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ModalEditar">Editar</button>
								
								<%if(item.getEstado() == 1){%> 
									<button type="button" onclick="abrirModalEliminar('<%= item.getIdUsuario() %>')" name="btnEliminar" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#ModalEliminar">Inactivar</button>
								<%}else{%>
									<button type="button"  onclick="abrirModalActivar('<%= item.getIdUsuario() %>')" name="btnEliminar" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ModalActivar">Activar</button>
								<% }%>
							</td>		
						</tr>
					 
						<% }}%>
					 </tbody>
				</table>
			</div>			
		</div>					
	</div>
</div>

<!-- Modal AGREGAR -->

<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Datos Administrador</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
     <div class="container-fluid">
          <form action="servletAgregarAdmin" id="formulario" method="post">
            <div class="mb-3">
              <label for="dni" class="form-label">DNI</label>
              <input type="text" id="dni" name="dni" pattern="\d{7,8}"  maxlength="8" placeholder="40998765" oninput="validarSoloNumeros(this)" required>
            </div>
            <div class="mb-3">
              <label for="cuil" class="form-label">CUIL</label>
              <input type="text" class="form-control" id="cuil" maxlength="13" placeholder="XX-XXXXXXXX-X" name="cuil" oninput="formatearCUIL(this)" required>
            </div>
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombres</label>
              <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Peter" required>
            </div>
            <div class="mb-3">
              <label for="apellido" class="form-label">Apellidos</label>
              <input type="text" class="form-control" id="apellido" placeholder="Parker" name="apellido" required>
            </div> 
            <div class="mb-3">
              <label for="correo" class="form-label">Correo Electrónico</label>
              <input type="email" class="form-control" id="correo" name="correo" placeholder="" required>
            </div>
            <div class="mb-3">
              <label for="telefono" class="form-label">Teléfono</label>
              <input type="number" class="form-control" id="telefono" name="telefono" oninput="validarSoloNumeros(this)" required>
            </div>
            <div class="mb-3">
              <label for="usuario" class="form-label">Nombre de Usuario</label>
              <input type="text" class="form-control" id="usuario" name="usuario" required>
            </div>
            <div class="mb-3">
              <label for="clave" class="form-label">Contraseña</label>
              <input type="password" class="form-control" id="clave" name="clave" required>
            </div>
            <div class="mb-3">
              <label for="confirmarClave" class="form-label">Confirmar Contraseña</label>
              <input type="password" class="form-control" id="confirmarClave" name="confirmarClave" required>
             <small id="errorPass" style="color: red; display: none;">Las contraseñas no coinciden</small>
            </div>
            <div class="d-grid">
              <button type="submit" class="btn btn-primary" id="btnCrearAdmin" name="btnCrearAdmin"  disabled>Aceptar</button>
            </div>
          </form>
		</div> 
	  </div> 
    </div>
  </div>
</div>
 
 <!-- Modal EDITAR -->
 
 <div class="modal fade" id="ModalEditar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Datos Administrador</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
     	<div class="container-fluid">
	          <form action="servletAgregarAdmin" method="post">
	            <input type="text" class="form-control" id="idUsuario" name="idUsuario" hidden>
	            <div class="mb-3">
	              <label for="dni" class="form-label">DNI</label>
	              <input type="number" class="form-control" id="txtdni" maxlength="8" name="dni" disabled>
	            </div>
	            <div class="mb-3">
	              <label for="cuil" class="form-label">CUIL</label>
	              <input type="text" class="form-control" id="txtcuil" maxlength="13" name="cuil" disabled>
	            </div>
	            <div class="mb-3">
	              <label for="nombre" class="form-label">Nombres</label>
	              <input type="text" class="form-control" id="txtnombre" name="nombre" required>
	            </div>
	            <div class="mb-3">
	              <label for="apellido" class="form-label">Apellidos</label>
	              <input type="text" class="form-control" id="txtapellido" name="apellido" required>
	            </div> 
	            <div class="mb-3">
	              <label for="correo" class="form-label">Correo Electrónico</label>
	              <input type="email" class="form-control" id="txtcorreo" name="correo" required>
	            </div>
	            <div class="mb-3">
	              <label for="telefono" class="form-label">Teléfono</label>
	              <input type="number" class="form-control" id="txttelefono" name="telefono" oninput="validarSoloNumeros(this)" required>
	            </div>
	            <div class="mb-3">
	              <label for="usuario" class="form-label">Nombre de Usuario</label>
	              <input type="text" class="form-control" id="txtusuario" name="usuario" required disabled>
	            </div>
	            <div class="mb-3">
	              <label for="clave" class="form-label">Contraseña</label>
	              <input type="Text" class="form-control" id="claveEditar" name="claveEditar" required>
	            </div>
	            <div class="d-grid">
	              <button type="submit" class="btn btn-primary" name="btnEditarCuenta">Aceptar</button>
	            </div>
	          </form>
  			</div> 
		</div> 
    </div>
  </div>
</div>

 <!-- Modal ELIMINAR -->
 
<div class="modal fade" id="ModalEliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalEliminar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Elimnar</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="servletAgregarAdmin" method="get">
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

 <!-- Modal ACTIVAR -->

<div class="modal fade" id="ModalActivar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalModalActivar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Activar</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="servletAgregarAdmin" method="get">
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
            <div class="mensaje-exito alert alert-success alert-dismissible fade show text-center" role="alert">
                Operación realizada con éxito
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else if (filas == -2) { %>
            <div class="mensaje-error alert alert-warning alert-dismissible fade show text-center" role="alert">
                El cliente está inactivo y no puede ser editado
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else { %>
            <div class="mensaje-error alert alert-danger alert-dismissible fade show text-center" role="alert">
                No se pudo completar la operación
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } %>
    <% } %>
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
        }
    });
});

function abrirModalEditar(id, dni, cuil, nombre, apellido, correo, telefono, usuario,pass) {
    document.getElementById("idUsuario").value = id;
    document.getElementById("txtdni").value = dni;
    document.getElementById("txtcuil").value = cuil;
    document.getElementById("txtnombre").value = nombre;
    document.getElementById("txtapellido").value = apellido;
    document.getElementById("txtcorreo").value = correo;
    document.getElementById("txttelefono").value = telefono;
    document.getElementById("txtusuario").value = usuario;
    document.getElementById("claveEditar").value = pass
  }
  
function abrirModalEliminar(id) {
    document.getElementById("idUsuarioDelete").value = id;
}

function abrirModalActivar(id) {
    document.getElementById("idUsuarioActivar").value = id;
}

function formatearCUIL(input) {
    // Elimina cualquier carácter que no sea número
    let cuil = input.value.replace(/\D/g, '');

    // Formatea automáticamente según la estructura XX-XXXXXXXX-X
    if (cuil.length > 2) {
      cuil = cuil.slice(0, 2) + '-' + cuil.slice(2);
    }
    if (cuil.length > 11) {
      cuil = cuil.slice(0, 11) + '-' + cuil.slice(11);
    }

    // Limita el largo a 13 caracteres (incluidos los guiones)
    input.value = cuil.slice(0, 13);
  }

function validarSoloNumeros(input) {
    // Elimina cualquier carácter que no sea un número
    input.value = input.value.replace(/\D/g, '');
  }
  
const formulario = document.getElementById('formulario');
const password = document.getElementById('clave');
const confirmPassword = document.getElementById('confirmarClave');
const errorMensaje = document.getElementById('errorMensaje');
const submitBtn = document.getElementById('btnCrearAdmin');
const errorPass = document.getElementById('errorPass');

function validarContrasenas() {
  if (password.value === confirmPassword.value && password.value !== '') {
    submitBtn.disabled = false; // Activa el botón
    errorPass.style.display = 'none'; // Oculta el mensaje de error
  } else {
    submitBtn.disabled = true; // Desactiva el botón
    errorPass.style.display = password.value && confirmPassword.value ? 'block' : 'none'; // Muestra el mensaje si hay algo escrito
  }
}

// Validación en tiempo real
	password.addEventListener('input', validarContrasenas);
	confirmPassword.addEventListener('input', validarContrasenas);
</script>
</body>
</html>