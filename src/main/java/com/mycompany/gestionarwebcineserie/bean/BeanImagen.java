/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Juan Bautista
 */
@ManagedBean
@ViewScoped
public class BeanImagen {
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
//        for (int i = 1; i <= 12; i++) {
            images.add("portada_1.jpg");
//        }
    }
 
    public List<String> getImages() {
        return images;
    }
}
