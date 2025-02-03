package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.daoCliente;
import dao.daoUsuario;
import daoImpl.daoClienteImpl;
import daoImpl.daoUsuarioImpl;
import entidades.cliente;
import negocio.negocioCliente;
import negocio.negocioUsuario;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioUsuarioImpl;


@WebServlet("/ServletEliminar")
public class ServletEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletEliminar() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCliente = request.getParameter("idCliente");
	    int filas = 0; // Indicador de éxito o fracaso

	    if (idCliente != null && !idCliente.isEmpty()) {
	        try {
	            int idCli = Integer.parseInt(idCliente);

	            negocioCliente negocioCliente = new negocioClienteImpl();
	            int idUsuario = negocioCliente.obtenerIdUsuario(idCli);

	            negocioUsuario negocioUsuario = new negocioUsuarioImpl();

	            if (negocioCliente.desactivarCliente(idCli)) {
	                filas = 1; // Cliente desactivado con éxito

	                if (idUsuario > 0 && negocioUsuario.desactivarUsuario(idUsuario)) {
	                    filas = 1; // Usuario también desactivado
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            filas = 0; // Fallo en la operación
	        }
	    } else {
	        filas = 0; // ID inválido
	    }

	    request.setAttribute("cantFilas", filas);
	    request.getRequestDispatcher("GestionarClientes.jsp").forward(request, response);
    }
	    

}
