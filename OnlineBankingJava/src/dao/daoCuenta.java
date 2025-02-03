package dao;

import java.util.ArrayList;


import entidades.Cuenta;

public interface daoCuenta {

	int agregar (Cuenta cuenta);
	boolean eliminar (Cuenta cuenta);
	ArrayList<Cuenta> leerTodos();
	int eliminarCuenta (int id);
	int editarCuenta(Cuenta cuenta);
	int activarCuenta(int id);
	
	
	Cuenta obtenerCuentaPorNumero(String numeroCuenta);
	ArrayList<Cuenta> obtenerCuentasCliente(String dni);
	ArrayList<Cuenta> obtenerCuentasPorUsuario(String dniCliente);
	int cantidadCuentas(String dni);
	int existeDNI(String dni);
	String ObtenerDNI(int id);
	
	String obtenerCBUMax();
	String obtenerCuentaMax(); 
	
	
}
