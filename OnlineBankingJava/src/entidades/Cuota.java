package entidades;

import java.util.Date;

public class Cuota {
	 	private int id;
	    private int idPrestamo;
	    private int numeroCuota;
	    private double monto;
	    private Date fechaPago;
	    
	    public Cuota() {}

	    public Cuota(int id, int idPrestamo, int numeroCuota, double monto, Date fechaPago) {
	    	super();
	        this.id = id;
	        this.idPrestamo = idPrestamo;
	        this.numeroCuota = numeroCuota;
	        this.monto = monto;
	        this.fechaPago = fechaPago;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public int getIdPrestamo() {
			return idPrestamo;
		}

		public void setIdPrestamo(int idPrestamo) {
			this.idPrestamo = idPrestamo;
		}

		public int getNumeroCuota() {
			return numeroCuota;
		}

		public void setNumeroCuota(int numeroCuota) {
			this.numeroCuota = numeroCuota;
		}

		public double getMonto() {
			return monto;
		}

		public void setMonto(double monto) {
			this.monto = monto;
		}

		public Date getFechaPago() {
			return fechaPago;
		}

		public void setFechaPago(Date fechaPago) {
			this.fechaPago = fechaPago;
		}
}
