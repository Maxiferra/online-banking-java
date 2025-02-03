
$(document).ready(function() {
    $('#dataTable').DataTable({
    	
    	autoWidth: false, 
        columnDefs: [
            { targets: "_all", defaultContent: "" } 
        ]
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

document.addEventListener('DOMContentLoaded', function () {
    const errorCloseButtons = document.querySelectorAll('.alert .btn-close');
    errorCloseButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            const modals = document.querySelectorAll('.modal.show');
            modals.forEach(function (modal) {
                const bootstrapModal = bootstrap.Modal.getInstance(modal);
                if (bootstrapModal) {
                    bootstrapModal.hide();
                }
            });
        });
    });
});

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

//Ejecutar cuando el documento esté cargado
document.addEventListener("DOMContentLoaded", function () {
    // Verificar si se debe mostrar el modal de registro
    if (abrirModal) {
        var modalRegistrar = new bootstrap.Modal(document.getElementById("modalRegistrar"));
        modalRegistrar.show();
    }

    // Verificar si se debe mostrar el modal de error al editar/registrar
    if (abrirModalPorError) {
        var modalEditarPorError = new bootstrap.Modal(document.getElementById("ModalEditar"));
        modalEditarPorError.show();
    }
});