/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Genero;
import com.mycompany.gestionarwebcineserie.model.Genero;
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
public class BeanGenero {

    private Genero entity = new Genero();
    private List<Genero> listEntity;
    private List<String> listGeneros;
    private String[] generos ;

    public String[] getGeneros() {
        return generos;
    }

    public void setGeneros(String[] generos) {
        this.generos = generos;
    }
    private String accion;

    public Genero getEntity() {
        return entity;
    }

    public void setEntity(Genero entity) {
        this.entity = entity;
    }

    public List<Genero> getListEntity() {
        return listEntity;
    }

    public void setListEntity(List<Genero> listEntity) {
        this.listEntity = listEntity;
    }

    public List<String> getListGeneros() {
        return listGeneros;
    }

    public void setListGeneros(List<String> listGeneros) {
        this.listGeneros = listGeneros;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
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
        this.entity.setNombre("");
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @throws Exception
     */
    private void registrar() throws Exception {
        try {
            Control_Genero.control_registrar(entity);
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
            Control_Genero.control_modificar(entity);
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
    public void eliminar(Genero objEntity) throws Exception {
        try {
            Control_Genero.control_eliminar(objEntity);
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
                    listEntity = Control_Genero.control_listar();
                }
            } else {
                listEntity = Control_Genero.control_listar();
            }
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
    public void leerID(Genero peliser) throws Exception {
        try {
            Genero temp = Control_Genero.control_leerID(peliser);
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
