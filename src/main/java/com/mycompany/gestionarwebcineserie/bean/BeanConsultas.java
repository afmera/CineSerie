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
public class BeanConsultas {

    /**
     * Variable y objetos de clases determinadas.
     */
    private Integer[] selectCalificacion = {1, 2, 3, 4, 5};
    private Pelicula_Serie entity = new Pelicula_Serie();
    private Favorita favorita = new Favorita();
    private List<Pelicula_Serie> listaEntities;

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

    /**
     * Metodo para filtrar todos los elementos repetidos en la tabla.
     *
     * @return lista de la clase determinada.
     */
    private void filtrarElementosRepetidos() {
        List<Pelicula_Serie> newList = this.listaEntities;
        List<Pelicula_Serie> oldList = this.listaEntities;
        List<Pelicula_Serie> temp = new ArrayList<>();
        if (newList.size() > 0) {
            for (int i = 0; i < oldList.size(); i++) {
                int count = 0;
                for (int j = 0; j < oldList.size(); j++) {
//                    if (oldList.get(i).getPersona().getId() == newList.get(j).getPersona().getId()) {
//                        count++;
//                    }
                }
                if (count == 1) {
//                    temp.add(new PPS_Actor(oldList.get(i).getId(), oldList.get(i).getPersona(), oldList.get(i).getPelicula_serie(), oldList.get(i).getTipo(), oldList.get(i).getTiempo_pantalla()));
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
     * Metodo para consultar toso los datos de un parametro determinado.
     *
     * @throws Exception Mensaje de Error.
     */
    public void consultar() throws Exception {
        try {
            if (this.entity != null) {
                Pelicula_Serie objEntity = this.entity;
                this.listaEntities.clear();
                if (objEntity != null) {
                    this.listaEntities.add(Control_Peliculas_Serie.control_leerID(objEntity));
                    Favorita fav = this.favorita;
                    if (fav != null) {
                        if (fav.getId() > 0) {
                            this.listaEntities.clear();
                            fav.setPelicula_serie(objEntity);
                            List<Favorita> lista = Control_Favorita.control_GetTuplasCalificacion(fav);
                            for (Favorita l : lista) {
                                this.listaEntities.add(l.getPelicula_serie());
                            }
                        }
                    }
                }
                Favorita fav = this.favorita;
                if (fav != null) {
                    if (fav.getId() > 0) {
                        this.listaEntities.clear();
                        fav.setPelicula_serie(objEntity);
                        List<Favorita> lista = Control_Favorita.control_GetTuplasCalificacion(fav);
                        for (Favorita l : lista) {
                            this.listaEntities.add(l.getPelicula_serie());
                        }
                    }
                }
            } else if(this.favorita!=null) {
                Favorita fav = this.favorita;
                if (fav != null) {
                    if (fav.getCalificacion() > 0) {
                        this.listaEntities.clear();
                        List<Favorita> lista = Control_Favorita.control_GetTuplasCalificacion(fav);
                        for (Favorita l : lista) {
                            this.listaEntities.add(l.getPelicula_serie());
                        }
                    }
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debes seleccionar una pelicula o serie para consultar."));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Sea presentado un error en la consuta en la base de datos.\nError es :" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        }
    }
}
