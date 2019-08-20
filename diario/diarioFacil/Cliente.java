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

    /** PARAMETROS
     *
     */

    private boolean frecuente=false;
    private int cantidadCompras=0;
    private String preguntaSeguridad="";
    private String respuestaSeguridad="";
    private List<Factura> historialDeFacturas = new ArrayList<>(); // Una Factura es una Compra (Coleccion de Ordenes)
    private List<Orden> historialDeOrdenes= new ArrayList<>(); // Una Orden es una cantidad n de Productos
    private Factura carrito = new Factura(); // El Carrito es la Compra


    /** CONSTRUCTORES
     *
     *
     */

    public Cliente() {}

    public Cliente( String nombrePersona, String contraPersona, int ced, String pre, String res) {
        super(nombrePersona, contraPersona,ced);
        this.preguntaSeguridad=pre;
        this.respuestaSeguridad=res;
    }

    /** GETTERS SETTERS
     *
     *
     */

    public Factura getCarrito(){
        return carrito;
    }

    public List<Factura> getHistorialDeFacturas(){
        updateFacturas();
        return historialDeFacturas;
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




    public void limpiarCarrito(){
        carrito = new Factura();
    }

    private void agregarAlCarrito(Orden orden){
        carrito.agregarOrdenes(orden);
    }

    public void completarCarrito(){
        historialDeFacturas.add(carrito);
        DiarioFacil.agregarFactura(carrito);
        limpiarCarrito();
        actualizarCompras();
    }


    private void actualizarCompras(){
        cantidadCompras = historialDeFacturas.size();
        frecuente = cantidadCompras>=5;
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







    public void agregarOrdenAHistorial(Orden orden){
        historialDeOrdenes.add(orden);
    }


    @Override
    public String toString() {
        String frec = " : ";
        if(frecuente){
            frec = " frecuente: ";
        }
        return "Cliente" + frec + super.toString();
    }
}
