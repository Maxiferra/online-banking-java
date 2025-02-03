package dao;

import java.sql.Date;
import java.util.ArrayList;

import entidades.Cuota;

public interface daoCuota {
	public ArrayList<Cuota> leerTodos(int idPrestamo);
	public boolean agregarCuota(int prestamoId, double monto, int cuotaNumero,Date hoy);
}