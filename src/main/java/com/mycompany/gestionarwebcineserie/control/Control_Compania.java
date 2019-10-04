/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosCompania;
import com.mycompany.gestionarwebcineserie.model.Compania;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_Compania {

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_registrar(Compania entity) throws Exception {
        DatosCompania.datosRegistrar(entity);
    }

    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     *
     * @return de tipo List<Compania>
     * @throws Exception mensaje de error
     */
    public static List<Compania> control_listar() throws Exception {
        return DatosCompania.datosListar();
    }

    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     *
     * @param entity de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static Compania control_leerID(Compania entity) throws Exception {
        return DatosCompania.datosLeerID(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_modificar(Compania entity) throws Exception {
        DatosCompania.datosModificar(entity);
    }

    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_eliminar(Compania entity) throws Exception {
        DatosCompania.datosElimnar(entity);
    }
}
