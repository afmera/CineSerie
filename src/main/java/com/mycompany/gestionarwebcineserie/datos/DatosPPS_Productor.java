/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.PPS_Productor;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Persona;
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
public class DatosPPS_Productor {
    
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
    public static void datosRegistrar(PPS_Productor entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into persona_pelicula_serie_productor "
                    + "("
                    + "per_id,"
                    + "ps_id,"
                    + "ppsp_tipo"
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
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrarLista(List<PPS_Productor> listEntities) throws Exception {
        Conexion c = new Conexion();
        try {
            if (listEntities.size() > 0) {
                c.conectar();
                String sql = "";
                ResultSet rs;
                for (PPS_Productor entity : listEntities) {
                    sql = "Select ppsp_id From persona_pelicula_serie_productor where ps_id=? AND per_id=?";
                    PreparedStatement st1 = c.getCn().prepareStatement(sql);
                    st1.setInt(1, entity.getPelicula_serie().getId());
                    st1.setInt(2, entity.getPersona().getId());
                    rs = st1.executeQuery();
                    boolean resultado = rs.next();
                    if (!resultado) {
                        sql = "Insert Into persona_pelicula_serie_productor(ps_id,per_id,ppsp_tipo)Values(?,?,?);";
                        PreparedStatement st2 = c.getCn().prepareStatement(sql);
                        st2.setInt(1, entity.getPelicula_serie().getId());
                        st2.setInt(2, entity.getPersona().getId());
                        st2.setString(3, entity.getTipo());
                        st2.executeUpdate();
                    }
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "La lista esta vacia."));
                System.out.println("La lista de los datos esta vacia.");
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Se presento un error en el registro en BD.\nError es : " + ex));
            System.out.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<PPS_Productor>
     * @throws Exception mensaje de error
     */
    public static List<PPS_Productor> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<PPS_Productor> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "Select "
                    + "ppsp.ppsp_id,"
                    + "ppsp.ppsp_tipo,"
                    
                    + "p.per_id,"
                    + "p.per_nombre,"
                    + "p.per_genero,"
                    + "p.per_fecha_nacimiento,"
                    + "ps.ps_id,"
                    + "ps.ps_titulo,"
                    + "ps.ps_ano_lanzamiento,"
                    + "ps.ps_longitud_minutos,"
                    + "ps.ps_sinopsis,"
                    + "ps.ps_tipo "
                    + "from "
                    + "persona_pelicula_serie_productor ppsp,"
                    + "persona p,"
                    + "pelicula_serie ps "
                    + "where "
                    + "ppsp.per_id=p.per_id "
                    + "AND "
                    + "ppsp.ps_id=ps.ps_id;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                PPS_Productor entity = new PPS_Productor(
                        rs.getInt("ppsp_id"), 
                        new Persona(
                                rs.getInt("per_id"),
                                rs.getString("per_nombre"),
                                rs.getString("per_genero"),
                                convetirFechaString(rs.getDate("per_fecha_nacimiento"))
                        ), 
                        new Pelicula_Serie(
                                rs.getInt("ps_id"),
                                rs.getString("ps_titulo"),
                                rs.getString("ps_ano_lanzamiento"),
                                rs.getString("ps_longitud_minutos"),
                                rs.getString("ps_sinopsis"),
                                rs.getString("ps_tipo")
                        ), 
                        rs.getString("ppsp_tipo"));
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
    public static PPS_Productor datosLeerID(PPS_Productor entity) throws Exception {
        Conexion c = new Conexion();
        PPS_Productor temp = new PPS_Productor();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from persona_pelicula_serie_productor where ppsp_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("ppsp_id"));
                temp.setPersona(new Persona(rs.getInt("per_id")));
                temp.setPelicula_serie(new Pelicula_Serie(rs.getInt("ps_id")));
                temp.setTipo(rs.getString("ppsp_tipo"));
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
    public static void datosModificar(PPS_Productor entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update persona_pelicula_serie_productor "
                    + "Set "
                    + "per_id=?,"
                    + "ps_id=?,"
                    + "ppsp_tipo "
                    + "Where "
                    + "ppsp_id=?;";
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
    public static void datosElimnar(PPS_Productor entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from persona_pelicula_serie_productor Where ppsp_id=?;";
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
     * Metodo statico para consultar la exitencia de una tupla de la tabla asignada.
     *
     * @param entity objeto de la clase determinada.
     * @return un boolean.
     * @throws Exception mensaje de error
     */
    public static boolean datosGetExitenciaTupla(PPS_Productor entity) throws Exception {
        Conexion c = new Conexion();
        ResultSet rs;
        boolean resultado = false;
        try {
            c.conectar();
            String sql = "SELECT "
                    + "* "
                    + "from "
                    + "persona_pelicula_serie_productor ppsp,"
                    + "persona p,"
                    + "pelicula_serie ps "
                    + "where "
                    + "ppsp.per_id=p.per_id "
                    + "AND "
                    + "ppsp.ps_id=ps.ps_id "
                    + "AND "
                    + "ppsp.per_id=? "
                    + "AND "
                    + "ppsp.ps_id=? "
                    + "AND "
                    + "ppsp.ppsp_tipo=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
            st.setString(3, entity.getTipo());
            rs = st.executeQuery();
            resultado = rs.next();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Se presento un erro en la consulta en BD.\nError es : " + ex));
            System.out.println("Error " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
        return resultado;
    }
    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<PPS_Productor> DatosGetAllTuplas(PPS_Productor entity) throws Exception {
        Conexion c = new Conexion();
        List<PPS_Productor> l;
        ResultSet rs;
        PPS_Productor temp;
        try {
            c.conectar();
            l = new ArrayList<>();
            String sql = "SELECT "
                    + "ppsp.ppsp_id,"
                    + "ppsp.ppsp_tipo,"
                    
                    + "p.per_id,"
                    + "p.per_nombre,"
                    + "p.per_genero,"
                    + "p.per_fecha_nacimiento,"
                    + "ps.ps_id,"
                    + "ps.ps_titulo,"
                    + "ps.ps_ano_lanzamiento,"
                    + "ps.ps_longitud_minutos,"
                    + "ps.ps_sinopsis,"
                    + "ps.ps_tipo "
                    + "from "
                    + "persona_pelicula_serie_productor ppsp,"
                    + "persona p,"
                    + "pelicula_serie ps "
                    + "where "
                    + "ppsp.per_id=p.per_id "
                    + "AND "
                    + "ppsp.ps_id=ps.ps_id "
                    + "AND "
                    + "ppsp.per_id=? "
                    + "AND "
                    + "ppsp.ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp = new PPS_Productor();
                temp.setPersona(new Persona(
                        rs.getInt("per_id"),
                        rs.getString("per_nombre"),
                        rs.getString("per_genero"),
                        rs.getString("per_fecha_nacimiento")
                ));
                temp.setPelicula_serie(
                        new Pelicula_Serie(
                                rs.getInt("ps_id"),
                                rs.getString("ps_titulo"),
                                rs.getString("ps_ano_lanzamiento"),
                                rs.getString("ps_longitud_minutos"),
                                rs.getString("ps_sinopsis"),
                                rs.getString("ps_tipo")));
                temp.setId(rs.getInt("ppsp_id"));
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
