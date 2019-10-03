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
public class PPS_Productor {
    private int id;
    private Persona persona;
    private Pelicula_Serie pelicual_serie;
    private String tipo;

    public PPS_Productor() {
    }

    public PPS_Productor(int id, Persona persona, Pelicula_Serie pelicual_serie, String tipo) {
        this.id = id;
        this.persona = persona;
        this.pelicual_serie = pelicual_serie;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Pelicula_Serie getPelicual_serie() {
        return pelicual_serie;
    }

    public void setPelicual_serie(Pelicula_Serie pelicual_serie) {
        this.pelicual_serie = pelicual_serie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "PPS_Productor{" + "id=" + id + ", persona=" + persona + ", pelicual_serie=" + pelicual_serie + ", tipo=" + tipo + '}';
    }
}
