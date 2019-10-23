/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosCompania_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Compania_Pelicula_Serie;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_Compania_Pelicula_Serie {

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_registrar(Compania_Pelicula_Serie entity) throws Exception {
        DatosCompania_Pelicula_Serie.datosRegistrar(entity);
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<Compania_Pelicula_Serie>
     * @throws Exception mensaje de error
     */
    public static List<Compania_Pelicula_Serie> control_listar() throws Exception {
        return DatosCompania_Pelicula_Serie.datosListar();
    }

    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     *
     * @param entity de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static Compania_Pelicula_Serie control_leerID(Compania_Pelicula_Serie entity) throws Exception {
        return DatosCompania_Pelicula_Serie.datosLeerID(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_modificar(Compania_Pelicula_Serie entity) throws Exception {
        DatosCompania_Pelicula_Serie.datosModificar(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_eliminar(Compania_Pelicula_Serie entity) throws Exception {
        DatosCompania_Pelicula_Serie.datosElimnar(entity);
    }
    
    /**
     * Metodo statico para consultas una tupla de la tabla asignada.
     *
     * @param entity objeto de la clase determinada
     * @return de tipo Compania_Pelicula_Serie
     * @throws Exception mensaje de error
     */
    public static Compania_Pelicula_Serie control_GetExitenciaTupla(Compania_Pelicula_Serie entity) throws Exception {
        return DatosCompania_Pelicula_Serie.datosGetExitenciaTupla(entity);
    }
    
    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<Compania_Pelicula_Serie> control_GetAllTuplas(Compania_Pelicula_Serie entity) throws Exception {
        return DatosCompania_Pelicula_Serie.datosGetAllTuplas(entity);
    }
    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void control_RegistrarLista(List<Compania_Pelicula_Serie> listEntities) throws Exception {
        DatosCompania_Pelicula_Serie.datosRegistrarLista(listEntities);
    }
}
