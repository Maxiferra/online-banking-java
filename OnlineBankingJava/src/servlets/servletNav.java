package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servletNav")
public class servletNav extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public servletNav() {
        super();
    }
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		 // Deshabilita la cache en el navegador
		
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        
        // Invalidar la sesion y eliminar los atributos asociados del usuario
        request.getSession().removeAttribute("usuarioLogueado");
        request.getSession().removeAttribute("nombreUsuario");
        request.getSession().invalidate();

      
        response.sendRedirect("Login.jsp");
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
