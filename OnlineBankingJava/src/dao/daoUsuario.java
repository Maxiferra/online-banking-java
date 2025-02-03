package dao;

import java.util.ArrayList;

import entidades.Usuario;

public interface daoUsuario {

	int altaUsuario(String nombreUsuario, String password, int idTipoUsuario);
	int obtenerIdUsuario(String nombreUsuario);
	Usuario obtenerUsuarioLogin(String dni, String username, String password);
	boolean eliminarUsuario(int idUsuario);
	boolean editarClave(int idUsuario, String nuevaClave);
	boolean validarNombreUsuario(String nombreUsuario);
}
