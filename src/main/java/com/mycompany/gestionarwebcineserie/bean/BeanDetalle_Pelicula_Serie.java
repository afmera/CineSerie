/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Genero;
import com.mycompany.gestionarwebcineserie.model.Genero_Pelicula_Serie;
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
public class BeanDetalle_Pelicula_Serie {

    private Genero_Pelicula_Serie entity = new Genero_Pelicula_Serie();
    private Pelicula_Serie pelicula_serie = new Pelicula_Serie();
    private Genero genero = new Genero();
    private String[] selectGenero;
    private List<Genero_Pelicula_Serie> listaGPS = new ArrayList<Genero_Pelicula_Serie>();
    private boolean boton = false;

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
     *
     * @throws java.lang.Exception
     */
    public void agregar() throws Exception {
        try {
            if (pelicula_serie != null) {
                if (selectGenero.length > 0) {
                    for (int cont = 0; cont < selectGenero.length; cont++) {
                        Genero_Pelicula_Serie gps = new Genero_Pelicula_Serie();
                        String[] valor = selectGenero[cont].split(",");
                        gps.setGenero(new Genero(Integer.parseInt(valor[0]), valor[1]));
                        gps.setPelicula_serie(pelicula_serie);
                        Genero_Pelicula_Serie temp = Control_Genero_Pelicula_Serie.control_GetExitenciaTupla(gps);
                        if (temp != null) {
                            FacesContext.getCurrentInstance().addMessage(
                                    null,
                                    new FacesMessage(
                                            FacesMessage.SEVERITY_WARN,
                                            "ADVERTENCIA",
                                            "El registro \nGenero :" + temp.getGenero().getNombre()
                                            + " Pelicula o Serie :" + temp.getPelicula_serie().getTitulo()
                                            + ". \nYa se encuentra agregado en la Bases de Datos. "
                                            + "\nVerifica los datos por favor y vuelve a intentarlo."));

                        } else {
                            listaGPS.add(gps);
                        }
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "Debes seleccionar por lo menos un genero para agregar."));
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
        if (pelicula_serie != null) {
            listaGPS.clear();
            listaGPS = Control_Genero_Pelicula_Serie.control_GetAllTuplas(pelicula_serie);
            boton = true;
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
     * @param objEntity de tipo String.
     * @throws java.lang.Exception
     */
    public void quitarElemento(Genero_Pelicula_Serie objEntity) throws Exception {
        try {
            Control_Genero_Pelicula_Serie.control_eliminar(objEntity);
            for (int cont = 0; cont < listaGPS.size(); cont++) {
                if (listaGPS.get(cont).getGenero().getNombre().equals(objEntity.getGenero().getNombre())) {
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

    /**
     * Metodo para listar un dato de la tabla en la base de datos.
     *
     * @param entity de tipo de la clase determinada.
     * @throws Exception Mesaje Error.
     */
    public void leerID(Genero_Pelicula_Serie entity) throws Exception {
        try {
            Genero_Pelicula_Serie temp = Control_Genero_Pelicula_Serie.control_leerID(entity);
            if (temp != null) {
                this.entity = temp;
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para modificar una tuplas de la tabla.
     *
     * @param objEntity_ps
     * @param objEntity_gen
     * @throws java.lang.Exception
     */
    public void modificarElemento(Pelicula_Serie objEntity_ps,Genero objEntity_gen) throws Exception {
        Genero_Pelicula_Serie temp=new Genero_Pelicula_Serie();
        temp.setGenero(objEntity_gen);
        temp.setPelicula_serie(objEntity_ps);
        

    }
}
