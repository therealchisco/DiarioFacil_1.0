/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laboratorio
 */
public class Promocion implements IOfertas{
    
    private List<Item> listaItems = new ArrayList<>();
    private boolean activo=false;
    private double descuento = 0;
    
    public Promocion(double descuento){
        this.descuento = descuento;
    }
    
    private double aplicarDescuento(){
        return 0;
    }
    
    public double aplicarDescuento(Item item){
        double precioItem = item.getProd().getPrecio();
        return aplicarDescuento(precioItem);
    }
    
    private double aplicarDescuento(double precioItem){
        return precioItem - (precioItem * (descuento/100));
    }
    
    
    
    
    
    
    /**
     * Develve la lista de productos que estan en promicon/ tienen descuento
     * @return 
     */
    @Override
    public List<Item> getListaItems() {
        return listaItems;
    }

    @Override
    public void setListaItems(List<Item> listaItems) {
        this.listaItems = listaItems;
    }

    @Override
    public void agregarItem(Item item){
        listaItems.add(item);
    }

    @Override
    public boolean isActivo() {
        return activo;
    }

    @Override
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
  
}
