package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import entidades.cliente;
import negocio.negocioCliente;
import negocioImpl.negocioClienteImpl;


@WebServlet("/servletMiPerfil")
public class servletMiPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public servletMiPerfil() {
        super();
       
    }
     
	

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	    	HttpSession session = request.getSession(false);
	    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

	        if (usuarioLogueado == null) {
	            response.sendRedirect("Login.jsp");
	            return;
	        }

	        int idUsuario = usuarioLogueado.getId();
	       
	        negocioCliente negocio = new negocioClienteImpl();
	        cliente miCliente = negocio.obtenerMiPerfil(idUsuario);
	        if (miCliente == null) {
	            System.out.println("No se encontró el cliente con el ID: " + idUsuario);
	        }
	        

	        if (miCliente != null) {
	            
	           
	            request.setAttribute("nombre", miCliente.getNombre() + ' ' + miCliente.getApellido());
	            request.setAttribute("dni", miCliente.getDni());
	            request.setAttribute("email", miCliente.getEmail());
	            request.setAttribute("telefono", miCliente.getTelefono());
	            request.setAttribute("nombreUsuario", usuarioLogueado.getTxtUsuario());
	            request.getRequestDispatcher("Miperfil.jsp").forward(request, response);
	        } else {
	            System.out.println("El cliente no fue encontrado en la base de datos.");
	            request.setAttribute("error", "No se pudo cargar el perfil.");
	            request.getRequestDispatcher("Miperfil.jsp").forward(request, response);
	        }    
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
}
}