/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosGenero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_Genero_Pelicula_Serie {

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param listEntities lista de una clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_registrar(List<Genero_Pelicula_Serie> listEntities) throws Exception {
        DatosGenero_Pelicula_Serie.datosRegistrar(listEntities);
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<Genero_Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static List<Genero_Pelicula_Serie> control_listar() throws Exception {
        return DatosGenero_Pelicula_Serie.datosListar();
    }

    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     *
     * @param entity de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static Genero_Pelicula_Serie control_leerID(Genero_Pelicula_Serie entity) throws Exception {
        return DatosGenero_Pelicula_Serie.datosLeerID(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_modificar(Genero_Pelicula_Serie entity) throws Exception {
        DatosGenero_Pelicula_Serie.datosModificar(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_eliminar(Genero_Pelicula_Serie entity) throws Exception {
        DatosGenero_Pelicula_Serie.datosElimnar(entity);
    }

    /**
     * Metodo statico para consultas una tupla de la tabla asignada.
     *
     * @param entity objeto de la clase determinada
     * @return objeto de la clase determinada
     * @throws java.lang.Exception Mensaje de error.
     */
    public static Genero_Pelicula_Serie control_GetExitenciaTupla(Genero_Pelicula_Serie entity) throws Exception {
        return DatosGenero_Pelicula_Serie.datosGetExitenciaTupla(entity);
    }

    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<Genero_Pelicula_Serie> control_GetAllTuplas(Pelicula_Serie entity) throws Exception {
        return DatosGenero_Pelicula_Serie.DatosGetAllTuplas(entity);
    }

    /**
     * Metodo para registrar una tupla de una tabla determinada.
     *
     * @param entity objeto de una clase determinada.
     * @throws Exception Mensaje de error.
     */
    public static void control_RegistrarTupla(Genero_Pelicula_Serie entity) throws Exception {
        DatosGenero_Pelicula_Serie.datosRegistrarTupla(entity);
    }
}
