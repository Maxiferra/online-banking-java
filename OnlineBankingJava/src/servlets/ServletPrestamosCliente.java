package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.daoCliente;
import dao.daoPrestamo;
import negocio.negocioPrestamo;
import negocio.negocioUsuario;
import negocioImpl.negocioPrestamoImpl;
import negocioImpl.negocioUsuarioImpl;
import daoImpl.daoPrestamoImpl;
import daoImpl.daoClienteImpl;
import entidades.Prestamo;
import entidades.Usuario;
import entidades.cliente;

@WebServlet("/ServletPrestamosCliente")
public class ServletPrestamosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletPrestamosCliente() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Obtener datos de sesion para filtrar el usuario
		int idUsuario=-1;
		try {
			idUsuario=(int) request.getSession().getAttribute("idUsuario");
			System.out.println("IdUsuario:" +idUsuario);
		}
		catch (NullPointerException el) {
			el.printStackTrace();
			System.out.println("Error: Sesion incorrecta o usuario no logueado.");
		}
		negocioPrestamoImpl negPrestamo = new negocioPrestamoImpl();
		ArrayList<Prestamo> listaPrestamos= new ArrayList<>();
		if (idUsuario!=-1){
			listaPrestamos=negPrestamo.leerTodosCliente(idUsuario);
		}
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

		request.setAttribute("listaPrestamos", listaPrestamos);
		request.setAttribute("listaClientes", listaClientes);
		request.setAttribute("listaCuentas", listaCuentas);

		RequestDispatcher dispatcher = request.getRequestDispatcher("PrestamosSolicitados.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		negocioPrestamoImpl negPrestamo = new negocioPrestamoImpl();
	    int prestamoId = Integer.parseInt(request.getParameter("prestamoDetalles"));
	    Prestamo prestamoCuotas=negPrestamo.getPrestamoById(prestamoId);
	    System.out.println("Prestamo: " + prestamoCuotas.getIdCliente());
	    request.setAttribute("prestamoCuotas", prestamoCuotas);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("PrestamosSolicitados.jsp");
		dispatcher.forward(request, response);
	}
}