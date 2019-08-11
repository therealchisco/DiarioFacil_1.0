/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;


/**
 *
 * @author USER
 */
public class Proveedor {
    int codigo=0;
    String nombre="";
    String correo="";

    public Proveedor() {
    }
public Proveedor(int cod, String nom, String correo){
    this.codigo=cod;
    this.nombre=nom;
    this.correo=correo;
}
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
