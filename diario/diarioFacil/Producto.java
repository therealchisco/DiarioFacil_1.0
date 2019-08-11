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
public class Producto {
   int codProveedor=0;
   int codProducto=0;
   String nombreProd="";
   int stockMinimo=0;
   int stockActual=0;
   double precio=0;

    public Producto() {
    }
   public Producto(int codProveedor, int codProd,String nom, int stockMin, int stockActual,double precio){
       this.codProducto=codProd;
       this.codProveedor=codProveedor;
       this.nombreProd=nom;
       this.stockActual=stockActual;
       this.stockMinimo=stockMin;
       this.precio=precio;
   }

    public int getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(int codProveedor) {
        this.codProveedor = codProveedor;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
   
}
