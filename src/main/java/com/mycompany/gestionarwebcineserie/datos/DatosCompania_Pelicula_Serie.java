/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.Compania;
import com.mycompany.gestionarwebcineserie.model.Compania_Pelicula_Serie;
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
public class DatosCompania_Pelicula_Serie {

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
    public static void datosRegistrar(Compania_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into compania_pelicula_serie"
                    + "("
                    + "com_id,"
                    + "ps_id"
                    + ")"
                    + "Values(?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getCompania().getId());
            st.setInt(2, entity.getPelicual_serie().getId());
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
     * @return de tipo List<Compania_Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static List<Compania_Pelicula_Serie> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<Compania_Pelicula_Serie> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "SELECT cps.cps_id,c.com_id,c.com_nombre,c.com_majos,c.com_nacionalidad,c.com_streaming,ps.ps_id,ps.ps_titulo,ps.ps_ano_lanzamiento,ps.ps_longitud_minutos,ps.ps_sinopsis,ps.ps_tipo "
                    + "From compania_pelicula_serie cps,compania c,pelicula_serie ps "
                    + "WHERE "
                    + "cps.com_id=c.com_id "
                    + "AND "
                    + "cps.ps_id=ps.ps_id;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Compania_Pelicula_Serie entity = new Compania_Pelicula_Serie(
                        rs.getInt("cps_id"),
                        new Compania(
                                rs.getInt("com_id"),
                                rs.getString("com_nombre"),
                                rs.getString("com_majos"),
                                rs.getString("com_nacionalidad"),
                                rs.getString("com_streaming")
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
     * Metodo para consultar por ID el registro de una Tupla.
     *
     * @param entity de tipo de determinada clase.
     * @return de tipo de determinada clase.
     * @throws Exception Mensaje errado.
     */
    public static Compania_Pelicula_Serie datosLeerID(Compania_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        Compania_Pelicula_Serie temp = new Compania_Pelicula_Serie();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from compania_pelicula_serie where cps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("cps_id"));
                temp.setCompania(new Compania(rs.getInt("com_id")));
                temp.setPelicual_serie(new Pelicula_Serie(rs.getInt("ps_id")));
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
    public static void datosModificar(Compania_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update compania_pelicula_serie "
                    + "Set "
                    + "com_id=?,"
                    + "ps_id=? "
                    + "Where "
                    + "cps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getCompania().getId());
            st.setInt(2, entity.getPelicual_serie().getId());
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
    public static void datosElimnar(Compania_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from compania_pelicula_serie Where cps_id=?;";
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
     * Metodo statico para consultas una tupla de la tabla asignada.
     *
     * @param entity objeto de la clase determinada
     * @return de tipo Compania_Pelicula_Serie
     * @throws Exception mensaje de error
     */
    public static Compania_Pelicula_Serie datosGetExitenciaTupla(Compania_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        Compania_Pelicula_Serie objEntity = null;
        ResultSet rs;
        try {
            c.conectar();
            String sql = "SELECT "
                    + "cps.cps_id,"
                    + "c.com_id,"
                    + "c.com_nombre,"
                    + "c.com_majos,"
                    + "c.com_nacionalidad,"
                    + "c.com_streaming,"
                    + "ps.ps_id,"
                    + "ps.ps_titulo,"
                    + "ps.ps_ano_lanzamiento,"
                    + "ps.ps_longitud_minutos,"
                    + "ps.ps_sinopsis,"
                    + "ps.ps_tipo "
                    + "From compania_pelicula_serie cps,compania c,pelicula_serie ps "
                    + "WHERE "
                    + "cps.com_id=c.com_id "
                    + "AND "
                    + "cps.ps_id=ps.ps_id "
                    + "AND "
                    + "cps.com_id=" + entity.getCompania().getId() + " "
                    + "And "
                    + "cps.ps_id=" + entity.getPelicual_serie().getId() + ";";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                objEntity = new Compania_Pelicula_Serie(
                        rs.getInt("cps_id"),
                        new Compania(
                                rs.getInt("com_id"),
                                rs.getString("com_nombre"),
                                rs.getString("com_majos"),
                                rs.getString("com_nacionalidad"),
                                rs.getString("com_streaming")
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
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return objEntity;
    }

    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<Compania_Pelicula_Serie> datosGetAllTuplas(Compania_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        List<Compania_Pelicula_Serie> l;
        ResultSet rs;
        Compania_Pelicula_Serie temp;
        try {
            c.conectar();
            l = new ArrayList<>();
            String sql = "SELECT * From compania_pelicula_serie cps,compania c,pelicula_serie ps "
                    + "WHERE "
                    + "cps.com_id=c.com_id "
                    + "AND "
                    + "cps.ps_id=ps.ps_id "
                    + "AND "
                    + "ps.ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPelicual_serie().getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp = new Compania_Pelicula_Serie();
                temp.setCompania(
                        new Compania(
                                rs.getInt("com_id"),
                                rs.getString("com_nombre"),
                                rs.getString("com_majos"),
                                rs.getString("com_nacionalidad"),
                                rs.getString("com_streaming")
                        )
                );
                temp.setPelicual_serie(
                        new Pelicula_Serie(
                                rs.getInt("ps_id"),
                                rs.getString("ps_titulo"),
                                rs.getString("ps_ano_lanzamiento"),
                                rs.getString("ps_longitud_minutos"),
                                rs.getString("ps_sinopsis"),
                                rs.getString("ps_tipo")
                        )
                );
                temp.setId(rs.getInt("cps_id"));
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

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrarLista(List<Compania_Pelicula_Serie> listEntities) throws Exception {
        Conexion c = new Conexion();
        try {
            if (listEntities.size() > 0) {
                c.conectar();
                String sql = "";
                ResultSet rs;
                for (Compania_Pelicula_Serie entity : listEntities) {
                    sql = "Select cps_id From compania_pelicula_serie where ps_id=? AND com_id=?";
                    PreparedStatement st1 = c.getCn().prepareStatement(sql);
                    st1.setInt(1, entity.getPelicual_serie().getId());
                    st1.setInt(2, entity.getCompania().getId());
                    rs = st1.executeQuery();
                    boolean resultado = rs.next();
                    if (!resultado) {
                        sql = "Insert Into compania_pelicula_serie(ps_id,com_id)Values(?,?);";
                        PreparedStatement st2 = c.getCn().prepareStatement(sql);
                        st2.setInt(1, entity.getPelicual_serie().getId());
                        st2.setInt(2, entity.getCompania().getId());
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
}
