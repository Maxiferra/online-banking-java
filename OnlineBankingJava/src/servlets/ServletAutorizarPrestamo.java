package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Prestamo;
import entidades.Usuario;
import negocioImpl.negocioPrestamoImpl;

@WebServlet("/ServletAutorizarPrestamo")
public class ServletAutorizarPrestamo extends HttpServlet {
    
    public ServletAutorizarPrestamo() {
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()==2) {
            response.sendRedirect("Login.jsp");
            return;
        }
    	
	        try {
	        	negocioPrestamoImpl negPrestamo = new negocioPrestamoImpl();
	        	int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
	        	Prestamo prestamo = negPrestamo.getPrestamoById(idPrestamo);
	        	negPrestamo.cambiarEstadoPrestamo(prestamo,1);
	            response.sendRedirect("ServletMostrarPrestamos");
	        } catch(Exception e) {
	            e.printStackTrace();
        }
    }

}