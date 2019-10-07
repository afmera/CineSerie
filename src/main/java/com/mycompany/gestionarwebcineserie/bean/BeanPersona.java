/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Persona;
import com.mycompany.gestionarwebcineserie.model.Persona;
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
public class BeanPersona {

    private Persona entity = new Persona();
    private List<Persona> listaEntities;
    private String accion;
    private String[] selectedEntity;

    public Persona getEntity() {
        return entity;
    }

    public void setEntity(Persona entity) {
        this.entity = entity;
    }

    public List<Persona> getListaEntities() {
        return listaEntities;
    }

    public void setListaEntities(List<Persona> listaEntities) {
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
        this.entity.setNombre("");
        this.entity.setGenero("");
        this.entity.setFecha_nacimiento("");
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @throws Exception
     */
    private void registrar() throws Exception {
        try {
            Control_Persona.control_registrar(entity);
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
            Control_Persona.control_modificar(entity);
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
    public void eliminar(Persona objEntity) throws Exception {
        try {
            Control_Persona.control_eliminar(objEntity);
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
                    listaEntities = Control_Persona.control_listar();
                }
            } else {
                listaEntities = Control_Persona.control_listar();
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param objEntity de tipo de la clase determinada.
     * @throws Exception
     */
    public void leerID(Persona objEntity) throws Exception {
        try {
            Persona temp = Control_Persona.control_leerID(objEntity);
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
