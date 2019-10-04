/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.PPS_Director;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Persona;
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
public class DatosPersona_Pelicula_Serie_Director {
    
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
     * @param entity del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrar(PPS_Director entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into persona_pelicula_serie_director "
                    + "("
                    + "per_id,"
                    + "ps_id,"
                    + "ppsd_tipo"
                    + ")"
                    + "Values(?,?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
            st.setString(3, entity.getTipo());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<PPS_Director>
     * @throws Exception mensaje de error
     */
    public static List<PPS_Director> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<PPS_Director> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "Select * from persona_pelicula_serie_director;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                PPS_Director entity = new PPS_Director(rs.getInt("ppsa_id"), new Persona(rs.getInt("per_id")), new Pelicula_Serie(rs.getInt("ps_id")),rs.getString("ppsd_tipo"));
                l.add(entity);
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
     * @param entity de tipo de determinada clase.
     * @return de tipo de determinada clase.
     * @throws Exception Mensaje errado.
     */
    public static PPS_Director datosLeerID(PPS_Director entity) throws Exception {
        Conexion c = new Conexion();
        PPS_Director temp = new PPS_Director();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from persona_pelicula_serie_director where ppsd_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("ppsd_id"));
                temp.setPersona(new Persona(rs.getInt("per_id")));
                temp.setPelicula_serie(new Pelicula_Serie(rs.getInt("ps_id")));
                temp.setTipo(rs.getString("ppsd_tipo"));
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return temp;
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param entity del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosModificar(PPS_Director entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update persona_pelicula_serie_director "
                    + "Set "
                    + "per_id=?,"
                    + "ps_id=?,"
                    + "ppsd_tipo "
                    + "Where "
                    + "ppsd_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
            st.setString(3, entity.getTipo());
            st.setInt(4, entity.getId());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }

    /**
     * Metodo para eliminar una tupla de la tabla en la base de datos.
     *
     * @param entity del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosElimnar(PPS_Director entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from persona_pelicula_serie_director Where ppsd_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }
}
