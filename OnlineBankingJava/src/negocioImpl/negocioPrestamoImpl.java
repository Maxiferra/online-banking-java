package negocioImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoImpl.daoCuentaImpl;
import daoImpl.daoPrestamoImpl;
import daoImpl.daoClienteImpl;
import entidades.Cuenta;
import entidades.Prestamo;
import excepciones.PrestamoExcepcion;

public class negocioPrestamoImpl {
	
	PrestamoExcepcion exc;

	public negocioPrestamoImpl() {
	}

	public ArrayList<Prestamo> leerTodos() throws PrestamoExcepcion {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			throw new PrestamoExcepcion(exc.errorMonto());		}
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		daoPrestamoImpl daoPrestamo = new daoPrestamoImpl();
		lista=daoPrestamo.leerTodos();
		return lista;
	}
	
	public ArrayList<Prestamo> leerTodosCliente(int idUsuario) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		daoPrestamoImpl daoPrestamo = new daoPrestamoImpl();
		lista=daoPrestamo.leerTodosCliente(idUsuario);
		return lista;
	}
	
	public ArrayList<String> leerClientes() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		ArrayList<String> lista = new ArrayList<String>();
		daoPrestamoImpl daoPrestamo = new daoPrestamoImpl();
		lista=daoPrestamo.leerClientes();
		return lista;
	}
	
	public ArrayList<String> leerCuentas() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		ArrayList<String> lista = new ArrayList<String>();
		daoPrestamoImpl daoPrestamo = new daoPrestamoImpl();
		lista=daoPrestamo.leerCuentas();
		return lista;
	}
	
	public ArrayList<Integer> leerCuentasId(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		ArrayList<Integer> lista = new ArrayList<Integer>();
		daoPrestamoImpl daoPrestamo = new daoPrestamoImpl();
		lista= daoPrestamo.leerCuentasId(id);
		return lista;
	}
	
	public boolean cambiarEstadoPrestamo(Prestamo prestamo, int estadoPrestamo){
		daoPrestamoImpl daoPrestamo=new daoPrestamoImpl();
		boolean modificado=daoPrestamo.cambiarEstadoPrestamo(prestamo, estadoPrestamo);
		return modificado;
	}
	
	public Prestamo getPrestamoById(int id) 
	{
		daoPrestamoImpl daoPrestamo=new daoPrestamoImpl();
		Prestamo prestamo=daoPrestamo.getPrestamoById(id);
		return prestamo;
	}
	
	public boolean altaPrestamo(double monto, int cuotas, String cuenta) {
		daoPrestamoImpl daoPrestamo=new daoPrestamoImpl();
		
		//Validaciones
		if (monto <=0) {
			throw new PrestamoExcepcion(exc.errorMonto());
		}
		if(cuotas<=0) {
			throw new PrestamoExcepcion(exc.errorCuotas());
		}
		
		if(daoPrestamo.altaPrestamo(monto, cuotas, cuenta))
		{
			System.out.println("Solicitud realizada correctamente");
			return true;
		}
		System.out.println("La solicitud no pudo ser procesada");
		return false;
	}
	
	public boolean restarCuota(int prestamoId){
		daoPrestamoImpl daoPrestamo=new daoPrestamoImpl();
		if(daoPrestamo.restarCuota(prestamoId))
		{
			System.out.println("Se resto cuota pendiente de pago.");
			return true;
		}
		System.out.println("La solicitud no pudo ser procesada");
		return false;
	}

}