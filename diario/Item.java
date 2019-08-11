/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.diario;

/**
 *
 * @author USER
 */
public class Item {
   private int consecutivo=0;
   private Producto prod;
   private int cantidad=0;

    public Item() {
      
    }
   public Item(int consecutivo, Producto prod,int cant){
       this.prod=prod;
       this.consecutivo=consecutivo;
       this.cantidad=cant;
   }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Producto getProd() {
        return prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
   
   
}
