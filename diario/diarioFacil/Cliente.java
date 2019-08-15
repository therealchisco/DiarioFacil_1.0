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
public class Cliente extends Usuario{
        /*
     *
     d8b   db  .d88b.  d888888b  .d8b.  .d8888.
     888o  88 .8P  Y8. `~~88~~' d8' `8b 88'  YP
     88V8o 88 88    88    88    88ooo88 `8bo.
     88 V8o88 88    88    88    88~~~88   `Y8b.
     88  V888 `8b  d8'    88    88   88 db   8D
     VP   V8P  `Y88P'     YP    YP   YP `8888Y' *

      hOLA AQUI HAY UN PAR DE INFOS SOBRE
      lOS PRINCIPALES CAMBIOS A ESTA CLASE
      */

     /*
     1. SE AGREGON LOS ATRIBUTOS
     */

    private  List<Factura> historialDeFacturas = new ArrayList<>();
    private List<Orden> historialDeOrdenes= new ArrayList<>(); // Tal vez no tan importante
    private Factura carrito = new Factura(); //


    /*
    2. SE CREARON LOS METODOS
    */

    public Factura getCarrito(){
        return carrito;
    }

    public void limpiarCarrito(){
        carrito = new Factura();
    }

    private void agregarAlCarrito(Orden orden){
        carrito.agregarOrdenes(orden);

    }

    private void completarCarrito(){
        historialDeFacturas.add(carrito);
        DiarioFacil.agregarFactura(carrito);
        limpiarCarrito();
    }


    public List<Factura> getHistorialDeFacturas(){
        updateFacturas();
        return historialDeFacturas;
    }

    /*

    d88888b db    db d888888b .d8888. d888888b d88888b d8b   db d888888b d88888b
    88'     `8b  d8'   `88'   88'  YP `~~88~~' 88'     888o  88 `~~88~~' 88'
    88ooooo  `8bd8'     88    `8bo.      88    88ooooo 88V8o 88    88    88ooooo
    88~~~~~  .dPYb.     88      `Y8b.    88    88~~~~~ 88 V8o88    88    88~~~~~
    88.     .8P  Y8.   .88.   db   8D    88    88.     88  V888    88    88.
    Y88888P YP    YP Y888888P `8888Y'    YP    Y88888P VP   V8P    YP    Y88888P

     */


    private void updateFacturas(){
        List<Factura> temp = new ArrayList<>();

        for(Factura factura: DiarioFacil.getListaFacturas()){
            if(factura.getCliente().equals(this)){
                temp.add(factura);
            }
        }
        historialDeFacturas= temp;
    }

    public List<Orden> getHistorialOrdenes(){
        List<Orden> temp = new ArrayList<>();

        updateFacturas();

        for(Factura f: historialDeFacturas){
            temp.addAll(f.getListaOrdenes());
        }
        return temp;
    }


    private boolean frecuente=false;
    private int cantidadCompras=0;
    private String preguntaSeguridad="";
    private String respuestaSeguridad="";




    public Cliente() {
    }

    public Cliente( String nombrePersona, String contraPersona, int ced, String pre, String res) {
        super(nombrePersona, contraPersona,ced);
        this.preguntaSeguridad=pre;
        this.respuestaSeguridad=res;
    }

    public void agregarOrdenAHistorial(Orden orden){
        historialDeOrdenes.add(orden);
    }

    public boolean isFrecuente() {
        return frecuente;
    }

    public void setFrecuente(boolean frecuente) {
        this.frecuente = frecuente;
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }

    public String getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    public void setPreguntaSeguridad(String preguntaSeguridad) {
        this.preguntaSeguridad = preguntaSeguridad;
    }

    public String getRespuestaSeguridad() {
        return respuestaSeguridad;
    }

    public void setRespuestaSeguridad(String respuestaSeguridad) {
        this.respuestaSeguridad = respuestaSeguridad;
    }
    
   
}
