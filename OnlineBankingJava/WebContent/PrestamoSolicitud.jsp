<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.cliente" %>
<%@ page import="entidades.Cuenta" %>
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
    width: 400px;
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

<div class="parteIzq border border-dark border-3 rounded-end">
    <div class="mt-5 ps-3">
        <ul class="nav flex-column">
            <li class="nav-item"><a class="nav-link active fs-2 text-light" aria-current="page" href="servletInicioUser">Inicio</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="Transferencias.jsp">Transferencias</a></li>
             <li class="nav-item"><a class="nav-link fs-2 text-light" href="SolicitarCuenta.jsp">Solicitar Cuenta</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="Prestamos.jsp">Préstamos</a></li>
            <li class="nav-item"><a class="nav-link fs-2 text-light" href="servletMiPerfil">Mi Perfil</a></li>
        </ul>
    </div>
</div>

<div class="parteDer border border-dark border-3 rounded-end">
    <div class="postbody">
        <div class="card">
            <div class="card-header">
                Solicitar Préstamo
            </div>
            <div class="card-body">
                <form action="ServletAgregarPrestamo" onsubmit="return confirmarAccion()" method="post">
                    <div class="form-group">
                        <label for="Monto">¿Cuánto dinero necesitas?</label>
                        <input type="text" class="form-control mb-3" id="Monto" required name="txtmonto" placeholder="Ingrese el monto"  oninput="validarMonto(this)">
                    </div>
                    <div class="form-group">
                        <label for="Cuotas">¿En cuántas cuotas lo quieres devolver?</label>
                    	<select name="txtcuotas" class="form-control mb-3" id=""txtcuenta"" aria-label="Cuotas" required>
                    		<option value="" disabled selected>-Seleccione cantidad de cuotas-</option>
                    		<option value="3">3</option>
                    		<option value="6">6</option>
                    		<option value="9">9</option>
                    		<option value="12">12</option>
                    		<option value="24">24</option>
                    	</select>
                    </div>
                    <div class="form-group">
                         <label for="Cuenta">¿Desde que cuenta envias el dinero?</label>
						 <select name="txtcuenta" class="form-control" id=""txtcuenta"" aria-label="Tu cuenta" required>
			               	
			               	<%
					    	 ArrayList<String> listaCuentasCliente = (ArrayList<String>) request.getAttribute("listaCuentasCliente");
                       		 if (listaCuentasCliente != null && !listaCuentasCliente.isEmpty()) {
                       			%>
                       			<option value="" disabled selected>-Seleccione una cuenta-</option>
                       			<%
       					        for (String cuenta: listaCuentasCliente) {
		                            %>
					                 <option value="<%= cuenta %>"><%= cuenta %></option>
					                 <%
       					        }
                       		 }
                       		 else{
                       		 %>
                       		 	<option value="" disabled selected>No se encontraron cuentas asociadas a su usuario</option>
                       		 <%
                       		 }
			                 %>
			                
			             </select>
                    </div>
                    <button type="submit" name="btnSolicitar" class="btn btn-primary fw-bold fs-5 mt-3">Realizar solicitud</button>
                </form>
                <div class="text-center mt-3">
                    <a class="btn btn-info text-white fw-bold fs-5" href="Prestamos.jsp">Regresar</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    function confirmarAccion() {
        var respuesta = confirm("¿Desea proceder?");
        return respuesta;  
    }
</script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
	
<script>
function validarMonto(input) {
    let value = input.value.replace(/[^0-9,.]/g, '');

    let commaCount = (value.match(/,/g) || []).length;
    let dotCount = (value.match(/\./g) || []).length;

    if (commaCount > 1) {
        value = value.replace(/,/g, '');
    }
    if (dotCount > 1) {
        value = value.replace(/\./g, '');
    }

    value = value.replace('.', ',');
    input.value = value;
}
</script>


</html>