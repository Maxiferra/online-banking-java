package negocioImpl;

import java.math.BigDecimal;
import java.util.ArrayList;

import daoImpl.daoMovimientoImpl;
import entidades.Movimiento;
import negocio.negocioMovimiento;

public class negocioMovimientoImpl implements negocioMovimiento{
	
	
	 
    public ArrayList<Movimiento> obtenerMovimientos(String numeroCuenta) {
        
       
        daoMovimientoImpl dao = new daoMovimientoImpl();
        
        
        return dao.obtenerMovimientos(numeroCuenta);
    }
	
    public ArrayList<Movimiento> FiltroRangoPorMonto(String numeroCuenta, BigDecimal montoMinimo, BigDecimal montoMaximo) {
        
        
        daoMovimientoImpl dao = new daoMovimientoImpl();
        
        
        return dao.FiltroRangoPorMonto(numeroCuenta, montoMinimo, montoMaximo);
    }

}
