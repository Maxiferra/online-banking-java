package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import entidades.Admin;
import entidades.Prestamo;
import entidades.Usuario;
import dao.daoPrestamo;

public class daoPrestamoImpl implements daoPrestamo{


	@Override
	public boolean altaPrestamo(double monto, int cuotas, String cuenta) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        
        int idCuenta=-1;
        int idCliente=-1;
        
      //Me traigo los ids para insertar la solicitud de prestamo.
        try {
            System.out.println("XXX"+cuenta);
    		PreparedStatement miSentencia = connection.prepareStatement("SELECT Cliente.ID AS idCliente, Cuenta.ID AS idCuenta FROM Cliente INNER JOIN Cuenta ON Cliente.DNI = Cuenta.DNICliente WHERE Cuenta.numeroCuenta=?");
    		miSentencia.setString(1, cuenta);
            ResultSet resultado = miSentencia.executeQuery();
            
            if (resultado.next()) {
            	idCuenta = resultado.getInt("idCuenta");
            	idCliente = resultado.getInt("idCliente");
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		try
		{
			String query = "INSERT INTO Prestamo (idCliente, idCuenta, fechaAlta, importePedido, plazoMeses, importePorMes, CantidadCuotas, estadoPrestamo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);
            Date hoy = new Date(System.currentTimeMillis());
            double importePorMes=(monto/cuotas);
            ps.setInt(1, idCliente);
            ps.setInt(2, idCuenta);
            ps.setDate(3, hoy);
            ps.setDouble(4, monto);
            ps.setInt(5, cuotas);
            ps.setDouble(6, importePorMes);
            ps.setDouble(7, cuotas);
            ps.setInt(8, 2); //Por default el estadoPrestamo va como pendiente

            ps.executeUpdate();     
		}
		 
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		try
		{
			String query = "INSERT INTO Movimiento(fecha, detalle, idTipoMovimiento, importe, tipoMovimiento, IdCuenta) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);
            Date hoy = new Date(System.currentTimeMillis());
            ps.setDate(1, hoy);
            ps.setString(2, "Prestamo Pendiente");
            ps.setInt(3, 2);
            ps.setDouble(4, monto);
            ps.setString(5, "Ingreso");
            ps.setInt(6, idCuenta);

            ps.executeUpdate();     
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		}	
	
	public int obtenerEstadoPrestamo(int id) { //Devuelve el estado del prestamo mediante su ID.
		int estadoPrestamo = -1;
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	       
	        e.printStackTrace();
	    }
		
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
		try {
			
			PreparedStatement miSentencia = connection.prepareStatement("SELECT estadoPrestamo FROM Prestamo WHERE ID = ?");
			miSentencia.setInt(1, id);
	        ResultSet resultado = miSentencia.executeQuery();
	        
	        if (resultado.next()) {
	        	estadoPrestamo = resultado.getInt("estadoPrestamo");
	        }
	        connection.close();
	    } catch (Exception e) {
	        System.out.println("Error en la conexión");
	        e.printStackTrace();
	    } finally {
	        
	    }
	    return estadoPrestamo;
	}
	
	public ArrayList<String> leerClientes() { //Recupera una lista con los nombres de clientes cotejados
		try {									//con los prestamos mediante Prestamos.IDCliente.
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		ArrayList<String> lista = new ArrayList<String>();
		 
		Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
		try {
			
			Statement st = connection.createStatement();
			String query = "SELECT CONCAT(Cliente.nombre, ' ', Cliente.apellido) AS Cliente FROM Prestamo INNER JOIN Cliente ON Cliente.ID = Prestamo.IdCliente;";
			ResultSet resultado = st.executeQuery(query);

			while (resultado.next()) {
				String nombre=resultado.getString("Cliente");
				lista.add(nombre);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return lista;
	}
	
	public ArrayList<String> leerCuentas() { //Recupera una lista con los nombres de cuentas cotejadas
		try {								 //con los prestamos mediante Prestamos.IDCuenta.
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}

		ArrayList<String> lista = new ArrayList<String>();
		Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
		try {
		
			Statement st = connection.createStatement();
			String query = "SELECT Cuenta.numeroCuenta AS Cuenta FROM Prestamo INNER JOIN Cuenta ON Cuenta.ID = Prestamo.IdCuenta;";
			ResultSet resultado = st.executeQuery(query);

			while (resultado.next()) {
				String cuenta=resultado.getString("Cuenta");
				lista.add(cuenta);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return lista;
	}
	
	public boolean cambiarEstadoPrestamo(Prestamo prestamo, int estadoPrestamo) {
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
		Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
		boolean modificado=false;
		
		try {
			
			PreparedStatement miSentencia = connection.prepareStatement("UPDATE Prestamo SET EstadoPrestamo=? WHERE ID=?");
			miSentencia.setInt(1, estadoPrestamo);
			miSentencia.setInt(2, prestamo.getId());
	        int rowsAffected = miSentencia.executeUpdate();
	        if (rowsAffected > 0) {
	        	if (estadoPrestamo==1)
	        	{
	        		rowsAffected=0;
	        		miSentencia = connection.prepareStatement("UPDATE Cuenta SET Saldo=Saldo+? WHERE ID=?");
	        		miSentencia.setDouble(1, prestamo.getImportePedido());
	        		miSentencia.setInt(2, prestamo.getIdCuenta());
	    			rowsAffected = miSentencia.executeUpdate();
	    			System.out.println(rowsAffected);
	    			if(rowsAffected > 0) 
	    			{
		        		rowsAffected=0;
		        		miSentencia = connection.prepareStatement("INSERT INTO Movimiento (fecha, detalle, idTipoMovimiento, importe, tipoMovimiento,IdCuenta)"
		        		+ "VALUES (?, ?, ?, ?, ?,?)");
		        		
		        		java.util.Date utilDate = new java.util.Date();
		        		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		        		miSentencia.setDate(1, (Date) sqlDate);
		        		miSentencia.setString(2, "Prestamo Aprobado");
		        		miSentencia.setInt(3, 2);
		        		miSentencia.setDouble(4, prestamo.getImportePedido());
		        		miSentencia.setString(5, "Ingreso");
		        		miSentencia.setInt(6, prestamo.getIdCuenta());
		    			rowsAffected = miSentencia.executeUpdate();
		    			if(rowsAffected>0) 
		    			{
		    				System.out.println("Se grabo el movimiento correctamente.");
		    			}
		    			else {
		    				System.out.println("Error: No se grabo el movimiento.");
		    			}
	    			}
	    			else {
	    				System.out.println("Error: No se deposito dinero en cuenta.");
	    			}
	        	}
	            System.out.println("Se asigno prestamo satisfactoriamente.");
	            modificado=true;
	        } else {
	            System.out.println("No se encontró el prestamo con ID: " + prestamo.getId());
	        }
	        connection.close();
	    } catch (Exception e) {
	        System.out.println("Error en la conexión");
	        e.printStackTrace();
	    } finally {
	        
	    }
	    return modificado;
	}
	
	
	public ArrayList<Prestamo> leerTodosCliente(int idUsuario){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
		try {
	       
	        String query = "SELECT Prestamo.ID, Prestamo.IDCliente, Prestamo.IDCuenta, Prestamo.fechaAlta, Prestamo.importePedido, Prestamo.plazoMeses,Prestamo.importePorMes,Prestamo.CantidadCuotas,Prestamo.estadoPrestamo FROM Prestamo " 
	        		+"INNER JOIN Cliente ON Cliente.ID=Prestamo.IDCliente "
	        		+"INNER JOIN Usuario ON Usuario.ID=Cliente.idUsuario " + 
	        		"WHERE Usuario.ID=?;";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setInt(1, idUsuario);
	        ResultSet resultado = pst.executeQuery();

			while (resultado.next()) {
				Prestamo prestamo=new Prestamo();
				prestamo.setId(resultado.getInt("ID"));
				prestamo.setIdCliente(resultado.getInt("IDCliente"));
				prestamo.setIdCuenta(resultado.getInt("IDCuenta"));
				prestamo.setFechaAlta(resultado.getDate("fechaAlta"));
				prestamo.setImportePedido(resultado.getDouble("importePedido"));
				prestamo.setPlazoMeses(resultado.getInt("plazoMeses"));
				prestamo.setImportePorMes(resultado.getInt("importePorMes"));
				prestamo.setCantidadCuotas(resultado.getInt("cantidadCuotas"));
				prestamo.setEstadoPrestamo(resultado.getInt("estadoPrestamo"));
				lista.add(prestamo);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return lista;
	}
		

	public ArrayList<Prestamo> leerTodos() {	// Recupera una lista con todos los Prestamos.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}

		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
		try {
			
			Statement st = connection.createStatement();

			String query = "SELECT Prestamo.ID, Prestamo.IDCliente, Prestamo.IDCuenta, Prestamo.fechaAlta, Prestamo.importePedido, Prestamo.plazoMeses,Prestamo.importePorMes,Prestamo.CantidadCuotas,Prestamo.estadoPrestamo "
					+ "FROM Prestamo";
			ResultSet resultado = st.executeQuery(query);

			while (resultado.next()) {
				Prestamo prestamo=new Prestamo();
				prestamo.setId(resultado.getInt("ID"));
				prestamo.setIdCliente(resultado.getInt("IDCliente"));
				prestamo.setIdCuenta(resultado.getInt("IDCuenta"));
				prestamo.setFechaAlta(resultado.getDate("fechaAlta"));
				prestamo.setImportePedido(resultado.getDouble("importePedido"));
				prestamo.setPlazoMeses(resultado.getInt("plazoMeses"));
				prestamo.setImportePorMes(resultado.getInt("importePorMes"));
				prestamo.setCantidadCuotas(resultado.getInt("cantidadCuotas"));
				prestamo.setEstadoPrestamo(resultado.getInt("estadoPrestamo"));
				lista.add(prestamo);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return lista;
	}
	
	public Prestamo getPrestamoById(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		Prestamo prestamo = null;
		Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
	    try {
	       
	        String query = "SELECT * FROM Prestamo WHERE ID=?";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setInt(1, id);
	        ResultSet resultado = pst.executeQuery();
			
			while (resultado.next()) {
				prestamo = new Prestamo();
				prestamo.setId(resultado.getInt("ID"));
				prestamo.setIdCliente(resultado.getInt("IDCliente"));
				prestamo.setIdCuenta(resultado.getInt("IDCuenta"));
				prestamo.setFechaAlta(resultado.getDate("fechaAlta"));
				prestamo.setImportePedido(resultado.getDouble("importePedido"));
				prestamo.setPlazoMeses(resultado.getInt("plazoMeses"));
				prestamo.setImportePorMes(resultado.getInt("importePorMes"));
				prestamo.setCantidadCuotas(resultado.getInt("cantidadCuotas"));
				prestamo.setEstadoPrestamo(resultado.getInt("estadoPrestamo"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return prestamo;
	}
	
	public boolean restarCuota(int prestamoId) {
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
		Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
		boolean modificado=false;
		
		try {
			
			PreparedStatement miSentencia = connection.prepareStatement("UPDATE Prestamo SET CantidadCuotas=(CantidadCuotas-1) WHERE ID=?");
			miSentencia.setInt(1, prestamoId);
	        int rowsAffected = miSentencia.executeUpdate();
	        if (rowsAffected > 0) {
	        	System.out.println("Se resto cuota pendiente de pago en prestamo correspondiente.");
	        } else {
	            System.out.println("No se encontró el prestamo con ID: " + prestamoId);
	        }
	        connection.close();
	    } catch (Exception e) {
	        System.out.println("Error en la conexión");
	        e.printStackTrace();
	    } finally {
	        
	    }
	    return modificado;
	}

	public ArrayList<Integer> leerCuentasId(int id) {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion cn = new conexion();
		    connection = cn.obtenerConexion();	
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			}
			
			ArrayList<Integer> lista = new ArrayList<Integer>();
			
			try {
		        String query = "SELECT Prestamo.CantidadCuotas FROM Prestamo INNER JOIN cuenta c on c.ID = Prestamo.IDCuenta WHERE c.ID = ?";
		        PreparedStatement ps = connection.prepareStatement(query);
		        ps.setInt(1, id);

		        ResultSet resultado = ps.executeQuery();

		        if (resultado.next()) {
		            int cantidad = resultado.getInt("CantidadCuotas");
		            lista.add(cantidad);
		        }
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return lista;
	}
	
}