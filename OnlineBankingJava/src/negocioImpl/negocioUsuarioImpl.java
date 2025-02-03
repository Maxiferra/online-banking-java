package negocioImpl;

import dao.daoUsuario;
import daoImpl.daoUsuarioImpl;
import entidades.Usuario;
import excepciones.UsuarioRepetidoException;
import negocio.negocioUsuario;

public class negocioUsuarioImpl implements negocioUsuario {
	
	 private daoUsuario usuarioDAO = new daoUsuarioImpl();  
	 
	 @Override
	    public int altaUsuario(String nombreUsuario, String password, int idTipoUsuario) {
	        return usuarioDAO.altaUsuario(nombreUsuario, password, idTipoUsuario);
	    }

	    @Override
	    public int obtenerIdUsuario(String nombreUsuario) {
	        return usuarioDAO.obtenerIdUsuario(nombreUsuario);
	    }
	    @Override
	    public Usuario validarUsuario(String dni, String username, String password) {
	        
	        return usuarioDAO.obtenerUsuarioLogin(dni, username, password);
	    }
	    @Override
	    public boolean eliminarUsuario(int idUsuario) {
	    	return usuarioDAO.eliminarUsuario(idUsuario);
	    }
	    @Override
	    public boolean editarClave(int idUsuario, String nuevaClave) {
	    	return usuarioDAO.editarClave(idUsuario, nuevaClave);
	    }
	    @Override
	   public boolean validarNombreUsuario(String nombreUsuario) {
	    	return usuarioDAO.validarNombreUsuario(nombreUsuario);
	    }
}
