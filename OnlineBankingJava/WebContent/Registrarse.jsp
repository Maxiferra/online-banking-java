<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrarme</title>
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
<a class="position-absolute top-0 start-0" href="Home.jsp">LOGO</a>

    <div class="position-absolute top-0 end-0">
        <a class="btn btn-primary me-2 border border-white rounded-pill px-4" href="Home.jsp">Inicio</a>
    </div>

    <div class="modal fade" id="modalRegistrarse" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="modalRegistrarseLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalRegistrarseLabel">Registrarme</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- Contenedor principal del formulario -->
        <div class="container">
          <div class="online-banking text-center">Registrarse</div>
          <div class="form-container">
            <h2>Registrarme</h2>
            <form action="servletCliente" method="get">
              <!-- Formulario -->
              <div class="mb-3">
                <label for="dni" class="form-label">DNI</label>
                <input type="text" class="form-control" id="dni" maxlength="8" name="dni" required pattern="\d{8}" title="Solo números">
              </div>
              <div class="mb-3">
                <label for="cuil" class="form-label">CUIL</label>
                <input type="text" class="form-control" id="cuil" maxlength="13" name="cuil" required pattern="\d{2}-\d{8}-\d{1}" title="Solo números">
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
                <input type="text" class="form-control" id="nacionalidad" name="nacionalidad" required pattern="[A-Za-z\s]+" title="Solo letras">
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
                <input type="text" class="form-control" id="localidad" name="localidad" required pattern="[A-Za-z\s]+" title="Solo letras">
              </div>
              <div class="mb-3">
                <label for="provincia" class="form-label">Provincia</label>
                <select class="form-select" id="provincia" name="provincia" required>
                  <option value="">Seleccionar</option>
                  <option value="Buenos Aires">Buenos Aires</option>
                  <option value="Córdoba">Córdoba</option>
                  <!-- Agrega todas las provincias necesarias -->
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
                <input type="text" class="form-control" id="usuario" name="usuario" required>
              </div>
              <div class="mb-3">
                <label for="clave" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="clave" name="clave" required>
              </div>
              <div class="mb-3">
                <label for="confirmarClave" class="form-label">Confirmar Contraseña</label>
                <input type="password" class="form-control" id="confirmarClave" name="confirmarClave" required>
              </div>
              <div class="d-grid">
                <button type="submit" class="btn btn-primary" name="btnCrearCuenta">Crear Cuenta</button>
              </div>
              <div class="mt-3 text-center">
                <span>¿Ya tienes una cuenta? <a href="Login.jsp">Iniciar Sesión</a></span>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<%
    int filas = 0;
    if (request.getAttribute("cantFilas") != null) {
        filas = (int) request.getAttribute("cantFilas");
    }
%>

<div class="d-flex justify-content-center mt-3">
    <% if (filas == 1) { %>
        <div class="alert alert-success text-center" role="alert">
            Se agregó correctamente en la base de datos
        </div>
    <% } else if (filas == 0 && request.getAttribute("cantFilas") != null) { %>
        <div class="alert alert-danger text-center" role="alert">
            No se pudo agregar en la base de datos
        </div>
    <% } %>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>
</body>
</html>

