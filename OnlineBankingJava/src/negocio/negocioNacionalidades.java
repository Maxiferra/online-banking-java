package negocio;

import java.util.ArrayList;

import entidades.Nacionalidades;

public interface negocioNacionalidades {
	public ArrayList<Nacionalidades> obtenerTodas();
    public Nacionalidades obtenerPorId(int id);
}
