package servlets;

import java.io.IOException;
import java.sql.Date;
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
import negocio.negocioCliente;
import negocio.negocioUsuario;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioMetodosImpl;
import negocioImpl.negocioUsuarioImpl;


@WebServlet("/ServletEditarCliente")
public class ServletEditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ServletEditarCliente() {
        super();
        
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario() != 1) {
            response.sendRedirect("Login.jsp");
            return;
        }
		
		if (request.getParameter("btnGuardarCambios") != null && request.getParameter("idCliente") != null) {			
					
			  negocioMetodosImpl negocioMetodos = new negocioMetodosImpl();
		        		        
		        if (!negocioMetodos.validarCamposEditar(request)) {
		        	request.setAttribute("cantFilas", -3);
		            RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarClientes.jsp");
		            dispatcher.forward(request, response);
		            return;
		        }
			
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			
			negocioCliente negocioCliente = new negocioClienteImpl();
			boolean clienteActivo = negocioCliente.verificarEstadoCliente(idCliente);

			if (!clienteActivo) {
			    request.setAttribute("cantFilas", -2);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarClientes.jsp");
			    dispatcher.forward(request, response);
			    return; 
			}
	        String clave = request.getParameter("claveEditar");
	        
	        cliente cliente = new cliente();
	        cliente.setId(idCliente);
	        cliente.setCuil(request.getParameter("cuilEditar"));
	        cliente.setNombre(request.getParameter("nombreEditar"));
	        cliente.setApellido(request.getParameter("apellidoEditar"));
	        cliente.setSexo(request.getParameter("generoEditar"));
	        cliente.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimientoEditar")));
	        cliente.setDireccion(request.getParameter("direccionEditar"));
	        cliente.setEmail(request.getParameter("correoEditar"));
	        cliente.setTelefono(request.getParameter("telefonoEditar"));
	        cliente.setIdNacionalidad(Integer.parseInt(request.getParameter("nacionalidadEditar")));
	        cliente.setIdLocalidad(Integer.parseInt(request.getParameter("localidadEditar")));
	        cliente.setIdProvincia(Integer.parseInt(request.getParameter("provinciaEditar")));
	        
	        boolean clienteEditado = negocioCliente.editarCliente(cliente);		
	        
	        boolean claveEditada = true;
	        if (clave != null && !clave.isEmpty()) {
	            int idUsuario = negocioCliente.obtenerIdUsuario(idCliente);
	            if (idUsuario != -1) {
	               
	                Usuario usuario = new Usuario();
	                usuario.setId(idUsuario); 
	                usuario.setPassword(clave); 

	                negocioUsuario negocioUsuario = new negocioUsuarioImpl();
	                claveEditada = negocioUsuario.editarClave(usuario.getId(), usuario.getPassword());
	            } else {
	                claveEditada = false;
	            }
	        }
	        
            int filas = 0;
            if (clienteEditado && claveEditada) {
                filas = 1;
                request.setAttribute("mensaje", "Se pudo editar al cliente");
            } else {
                request.setAttribute("mensaje", "No se pudo editar");
            }

            request.setAttribute("cantFilas", filas);
        
            RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarClientes.jsp");
            dispatcher.forward(request, response);
		}
        
	}

}
