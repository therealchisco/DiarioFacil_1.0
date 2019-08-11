/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.diario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Roberto
 */
public class Factura {
    private Usuario cliente;
    private int codFactura;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");	
    private Calendar calendar = new GregorianCalendar();
    private double montoTotal;
    public List<Orden> listaOrdenes=new ArrayList<>();

    public Factura() {
    }

    public Factura(Usuario cliente, int codFactura) {
        this.cliente = cliente;
        this.codFactura = codFactura;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public int getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(int codFactura) {
        this.codFactura = codFactura;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(List<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }
    
    public void agregarOrdenes(Orden o){
        listaOrdenes.add(o);
    }
    
}
