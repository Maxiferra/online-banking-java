package negocio;

import java.sql.Date;
import java.util.ArrayList;

import entidades.Cuota;

public interface negocioCuota {
	public ArrayList<Cuota> leerCuotasPrestamoId(int prestamoId);
	public boolean agregarCuota(int prestamoId, double monto, int cuotaNumero, Date hoy);
}
