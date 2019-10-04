/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosFavorita;
import com.mycompany.gestionarwebcineserie.model.Favorita;
import java.util.List;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Control_Favorita {
    
    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_registrar(Favorita entity) throws Exception {
        DatosFavorita.datosRegistrar(entity);
    }
    /**
     * Metodo statico para consultas generarles de la tabla asignada.
     * @return de tipo List<Favorita>
     * @throws Exception mensaje de error
     */
    public static List<Favorita> control_listar() throws Exception
    {
        return DatosFavorita.datosListar();
    }
    /**
     * Metodo statico para consultar por ID una tupla de la tabla asignada.
     * @param entity de tipo de la clase determinada.
     * @return de tipo de la clase determinada.
     * @throws Exception mensaje de error
     */
    public static Favorita control_leerID(Favorita entity) throws Exception
    {
        return DatosFavorita.datosLeerID(entity);
    }
    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_modificar(Favorita entity) throws Exception {
        DatosFavorita.datosModificar(entity);
    }
    /**
     * Metodo de control de loa accesos a la manipulacion de datos.
     *
     * @param entity del tipo de la clase definada.
     * @throws java.lang.Exception Mensaje de error.
     */
    public static void control_eliminar(Favorita entity) throws Exception {
        DatosFavorita.datosElimnar(entity);
    }
}
