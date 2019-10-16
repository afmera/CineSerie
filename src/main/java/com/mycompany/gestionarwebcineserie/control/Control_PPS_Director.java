/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosPPS_Director;
import com.mycompany.gestionarwebcineserie.model.PPS_Director;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_PPS_Director {

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_registrar(PPS_Director entity) throws Exception {
        DatosPPS_Director.datosRegistrar(entity);
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void control_RegistrarLista(List<PPS_Director> listEntities) throws Exception {
        DatosPPS_Director.datosRegistrarLista(listEntities);
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<PPS_Director>
     * @throws Exception mensaje de error
     */
    public static List<PPS_Director> control_listar() throws Exception {
        return DatosPPS_Director.datosListar();
    }

    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     *
     * @param entity de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static PPS_Director control_leerID(PPS_Director entity) throws Exception {
        return DatosPPS_Director.datosLeerID(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_modificar(PPS_Director entity) throws Exception {
        DatosPPS_Director.datosModificar(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_eliminar(PPS_Director entity) throws Exception {
        DatosPPS_Director.datosElimnar(entity);
    }
    
    /**
     * Metodo statico para consultar la exitencia de una tupla de la tabla asignada.
     *
     * @param entity objeto de la clase determinada.
     * @return un boolean.
     * @throws Exception mensaje de error
     */
    public static boolean control_GetExitenciaTupla(PPS_Director entity) throws Exception {
        return DatosPPS_Director.datosGetExitenciaTupla(entity);
    }
    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<PPS_Director> control_GetAllTuplas(PPS_Director entity) throws Exception {
        return DatosPPS_Director.datosGetAllTuplas(entity);
    }
}
