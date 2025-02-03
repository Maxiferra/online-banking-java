package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.daoCliente;
import entidades.Localidades;
import entidades.Nacionalidades;
import entidades.Provincias;
import entidades.Usuario;
import entidades.cliente;

public class daoClienteImpl implements daoCliente {



	@Override
	public int altaCliente(cliente cliente) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		int filas = 0;
		
		  Connection connection = null;
	      conexion cn = new conexion();
	      connection = cn.obtenerConexion();
		
		try {
			
			Statement st = connection.createStatement();
			String query = "INSERT INTO Cliente (DNI, CUIL, nombre, apellido, sexo, idNacionalidad, fechaNacimiento, direccion, idLocalidad, idProvincia, Email, telefono, idUsuario) VALUES ('"
	                + cliente.getDni() + "', '" + cliente.getCuil() + "', '" + cliente.getNombre() + "', '"
	                + cliente.getApellido() + "', '" + cliente.getSexo() + "', " + cliente.getIdNacionalidad() + ", '"
	                + cliente.getFechaNacimiento() + "', '" + cliente.getDireccion() + "', " + cliente.getIdLocalidad()
	                + ", " + cliente.getIdProvincia() + ", '" + cliente.getEmail() + "', '" + cliente.getTelefono() + "', "
	                + cliente.getIdUsuario() + ")";

			filas = st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public ArrayList<cliente> leerTodos() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e1) {
	        e1.printStackTrace();
	    }

	    ArrayList<cliente> lista = new ArrayList<>();
	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
	    
	    try {
	        Statement st = connection.createStatement();
	        String query = "SELECT Cliente.ID, Cliente.DNI, Cliente.CUIL, Cliente.nombre, Cliente.apellido, " +
	                       "Cliente.sexo, Cliente.idNacionalidad, Nacionalidades.nombre AS nombreNacionalidad, " +
	                       "Cliente.fechaNacimiento, Cliente.direccion, Cliente.idLocalidad, Localidades.nombre AS nombreLocalidad, " +
	                       "Cliente.idProvincia, Provincias.nombre AS nombreProvincia, " +
	                       "Cliente.Email, Cliente.telefono, Cliente.eliminado, Usuario.TxtUsuario, Usuario.Password " +
	                       "FROM Cliente " +
	                       "LEFT JOIN Usuario ON Cliente.idUsuario = Usuario.ID " +
	                       "LEFT JOIN Nacionalidades ON Cliente.idNacionalidad = Nacionalidades.ID " +
	                       "LEFT JOIN Localidades ON Cliente.idLocalidad = Localidades.ID " +
	                       "LEFT JOIN Provincias ON Cliente.idProvincia = Provincias.ID";
	        ResultSet resultado = st.executeQuery(query);

	        while (resultado.next()) {
	            cliente cliente = new cliente();
	            cliente.setId(resultado.getInt("ID"));
	            cliente.setDni(resultado.getString("DNI"));
	            cliente.setCuil(resultado.getString("CUIL"));
	            cliente.setNombre(resultado.getString("nombre"));
	            cliente.setApellido(resultado.getString("apellido"));
	            cliente.setSexo(resultado.getString("sexo"));
	            cliente.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
	            cliente.setDireccion(resultado.getString("direccion"));
	            cliente.setEmail(resultado.getString("Email"));
	            cliente.setTelefono(resultado.getString("telefono"));
	            cliente.setEliminado(resultado.getBoolean("eliminado"));

	            String txtUsuario = resultado.getString("TxtUsuario");
	            String password = resultado.getString("Password");
	            if (txtUsuario != null) {
	                Usuario usuario = new Usuario();
	                usuario.setTxtUsuario(txtUsuario);
	                usuario.setPassword(password);
	                cliente.setUsuario(usuario);
	            }

	            Nacionalidades nacionalidad = new Nacionalidades();
	            nacionalidad.setId(resultado.getInt("idNacionalidad"));
	            nacionalidad.setNombre(resultado.getString("nombreNacionalidad"));
	            cliente.setNacionalidad(nacionalidad);

	            Localidades localidad = new Localidades();
	            localidad.setId(resultado.getInt("idLocalidad"));
	            localidad.setNombre(resultado.getString("nombreLocalidad"));
	            cliente.setLocalidad(localidad);

	            Provincias provincia = new Provincias();
	            provincia.setId(resultado.getInt("idProvincia"));
	            provincia.setNombre(resultado.getString("nombreProvincia"));
	            cliente.setProvincia(provincia);

	            lista.add(cliente);
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}

	
	@Override
	public cliente obtenerClienteId(int id) {
	    cliente cliente = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e1) {
	        e1.printStackTrace();
	    }
	    
	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
	    
	    try {
	       
	        Statement st = connection.createStatement();
	        
	        String query = "SELECT Cliente.ID, Cliente.DNI, Cliente.CUIL, Cliente.nombre, Cliente.apellido, " +
                    "Cliente.sexo, Cliente.idNacionalidad, Cliente.fechaNacimiento, " +
                    "Cliente.direccion, Cliente.idLocalidad, Cliente.idProvincia, " +
                    "Cliente.Email, Cliente.telefono, Usuario.TxtUsuario " +
                    "FROM Cliente " +
                    "LEFT JOIN Usuario ON Cliente.idUsuario = Usuario.ID " +
                    "WHERE Cliente.ID = " + id;
	                       
	        ResultSet resultado = st.executeQuery(query);

	        if (resultado.next()) {
	            cliente = new cliente();
	            cliente.setId(resultado.getInt("ID"));
	            cliente.setDni(resultado.getString("DNI"));
	            cliente.setCuil(resultado.getString("CUIL"));
	            cliente.setNombre(resultado.getString("nombre"));
	            cliente.setApellido(resultado.getString("apellido"));
	            cliente.setSexo(resultado.getString("sexo"));
	            cliente.setIdNacionalidad(resultado.getInt("nacionalidad"));
	            cliente.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
	            cliente.setDireccion(resultado.getString("direccion"));
	            cliente.setIdLocalidad(resultado.getInt("localidad"));
	            cliente.setIdProvincia(resultado.getInt("provincia"));
	            cliente.setEmail(resultado.getString("Email"));
	            cliente.setTelefono(resultado.getString("telefono"));	           

	           
	            String txtUsuario = resultado.getString("TxtUsuario");
	            if (txtUsuario != null) {
	                Usuario usuario = new Usuario();
	                usuario.setTxtUsuario(txtUsuario);
	                cliente.setUsuario(usuario);
	            }
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 

	    return cliente;
	}
	
	@Override
	public boolean desactivarCliente(int idCliente) {
		boolean desactivado = false;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        Statement st = connection.createStatement();

	        String queryGetUsuario = "SELECT idUsuario FROM Cliente WHERE ID = " + idCliente;
	        ResultSet resultado = st.executeQuery(queryGetUsuario);
	        int idUsuario = -1;
	        if (resultado.next()) {
	            idUsuario = resultado.getInt("idUsuario");
	        }

	        if (idUsuario != -1) {
	            String queryDesactivarCliente = "UPDATE Cliente SET eliminado = TRUE WHERE ID = " + idCliente;
	            int filasCliente = st.executeUpdate(queryDesactivarCliente);

	            String queryDesactivarUsuario = "UPDATE Usuario SET eliminado = TRUE WHERE ID = " + idUsuario;
	            int filasUsuario = st.executeUpdate(queryDesactivarUsuario);

	            desactivado = (filasCliente > 0 && filasUsuario > 0);
	        }

	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return desactivado;
	}
	
	@Override
	public boolean activarCliente(int idCliente) {
		boolean activado = false;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        Statement st = connection.createStatement();

	        String queryGetUsuario = "SELECT idUsuario FROM Cliente WHERE ID = " + idCliente;
	        ResultSet resultado = st.executeQuery(queryGetUsuario);
	        int idUsuario = -1;
	        if (resultado.next()) {
	            idUsuario = resultado.getInt("idUsuario");
	        }

	        if (idUsuario != -1) {
	            String queryActivarCliente = "UPDATE Cliente SET eliminado = FALSE WHERE ID = " + idCliente;
	            int filasCliente = st.executeUpdate(queryActivarCliente);

	            String queryActivarUsuario = "UPDATE Usuario SET eliminado = FALSE WHERE ID = " + idUsuario;
	            int filasUsuario = st.executeUpdate(queryActivarUsuario);

	            activado = (filasCliente > 0 && filasUsuario > 0);
	        }

	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return activado;
	}

	
	@Override
	public int obtenerIdUsuario(int idCliente) {
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
	       
	        Statement st = connection.createStatement();

	        String query = "SELECT idUsuario FROM Cliente WHERE ID = " + idCliente;
	        ResultSet resultado = st.executeQuery(query);

	        if (resultado.next()) {
	            idUsuario = resultado.getInt("idUsuario");
	        }

	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return idUsuario;
	}
	
	@Override
	public boolean actualizar(cliente cliente) {
	    boolean actualizado = false;

	    try {
	        
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
	    
	    try {
	        
	        Statement st = connection.createStatement();

	      
	        String query = "UPDATE Cliente SET " +
	                       "DNI = '" + cliente.getDni() + "', " +
	                       "CUIL = '" + cliente.getCuil() + "', " +
	                       "nombre = '" + cliente.getNombre() + "', " +
	                       "apellido = '" + cliente.getApellido() + "', " +
	                       "sexo = '" + cliente.getSexo() + "', " +
	                       "idNacionalidad = " + cliente.getIdNacionalidad() + ", " +
	                       "fechaNacimiento = '" + cliente.getFechaNacimiento() + "', " +
	                       "direccion = '" + cliente.getDireccion() + "', " +
	                       "idLocalidad = " + cliente.getIdLocalidad() + ", " +
	                       "idProvincia = " + cliente.getIdProvincia() + ", " +
	                       "Email = '" + cliente.getEmail() + "', " +
	                       "telefono = '" + cliente.getTelefono() + "' " +
	                       "WHERE ID = " + cliente.getId();

	        
	        int filasAfectadas = st.executeUpdate(query);
	        actualizado = (filasAfectadas > 0); 

	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return actualizado;
	}
	
	@Override
	public boolean editarCliente(cliente cliente) {
	    boolean editado = false;

	    try {
	      
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	       
	    	String query = "UPDATE Cliente SET cuil = ?,nombre = ?, apellido = ?, sexo = ?, fechaNacimiento = ?, " +
                    "direccion = ?,idNacionalidad = ?,idLocalidad = ?, idProvincia = ?,  Email = ?, telefono = ? WHERE ID = ?";
		     
	    	PreparedStatement ps = connection.prepareStatement(query);
		     ps.setString(1, cliente.getCuil());
		     ps.setString(2, cliente.getNombre());
		     ps.setString(3, cliente.getApellido());
		     ps.setString(4, cliente.getSexo());
		     ps.setDate(5, cliente.getFechaNacimiento());
		     ps.setString(6, cliente.getDireccion());
		     ps.setInt(7, cliente.getIdNacionalidad());
		     ps.setInt(8, cliente.getIdLocalidad());
		     ps.setInt(9, cliente.getIdProvincia());
		     ps.setString(10, cliente.getEmail());
		     ps.setString(11, cliente.getTelefono());
		     ps.setInt(12, cliente.getId());

	        int filasAfectadas = ps.executeUpdate();
	        editado = (filasAfectadas > 0);

	        connection.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return editado; 
	}
	
	@Override
	public cliente obtenerMiPerfil(int idUsuario) {
		 cliente miCliente = null;
		    Connection connection = null;
		    conexion cn = new conexion();

		    try {
		        connection = cn.obtenerConexion();
		        String query = "SELECT Cliente.ID, Cliente.DNI, Cliente.CUIL, Cliente.nombre, Cliente.apellido, " +
		                       "Cliente.sexo, Cliente.idNacionalidad, Cliente.fechaNacimiento, " +
		                       "Cliente.direccion, Cliente.idLocalidad, Cliente.idProvincia, " +
		                       "Cliente.Email, Cliente.telefono, Cliente.eliminado, Usuario.TxtUsuario, Usuario.Password " +
		                       "FROM Cliente " +
		                       "LEFT JOIN Usuario ON Cliente.idUsuario = Usuario.ID " +
		                       "WHERE Cliente.idUsuario = ?";

		        PreparedStatement ps = connection.prepareStatement(query);
		        ps.setInt(1, idUsuario);

		        ResultSet resultado = ps.executeQuery();

		        if (resultado.next()) {
		            miCliente = new cliente();
		            miCliente.setId(resultado.getInt("ID"));
		            miCliente.setDni(resultado.getString("DNI"));
		            miCliente.setCuil(resultado.getString("CUIL"));
		            miCliente.setNombre(resultado.getString("nombre"));
		            miCliente.setApellido(resultado.getString("apellido"));
		            miCliente.setSexo(resultado.getString("sexo"));
		            miCliente.setIdNacionalidad(resultado.getInt("idNacionalidad"));
		            miCliente.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
		            miCliente.setDireccion(resultado.getString("direccion"));
		            miCliente.setIdLocalidad(resultado.getInt("idLocalidad"));
		            miCliente.setIdProvincia(resultado.getInt("idProvincia"));
		            miCliente.setEmail(resultado.getString("Email"));
		            miCliente.setTelefono(resultado.getString("telefono"));
		            miCliente.setEliminado(resultado.getBoolean("eliminado"));

		            String txtUsuario = resultado.getString("TxtUsuario");
		            String password = resultado.getString("Password");
		            if (txtUsuario != null) {
		                Usuario usuario = new Usuario();
		                usuario.setTxtUsuario(txtUsuario);
		                usuario.setPassword(password);
		                miCliente.setUsuario(usuario);
		            }
		        }
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return miCliente;
	}
	
	@Override
	public boolean verificarEstadoCliente(int idCliente) {
	    boolean estadoActivo = false;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        String query = "SELECT eliminado FROM Cliente WHERE ID = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, idCliente);

	        ResultSet resultado = ps.executeQuery();

	        if (resultado.next()) {
	            estadoActivo = !resultado.getBoolean("eliminado");
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return estadoActivo;
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
	        String query = "SELECT COUNT(*) AS cantidad FROM Cliente WHERE DNI = ?";
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
	        String query = "SELECT COUNT(*) AS cantidad FROM Cliente WHERE CUIL = ?";
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
	public boolean validarEmail(String email) {
	    boolean emailValido = true;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        String query = "SELECT COUNT(*) AS cantidad FROM Cliente WHERE Email = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, email);

	        ResultSet resultado = ps.executeQuery();

	        if (resultado.next()) {
	            int cantidad = resultado.getInt("cantidad");
	            emailValido = (cantidad == 0);
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return emailValido;
	}
	
	
	@Override
	public boolean validarCuilEditar(String cuil, int idCliente) {
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
	        String query = "SELECT COUNT(*) AS cantidad FROM Cliente WHERE CUIL = ? AND ID != ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, cuil);
	        ps.setInt(2, idCliente);

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
	public boolean validarEmailEditar(String email, int idCliente) {
	    boolean emailValido = true;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        String query = "SELECT COUNT(*) AS cantidad FROM Cliente WHERE Email = ? AND ID != ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, email);
	        ps.setInt(2, idCliente);

	        ResultSet resultado = ps.executeQuery();

	        if (resultado.next()) {
	            int cantidad = resultado.getInt("cantidad");
	            emailValido = (cantidad == 0);
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return emailValido;
	}
	
	
	@Override
	public int ObtenerIdCliente(String DNI) {
	    int IdCliente=0;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        String query = "SELECT ID FROM Cliente WHERE DNI = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1,DNI);

	        ResultSet resultado = ps.executeQuery();

	        if (resultado.next()) {
	        	IdCliente = resultado.getInt("ID");
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return IdCliente;
	}
	
	@Override
	public String ObtenerDniCliente(int ID) {
	   String dni="";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    try {
	        String query = "SELECT c.DNI FROM cliente c INNER JOIN usuario u ON c.idUsuario = u.ID WHERE u.ID = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1,ID);

	        ResultSet resultado = ps.executeQuery();

	        if (resultado.next()) {
	        	dni = resultado.getString("DNI");
	    	    System.out.println("Cuenta: dni=" + dni);
	        }
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dni;
	}
	
	
	
	
	
	
}
