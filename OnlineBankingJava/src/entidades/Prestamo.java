package entidades;

import java.util.Date;

public class Prestamo {

	 private int id;
	    private int idCliente;
	    private int idCuenta;
	    private Date fechaAlta;
	    private double importePedido;
	    private int plazoMeses;
	    private double importePorMes;
	    private int cantidadCuotas;
	    private int estadoPrestamo;
	    
	    public Prestamo() {}

	    public Prestamo(int id, int idCliente, int idCuenta, Date fechaAlta, 
	                    double importePedido, int plazoMeses, double importePorMes, 
	                    int cantidadCuotas, int estadoPrestamo) {
	    	super();
	        this.id = id;
	        this.idCliente = idCliente;
	        this.idCuenta = idCuenta;
	        this.fechaAlta = fechaAlta;
	        this.importePedido = importePedido;
	        this.plazoMeses = plazoMeses;
	        this.importePorMes = importePorMes;
	        this.cantidadCuotas = cantidadCuotas;
	        this.estadoPrestamo = estadoPrestamo;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getIdCliente() {
			return idCliente;
		}

		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}

		public int getIdCuenta() {
			return idCuenta;
		}

		public void setIdCuenta(int idCuenta) {
			this.idCuenta = idCuenta;
		}

		public Date getFechaAlta() {
			return fechaAlta;
		}

		public void setFechaAlta(Date fechaAlta) {
			this.fechaAlta = fechaAlta;
		}

		public double getImportePedido() {
			return importePedido;
		}

		public void setImportePedido(double importePedido) {
			this.importePedido = importePedido;
		}

		public int getPlazoMeses() {
			return plazoMeses;
		}

		public void setPlazoMeses(int plazoMeses) {
			this.plazoMeses = plazoMeses;
		}

		public double getImportePorMes() {
			return importePorMes;
		}

		public void setImportePorMes(double importePorMes) {
			this.importePorMes = importePorMes;
		}

		public int getCantidadCuotas() {
			return cantidadCuotas;
		}

		public void setCantidadCuotas(int cantidadCuotas) {
			this.cantidadCuotas = cantidadCuotas;
		}
		
		public int getEstadoPrestamo() {
			return estadoPrestamo;
		}

		public void setEstadoPrestamo(int estadoPrestamo) {
			this.estadoPrestamo = estadoPrestamo;
		}
	
}