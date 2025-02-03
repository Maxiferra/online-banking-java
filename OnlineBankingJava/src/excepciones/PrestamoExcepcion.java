package excepciones;

public class PrestamoExcepcion extends RuntimeException{
	
    public PrestamoExcepcion() {
        super("Error al intentar validar prestamo.");
    }

    public PrestamoExcepcion(String message) {
        super(message);
    }

    public String errorMonto() {
        return "El monto es obligatorio, y debe ser mayor a cero.";
    }

    public String errorCuotas() {
        return "La cantidad de cuotas es obligatoria, y debe ser mayor a cero.";
    }
    
    public String errorCuenta() {
        return "Error. Verifique la cuenta elegida.";
    }
   
}