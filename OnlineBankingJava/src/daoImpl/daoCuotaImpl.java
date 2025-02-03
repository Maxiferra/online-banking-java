package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Cuota;
import entidades.Prestamo;

public class daoCuotaImpl {

	public daoCuotaImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Cuota> leerTodos(int idPrestamo) {	// Recupera una lista con todos los Prestamos.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}

		ArrayList<Cuota> lista = new ArrayList<Cuota>();
		Connection connection = null;
	    conexion cn = new conexion();
	    connection = cn.obtenerConexion();
		try {
			String query = "SELECT * FROM Cuotas WHERE IDPrestamo=?";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setInt(1, idPrestamo);
	        ResultSet resultado = pst.executeQuery();

			while (resultado.next()) {
				Cuota cuota=new Cuota();
				cuota.setIdPrestamo(resultado.getInt("IDPrestamo"));
				cuota.setNumeroCuota(resultado.getInt("NumeroCuota"));
				cuota.setMonto(resultado.getDouble("Monto"));
				cuota.setFechaPago(resultado.getDate("FechaPago"));
				lista.add(cuota);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return lista;
	}
	
	
	
	public boolean agregarCuota(int prestamoId, double monto, int cuotaNumero,Date hoy) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        
        
		try
		{
			String query = "INSERT INTO Cuotas (IDPrestamo, NumeroCuota, Monto, FechaPago) VALUES (?, ?, ?, ?);";
			PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, prestamoId);
            ps.setInt(2, cuotaNumero);
            ps.setDouble(3, monto);
            ps.setDate(4, hoy);
            ps.executeUpdate();     
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
        int idCuenta=-1;
        
        
		try
		{
			PreparedStatement miSentencia = connection.prepareStatement("SELECT IDCuenta FROM Prestamo where ID=?;");
			miSentencia.setInt(1, prestamoId);
	        ResultSet resultado = miSentencia.executeQuery();
	        if (resultado.next()) {
	        	idCuenta = resultado.getInt("IDCuenta");
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		//grabo movimiento
		try
		{
			String query = "INSERT INTO Movimiento(fecha, detalle, idTipoMovimiento, importe, tipoMovimiento, IdCuenta) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);;
            ps.setDate(1, hoy);
            ps.setString(2, "Pago de cuota"+cuotaNumero+" prestamo "+prestamoId);
            ps.setInt(3, 3);
            ps.setDouble(4, monto*-1);
            ps.setString(5, "Egreso");
            ps.setInt(6, idCuenta);

            ps.executeUpdate();     
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		//Resta Saldo
		try	{
				String query = "UPDATE Cuenta SET Saldo = Saldo - ? WHERE ID = ?";
				PreparedStatement ps = connection.prepareStatement(query);;
				ps.setDouble(1, monto);
				ps.setInt(2, idCuenta);

				ps.executeUpdate();     
			}
				catch(Exception e)
				{
					e.printStackTrace();
					return false;
				}
		return true;
	}
}