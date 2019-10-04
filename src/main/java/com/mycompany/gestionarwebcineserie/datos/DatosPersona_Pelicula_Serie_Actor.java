/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.PPS_Actor;
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
public class DatosPersona_Pelicula_Serie_Actor {
    
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
    public static void datosRegistrar(PPS_Actor entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into persona_pelicula_serie_actor "
                    + "("
                    + "per_id,"
                    + "ps_id,"
                    + "ppsa_tipo,"
                    + "ppsa_tiempo_pantalla"
                    + ")"
                    + "Values(?,?,?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
            st.setString(3, entity.getTipo());
            st.setString(4, entity.getTiempo_pantalla());
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
     * @return de tipo List<PPS_Actor>
     * @throws Exception mensaje de error
     */
    public static List<PPS_Actor> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<PPS_Actor> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "Select * from persona_pelicula_serie_actor;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                PPS_Actor entity = new PPS_Actor(rs.getInt("ppsa_id"), new Persona(rs.getInt("per_id")), new Pelicula_Serie(rs.getInt("ps_id")),rs.getString("ppsa_tipo"),rs.getString("ppsa_tiempo_pantalla"));
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
    public static PPS_Actor datosLeerID(PPS_Actor entity) throws Exception {
        Conexion c = new Conexion();
        PPS_Actor temp = new PPS_Actor();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from persona_pelicula_serie_actor where ppsa_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("ppsa_id"));
                temp.setPersona(new Persona(rs.getInt("per_id")));
                temp.setPelicula_serie(new Pelicula_Serie(rs.getInt("ps_id")));
                temp.setTipo(rs.getString("ppsa_tipo"));
                temp.setTiempo_pantalla(rs.getString("ppsa_tempo_pantalla"));
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
    public static void datosModificar(PPS_Actor entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update persona_pelicula_serie_actor "
                    + "Set "
                    + "per_id=?,"
                    + "ps_id=?,"
                    + "ppsa_tipo,"
                    + "ppsa_tiempo_pantalla "
                    + "Where "
                    + "ppsa_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
            st.setString(3, entity.getTipo());
            st.setString(4, entity.getTiempo_pantalla());
            st.setInt(5, entity.getId());
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
    public static void datosElimnar(PPS_Actor entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from persona_pelicula_serie_actor Where ppsa_id=?;";
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
