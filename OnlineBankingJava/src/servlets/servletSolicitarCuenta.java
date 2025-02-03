package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import entidades.Cuenta;
import entidades.Usuario;
import entidades.cliente;
import negocio.negocioCliente;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioCuentaImpl;


@WebServlet("/servletSolicitarCuenta")
public class servletSolicitarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public servletSolicitarCuenta() {
        super();
       
    }

    public void destroy() {
        AbandonedConnectionCleanupThread.uncheckedShutdown();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		destroy();
		
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
	
		
        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario() != 2) {
            response.sendRedirect("Login.jsp");
            return;
        }

		int idUsuario = usuarioLogueado.getId();
		 System.out.println(idUsuario);
	
	    negocioClienteImpl dao= new negocioClienteImpl();
	  
	
	    String dni = dao.ObtenerDniCliente(idUsuario);
	    System.out.println("Cuenta: dni=" + dni);
	    
	    negocioCuentaImpl neg = new negocioCuentaImpl();
	    
	    if (request.getParameter("btnQuiero1") != null) {
	        
	    	Cuenta nueva = new Cuenta();
	        
	    	int filas = 0;
	        int existe = 0;  
	        int cant = 0;
	    
	        existe = neg.buscarDNI(dni);
	        cant = neg.cantidadCuentas(dni);
	        System.out.println(existe);
	        System.out.println(cant);

	        if (existe > 0) {
	            if (cant < 3) {
	                // Proceso de creación de la cuenta
	                nueva.setDNICliente(dni);
	                Date fecha = new Date();  
	                SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
	                String fechaString = f1.format(fecha);
	                nueva.setFechaCreacion(java.sql.Date.valueOf(fechaString));

	                nueva.setTipoCuenta(1);
	                String CBU = neg.obtenerCBUMax();
	                String Cuenta = neg.obtenerCuentaMax();
	                nueva.setCbu(CBU);
	                nueva.setNumeroCuenta(Cuenta);
	                
	                int saldo = 10000;
	                nueva.setSaldo(saldo);
	                nueva.setEliminado(false);

	                System.out.println(nueva.getDNICliente());
	                System.out.println(nueva.getFechaCreacion());
	                System.out.println(nueva.getTipoCuenta());
	                System.out.println(CBU);
	                System.out.println(nueva.getNumeroCuenta());
	                System.out.println(nueva.getSaldo());
	                System.out.println(nueva.getEliminado());
	                
	                
	                neg.agregar(nueva);
	                filas = 1; 
	               
	                request.setAttribute("CantFilas", filas);
	              
	            } else {
	                filas = 2; 
	                request.setAttribute("CantFilas", filas);
	               
	            }
	        }	      
	        RequestDispatcher rd = request.getRequestDispatcher("SolicitarCuenta.jsp");
	        rd.forward(request, response);	    	        
	    }
	    
	    if (request.getParameter("btnQuiero2") != null) {
		                   
	    	Cuenta nueva = new Cuenta();
	        int filas = 0;
	       
	        int existe = 0;  
	        int cant = 0;
	    
	        existe = neg.buscarDNI(dni);
	        cant = neg.cantidadCuentas(dni);

	        if (existe > 0) {
	            if (cant < 3) {
	                // Proceso de creación de la cuenta
	                nueva.setDNICliente(dni);
	                Date fecha = new Date();  
	                SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
	                String fechaString = f1.format(fecha);
	                nueva.setFechaCreacion(java.sql.Date.valueOf(fechaString));

	                nueva.setTipoCuenta(2);
	                String CBU = neg.obtenerCBUMax();
	                String Cuenta = neg.obtenerCuentaMax();
	                nueva.setCbu(CBU);
	                nueva.setNumeroCuenta(Cuenta);
	                
	                int saldo = 10000;
	                nueva.setSaldo(saldo);
	                nueva.setEliminado(false);

	                System.out.println(nueva.getDNICliente());
	                System.out.println(nueva.getFechaCreacion());
	                System.out.println(nueva.getTipoCuenta());
	                System.out.println(CBU);
	                System.out.println(nueva.getNumeroCuenta());
	                System.out.println(nueva.getSaldo());
	                System.out.println(nueva.getEliminado());
	                                
	                neg.agregar(nueva);
	                filas = 1;                
	                request.setAttribute("CantFilas", filas);	              
	            } else {
	                filas = 2; 
	                request.setAttribute("CantFilas", filas);	               
	            }
	        }	   
	        RequestDispatcher rd = request.getRequestDispatcher("SolicitarCuenta.jsp");
	        rd.forward(request, response);
	        
	    }    
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
