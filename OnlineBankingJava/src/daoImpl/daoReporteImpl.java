package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class daoReporteImpl {

	public daoReporteImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String generarReporte(int reporteSelected) {
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	       
	        e.printStackTrace();
	    }
		
		String reported="";
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
		PreparedStatement miSentencia;
		ResultSet resultado;
		try {
			switch (reporteSelected) {
		    case 1:
		    	miSentencia = connection.prepareStatement("SELECT COUNT(*) AS totalUsuarios FROM Usuario WHERE eliminado=0;");
		    	resultado=miSentencia.executeQuery();
		        if (resultado.next()) {
		        	reported = "Total de usuarios activos: "+resultado.getInt("totalUsuarios");
		        }
		        break;
		    case 2:
		    	miSentencia = connection.prepareStatement("SELECT COUNT(*) AS totalClientes FROM Cliente WHERE eliminado=0;");
		    	resultado=miSentencia.executeQuery();
		    	if (resultado.next()) {
		        	reported = "Total de clientes activos: "+resultado.getInt("totalClientes");
		        }
		        break;
		    case 3:
		    	miSentencia = connection.prepareStatement("SELECT COUNT(DISTINCT p.IDCliente) AS totalUsuariosPrestamo FROM Prestamo p;");
		    	resultado=miSentencia.executeQuery();
		    	if (resultado.next()) {
		        	reported = "Total de personas que solicitaron prestamos: "+resultado.getInt("totalUsuariosPrestamo");
		        }
		        break;
		    case 5:
		    	miSentencia = connection.prepareStatement("SELECT SUM(Saldo) AS totalDineroEnCuentas FROM Cuenta WHERE eliminado = FALSE;");
		    	resultado=miSentencia.executeQuery();
		    	if (resultado.next()) {
		        	reported = "Total de dinero actualmente disponible en el banco: $"+resultado.getDouble("totalDineroEnCuentas");
		        }
		        break;
		    case 6:
		    	miSentencia = connection.prepareStatement("SELECT SUM(importePedido) AS totalImportePrestamos FROM Prestamo;");
		    	resultado=miSentencia.executeQuery();
		    	if (resultado.next()) {
		        	reported = "Total de dinero solicitado en prestamos: $"+resultado.getDouble("totalImportePrestamos");
		        }
		        break;
			case 8:
				miSentencia = connection.prepareStatement("SELECT n.nombre AS pais, COUNT(*) AS cantidadUsuarios FROM Cliente c JOIN Nacionalidades n ON c.idNacionalidad = n.ID GROUP BY n.nombre;");
				resultado=miSentencia.executeQuery();
				reported="Cantidad de usuarios segregados por pais:"+"<br>";
				if (resultado.next()) {
		        	reported += resultado.getString("pais")+": "+resultado.getInt("cantidadUsuarios");
		        	reported+="<br>";
		        }
		        break;
			case 9:
				miSentencia = connection.prepareStatement("SELECT tm.descripcion AS tipoMovimiento, COUNT(*) AS cantidadMovimientos " + 
						"FROM Movimiento m JOIN TipoMovimiento tm ON m.idTipoMovimiento = tm.ID "+
						"GROUP BY tm.descripcion ORDER BY cantidadMovimientos DESC LIMIT 1;");
				resultado=miSentencia.executeQuery();
				if (resultado.next()) {
		        	reported="Tipo de movimiento mas frecuente: "+resultado.getString("tipoMovimiento")+", realizado "+resultado.getInt("cantidadMovimientos")+" veces.";
		        }
			    break;
			case 10:
				miSentencia = connection.prepareStatement("SELECT tc.descripcion AS tipoCuenta, SUM(c.Saldo) AS totalSaldo " + 
						"FROM Cuenta c JOIN TipoCuenta tc ON c.idTipoCuenta = tc.ID GROUP BY tc.descripcion;");
				resultado=miSentencia.executeQuery();
				reported="Tipos de cuenta y saldos totales:<br><br>";
				while(resultado.next()) {
			        reported+="Tipo "+resultado.getString("tipoCuenta")+", saldo total: $"+resultado.getDouble("totalSaldo")+"<br>";
				}
			    break;
			case 11:
				miSentencia = connection.prepareStatement("select ROUND(avg(ImportePorMes),2) 'promedioPestamo',ROUND(avg(PlazoMeses),0) 'promedioCuotas'  from prestamo");
				resultado=miSentencia.executeQuery();
				reported="Promedio de Prestamos:<br><br>";
				while(resultado.next()) {
			        reported+="Promedio de importe : $"+resultado.getDouble("promedioPestamo")+"<br> Promedio de cuotas: "+resultado.getInt("promedioCuotas")+"<br>";
				}
			    break;
		    default:
		    	reported="No se pudo procesar la opcion elegida para el reporte. Intente nuevamente.";
		    	break;
			}
	        connection.close();
	    } catch (Exception e) {
	        System.out.println("Error en la conexión");
	        e.printStackTrace();
	    } finally {
	        
	    }
	    return reported;
	}
	
	public String generarReporteFechas(int reporteSelected, Date fechaInicio, Date fechaFin) { //Devuelve el estado del prestamo mediante su ID.
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	       
	        e.printStackTrace();
	    }
		
		String reported="";
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
		PreparedStatement miSentencia;
		ResultSet resultado;
		try {
			switch (reporteSelected) {
		    case 4:
		    	miSentencia = connection.prepareStatement("SELECT COUNT(DISTINCT p.IDCliente) AS totalUsuariosPrestamo " + 
		    			"FROM Prestamo p WHERE p.fechaAlta BETWEEN ? AND ?;");
		    	miSentencia.setDate(1, fechaInicio);
		    	miSentencia.setDate(2, fechaFin);
		    	resultado=miSentencia.executeQuery();
		        if (resultado.next()) {
		        	reported = "Total de usuarios que solicitaron prestamos durante "+fechaInicio+" y "+fechaFin+": "+resultado.getInt("totalUsuariosPrestamo");
		        }
		        break;
		    case 7:
		    	miSentencia = connection.prepareStatement("SELECT SUM(importePedido) AS totalImportePrestamos " + 
		    			"FROM Prestamo WHERE fechaAlta BETWEEN ? AND ?;");
		    	miSentencia.setDate(1, fechaInicio);
		    	miSentencia.setDate(2, fechaFin);
		    	resultado=miSentencia.executeQuery();
		        if (resultado.next()) {
		        	reported = "Importe total solicitado mediante prestamos durante "+fechaInicio+" y "+fechaFin+": $"+resultado.getDouble("totalImportePrestamos");
		        }
		        break;
		    default:
		    	reported="No se pudo procesar la opcion elegida para el reporte. Intente nuevamente.";
		    	break;
			}
	        connection.close();
	    } catch (Exception e) {
	        System.out.println("Error en la conexión");
	        e.printStackTrace();
	    } finally {
	        
	    }
	    return reported;
	}
}
