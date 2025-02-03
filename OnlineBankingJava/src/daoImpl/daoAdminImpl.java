package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.daoAdmin;
import entidades.Admin;
import entidades.Usuario;

public class daoAdminImpl implements daoAdmin {

	
	@Override
	public int agregar(Admin admin)
	{
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 conexion cn = new conexion();
		     connection = cn.obtenerConexion();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		int filas=0;
	
		try
		{
			
			Statement st = connection.createStatement();
			String query = "INSERT INTO Admin (ID,DNI, CUIL, nombre, apellido, Email, telefono,Estado) VALUES ('" 
					+ admin.getIdUsuario() + "', '"
					+ admin.getDni() + "', '" 
	                + admin.getCuil() + "', '" 
	                + admin.getNombre() + "', '" 
	                + admin.getApellido() + "', '" 	                
	                + admin.getEmail() + "', '" 	                
	                + admin.getTelefono() + "',1)";
					
	                filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(filas >0) {
			
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public ArrayList<Admin> leerTodos(){
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion cn = new conexion();
		    connection = cn.obtenerConexion();	
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			}
			
			ArrayList<Admin> lista = new ArrayList<Admin>();
			
			try {
		
			Statement st = connection.createStatement();				
			String query = "SELECT A.ID, A.DNI, A.CUIL, A.nombre, A.apellido, u.TxtUsuario,u.password,A.Email,A.telefono,A.Estado FROM Admin A JOIN Usuario u ON A.ID = U.ID";
	        ResultSet resultado = st.executeQuery(query);
	        
	        while(resultado.next()) {
	        	Admin admin = new Admin();
	        	admin.setIdUsuario(Integer.parseInt(resultado.getString("ID")));
	        	admin.setDni(resultado.getString("DNI"));
	        	admin.setCuil(resultado.getString("CUIL"));
	            admin.setNombre(resultado.getString("nombre"));
	            admin.setApellido(resultado.getString("apellido"));
	            admin.setTelefono(resultado.getString("telefono"));
	            admin.setEmail(resultado.getString("Email"));
	            admin.setEstado(Integer.parseInt(resultado.getString("Estado")));
	            String txtUsuario = resultado.getString("TxtUsuario");
	            String Pass = resultado.getString("password");
				if (txtUsuario != null) {
					Usuario usuario = new Usuario();
					usuario.setTxtUsuario(txtUsuario);
					usuario.setPassword(Pass);
					admin.setUsuario(usuario);
				}
				
	            lista.add(admin);
	        }
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
	
	public int eliminarxAdmin(int idAdmin){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		int filas=0;
		  Connection connection = null;
	      conexion cn = new conexion();
	      connection = cn.obtenerConexion();
		
		try{
			
				Statement st = connection.createStatement();
				String query = "update Admin set Estado = 0 Where ID = " + idAdmin;
				filas=st.executeUpdate(query);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return filas;			
		}
	
	public int ActivarxAdmin(int idAdmin){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		int filas=0;
		  Connection connection = null;
	      conexion cn = new conexion();
	      connection = cn.obtenerConexion();
		try{
				
				Statement st = connection.createStatement();
				String query = "update Admin set Estado = 1 Where ID = " + idAdmin;
				filas=st.executeUpdate(query);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return filas;			
		}
		
	public int editarAdmin(Admin admin) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		int filas=0;
		  Connection connection = null;
	      conexion cn = new conexion();
	      connection = cn.obtenerConexion();
		
		try
			{
				
				Statement st = connection.createStatement();
				String query = "UPDATE Admin SET "
						+ "nombre = '" + admin.getNombre()+ "'" 
						+ ",apellido = '"+ admin.getApellido()+ "'" 
						+ ",Email = '" + admin.getEmail()+ "'" 
						+ ",telefono =  '" + admin.getTelefono()+ "'" 
						+ " Where ID = " + admin.getIdUsuario();

						filas=st.executeUpdate(query);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if(filas >0) {
				
				return 1;
			}
			else {
				return 0;
			}
		}
	
	@Override
	public boolean validarDni(String dni) {
	    boolean dniValido = true;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        String query = "SELECT COUNT(*) AS cantidad FROM Admin WHERE DNI = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, dni);

	        ResultSet resultado = ps.executeQuery();

	        if (resultado.next()) {
	            int cantidad = resultado.getInt("cantidad");
	            dniValido = (cantidad == 0);
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dniValido;
	}
	@Override
	public boolean validarCuil(String cuil) {
	    boolean cuilValido = true;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        String query = "SELECT COUNT(*) AS cantidad FROM Admin WHERE CUIL = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, cuil);

	        ResultSet resultado = ps.executeQuery();

	        if (resultado.next()) {
	            int cantidad = resultado.getInt("cantidad");
	            cuilValido = (cantidad == 0);
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return cuilValido;
	}
	@Override
	public boolean eliminar(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}
}
