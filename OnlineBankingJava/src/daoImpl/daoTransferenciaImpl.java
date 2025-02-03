package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class daoTransferenciaImpl {

	public daoTransferenciaImpl() {
		// TODO Auto-generated constructor stub
	}

	public String agregarTransferenciaPropia(String cuentaOrigen, String cuentaDestino, double monto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        
        int idCuenta=-1;
        
        try {
	        String query = "SELECT ID FROM Cuenta WHERE numeroCuenta=?";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setString(1, cuentaOrigen);
	        ResultSet resultado = pst.executeQuery();
	        while (resultado.next()) {
				idCuenta=resultado.getInt("ID");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "No se pudo encontrar el ID de su cuenta.";
		}
        
		System.out.println("Encontro id cuentaorigen"+idCuenta);
        
        try {
	        String query = "SELECT Saldo FROM Cuenta WHERE numeroCuenta=?";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setString(1, cuentaOrigen);
	        ResultSet resultado = pst.executeQuery();
	        double saldoDisponible=0;
	        while (resultado.next()) {
				saldoDisponible=resultado.getDouble("Saldo");
			}
	        System.out.println("Saldo disponible:"+saldoDisponible);
	        if((saldoDisponible-monto)<0) {
	        	return "Saldo insuficiente para realizar la transferencia. Saldo disponible: $"+saldoDisponible;
	        }
	        System.out.println("Saldo suficiente en cuentaorigen"+(saldoDisponible-monto));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Saldo insuficiente en cuenta origen.";
		}

        try {
        	PreparedStatement miSentencia = connection.prepareStatement("UPDATE Cuenta SET Saldo=(Saldo-?) WHERE numeroCuenta=?");
			miSentencia.setDouble(1, monto);
			miSentencia.setString(2, cuentaOrigen);
	        int rowsAffected = miSentencia.executeUpdate();
	        if (rowsAffected!=1) {
	        	return "Hubo un error al intentar realizar la transferencia.";
	        }
			System.out.println("Se resto saldo en cuentaOrigen");
        	miSentencia = connection.prepareStatement("UPDATE Cuenta SET Saldo=(Saldo+?) WHERE numeroCuenta=?");
			miSentencia.setDouble(1, monto);
			miSentencia.setString(2, cuentaDestino);
	        rowsAffected = miSentencia.executeUpdate();
	        if (rowsAffected!=1) {
	        	return "No se pudo enviar el dinero a cuenta destino.";
	        }
	        System.out.println("Se sumo saldo en cuentaDestino");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "No se pudo enviar el dinero a cuenta destino.";
		}
		
		try
		{
			String query = "INSERT INTO Movimiento(fecha, detalle, idTipoMovimiento, importe, tipoMovimiento, IdCuenta) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);
            Date hoy = new Date(System.currentTimeMillis());
            ps.setDate(1, hoy);
            ps.setString(2, "Transferencia a cuenta propia.");
            ps.setInt(3, 4);
            ps.setDouble(4, monto*-1);
            ps.setString(5, "Egreso");
            ps.setInt(6, idCuenta);

            ps.executeUpdate();     
	        System.out.println("Se sumo movimiento de egreso");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Se realizo el egreso de saldo, pero hubo un error al intentar grabar el movimiento. El mismo puede no aparecer registrado.";
		}
		
        try {
	        String query = "SELECT ID FROM Cuenta WHERE numeroCuenta=?";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setString(1, cuentaDestino);
	        ResultSet resultado = pst.executeQuery();
	        while (resultado.next()) {
				idCuenta=resultado.getInt("ID");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "No se pudo verificar ID de cuenta destino.";
		}
		
        System.out.println("Se encontro idDestino"+idCuenta);
		try
		{
			String query = "INSERT INTO Movimiento(fecha, detalle, idTipoMovimiento, importe, tipoMovimiento, IdCuenta) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);
            Date hoy = new Date(System.currentTimeMillis());
            ps.setDate(1, hoy);
            ps.setString(2, "Transferencia a cuenta propia.");
            ps.setInt(3, 4);
            ps.setDouble(4, monto);
            ps.setString(5, "Ingreso");
            ps.setInt(6, idCuenta);

            ps.executeUpdate();     
            System.out.println("Se sumo movimiento de egreso");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Se realizo la transferencia, pero no se pudo grabar movimiento de ingreso en cuenta destino.";
		}
		return "Se realizo transferencia satisfactoriamente";
		}
	
	public String agregarTransferenciaTercero(String cuentaOrigen, String cbuDestino, double monto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        
        int idCuenta=-1;
        
        try {
	        String query = "SELECT ID FROM Cuenta WHERE numeroCuenta=?";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setString(1, cuentaOrigen);
	        ResultSet resultado = pst.executeQuery();
	        while (resultado.next()) {
				idCuenta=resultado.getInt("ID");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "No se pudo encontrar el ID de su cuenta.";
		}
        
        System.out.println("Se encontro idOrigen: "+idCuenta);
        
        
        int idCuentaDestino=0;
        try {
            System.out.println("CBUdestino: "+cbuDestino);
	        String query = "SELECT ID FROM Cuenta WHERE CBU=? LIMIT 1;";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setString(1, cbuDestino);
	        ResultSet resultado = pst.executeQuery();
	        while (resultado.next()) {
		        idCuentaDestino=resultado.getInt("ID");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "No se pudo encontrar el ID de su cuenta destino.";
		}
        
        if(idCuentaDestino==idCuenta) return "No puede transferir a la misma cuenta";
        
        System.out.println("Se encontro idCuentaDestino: "+idCuentaDestino);
        
        try {
	        String query = "SELECT Saldo FROM Cuenta WHERE numeroCuenta=?";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setString(1, cuentaOrigen);
	        ResultSet resultado = pst.executeQuery();
	        double saldoDisponible=0;
	        while (resultado.next()) {
				saldoDisponible=resultado.getDouble("Saldo");
			}
	        System.out.println("Saldo disponible: "+saldoDisponible);
	        if((saldoDisponible-monto)<0) {
	        	System.out.println("Saldo insuficiente para realizar la transferencia. Saldo disponible: $"+saldoDisponible);
	        	return "Saldo insuficiente para realizar la transferencia. Saldo disponible: $"+saldoDisponible;
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Saldo disponible insuficiente para realizar transferencia.";
		}
        
        
        try {
        	PreparedStatement miSentencia = connection.prepareStatement("UPDATE Cuenta SET Saldo=(Saldo-?) WHERE numeroCuenta=?");
			miSentencia.setDouble(1, monto);
			miSentencia.setString(2, cuentaOrigen);
	        int rowsAffected = miSentencia.executeUpdate();
	        if (rowsAffected!=1) {
	        	return "Hubo un error al intentar realizar el movimiento de saldo en cuenta origen.";
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Hubo un error al intentar realizar el movimiento de saldo en cuenta origen.";
		}
        
        System.out.println("Se resto saldo en cuenta origen.");
		
		try
		{
			String query = "INSERT INTO Movimiento(fecha, detalle, idTipoMovimiento, importe, tipoMovimiento, IdCuenta) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);
            Date hoy = new Date(System.currentTimeMillis());
            ps.setDate(1, hoy);
            ps.setString(2, "Transferencia a CBU: "+cbuDestino);
            ps.setInt(3, 4);
            ps.setDouble(4, monto*-1);
            ps.setString(5, "Egreso");
            ps.setInt(6, idCuenta);

            ps.executeUpdate();     
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Hubo un error al intentar grabar el egreso de saldo en movimientos.";
		}
		
        System.out.println("Se agrego egreso en movimientos.");

		
        try {
        	PreparedStatement miSentencia = connection.prepareStatement("UPDATE Cuenta SET Saldo=(Saldo+?) WHERE ID=?");
			miSentencia.setDouble(1, monto);
			miSentencia.setInt(2, idCuentaDestino);
	        int rowsAffected = miSentencia.executeUpdate();
	        if (rowsAffected!=1) {
	        	return "Hubo un error al intentar realizar el ingreso de saldo en cuenta destino.";
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Hubo un error al intentar realizar el ingreso de saldo en cuenta destino.";
		}
        
        System.out.println("Se agrego saldo a cuenta destino.");

		
		try
		{
			String query = "INSERT INTO Movimiento(fecha, detalle, idTipoMovimiento, importe, tipoMovimiento, IdCuenta) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);
            Date hoy = new Date(System.currentTimeMillis());
            ps.setDate(1, hoy);
            ps.setString(2, "Transferencia desde cuenta con id: "+idCuenta);
            ps.setInt(3, 4);
            ps.setDouble(4, monto);
            ps.setString(5, "Ingreso");
            ps.setInt(6, idCuentaDestino);

            ps.executeUpdate();     
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Hubo un error al intentar grabar el ingreso de saldo en movimientos.";
		}
		
        System.out.println("Se agrego ingreso en movimientos.");

		
		return "Se realizo transferencia satisfactoriamente.";
	}
	
	public boolean existeCBU(String cbuDestino) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        int contados=0;
        try {
	        String query = "SELECT COUNT(*) AS contados FROM Cuenta WHERE CBU=?";
	        PreparedStatement pst = connection.prepareStatement(query);
	        pst.setString(1, cbuDestino);
	        ResultSet resultado = pst.executeQuery();
	        while (resultado.next()) {
	        	contados=resultado.getInt("contados");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
        System.out.println("contados"+contados);
        if(contados>0) {
        	return true;
        }
		return false;
	}
}
