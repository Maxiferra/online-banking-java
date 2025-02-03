package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

import dao.daoLocalidades;
import entidades.Localidades;

public class daoLocalidadesImpl implements daoLocalidades {


    @Override
    
    public ArrayList<Localidades> obtenerTodas() {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

        ArrayList<Localidades> lista = new ArrayList<Localidades>();
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        
        try {
            
            Statement st = connection.createStatement();
            String query = "SELECT * FROM Localidades";
            ResultSet resultado = st.executeQuery(query);
                    

            while (resultado.next()) {
                Localidades localidad = new Localidades();
                localidad.setId(resultado.getInt("ID"));
                localidad.setNombre(resultado.getString("nombre"));
                localidad.setIdProvincia(resultado.getInt("idProvincia"));
                
                lista.add(localidad);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return lista;
    }

    @Override
    public Localidades obtenerPorId(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Localidades localidad = null;
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        try {
          
            String query = "SELECT * FROM Localidades WHERE ID = ?";
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id); 
            
            ResultSet resultado = ps.executeQuery();

            if(resultado.next()) {
                localidad = new Localidades();
                localidad.setId(resultado.getInt("ID"));
                localidad.setNombre(resultado.getString("nombre"));
                localidad.setIdProvincia(resultado.getInt("idProvincia"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localidad;
    }
}
