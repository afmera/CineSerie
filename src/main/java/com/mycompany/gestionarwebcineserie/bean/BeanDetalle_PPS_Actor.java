/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_PPS_Actor;
import com.mycompany.gestionarwebcineserie.model.PPS_Actor;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
@ManagedBean
@ViewScoped
public class BeanDetalle_PPS_Actor {

    /**
     * Variable y objetos de clases determinadas.
     */
    private String[] selectTipo = {"Protagonista", "Secundario", "De Reparto", "De Pequeñas Partes"};
    private Persona entityPer = new Persona();
    private Pelicula_Serie entityPS = new Pelicula_Serie();
    private PPS_Actor entity = new PPS_Actor();
    private List<PPS_Actor> listaEntities = new ArrayList<>();
    private String[] selectedEntity;

    public String[] getSelectTipo() {
        return selectTipo;
    }

    public void setSelectTipo(String[] selectTipo) {
        this.selectTipo = selectTipo;
    }

    public Persona getEntityPer() {
        return entityPer;
    }

    public void setEntityPer(Persona entityPer) {
        this.entityPer = entityPer;
    }

    public Pelicula_Serie getEntityPS() {
        return entityPS;
    }

    public void setEntityPS(Pelicula_Serie entityPS) {
        this.entityPS = entityPS;
    }

    public PPS_Actor getEntity() {
        return entity;
    }

    public void setEntity(PPS_Actor entity) {
        this.entity = entity;
    }

    public List<PPS_Actor> getListaEntities() {
        return listaEntities;
    }

    public void setListaEntities(List<PPS_Actor> listaEntities) {
        this.listaEntities = listaEntities;
    }

    public String[] getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(String[] selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    /**
     * Metodo para filtrar todos los elementos repetidos en la tabla.
     *
     * @return lista de la clase determinada.
     */
    private void filtrarElementosRepetidos() {
        List<PPS_Actor> newList = this.listaEntities;
        List<PPS_Actor> oldList = this.listaEntities;
        List<PPS_Actor> temp = new ArrayList<>();
        if (newList.size() > 0) {
            for (int i = 0; i < oldList.size(); i++) {
                int count = 0;
                for (int j = 0; j < oldList.size(); j++) {
                    if (oldList.get(i).getPersona().getId() == newList.get(j).getPersona().getId()) {
                        count++;
                    }
                }
                if (count == 1) {
                    temp.add(new PPS_Actor(oldList.get(i).getId(), oldList.get(i).getPersona(), oldList.get(i).getPelicula_serie(), oldList.get(i).getTipo(), oldList.get(i).getTiempo_pantalla()));
                }
            }
        }
        if (temp.size() > 0) {
            this.listaEntities.clear();
            this.listaEntities = temp;
        }
    }

    /**
     * Metodo void para limpiar la lista de la clase determinda.
     */
    public void limpiar() {
        listaEntities.clear();
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
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param valor de tipo boolean.
     * @throws Exception
     */
    public void listar(boolean valor) throws Exception {
        try {
            if (!valor) {
                if (!isPostBack()) {
                    listaEntities = Control_PPS_Actor.control_listar();
                }
            } else {
                listaEntities = Control_PPS_Actor.control_listar();
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Sea presentado al consultar en la base de datos.\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        }
    }

    /**
     * Metodo void para quitar un elemeto de la lista.
     *
     * @param objEntity de tipo String.
     * @throws java.lang.Exception
     */
    public void quitarElemento(PPS_Actor objEntity) throws Exception {
        try {
            Control_PPS_Actor.control_eliminar(objEntity);
            for (int cont = 0; cont < this.listaEntities.size(); cont++) {
                if (this.listaEntities.get(cont).getPersona().getNombre().equals(objEntity.getPersona().getNombre())) {
                    this.listaEntities.remove(cont);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "El registro a sido retirado de la tabla."));
                }
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Sea presentado un error en la lista.\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    /**
     * Metodo void para el almacenamiento de datos.
     *
     * @throws Exception
     */
    public void registrar() throws Exception {
        try {
            if (this.listaEntities.size() > 0) {
                {
                    this.filtrarElementosRepetidos();
                    Control_PPS_Actor.control_RegistrarLista(listaEntities);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "El registro sea Almacenado de forma exitosa."));
                    listaEntities.clear();
                }
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Sea presentado un error en el almacenaje.\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    /**
     * Metodo void para agregar ala la lista.
     *
     * @throws java.lang.Exception
     */
    public void agregar() throws Exception {
        try {
            if (this.entityPS != null) {
                if (this.entityPer != null) {
                    PPS_Actor objEntity = this.entity;
                    objEntity.setPelicula_serie(this.entityPS);
                    objEntity.setPersona(this.entityPer);
//                    PPS_Actor temp = Control_PPS_Actor.control_GetExitenciaTupla(objEntity);
                    boolean rest = Control_PPS_Actor.control_GetExitenciaTupla(objEntity);
//                    if (temp != null) {
                    if (rest) {
                        FacesContext.getCurrentInstance().addMessage(
                                null,
                                new FacesMessage(
                                        FacesMessage.SEVERITY_WARN,
                                        "ADVERTENCIA",
                                        "El registro de la Persona :" + objEntity.getPersona().getNombre() + "\n"
                                        + "y la Pelicula o Serie :" + objEntity.getPelicula_serie().getTitulo() + ".\n"
                                        + "Ya se encuentra agregado en la Bases de Datos."
                                        + "\nVerifica los datos por favor y vuelve a intentarlo."));

                    } else {
                        listaEntities.add(objEntity);
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "Debes seleccionar unaa persona para agregar."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "Debes seleccionar una pelicula o serie para agregar."));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Sea presentado un error en el almacenaje.\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para consultar toso los datos de un parametro determinado.
     *
     * @throws Exception Mensaje de Error.
     */
    public void consultar() throws Exception {
        try {
            PPS_Actor objEntity = this.entity;
            if (this.entityPS != null) {
                if (this.entityPer != null) {
                    objEntity.setPelicula_serie(entityPS);
                    objEntity.setPersona(entityPer);
                    this.listaEntities.clear();
                    this.listaEntities = Control_PPS_Actor.control_GetAllTuplas(objEntity);
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debes seleccionar una persona para consultar."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debes seleccionar una pelicula o serie para consultar."));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Sea presentado un error en la consuta en la base de datos.\nError es :" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        }
    }
}
