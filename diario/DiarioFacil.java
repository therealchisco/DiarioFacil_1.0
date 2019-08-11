/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.diario;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Roberto
 */
public class DiarioFacil {
    
    // Listas de Objetos
    public static List<Usuario> lstUsuarios = new ArrayList<>();
    public static List<Categoria> listaCategorias=new ArrayList<>();
    public static List<Proveedor> listaProveedores=new ArrayList<>();
    public static List<Factura> listaFacturas=new ArrayList<>();
    public static List<Combos> listaCombos=new ArrayList<>();
    public static List<Promocion> listaPromociones = new ArrayList();


    public DiarioFacil() {
    }

    public static List<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<Usuario> lstUsuarios) {
        DiarioFacil.lstUsuarios = lstUsuarios;
    }
    
    public static void agregarProveedor(Proveedor p){
        listaProveedores.add(p);
    }
    
    public static void  agregarUsuario(Usuario u){
        lstUsuarios.add(u);
    }
    public static void agregarCategoria(Categoria c){
        listaCategorias.add(c);
    }

    public static List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public static void setListaCategorias(List<Categoria> listaCategorias) {
        DiarioFacil.listaCategorias = listaCategorias;
    }

    public static List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public static void setListaProveedores(List<Proveedor> listaProveedores) {
        DiarioFacil.listaProveedores = listaProveedores;
    }


    public static List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public static void setListaFacturas(List<Factura> listaFacturas) {
        DiarioFacil.listaFacturas = listaFacturas;
    }
      
    public static void agregarFactura(Factura f){
        listaFacturas.add(f);
    }
    
    public static List<Combos> getListaCombos() {
        return listaCombos;
    }

    public static void setListaCombos(List<Combos> listaCombos) {
        DiarioFacil.listaCombos = listaCombos;
    }
    
    public static void agregarCombo(Combos c){
        listaCombos.add(c);

    }
    
    @Override
    public String toString() {
        String memo="";
        for(Usuario p:lstUsuarios){
            memo += p.getNombrePersona();
        }
        return "DiarioFacil " + memo;
    }
    
    
}
