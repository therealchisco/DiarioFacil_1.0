/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

    public static int codigoFactura = 0;

    private static void actualizarCodigoFactura(){
        codigoFactura = listaFacturas.size();
    }

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
        DiarioFacil.actualizarCodigoFactura();
    }

      
    public static void agregarFactura(Factura f){
        listaFacturas.add(f);
        actualizarCodigoFactura();
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

    public static List<Promocion> getListaPromociones() {
        return listaPromociones;
    }

    public static void setListaPromociones(List<Promocion> listaPromociones) {
        DiarioFacil.listaPromociones = listaPromociones;
    }
    
    public static void agregarPromociones(Promocion p){
        listaPromociones.add(p);

    }

    public static void eliminarPromocion(int posicion){
        listaPromociones.remove(posicion);
        mostrar("El elemento "+posicion+" ha sido removido con exito");
    }

    public static void cambiarActivoInactivo(int posicion){
        listaPromociones.get(posicion).cambiarEstado();
        String activa = listaPromociones.get(posicion).estaActiva();
        mostrar("La promocion numero "+posicion+" ahora está " + activa );
    }

    public static void ajustarDescuento(int posicion, int porcentaje){
        listaPromociones.get(posicion).setDescuento(porcentaje);
        mostrar("El descuento de la Promocion "+posicion+" ha sido ajustado a "+porcentaje+"%");
    }

    @Override
    public String toString() {
        String memo="";
        for(Usuario p:lstUsuarios){
            memo += p.getNombrePersona();
        }
        return "DiarioFacil " + memo;
    }

    private static void mostrar(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }

    private static void mostrar(StringBuffer mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }
    
}
