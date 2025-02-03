package negocio;

import java.util.ArrayList;

import entidades.cliente;

public interface negocioCliente {

	int altaCliente(cliente cliente);
    ArrayList<cliente> leerTodos();
    cliente obtenerClienteId(int id);
    boolean desactivarCliente(int idCliente);
    boolean activarCliente(int idCliente);
    int obtenerIdUsuario(int idCliente);
    boolean actualizar(cliente cliente);
    boolean editarCliente(cliente cliente);
    cliente obtenerMiPerfil(int idUsuario);
    boolean verificarEstadoCliente(int idCliente);
    boolean validarDni(String dni);
    boolean validarCuil(String cuil);
	boolean validarEmail(String email);
	boolean validarCuilEditar(String cuil, int idCliente);
	boolean validarEmailEditar(String email, int idCliente);
	String ObtenerDniCliente(int ID); 
	 
	 
}
