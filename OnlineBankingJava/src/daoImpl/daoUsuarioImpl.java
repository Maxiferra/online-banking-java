package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Usuario;
import excepciones.UsuarioRepetidoException;
import dao.daoUsuario;

public class daoUsuarioImpl implements daoUsuario{

	@Override
	public int altaUsuario(String nombreUsuario, String password, int idTipoUsuario) {
		
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
			
		
			String query = "INSERT INTO Usuario (TxtUsuario, Password, idTipoUsuario) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, password);
            ps.setInt(3, idTipoUsuario);
            
            filas = ps.executeUpdate();     
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
		}	
	
	@Override
	public int obtenerIdUsuario(String nombreUsuario){
		int idUsuario = -1;
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	       
	        e.printStackTrace();
	    }
		
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
		try {
		
			PreparedStatement miSentencia = connection.prepareStatement("SELECT ID FROM Usuario WHERE TxtUsuario = ?");
			miSentencia.setString(1, nombreUsuario);
	        ResultSet resultado = miSentencia.executeQuery();
	        
	        if (resultado.next()) {
	            idUsuario = resultado.getInt("ID");
	        }
	        
	        connection.close();
	    } catch (Exception e) {
	        System.out.println("Error en la conexión");
	        e.printStackTrace();
	    } finally {
	        
	    }
	    return idUsuario;
	}
	
	
	@Override
	public Usuario obtenerUsuarioLogin(String dni ,String username, String password) {
	    Usuario usuario = null;
	   
	    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
	    
	    
	        Connection connection = null;
	        conexion cn = new conexion();
	        connection = cn.obtenerConexion();
	        try {
	        	String query = "SELECT u.ID, u.TxtUsuario, u.Password, u.idTipoUsuario, " +
	                    "c.DNI AS ClienteDNI, a.DNI AS AdminDNI " +
	                    "FROM Usuario u " +
	                    "LEFT JOIN Cliente c ON u.ID = c.idUsuario " +
	                    "LEFT JOIN Admin a ON u.ID = a.ID " +
	                    "WHERE u.TxtUsuario = ? AND u.Password = ? AND " +
	                    "(c.DNI = ? OR a.DNI = ?) " +
	                    "AND (c.eliminado = 0 OR c.eliminado IS NULL) " +
	                    "AND (a.Estado = 1 OR a.Estado IS NULL)";
	                   
	        
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, username);
	        ps.setString(2, password);
	        ps.setString(3, dni);
	        ps.setString(4, dni);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            
	            int tipoUsuarioId = rs.getInt("idTipoUsuario");
	            
	          
	            usuario = new Usuario(rs.getInt("ID"), tipoUsuarioId, rs.getString("TxtUsuario"), rs.getString("Password"));
	        }
	    }  
	    catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection!= null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	  
	    
	    return usuario;
	}

	@Override
	public boolean eliminarUsuario(int idUsuario) {
	    boolean eliminado = false;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
	    try {
	      
	        String query = "DELETE FROM Usuario WHERE ID = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, idUsuario);

	        int filasAfectadas = ps.executeUpdate();
	        eliminado = (filasAfectadas > 0);
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return eliminado;
	}
	
	@Override
	public boolean editarClave(int idUsuario, String nuevaClave) {
	    boolean editar = false;

	    try {	        
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	    	String query = "UPDATE Usuario SET Password = ? WHERE ID = ?";

	    	PreparedStatement ps = connection.prepareStatement(query);
	    	ps.setString(1, nuevaClave); // Asignar la nueva clave
	        ps.setInt(2, idUsuario);
	        	        
	        int filasAfectadas = ps.executeUpdate();
	        editar = (filasAfectadas > 0); 

	        connection.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return editar; 
	}
	
	@Override
	public boolean validarNombreUsuario(String nombreUsuario) {
	    boolean nombreValido = true;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        String query = "SELECT COUNT(*) AS cantidad FROM Usuario WHERE TxtUsuario = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, nombreUsuario);

	        ResultSet resultado = ps.executeQuery();

	        if (resultado.next()) {
	            int cantidad = resultado.getInt("cantidad");
	            nombreValido = (cantidad == 0); 
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return nombreValido;
	}
}
