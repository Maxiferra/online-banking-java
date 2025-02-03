package negocioImpl;

import java.util.ArrayList;

import entidades.TipoCuenta;
import negocio.negocioTipoCuentas;
import daoImpl.daoTipoCuentaImpl;

public class negocioTipoCuentasImpl implements negocioTipoCuentas  {

	@Override
	public ArrayList<TipoCuenta> leerTodos() {
		
		
		daoTipoCuentaImpl dao= new daoTipoCuentaImpl();
		
		
		return dao.leerTodos();
	}

	
	
}
