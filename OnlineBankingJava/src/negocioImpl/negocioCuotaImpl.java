package negocioImpl;

import java.sql.Date;
import java.util.ArrayList;

import daoImpl.daoCuotaImpl;
import daoImpl.daoPrestamoImpl;
import entidades.Cuenta;
import entidades.Cuota;
import entidades.Prestamo;

public class negocioCuotaImpl {

	public negocioCuotaImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean agregarCuota(int prestamoId, double monto, int cuotaNumero,Date hoy){
		daoCuotaImpl daoCuota = new daoCuotaImpl();
		
		if(daoCuota.agregarCuota(prestamoId, monto, cuotaNumero, hoy)  ) {
			System.out.println("Se realizo pago de cuota pendiente correctamente.");
			return true;
		}
		else {
			System.out.println("No se pudo agregar el pago.");
			return false;
		}
	}
	
	public ArrayList<Cuota> leerCuotasPrestamoId(int prestamoId){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		ArrayList<Cuota> lista = new ArrayList<Cuota>();
		daoCuotaImpl daoCuota = new daoCuotaImpl();
		lista=daoCuota.leerTodos(prestamoId);
		return lista;
	}

	public boolean SaldoDisponible(int idPrestamo, double monto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Prestamo prestamo = new Prestamo();
		Cuenta cuenta = new Cuenta();
		negocioPrestamoImpl negocioPres = new negocioPrestamoImpl();
		negocioCuentaImpl negocioCuenta = new negocioCuentaImpl();
		
		prestamo = negocioPres.getPrestamoById(idPrestamo);

		cuenta = negocioCuenta.obtenerCuentaID(prestamo.getIdCuenta());
		
		if(cuenta.getSaldo() >= monto) {
			return true;
		}else {
			return false;
		}
		
		
	}

}
