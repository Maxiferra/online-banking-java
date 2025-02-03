package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;

/**
 * Servlet implementation class servletInicioAdmin
 */
@WebServlet("/servletInicioAdmin")
public class servletInicioAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public servletInicioAdmin() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()==2) {
            response.sendRedirect("Login.jsp");
            return;
        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()!= 1) {
            response.sendRedirect("Login.jsp");
            return;
        }
	}

}
