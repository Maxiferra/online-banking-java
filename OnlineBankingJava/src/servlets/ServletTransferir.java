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
import entidades.Usuario;
import entidades.cliente;
import excepciones.Validaciones;
import negocio.negocioCliente;
import negocioImpl.negocioClienteImpl;
import negocioImpl.negocioCuentaImpl;
import negocioImpl.negocioPrestamoImpl;
import negocioImpl.negocioTransferenciaImpl;


@WebServlet("/ServletTransferir")
public class ServletTransferir  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletTransferir() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		int tipoTransferencia=Integer.parseInt(request.getParameter("txttipotransferencia"));
		
		String cuentaOrigen=request.getParameter("txtCuentaOrigen");
		String cuentaDestino=request.getParameter("txtCuentaDestino");
		String montoStr=request.getParameter("txtmonto").replace(',','.');
		negocioTransferenciaImpl negocioTransferencia = new negocioTransferenciaImpl();

        Validaciones validar= new Validaciones();
        if (!validar.isNumeroPositivoD(montoStr)) {
        	request.setAttribute("message", "El monto no puede ser cero, estar vacio o contener letras y/o simbolos. Verifique su ingreso");
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
            rd.forward(request, response);
            return;
		}
        Double monto=Double.parseDouble(montoStr);
        if (monto<1) {
        	request.setAttribute("message", "El monto no puede ser cero, estar vacio o contener letras y/o simbolos. Verifique su ingreso");
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
            rd.forward(request, response);
            return;
		}
        if (cuentaOrigen.equals(cuentaDestino)) {
        	request.setAttribute("message", "No puede transferir a la misma cuenta");
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
            rd.forward(request, response);
            return;
		}
				
		String transferido="";
		
		if(tipoTransferencia==0){
			//transferenciaPropia
			transferido=negocioTransferencia.agregarTransferenciaPropia(cuentaOrigen,cuentaDestino,monto);
        	request.setAttribute("message", transferido);
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
            rd.forward(request, response);
            return;
		}
		else {
			//transferenciaACbu
	        if (!validar.CBUFormatOK(cuentaDestino)||!negocioTransferencia.existeCBU(cuentaDestino)) {
	        	request.setAttribute("message", "El CBU ingresado no corresponde a una cuenta activa o existente. Verifique el numero de ingresado.");
				RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
	            rd.forward(request, response);
	            return;
			}
			transferido=negocioTransferencia.agregarTransferenciaTercero(cuentaOrigen,cuentaDestino,monto);
			request.setAttribute("message", transferido);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencias.jsp");
			dispatcher.forward(request, response);
		}	
	}

}