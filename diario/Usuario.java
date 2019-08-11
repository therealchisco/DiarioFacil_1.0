/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.diario;

/**
 *
 * @author Roberto
 */
public abstract class Usuario {
    protected String nombrePersona;
    protected String contraPersona;
    protected int cedula=0;
    public Usuario() {
    } 

    public Usuario(String nombrePersona, String contraPersona, int ced) {
        this.nombrePersona = nombrePersona;
        this.contraPersona = contraPersona;
        this.cedula=ced;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getContraPersona() {
        return contraPersona;
    }

    public void setContraPersona(String contraPersona) {
        this.contraPersona = contraPersona;
    }    

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    
    
}
