/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.Favorita;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class DatosFavorita {

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
    public static void datosRegistrar(Favorita entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into favorita"
                    + "("
                    + "fav_calificacion,"
                    + "fav_comentario,"
                    + "ps_id"
                    + ")"
                    + "Values(?,?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getCalificacion());
            st.setString(2, entity.getComentario());
            st.setInt(3, entity.getPelicula_serie().getId());
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
     * @return de tipo List<Favorita>
     * @throws Exception mensaje de error
     */
    public static List<Favorita> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<Favorita> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "Select * from favotira;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Favorita entity = new Favorita(rs.getInt("fav_id"), rs.getInt("fav_calificacion"), rs.getString("fav_comentario"), new Pelicula_Serie(rs.getInt("ps_id")));
                l.add(entity);
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
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
    public static Favorita datosLeerID(Favorita entity) throws Exception {
        Conexion c = new Conexion();
        Favorita temp = new Favorita();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from favorita where fav_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("fav_id"));
                temp.setCalificacion(rs.getInt("fav_calificacion"));
                temp.setComentario(rs.getString("fav_comentario"));
                temp.setPelicula_serie(new Pelicula_Serie(rs.getInt("ps_id")));
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
     * Metodo statico para consultar una tupla por la llave forania.
     *
     * @param entity objeto de la clase determinada.
     * @return objeto de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static Favorita datosLeerIDByForeginKey(Favorita entity) throws Exception {
        Conexion c = new Conexion();
        Favorita temp = new Favorita();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from favorita where ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPelicula_serie().getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("fav_id"));
                temp.setCalificacion(rs.getInt("fav_calificacion"));
                temp.setComentario(rs.getString("fav_comentario"));
                temp.setPelicula_serie(new Pelicula_Serie(rs.getInt("ps_id")));
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
    public static void datosModificar(Favorita entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update favorita "
                    + "Set "
                    + "fav_calificacion=?,"
                    + "fav_comentario=?,"
                    + "ps_id=? "
                    + "Where "
                    + "fav_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getCalificacion());
            st.setString(2, entity.getComentario());
            st.setInt(3, entity.getPelicula_serie().getId());
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
     * Metodo para eliminar una tupla de la tabla por ID en la base de datos.
     *
     * @param entity del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosElimnarID(Favorita entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from favorita Where fav_id=?;";
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
     * Metodo para eliminar una tupla de la tabla por Llave Foranea en la base
     * de datos.
     *
     * @param entity del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosElimnarForeignKey(Favorita entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from favorita Where ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPelicula_serie().getId());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }

    /**
     * Metodo statico para consultar una lista de tuplas de la tabla asignada.
     *
     * @param entity objeto de la clase determinada.
     * @return un boolean.
     * @throws Exception mensaje de error
     */
    public static List<Favorita> datosGetTuplasCalificacion(Favorita entity) throws Exception {
        Conexion c = new Conexion();
        ResultSet rs;
        List<Favorita> l;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "SELECT  "
                    + "ps.ps_id,"
                    + "ps.ps_titulo,"
                    + "ps.ps_ano_lanzamiento,"
                    + "ps.ps_longitud_minutos,"
                    + "ps.ps_sinopsis,"
                    + "ps.ps_tipo,"
                    + "f.fav_id,"
                    + "f.fav_calificacion,"
                    + "f.fav_comentario "
                    + "FROM pelicula_serie ps,favorita f "
                    + "where "
                    + "f.ps_id=ps.ps_id "
                    + "AND "
                    + "f.fav_calificacion=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getCalificacion());
            rs = st.executeQuery();
            while (rs.next()) {
                l.add(
                        new Favorita(
                                rs.getInt("fav_id"),
                                rs.getInt("fav_calificacion"),
                                rs.getString("fav_comentario"),
                                new Pelicula_Serie(
                                        rs.getInt("ps_id"),
                                        rs.getString("ps_titulo"),
                                        convetirFechaString(
                                                rs.getDate("ps_ano_lanzamiento")
                                        ),
                                        rs.getString("ps_longitud_minutos"),
                                        rs.getString("ps_sinopsis"),
                                        rs.getString("ps_tipo")
                                )
                        )
                );
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Se presento un erro en la consulta en BD.\nError es : " + ex));
            System.out.println("Error " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return l;
    }
}
