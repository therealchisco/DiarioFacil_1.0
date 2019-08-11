/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.diario;

import javax.swing.JOptionPane;

/**
 *
 * @author Roberto
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DiarioFacil df = new DiarioFacil();

        Usuario u=new Administrador("Jose Quesada","1234",1);
        Usuario c=new Cliente("Manuel Ramírez","1515",2,"¿Cuál fue su primera mascota?","un perro");
     
        DiarioFacil.agregarUsuario(u);
        DiarioFacil.agregarUsuario(c);
       
        Categoria cat=new Categoria(1,"Lácteos");
        
        DiarioFacil.agregarCategoria(cat);
        
        Proveedor prov=new Proveedor(1,"3 robles","contacto@3robles.com");
        for(Categoria ca:DiarioFacil.getListaCategorias()){
            if(ca.getCodCategoria()==1){
                Producto prod= new Producto(1,1,"leche 1 litro", 10,20,500);
                ca.agregarProducto(prod);
            }
        }
       
        menuPrincipal();
    }
    public static void menuPrincipal(){
        BackOffice bo = new BackOffice();
        FrontOffice fo = new FrontOffice();
        
        boolean formatoIncorrecto=false;
        
        int opcion=0;
         do{
              formatoIncorrecto=false;
             try{
              opcion=Integer.parseInt(JOptionPane.showInputDialog
                (null,"Bienvenido a Diario Facil\n"+
                        "Ingrese una opción\n"+
                        "1. Administrador\n"+
                        "2. Cliente\n"+
                        "3. Salir")); 
            
            switch(opcion){
                case 1:
                   bo.iniciarSesion();
                break;
                    
                case 2:
                    fo.menu(); 
                break;
               
                case 3:
                    System.exit(0);
                break;
            }   
             }catch(NumberFormatException nfe){
                 formatoIncorrecto=true;
                 JOptionPane.showMessageDialog
        (null,"La opción ingresada no tiene el formato correcto"+
                ", recuerde usar solo números");
             }
            
            

            
        }while(opcion>3 ||opcion<1 ||formatoIncorrecto==true);
    }
}
