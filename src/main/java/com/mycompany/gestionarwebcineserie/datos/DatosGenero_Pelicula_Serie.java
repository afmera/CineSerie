/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.Genero;
import com.mycompany.gestionarwebcineserie.model.Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
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
public class DatosGenero_Pelicula_Serie {

    /**
     * Metodo para convertir un valor string de fecha a una valor java.sql.Date
     *
     * @param f de tipo String
     * @return un valor de tipo java.sql.Date
     * @throws ParseException mensaje de error
     */
    public static java.sql.Date convertirFecha(String f) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(sql);
        return text;
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrar(List<Genero_Pelicula_Serie> listEntities) throws Exception {
        Conexion c = new Conexion();
        try {
            if (listEntities.size() > 0) {
                c.conectar();
                String sql = "";
                ResultSet rs;
                for (Genero_Pelicula_Serie entity : listEntities) {
                    sql = "Select gps_id From genero_pelicula_serie where ps_id=? AND gen_id=?";
                    PreparedStatement st1 = c.getCn().prepareStatement(sql);
                    st1.setInt(1, entity.getPelicula_serie().getId());
                    st1.setInt(2, entity.getGenero().getId());
                    rs = st1.executeQuery();
                    boolean resultado = rs.next();
                    if (!resultado) {
                        sql = "Insert Into genero_pelicula_serie(ps_id,gen_id)Values(?,?);";
                        PreparedStatement st2 = c.getCn().prepareStatement(sql);
                        st2.setInt(1, entity.getPelicula_serie().getId());
                        st2.setInt(2, entity.getGenero().getId());
                        st2.executeUpdate();
                    }
                }
            } else {
                System.out.println("La lista de los datos esta vacia.");
            }
        } catch (Exception ex) {
            System.out.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }

    /**
     * Metodo para registrar una tupla de una tabla determinada.
     *
     * @param entity objeto de una clase determinada.
     * @throws Exception Mensaje de error.
     */
    public static void datosRegistrarTupla(Genero_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into genero_pelicula_serie"
                    + "("
                    + "ps_id,"
                    + "gen_id"
                    + ")"
                    + "Values(?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPelicula_serie().getId());
            st.setInt(2, entity.getGenero().getId());
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
     * @return de tipo List<Genero_Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static List<Genero_Pelicula_Serie> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<Genero_Pelicula_Serie> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "SELECT gps.gps_id,ps.ps_id,ps.ps_titulo,ps.ps_ano_lanzamiento,ps.ps_longitud_minutos,ps.ps_sinopsis,ps.ps_tipo,g.gen_id,g.gen_nombre FROM genero_pelicula_serie gps, pelicula_serie ps,genero g WHERE gps.gen_id=g.gen_id AND gps.ps_id=ps.ps_id;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Genero_Pelicula_Serie entity = new Genero_Pelicula_Serie(
                        rs.getInt("gps_id"), 
                        new Genero(
                                rs.getInt("gen_id"),
                                rs.getString("gen_nombre")
                        ), 
                        new Pelicula_Serie(
                                rs.getInt("ps_id"),
                                rs.getString("ps_titulo"),
                                rs.getString("ps_ano_lanzamiento"),
                                rs.getString("ps_longitud_minutos"),
                                rs.getString("ps_sinopsis"),
                                rs.getString("ps_tipo")
                        )
                );
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
     * Metodo statico para consultas una tupla de la tabla asignada.
     *
     * @param gps objeto de la clase determinada
     * @return de tipo List<Genero_Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static Genero_Pelicula_Serie datosGetExitenciaTupla(Genero_Pelicula_Serie gps) throws Exception {
        Conexion c = new Conexion();
        Genero_Pelicula_Serie entity = null;
        ResultSet rs;
        try {
            c.conectar();
            String sql = "SELECT "
                    + "gps.gps_id,"
                    + "g.gen_id,"
                    + "g.gen_nombre,"
                    + "ps.ps_id,"
                    + "ps.ps_titulo,"
                    + "ps.ps_ano_lanzamiento,"
                    + "ps.ps_longitud_minutos,"
                    + "ps.ps_sinopsis,"
                    + "ps.ps_tipo "
                    + "From genero_pelicula_serie gps,genero g,pelicula_serie ps "
                    + "WHERE "
                    + "gps.gen_id=g.gen_id "
                    + "AND "
                    + "gps.ps_id=ps.ps_id "
                    + "AND "
                    + "gps.gen_id=" + gps.getGenero().getId() + " "
                    + "And "
                    + "gps.ps_id=" + gps.getPelicula_serie().getId() + ";";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                entity = new Genero_Pelicula_Serie(
                        rs.getInt("gps_id"),
                        new Genero(
                                rs.getInt("gen_id"),
                                rs.getString("gen_nombre")
                        ),
                        new Pelicula_Serie(
                                rs.getInt("ps_id"),
                                rs.getString("ps_titulo"),
                                rs.getString("ps_ano_lanzamiento"),
                                rs.getString("ps_longitud_minutos"),
                                rs.getString("ps_sinopsis"),
                                rs.getString("ps_tipo"))
                );
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return entity;
    }

    /**
     * Metodo para consultar por ID el registro de una Tupla.
     *
     * @param entity de tipo de determinada clase.
     * @return de tipo de determinada clase.
     * @throws Exception Mensaje errado.
     */
    public static Genero_Pelicula_Serie datosLeerID(Genero_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        Genero_Pelicula_Serie temp = new Genero_Pelicula_Serie();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "SELECT * From genero_pelicula_serie gps,genero g,pelicula_serie ps "
                    + "WHERE "
                    + "gps.gen_id=g.gen_id "
                    + "AND "
                    + "gps.ps_id=ps.ps_id "
                    + "AND "
                    + "ps.ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setGenero(new Genero(rs.getInt("gen_id"), rs.getString("gen_nombre")));
                temp.setPelicula_serie(
                        new Pelicula_Serie(
                                rs.getInt("ps_id"),
                                rs.getString("ps_titulo"),
                                rs.getString("ps_ano_lanzamiento"),
                                rs.getString("ps_longitud_minutos"),
                                rs.getString("ps_sinopsis"),
                                rs.getString("ps_tipo")));
                temp.setId(rs.getInt("gps_id"));
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
    public static void datosModificar(Genero_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update genero_pelicula_serie "
                    + "Set "
                    + "ps_id=?,"
                    + "gen_id=? "
                    + "Where "
                    + "gps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPelicula_serie().getId());
            st.setInt(2, entity.getGenero().getId());
            st.setInt(3, entity.getId());
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
    public static void datosElimnar(Genero_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from genero_pelicula_serie Where gps_id=?;";
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

    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<Genero_Pelicula_Serie> DatosGetAllTuplas(Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        List<Genero_Pelicula_Serie> l;
        ResultSet rs;
        Genero_Pelicula_Serie temp;
        try {
            c.conectar();
            l = new ArrayList<>();
            String sql = "SELECT * From genero_pelicula_serie gps,genero g,pelicula_serie ps "
                    + "WHERE "
                    + "gps.gen_id=g.gen_id "
                    + "AND "
                    + "gps.ps_id=ps.ps_id "
                    + "AND "
                    + "ps.ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp = new Genero_Pelicula_Serie();
                temp.setGenero(new Genero(rs.getInt("gen_id"), rs.getString("gen_nombre")));
                temp.setPelicula_serie(
                        new Pelicula_Serie(
                                rs.getInt("ps_id"),
                                rs.getString("ps_titulo"),
                                rs.getString("ps_ano_lanzamiento"),
                                rs.getString("ps_longitud_minutos"),
                                rs.getString("ps_sinopsis"),
                                rs.getString("ps_tipo")));
                temp.setId(rs.getInt("gps_id"));
                l.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return l;
    }
}
