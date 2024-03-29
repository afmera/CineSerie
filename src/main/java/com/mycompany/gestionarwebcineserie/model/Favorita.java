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
public class Favorita {
    private int id;
    private Integer calificacion;
    private String comentario;
    private Pelicula_Serie pelicula_serie;

    public Favorita() {
    }

    public Favorita(int id) {
        this.id = id;
    }

    public Favorita(Pelicula_Serie pelicula_serie) {
        this.pelicula_serie = pelicula_serie;
    }

    public Favorita(int id, Integer calificacion, String comentario, Pelicula_Serie pelicula_serie) {
        this.id = id;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.pelicula_serie = pelicula_serie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Pelicula_Serie getPelicula_serie() {
        return pelicula_serie;
    }

    public void setPelicula_serie(Pelicula_Serie pelicula_serie) {
        this.pelicula_serie = pelicula_serie;
    }

    @Override
    public String toString() {
        return "Favorita{" + "id=" + id + ", calificacion=" + calificacion + ", comentario=" + comentario + ", pelicula_serie=" + pelicula_serie + '}';
    }
}
