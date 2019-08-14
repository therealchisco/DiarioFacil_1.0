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
 * @author Roberto
 */
public class Categoria {
    private int codCategoria=0;
    private String nombreCategoria="";
    private List<Producto> listaProductos=new ArrayList<>();

    public Categoria() {
    }

    public Categoria(int cod, String nom){
        this.codCategoria=cod;
        this.nombreCategoria=nom;
    }
    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    public void agregarProducto(Producto p){
      listaProductos.add(p);
    }
}
