/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Peliculas_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
@ManagedBean
@ViewScoped
public class BeanPeliculas_Serie {

    private Pelicula_Serie ps = new Pelicula_Serie();
    private List<Pelicula_Serie> listaPS;
//    private List<Pelicula_Serie> listaPSNombre;
    private String accion;
//    private String[] ListaPeliculas;

//    public List<Pelicula_Serie> getListaPSNombre() {
//        return listaPSNombre;
//    }
//
//    public void setListaPSNombre(List<Pelicula_Serie> listaPSNombre) {
//        this.listaPSNombre = listaPSNombre;
//    }

//    public String[] getListaPeliculas() {
//        return ListaPeliculas;
//    }
//
//    public void setListaPeliculas(String[] ListaPeliculas) {
//        this.ListaPeliculas = ListaPeliculas;
//    }

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
     * Metodo para verificar el uso del preRenderView para evitar consumo de
     * recursos innecesarios.
     *
     * @return de tipo boolean.
     */
    private boolean isPostBack() {
        return FacesContext.getCurrentInstance().isPostback();
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
            this.listar(true);
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
            this.listar(true);
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
            this.listar(true);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param valor de tipo boolean.
     * @throws Exception
     */
    public void listar(boolean valor) throws Exception {
        try {
            if (!valor) {
                if (!isPostBack()) {
                    listaPS = Control_Peliculas_Serie.control_listar();
                }
            } else {
                listaPS = Control_Peliculas_Serie.control_listar();
            }

        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param valor de tipo boolean.
     * @throws Exception
     */
//    public void listarPorTipo(boolean valor) throws Exception {
//        try {
//            if (!valor) {
//                if (!isPostBack()) {
//                    listaPSNombre = Control_Peliculas_Serie.control_listarPorTipo(ps);
//                }
//            } else {
//                listaPSNombre = Control_Peliculas_Serie.control_listarPorTipo(ps);
//            }
//
//        } catch (Exception ex) {
//            System.out.println("Error " + ex);
//            throw ex;
//        }
//    }
    /**
     * Metod de arranque.
     */
//    @PostConstruct
//    public void init()
//    {
//        try {
//            this.listarPorTipo(false);
//        } catch (Exception ex) {
//            System.out.println("Error"+ex);
//        }
//    }

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
