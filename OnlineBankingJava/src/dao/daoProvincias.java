package dao;


import java.util.ArrayList;

import entidades.Provincias;

public interface daoProvincias {
	ArrayList<Provincias> obtenerTodas();
    Provincias obtenerPorId(int id);
}
