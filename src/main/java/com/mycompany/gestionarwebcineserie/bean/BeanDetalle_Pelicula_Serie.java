/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Compania_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.control.Control_Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Compania;
import com.mycompany.gestionarwebcineserie.model.Compania_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Genero;
import com.mycompany.gestionarwebcineserie.model.Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
@ManagedBean
@ViewScoped
public class BeanDetalle_Pelicula_Serie {

    private Genero_Pelicula_Serie entityGPS = new Genero_Pelicula_Serie();
    private Compania_Pelicula_Serie entityCPS = new Compania_Pelicula_Serie();
    private List<Genero_Pelicula_Serie> listEntitiesGPS;
    private List<Compania_Pelicula_Serie> listEntitiesCPS;
    private String[] selectEntitiesGPS = null;
    private String[] selectEntitiesCPS = null;
    private String accionPestana01;
    private String txt1;

    public String getAccionPestana01() {
        return accionPestana01;
    }

    public void setAccionPestana01(String accionPestana01) {
        this.limpiar();
        this.accionPestana01 = accionPestana01;
    }

    public Genero_Pelicula_Serie getEntityGPS() {
        return entityGPS;
    }

    public void setEntityGPS(Genero_Pelicula_Serie entityGPS) {
        this.entityGPS = entityGPS;
    }

    public Compania_Pelicula_Serie getEntityCPS() {
        return entityCPS;
    }

    public void setEntityCPS(Compania_Pelicula_Serie entityCPS) {
        this.entityCPS = entityCPS;
    }

    public List<Genero_Pelicula_Serie> getListEntitiesGPS() {
        return listEntitiesGPS;
    }

    public void setListEntitiesGPS(List<Genero_Pelicula_Serie> listEntitiesGPS) {
        this.listEntitiesGPS = listEntitiesGPS;
    }

    public List<Compania_Pelicula_Serie> getListEntitiesCPS() {
        return listEntitiesCPS;
    }

    public void setListEntitiesCPS(List<Compania_Pelicula_Serie> listEntitiesCPS) {
        this.listEntitiesCPS = listEntitiesCPS;
    }

    public String[] getSelectEntitiesGPS() {
        return selectEntitiesGPS;
    }

    public void setSelectEntitiesGPS(String[] selectEntitiesGPS) {
        this.selectEntitiesGPS = selectEntitiesGPS;
    }

    public String[] getSelectEntitiesCPS() {
        return selectEntitiesCPS;
    }

    public void setSelectEntitiesCPS(String[] selectEntitiesCPS) {
        this.selectEntitiesCPS = selectEntitiesCPS;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    /**
     * Metdo para autocompletar el texto del nombre.
     *
     * @param query de tipo String.
     * @return de tipo String.
     */
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            results.add(query + i);
        }
        return results;
    }

    public void obtenerValorSelected() {

    }

    /**
     * Metodo para determinar la accion registrar o modificar.
     *
     * @param valor
     * @param entityPeliculaSerie
     * @param entityCompania
     * @param entityGenero
     * @throws Exception mensaje de error.
     */
    public void operar(boolean valor, Pelicula_Serie entityPeliculaSerie, Compania entityCompania, Genero entityGenero) throws Exception {
        if (selectEntitiesCPS.length > 0) {
            for (int cont = 0; cont < selectEntitiesCPS.length; cont++) {
                if (selectEntitiesCPS[cont].equals(entityCompania.getNombre())) {
                    entityCPS.setCompania(new Compania());
                }
                entityCPS.setPelicual_serie(entityPeliculaSerie);
            }
        }
        entityCPS.setCompania(entityCompania);
        entityCPS.setPelicual_serie(entityPeliculaSerie);
        entityGPS.setGenero(entityGenero);
        entityGPS.setPelicula_serie(entityPeliculaSerie);
        if (valor) {
            this.registrar();
            this.limpiar();
        }
    }

    /**
     * Metodo para limpiar el contenido del objeto.
     */
    public void limpiar() {
        this.entityCPS.setId(0);
        this.entityCPS.setCompania(null);
        this.entityCPS.setPelicual_serie(null);

        this.entityGPS.setId(0);
        this.entityGPS.setGenero(null);
        this.entityGPS.setPelicula_serie(null);
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @throws Exception
     */
    private void registrar() throws Exception {
        try {
            Control_Genero_Pelicula_Serie.control_registrar(entityGPS);
            Control_Compania_Pelicula_Serie.control_registrar(entityCPS);
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
            Control_Genero_Pelicula_Serie.control_modificar(entityGPS);
            Control_Compania_Pelicula_Serie.control_modificar(entityCPS);
            this.listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Metodo para registrar atos en la base de datos.
     *
     * @param objEntityGPS de tipo de la clase determinda.
     * @param objEntityCPS
     * @throws Exception
     */
    public void eliminar(Genero_Pelicula_Serie objEntityGPS, Compania_Pelicula_Serie objEntityCPS) throws Exception {
        try {
            Control_Genero_Pelicula_Serie.control_eliminar(objEntityGPS);
            Control_Compania_Pelicula_Serie.control_eliminar(entityCPS);
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
            listEntitiesGPS = Control_Genero_Pelicula_Serie.control_listar();
            listEntitiesCPS = Control_Compania_Pelicula_Serie.control_listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :" + ex);
            System.out.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para listar todos los datos de la tabla en la base de datos.
     *
     * @param objEntityGPS de tipo de la clase determinada.
     * @param objEntityCPS
     * @throws Exception
     */
    public void leerID(Genero_Pelicula_Serie objEntityGPS, Compania_Pelicula_Serie objEntityCPS) throws Exception {
        try {
            Compania_Pelicula_Serie tempCPS = Control_Compania_Pelicula_Serie.control_leerID(objEntityCPS);
            Genero_Pelicula_Serie tempGPS = Control_Genero_Pelicula_Serie.control_leerID(entityGPS);
            if ((tempCPS != null) || (tempGPS != null)) {
                this.entityCPS = tempCPS;
                this.entityGPS = tempGPS;
                this.accionPestana01 = "Modificar";
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        }
    }
}
