<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

</head>
		<body style="background-color: darkslategrey; color: black;">
     <div class="bg-light text-dark">
			
			<div class="position-absolute ms-5 mb-3 top-1 start-2">
   
        <img src="<%= request.getContextPath() %>/Logo/Logo2.jpeg"  
                     alt="Logo" class="rounded-circle me-3" style="width: 90px; height: 90px;">
    
</div>
			<div class="position-absolute mt-3 top-1 end-0 me-4" id="navbarContent">
    		<div class="d-flex align-items-center">
        
      
        
        
        <p class="text-white mb-0 me-2 fw-bold fs-4"> <i class="bi bi-person-circle m-2"></i>
    <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "Estas desconectado" %>
</p>
	
	<%
                    if (session.getAttribute("usuarioLogueado") != null) {
                %>
                   <a class="nav-link btn btn-danger text-white fw-bold border border-white rounded-pill ms-2 mb-1" 
                   href="servletNav"><i class="bi bi-x-circle-fill m-2 text-start"></i>Salir</a>
                <%
                    }
                %>
        
       
    </div>
</div>
	 </div>
</body>
</html>