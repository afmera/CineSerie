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
import static com.sun.javafx.logging.PulseLogger.addMessage;
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
public class BeanConsultas {

    /**
     * Variable y objetos de clases determinadas.
     */
    private Integer[] selectCalificacion = {1, 2, 3, 4, 5};
    private Object[] selectedOpcion;
    private Pelicula_Serie entity = new Pelicula_Serie();
    private Favorita favorita = new Favorita();
    private List<Pelicula_Serie> listaEntities;
    private String opcion;
    private String valorEditable;

    public String getValorEditable() {
        return valorEditable;
    }

    public void setValorEditable(String valorEditable) {
        this.valorEditable = valorEditable;
    }

    public Object[] getSelectedOpcion() {
        return selectedOpcion;
    }

    public void setSelectedOpcion(Object[] selectedOpcion) {
        this.selectedOpcion = selectedOpcion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Pelicula_Serie getEntity() {
        return entity;
    }

    public void setEntity(Pelicula_Serie entity) {
        this.entity = entity;
    }

    public Favorita getFavorita() {
        return favorita;
    }

    public void setFavorita(Favorita favorita) {
        this.favorita = favorita;
    }

    public List<Pelicula_Serie> getListaEntities() {
        return listaEntities;
    }

    public void setListaEntities(List<Pelicula_Serie> listaEntities) {
        this.listaEntities = listaEntities;
    }

    public Integer[] getSelectCalificacion() {
        return selectCalificacion;
    }

    public void setSelectCalificacion(Integer[] selectCalificacion) {
        this.selectCalificacion = selectCalificacion;
    }

    /*
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
     * Metodo para determinar el tipo de consulta a realizar.
     *
     * @throws java.lang.Exception Mensaje de error.
     */
    public void opcionConsulta() throws Exception {
        int cont = 0;
        String op = this.opcion;
        System.out.println("Opcion " + opcion);
        if (op.equals("ps")) {
            List<Pelicula_Serie> lista = Control_Peliculas_Serie.control_listar();
            selectedOpcion = new Object[lista.size()];
            for (Pelicula_Serie l : lista) {
                selectedOpcion[cont] = l.getTitulo();
                cont++;
            }
        }
    }

    /**
     * Metodo void para la ejecucion de la consulta.
     *
     * @throws Exception Mensaje de error.
     */
    public void ejecucionConsulta() throws Exception {
        String op = this.opcion;
        String valor=this.valorEditable;
//        if (op.equals("ps")) {
        listaEntities.clear();
            Pelicula_Serie objEntity = new Pelicula_Serie();
            objEntity.setTipo(valor);
            listaEntities = Control_Peliculas_Serie.control_ConsultarNombre(objEntity);
//        }
    }
}
