package dao;

import java.util.ArrayList;
import entidades.Prestamo;


public interface daoPrestamo {
	public boolean altaPrestamo(double monto, int cuotas, String cuenta);
	boolean cambiarEstadoPrestamo(Prestamo prestamo, int estadoPrestamo);
	ArrayList<Prestamo> leerTodos();
	ArrayList<String> leerClientes();
	ArrayList<String> leerCuentas();
	ArrayList<Integer> leerCuentasId(int Id);
	ArrayList<Prestamo> leerTodosCliente(int idUsuario);
	int obtenerEstadoPrestamo(int idPrestamo);
	Prestamo getPrestamoById(int id);
	boolean restarCuota(int prestamoId);
}