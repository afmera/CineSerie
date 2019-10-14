/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestionarwebcineserie.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class Conexion {

    
    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * Metodo para conectarse a la base de datos.
     *
     * @throws Exception mensaje del error de conexion
     */
    public void conectar() throws Exception {
        try {
            String userName = "postgres";//Nombre del Usario de acceso a la base de datos.
            String passContrasena = "postgres";//Contraseña de acceso a la base de datos.
            String bd_name = "db_cine_serie";//Nombre de la base de datos
            String urlDatabase = "jdbc:postgresql://localhost:5432/" + bd_name;//Direccion de la base de datos.
            Class.forName("org.postgresql.Driver");//Agregacion del nombre del driver de coneccion
            cn = DriverManager.getConnection(urlDatabase, userName, passContrasena);//coneccion a la base de datos por el administrador del controlador
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Sea presentado al consultar en la base de datos.\n" + ex));
            System.out.println("Error " + ex);
            throw ex;
        }
    }

    /**
     * Metodo para cerrar la conexion a la base de datos.
     *
     * @throws java.lang.Exception mensaje del error de conexion
     */
    public void cerrar() throws Exception {
        try {
            if (cn != null) {
                if (cn.isClosed() == false) {
                    cn.close();
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
