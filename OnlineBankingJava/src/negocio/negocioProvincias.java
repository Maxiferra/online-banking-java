package negocio;

import java.util.ArrayList;

import entidades.Provincias;

public interface negocioProvincias {
	 	ArrayList<Provincias> obtenerTodas();
	    Provincias obtenerPorId(int id);
}
