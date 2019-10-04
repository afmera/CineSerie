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
public class Control_Productor {

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
}
