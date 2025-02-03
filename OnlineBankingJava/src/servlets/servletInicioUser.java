package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cuenta;
import entidades.Movimiento;
import negocio.negocioMovimiento;
import negocioImpl.negocioCuentaImpl;
import negocioImpl.negocioMovimientoImpl;

/**
 * Servlet implementation class servletInicioUser
 */
@WebServlet("/servletInicioUser")
public class servletInicioUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletInicioUser() {
        super();
        // TODO Auto-generated constructor stub
    }

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
        if (session.getAttribute("usuarioLogueado") == null) {
            
            response.sendRedirect("Login.jsp");
            return;
        }
				
	    String dniCliente = (String) session.getAttribute("dniCliente");	 
	    if (dniCliente == null || dniCliente.isEmpty()) {
	        
	        request.setAttribute("error", "No se encontró información del cliente.");	      
	        request.getRequestDispatcher("InicioUser.jsp").forward(request, response);
	        return;
	    }

	    negocioCuentaImpl cuentaNegocio = new negocioCuentaImpl();
	    ArrayList<Cuenta> listaCuentas = cuentaNegocio.obtenerCuentasPorUsuario(dniCliente);
	    request.setAttribute("listaCuentas", listaCuentas);

	    if (listaCuentas == null || listaCuentas.isEmpty()) {
	        
	        request.setAttribute("error", "No se encontraron cuentas asociadas a este cliente.");
	       
	        request.getRequestDispatcher("InicioUser.jsp").forward(request, response);
	        return;
	    }

	    // Obtiene el número de cuenta seleccionado:
	    
	    String numeroCuenta = request.getParameter("numeroCuenta");
	    Cuenta cuentaDetalles = null;
	    ArrayList<Movimiento> movimientos = new ArrayList<>();
	    
	  
	    if (numeroCuenta != null && !numeroCuenta.isEmpty()) {
	        cuentaDetalles = cuentaNegocio.obtenerCuentaPorNumero(numeroCuenta);
	    } else {
	    	
	        // Si no se seleccionó una cuenta, toma la primera de la lista de cuentas:
	        if (listaCuentas != null && !listaCuentas.isEmpty()) {
	            cuentaDetalles = listaCuentas.get(0); 
	        }
	    }

	    negocioMovimiento movimientoNegocio = new negocioMovimientoImpl();
	    	    	    
	    String aplicarFiltro = request.getParameter("aplicarFiltro");
	    String limpiarFiltro = request.getParameter("limpiarFiltro");
	    String minMontoStr = request.getParameter("minMonto");
	    String maxMontoStr = request.getParameter("maxMonto");

	    if ("true".equals(aplicarFiltro)) {
	        BigDecimal minMonto = BigDecimal.ZERO;  
	        BigDecimal maxMonto = new BigDecimal("9999999.99");

	        try {
	            // primero valido los montos :
	            if (minMontoStr != null && !minMontoStr.isEmpty()) {
	                minMonto = new BigDecimal(minMontoStr);
	                if (minMonto.compareTo(BigDecimal.ZERO) < 0) {
	                    request.setAttribute("error", "No se permiten montos negativos.");
	                    request.getRequestDispatcher("InicioUser.jsp").forward(request, response);
	                    return;
	                }
	            }

	            if (maxMontoStr != null && !maxMontoStr.isEmpty()) {
	                maxMonto = new BigDecimal(maxMontoStr);
	                if (maxMonto.compareTo(BigDecimal.ZERO) < 0) {
	                    request.setAttribute("error", "No se permiten montos negativos.");
	                    request.getRequestDispatcher("InicioUser.jsp").forward(request, response);
	                    return;
	                }
	            }
	        } catch (NumberFormatException e) {
	            request.setAttribute("error", "Los montos proporcionados no son válidos.");
	            request.getRequestDispatcher("InicioUser.jsp").forward(request, response);
	            return;
	        }

	        movimientos = movimientoNegocio.FiltroRangoPorMonto(cuentaDetalles.getNumeroCuenta(), minMonto, maxMonto);
	    } else if ("true".equals(limpiarFiltro)) {
	        movimientos = movimientoNegocio.obtenerMovimientos(cuentaDetalles.getNumeroCuenta());
	        minMontoStr = ""; 
	        maxMontoStr = "";
	    } else {
	        movimientos = movimientoNegocio.obtenerMovimientos(cuentaDetalles.getNumeroCuenta());
	    }	  
	    
	    //los envio al jsp
	    request.setAttribute("cuentaDetalles", cuentaDetalles);
	    request.setAttribute("movimientos", movimientos);
	    request.setAttribute("minMonto", minMontoStr); 
	    request.setAttribute("maxMonto", maxMontoStr); 	   
	    request.getRequestDispatcher("InicioUser.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
