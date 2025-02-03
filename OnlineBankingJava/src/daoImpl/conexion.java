package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {

	private String host="jdbc:mysql://localhost:3306/Banco";
	private String user="root";
	private String pass="root";
	
	
	protected Connection cn=null;
	
	public Connection obtenerConexion() {
		
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn= DriverManager.getConnection(host, user, pass);
            return cn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
}
