package negocio;

public interface negocioTransferencia {
	boolean agregarTransferenciaPropia(String cuentaOrigen,String cuentaDestino,double monto);
	boolean agregarTransferenciaTercero(String cuentaOrigen,String cbuDestino, double monto);
}
