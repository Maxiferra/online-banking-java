package dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import entidades.Movimiento;

public interface daoMovimiento {

	public ArrayList<Movimiento> obtenerMovimientos(String numeroCuenta);
	public ArrayList<Movimiento> FiltroRangoPorMonto(String numeroCuenta, BigDecimal montoMinimo, BigDecimal montoMaximo);
	
}
