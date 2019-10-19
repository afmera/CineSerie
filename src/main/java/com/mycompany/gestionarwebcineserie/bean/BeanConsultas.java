/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import com.mycompany.gestionarwebcineserie.control.Control_Favorita;
import com.mycompany.gestionarwebcineserie.control.Control_Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.control.Control_PPS_Actor;
import com.mycompany.gestionarwebcineserie.control.Control_PPS_Director;
import com.mycompany.gestionarwebcineserie.control.Control_Peliculas_Serie;
import com.mycompany.gestionarwebcineserie.control.Control_Persona;
import com.mycompany.gestionarwebcineserie.model.Favorita;
import com.mycompany.gestionarwebcineserie.model.Genero_Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.PPS_Actor;
import com.mycompany.gestionarwebcineserie.model.PPS_Director;
import com.mycompany.gestionarwebcineserie.model.Pelicula_Serie;
import com.mycompany.gestionarwebcineserie.model.Persona;
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
        if (op.equals("act")) {
            List<PPS_Actor> lista = Control_PPS_Actor.control_listar();
            selectedOpcion = new Object[lista.size()];
            for (PPS_Actor l : lista) {
                selectedOpcion[cont] = l.getPersona().getNombre();
                cont++;
            }
        }
        if (op.equals("dir")) {
            List<PPS_Director> lista = Control_PPS_Director.control_listar();
            selectedOpcion = new Object[lista.size()];
            for (PPS_Director l : lista) {
                selectedOpcion[cont] = l.getPersona().getNombre();
                cont++;
            }
        }
        if (op.equals("cal")) {
//            List<Favorita> lista = Control_Favorita.control_listar();
//            selectedOpcion = new Object[lista.size()];
//            for (Favorita l : lista) {
//                selectedOpcion[cont] = l.getCalificacion();
//                cont++;
//            }
selectedOpcion= new Object[5];
selectedOpcion[0]="1";
selectedOpcion[1]="2";
selectedOpcion[2]="3";
selectedOpcion[3]="4";
selectedOpcion[4]="5";
        }
        if (op.equals("gen")) {
            List<Genero_Pelicula_Serie> lista = Control_Genero_Pelicula_Serie.control_listar();
            selectedOpcion = new Object[lista.size()];
            for (Genero_Pelicula_Serie l : lista) {
                selectedOpcion[cont] = l.getGenero().getNombre();
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
        String valor = this.valorEditable;
        listaEntities.clear();
        if (op.equals("ps")) {
            Pelicula_Serie objEntity = new Pelicula_Serie();
            objEntity.setTitulo(valor);
            listaEntities = Control_Peliculas_Serie.control_ConsultarNombre(objEntity);
        }
        if (op.equals("act")) {
            Persona obj = new Persona();
            obj.setNombre(valor);
            PPS_Actor objEntity = new PPS_Actor();
            objEntity.setPersona(obj);
            List<PPS_Actor> lista = Control_PPS_Actor.control_ListarNombreActor(objEntity);
            for (PPS_Actor act : lista) {
                listaEntities.add(act.getPelicula_serie());
            }
        }
        if (op.equals("dir")) {
            Persona obj = new Persona();
            obj.setNombre(valor);
            PPS_Director objDirector = new PPS_Director();
            objDirector.setPersona(obj);
            List<PPS_Director> lista = Control_PPS_Director.control_ListarNombreDirector(objDirector);
            for (PPS_Director direc : lista) {
                listaEntities.add(direc.getPelicula_serie());
            }
        }
        if (op.equals("cal")) {
            try {
                Favorita obj = new Favorita();
                obj.setCalificacion(Integer.parseInt(valor));
                List<Favorita> lista = Control_Favorita.control_GetTuplasCalificacion(obj);
                for (Favorita f : lista) {
                    listaEntities.add(f.getPelicula_serie());
                }
            } catch (Exception ex) {
                System.out.println("Error es : " + ex);
            }
        }
        if (op.equals("gen")) {

        }
    }
}
