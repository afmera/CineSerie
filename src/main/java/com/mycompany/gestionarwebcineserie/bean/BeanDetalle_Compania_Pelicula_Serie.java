/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Compania_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Compania;
import com.mycompany.gestionarwebcineserie.model.Compania_Pelicula_Serie;
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
public class BeanDetalle_Compania_Pelicula_Serie {

    private Compania_Pelicula_Serie entity = new Compania_Pelicula_Serie();
    private Pelicula_Serie entityPS = new Pelicula_Serie();
    private Compania entityComp = new Compania();
    private List<Compania_Pelicula_Serie> listaCPS = new ArrayList<>();

    public Compania_Pelicula_Serie getEntity() {
        return entity;
    }

    public void setEntity(Compania_Pelicula_Serie entity) {
        this.entity = entity;
    }

    public Pelicula_Serie getEntityPS() {
        return entityPS;
    }

    public void setEntityPS(Pelicula_Serie entityPS) {
        this.entityPS = entityPS;
    }

    public Compania getEntityComp() {
        return entityComp;
    }

    public void setEntityComp(Compania entityComp) {
        this.entityComp = entityComp;
    }

    public List<Compania_Pelicula_Serie> getListaCPS() {
        return listaCPS;
    }

    public void setListaCPS(List<Compania_Pelicula_Serie> listaCPS) {
        this.listaCPS = listaCPS;
    }

    /**
     * Metodo para verificar datos repetidos en la List de la Tabla.
     *
     * @return del tipo Boolean.
     */
    private boolean verificarElementosRepetidos(Compania_Pelicula_Serie entity) {
        boolean resultado = false;
        List<Compania_Pelicula_Serie> newList = this.listaCPS;
        if (newList.size() > 0) {
            for (int i = 0; i < newList.size(); i++) {
                int count = 0;
                if (newList.get(i).getCompania().getId() == entity.getCompania().getId()) {
                    count++;
                }
                if (count > 0) {
                    resultado = true;
                    break;
                }
            }
        }
        return resultado;
    }

    /**
     * Metodo para filtrar todos los elementos repetidos en la tabla.
     *
     * @return lista de la clase determinada.
     */
    private void filtrarElementosRepetidos() {
        List<Compania_Pelicula_Serie> newList = this.listaCPS;
        List<Compania_Pelicula_Serie> oldList = this.listaCPS;
        List<Compania_Pelicula_Serie> temp = new ArrayList<>();
        if (newList.size() > 0) {
            for (int i = 0; i < oldList.size(); i++) {
                int count = 0;
                for (int j = 0; j < oldList.size(); j++) {
                    if (oldList.get(i).getCompania().getId() == newList.get(j).getCompania().getId()) {
                        count++;
                    }
                }
                if (count == 1) {
                    temp.add(
                            new Compania_Pelicula_Serie(
                                    oldList.get(i).getId(),
                                    oldList.get(i).getCompania(),
                                    oldList.get(i).getPelicual_serie()
                            )
                    );
                }
            }
        }
        if (temp.size() > 0) {
            this.listaCPS.clear();
            this.listaCPS = temp;
        }
    }
//
//    /**
//     * Limapiar list de la clase determinada.
//     */
//    public void limpiar() {
//        listaCPS.clear();
//    }

    /**
     * Metodo void para agregar ala la lista.
     *
     * @throws java.lang.Exception
     */
    public void agregar() throws Exception {
        try {
            if (this.entityPS != null) {
                Pelicula_Serie ps = this.entityPS;
                if (this.entityComp != null) {
                    Compania com = this.entityComp;
                    Compania_Pelicula_Serie cps = new Compania_Pelicula_Serie();
                    cps.setCompania(com);
                    cps.setPelicual_serie(ps);
                    if (!verificarElementosRepetidos(cps)) {
                        Compania_Pelicula_Serie temp = Control_Compania_Pelicula_Serie.control_GetExitenciaTupla(cps);
                        if (temp != null) {
                            FacesContext.getCurrentInstance().addMessage(
                                    null,
                                    new FacesMessage(
                                            FacesMessage.SEVERITY_WARN,
                                            "ADVERTENCIA",
                                            "El registro \nCompania :" + temp.getCompania().getNombre()
                                            + " Pelicula o Serie :" + temp.getPelicual_serie().getTitulo()
                                            + ". \nYa se encuentra agregado en la Bases de Datos. "
                                            + "\nVerifica los datos por favor y vuelve a intentarlo."));

                        } else {
                            listaCPS.add(cps);
                            System.out.println("listaCPS.size() " + listaCPS.size());
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADVERTENCIA", "Lo sentimos, el elemento ha agregar ya esta en la tabla."));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADVERTENCIA", "Debes seleccionar una compania para agregar."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADVERTENCIA", "Debes seleccionar una pelicula o serie para agregar."));
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
        if (this.entityPS != null) {
            Pelicula_Serie ps = this.entityPS;
            Compania_Pelicula_Serie cps = new Compania_Pelicula_Serie();
            cps.setPelicual_serie(ps);
            listaCPS.clear();
            listaCPS = Control_Compania_Pelicula_Serie.control_GetAllTuplas(cps);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debes seleccionar una pelicula o serie para consultar."));
        }
    }

    /**
     * Metodo void para el almacenamiento de datos.
     *
     * @throws Exception
     */
    public void registrar() throws Exception {
        try {
            if (listaCPS.size() > 0) {
                {
                    this.filtrarElementosRepetidos();
                    Control_Compania_Pelicula_Serie.control_RegistrarLista(listaCPS);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "El registro sea Almacenado de forma exitosa."));
                    listaCPS.clear();
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
     * Metodo void para quitar un elemeto de la lista.
     *
     * @param objEntity de tipo String.
     * @throws java.lang.Exception
     */
    public void quitarElemento(Compania_Pelicula_Serie objEntity) throws Exception {
        try {
            Control_Compania_Pelicula_Serie.control_eliminar(objEntity);
            for (int cont = 0; cont < listaCPS.size(); cont++) {
                if (listaCPS.get(cont).getCompania().getNombre().equals(objEntity.getCompania().getNombre())) {
                    listaCPS.remove(cont);
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
     * Metodo para listar un dato de la tabla en la base de datos.
     *
     * @param entity de tipo de la clase determinada.
     * @throws Exception Mesaje Error.
     */
    public void leerID(Compania_Pelicula_Serie entity) throws Exception {
        try {
            Compania_Pelicula_Serie temp = Control_Compania_Pelicula_Serie.control_leerID(entity);
            if (temp != null) {
                this.entity = temp;
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Sea presentado un error en :\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    /**
     * Metodo void para una consulta general de los datos en la tabla.
     *
     * @param valor
     * @throws Exception Mensaje Error.
     */
    public void listar(boolean valor) throws Exception {
        try {
            if (!valor) {
                if (!isPostBack()) {
                    this.listaCPS = Control_Compania_Pelicula_Serie.control_listar();
                }
            } else {
                this.listaCPS = Control_Compania_Pelicula_Serie.control_listar();
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Sea presentado un error en :\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        }
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
}
