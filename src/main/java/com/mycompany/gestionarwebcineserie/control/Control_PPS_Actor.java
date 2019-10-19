/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosPPS_Actor;
import com.mycompany.gestionarwebcineserie.model.PPS_Actor;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_PPS_Actor {

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_registrar(PPS_Actor entity) throws Exception {
        DatosPPS_Actor.datosRegistrar(entity);
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void control_RegistrarLista(List<PPS_Actor> listEntities) throws Exception {
        DatosPPS_Actor.datosRegistrarLista(listEntities);
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<PPS_Actor>
     * @throws Exception mensaje de error
     */
    public static List<PPS_Actor> control_listar() throws Exception {
        return DatosPPS_Actor.datosListar();
    }

    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     *
     * @param entity de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static PPS_Actor control_leerID(PPS_Actor entity) throws Exception {
        return DatosPPS_Actor.datosLeerID(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_modificar(PPS_Actor entity) throws Exception {
        DatosPPS_Actor.datosModificar(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_eliminar(PPS_Actor entity) throws Exception {
        DatosPPS_Actor.datosElimnar(entity);
    }

    /**
     * Metodo statico para consultas una tupla de la tabla asignada.
     *
     * @param entity objeto de la clase determinada.
     * @return objeto de la clase determinada.
     * @throws Exception mensaje de error
     */
//    public static PPS_Actor control_GetExitenciaTupla(PPS_Actor entity) throws Exception {
    public static boolean control_GetExitenciaTupla(PPS_Actor entity) throws Exception {
        return DatosPPS_Actor.datosGetExitenciaTupla(entity);
    }

    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<PPS_Actor> control_GetAllTuplas(PPS_Actor entity) throws Exception {
        return DatosPPS_Actor.DatosGetAllTuplas(entity);
    }

    /**
     * Metodo statico para consultar todas las peliculas desde un valor
     * determindo por parametro.
     *
     * @param entity de la clase determinda.
     * @return una lista de la clase determinda.
     * @throws Exception Mensaje de error.
     */
    public static List<PPS_Actor> control_ListarNombreActor(PPS_Actor entity) throws Exception {
        return DatosPPS_Actor.datosListarNombreActor(entity);
    }
}
