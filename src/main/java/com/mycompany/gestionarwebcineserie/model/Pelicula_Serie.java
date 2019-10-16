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
public class Pelicula_Serie {

    private int id;
    private String titulo;
    private String ano_lanzamiento;
    private String duracion;
    private String sinopsis;
    private String tipo;

    public Pelicula_Serie() {
    }

    public Pelicula_Serie(int id) {
        this.id = id;
    }

    public Pelicula_Serie(String titulo, String ano_lanzamiento, String duracion, String sinopsis, String tipo) {
        this.titulo = titulo;
        this.ano_lanzamiento = ano_lanzamiento;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.tipo = tipo;
    }

    public Pelicula_Serie(int id, String titulo, String ano_lanzamiento, String duracion, String sinopsis, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.ano_lanzamiento = ano_lanzamiento;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.tipo = tipo;
    }

    public String getAno_lanzamiento() {
        return ano_lanzamiento;
    }

    public void setAno_lanzamiento(String ano_lanzamiento) {
        this.ano_lanzamiento = ano_lanzamiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pelicula_Serie other = (Pelicula_Serie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
    }
}
