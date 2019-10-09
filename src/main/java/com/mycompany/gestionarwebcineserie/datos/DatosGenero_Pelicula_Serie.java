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
import javax.swing.JOptionPane;

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
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void datosRegistrar(List<Genero_Pelicula_Serie> listEntities) throws Exception {
        Conexion c = new Conexion();
        try {
            if (listEntities.size() > 0) {
                c.conectar();
                String sql = "Insert Into genero_pelicula_serie"
                        + "("
                        + "ps_id,"
                        + "gen_id"
                        + ")"
                        + "Values(?,?);";
                for (Genero_Pelicula_Serie entity : listEntities) {
                    PreparedStatement st = c.getCn().prepareStatement(sql);
                    st.setInt(1, entity.getPelicula_serie().getId());
                    st.setInt(2, entity.getGenero().getId());
                    st.executeUpdate();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "El registro del dato solicitado es invalido");
            }
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
            String sql = "Select * from genero_pelicula_serie;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Genero_Pelicula_Serie entity = new Genero_Pelicula_Serie(rs.getInt("gps_id"), new Genero(rs.getInt("gen_id")), new Pelicula_Serie(rs.getInt("ps_id")));
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
    public static Genero_Pelicula_Serie datosLeerID(Genero_Pelicula_Serie entity) throws Exception {
        Conexion c = new Conexion();
        Genero_Pelicula_Serie temp = new Genero_Pelicula_Serie();
        ResultSet rs;
        try {
            c.conectar();
            String sql = "Select * from genero_pelicula_serie where gps_id=?;";
            PreparedStatement st = c.getCn().prepareStatement(sql);
            st.setInt(1, entity.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                temp.setId(rs.getInt("gps_id"));
                temp.setPelicula_serie(new Pelicula_Serie(rs.getInt("ps_id")));
                temp.setGenero(new Genero(rs.getInt("gen_id")));
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
}
