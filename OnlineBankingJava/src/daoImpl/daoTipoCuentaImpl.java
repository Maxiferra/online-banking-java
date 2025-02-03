package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.daoTipoCuentas;
import entidades.TipoCuenta;

public class daoTipoCuentaImpl implements daoTipoCuentas{


	
	
	@Override
	public ArrayList<TipoCuenta> leerTodos() {
		
	
	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<TipoCuenta> lista = new ArrayList<TipoCuenta>();
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
		
		try{
			
			Statement st = connection.createStatement();				
			String query = "SELECT T.ID, T.descripcion FROM tipocuenta as T";
	        ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				
				TipoCuenta tp = new TipoCuenta();
				
				tp.setId(rs.getInt("ID"));
				tp.setDescripcion(rs.getString("descripcion"));	
				
				lista.add(tp);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		finally {
	           
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
			
		return  lista;
	}


	
	
}
