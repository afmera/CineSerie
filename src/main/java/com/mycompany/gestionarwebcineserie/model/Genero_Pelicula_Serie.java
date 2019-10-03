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
public class Genero_Pelicula_Serie {
    private int id;
    private Genero genero;
    private Pelicula_Serie pelicula_serie;

    public Genero_Pelicula_Serie() {
    }

    public Genero_Pelicula_Serie(int id, Genero genero, Pelicula_Serie pelicula_serie) {
        this.id = id;
        this.genero = genero;
        this.pelicula_serie = pelicula_serie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Pelicula_Serie getPelicula_serie() {
        return pelicula_serie;
    }

    public void setPelicula_serie(Pelicula_Serie pelicula_serie) {
        this.pelicula_serie = pelicula_serie;
    }

    @Override
    public String toString() {
        return "Genero_Pelicula_Serie{" + "id=" + id + ", genero=" + genero + ", pelicula_serie=" + pelicula_serie + '}';
    }
}
