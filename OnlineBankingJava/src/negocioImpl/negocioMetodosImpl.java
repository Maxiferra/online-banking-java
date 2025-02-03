package negocioImpl;

import javax.servlet.http.HttpServletRequest;

import dao.daoAdmin;
import dao.daoCliente;
import dao.daoUsuario;
import daoImpl.daoAdminImpl;
import daoImpl.daoClienteImpl;
import daoImpl.daoUsuarioImpl;
import negocio.NegocioMetodos;
import negocio.negocioLocalidades;
import negocio.negocioNacionalidades;
import negocio.negocioProvincias;

public class negocioMetodosImpl implements NegocioMetodos {

	public void cargarListas(HttpServletRequest request) {
		negocioNacionalidades negocioNacionalidades = new negocioNacionalidadesImpl();
		negocioLocalidades negocioLocalidades = new negocioLocalidadesImpl();
		negocioProvincias negocioProvincias = new negocioProvinciasImpl();

		request.setAttribute("listaNacionalidades", negocioNacionalidades.obtenerTodas());
		request.setAttribute("listaLocalidades", negocioLocalidades.obtenerTodas());
		request.setAttribute("listaProvincias", negocioProvincias.obtenerTodas());
	}

	public boolean validarCamposRegistrar(HttpServletRequest request) {

		String dni = request.getParameter("dni");

		daoCliente daoCliente = new daoClienteImpl();
		if (!daoCliente.validarDni(dni)) {
			request.setAttribute("errorMessage", "El DNI del cliente ya esta registrado");
			return false;
		}

		daoAdmin daoAdmin = new daoAdminImpl();
		if (!daoAdmin.validarDni(dni)) {
			request.setAttribute("errorMessage", "El DNI ya lo tiene registrado un administrador");
			return false;
		}

		if (dni == null || dni.isEmpty()) {
			request.setAttribute("errorMessage", "El campo DNI es obligatorio");
			return false;
		}

		String cuil = request.getParameter("cuil");
		if (cuil == null || cuil.isEmpty()) {
			request.setAttribute("errorMessage", "El campo CUIL es obligatorio");
			return false;
		} else if (!daoCliente.validarCuil(cuil)) {
			request.setAttribute("errorMessage", "El CUIL de este cliente ya está registrado");
			return false;
		}

		String nombre = request.getParameter("nombre");
		if (nombre == null || nombre.isEmpty()) {
			request.setAttribute("errorMessage", "El campo Nombre es obligatorio");
			return false;
		}

		String apellido = request.getParameter("apellido");
		if (apellido == null || apellido.isEmpty()) {
			request.setAttribute("errorMessage", "El campo Apellido es obligatorio");
			return false;
		}

		String genero = request.getParameter("genero");
		if (genero == null || genero.isEmpty()) {
			request.setAttribute("errorMessage", "Debe seleccionar un género");
			return false;
		}

		String nacionalidad = request.getParameter("nacionalidad");
		if (nacionalidad == null || nacionalidad.isEmpty()) {
			request.setAttribute("errorMessage", "Debe seleccionar una nacionalidad");
			return false;
		}

		String fechaNacimiento = request.getParameter("fechaNacimiento");
		if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
			request.setAttribute("errorMessage", "Debe ingresar una fecha de nacimiento válida");
			return false;
		} else {			
			java.time.LocalDate fechaNac = java.time.LocalDate.parse(fechaNacimiento);
			java.time.LocalDate hoy = java.time.LocalDate.now();
			java.time.Period edad = java.time.Period.between(fechaNac, hoy);

			if (edad.getYears() < 18) {
				request.setAttribute("errorMessage", "Para poder registrarse debe ser mayor de 18 años");
				return false;
			}
		}
		String direccion = request.getParameter("direccion");
		if (direccion == null || direccion.isEmpty()) {
			request.setAttribute("errorMessage", "El campo Dirección es obligatorio");
			return false;
		}

		String localidad = request.getParameter("localidad");
		if (localidad == null || localidad.isEmpty()) {
			request.setAttribute("errorMessage", "Debe seleccionar una localidad");
			return false;
		}

		String provincia = request.getParameter("provincia");
		if (provincia == null || provincia.isEmpty()) {
			request.setAttribute("errorMessage", "Debe seleccionar una provincia");
			return false;
		}

		String correo = request.getParameter("correo");
		if (correo == null || correo.isEmpty()) {
			request.setAttribute("errorMessage", "El Correo Electrónico es obligatorio");
			return false;
		}
		String email = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		if (!correo.matches(email)) {
		    request.setAttribute("errorMessage", "El E-mail no tiene un formato válido");
		    return false;
		}
		if (!daoCliente.validarEmail(correo)) {
		    request.setAttribute("errorMessage", "El email ya está registrado por otro cliente");
		    return false;
		}

		String telefono = request.getParameter("telefono");
		if (telefono == null || telefono.isEmpty()) {
			request.setAttribute("errorMessage", "El campo Teléfono es obligatorio");
			return false;
		}

		daoUsuario daoUsuario = new daoUsuarioImpl();
		String nombreUsuario = request.getParameter("usuario");
		if (nombreUsuario == null || nombreUsuario.isEmpty()) {
			request.setAttribute("errorMessage", "El Nombre de Usuario es obligatorio");
			return false;
		} else if (!daoUsuario.validarNombreUsuario(nombreUsuario)) {
			request.setAttribute("errorMessage", "El Nombre de Usuario ya está registrado por otro cliente");
			return false;
		}

		String password = request.getParameter("clave");
		if (password == null || password.isEmpty()) {
			request.setAttribute("errorMessage", "Debe ingresar una contraseña");
			return false;
		}

		String confirmarClave = request.getParameter("confirmarClave");
		if (confirmarClave == null || confirmarClave.isEmpty()) {
			request.setAttribute("errorMessage", "Debe confirmar la contraseña");
			return false;
		} else if (!password.equals(confirmarClave)) {
			request.setAttribute("errorMessage", "Las contraseñas no coinciden");
			return false;
		}

		return true;
	}

	public boolean validarCamposRegistrarAdmin(HttpServletRequest request) {

		String dni = request.getParameter("dni");
		String cuil = request.getParameter("cuil");

		daoAdmin daoAdmin = new daoAdminImpl();
		
		String correo = request.getParameter("correo");
		if (correo == null || correo.isEmpty()) {
			request.setAttribute("errorMessage", "El Correo Electrónico es obligatorio");
			return false;
		}
		String email = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		if (!correo.matches(email)) {
		    request.setAttribute("errorMessage", "El E-mail no tiene un formato válido");
		    return false;
		}
		
		if (!daoAdmin.validarDni(dni)) {
			request.setAttribute("errorMessage", "El DNI del administrador ya esta registrado");
			return false;
		}

		if (!daoAdmin.validarCuil(cuil)) {
			request.setAttribute("errorMessage", "El Cuil ya lo tiene registrado un administrador");
			return false;
		}
		return true;
	}

	public boolean validarCamposEditar(HttpServletRequest request) {

		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

		String cuil = request.getParameter("cuilEditar");
		if (cuil == null || cuil.isEmpty()) {
			request.setAttribute("errorMessage", "El CUIL es obligatorio");
			return false;
		}

		daoCliente daoCliente = new daoClienteImpl();
		if (!daoCliente.validarCuilEditar(cuil, idCliente)) {
			request.setAttribute("errorMessage", "El CUIL ya está registrado por otro cliente");
			return false;
		}

		String nombre = request.getParameter("nombreEditar");
		if (nombre == null || nombre.isEmpty()) {
			request.setAttribute("errorMessage", "El Nombre es obligatorio");
			return false;
		}

		String apellido = request.getParameter("apellidoEditar");
		if (apellido == null || apellido.isEmpty()) {
			request.setAttribute("errorMessage", "El Apellido es obligatorio");
			return false;
		}

		String genero = request.getParameter("generoEditar");
		if (genero == null || genero.isEmpty()) {
			request.setAttribute("errorMessage", "Debe seleccionar un género");
			return false;
		}

		String nacionalidad = request.getParameter("nacionalidadEditar");
		if (nacionalidad == null || nacionalidad.isEmpty()) {
			request.setAttribute("errorMessage", "Debe seleccionar una nacionalidad");
			return false;
		}

		String fechaNacimiento = request.getParameter("fechaNacimientoEditar");
		if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
			request.setAttribute("errorMessage", "Debe ingresar una fecha de nacimiento valida");
			return false;
		}

		String direccion = request.getParameter("direccionEditar");
		if (direccion == null || direccion.isEmpty()) {
			request.setAttribute("errorMessage", "El campo Dirección es obligatorio");
			return false;
		} else if (!direccion.matches("[A-Za-z0-9\\s]+")) {
			request.setAttribute("errorMessage", "La Dirección solo puede contener letras y números");
			return false;
		}

		String localidad = request.getParameter("localidadEditar");
		if (localidad == null || localidad.isEmpty()) {
			request.setAttribute("errorMessage", "Debe seleccionar una localidad");
			return false;
		}

		String provincia = request.getParameter("provinciaEditar");
		if (provincia == null || provincia.isEmpty()) {
			request.setAttribute("errorMessage", "Debe seleccionar una provincia");
			return false;
		}

		String correo = request.getParameter("correoEditar");
		if (correo == null || correo.isEmpty()) {
			request.setAttribute("errorMessage", "El Email es obligatorio");
			return false;
		}
		String email = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		if (!correo.matches(email)) {
		    request.setAttribute("errorMessage", "El E-mail no tiene un formato válido");
		    return false;
		}

		if (!daoCliente.validarEmailEditar(correo, idCliente)) {
			request.setAttribute("errorMessage", "El Email ya está registrado por otro cliente");
			return false;
		}

		String telefono = request.getParameter("telefonoEditar");
		if (telefono == null || telefono.isEmpty()) {
			request.setAttribute("errorMessage", "El Telefono es obligatorio");
			return false;
		}

		String clave = request.getParameter("claveEditar");
		if (clave != null && clave.isEmpty()) {
			request.setAttribute("errorMessage", "La Clave no puede estar vacia");
			return false;
		}
		String confirmarClave = request.getParameter("confirmarClaveEditar");
		if (confirmarClave == null || confirmarClave.isEmpty()) {
			request.setAttribute("errorMessage", "Debe confirmar la contraseña");
			return false;
		} else if (!clave.equals(confirmarClave)) {
			request.setAttribute("errorMessage", "Las contraseñas no coinciden");
			return false;
		}

		return true;
	}

	public boolean validarCamposCuentas(HttpServletRequest request) {

	    
				// Validar fecha de creación
				String fechaCreacion = request.getParameter("txtFechaCreacion");
				if (fechaCreacion == null || fechaCreacion.isEmpty()) {
					request.setAttribute("errorMessage", "Debe ingresar una fecha válida");
					return false;
				} else {
					try {
						java.time.LocalDate fechahoy = java.time.LocalDate.parse(fechaCreacion);
						java.time.LocalDate hoy = java.time.LocalDate.now();

						if (fechahoy.isAfter(hoy)) {
							request.setAttribute("errorMessage", "La fecha no puede ser posterior a la fecha actual");
							return false;
						}
					} catch (java.time.format.DateTimeParseException e) {
						request.setAttribute("errorMessage", "El formato de la fecha es incorrecto");
						return false;
					}
				}

				return true;

		}
}
