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
 * @author USER
 */
public class Combos implements IOfertas{
    private int codCombo=0;
    private String nombreCombo="";
    private List<Item> listaItems = new ArrayList<>();
    private double precio=0;
    private boolean activo=false;
    
    public Combos() {
    }
    
    public Combos(int cod, String nom){
          this.codCombo=cod;
          this.nombreCombo=nom;
    }

    public int getCodCombo() {
        return codCombo;
    }

    public void setCodCombo(int codCombo) {
        this.codCombo = codCombo;
    }

    public String getNombreCombo() {
        return nombreCombo;
    }

    public void setNombreCombo(String nombreCombo) {
        this.nombreCombo = nombreCombo;
    }

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
