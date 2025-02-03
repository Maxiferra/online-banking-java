package negocioImpl;

import java.util.ArrayList;

import dao.daoLocalidades;
import daoImpl.daoLocalidadesImpl;
import entidades.Localidades;
import negocio.negocioLocalidades;

public class negocioLocalidadesImpl implements negocioLocalidades {
	private daoLocalidades daoLoc = new daoLocalidadesImpl();

    @Override
    public ArrayList<Localidades> obtenerTodas() {
        return daoLoc.obtenerTodas();
    }

    @Override
    public Localidades obtenerPorId(int id) {
        return daoLoc.obtenerPorId(id);
    }
}
