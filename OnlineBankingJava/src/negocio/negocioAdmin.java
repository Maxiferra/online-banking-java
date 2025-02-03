package negocio;

import java.util.List;

import entidades.Admin;

public interface negocioAdmin {
	int eliminarxAdmin (int IdAdmin);
	int ActivarxAdmin (int IdAdmin);
	boolean agregar (Admin admin);
	void eliminar (Admin admin);
	public List<Admin> leerTodos();
	int editarAdmin(Admin admin);
}
