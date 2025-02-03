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

import entidades.Admin;
import entidades.Usuario;
import negocio.NegocioMetodos;
import negocio.negocioUsuario;
import negocioImpl.negocioAdminImpl;
import negocioImpl.negocioMetodosImpl;
import negocioImpl.negocioUsuarioImpl;

@WebServlet("/servletAgregarAdmin")
public class servletAgregarAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletAgregarAdmin() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession(false);
    	Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || usuarioLogueado.getTipoUsuario()==2) {
            response.sendRedirect("Login.jsp");
            return;
        }
		
		if(request.getParameter("Param")!=null)
		{
			request.setAttribute("listaAdmins", CargarLista());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/GestionarAdmin.jsp");
			dispatcher.forward(request, response);
		}
		
		if (request.getParameter("btnEliminarModal")!=null && request.getParameter("idUsuarioDelete")!=null) {
			negocioAdminImpl eliminar= new negocioAdminImpl();
			int idDel = Integer.parseInt(request.getParameter("idUsuarioDelete"));
			int confirmado = eliminar.eliminarxAdmin(idDel);

            request.setAttribute("cantFilas", confirmado);
			request.setAttribute("listaAdmins", CargarLista());
			RequestDispatcher rc= request.getRequestDispatcher("/GestionarAdmin.jsp");
			rc.forward(request, response);
		}
		
		if (request.getParameter("btnActivarModal")!=null && request.getParameter("idUsuarioActivar")!=null) {
			negocioAdminImpl activar = new negocioAdminImpl();
			int idAct = Integer.parseInt(request.getParameter("idUsuarioActivar"));
			int confirmado = activar.ActivarxAdmin(idAct);

            request.setAttribute("cantFilas", confirmado);
			request.setAttribute("listaAdmins", CargarLista());
			RequestDispatcher rc= request.getRequestDispatcher("/GestionarAdmin.jsp");
			rc.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws   ServletException, IOException
	{
		int filasUsuario = 0;

		if (request.getParameter("btnCrearAdmin") != null) {	        
	        String nombreUsuario = request.getParameter("usuario");
	        String password = request.getParameter("clave");

	        negocioUsuarioImpl negocioUsuario = new negocioUsuarioImpl();
	        NegocioMetodos negocioMetodos = new negocioMetodosImpl();
	        /*Primero agrego el usuario con la informacion del Formulario*/
	        if (negocioUsuario.obtenerIdUsuario(nombreUsuario) != -1) {
	        	String mensajeError =  "El nombre de usuario ya se encuentra registrado ,Por favor elige otro";
	            request.setAttribute("errorMessage", mensajeError);
	            request.setAttribute("listaAdmins", CargarLista());
	            RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarAdmin.jsp");
	            dispatcher.forward(request, response);
	            return;
	        } 		     
	        if(!negocioMetodos.validarCamposRegistrarAdmin(request)) {
	            request.setAttribute("listaAdmins", CargarLista());
	            RequestDispatcher dispatcher = request.getRequestDispatcher("GestionarAdmin.jsp");
	            dispatcher.forward(request, response);
	            return;
	        }
	            filasUsuario = negocioUsuario.altaUsuario(nombreUsuario, password, 1);
	            if (filasUsuario > 0) {		                
	                int idUsuario = negocioUsuario.obtenerIdUsuario(nombreUsuario);

	               if (idUsuario != -1) {
	                Admin admin = new Admin();
	                admin.setDni(request.getParameter("dni"));
	                admin.setCuil(request.getParameter("cuil"));
	                admin.setNombre(request.getParameter("nombre"));
	                admin.setApellido(request.getParameter("apellido"));
	                admin.setEmail(request.getParameter("correo"));
	                admin.setTelefono(request.getParameter("telefono"));
	                admin.setIdUsuario(idUsuario);

	                negocioAdminImpl NegocioAdmin = new negocioAdminImpl();
	               int confirmado = NegocioAdmin.agregar(admin);

                    request.setAttribute("cantFilas", confirmado);
                    request.setAttribute("listaAdmins", CargarLista()); 
                    
                    RequestDispatcher rc= request.getRequestDispatcher("/GestionarAdmin.jsp");
        			rc.forward(request, response);
                
            } 
        }  
	}
		if (request.getParameter("btnEditarCuenta") != null) {	        
				negocioAdminImpl NegocioAdmin = new negocioAdminImpl();
				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				negocioUsuarioImpl negocioUsuario = new negocioUsuarioImpl();
				
					negocioUsuario.editarClave(idUsuario, request.getParameter("claveEditar"));
		               if (idUsuario != -1) {
		                Admin admin = new Admin();
		                admin.setNombre(request.getParameter("nombre"));
		                admin.setApellido(request.getParameter("apellido"));
		                admin.setEmail(request.getParameter("correo"));
		                admin.setTelefono(request.getParameter("telefono"));
		                admin.setIdUsuario(idUsuario);

		                int confirmado = NegocioAdmin.editarAdmin(admin);

	                    request.setAttribute("cantFilas", confirmado);
	                    request.setAttribute("listaAdmins", CargarLista()); 
	                    
	                    RequestDispatcher rc= request.getRequestDispatcher("/GestionarAdmin.jsp");
	        			rc.forward(request, response);
				
				
                
            } 
        }  

		
	}
	
	protected ArrayList<Admin> CargarLista() {
		negocioAdminImpl NegocioAdmin = new negocioAdminImpl();
		ArrayList<Admin> lista = NegocioAdmin.leerTodos();

		return lista;
	}
}


