/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;


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
        Categoria cat2=new Categoria(2,"Carnes");
        Categoria cat3=new Categoria(3,"Bebidas");
        Categoria cat4 = new Categoria(4,"Vegetales");
        Categoria cat5 = new Categoria(5,"Frutas");
        Categoria cat6=new Categoria(6,"Licores");

        DiarioFacil.agregarCategoria(cat);


        Proveedor prov=new Proveedor(1,"Dos Pinos","contacto@2pinos.com");
        Proveedor prov2=new Proveedor(2,"Florida","ordenes@floridas.com");
        Proveedor prov3=new Proveedor(3,"Don Fernando","contacto@donfernando.com");
        Proveedor prov4=new Proveedor(3,"Feria Verda","conracto@feriaverde.com");

        for(Categoria ca:DiarioFacil.getListaCategorias()){
            if(ca.getCodCategoria()==1){
                Producto prod= new Producto(1,1,"leche 1 litro", 10,20,500);
                Producto prod2 = new Producto(1,2,"Yogurt Fresa 300ml",10,20,600);
                Producto prod3 = new Producto(1,3,"Yogurt Fresa 300ml",10,20,1200);
                Producto prod4 = new Producto(1,4,"Queso Palmito 0.5 kg",10,20,2000);
                Producto prod5 = new Producto(1,5,"Yogurt Fresa 300ml",10,20,600);
                ca.agregarProducto(prod);
                ca.agregarProducto(prod2);
                ca.agregarProducto(prod3);
                ca.agregarProducto(prod4);
                ca.agregarProducto(prod5);
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
