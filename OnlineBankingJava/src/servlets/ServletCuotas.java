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

import entidades.Cuota;
import entidades.Prestamo;
import entidades.Usuario;
import negocioImpl.negocioCuotaImpl;
import negocioImpl.negocioPrestamoImpl;

@WebServlet("/ServletCuotas")
public class ServletCuotas extends HttpServlet {

	public ServletCuotas() {
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
		negocioPrestamoImpl negPrestamo = new negocioPrestamoImpl();
	    int prestamoId = Integer.parseInt(request.getParameter("prestamoDetalles"));
	    Prestamo prestamoCuotas=negPrestamo.getPrestamoById(prestamoId);
	    
	    request.setAttribute("idPrestamo", prestamoId);
	    request.setAttribute("restantes", prestamoCuotas.getCantidadCuotas());
	    request.setAttribute("montoAux", prestamoCuotas.getImportePorMes());
	    
	    negocioCuotaImpl negCuota=new negocioCuotaImpl();
	    ArrayList<Cuota> listaCuotas=negCuota.leerCuotasPrestamoId(prestamoId);
	    double primerCuota=0;
		request.setAttribute("primerCuota", primerCuota);
	    if (listaCuotas.size()==0) {
	    	primerCuota=prestamoCuotas.getImportePedido()-(prestamoCuotas.getImportePorMes()*prestamoCuotas.getPlazoMeses())+prestamoCuotas.getImportePorMes();
	    	request.setAttribute("primerCuota", primerCuota);
			listaCuotas = new ArrayList<>();
		}
		request.setAttribute("listaCuotas", listaCuotas);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("PrestamoCuota.jsp");
		dispatcher.forward(request, response);
	}

}
