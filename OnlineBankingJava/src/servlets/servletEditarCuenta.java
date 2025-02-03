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
import negocioImpl.negocioCuentaImpl;
import negocioImpl.negocioTipoCuentasImpl;

@WebServlet("/servletEditarCuenta")
public class servletEditarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	
	
    public servletEditarCuenta() {
        super();
      
    }

    
	protected ArrayList<TipoCuenta> CargarTC() {
		
		negocioTipoCuentasImpl TC= new negocioTipoCuentasImpl();
	
		ArrayList<TipoCuenta> listaTC = TC.leerTodos();
		return listaTC;
    
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()==2) {
            response.sendRedirect("Login.jsp");
            return;
        }
		
		if (request.getParameter("btnEditarCuenta") != null && request.getParameter("id") != null) {
		       
		    
		    		negocioCuentaImpl neg= new negocioCuentaImpl();
		    					    	
			    	int ID= Integer.parseInt(request.getParameter("id"));
			    	
			    	if(ID!=-1) {
			    		
			    	Cuenta cta= new Cuenta();
			    	
			    	 cta.setId(Integer.parseInt(request.getParameter("id")));
			    	
			    	 cta.setFechaCreacion(Date.valueOf(request.getParameter("txtFechaCreacion")));

			    	 cta.setSaldo(Double.valueOf(request.getParameter("txtSaldo")));
       
			        System.out.println("id: " + cta.getId());
			        System.out.println("FECHA: " + cta.getFechaCreacion());
			        System.out.println("SALDO" + cta.getSaldo());
			       

			            int confirmado = neg.editarCuenta(cta);
			            int filas = 0; 

			            if (confirmado == 1) {
			                filas = 1; 
			            } else {
			                filas = 0; 
			            }
			            
			            request.setAttribute("cantFilas", filas);
		            
				    	RequestDispatcher rc= request.getRequestDispatcher("GestionarCuentas.jsp");
	        			rc.forward(request, response);         
	                			            
			    	}			    				    	       		
		 		}					
	}

}
