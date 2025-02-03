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

import entidades.Usuario;
import entidades.cliente;
import negocio.NegocioMetodos;
import negocio.negocioCliente;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioMetodosImpl;

@WebServlet("/ServletMostrarClientes")
public class ServletMostrarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletMostrarClientes() {
		super();

	}

	 protected ArrayList<cliente> CargarLista() {
	        negocioCliente negocioCliente = new negocioClienteImpl();
	        return negocioCliente.leerTodos();
	    }
	
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario() != 1) {
            response.sendRedirect("Login.jsp");
            return;
        }
		
		negocioCliente negocioCliente = new negocioClienteImpl();
        int filas = -1; 

        if (request.getParameter("btnActualizar") != null) {
            ArrayList<cliente> listaClientes = CargarLista();
            request.setAttribute("listaClientes", listaClientes);
            request.setAttribute("mensaje", "Registros actualizados correctamente");
        }
        
        if (request.getParameter("btnEliminarModal") != null && request.getParameter("idUsuarioDelete") != null) {
            int idClienteDes = Integer.parseInt(request.getParameter("idUsuarioDelete"));
            if (negocioCliente.desactivarCliente(idClienteDes)) {
                filas = 1; 
            } else {
                filas = 0;
            }
       }
 
       
        if (request.getParameter("btnActivarModal") != null && request.getParameter("idUsuarioActivar") != null) {
            int idClienteAct = Integer.parseInt(request.getParameter("idUsuarioActivar"));
            if (negocioCliente.activarCliente(idClienteAct)) {
                filas = 1; 
            } else {
                filas = 0; 
            }
        }
        NegocioMetodos negocioMetodos = new negocioMetodosImpl();

	    negocioMetodos.cargarListas(request);
        
        request.setAttribute("cantFilas", filas); 
        request.setAttribute("listaClientes", CargarLista());

        RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarClientes.jsp");
        dispatcher.forward(request, response);
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
