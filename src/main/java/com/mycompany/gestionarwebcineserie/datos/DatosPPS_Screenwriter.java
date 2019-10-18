/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.PPS_Screenwriter;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class DatosPPS_Screenwriter {

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param entity del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrar(PPS_Screenwriter entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into persona_pelicula_serie_screenwriter "
                    + "("
                    + "per_id,"
                    + "ps_id"
                    + ")"
                    + "Values(?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
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
     * @return de tipo List<PPS_Screenwriter>
     * @throws Exception mensaje de error
     */
    public static List<PPS_Screenwriter> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<PPS_Screenwriter> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "Select "
                    + "ppssw.ppssw_id,"
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
                    + "persona_pelicula_serie_screenwriter ppssw,"
                    + "persona p,"
                    + "pelicula_serie ps "
                    + "where "
                    + "ppssw.per_id=p.per_id "
                    + "AND "
                    + "ppssw.ps_id=ps.ps_id;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                PPS_Screenwriter entity = new PPS_Screenwriter(
                        rs.getInt("ppssw_id"),
                        new Persona(
                                rs.getInt("per_id"),
                                rs.getString("per_nombre"),
                                rs.getString("per_genero"),
                                rs.getString("per_fecha_nacimiento")
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
    public static PPS_Screenwriter datosLeerID(PPS_Screenwriter entity) throws Exception {
        Conexion c = new Conexion();
        PPS_Screenwriter temp = new PPS_Screenwriter();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select "
                    + "ppssw.ppssw_id,"
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
                    + "persona_pelicula_serie_screenwriter ppssw,"
                    + "persona p,"
                    + "pelicula_serie ps "
                    + "where "
                    + "ppssw.per_id=p.per_id "
                    + "AND "
                    + "ppssw.ps_id=ps.ps_id "
                    + "AND "
                    + "ppssw_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("ppssw_id"));
                temp.setPersona(
                        new Persona(
                                rs.getInt("per_id"),
                                rs.getString("per_nombre"),
                                rs.getString("per_genero"),
                                rs.getString("per_fecha_nacimiento")
                        )
                );
                temp.setPelicula_serie(
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
        return temp;
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param entity del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void datosModificar(PPS_Screenwriter entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update persona_pelicula_serie_screenwriter "
                    + "Set "
                    + "per_id=?,"
                    + "ps_id=? "
                    + "Where "
                    + "ppssw_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
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
    public static void datosElimnar(PPS_Screenwriter entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from persona_pelicula_serie_screenwriter Where ppssw_id=?;";
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
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrarLista(List<PPS_Screenwriter> listEntities) throws Exception {
        Conexion c = new Conexion();
        try {
            if (listEntities.size() > 0) {
                c.conectar();
                String sql = "";
                ResultSet rs;
                for (PPS_Screenwriter entity : listEntities) {
                    sql = "Select ppssw_id From persona_pelicula_serie_screenwriter where ps_id=? AND per_id=?";
                    PreparedStatement st1 = c.getCn().prepareStatement(sql);
                    st1.setInt(1, entity.getPelicula_serie().getId());
                    st1.setInt(2, entity.getPersona().getId());
                    rs = st1.executeQuery();
                    boolean resultado = rs.next();
                    if (!resultado) {
                        sql = "Insert Into persona_pelicula_serie_screenwriter(ps_id,per_id)Values(?,?);";
                        PreparedStatement st2 = c.getCn().prepareStatement(sql);
                        st2.setInt(1, entity.getPelicula_serie().getId());
                        st2.setInt(2, entity.getPersona().getId());
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
     * Metodo statico para consultar la exitencia de una tupla de la tabla
     * asignada.
     *
     * @param entity objeto de la clase determinada.
     * @return un boolean.
     * @throws Exception mensaje de error
     */
    public static boolean datosGetExitenciaTupla(PPS_Screenwriter entity) throws Exception {
        Conexion c = new Conexion();
        ResultSet rs;
        boolean resultado = false;
        try {
            c.conectar();
            String sql = "SELECT "
                    + "* "
                    + "from "
                    + "persona_pelicula_serie_screenwriter ppssw,"
                    + "persona p,"
                    + "pelicula_serie ps "
                    + "where "
                    + "ppssw.per_id=p.per_id "
                    + "AND "
                    + "ppssw.ps_id=ps.ps_id "
                    + "AND "
                    + "ppssw.per_id=? "
                    + "AND "
                    + "ppssw.ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
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
    public static List<PPS_Screenwriter> DatosGetAllTuplas(PPS_Screenwriter entity) throws Exception {
        Conexion c = new Conexion();
        List<PPS_Screenwriter> l;
        ResultSet rs;
        PPS_Screenwriter temp;
        try {
            c.conectar();
            l = new ArrayList<>();
            String sql = "SELECT "
                    + "ppssw.ppssw_id,"
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
                    + "persona_pelicula_serie_screenwrite ppssw,"
                    + "persona p,"
                    + "pelicula_serie ps "
                    + "where "
                    + "ppssw.per_id=p.per_id "
                    + "AND "
                    + "ppssw.ps_id=ps.ps_id "
                    + "AND "
                    + "ppssw.per_id=? "
                    + "AND "
                    + "ppssw.ps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getPersona().getId());
            st.setInt(2, entity.getPelicula_serie().getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp = new PPS_Screenwriter();
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
                temp.setId(rs.getInt("ppsa_id"));
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
