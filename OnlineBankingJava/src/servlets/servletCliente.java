package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.daoCliente;
import dao.daoUsuario;
import daoImpl.daoClienteImpl;
import daoImpl.daoUsuarioImpl;
import entidades.cliente;
import negocio.NegocioMetodos;
import negocio.negocioCliente;
import negocio.negocioUsuario;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioMetodosImpl;
import negocioImpl.negocioUsuarioImpl;

@WebServlet("/servletCliente")
public class servletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public servletCliente() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NegocioMetodos negocioMetodos = new negocioMetodosImpl();

	    negocioMetodos.cargarListas(request);
	    
		 String abrir = request.getParameter("openModal");
		    if ("true".equals(abrir)) {
		        request.setAttribute("abrirModal", true);
		    }
		
		    RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
		    dispatcher.forward(request, response);				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 if (request.getParameter("btnCrearCuenta") != null) {
	            NegocioMetodos negocioMetodos = new negocioMetodosImpl();

	            if (!negocioMetodos.validarCamposRegistrar(request)) {	               
	                request.setAttribute("cantFilas", -3);
	                RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
	                dispatcher.forward(request, response);
	                return;
	            }

	            String nombreUsuario = request.getParameter("usuario");
	            String password = request.getParameter("clave");

	            negocioUsuario negocioUsuario = new negocioUsuarioImpl();
	            negocioCliente negocioCliente = new negocioClienteImpl();

	            if (negocioUsuario.obtenerIdUsuario(nombreUsuario) != -1) {
	                request.setAttribute("cantFilas", -3);
	                request.setAttribute("errorMessage", "El nombre de usuario ya existe, por favor elige otro");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
	                dispatcher.forward(request, response);
	                return;
	            }

	            int filasUsuario = negocioUsuario.altaUsuario(nombreUsuario, password, 2);
	            if (filasUsuario > 0) {
	                int idUsuario = negocioUsuario.obtenerIdUsuario(nombreUsuario);
	                if (idUsuario != -1) {
	                    cliente cliente = new cliente();
	                    cliente.setDni(request.getParameter("dni"));
	                    cliente.setCuil(request.getParameter("cuil"));
	                    cliente.setNombre(request.getParameter("nombre"));
	                    cliente.setApellido(request.getParameter("apellido"));
	                    cliente.setSexo(request.getParameter("genero"));
	                    cliente.setIdNacionalidad(Integer.parseInt(request.getParameter("nacionalidad")));
	                    cliente.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));
	                    cliente.setDireccion(request.getParameter("direccion"));
	                    cliente.setIdLocalidad(Integer.parseInt(request.getParameter("localidad")));
	                    cliente.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));
	                    cliente.setEmail(request.getParameter("correo"));
	                    cliente.setTelefono(request.getParameter("telefono"));
	                    cliente.setIdUsuario(idUsuario);

	                    int filasCliente = negocioCliente.altaCliente(cliente);
	                    if (filasCliente > 0) {
	                        request.setAttribute("cantFilas", 1);
	                    } else {
	                        negocioUsuario.eliminarUsuario(idUsuario);
	                        request.setAttribute("cantFilas", -3);
	                        request.setAttribute("errorMessage", "Error al registrar el cliente, El usuario creado fue eliminado");
	                    }
	                }
	            } else {
	                request.setAttribute("cantFilas", -3);
	                request.setAttribute("errorMessage", "Error al crear el usuario, Intente nuevamente");
	            }

	            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
	}


