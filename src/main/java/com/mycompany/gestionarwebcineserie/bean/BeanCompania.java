/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Compania;
import com.mycompany.gestionarwebcineserie.model.Compania;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
@ManagedBean
@ViewScoped
public class BeanCompania {

    private Compania enti = new Compania();
    private List<Compania> listaEntities;
    private String[] selectEntities = null;
    private List<String> pais;
    private String accion;

    public String[] getSelectEntities() {
        return selectEntities;
    }

    public void setSelectEntities(String[] selectEntities) {
        this.selectEntities = selectEntities;
    }

    public List<String> getPais() {
        return pais;
    }

    public void setPais(List<String> pais) {
        this.pais = pais;
    }

    public Compania getEnti() {
        return enti;
    }

    public void setEnti(Compania enti) {
        this.enti = enti;
    }

    public List<Compania> getListaEntities() {
        return listaEntities;
    }

    public void setListaEntities(List<Compania> listaEntities) {
        this.listaEntities = listaEntities;
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
        this.enti.setId(0);
        this.enti.setNombre("");
        this.enti.setMajos("");
        this.enti.setNacionalidad("");
        this.enti.setStreaming("");
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @throws Exception
     */
    private void registrar() throws Exception {
        try {
            Control_Compania.control_registrar(enti);
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
            Control_Compania.control_modificar(enti);
            this.listar(true);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @param entity de tipo de la clase determinda.
     * @throws Exception
     */
    public void eliminar(Compania entity) throws Exception {
        try {
            Control_Compania.control_eliminar(entity);
            this.listar(true);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param valor
     * @throws Exception
     */
    public void listar(boolean valor) throws Exception {
        try {
            if (!valor) {
                if (!isPostBack()) {
            listaEntities = Control_Compania.control_listar();
            }
            } else {
                listaEntities = Control_Compania.control_listar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :" + ex);
            System.out.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param entity de tipo de la clase determinada.
     * @throws Exception
     */
    public void leerID(Compania entity) throws Exception {
        try {
            Compania temp = Control_Compania.control_leerID(entity);
            if (temp != null) {
                this.enti = temp;
                this.accion = "Modificar";
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para cargar los paices.
     */
    @PostConstruct
    public void selectNacionalida() {
        pais = new ArrayList<String>();
        pais.add("Estados Unidos");
        pais.add("Inglaterra");
        pais.add("Alemania");
        pais.add("Francia");
        pais.add("España");
        pais.add("Mexico");
        pais.add("Argentina");
        pais.add("Brasil");
        pais.add("Colombia");
        pais.add("Republica Popular de China");
        pais.add("Republica de Corea");
        pais.add("Otro");
    }
}
