package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import entidades.Cuenta;

@WebServlet("/ServletTransferenciaPropia")
public class ServletTransferenciaPropia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletTransferenciaPropia() {
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
        ArrayList<String> listaCuentasDestino = new ArrayList<String>();
		
        for (Cuenta cuenta: listaCuentasCliente) {
        	if(!cuenta.getEliminado()) listaCuentas.add(cuenta.getNumeroCuenta());
        }
		
        listaCuentasDestino=listaCuentas;
        
		request.setAttribute("listaCuentas", listaCuentas);
		request.setAttribute("listaCuentasDestino", listaCuentasDestino);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TransferenciasPropias.jsp");
		dispatcher.forward(request, response);
	}

}