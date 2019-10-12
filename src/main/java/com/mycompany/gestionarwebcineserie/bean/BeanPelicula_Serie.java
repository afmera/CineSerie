/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Peliculas_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.sql.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
@ManagedBean
@ViewScoped
public class BeanPelicula_Serie {

    /**
     * Variable y objetos de clases determinadas.
     */
    private Pelicula_Serie entity = new Pelicula_Serie();
    private List<Pelicula_Serie> listaEntities;
    private String accion;
    private String[] selectedEntity;
    private Integer rating;
    private String textArea;
    private Date date7;

    public Date getDate7() {
        return date7;
    }

    public void setDate7(Date date7) {
        this.date7 = date7;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Pelicula_Serie getEntity() {
        return entity;
    }

    public void setEntity(Pelicula_Serie entity) {
        this.entity = entity;
    }

    public List<Pelicula_Serie> getListaEntities() {
        return listaEntities;
    }

    public void setListaEntities(List<Pelicula_Serie> listaEntities) {
        this.listaEntities = listaEntities;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public String[] getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(String[] selectedEntity) {
        this.selectedEntity = selectedEntity;
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
        this.entity.setId(0);
        this.entity.setAn_lanzamiento("");
        this.entity.setDuracion("");
        this.entity.setSinopsis("");
        this.entity.setTipo("");
        this.entity.setTitulo("");
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @throws Exception
     */
    private void registrar() throws Exception {
        try {
            Control_Peliculas_Serie.control_registrar(entity);
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
            Control_Peliculas_Serie.control_modificar(entity);
            this.listar(true);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @param objEntity de tipo de la clase determinda.
     * @throws Exception
     */
    public void eliminar(Pelicula_Serie objEntity) throws Exception {
        try {
            Control_Peliculas_Serie.control_eliminar(objEntity);
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
                    listaEntities = Control_Peliculas_Serie.control_listar();
                }
            } else {
                listaEntities = Control_Peliculas_Serie.control_listar();
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param objEntity de tipo de la clase determinada.
     * @throws Exception
     */
    public void leerID(Pelicula_Serie objEntity) throws Exception {
        try {
            Pelicula_Serie temp = Control_Peliculas_Serie.control_leerID(objEntity);
            if (temp != null) {
                this.entity = temp;
                this.accion = "Modificar";
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        }
    }
}
