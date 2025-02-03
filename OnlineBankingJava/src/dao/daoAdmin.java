package dao;
import java.util.List;

import entidades.Admin;

public interface daoAdmin {	
	int eliminarxAdmin (int IdAdmin);
	int agregar (Admin admin);
	boolean eliminar (Admin admin);
	public List<Admin> leerTodos();
	int editarAdmin(Admin admin);
	boolean validarDni(String dni);
	boolean validarCuil(String cuil);
}
