/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosPPS_Screenwriter;
import com.mycompany.gestionarwebcineserie.model.PPS_Screenwriter;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_PPS_Screenwriter {

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_registrar(PPS_Screenwriter entity) throws Exception {
        DatosPPS_Screenwriter.datosRegistrar(entity);
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<PPS_Screenwriter>
     * @throws Exception mensaje de error
     */
    public static List<PPS_Screenwriter> control_listar() throws Exception {
        return DatosPPS_Screenwriter.datosListar();
    }

    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     *
     * @param entity de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static PPS_Screenwriter control_leerID(PPS_Screenwriter entity) throws Exception {
        return DatosPPS_Screenwriter.datosLeerID(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_modificar(PPS_Screenwriter entity) throws Exception {
        DatosPPS_Screenwriter.datosModificar(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_eliminar(PPS_Screenwriter entity) throws Exception {
        DatosPPS_Screenwriter.datosElimnar(entity);
    }
    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void control_RegistrarLista(List<PPS_Screenwriter> listEntities) throws Exception {
        DatosPPS_Screenwriter.datosRegistrarLista(listEntities);
    }
    
    /**
     * Metodo statico para consultar la exitencia de una tupla de la tabla asignada.
     *
     * @param entity objeto de la clase determinada.
     * @return un boolean.
     * @throws Exception mensaje de error
     */
    public static boolean control_GetExitenciaTupla(PPS_Screenwriter entity) throws Exception {
        return DatosPPS_Screenwriter.datosGetExitenciaTupla(entity);
    }
    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<PPS_Screenwriter> control_GetAllTuplas(PPS_Screenwriter entity) throws Exception {
        return DatosPPS_Screenwriter.DatosGetAllTuplas(entity);
    }
}
