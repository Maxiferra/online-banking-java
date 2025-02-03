package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.daoNacionalidades;

import java.sql.Statement;


import entidades.Nacionalidades;

public class daoNacionalidadesImpl implements daoNacionalidades {

  
    
    @Override
    public ArrayList<Nacionalidades> obtenerTodas() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Nacionalidades> lista = new ArrayList<Nacionalidades>();
        
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        try {
         
            Statement st = connection.createStatement();
            String query = "SELECT * FROM Nacionalidades";
            ResultSet resultado = st.executeQuery(query);

            while (resultado.next()) {
                Nacionalidades nacionalidad = new Nacionalidades();
                nacionalidad.setId(resultado.getInt("ID"));
                nacionalidad.setNombre(resultado.getString("nombre"));

                lista.add(nacionalidad);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           
        }
        return lista;
    }

    @Override
    public Nacionalidades obtenerPorId(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Nacionalidades nacionalidad = null;
        Connection connection = null;
        conexion cn = new conexion();
        connection = cn.obtenerConexion();
        try {
           
            String query = "SELECT * FROM Nacionalidades WHERE ID = ?";
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet resultado = ps.executeQuery();

            if (resultado.next()) {
                nacionalidad = new Nacionalidades();
                nacionalidad.setId(resultado.getInt("ID"));
                nacionalidad.setNombre(resultado.getString("nombre"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nacionalidad;
    }

}
