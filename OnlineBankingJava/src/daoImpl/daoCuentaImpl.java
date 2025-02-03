package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import dao.daoCuenta;
import entidades.Cuenta;
import entidades.TipoCuenta;
import daoImpl.conexion;

public class daoCuentaImpl implements daoCuenta {


	
	@Override
	 public int agregar(Cuenta cuenta){

		 Connection connection = null;
		    PreparedStatement psCuenta = null;
		    PreparedStatement psMovimiento = null;
		    int resultado = 0;

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conexion cn = new conexion();
		        connection = cn.obtenerConexion();
		        connection.setAutoCommit(false); 
		       
		        String queryCuenta = "INSERT INTO cuenta (DNICliente, FechaCreacion, idTipoCuenta, numeroCuenta, CBU, Saldo, eliminado) VALUES (?,?,?,?,?,?,?)";
		        psCuenta = connection.prepareStatement(queryCuenta, Statement.RETURN_GENERATED_KEYS);

		        java.sql.Date fecha = new java.sql.Date(cuenta.getFechaCreacion().getTime());
		        psCuenta.setString(1, cuenta.getDNICliente());
		        psCuenta.setDate(2, fecha);
		        psCuenta.setInt(3, cuenta.getTipoCuenta());
		        psCuenta.setString(4, cuenta.getNumeroCuenta());
		        psCuenta.setString(5, cuenta.getCbu());
		        psCuenta.setDouble(6, cuenta.getSaldo());
		        psCuenta.setBoolean(7, cuenta.getEliminado());

		        int filasCuenta = psCuenta.executeUpdate();

		        if (filasCuenta > 0) {
		            // Obtiene el ID generado para la cuenta el get es cuando hay autoincrementador
		            ResultSet rs = psCuenta.getGeneratedKeys();
		            int idCuenta = 0;
		            if (rs.next()) {
		                idCuenta = rs.getInt(1);
		            }
		            rs.close();

		            
		            String queryMovimiento = "INSERT INTO movimiento (fecha, detalle, idTipoMovimiento, importe, tipoMovimiento, IdCuenta) VALUES (?,?,?,?,?,?)";
		            psMovimiento = connection.prepareStatement(queryMovimiento);

		            psMovimiento.setDate(1, fecha);
		            psMovimiento.setString(2, "Alta inicial de cuenta");
		            psMovimiento.setInt(3, 1); 
		            psMovimiento.setDouble(4, 10000.00);
		            psMovimiento.setString(5, "Ingreso");
		            psMovimiento.setInt(6, idCuenta);

		            int filasMovimiento = psMovimiento.executeUpdate();

		            if (filasMovimiento > 0) {
		                resultado = 1;
		            } else {
		                throw new SQLException("Error al insertar el movimiento inicial.");
		            }
		        } else {
		            throw new SQLException("Error al insertar la cuenta.");
		        }

		        connection.commit(); // salio bien la transaccion
		    } catch (Exception e) {
		        System.out.println("Error al insertar la cuenta y el movimiento inicial: " + e.getMessage());
		        e.printStackTrace();
		        if (connection != null) {
		            try {
		                connection.rollback(); // vuelve atras la trans
		            } catch (SQLException rollbackEx) {
		                rollbackEx.printStackTrace();
		            }
		        }
		    } finally {
		        try {
		            if (psCuenta != null) psCuenta.close();
		            if (psMovimiento != null) psMovimiento.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return resultado;

    }
	
	
	
    @Override
    public boolean eliminar(Cuenta cuenta) {
        return false;
    }

    //ELIMINAR UNA CUENTA POR ID
    public int eliminarCuenta(int id){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        
        int filas = 0;
    
        try {
            Statement st = connection.createStatement();
            String query = "UPDATE cuenta set eliminado=TRUE WHERE ID=" + id;
                        
            filas = st.executeUpdate(query);
        } catch(Exception e) {
            e.printStackTrace();
        }finally {
           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        return filas;
    }
    
  //ACTIVAR UNA CUENTA POR NÚMERO ID

	@Override
	public int activarCuenta(int id) {
	
	     try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        
	        Connection connection = null;
	        conexion cn = new conexion();
	        connection = cn.obtenerConexion();
	        
	        int filas = 0;
	    
	        try {
	            Statement st = connection.createStatement();
	            String query = "UPDATE cuenta set eliminado=FALSE WHERE ID=" + id;
	            filas = st.executeUpdate(query);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }finally {
	            
	            try {
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        
		
		
		
		return filas;
	}
	
    
    
    
    //EDITAR CUENTA
    public int editarCuenta(Cuenta cuenta) {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
      
        int filas = 0;
    
        try {
        	Statement st = connection.createStatement();
      		
            String query = "UPDATE cuenta SET " + 
			 "FechaCreacion='" + cuenta.getFechaCreacion()+ "', " +
			 "saldo='" + cuenta.getSaldo()+ "' " + 
			 " Where ID=" + cuenta.getId();
           
            filas=st.executeUpdate(query);
           
            
       
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return filas;
    }

    
    
    
    //LISTAR TODOS
    @Override
    public ArrayList<Cuenta> leerTodos() {
    	ArrayList<Cuenta> lista = new ArrayList<>();       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return lista; 
        }

        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();

        if (connection == null) {
            System.out.println("Error al obtener la conexión a la base de datos.");
            return lista; 
        }

        try {   
            String query = "SELECT C.ID, C.DNICliente, C.FechaCreacion, C.idTipoCuenta, C.numeroCuenta, "
            		+ "C.CBU, C.Saldo, C.eliminado, TC.ID AS IDTIPO, TC.descripcion AS DESCRIPCION "
            		+ "FROM cuenta as C LEFT JOIN TipoCuenta as TC ON C.idTipoCuenta = TC.ID ORDER BY C.DNICliente";

            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                
                Cuenta cuenta = new Cuenta();
               
                cuenta.setId(rs.getInt("ID"));
                cuenta.setDNICliente(rs.getString("DNICliente"));
                cuenta.setFechaCreacion((Date)rs.getDate("FechaCreacion"));
                cuenta.setTipoCuenta(rs.getInt("idTipoCuenta"));
                cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
                cuenta.setCbu(rs.getString("CBU"));
                cuenta.setSaldo(rs.getDouble("Saldo"));
                cuenta.setEliminado(rs.getBoolean("eliminado"));
                
                TipoCuenta TC= new TipoCuenta();
                TC.setId(rs.getInt("IDTIPO"));
                TC.setDescripcion(rs.getString("descripcion"));
                cuenta.setCuenta(TC);
                     
                
                lista.add(cuenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lista;
    }
    
    //LISTAR TODOS
    @Override
    public ArrayList<Cuenta> obtenerCuentasCliente(String dni) {
    	ArrayList<Cuenta> lista = new ArrayList<>();       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return lista; 
        }

        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();

        if (connection == null) {
            System.out.println("Error al obtener la conexión a la base de datos.");
            return lista; 
        }

        try {   
            String query = "SELECT C.ID, C.DNICliente, C.FechaCreacion, C.idTipoCuenta, C.numeroCuenta, C.CBU, C.Saldo, C.eliminado FROM cuenta as C WHERE C.DNICliente=?;";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, dni); // Establecer el número de cuenta en la consulta
            ResultSet rs = st.executeQuery();
          
            while (rs.next()) {
                
                Cuenta cuenta = new Cuenta();
               
                cuenta.setId(rs.getInt("ID"));
                cuenta.setDNICliente(rs.getString("DNICliente"));
                cuenta.setFechaCreacion((Date)rs.getDate("FechaCreacion"));
                cuenta.setTipoCuenta(rs.getInt("idTipoCuenta"));
                cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
                cuenta.setCbu(rs.getString("CBU"));
                cuenta.setSaldo(rs.getDouble("Saldo"));
                cuenta.setEliminado(rs.getBoolean("eliminado"));
                
                lista.add(cuenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lista;
    }
    
    public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
        Cuenta cuenta = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();

        try {
            String query = "SELECT * FROM cuenta WHERE numeroCuenta = ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, numeroCuenta); 
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID"));
                cuenta.setDNICliente(rs.getString("DNICliente"));
                cuenta.setFechaCreacion(rs.getDate("FechaCreacion"));
                cuenta.setTipoCuenta(rs.getInt("idTipoCuenta"));
                cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
                cuenta.setCbu(rs.getString("cbu"));
                cuenta.setSaldo(rs.getDouble("Saldo"));
                cuenta.setEliminado(rs.getBoolean("eliminado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cuenta;
    }
    
    
    
//CANTIDAD DE CUENTAS POR DNI    
    
    
	@Override
	public int cantidadCuentas(String dni) {
		
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int CantidadCuentas = 0;
	
		ResultSet resultSet;
		conexion cn = new conexion();
		Connection connection = cn.obtenerConexion();
		
		try 
		{
			Statement st = connection.createStatement();
			String query = "SELECT count(numeroCuenta) FROM cuenta WHERE eliminado=0 AND DNICliente=" + dni;
			resultSet = st.executeQuery(query);
			
			while(resultSet.next())
			{
				CantidadCuentas = resultSet.getInt(1);
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
		return CantidadCuentas;

			}
	
	
	
	//OBTENER DNI CLIENTE SEGUN CUENTA   
    
    
		@Override
		public String ObtenerDNI(int id) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			String DNI="";
		
			ResultSet resultSet;
			conexion cn = new conexion();
			Connection connection = cn.obtenerConexion();
			
			try 
			{
				Statement st = connection.createStatement();
				String query = "SELECT DNICliente FROM cuenta WHERE ID =" + id;
				resultSet = st.executeQuery(query);
				
				while(resultSet.next())
				{
					DNI = resultSet.getString("DNICliente");
					  System.out.println(DNI);
				}
			} 
			
			catch (SQLException e) 
			{
				e.printStackTrace();
			}finally {
	           
	            try {
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        
			return DNI;

				}
		
	
	
	
	// CONFIRMAR SI EXISTE DNI COMO CLIENTE
	@Override
	public int existeDNI(String dni) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int Cantidad= 0;
		
		ResultSet resultSet;
		conexion cn = new conexion();
		Connection connection = cn.obtenerConexion();
		
		try 
		{
			Statement st = connection.createStatement();
			String query ="SELECT count(DNI) FROM cliente WHERE DNI=" + dni;
			resultSet = st.executeQuery(query);
			
			while(resultSet.next())
			{
				Cantidad= resultSet.getInt(1);
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      

		return Cantidad;
	}

	//CBU MAXIMO

	@Override
	public String obtenerCBUMax() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String CBUMax ="";
		PreparedStatement statement;
		ResultSet resultSet;
		conexion cn = new conexion();
		Connection connection = cn.obtenerConexion();
		
		try 
		{
			statement = connection.prepareStatement("SELECT MAX(CBU) FROM cuenta");
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				CBUMax = resultSet.getString(1);
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		return CBUMax;


	}

//NUMERO DE CUENTA MAXIMAS

	@Override
	public String obtenerCuentaMax() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String CUENTAMax = "";
		PreparedStatement statement;
		ResultSet resultSet;
		conexion cn = new conexion();
		Connection connection = cn.obtenerConexion();
		
		try 
		{
			statement = connection.prepareStatement("SELECT MAX(numeroCuenta) FROM cuenta");
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				CUENTAMax = resultSet.getString(1);
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		return CUENTAMax;	
		

	}
	public ArrayList<Cuenta> obtenerCuentasPorUsuario(String dniCliente) {
	    ArrayList<Cuenta> lista = new ArrayList<>();
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return lista; 
	    }

	    Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();

	    if (connection == null) {
	        System.out.println("Error al obtener la conexión a la base de datos.");
	        return lista; 
	    }

	    try {   
	        
	        String query = "SELECT C.ID, C.DNICliente, C.FechaCreacion, C.idTipoCuenta, C.numeroCuenta, "
	        				+ "C.CBU, C.Saldo, C.eliminado, TC.ID AS IDTIPO, TC.descripcion AS DESCRIPCION "
	        				+ "FROM cuenta as C LEFT JOIN TipoCuenta as TC ON C.idTipoCuenta = TC.ID WHERE C.DNICliente = ? AND C.eliminado=false ORDER BY C.DNICliente";
	        PreparedStatement st = connection.prepareStatement(query);
	        st.setString(1, dniCliente); 

	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();
	            cuenta.setId(rs.getInt("ID"));
	            cuenta.setDNICliente(rs.getString("DNICliente"));
	            cuenta.setFechaCreacion(rs.getDate("FechaCreacion"));
	            cuenta.setTipoCuenta(rs.getInt("idTipoCuenta"));
	            cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
	            cuenta.setCbu(rs.getString("CBU"));
	            cuenta.setSaldo(rs.getDouble("Saldo"));
	            cuenta.setEliminado(rs.getBoolean("eliminado"));
	            
	            TipoCuenta TC = new TipoCuenta();
	            TC.setId(rs.getInt("IDTIPO"));
	            TC.setDescripcion(rs.getString("descripcion"));
	            cuenta.setCuenta(TC);
	            
	            
	            lista.add(cuenta);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return lista;
	}



	public Cuenta obtenerCuentaID(Integer idCuenta) {
		Cuenta cuenta = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();

        try {
            String query = "SELECT * FROM cuenta WHERE ID = ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, idCuenta); 
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID"));
                cuenta.setDNICliente(rs.getString("DNICliente"));
                cuenta.setFechaCreacion(rs.getDate("FechaCreacion"));
                cuenta.setTipoCuenta(rs.getInt("idTipoCuenta"));
                cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
                cuenta.setCbu(rs.getString("cbu"));
                cuenta.setSaldo(rs.getDouble("Saldo"));
                cuenta.setEliminado(rs.getBoolean("eliminado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cuenta;
	}

	
	
	
}
		
	

	