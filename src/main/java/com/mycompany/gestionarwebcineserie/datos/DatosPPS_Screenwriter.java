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
            String sql = "Select * from persona_pelicula_serie_screenwriter;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                PPS_Screenwriter entity = new PPS_Screenwriter(rs.getInt("ppssw_id"), new Persona(rs.getInt("per_id")), new Pelicula_Serie(rs.getInt("ps_id")));
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
            String sql = "Select * from persona_pelicula_serie_screenwriter where ppssw_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("ppssw_id"));
                temp.setPersona(new Persona(rs.getInt("per_id")));
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
}
