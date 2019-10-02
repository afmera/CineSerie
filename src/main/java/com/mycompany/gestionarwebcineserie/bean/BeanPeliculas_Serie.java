/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Peliculas_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
@ManagedBean
@ViewScoped
public class BeanPeliculas_Serie {

    private Pelicula_Serie ps = new Pelicula_Serie();
    private List<Pelicula_Serie> listaPS;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public Pelicula_Serie getPs() {
        return ps;
    }

    public void setPs(Pelicula_Serie ps) {
        this.ps = ps;
    }

    public List<Pelicula_Serie> getListaPS() {
        return listaPS;
    }

    public void setListaPS(List<Pelicula_Serie> listaPS) {
        this.listaPS = listaPS;
    }

    /**
     * Metodo para determinar la accion registrar o modificar.
     *
     * @throws Exception mensaje de error.
     */
    public void operar() throws Exception {
        switch (accion) {
            case "Registrar":
                this.registrar();
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }

    /**
     * Metodo para limpiar el contenido del objeto.
     */
    public void limpiar() {
        this.ps.setId(0);
        this.ps.setAn_lanzamiento("");
        this.ps.setDuracion("");
        this.ps.setSinopsis("");
        this.ps.setTipo("");
        this.ps.setTitulo("");
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @throws Exception
     */
    private void registrar() throws Exception {
        try {
            Control_Peliculas_Serie.control_registrar(ps);
            this.listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @throws Exception
     */
    private void modificar() throws Exception {
        try {
            Control_Peliculas_Serie.control_modificar(ps);
            this.listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @param peliser de tipo de la clase determinda.
     * @throws Exception
     */
    public void eliminar(Pelicula_Serie peliser) throws Exception {
        try {
            Control_Peliculas_Serie.control_eliminar(peliser);
            this.listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @throws Exception
     */
    public void listar() throws Exception {
        try {
            listaPS = Control_Peliculas_Serie.control_listar();
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param peliser de tipo de la clase determinada.
     * @throws Exception
     */
    public void leerID(Pelicula_Serie peliser) throws Exception {
        try {
            Pelicula_Serie temp = Control_Peliculas_Serie.control_leerID(peliser);
            if (temp != null) {
                this.ps = temp;
                this.accion = "Modificar";
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        }
    }
}
