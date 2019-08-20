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
    private Factura carrito = new Factura(this,DiarioFacil.codigoFactura); // El Carrito es la Compra


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


    public Factura getUltimaCompra(){
        return historialDeFacturas.get(cantidadCompras);
    }

    public void limpiarCarrito(){
        carrito = new Factura(this,DiarioFacil.codigoFactura);
    }

    private void agregarAlCarrito(Orden orden){
        carrito.agregarOrdenes(orden);
    }

    public void repetirOrden(){
        limpiarCarrito();
        Factura ultima = getUltimaCompra();
        carrito.agregarOrdenes(ultima.getListaOrdenes());
    }

    public void completarCarrito(){
        historialDeFacturas.add(carrito);
        DiarioFacil.agregarFactura(carrito);
        limpiarCarrito();
        actualizarCompras();
    }

    public void cambiarCantidad(int posicion){
        int cantidad = -1;
        while(cantidad< 1){
           cantidad = inputInt("Ingrese Nueva Cantidad");
        }
        carrito.getListaOrdenes().get(posicion).setCantidad(cantidad);
        JOptionPane.showMessageDialog(null,"Cantidad Modificada con Exito");
    }


    private int inputInt(String mensaje){
        int input = 0;
        try{
            input = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Por Favor Ingrese un numero Entero","Formato Incorrecto",JOptionPane.ERROR_MESSAGE);
            return inputInt(mensaje);
        }

        return input;
    }

    public void agregarOrdenACarrito(Orden orden){
        carrito.agregarOrdenes(orden);
    }

    private void actualizarCompras(){
        cantidadCompras = historialDeFacturas.size();
        frecuente = cantidadCompras>=5;
    }

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
