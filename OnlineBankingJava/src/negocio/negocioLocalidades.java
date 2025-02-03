package negocio;

import java.util.ArrayList;

import entidades.Localidades;

public interface negocioLocalidades {
	ArrayList<Localidades> obtenerTodas();
    Localidades obtenerPorId(int id);
}
