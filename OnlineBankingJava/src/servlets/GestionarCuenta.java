package servlets;
import java.util.List;  
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


import daoImpl.daoCuentaImpl;
import entidades.Admin;
import entidades.Cuenta;
import entidades.Nacionalidades;
import entidades.TipoCuenta;
import negocio.negocioNacionalidades;
import negocioImpl.negocioAdminImpl;
import negocioImpl.negocioCuentaImpl;
import negocioImpl.negocioNacionalidadesImpl;
import negocioImpl.negocioTipoCuentasImpl;



@WebServlet("/GestionarCuenta")
public class GestionarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public GestionarCuenta() {
        super();
    }

	protected ArrayList<Cuenta> CargarLista() {
		negocioCuentaImpl cuentaNegocio = new negocioCuentaImpl();
		ArrayList<Cuenta> lista = cuentaNegocio.leerTodos();

		return lista;
	}
	
	private void cargarTC(HttpServletRequest request) {
		  
		  negocioTipoCuentasImpl daoTC= new negocioTipoCuentasImpl();
	        ArrayList<TipoCuenta> listaTC = daoTC.leerTodos();
	        request.setAttribute("listaTC", listaTC);
	}
	  
	  
	private boolean validarCampos(HttpServletRequest request) {
			
		//validar dni
		String dni = request.getParameter("dni");
		if (dni == null || dni.isEmpty()) {
	 	        request.setAttribute("errorMessage", "El campo DNI es obligatorio");
	 	        return false;
		} else if (!dni.matches("\\d{7,8}")) { 
	 	        request.setAttribute("errorMessage", "El DNI debe contener 7 u 8 dígitos");
	 	        return false;
		}

		//validar fecha
		String fechaCreacion = request.getParameter("fecha");
		if (fechaCreacion == null || fechaCreacion.isEmpty()) {
	   	        request.setAttribute("errorMessage", "Debe ingresar una fecha de nacimiento válida");
	   	        return false;
		}   
		return true;
	}

	//SERVLET GET    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("Param")!=null)
		{
			request.setAttribute("listaCuentas", CargarLista());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioAdmin.jsp");
			dispatcher.forward(request, response);
		}
		if (request.getParameter("btnEliminarModal")!=null && request.getParameter("idEliminar")!=null) {
			//LLAMO AL NEGOCIO ELIMINAR CUENTA
			negocioCuentaImpl eliminar= new negocioCuentaImpl();
			
			int id = Integer.parseInt(request.getParameter("idEliminar"));
			
			int confirmado = eliminar.eliminarCuenta(id);

            request.setAttribute("cantFilas", confirmado);
            request.setAttribute("listaCuentas",CargarLista());
			RequestDispatcher rc= request.getRequestDispatcher("/InicioAdmin.jsp");
			rc.forward(request, response);
		}
		
		if (request.getParameter("btnActivarModal")!=null && request.getParameter("idActivar")!=null) {
			//LLAMO NEGOCIO PARA ACTIVAR NUEVAMENTE CUENTA
			negocioCuentaImpl activar= new negocioCuentaImpl();
			
			int idAct = Integer.parseInt(request.getParameter("idActivar"));
			int confirmado = activar.activarCuenta(idAct);

            request.setAttribute("cantFilas", confirmado);
            request.setAttribute("listaCuentas", CargarLista());
			RequestDispatcher rc= request.getRequestDispatcher("/InicioAdmin.jsp");
			rc.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		negocioCuentaImpl daoC= new negocioCuentaImpl();
		
		if (!validarCampos(request)) {
	       
	        request.setAttribute("abrirModal", true);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioAdmin.jsp");
	        dispatcher.forward(request, response);
	        return;
	    }
		
		if (request.getParameter("btnGuardar") != null) {
			try {
				Cuenta nueva = new Cuenta();
				nueva.setDNICliente(request.getParameter("txtDNICliente"));
		            
				SimpleDateFormat formato= new SimpleDateFormat("dd-mm-yyyy");
				try {
						nueva.setFechaCreacion(formato.parse("txtFechaCreacion"));
				
				} catch (ParseException e) {	
						e.printStackTrace();
				}
				
				// Convierto tipoCuenta de texto a número
				String tipoCuentaStr = request.getParameter("txtTipoCuenta");
				int tipoCuenta = 0; 
		            
				switch (tipoCuentaStr) {
		            case "Ahorro":
	                    tipoCuenta = 1; 
	                    break;
	                case "Corriente":
	                    tipoCuenta = 2; 
	                    break;
	                default:
	                	throw new IllegalArgumentException("Tipo de cuenta no reconocido: " + tipoCuentaStr);
					}
				
				nueva.setTipoCuenta(tipoCuenta);
		           
				String CBU=""; 
				String Cuenta="";
				CBU= daoC.obtenerCBUMax();
				Cuenta= daoC.obtenerCuentaMax();
				nueva.setCbu(CBU);
				nueva.setNumeroCuenta(Cuenta);
		           
				int saldo=10000;
				nueva.setSaldo(saldo);

	 // Agrega la nueva cuenta le agregue carteles depuradores para encontrar errores que tenia: 
		     int devolucion=0;
		     String mensaje="";
		     	negocioCuentaImpl neg= new negocioCuentaImpl();
		     	
		     	devolucion=neg.agregar(nueva);
		        
		     	if(devolucion==1) {
		     		
		     	mensaje="Cuenta agregada";
		     	request.setAttribute("Mensaje", mensaje);
		        RequestDispatcher rd = request.getRequestDispatcher("/InicioAdmin.jsp");
			    rd.forward(request, response);
			    return;
			    }
		     	if(devolucion==2) {
		     	
		     		mensaje="DNI no valido";
			     	request.setAttribute("Mensaje", mensaje);
			        RequestDispatcher rd = request.getRequestDispatcher("/InicioAdmin.jsp");
				    rd.forward(request, response);	
				    return;
				}
		     	if(devolucion==3) {
		     		
		     		mensaje="El cliente llego al máximo de cuentas";
			     	request.setAttribute("Mensaje", mensaje);
			        RequestDispatcher rd = request.getRequestDispatcher("/InicioAdmin.jsp");
				    rd.forward(request, response);
				    return;
		     	}
		     	else {
		     		
		     		mensaje="No se pudo agregar la cuenta";
		     		request.setAttribute("Mensaje", mensaje);
		  		    RequestDispatcher rd = request.getRequestDispatcher("/InicioAdmin.jsp");
		  		    rd.forward(request, response);
		  		  return;
		     	}
		     }
		     finally {}        
		}
		    
		    
    // VER DETALLES DE LA CUENTA PARA EDITAR
		if (request.getParameter("btnEditar") != null) {
			try {
				negocioCuentaImpl neg= new negocioCuentaImpl();
		    					    	
				int ID= Integer.parseInt(request.getParameter("id"));
			    	
				if(ID!=-1) {
			    	Cuenta cta= new Cuenta();
			    	
					cta.setDNICliente(request.getParameter("DNICliente"));
			    	cta.setFechaCreacion(Date.valueOf(request.getParameter("FechaCreacion")));
			    	// Convierto tipoCuenta de texto a número
			    	String tipoCuentaStr = request.getParameter("idTipoCuenta");
			    	int tipoCuenta = 0; 
			    	
			    	switch (tipoCuentaStr) {
			                case "Ahorro":
			                    tipoCuenta = 1; 
			                    break;
			                case "Corriente":
			                    tipoCuenta = 2; 
			                    break;
			                default:
			                    throw new IllegalArgumentException("Tipo de cuenta no reconocido: " + tipoCuentaStr);
			    	}
			    	
			    	cta.setTipoCuenta(tipoCuenta);
			    	cta.setNumeroCuenta(request.getParameter("numeroCuenta"));
			    	cta.setCbu(request.getParameter("CBU"));
			    	cta.setSaldo(Double.valueOf(request.getParameter("Saldo")));
			    	cta.setEliminado(Boolean.valueOf(request.getParameter("eliminado")));

			    	int confirmado = neg.editarCuenta(cta);

			    	request.setAttribute("cantFilas", confirmado);
			    	request.setAttribute("listaAdmins", CargarLista()); 
	                    
			    	RequestDispatcher rc= request.getRequestDispatcher("/InicioAdmin.jsp");
			    	rc.forward(request, response);
				}
			}
			finally{}
		}	
	}
}

		    
    
		