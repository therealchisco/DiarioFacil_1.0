/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;



/**
 *
 * @author Roberto
 */
public class Cliente extends Usuario{
    
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
