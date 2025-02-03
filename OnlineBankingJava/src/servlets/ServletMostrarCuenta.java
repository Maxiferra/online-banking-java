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

import entidades.Cuenta;
import entidades.TipoCuenta;
import entidades.Usuario;
import entidades.cliente;
import negocioImpl.negocioCuentaImpl;
import negocioImpl.negocioTipoCuentasImpl;


@WebServlet("/ServletMostrarCuenta")
public class ServletMostrarCuenta extends HttpServlet {

	private static final long serialVersionUID = 1L;
  
    public ServletMostrarCuenta() {
        super();
       
    }

    
	protected ArrayList<Cuenta> CargarLista() {
		negocioCuentaImpl cuentaNegocio = new negocioCuentaImpl();
		ArrayList<Cuenta> lista = cuentaNegocio.leerTodos();

		return lista;
	}
	
	protected ArrayList<TipoCuenta> CargarTC() {
		
		negocioTipoCuentasImpl TC= new negocioTipoCuentasImpl();
	
		ArrayList<TipoCuenta> listaTC = TC.leerTodos();
		return listaTC;
    
	}
		   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()!= 1) {
            response.sendRedirect("Login.jsp");
            return;
        }
				  
			if (request.getParameter("btnActualizar") != null) {
	           
				ArrayList<Cuenta> listacuentas= CargarLista();
				request.setAttribute("listaCuentas",listacuentas);
	        }
	
		
			if (request.getParameter("btnEliminarModal") != null && request.getParameter("idEliminar") != null) {
			    negocioCuentaImpl eliminar = new negocioCuentaImpl();
			    int idEliminar = Integer.parseInt(request.getParameter("idEliminar"));
			    
			    	if (eliminar.validarDeuda(idEliminar)) {
			 	        request.setAttribute("cantFilas", -4);
			 	        
			 	        request.setAttribute("listaCuentas",CargarLista());
						request.setAttribute("listaTC",CargarTC());
						
			 	        RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarCuentas.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
			    
			    int filas;
			    if (eliminar.eliminarCuenta(idEliminar) == 1) {
			        filas = 1; 
			    } else {
			        filas = 0; 
			    }
			    request.setAttribute("cantFilas", filas);
			}
			
			if (request.getParameter("btnActivarModal") != null && request.getParameter("idActivar") != null) {
			    negocioCuentaImpl activar = new negocioCuentaImpl();
			    int idAct = Integer.parseInt(request.getParameter("idActivar"));
			    int filas;
			    if (activar.activarCuenta(idAct) == 1) {
			        filas = 1; 
			    } else {
			        filas = 0;
			    }
			    request.setAttribute("cantFilas", filas);
			}
						
			request.setAttribute("listaCuentas",CargarLista());
			request.setAttribute("listaTC",CargarTC());
			RequestDispatcher rt= request.getRequestDispatcher("GestionarCuentas.jsp");
			rt.forward(request, response);
	
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
