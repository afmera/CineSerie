/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosPPS_Productor;
import com.mycompany.gestionarwebcineserie.model.PPS_Productor;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_PPS_Productor {

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_registrar(PPS_Productor entity) throws Exception {
        DatosPPS_Productor.datosRegistrar(entity);
    }

    /**
     * Metodo para registrar datos en la base de datos.
     *
     * @param listEntities lista de la clase determinida.
     * @throws Exception mensaje de error.
     */
    public static void control_RegistrarLista(List<PPS_Productor> listEntities) throws Exception {
        DatosPPS_Productor.datosRegistrarLista(listEntities);
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<PPS_Productor>
     * @throws Exception mensaje de error
     */
    public static List<PPS_Productor> control_listar() throws Exception {
        return DatosPPS_Productor.datosListar();
    }

    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     *
     * @param entity de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static PPS_Productor control_leerID(PPS_Productor entity) throws Exception {
        return DatosPPS_Productor.datosLeerID(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_modificar(PPS_Productor entity) throws Exception {
        DatosPPS_Productor.datosModificar(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_eliminar(PPS_Productor entity) throws Exception {
        DatosPPS_Productor.datosElimnar(entity);
    }

    /**
     * Metodo statico para consultas una tupla de la tabla asignada.
     *
     * @param entity objeto de la clase determinada.
     * @return objeto de la clase determinada.
     * @throws Exception mensaje de error
     */
//    public static PPS_Productor control_GetExitenciaTupla(PPS_Productor entity) throws Exception {
    public static boolean control_GetExitenciaTupla(PPS_Productor entity) throws Exception {
        return DatosPPS_Productor.datosGetExitenciaTupla(entity);
    }
    /**
     * Metodo para consultar todos los datos relacionado con de terminado
     * parametro.
     *
     * @param entity de la clase determinada.
     * @return de la clase determinada.
     * @throws Exception Mensaje de Error.
     */
    public static List<PPS_Productor> control_GetAllTuplas(PPS_Productor entity) throws Exception {
        return DatosPPS_Productor.DatosGetAllTuplas(entity);
    }
}
