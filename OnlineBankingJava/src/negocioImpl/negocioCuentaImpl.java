package negocioImpl;


import java.math.BigInteger;
import java.util.ArrayList;

import daoImpl.daoClienteImpl;
import daoImpl.daoCuentaImpl;
import entidades.Cuenta;
import entidades.Prestamo;
import negocio.negocioCuenta;
import negocio.negocioPrestamo;

public  class negocioCuentaImpl implements negocioCuenta {

	
	//AGREGAR CUENTA
	@Override
	public int agregar(Cuenta cuenta) {
		daoClienteImpl daocli= new daoClienteImpl();
		daoCuentaImpl dao= new daoCuentaImpl();
		
		//VALIDO SI EL CLIENTE ESTA ACTIVO
		String DNI=cuenta.getDNICliente();
		int ID=daocli.ObtenerIdCliente(DNI);
		boolean estado=daocli.verificarEstadoCliente(ID);
		
		int respuesta=0;
		//SI ESTA ACTIVO AGREGO CUENTA
			if (estado) {
			respuesta=dao.agregar(cuenta);
		}
		
		
		return respuesta;
	}

	
	
	//ELIMINAR CUENTA X  DNI
	public int eliminarCuenta (int id){
		daoCuentaImpl dao= new daoCuentaImpl();
		int filas=0;
		filas= dao.eliminarCuenta(id);
		return filas;
	}
	
	//ACTIVAR CUENTA
	@Override
	public int activarCuenta(int id) {
		
		daoCuentaImpl dao= new daoCuentaImpl();
		
		String dni= dao.ObtenerDNI(id);
		int cant=dao.cantidadCuentas(dni);
		int filas=0;
		
		if(cant<3) {
			
			filas= dao.activarCuenta(id);
		}
		
		
		return filas;
	}

	
	
	
	//LISTAR TODOS
	
	public ArrayList<Cuenta> leerTodos() {
        daoCuentaImpl dao = new daoCuentaImpl();
        return dao.leerTodos(); // Llamar a DAO para obtener la lista de cuentas
    }

	
	//LISTAR TODOS X CLIENTE
	public ArrayList<Cuenta> obtenerCuentasCliente(String dni) {
        daoCuentaImpl dao = new daoCuentaImpl();
        return dao.obtenerCuentasCliente(dni); // Llamar a DAO para obtener la lista de cuentas
    }



	//EDITAR CUENTA

	@Override
	public int editarCuenta(Cuenta cuenta) {
		
		daoCuentaImpl dao= new daoCuentaImpl();
	
		return dao.editarCuenta(cuenta);			
	}

	public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
		daoCuentaImpl dao = new daoCuentaImpl();
	    // Llamamos al método del DAO para obtener la cuenta por número
	    return dao.obtenerCuentaPorNumero(numeroCuenta);
	}

// CANTIDAD DE CUENTAS X CLIENTE	
	
	@Override
	public int cantidadCuentas(String dni) {
		
	daoCuentaImpl dao=new daoCuentaImpl();
	 // Llamamos al método del DAO para obtener la cantidad de cuenta activas del dni
	return dao.cantidadCuentas(dni);
				
	}



//MONTO POR CUENTA	
	
	@Override
	public double montoCuenta(String dni, String numeroCuenta) {
		
		return 0;
	}

//BUSCAR SI EL CLIENTE EXISTE 

	@Override
	public int buscarDNI(String dni) {
	
		int resp=0;
		int cantidad=0;
	
		// Llamamos al método del DAO para saber si existe el dni
		daoCuentaImpl dao=new daoCuentaImpl();
	
		//si encuentra trae 1
		cantidad=dao.existeDNI(dni);
		
		//si existe retorno true
		if (cantidad>0) {
			resp=1;
			return resp;
		}
		
		//si no lo encuentra retorna false
		return resp;
	}



	
	//MAXIMO CBU
	
	@Override
	public String obtenerCBUMax() {
		 daoCuentaImpl dao = new daoCuentaImpl();
		
		  BigInteger max = new BigInteger(dao.obtenerCBUMax());
	        return max.add(BigInteger.ONE).toString();
	}


	//MAXIMO CUENTA
	
	@Override
	public String obtenerCuentaMax() {
	
		 daoCuentaImpl dao = new daoCuentaImpl();
		   
		   
		  BigInteger max = new BigInteger(dao.obtenerCuentaMax());
	        return max.add(BigInteger.ONE).toString();
		 
   
		  
	}

	
	 @Override
	    public ArrayList<Cuenta> obtenerCuentasPorUsuario(String dniCliente) {
	        daoCuentaImpl dao = new daoCuentaImpl();
	        // Llamamos al método del DAO para obtener todas las cuentas de un usuario
	        return dao.obtenerCuentasPorUsuario(dniCliente);
	    }



	public boolean validarDeuda(int id) {
		negocioPrestamoImpl negocio = new negocioPrestamoImpl();
		ArrayList<Integer> LstCuotas = negocio.leerCuentasId(id);
		
		for (Integer item : LstCuotas) {		
			if(item != 0) {				
				return true;
			}
		}
		
		return false;
	}



	public Cuenta obtenerCuentaID(Integer idCuenta) {
		daoCuentaImpl dao = new daoCuentaImpl();
        // Llamamos al método del DAO para obtener todas las cuentas de un usuario
        return dao.obtenerCuentaID(idCuenta);
	}


	
	
	

}
