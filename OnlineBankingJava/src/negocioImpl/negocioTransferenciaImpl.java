package negocioImpl;

import java.sql.Date;

import daoImpl.daoReporteImpl;
import daoImpl.daoTransferenciaImpl;

public class negocioTransferenciaImpl {

	public negocioTransferenciaImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String agregarTransferenciaPropia(String cuentaOrigen,String cuentaDestino,double monto) {
		String transferido="";
		daoTransferenciaImpl daoTransferencia= new daoTransferenciaImpl();
		transferido = daoTransferencia.agregarTransferenciaPropia(cuentaOrigen, cuentaDestino, monto);
		return transferido;
	}
	
	public String agregarTransferenciaTercero(String cuentaOrigen,String cbuDestino, double monto) {
		String transferido="";
		daoTransferenciaImpl daoTransferencia= new daoTransferenciaImpl();
		transferido = daoTransferencia.agregarTransferenciaTercero(cuentaOrigen, cbuDestino, monto);
		return transferido;
	}
	
	public boolean existeCBU(String cuenta) {
		daoTransferenciaImpl daoTransferencia= new daoTransferenciaImpl();
		if(daoTransferencia.existeCBU(cuenta)){
			return true;
		}
		return false;
	}

}