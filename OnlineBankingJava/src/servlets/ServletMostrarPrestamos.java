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

import dao.daoCliente;
import dao.daoPrestamo;
import negocio.negocioPrestamo;
import negocioImpl.negocioPrestamoImpl;
import daoImpl.daoPrestamoImpl;
import daoImpl.daoClienteImpl;
import entidades.Prestamo;
import entidades.Usuario;
import entidades.cliente;

@WebServlet("/ServletMostrarPrestamos")
public class ServletMostrarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletMostrarPrestamos() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado.getTipoUsuario()!= 1) {
            response.sendRedirect("InicioUser.jsp");
            return;
        }
		
		negocioPrestamoImpl negPrestamo = new negocioPrestamoImpl();
		ArrayList<Prestamo> listaPrestamos=negPrestamo.leerTodos();
		ArrayList<String> listaClientes= negPrestamo.leerClientes();
		ArrayList<String> listaCuentas= negPrestamo.leerCuentas();

		if (listaPrestamos == null) {
			listaPrestamos = new ArrayList<>(); // Evita que sea null
		}
		if (listaClientes == null) {
			listaClientes = new ArrayList<>(); // Evita que sea null
		}
		if (listaCuentas == null) {
			listaCuentas = new ArrayList<>(); // Evita que sea null
		}

		request.setAttribute("listaPrestamos", listaPrestamos);// Asegura que listaClientes esté aquí
		request.setAttribute("listaClientes", listaClientes);
		request.setAttribute("listaCuentas", listaCuentas);

		RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarPrestamos.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}