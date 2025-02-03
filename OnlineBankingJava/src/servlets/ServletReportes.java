package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.daoCliente;
import dao.daoPrestamo;
import negocio.negocioPrestamo;
import negocioImpl.negocioPrestamoImpl;
import negocioImpl.negocioReporteImpl;
import daoImpl.daoPrestamoImpl;
import daoImpl.daoClienteImpl;
import entidades.Prestamo;
import entidades.Usuario;
import entidades.cliente;

@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletReportes() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()!=1) {
            response.sendRedirect("Login.jsp");
            return;
        }
		
		
		negocioReporteImpl negReporte = new negocioReporteImpl();
		int reporteSelected = Integer.parseInt(request.getParameter("tipoReporte"));
		String reported="";
		if (reporteSelected==4||reporteSelected==7) {
			Date fechaInicio=Date.valueOf(request.getParameter("fechaInicio"));
			Date fechaFin=Date.valueOf(request.getParameter("fechaFin"));
			reported=negReporte.generarReporteFechas(reporteSelected, fechaFin, fechaInicio);				

			if(fechaInicio.compareTo(fechaFin) < 0) {
				reported=negReporte.generarReporteFechas(reporteSelected, fechaInicio, fechaFin);
			}
			else {
				reported=negReporte.generarReporteFechas(reporteSelected, fechaFin, fechaInicio);				
			}
		}
		else {
			reported=negReporte.generarReporte(reporteSelected);	
		}
		
		request.setAttribute("reported", reported);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Reportes.jsp");
		dispatcher.forward(request, response);
	}

}