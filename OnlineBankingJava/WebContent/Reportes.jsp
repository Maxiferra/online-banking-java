<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar prestamo</title>
<style type="text/css">

<jsp:include page="css\StyleSheet.css"></jsp:include>

</style>

<!--Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<style>
.postbody {
    display: flex;
    height: 800px;
    justify-content: center;
    align-items: center;
}

.card {
    width: 800px;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border: 5px solid #0077b6;
    padding: 30px;
}

.card-header {
    font-family: Roboto, sans-serif;
    font-size: 24px;
    font-weight: bold;
    color: #000000;
    background-color: transparent;
    text-align: center;
    width: 100%;
    padding: 10px;
}

.card-body {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
    width: 100%;
}
.btn-primary {
    background-color: #007bff;
    color: #ffffff;
    font-weight: bold;
    font-size: 18px;
    width: 100%;
    padding: 12px;
    border-radius: 5px;
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
			<li class="nav-item"><a class="nav-link active fs-4 text-light"
					aria-current="page" href="ServletMostrarCuenta">Gestionar Cuentas</a></li>
			<li class="nav-item"><a class="nav-link fs-4 text-light"
					aria-current="page" href="ServletMostrarClientes">Gestionar Clientes</a></li>
			<li class="nav-item"><a class="nav-link fs-4 text-light"
					href="servletAgregarAdmin?Param=list">Gestionar Administradores</a></li>
			<li class="nav-item"><a class="nav-link fs-4 text-light"
					href="ServletMostrarPrestamos">Gestionar Préstamos</a></li>
			<li class="nav-item"><a class="nav-link fs-4 text-light"
					href="ServletMostrarCuenta">Reportes</a></li>
		</ul>
	</div>
</div>

<div class="parteDer-ADM border border-dark border-3 rounded-end">
    <div class="postbody">
        <div class="card">
            <div class="card-header">
                Generar Reporte
            </div>
            <div class="card-body">
			<form action="ServletReportes" method="post">
			    <div>
			        <label class="fs-5">Seleccione el tipo de reporte o metrica que desee consultar:</label>
					<select class="form-select mb-2" id="tipoReporte" name="tipoReporte" onchange="toggleFechas()">
        			    <option value="0">Seleccione un reporte</option>
			            <option value="1">Total de usuarios registrados en la plataforma</option>
						<option value="2">Total de clientes activos</option>
						<option value="3">Cantidad de usuarios que pidieron un prestamo</option>
						<option value="4">Cantidad de usuarios que pidieron prestamos (entre fechas)</option>
						<option value="5">Total de dinero disponible en banco</option>
						<option value="6">Total de dinero solicitado en prestamos</option>
						<option value="7">Total de dinero solicitado en prestamos (entre fechas)</option>
						<option value="8">Cantidad de usuarios segregados por pais</option>
						<option value="9">Tipo de movimiento mas frecuente</option>
						<option value="10">Distribucion de saldos caja corriente vs caja de ahorro</option>
						<option value="11">Promedio de Prestamos</option>
			        </select>
			    </div>
			    <div id="fechasDiv" name="fechasDiv" style="display: none">
				    <div>
				        <label for="fechaInicio" class="fs-6">Seleccione una fecha de inicio:</label>
				        <input class="mt-2" type="date" id="fechaInicio" name="fechaInicio" min="2000-01-01" max="2024-12-31">
				    </div>
				    <div>
				        <label for="fechaFin" class="fs-6">Seleccione una fecha de fin:</label>
				        <input class="mt-2" type="date" id="fechaFin" name="fechaFin" min="2000-01-01" max="2024-12-31">
				    </div>
			    </div>
			    
			<button type="submit" class="btn btn-primary mt-4 fw-bold fs-5">Generar reporte</button>
			</form>
			</div>
			    <h1 class="text-center mt-3">
			        <% String reported = (String) request.getAttribute("reported");
			        	if (reported != null) {
			            	out.print(reported);
			        	} %>
			    </h1>
                <div class="text-center">
                    <a class="btn btn-info text-white fw-bold fs-5" href="InicioAdmin.jsp">Regresar</a>
                </div>
            </div>
        </div>
    </div>

</body>
<script>
    function toggleFechas() {
        var tipoReporte = document.getElementById("tipoReporte").value;
        var fechasDiv = document.getElementById("fechasDiv");
        
        // Show the fechaInicio div only if a specific option is selected
        if (tipoReporte == "4" || tipoReporte == "7") {
            fechasDiv.style.display = "block"; // Show div
            document.getElementById("fechaInicio").setAttribute("required", "required");
            document.getElementById("fechaFin").setAttribute("required", "required");
        } else {
            fechasDiv.style.display = "none"; // Hide div
            document.getElementById("fechaInicio").removeAttribute("required", "required");
            document.getElementById("fechaFin").removeAttribute("required", "required")
        }
    }
</script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>


</html>