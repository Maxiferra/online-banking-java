package dao;

import java.util.ArrayList;

import entidades.Localidades;

public interface daoLocalidades {
	ArrayList<Localidades> obtenerTodas();
    Localidades obtenerPorId(int id);
}
