package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;

import entidades.Movimiento;

public interface negocioMovimiento {


	ArrayList<Movimiento> obtenerMovimientos(String numeroCuenta);
	ArrayList<Movimiento> FiltroRangoPorMonto(String numeroCuenta, BigDecimal montoMinimo, BigDecimal montoMaximo);
	
	
}
