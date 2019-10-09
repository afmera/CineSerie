/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.datos.DatosGenero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Genero;
import com.mycompany.gestionarwebcineserie.model.Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import com.sun.jmx.snmp.BerDecoder;
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
public class BeanDetalle_Pelicula_Serie {

    private Pelicula_Serie pelicula_serie = new Pelicula_Serie();
    private Genero genero = new Genero();
//    private String[] selectGenero = new String[0];
    private String[] selectGenero = {"Item 1"};
//    private Genero[] selectedEntiy= null;
    private List<Genero_Pelicula_Serie> listaGPS = new ArrayList<Genero_Pelicula_Serie>();

//    public Genero[] getSelectedEntiy() {
//        return selectedEntiy;
//    }
//
//    public void setSelectedEntiy(Genero[] selectedEntiy) {
//        this.selectedEntiy = selectedEntiy;
//    }
    public String[] getSelectGenero() {
        return selectGenero;
    }

    public void setSelectGenero(String[] selectGenero) {
        this.selectGenero = selectGenero;
    }

    public Pelicula_Serie getPelicula_serie() {
        return pelicula_serie;
    }

    public void setPelicula_serie(Pelicula_Serie pelicula_serie) {
        this.pelicula_serie = pelicula_serie;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Genero_Pelicula_Serie> getListaGPS() {
        return listaGPS;
    }

    public void setListaGPS(List<Genero_Pelicula_Serie> listaGPS) {
        this.listaGPS = listaGPS;
    }

    /**
     * Metodo void para agregar ala la lista.
     */
    public void agregar() {
        try {
            System.out.println("\n\nselectGenero.length " + selectGenero.length);
            if (selectGenero.length > 0) {
//        if(selectedEntiy.length>0){
                System.out.println("\nselectGenero[0] " + selectGenero[0]);
                
//        Genero_Pelicula_Serie gps = new Genero_Pelicula_Serie();
//        gps.setGenero(genero);
//        gps.setPelicula_serie(pelicula_serie);
//        listaGPS.add(gps);
//            Genero_Pelicula_Serie gps = new Genero_Pelicula_Serie();
//            for (String sg : selectGenero) {
                for (int cont = 0; cont < selectGenero.length; cont++) {
                    Genero_Pelicula_Serie gps = new Genero_Pelicula_Serie();
                    String[] valor=selectGenero[cont].split(",");
                    System.out.println("valor[0] "+valor[0]);
//                genero.setNombre(selectGenero[cont]);
//                gps.setGenero(genero);
                    gps.setGenero(new Genero(Integer.parseInt(valor[0]),valor[1]));
                    gps.setPelicula_serie(pelicula_serie);
                    listaGPS.add(gps);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "Debes seleccionar por lo menos un genero para agregar."));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Sea presentado un error en el almacenaje.\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        }
    }

    /**
     * Metodo void para el almacenamiento de datos.
     *
     * @throws Exception
     */
    public void registrar() throws Exception {
        try {
            if (listaGPS.size() > 0) {
                Control_Genero_Pelicula_Serie.control_registrar(listaGPS);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "El registro sea Almacenado de forma exitosa."));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Sea presentado un error en el almacenaje.\n" + ex));
            System.out.println("Error es : " + ex);
            throw ex;
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    /**
     * Metodo void para quitar un elemeto de la lista.
     *
     * @param valor de tipo String.
     */
    public void quitarElemento(String valor) {
        try {
            for (int cont = 0; cont < listaGPS.size(); cont++) {
                System.out.println("\nlistaGPS.get(cont).getGenero().getNombre() " + listaGPS.get(cont).getGenero().getNombre()
                        + "\nvalor " + valor);
                if (listaGPS.get(cont).getGenero().getNombre().equals(valor)) {
                    listaGPS.remove(cont);
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
}
