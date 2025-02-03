package dao;

import java.util.ArrayList;

import entidades.Nacionalidades;

public interface daoNacionalidades {
	public ArrayList<Nacionalidades> obtenerTodas();
    public Nacionalidades obtenerPorId(int id);
}
