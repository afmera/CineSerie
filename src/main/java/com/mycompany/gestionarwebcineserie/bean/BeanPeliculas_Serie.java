/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Peliculas_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
@ManagedBean
@RequestScoped
public class BeanPeliculas_Serie {

    private Pelicula_Serie ps = new Pelicula_Serie();

    public Pelicula_Serie getPs() {
        return ps;
    }

    public void setPs(Pelicula_Serie ps) {
        this.ps = ps;
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @throws Exception
     */
    public void registrar() throws Exception {
        try {
            Control_Peliculas_Serie.control_registrar(ps);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
