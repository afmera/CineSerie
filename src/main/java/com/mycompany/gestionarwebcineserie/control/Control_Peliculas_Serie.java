/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.control;

import com.mycompany.gestionarwebcineserie.datos.DatosPeliculas_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;

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
        DatosPeliculas_Serie.registrar(ps);
    }
}
