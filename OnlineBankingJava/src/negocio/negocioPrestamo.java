package negocio;

import java.util.ArrayList;
import java.util.List;

import entidades.Prestamo;
import entidades.Cuenta;
import entidades.cliente;

public interface negocioPrestamo {
	boolean agregar (Prestamo prestamo);
	boolean cambiarEstadoPrestamo (Prestamo prestamo, int estadoPrestamo);
	ArrayList<Prestamo> leerTodos();
	ArrayList<Prestamo> leerTodosCliente(String dni);
	ArrayList<String> leerClientes();
	ArrayList<String> leerCuentas();
	int editarCuenta(Cuenta cuenta);
	Prestamo getPrestamoById(int id);
	boolean altaPrestamo(double monto, int cuotas, String cuenta);
	boolean restarCuota(int prestamoId); //NumeroCuotas-1
}