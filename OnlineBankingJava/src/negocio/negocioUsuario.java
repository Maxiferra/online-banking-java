package negocio;

import entidades.Usuario;

public interface negocioUsuario {
	 int altaUsuario(String nombreUsuario, String password, int idTipoUsuario);
	 int obtenerIdUsuario(String nombreUsuario);
	 Usuario validarUsuario(String dni, String username, String password);
	 boolean eliminarUsuario(int idUsuario);
	 boolean editarClave(int idUsuario, String nuevaClave);
	 boolean validarNombreUsuario(String nombreUsuario);
}
