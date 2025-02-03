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

import negocio.negocioCliente;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioCuentaImpl;
import entidades.Usuario;
import entidades.cliente;
import excepciones.PrestamoExcepcion;
import entidades.Cuenta;

import entidades.Cuenta;
import entidades.Usuario;
import entidades.cliente;
import negocio.negocioCliente;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioCuentaImpl;

@WebServlet("/ServletPrestamoSolicitar")
public class ServletPrestamoSolicitar extends HttpServlet {

	public ServletPrestamoSolicitar() {
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws PrestamoExcepcion, ServletException, IOException{
		
		HttpSession session = request.getSession(false);
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
	
	    int idUsuario = usuarioLogueado.getId();
	   
	    negocioCliente negocio = new negocioClienteImpl();
	    cliente miCliente = negocio.obtenerMiPerfil(idUsuario);
	    if (miCliente == null) {
	        System.out.println("No se encontró el cliente con el ID: " + idUsuario);
	    }
	    
	    negocioCuentaImpl negCuenta = new negocioCuentaImpl();
	    ArrayList<Cuenta> listaCuentasCliente=negCuenta.obtenerCuentasCliente(miCliente.getDni());
	    ArrayList<String> listaCuentas=new  ArrayList<String>();
        for (Cuenta cuenta: listaCuentasCliente) {
        	if(!cuenta.getEliminado()) listaCuentas.add(cuenta.getNumeroCuenta());
        }
		
		request.setAttribute("listaCuentasCliente", listaCuentas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PrestamoSolicitud.jsp");
		dispatcher.forward(request, response);
		return;
	}
}