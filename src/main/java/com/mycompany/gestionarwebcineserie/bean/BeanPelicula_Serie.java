/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Favorita;
import com.mycompany.gestionarwebcineserie.control.Control_Peliculas_Serie;
import com.mycompany.gestionarwebcineserie.model.Favorita;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
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
public class BeanPelicula_Serie {

    /**
     * Variable y objetos de clases determinadas.
     */
    private Pelicula_Serie entity = new Pelicula_Serie();
    private List<Pelicula_Serie> listaEntities = new ArrayList<>();
    private String accion;
    private String[] selectedEntity;

    private Favorita entityFav = new Favorita();

    public Favorita getEntityFav() {
        return entityFav;
    }

    public void setEntityFav(Favorita entityFav) {
        this.entityFav = entityFav;
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
        this.entity.setDuracion("");
        this.entity.setSinopsis("");
        this.entity.setTipo("");
        this.entity.setTitulo("");

        this.entityFav.setId(0);
        this.entityFav.setCalificacion(0);
        this.entityFav.setComentario("");
    }

    /**
     * Metodo para registrar una tupla en la base de datos.
     *
     * @throws Exception
     */
    private void registrar() throws Exception {
        try {
            Control_Peliculas_Serie.control_registrar(entity);
            if (entityFav.getCalificacion() > 0 || entityFav.getComentario().length() > 0) {
                Pelicula_Serie objEntity = Control_Peliculas_Serie.control_LastInsertID();
                entityFav.setPelicula_serie(objEntity);
                Control_Favorita.control_registrar(entityFav);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "Sea almacena de forma correta la informacion.\n"));
            this.listar(true);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Sea presentado un error en el almacenaje.\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para modificar una tupla de la base de datos.
     *
     * @throws Exception
     */
    private void modificar() throws Exception {
        try {
            Control_Peliculas_Serie.control_modificar(entity);
            if (entityFav.getCalificacion() > 0 || entityFav.getComentario().length() > 0) {
                entityFav.setPelicula_serie(this.entity);
                //Necesito verificar si el registro ya ha sido almacenad, sino se registrara por primera vez.
                Favorita temp = Control_Favorita.control_LeerIDByForeginKey(entityFav);
                if (temp != null) {
                    Control_Favorita.control_modificar(entityFav);
                } else {
                    Control_Favorita.control_registrar(entityFav);
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "Sea modificado un registro de forma correta.\n"));
            }
            this.listar(true);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Sea presentado un error en la actualizacion del registro.\n" + ex));
            System.out.println("Error es : " + ex);
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "Sea eliminado un registro de forma correta.\n"));
            this.listar(true);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Sea presentado un error en la eliminacion de un registro.\n" + ex));
            System.out.println("Error es : " + ex);
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Sea presentado al consultar en la base de datos.\n" + ex));
            System.out.println("Error es : " + ex);
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
                Favorita temp2 = Control_Favorita.control_LeerIDByForeginKey(new Favorita(objEntity));
                if (temp2 != null) {
                    this.entityFav = temp2;
                }
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Sea presentado un error en la consulta de un registro.\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        }
    }
}
