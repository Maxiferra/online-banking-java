<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	   <%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.cliente" %>
<%@ page import="entidades.Usuario" %>
<%@ page import="entidades.Provincias" %>
<%@ page import="entidades.Localidades" %>
<%@ page import="entidades.Nacionalidades" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>


<body
	style="background-color: darkslategrey; color: black">
	<div class="container">
		<div class="image-home"></div>
		
			<a class="position-absolute pt-3 ps-3 top-0 start-0" href="Home.jsp"></a>
			
			<div class="position-absolute pt-3 top-0 end-0" id="navbarContent">
			
			<div class="d-flex align-items-center">
				
   <a href="servletCliente?openModal=true" 
   class="nav-link btn btn-primary text-white border border-white rounded-pill px-4 ms-2">
   Hacete Cliente
</a>
					 <a class="nav-link btn btn-primary text-white border border-white  rounded-pill px-4 ms-2"
					href="Login.jsp">Online Banking</a>
			</div>
		</div>
		<div class="position-absolute top-50 start-50 translate-middle text-home">
		<h1>Bienvenido a tu banco</h1>
		</div>
	</div>


<div class="modal fade" id="modalRegistrarse" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="modalRegistrarseLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Registrarse</h5> 
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="container-fluid">
          <form action="servletCliente" method="post">
            <div class="mb-3">
              <label for="dni" class="form-label">DNI</label>
              <input type="text" class="form-control" id="dni" maxlength="8" name="dni" required pattern="\d{8}" title="El DNI debe ser de 8 números">
            </div>
            <div class="mb-3">
              <label for="cuil" class="form-label">CUIL</label>
              <input type="text" class="form-control" id="cuil" maxlength="13" name="cuil" required pattern="\d{2}-\d{8}-\d{1}" title="Solo números y formato de CUIL (##-########-#)">
            </div>
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombres</label>
              <input type="text" class="form-control" id="nombre" name="nombre" required pattern="[A-Za-z\s]+" title="Solo letras">
            </div>
            <div class="mb-3">
              <label for="apellido" class="form-label">Apellidos</label>
              <input type="text" class="form-control" id="apellido" name="apellido" required pattern="[A-Za-z\s]+" title="Solo letras">
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
              <input type="text" class="form-control" id="direccion" name="direccion" required pattern="[A-Za-z0-9\s]+" title="Solo letras y números">
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
                <% 
                  ArrayList<Provincias> listaProvincias = (ArrayList<Provincias>) request.getAttribute("listaProvincias");
                  if (listaProvincias != null && !listaProvincias.isEmpty()) {
                      for (Provincias provincia : listaProvincias) { 
                %>
                  <option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>
                <% 
                      } 
                  } else { 
                %>
                  <option value="">No hay Provincias disponibles</option>
                <% } %>
              </select>
            </div>
            <div class="mb-3">
              <label for="correo" class="form-label">Correo Electrónico</label>
              <input type="email" class="form-control" id="correo" name="correo" required>
            </div>
            <div class="mb-3">
              <label for="telefono" class="form-label">Teléfono</label>
              <input type="text" class="form-control" id="telefono" name="telefono" required pattern="\d+" title="Solo números">
            </div>
            <div class="mb-3">
              <label for="usuario" class="form-label">Nombre de Usuario</label>
              <input type="text" class="form-control" id="usuario" name="usuario" required pattern="[A-Za-z0-9\.\-]+" title="Solo letras, números, puntos y guiones">
            </div>
            <div class="mb-3">
              <label for="clave" class="form-label">Contraseña</label>
              <input type="password" class="form-control" id="clave" name="clave" required pattern="[A-Za-z0-9\.\-]+" title="Solo letras, números, puntos y guiones">
            </div>
            <div class="mb-3">
              <label for="confirmarClave" class="form-label">Confirmar Contraseña</label>
              <input type="password" class="form-control" id="confirmarClave" name="confirmarClave" required pattern="[A-Za-z0-9\.\-]+" title="Solo letras, números, puntos y guiones">
            </div>
            <div class="d-grid">
              <button type="submit" class="btn btn-primary" name="btnCrearCuenta">Crear Cuenta</button>
            </div>
           <div class="mt-3">
    <% 
        String msjErrorRegistrar = (String) request.getAttribute("errorMessage");
        if (msjErrorRegistrar != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <%= msjErrorRegistrar %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <% 
        }
    %>
</div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>

<!-- Mensaje de confirmacion agregar-->
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
        <% } else if (filas == -3) { %>
            <div class="mensaje-error alert alert-danger alert-dismissible fade show text-center" role="alert">
                <%= request.getAttribute("errorMessage") %>
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

<!-- Script para modal carga-->
<%
    Boolean abrir = (Boolean) request.getAttribute("abrirModal");
    if (abrir != null && abrir) {
%>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var modalRegistrarse = new bootstrap.Modal(document.getElementById("modalRegistrarse"));
        modalRegistrarse.show();
    });
</script>
<%
    }
%>

<!-- Script para modal provincia asociada a localidad-->
<script>
function actualizarProvincia() {
    const localidad = document.getElementById('localidad');
    const provincia = document.getElementById('provincia');
    const provinciaId = localidad.options[localidad.selectedIndex].getAttribute('data-provincia');

    for (var indice = 0; indice < provincia.options.length; indice++) {
        if (provincia.options[indice].value === provinciaId) {
            provincia.selectedIndex = indice;
            break;
    	}
	}
}
</script>

</body>
</html>
