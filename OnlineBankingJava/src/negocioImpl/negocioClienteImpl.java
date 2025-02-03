package negocioImpl;

import java.util.ArrayList;

import daoImpl.daoClienteImpl;
import entidades.cliente;
import negocio.negocioCliente;

public class negocioClienteImpl implements negocioCliente{
	
	private daoClienteImpl daoCliente;

    
    public negocioClienteImpl() {
        daoCliente = new daoClienteImpl();
    }

    @Override
    public int altaCliente(cliente cliente) {
        return daoCliente.altaCliente(cliente);
    }

    @Override
    public ArrayList<cliente> leerTodos() {
        return daoCliente.leerTodos();
    } 
    @Override
    public cliente obtenerClienteId(int idCliente) {
        return daoCliente.obtenerClienteId(idCliente);
    }
    @Override
   public boolean desactivarCliente(int idCliente) {
    	return daoCliente.desactivarCliente(idCliente);
    }
    @Override
    public boolean activarCliente(int idCliente) {
     	return daoCliente.activarCliente(idCliente);
     }
    @Override
    public int obtenerIdUsuario(int idCliente) {
    	return daoCliente.obtenerIdUsuario(idCliente);
    }
    @Override
    public boolean actualizar(cliente cliente) {
    	return daoCliente.actualizar(cliente);
    }
    @Override
   public boolean editarCliente(cliente cliente) {
    	return daoCliente.editarCliente(cliente);
    }
    @Override
    public cliente obtenerMiPerfil(int idUsuario) {
        return daoCliente.obtenerMiPerfil(idUsuario);
    }
    @Override
    public boolean verificarEstadoCliente(int idCliente) {
    	return daoCliente.verificarEstadoCliente(idCliente);
    }  
    @Override
    public boolean validarDni(String dni) {
    	return daoCliente.validarDni(dni);
    }
    @Override
   public boolean validarCuil(String cuil) {
    	return daoCliente.validarCuil(cuil);
    }
    @Override
	public boolean validarEmail(String email) {
    	return daoCliente.validarEmail(email);
    }
    @Override
    public boolean validarCuilEditar(String cuil, int idCliente) {
    	return daoCliente.validarCuilEditar(cuil, idCliente);
    }
    @Override
	public boolean validarEmailEditar(String email, int idCliente) {
    	return daoCliente.validarEmailEditar(email, idCliente);
    }
    
    public String ObtenerDniCliente(int ID) {
    	
    	return daoCliente.ObtenerDniCliente(ID);
    }
    
    
    
}
