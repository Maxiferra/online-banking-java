package negocioImpl;

import java.util.ArrayList;

import dao.daoProvincias;
import daoImpl.daoProvinciasImpl;
import entidades.Provincias;
import negocio.negocioProvincias;

public class negocioProvinciasImpl implements negocioProvincias {
	private daoProvincias daoProv = new daoProvinciasImpl();

    @Override
    public ArrayList<Provincias> obtenerTodas() {      
        return daoProv.obtenerTodas();
    }

    @Override
    public Provincias obtenerPorId(int id) {      
        return daoProv.obtenerPorId(id);
    }
}
