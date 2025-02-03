package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.daoAdmin;
import dao.daoUsuario;
import daoImpl.daoAdminImpl;
import daoImpl.daoUsuarioImpl;
import entidades.Admin;
import entidades.Prestamo;
import excepciones.PrestamoExcepcion;
import excepciones.Validaciones;
import negocioImpl.negocioPrestamoImpl;

@WebServlet("/ServletAgregarPrestamo")
public class ServletAgregarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAgregarPrestamo() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws PrestamoExcepcion, ServletException, IOException {
			String cuotasStr="";
			String montoStr="";
	        String cuenta ="";
	        double monto=0;
	        int cuotas=0;
			cuotasStr=request.getParameter("txtcuotas");
			montoStr=request.getParameter("txtmonto").replace(',','.');

	        Validaciones validar= new Validaciones();
	        if (!validar.isNumeroPositivoD(montoStr)) {
	        	request.setAttribute("message", "El monto no puede ser cero o estar vacio. Verifique su ingreso");
				RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
	            rd.forward(request, response);
	            return;
			}
			if(!validar.isNumeroPositivoI(cuotasStr)) {
	        	request.setAttribute("message", "Debe indicar la cantidad de cuotas a pagar.Verifique su ingreso");
				RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
	            rd.forward(request, response);
	            return;
			}
			
	        
			monto = Double.parseDouble(montoStr);
	        if(monto==0||monto<1) {
	        	request.setAttribute("message", "El monto no puede ser cero o estar vacio. Verifique su ingreso");
				RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
	            rd.forward(request, response);
	            return;
	        }
			cuotas = Integer.parseInt(cuotasStr);
			if(cuotas==0) {
	        	request.setAttribute("message", "Debe indicar la cantidad de cuotas a pagar.Verifique su ingreso");
				RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
	            rd.forward(request, response);
	            return;
			}
			
	        
	        cuenta = request.getParameter("txtcuenta");
	        
	        negocioPrestamoImpl negPrestamo = new negocioPrestamoImpl();
	        System.out.println("YYY"+monto+cuotas+cuenta);
	        boolean solicitado=negPrestamo.altaPrestamo(monto,cuotas,cuenta);
	        if(solicitado) {
	        	System.out.println("Se realizo la solicitud correctamente.");
	        	request.setAttribute("message", "Su solicitud fue procesada. La misma debe ser aprobada por un administrador.");
	        }
	        else {
	        	System.out.println("No ser pudo procesar la solicitud.");
	        	request.setAttribute("message", "No se pudo procesar su solicitud. Intente nuevamente.");
	        }
            RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
            rd.forward(request, response);
	}
}