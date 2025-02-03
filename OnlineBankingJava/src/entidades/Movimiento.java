package entidades;

import java.math.BigDecimal;
import java.util.Date;

public class Movimiento {
	
	  private Date fecha;
	    private String detalle;
	    private BigDecimal importe;
	    private TipoMovimiento tipoMovimiento; 

	    public Movimiento() {
	    }

	    public Movimiento(Date fecha, String detalle, BigDecimal importe) {
	        this.fecha = fecha;
	        this.detalle = detalle;
	        this.importe = importe;
	    }

	    public Date getFecha() {
	        return fecha;
	    }

	    public void setFecha(Date fecha) {
	        this.fecha = fecha;
	    }

	    public String getDetalle() {
	        return detalle;
	    }

	    public void setDetalle(String detalle) {
	        this.detalle = detalle;
	    }

	    public BigDecimal getImporte() {
	        return importe;
	    }

	    public void setImporte(BigDecimal importe) {
	        this.importe = importe;
	    }

	    public TipoMovimiento getTipoMovimiento() {  
	        return tipoMovimiento;
	    }

	    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {  
	    	
	    	 this.tipoMovimiento = tipoMovimiento;
	    }

	    @Override
	    public String toString() {
	        return "Movimiento{" +
	                "fecha=" + fecha +
	                ", detalle='" + detalle + '\'' +
	                ", importe=" + importe +
	                ", tipoMovimiento=" + tipoMovimiento +  
	                '}';
	    }

}
