package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Usuario;
import entidades.cliente;
import negocio.negocioCliente;
import negocio.negocioUsuario;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioUsuarioImpl;


@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public servletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
        String username = request.getParameter("usuario");
        String password = request.getParameter("clave");

        if (dni == null || username == null || password == null || dni.isEmpty() || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Por favor complete todos los campos.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        try {
          
            negocioUsuario negocio = new negocioUsuarioImpl();
            Usuario usuario = negocio.validarUsuario(dni, username, password);

            if (usuario != null) {
                
                negocioCliente negocioCliente = new negocioClienteImpl();  
                cliente cliente = negocioCliente.obtenerMiPerfil(usuario.getId()); 
               
                request.getSession().setAttribute("usuarioLogueado", usuario);
                if (cliente != null) {
                    request.getSession().setAttribute("dniCliente", cliente.getDni()); 
                }
                request.getSession().setAttribute("nombreUsuario", usuario.getTxtUsuario());
                request.getSession().setAttribute("tipoUsuario", usuario.getTipoUsuario());
                request.getSession().setAttribute("idUsuario", usuario.getId());

              
                if (usuario.getTipoUsuario() == 1) {
                    response.sendRedirect("InicioAdmin.jsp");
                } else if (usuario.getTipoUsuario() == 2) {
                    response.sendRedirect("servletInicioUser");
                } else {
                    response.sendRedirect("InicioOtroTipo.jsp");
                }
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Ocurrió un error en el sistema. Intente nuevamente.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
	}

}
