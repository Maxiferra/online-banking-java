package entidades;

import java.util.Date;

public class Cuenta {

	   	private int id;
	    private String DNICliente;
	    private Date fechaCreacion;
	    private int tipoCuenta; 
	    private String numeroCuenta;
	    private String cbu;
	    private double saldo;
	    private boolean eliminado;
	    
	    
	    private TipoCuenta cuenta;
	    
	  

	    // Constructor con parámetros
	    public Cuenta(int id, String DNICliente, Date fechaCreacion, int tipoCuenta, 
	                  String numeroCuenta, String cbu, double saldo, boolean eliminado, TipoCuenta cuenta ) {
	       
	    	this.id = id;
	        this.DNICliente = DNICliente;
	        this.fechaCreacion = fechaCreacion;
	        this.tipoCuenta = tipoCuenta;
	        this.numeroCuenta = numeroCuenta;
	        this.cbu = cbu;
	        this.saldo = saldo;
	        this.eliminado = false;
	        
	        this.cuenta=cuenta;
	    }

	    
	    public Cuenta() {
	       
	        this.saldo = 0.0;
	        this.eliminado = false;
	    }

	   
	

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getDNICliente() {
	        return DNICliente;
	    }

	    public void setDNICliente(String dni) {
	        this.DNICliente = dni;
	    }

	    public Date getFechaCreacion() {
	        return fechaCreacion;
	    }

	    public void setFechaCreacion(Date fechaCreacion) {
	        this.fechaCreacion = fechaCreacion;
	    }

	    public int getTipoCuenta() {
	        return tipoCuenta;
	    }

	    public void setTipoCuenta(int tipoCuenta) {
	        this.tipoCuenta = tipoCuenta;
	    }

	    public String getNumeroCuenta() {
	        return numeroCuenta;
	    }

	    public void setNumeroCuenta(String numeroCuenta) {
	        this.numeroCuenta = numeroCuenta;
	    }

	    public String getCbu() {
	        return cbu;
	    }

	    public void setCbu(String cbu) {
	        this.cbu = cbu;
	    }

	    public double getSaldo() {
	        return saldo;
	    }

	    public void setSaldo(double saldo) {
	        this.saldo = saldo;
	    }

	    public boolean getEliminado() {
	        return eliminado;
	    }

	    public void setEliminado(boolean eliminado) {
	        this.eliminado = eliminado;
	    }
	    
	    
		public TipoCuenta getCuenta() {
			return cuenta;
		}

		public void setCuenta(TipoCuenta cuenta) {
			this.cuenta = cuenta;
		}
	    

	    
	    @Override
	    public String toString() {
	        return "Cuenta: id=" + id + ", DNICliente=" + DNICliente + ", fechaCreacion=" + fechaCreacion
	                + ", tipoCuenta=" + tipoCuenta + ", numeroCuenta=" + numeroCuenta + ", cbu=" + cbu + ", saldo="
	                + saldo + ", eliminado=" + eliminado + " ";
	    }

	
	
}
