package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.daoProvincias;
import entidades.Provincias;

public class daoProvinciasImpl implements daoProvincias{


	
	@Override
    public ArrayList<Provincias> obtenerTodas() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Provincias> lista = new ArrayList<Provincias>();
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        try {
        
			Statement st = connection.createStatement();
            String query = "SELECT * FROM Provincias";
            ResultSet resultado = st.executeQuery(query);

            while (resultado.next()) {
                Provincias provincias = new Provincias();
                provincias.setId(resultado.getInt("ID"));
                provincias.setNombre(resultado.getString("nombre"));
                
                lista.add(provincias);
            }
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return lista;
    }

    @Override
    public Provincias obtenerPorId(int id) {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        Provincias provincias = null;
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        try {          
        	
            String query = "SELECT * FROM Provincias WHERE ID = ?";
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id); 
            
            ResultSet resultado = ps.executeQuery();

            if(resultado.next()) {
                provincias = new Provincias();
                provincias.setId(resultado.getInt("ID"));
                provincias.setNombre(resultado.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provincias;
    }
}
