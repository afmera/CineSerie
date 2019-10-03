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
public class PPS_Actor {
    private int id;
    private Persona persona;
    private Pelicula_Serie pelicula_serie;
    private String tipo;
    private String tiempo_pantalla;

    public PPS_Actor() {
    }

    public PPS_Actor(int id, Persona persona, Pelicula_Serie pelicula_serie, String tipo, String tiempo_pantalla) {
        this.id = id;
        this.persona = persona;
        this.pelicula_serie = pelicula_serie;
        this.tipo = tipo;
        this.tiempo_pantalla = tiempo_pantalla;
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

    public Pelicula_Serie getPelicula_serie() {
        return pelicula_serie;
    }

    public void setPelicula_serie(Pelicula_Serie pelicula_serie) {
        this.pelicula_serie = pelicula_serie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTiempo_pantalla() {
        return tiempo_pantalla;
    }

    public void setTiempo_pantalla(String tiempo_pantalla) {
        this.tiempo_pantalla = tiempo_pantalla;
    }

    @Override
    public String toString() {
        return "PPS_Actor{" + "id=" + id + ", persona=" + persona + ", pelicula_serie=" + pelicula_serie + ", tipo=" + tipo + ", tiempo_pantalla=" + tiempo_pantalla + '}';
    }
}
