package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.daoMovimiento;
import entidades.Movimiento;
import entidades.TipoMovimiento;

public class daoMovimientoImpl implements daoMovimiento {
	
	
	public ArrayList<Movimiento> obtenerMovimientos(String numeroCuenta) {
		 ArrayList<Movimiento> lista = new ArrayList<>();
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
		        
		        String query = "SELECT " +
		                       "    m.fecha, " +
		                       "    m.detalle, " +
		                       "    m.importe, " +
		                       "    tm.descripcion AS tipo_descripcion " +
		                       "FROM " +
		                       "    Movimiento m " +
		                       "JOIN " +
		                       "    TipoMovimiento tm ON m.idTipoMovimiento = tm.ID " +
		                       "JOIN " +
		                       "    Cuenta c ON m.IdCuenta = c.ID " +
		                       "WHERE " +
		                       "    c.numeroCuenta = ?" +
		                       "ORDER BY " +
		                       "    m.fecha DESC,"+
		                       "    m.ID DESC";

		       
		        PreparedStatement statement = connection.prepareStatement(query);
		        statement.setString(1, numeroCuenta);  

		        ResultSet resultSet = statement.executeQuery();

		        while (resultSet.next()) {
		            Date fecha = resultSet.getDate("fecha");
		            String detalle = resultSet.getString("detalle");
		            BigDecimal importe = resultSet.getBigDecimal("importe");

		            
		            Movimiento movimiento = new Movimiento(fecha, detalle, importe);

		           
		            String tipoDescripcion = resultSet.getString("tipo_descripcion");
		            TipoMovimiento tipoMovimiento = new TipoMovimiento(0, tipoDescripcion);  

		           
		            movimiento.setTipoMovimiento(tipoMovimiento);

		            
		            lista.add(movimiento);
		        }

		        resultSet.close();
		        statement.close();
		        connection.close();

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return lista;
}
	
	  public ArrayList<Movimiento> FiltroRangoPorMonto(String numeroCuenta, BigDecimal montoMinimo, BigDecimal montoMaximo) {
		  ArrayList<Movimiento> lista = new ArrayList<>();

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
		        
		        String query = "SELECT m.fecha, m.detalle, m.importe, tm.ID AS tipoMovimiento, tm.descripcion AS DescripcionMovimiento "
		                       + "FROM Movimiento m "
		                       + "INNER JOIN Cuenta c ON m.IdCuenta = c.ID "
		                       + "INNER JOIN TipoMovimiento tm ON m.idTipoMovimiento = tm.ID "
		                       + "WHERE c.numeroCuenta = ? AND m.importe BETWEEN ? AND ?";

		        PreparedStatement stmt = connection.prepareStatement(query);
		        stmt.setString(1, numeroCuenta); 
		        stmt.setBigDecimal(2, montoMinimo); 
		        stmt.setBigDecimal(3, montoMaximo); 
		        
		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            Movimiento movimiento = new Movimiento();
		            movimiento.setFecha(rs.getDate("fecha"));
		            movimiento.setDetalle(rs.getString("detalle"));                
		            movimiento.setImporte(rs.getBigDecimal("importe"));

		            
		            int tipoMovimientoId = rs.getInt("tipoMovimiento");
		            String descripcion = rs.getString("DescripcionMovimiento");
		            TipoMovimiento tipoMovimiento = new TipoMovimiento(tipoMovimientoId, descripcion);
		            movimiento.setTipoMovimiento(tipoMovimiento);               
		            
		            lista.add(movimiento);
		        }

		        rs.close();
		        stmt.close();

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

		    return lista;
	}
	
}
