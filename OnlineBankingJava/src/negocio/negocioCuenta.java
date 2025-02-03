package negocio;

import java.util.ArrayList;


import entidades.Cuenta;

public interface negocioCuenta {

	
	int agregar (Cuenta cuenta);
	int eliminarCuenta (int id);
	int activarCuenta (int id);
	ArrayList<Cuenta> leerTodos();
	int editarCuenta(Cuenta cuenta);
	Cuenta obtenerCuentaPorNumero(String numeroCuenta);
	int cantidadCuentas(String dni);
	double montoCuenta(String dni, String numeroCuenta);
	int buscarDNI(String dni);
	String obtenerCBUMax();
	String obtenerCuentaMax();
	ArrayList<Cuenta> obtenerCuentasPorUsuario(String dniCliente);
	 
}
