/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.sql.Date;
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
public class DatosPeliculas_Serie {

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
     * @param ps del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrar(Pelicula_Serie ps) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into pelicula_serie(ps_titulo,ps_ano_lanzamiento,ps_longitud_minutos,ps_sinopsis,ps_tipo)Values(?,?,?,?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setString(1, ps.getTitulo());
            st.setDate(2, convertirFecha(ps.getAn_lanzamiento()));
            st.setString(3, ps.getDuracion());
            st.setString(4, ps.getSinopsis());
            st.setString(5, ps.getTipo());
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
     * @return de tipo List<Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static List<Pelicula_Serie> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<Pelicula_Serie> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "Select ps_id,ps_titulo,ps_ano_lanzamiento,ps_longitud_minutos,ps_sinopsis,ps_tipo from pelicula_serie;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Pelicula_Serie p = new Pelicula_Serie(rs.getInt("ps_id"), rs.getString("ps_titulo"), convetirFechaString(rs.getDate("ps_ano_lanzamiento")), rs.getString("ps_longitud_minutos"), rs.getString("ps_sinopsis"), rs.getString("ps_tipo"));
                l.add(p);
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
     * Metodo statico para consultar una tupla de la tabla asignada.
     *
     * @param entity obnjeto de la clase determinada.
     * @return de tipo List<Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static List<Pelicula_Serie> datosListarPorTipo(Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        List<Pelicula_Serie> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "SELECT ps.ps_titulo FROM pelicula_serie ps where ps.ps_tipo= ?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setString(1, entity.getTipo());
            rs = st.executeQuery();
            while (rs.next()) {
                Pelicula_Serie p = new Pelicula_Serie(rs.getInt("ps_id"), rs.getString("ps_titulo"), convetirFechaString(rs.getDate("ps_ano_lanzamiento")), rs.getString("ps_longitud_minutos"), rs.getString("ps_sinopsis"), rs.getString("ps_tipo"));
                l.add(p);
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
     * @param ps de tipo de determinada clase.
     * @return de tipo de determinada clase.
     * @throws Exception Mensaje errado.
     */
    public static Pelicula_Serie datosLeerID(Pelicula_Serie ps) throws Exception {
        Conexion c = new Conexion();
        Pelicula_Serie p = new Pelicula_Serie();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select ps_id,ps_titulo,ps_ano_lanzamiento,ps_longitud_minutos,ps_sinopsis,ps_tipo from pelicula_serie where ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, ps.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("ps_id"));
                p.setTitulo(rs.getString("ps_titulo"));
                p.setAn_lanzamiento(convetirFechaString(rs.getDate("ps_ano_lanzamiento")));
                p.setDuracion(rs.getString("ps_longitud_minutos"));
                p.setSinopsis(rs.getString("ps_sinopsis"));
                p.setTipo(rs.getString("ps_tipo"));
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return p;
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param ps del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosModificar(Pelicula_Serie ps) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update pelicula_serie "
                    + "Set "
                    + "ps_titulo=?,"
                    + "ps_ano_lanzamiento=?,"
                    + "ps_longitud_minutos=?,"
                    + "ps_sinopsis=?,"
                    + "ps_tipo=? "
                    + "Where "
                    + "ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setString(1, ps.getTitulo());
            st.setDate(2, convertirFecha(ps.getAn_lanzamiento()));
            st.setString(3, ps.getDuracion());
            st.setString(4, ps.getSinopsis());
            st.setString(5, ps.getTipo());
            st.setInt(6, ps.getId());
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
     * @param ps del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosElimnar(Pelicula_Serie ps) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from pelicula_serie Where ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, ps.getId());
            st.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }
}
