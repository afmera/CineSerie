/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
     * Metodo para registrar datos en la base de datos.
     *
     * @param ps del clase definida.
     * @throws Exception mensaje de error.
     */
    public static void registrar(Pelicula_Serie ps) throws Exception {
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
            st.executeQuery();
        } catch (Exception ex) {
            System.err.println("Error en Sql " + ex);
            throw ex;
        } finally {
            c.cerrar();
        }
    }
}
