/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;


/**
 *
 * @author Roberto
 */
public class Orden {
    private Producto producto;
    private int cantidad;
    private double subtotal;
    private Combos combo;

    public Orden() {
    }

    public Orden(Producto producto, int cantidad, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Orden(Combos combo) {
        this.combo = combo;
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Combos getCombo() {
        return combo;
    }

    public void setCombo(Combos combo) {
        this.combo = combo;
    }
    
    public void eliminarProductos(){
        this.producto = null;
        this.cantidad = 0;
        this.subtotal = 0;
    }
    
    public void eliminarCombos(){
        this.combo = null;
    }
    
}
