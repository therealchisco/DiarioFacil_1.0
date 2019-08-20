/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;


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

    public void agregarOrdenes(List<Orden> lista){
        listaOrdenes.addAll(lista);
    }

    public String getFechaString(){
        String fechaString;
        sdf.setTimeZone(calendar.getTimeZone());
        fechaString = sdf.format(calendar.getTime());
        return fechaString;
    }

    /*_____                _     _
     / ____|              | |   (_)
    | |     __ _ _ __ ___ | |__  _  ___  ___
    | |    / _` | '_ ` _ \| '_ \| |/ _ \/ __|
    | |___| (_| | | | | | | |_) | | (_) \__ \
     \_____\__,_|_| |_| |_|_.__/|_|\___/|___/*/

    @Override
    public String toString() {
        return "Factura #" + codFactura + " " + cliente.toString() + " " + getFechaString();
    }
    
    public void eliminarOrdenes(){
        listaOrdenes.removeAll(listaOrdenes);
    }
    
}
