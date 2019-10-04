/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

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
public class DatosPersona {
    
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
    public static void datosRegistrar(Persona entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Insert Into persona "
                    + "("
                    + "per_nombre,"
                    + "per_genero,"
                    + "per_fecha_nacimiento"
                    + ")"
                    + "Values(?,?,?);";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setString(1, entity.getNombre());
            st.setString(2, entity.getGenero());
            st.setDate(3, convertirFecha(entity.getFecha_nacimiento()));
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
     * @return de tipo List<Persona>
     * @throws Exception mensaje de error
     */
    public static List<Persona> datosListar() throws Exception {
        Conexion c = new Conexion();
        List<Persona> l;
        ResultSet rs;
        try {
            l = new ArrayList<>();
            c.conectar();
            String sql = "Select * from compania_pelicula_serie;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Persona entity = new Persona(rs.getInt("per_id"),rs.getString("per_nombre"),rs.getString("per_genero"),convetirFechaString(rs.getDate("per_fecha_nacimiento")));
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
    public static Persona datosLeerID(Persona entity) throws Exception {
        Conexion c = new Conexion();
        Persona temp = new Persona();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from persona where per_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("per_id"));
                temp.setNombre(rs.getString("per_nombre"));
                temp.setGenero(rs.getString("per_genero"));
                temp.setFecha_nacimiento(convetirFechaString(rs.getDate("per_fecha_nacimiento")));
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
    public static void datosModificar(Persona entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Update persona "
                    + "Set "
                    + "per_nombre=?,"
                    + "per_genero=?,"
                    + "per_fecha_nacimiento "
                    + "Where "
                    + "per_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setString(1, entity.getNombre());
            st.setString(2, entity.getGenero());
            st.setString(3, entity.getFecha_nacimiento());
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
    public static void datosElimnar(Persona entity) throws Exception {
        Conexion c = new Conexion();
        try {
            c.conectar();
            String sql = "Delete from persona Where per_id=?;";
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
