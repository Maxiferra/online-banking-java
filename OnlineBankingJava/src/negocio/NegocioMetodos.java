package negocio;

import javax.servlet.http.HttpServletRequest;

public interface NegocioMetodos {

	void cargarListas(HttpServletRequest request);
	boolean validarCamposRegistrar(HttpServletRequest request);
	boolean validarCamposRegistrarAdmin(HttpServletRequest request);
	boolean validarCamposEditar(HttpServletRequest request);
	boolean validarCamposCuentas(HttpServletRequest request) ;
}
