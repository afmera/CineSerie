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
public class Compania_Pelicula_Serie {
    private int id;
    private Compania compania;
    private Pelicula_Serie pelicual_serie;

    public Compania_Pelicula_Serie() {
    }

    public Compania_Pelicula_Serie(int id, Compania compania, Pelicula_Serie pelicual_serie) {
        this.id = id;
        this.compania = compania;
        this.pelicual_serie = pelicual_serie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public Pelicula_Serie getPelicual_serie() {
        return pelicual_serie;
    }

    public void setPelicual_serie(Pelicula_Serie pelicual_serie) {
        this.pelicual_serie = pelicual_serie;
    }

    @Override
    public String toString() {
        return "Compania_Pelicula_Serie{" + "id=" + id + ", compania=" + compania + ", pelicual_serie=" + pelicual_serie + '}';
    }
}
