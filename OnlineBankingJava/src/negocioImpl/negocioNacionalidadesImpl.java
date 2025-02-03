package negocioImpl;

import java.util.ArrayList;

import dao.daoNacionalidades;
import daoImpl.daoNacionalidadesImpl;
import entidades.Nacionalidades;
import negocio.negocioNacionalidades;

public class negocioNacionalidadesImpl implements negocioNacionalidades {
	
	private daoNacionalidades daoNac = new daoNacionalidadesImpl();

    @Override
    public ArrayList<Nacionalidades> obtenerTodas() {
        return daoNac.obtenerTodas();
    }

    @Override
    public Nacionalidades obtenerPorId(int id) {
        return daoNac.obtenerPorId(id);
    }
}
