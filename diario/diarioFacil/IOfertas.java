/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;


import java.util.List;

/**
 *
 * @author Laboratorio
 */
public interface IOfertas {
    
    public List<Item> getListaItems();

    public void setListaItems(List<Item> listaItems);

    public void agregarItem(Item item);
    
    public boolean isActivo();

    public void setActivo(boolean activo);
  
}
