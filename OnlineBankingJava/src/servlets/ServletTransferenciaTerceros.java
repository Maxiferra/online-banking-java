package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cuenta;
import entidades.Usuario;
import entidades.cliente;
import negocio.negocioCliente;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioCuentaImpl;


@WebServlet("/ServletTransferenciaTerceros")
public class ServletTransferenciaTerceros  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletTransferenciaTerceros() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	HttpSession session = request.getSession(false);
	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

    if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario() != 2) {
        response.sendRedirect("Login.jsp");
        return;
    }

    int idUsuario = usuarioLogueado.getId();
   
    negocioCliente negocio = new negocioClienteImpl();
    cliente miCliente = negocio.obtenerMiPerfil(idUsuario);
    if (miCliente == null) {
        System.out.println("No se encontró el cliente con el ID: " + idUsuario);
    }
    
    negocioCuentaImpl negCuenta = new negocioCuentaImpl();
    ArrayList<Cuenta> listaCuentasCliente=negCuenta.obtenerCuentasCliente(miCliente.getDni());
    ArrayList<String> listaCuentas = new ArrayList<String>();
    for (Cuenta cuenta: listaCuentasCliente) {
    	if(!cuenta.getEliminado()) listaCuentas.add(cuenta.getNumeroCuenta());
    }
    
	request.setAttribute("listaCuentas", listaCuentas);
	RequestDispatcher dispatcher = request.getRequestDispatcher("TransferenciasTerceros.jsp");
	dispatcher.forward(request, response);
}

}
