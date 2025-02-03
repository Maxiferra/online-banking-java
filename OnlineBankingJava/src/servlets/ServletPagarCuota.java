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

import entidades.Cuota;
import entidades.Prestamo;
import entidades.Usuario;
import negocioImpl.negocioCuotaImpl;
import negocioImpl.negocioPrestamoImpl;

@WebServlet("/ServletPagarCuota")
public class ServletPagarCuota extends HttpServlet {

	public ServletPagarCuota() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
	
		
        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario() != 2) {
            response.sendRedirect("Login.jsp");
            return;
        }
		
	    int prestamoId = Integer.parseInt(request.getParameter("cuotaPago"));
	    double monto= Double.parseDouble(request.getParameter("cuotaMonto"));
	    int cuotaNumero = Integer.parseInt(request.getParameter("cuotaNumero"));
	    Date hoy = new Date(System.currentTimeMillis());
	    
	    negocioPrestamoImpl negPrestamo = new negocioPrestamoImpl();
	    negocioCuotaImpl negCuota=new negocioCuotaImpl();
	    if(negCuota.SaldoDisponible(prestamoId,monto)) {
	    	boolean cuotaPaga=negCuota.agregarCuota(prestamoId, monto,cuotaNumero,hoy);
	    	boolean cuotaRestada=negPrestamo.restarCuota(prestamoId);
	    
	   
	    
		    if(cuotaPaga) {
		    	System.out.println("Se realizo pago correctamente.");
		    	request.setAttribute("message", "Se realizo pago correctamente.");
		    	if(cuotaRestada) request.setAttribute("message", "Se realizo pago correctamente, pero no se pudo grabar el movimiento.");
		    }
		    else {
		    	System.out.println("Hubo un problema al intentar realizar su pago.");
		    	request.setAttribute("message", "Hubo un problema al intentar realizar su pago");
		    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("Prestamos.jsp");
	    dispatcher.forward(request, response);
	    }else {
	    	request.setAttribute("message", "No se realizo el pago, Saldo insuficiente");
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("Prestamos.jsp");
		    dispatcher.forward(request, response);
	    }
	}

}
