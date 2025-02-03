<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mi perfil</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
	
	
	<style type="text/css">
		<jsp:include page="css\StyleSheet.css"></jsp:include>
	</style>
	
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


<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="form-container mt-0">
        <h2>Mi Perfil</h2>
        
       <form action="servletMiPerfil" method="get">
            <div class="mb-3">
                <label class="form-label" for="nombre">Nombre Completo</label>
                <input type="text" class="form-control" id="nombre" value="${nombre != null ? nombre : 'No disponible'}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label" for="dni">DNI</label>
                <input type="text" class="form-control" id="dni" value="${dni != null ? dni : 'No disponible'}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label" for="email">Correo Electrónico</label>
                <input type="text" class="form-control" id="email" value="${email != null ? email : 'No disponible'}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label" for="telefono">Teléfono</label>
                <input type="text" class="form-control" id ="telefono" value="${telefono != null ? telefono : 'No disponible'}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label" for="nombreUsuario">Nombre de Usuario</label>
                <input type="text" class="form-control" id="nombreUsuario" value="${nombreUsuario != null ? nombreUsuario : 'No disponible'}" readonly>
            </div>
        </form>
        <div class="text-center mt-3">
            <span><a class="btn btn-info text-white fw-bold fs-5" href="servletInicioUser">Regresar</a></span>
        </div>
        <div class="card-footer text-center mt-2">
            <span class="fw-bold fs-5">Estos son tus datos personales.</span>
        </div>
    </div>
</div>

</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>


</body>
</html>