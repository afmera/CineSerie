/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.Compania;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class DatosCompania {
    
    /**
     * Metodo para convertir un valor string de fecha a una valor java.sql.Date
     *
     * @param f de tipo String
     * @return un valor de tipo java.sql.Date
     * @throws ParseException mensaje de error
     */
    public static java.sql.Date convertirFecha(String f) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = format.parse(f);
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        return sql;
    }

    /**
     * Metodo para convertir una fecha con java.sql.Date sql a String
     *
     * @param sql de tipo java.sql.Date sql
     * @return de tipo String.
     */
    public static String convetirFechaString(java.sql.Date sql) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String text = df.format(sql);
        return text;
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param com del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrar(Compania com) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into compania"
                    + "("
                    + "com_nombre,"
                    + "com_majos,"
                    + "com_nacionalidad,"
                    + "com_streaming"
                    + ")"
                    + "Values(?,?,?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setString(1, com.getNombre());
            st.setString(2, com.getMajos());
            st.setString(3, com.getNacionalidad());
            st.setString(4, com.getStreaming());
            st.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<Compania>
     * @throws Exception mensaje de error
     */
    public static List<Compania> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<Compania> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "Select * from compania;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Compania com = new Compania(rs.getInt("com_id"), rs.getString("com_nombre"),(rs.getString("com_majos")), rs.getString("com_nacionalidad"), rs.getString("com_streaming"));
                l.add(com);
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return l;
    }

    /**
     * Metodo para consultar por ID el registro de una Tupla.
     *
     * @param com de tipo de determinada clase.
     * @return de tipo de determinada clase.
     * @throws Exception Mensaje errado.
     */
    public static Compania datosLeerID(Compania com) throws Exception {
        Conexion c = new Conexion();
        Compania temp = new Compania();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from compania where com_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, com.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("com_id"));
                temp.setNombre(rs.getString("com_nombre"));
                temp.setMajos(rs.getString("com_majos"));
                temp.setNacionalidad(rs.getString("com_nacionalidad"));
                temp.setStreaming(rs.getString("com_streaming"));
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return temp;
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param ps del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosModificar(Compania ps) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update compania "
                    + "Set "
                    + "com_nombre=?,"
                    + "com_majos=?,"
                    + "com_nacionalidad=?,"
                    + "com_streaming=?"
                    + "Where "
                    + "com_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setString(1, ps.getNombre());
            st.setString(2, ps.getMajos());
            st.setString(3, ps.getNacionalidad());
            st.setString(4, ps.getStreaming());
            st.setInt(5, ps.getId());
            st.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }

    /**
     * Metodo para eliminar una tupla de la tabla en la base de datos.
     *
     * @param com del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosElimnar(Compania com) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from compania Where com_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, com.getId());
            st.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }
}
