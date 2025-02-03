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

import entidades.Cuenta;
import entidades.TipoCuenta;
import entidades.Usuario;
import negocio.NegocioMetodos;
import negocioImpl.negocioCuentaImpl;
import negocioImpl.negocioMetodosImpl;
import negocioImpl.negocioTipoCuentasImpl;

/**
 * Servlet implementation class ServletAgregarCuenta
 */
@WebServlet("/ServletAgregarCuenta")
public class ServletAgregarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletAgregarCuenta() {
        super();
    
    }
    
	protected ArrayList<TipoCuenta> CargarTC() {
		
		negocioTipoCuentasImpl TC= new negocioTipoCuentasImpl();
	
		ArrayList<TipoCuenta> listaTC = TC.leerTodos();
		return listaTC;
	 }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()!=1) {
            response.sendRedirect("Login.jsp");
            return;
        }
		
		request.setAttribute("listaTC",CargarTC());
	    
		 String abrir = request.getParameter("openModal");
		    if ("true".equals(abrir)) {
		        request.setAttribute("abrirModal", true);
		    }		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarCuentas.jsp");
	    dispatcher.forward(request, response);									
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		 
		HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()!=1) {
            response.sendRedirect("Login.jsp");
            return;
        }
		
		
		
		negocioCuentaImpl neg= new negocioCuentaImpl();
		
	    if (request.getParameter("btnGuardar") != null) {
	       
	    	NegocioMetodos negocioMetodos = new negocioMetodosImpl();

	        if (!negocioMetodos.validarCamposCuentas(request)) {	            
	        	 request.setAttribute("cantFilas", -3);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarCuentas.jsp");
	            dispatcher.forward(request, response);
	            return;
	        } 

	    	
	            
	          Cuenta nueva = new Cuenta();
	          int filas=0;	            	      
	          int existe=0;  
	          int cant=0;
	          
	          String dni= request.getParameter("txtDNICliente");
	          existe=neg.buscarDNI(dni);
	          cant=neg.cantidadCuentas(dni);
	         if(existe>0) {
	        	 
	        	 if(cant<3) {	        		 	        		 
	        		 //DNI  
	   	          nueva.setDNICliente(request.getParameter("txtDNICliente"));	        
	   	          //FECHA CREACION
	              nueva.setFechaCreacion(Date.valueOf(request.getParameter("txtFechaCreacion")));   	          
	               //TIPO DE CUENTA    	    
	   	          nueva.setTipoCuenta(Integer.parseInt(request.getParameter("txtTipoCuenta")));   	            
	   	          //VALIDACION CBU Y NUMERO CUENTA
	   	           String CBU=""; 
	   	           String Cuenta="";
	   	           CBU= neg.obtenerCBUMax();
	   	           Cuenta= neg.obtenerCuentaMax();
	   	           nueva.setCbu(CBU);
	   	           System.out.println(CBU);
	   	           nueva.setNumeroCuenta(Cuenta);
	   	           
	   	           int saldo=10000;
	   	           
	   	           //SALDO
	   	           nueva.setSaldo(saldo);
	   	           
	   	           //ELIMINADO
	   	           nueva.setEliminado(false);

	   	        int resultado = neg.agregar(nueva);
	   	        if (resultado > 0) { 
	   	            filas = 1; 
	   	        } else {
	   	            filas = 0;
	   	        }
	   	    } else {
	   	        filas = 2; 
	   	    }
	   	} else {
	   	    filas = 4; 
	   	} 
	         
	         request.setAttribute("cantFilas", filas);
	     	RequestDispatcher rd = request.getRequestDispatcher("GestionarCuentas.jsp");
		    rd.forward(request, response);      
	    }	
	}
}

	
	

