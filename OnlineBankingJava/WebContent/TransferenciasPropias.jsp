<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.cliente" %>
<%@ page import="entidades.Cuenta" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TransferenciasPropias</title>
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

<jsp:include page="css\StyleSheet.css"></jsp:include>
.card {
    width: 400px;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border: 5px solid #0077b6;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
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
    padding: 15px;
}

.card-body {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 15px;
    width: 100%;
}

.btn-primary {
    background-color: #007bff;
    color: #ffffff;
    font-weight: bold;
    font-size: 18px;
    width: 100%;
    padding: 10px;
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
                TRANSFERENCIAS CUENTAS PROPIAS
            </div>
            <div class="card-body">
                <form action="ServletTransferir" method="post" id="transferenciaForm">
                        <div class="d-flex justify-content-between align-items-center">
                            <label for="contacto" class="me-2">Selecciona la cuenta desde la cual vas a transferir el dinero</label>
                        </div>
						 <select name="txtCuentaOrigen" class="form-control" id="txtCuentaOrigen" aria-label="Tu cuenta" required>
			               	
			               	<%
					    	 ArrayList<String> listaCuentas = (ArrayList<String>) request.getAttribute("listaCuentas");
                       		 if (listaCuentas != null && !listaCuentas.isEmpty()) {
                       			%>
                       			<option value="" disabled selected>-Seleccione una cuenta-</option>
                       			<%
       					        for (String cuenta: listaCuentas) {
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
                        <div class="d-flex justify-content-between align-items-center">
                            <label for="contacto" class="me-2">Selecciona la cuenta a la cual vas a transferirte el dinero</label>
                        </div>
						 <select name="txtCuentaDestino" class="form-control" id="txtCuentaDestino" aria-label="Tu cuenta" required>
			               	<% 
					    	 ArrayList<String> listaCuentasDestino = (ArrayList<String>) request.getAttribute("listaCuentasDestino");
                       		 if (listaCuentasDestino != null && !listaCuentasDestino.isEmpty()) {
                       			%>
                       			<option value="" disabled selected>-Seleccione una cuenta-</option>
                       			<%
       					        for (String cuenta: listaCuentasDestino) {      					        	
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
                    <div class="form-group">
                        <label>Ingrese el importe</label>
                        <input type="text" class="form-control" id="txtmonto" required name="txtmonto" placeholder="Ingrese el importe">
                    </div>
                    	<input type="hidden" id="txttipotransferencia" name="txttipotransferencia" value="0">
                    <div class="form-group text-center mt-3">
                        <button type="submit" class="btn btn-primary fw-bold fs-5 mt-3">Confirmar</button>
                    </div>                 
                </form>
                <div class=class="text-center mt-3">
								<span><a class="btn btn-info text-white fw-bold fs-5" href="Transferencias.jsp">Regresar</a></span>
							</div>
            </div>
        </div>
    </div>
</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>


</body>
</html>