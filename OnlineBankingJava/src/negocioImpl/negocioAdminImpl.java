package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import daoImpl.daoAdminImpl;
import entidades.Admin;

public class negocioAdminImpl {
	//AGREGAR CUENTA
		
		public int agregar(Admin admin) {
			daoAdminImpl dao= new daoAdminImpl();
			int filas=0;
			filas= dao.agregar(admin);
			
			return filas;
		}

		public int eliminarxAdmin (int IdAdmin){
		
			daoAdminImpl dao= new daoAdminImpl();
			int filas=0;
			filas= dao.eliminarxAdmin(IdAdmin);
			
			return filas;
			}

		public int ActivarxAdmin (int IdAdmin){
			
			daoAdminImpl dao= new daoAdminImpl();
			int filas=0;
			filas= dao.ActivarxAdmin(IdAdmin);
			
			return filas;
			}
		
		public boolean eliminar(Admin admin) {
			return false;
		}

		//LISTAR TODOS
		public ArrayList<Admin> leerTodos() {	
			daoAdminImpl dao= new daoAdminImpl();
			return dao.leerTodos();
		}

		//EDITAR CUENTA

		public int editarAdmin(Admin admin) {
			daoAdminImpl dao= new daoAdminImpl();
			return dao.editarAdmin(admin);
		}
}
