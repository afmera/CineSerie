/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosPeliculas_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_Peliculas_Serie {

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param ps del tipo de la clase definada.
     */
    public static void control_registrar(Pelicula_Serie ps) throws Exception {
        DatosPeliculas_Serie.datosRegistrar(ps);
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static List<Pelicula_Serie> control_listar() throws Exception {
        return DatosPeliculas_Serie.datosListar();
    }

    /**
     * Metodo statico para consultar una tupla de la tabla asignada.
     *
     * @param entity obnjeto de la clase determinada.
     * @return de tipo List<Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static List<Pelicula_Serie> control_listarPorTipo(Pelicula_Serie entity) throws Exception {
        return DatosPeliculas_Serie.datosListarPorTipo(entity);
    }

    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     *
     * @param ps de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static Pelicula_Serie control_leerID(Pelicula_Serie ps) throws Exception {
        return DatosPeliculas_Serie.datosLeerID(ps);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param ps del tipo de la clase definada.
     */
    public static void control_modificar(Pelicula_Serie ps) throws Exception {
        DatosPeliculas_Serie.datosModificar(ps);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param ps del tipo de la clase definada.
     */
    public static void control_eliminar(Pelicula_Serie ps) throws Exception {
        DatosPeliculas_Serie.datosElimnar(ps);
    }

    /**
     * Metodo statico para consultar el ultimo registro de la tabla determinda.
     *
     * @return objeto de la clase determinda.
     * @throws Exception Mensaje de Error.
     */
    public static Pelicula_Serie control_LastInsertID() throws Exception {
        return DatosPeliculas_Serie.datosLastInsertID();
    }
}
