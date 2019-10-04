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
public class Persona {
    private int id;
    private String nombre;
    private String genero;
    private String fecha_nacimiento;

    public Persona() {
    }

    public Persona(int id) {
        this.id = id;
    }

    public Persona(int id, String nombre, String genero, String fecha_nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.fecha_nacimiento = fecha_nacimiento;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", genero=" + genero + ", fecha_nacimiento=" + fecha_nacimiento + '}';
    }
}
