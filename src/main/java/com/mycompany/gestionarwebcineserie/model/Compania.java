/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.model;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Compania {
    private int id;
    private String nombre;
    private String majos;
    private String nacionalidad;
    private String streaming;

    public Compania() {
    }

    public Compania(int id) {
        this.id = id;
    }

    public Compania(int id, String nombre, String majos, String nacionalidad, String streaming) {
        this.id = id;
        this.nombre = nombre;
        this.majos = majos;
        this.nacionalidad = nacionalidad;
        this.streaming = streaming;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMajos() {
        return majos;
    }

    public void setMajos(String majos) {
        this.majos = majos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getStreaming() {
        return streaming;
    }

    public void setStreaming(String streaming) {
        this.streaming = streaming;
    }

    @Override
    public String toString() {
        return "Compania{" + "id=" + id + ", nombre=" + nombre + ", majos=" + majos + ", nacionalidad=" + nacionalidad + ", streaming=" + streaming + '}';
    }
}
