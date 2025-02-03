<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion usuario</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
</head>
</head>
<body>
<div class="container-fluid mt-4">
	<div class="row">
	
		<div class="col-8">
		 	<div class="row mb-4">
		 		<div class="col-10">
		 			<h3 class='text-center'>Gestor Cliente</h3>
		 		</div>
		 	 	<div class="col-2">
		 	 		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Crear Usuario</button>
		 	 	</div>
		 	</div>
		 	<div class="row">
			 	<table class="table text-center" id="dataTable">
				  <thead>
				    <tr>
				      <th scope="col">Usuario</th>
				      <th scope="col">Nombre</th>
				      <th scope="col">Apellido</th>
		      		  <th scope="col">DNI</th>
				      <th scope="col">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">Usuario1</th>
				      <td>Pedro</td>
				      <td>Alvarez</td>
				      <td>123213123</td>
				      <td>
				      	<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ModalDesasinar" >
							Desasinar
						</button>
						<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ModalEliminar"  >
							Eiminar
						</button>
					  </td>
				    </tr>
				  </tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<!-- Modal -->
<div class="modal fade" id="ModalEliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalEliminar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Elimnar</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	       	<p>Seguro desea eliminar usuario</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <button type="button" class="btn btn-danger">Aceptar</button>
	      </div>
	    </div>
	  </div>
	</div>
</div>
<div class="modal fade" id="ModalDesasinar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ModalEliminar" aria-hidden="false">
  <div class="modal-dialog modal-dialog-centered"  >
	<div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Elimnar</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	       	<p>Desea Desasignar Usuario</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <button type="button" class="btn btn-danger">Aceptar</button>
	      </div>
	    </div>
	  </div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Crear Usuario</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
	      <div class="container">
		      <div class="row">
				<div class=" col-12 mb-3">
				 <select class="form-select" name="ddlUsuario">
				 	<option>Cliente 1</option>
				 	<option>Cliente 2</option>
				 	<option>Cliente 3</option>
				 </select>
				</div>

				<div class="mb-3">
				  <input type="text" name='txtUsuario' class="form-control" placeholder="Usuario" aria-label="Usuario" aria-describedby="basic-addon1">
				</div>
				<div class="mb-3">
				  <input type="password" name='txtPass' class="form-control" placeholder="Contrasenia" aria-label="Contrasenia" aria-describedby="basic-addon1">
				</div>
		      </div>
	      </div>
      	</div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <a type="submit" class="btn btn-primary">Guardar</a>
	      </div>
      </div> 
    </div>
  </div>


<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script>
	$(document).ready(function() {
		$('#dataTable').DataTable();
	});
</script>

</body>
</html>