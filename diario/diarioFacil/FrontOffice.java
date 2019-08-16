/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;



import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ListIterator;

/**
 *
 * @author Roberto
 */
public class FrontOffice implements IHistorial{
int codFactura=0;
    Cliente client;    
    public void verProductos(){
            StringBuffer lista = new StringBuffer();
           lista.append("Lista de productos \n");
           
           
           for(Categoria c:DiarioFacil.getListaCategorias()){
               for(Producto p:c.getListaProductos()){
                 lista.append("Código: ");
                 lista.append(p.getCodProducto());
                 lista.append("\t nombre:");
                 lista.append(p.getNombreProd());
                 lista.append("\t Precio:");
                 lista.append(p.getPrecio());
                 lista.append("\t Stock actual:");
                 if(p.getStockActual()==0){
                     lista.append("Agotado");
                 }else{
                     lista.append(p.getStockActual());
                 }
                 lista.append("\n");
                 
                 
               }
              
               
           }
           JOptionPane.showMessageDialog(null,lista);
       }
    
    public void verCombos(){
            StringBuffer lista = new StringBuffer();
                       lista.append("Lista de combos \n");

                       for(Combos c:DiarioFacil.getListaCombos()){
                        if(c.isActivo()){
                            lista.append("Código:");
                            lista.append(c.getCodCombo());
                            lista.append("\t Nombre:");
                            lista.append(c.getNombreCombo());
                            lista.append("\t Precio:");
                            lista.append(c.getPrecio());



                            lista.append("\n");
                        }
                       

                       }



                       JOptionPane.showMessageDialog(null,lista);
       }
    
    public void annadirProductoAlCarrito(){
        boolean formatoIncorrecto=false, status=false;
        Producto productoEnviado;
        int codigoFactura=1, opcion=0,canti=0,confirm=0;
        double montoTotal=0;
        do{
            formatoIncorrecto=false;
             
            try{
            opcion=Integer.parseInt(JOptionPane.showInputDialog
            (null,"Digite el código del producto que desea añadir"));
            for(Categoria c:DiarioFacil.getListaCategorias()){
                for(Producto p:c.getListaProductos()){
                    if(opcion==p.codProducto){
                        if(p.stockActual==0){
                            JOptionPane.showMessageDialog(null,"Ese producto se encuentra agotado");
                            formatoIncorrecto=true;
                        }else{
                            do{
                                formatoIncorrecto=false;
                                try{
                                canti=Integer.parseInt(JOptionPane.showInputDialog
                                (null,"Ingrese la cantidad de '"+p.getNombreProd()+"' que desea añadir al carrito"));
                                    if(canti>p.stockActual){
                                        JOptionPane.showMessageDialog(null,"Se lograron añadir solo: "+p.stockActual+" unidades");
                                        canti=p.stockActual;
                                    }
                                    do{
                                        formatoIncorrecto=false;
                                        try{
                                        confirm=Integer.parseInt(JOptionPane.showInputDialog
                                        (null,"Producto: "+p.nombreProd+" Cantidad: "+canti+" Precio: "+canti*p.precio+"\n"
                                        + "¿Está seguro de querer añadir esta cantidad al carrito?\n"
                                        + "(No=0 Si=1)")); 

                                            if(confirm==1){
                                                productoEnviado = p;
                                                montoTotal = canti*p.precio;
                                                if (DiarioFacil.listaFacturas.isEmpty()){
                                                    Factura f = new Factura(client, codigoFactura);
                                                    Orden o = new Orden(productoEnviado, canti, montoTotal);
                                                    f.agregarOrdenes(o);
                                                    DiarioFacil.agregarFactura(f);
                                                }else{
                                                    for(Factura f:DiarioFacil.listaFacturas){
                                                        for(Orden ord:f.listaOrdenes){
                                                            if(ord.getProducto()==p){
                                                                status=true;
                                                            }
                                                        }
                                                        if(f.getCodFactura()==codigoFactura && status==true){
                                                            for(Orden ord:f.listaOrdenes){
                                                                if(ord.getProducto()==p){
                                                                    ord.setCantidad(ord.getCantidad()+canti);
                                                                    ord.setSubtotal(p.precio*ord.getCantidad());
                                                                }
                                                            }
                                                        }else{
                                                            Orden o = new Orden(productoEnviado, canti, montoTotal);
                                                            f.agregarOrdenes(o);
                                                        }
                                                    }
                                                }
                                                
                                            }
                                            JOptionPane.showMessageDialog(null,"Su orden ha sido añadida con éxito");

                                        }catch(NumberFormatException nfe){
                                            formatoIncorrecto=true;
                                            JOptionPane.showMessageDialog(null,"La opción ingresada no tiene el formato correcto, recuerde usar solo números");
                                        }
                                    }while(confirm<0 || confirm>1 ||formatoIncorrecto==true);

                                }catch(NumberFormatException nfe){
                                formatoIncorrecto=true;
                                JOptionPane.showMessageDialog(null,"La opción ingresada no tiene el formato correcto, recuerde usar solo números");
                                }


                            }while(canti<=0 ||formatoIncorrecto==true);
                        }
                   }
                }
            }
              
            }catch(NumberFormatException nfe){
                formatoIncorrecto=true;
                JOptionPane.showMessageDialog
                (null,"La opción ingresada no tiene el formato correcto"+
                       ", recuerde usar solo números");
            }
        }while(opcion<0 ||formatoIncorrecto==true);
    }

    public void annadirComboAlCarrito(){
        boolean formatoIncorrecto=false, status=false;
        Combos comboEnviado;
        int codigoFactura=1, opcion=0,canti=0,confirm=0;
        double montoTotal=0;
        do{
            formatoIncorrecto=false;
             
            try{
            opcion=Integer.parseInt(JOptionPane.showInputDialog
            (null,"Digite el código del combo que desea añadir"));
            for(Combos comb:DiarioFacil.getListaCombos()){
                
                    if(opcion==comb.getCodCombo()){
                        if(comb.isActivo()==false){
                            JOptionPane.showMessageDialog(null,"Ese combo no está disponible");
                            formatoIncorrecto=true;
                        }else{
                            do{
                                formatoIncorrecto=false;
                                
                                do{
                                    formatoIncorrecto=false;
                                    try{
                                    String text="";
                                    for(Item it:comb.getListaItems()){
                                        if(it.getCantidad()==0){
                                            text+="No hay '"+it.getProd().nombreProd+"' para añadir al combo\n";
                                        }
                                    }
                                    
                                    confirm=Integer.parseInt(JOptionPane.showInputDialog
                                    (null,text+"Combo: "+comb.getNombreCombo()+" Precio: "+comb.getPrecio()+"\n"
                                    + "¿Está seguro de querer añadir este combo al carrito?\n"
                                    + "(No=0 Si=1)")); 

                                        if(confirm==1){
                                            comboEnviado = comb;
                                            if (DiarioFacil.listaFacturas.isEmpty()){
                                                Factura f = new Factura(client, codigoFactura);
                                                Orden o = new Orden(comboEnviado);
                                                f.agregarOrdenes(o);
                                                DiarioFacil.agregarFactura(f);
                                            }else{
                                                for(Factura f:DiarioFacil.listaFacturas){
                                                    if(f.getCodFactura()==codigoFactura){
                                                        Orden o = new Orden(comboEnviado);
                                                        f.agregarOrdenes(o);
                                                    }
                                                }
                                            }

                                        }
                                        JOptionPane.showMessageDialog(null,"Su orden ha sido añadida con éxito");

                                    }catch(NumberFormatException nfe){
                                        formatoIncorrecto=true;
                                        JOptionPane.showMessageDialog(null,"La opción ingresada no tiene el formato correcto, recuerde usar solo números");
                                    }
                                }while(confirm<0 || confirm>1 ||formatoIncorrecto==true);

                                


                            }while(canti<=0 ||formatoIncorrecto==true);
                        }
                   }
                
            }
              
            }catch(NumberFormatException nfe){
                formatoIncorrecto=true;
                JOptionPane.showMessageDialog
                (null,"La opción ingresada no tiene el formato correcto"+
                       ", recuerde usar solo números");
            }
        }while(opcion<0 ||formatoIncorrecto==true);
    }
    
    // Fuck my life, yo hablando tanto de eficiencia y esto es peor que O(n^2) lol
    private boolean enPromocion(Producto p){
        boolean enPromocion = false;
        for(Promocion promocion : DiarioFacil.listaPromociones){
            for(Item item : promocion.getListaItems()){
                enPromocion = enPromocion || item.getProd().getCodProducto()==p.getCodProducto();
                if(enPromocion){
                    break;
                }
            }
        }
        return enPromocion;
    }
    
    /**
     * Revisa si el Producto p tiene Promocion y en caso de que sea cierto
     * y en caso de que el cliente que este activo sea frecuente
     * muestra el precio ajustado (con la promocion/descuento aplicado)
     * @param p Producto por 
     * @param clienteFrecuente status cliente activo
     * @return percio ajustado con descuento si clienteFrecuente y p en promocion
     */
    private double ajustarPrecio(Producto p, boolean clienteFrecuente){
        if(clienteFrecuente){
        boolean enPromocion = false;
        for(Promocion promocion : DiarioFacil.listaPromociones){
            for(Item item : promocion.getListaItems()){
                enPromocion = enPromocion || item.getProd().getCodProducto()==p.getCodProducto();
                if(enPromocion){
                    return promocion.aplicarDescuento(item);
                }
            }
        }
        }
        return p.getPrecio();
    }

    private StringBuffer formatearFactura(Factura factura){

        StringBuffer facturaFormateada = new StringBuffer();

        facturaFormateada.append("Fecha: "+factura.getFechaString()+"\n");

        for(Orden o : factura.getListaOrdenes()) {
            facturaFormateada.append(formatearOrden(o));
        }

        facturaFormateada.append("=================================================\n");
        return facturaFormateada;
    }

    private StringBuffer formatearOrden(Orden o){
        StringBuffer ordenFormateada = new StringBuffer();
        ordenFormateada.append("Nombre: ");
        ordenFormateada.append(o.getProducto().getNombreProd()); // Aqui mejor usare el
        ordenFormateada.append("\t Cantidad:");
        ordenFormateada.append(o.getCantidad());
        ordenFormateada.append("\t Precio:");

        // DEPENDIENDO DE SI EL CLIENTE ES FRECUENTE O NO,
        // MOSTRAMOS LOS PRECIOS CON DESCUENTO
        double precio = ajustarPrecio(o.getProducto(), this.client.isFrecuente());

        ordenFormateada.append(precio); // Aqui mejor usar el getPrecio();
        ordenFormateada.append("\t Subtotal:");
        ordenFormateada.append(o.getSubtotal());
        ordenFormateada.append("\n");
        return ordenFormateada;
    }



    // Genera la lista del carrito ajustando mostrando Productos en promocion si el cliente es Frecuente
    private StringBuffer listaCarrito(){
        return formatearFactura(client.getCarrito());
    }




    
    public void verCarrito(){
        StringBuffer listaCarrito = listaCarrito();
        JOptionPane.showMessageDialog(null,listaCarrito);
    }

    public void menu(){
        int op=0;
        do{
           op=Integer.parseInt(JOptionPane.showInputDialog(null, "1) Crear cuenta \n"
                   + "2) Ingresar en una cuenta ya creada \n"
                   + "3) Volver \n"));
           
           switch(op){
               case 1:
                   signUp();
                   break;
               case 2:
                   login();
                   break;
               case 3:
                   Tester.menuPrincipal();
                   break;
           }
        }while(op!=3 || op<1 || op>3);
    }
    
    public void signUp(){
        DiarioFacil df = new DiarioFacil();
        int op=0;
        String nombre="";
        String contra="";
        int cedula=0;
        String prese="";
        String resse="";
        boolean uno=false;
        boolean dos=false;
        boolean tres=false;
        boolean cuatro=false;
        boolean cedulaIncorrecta=false;
        boolean corta=false;
        do{
            do{
                try{
                    cedula=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su cédula"));
                    for(Usuario u:DiarioFacil.getLstUsuarios()){
                        if(u.getCedula()==cedula && u instanceof Cliente){
                            JOptionPane.showMessageDialog(null, "Ese usuario ya existe");
                            cedulaIncorrecta=true;
                        }
                    }
                }catch(NumberFormatException nfe){
                    cedulaIncorrecta=true;
                    JOptionPane.showMessageDialog(null,"Formato de cédula incorrecto. ","Login Administrador",JOptionPane.ERROR_MESSAGE);

                }
            }while(cedula==0 || cedulaIncorrecta==true);
            uno=true;
            do{
                nombre=JOptionPane.showInputDialog(null, "Ingrese su nombre");
            }while("".equals(nombre));
            dos=true;
            do{
                corta=false;
                try{
                    contra=JOptionPane.showInputDialog(null,"Ingrese una contraseña");
                    validarContrasenna(contra);
                }catch(DiarioContraseñaException dce){
                    System.out.print(dce.getMessage());
                }
                if(contra.length()<4){
                    corta=true;
                }
            }while(corta==true);
            tres=true;
            do{
                op=Integer.parseInt(JOptionPane.showInputDialog(null, "Elija una pregunta de seguridad \n"
                    + "1) ¿Cuál fue su primera mascota? \n"
                    + "2) ¿Cuál es el nombre de su primer mejor amigo \n"
                    + "3) ¿Cuál es su comida favorita?"));
                
                switch(op){
                    case 1:
                        prese="¿Cuál fue su primera mascota?";
                        break;
                    case 2:
                        prese="¿Cuál es el nombre de su primer mejor amigo";
                        break;
                    case 3:
                        prese="¿Cuál es su comida favorita?";
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción invalida.");
                }
            }while(op<1 || op>3);
            do{
                resse=JOptionPane.showInputDialog(null, "Ingrese su respuesta");
            }while(resse=="");
            cuatro=true;
        }while(uno==false || dos==false || tres==false || cuatro==false);
        Usuario c=new Cliente(nombre,contra,cedula,prese,resse);
        client = (Cliente) c;
        DiarioFacil.agregarUsuario(c);
        
    }
    
    public void login(){
        int cedula=0;
        String contra="";
        int op=0;
        boolean usuarioExistente=false;
        boolean formatoIncorrecto=false;
        String msgCed="La cédula no tiene el formato correcto";
        String msgContra="La contraseña es muy corta";
        do{
            do{
                formatoIncorrecto=false;
                try{
                    cedula=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su cédula"));
                }catch(NumberFormatException nfe){
                    formatoIncorrecto=true;
                    JOptionPane.showMessageDialog(null, msgCed, "Login Cliente", JOptionPane.ERROR_MESSAGE);
                }
            }while(formatoIncorrecto==true);
            
            contra=JOptionPane.showInputDialog(null,"Ingrese una contraseña");
            
            for(Usuario u:DiarioFacil.getLstUsuarios()){
                if(u.getCedula()==cedula && u.getContraPersona().equals(contra) && u instanceof Cliente){
                    client=((Cliente)u);
                    JOptionPane.showMessageDialog(null, "Bienvenido a DiarioFacil, "+client.getNombrePersona());
               
                    do{
                        formatoIncorrecto=false;
                        try{
                           
                                do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Ver artículos \n"
                                        + "2) Agregar artículos al carrito \n"
                                        + "3) Carrito \n"
                                            +"4) Historial \n"
                                            +"5) Cambiar contraseña \n"
                                        + "6) Cerrar sesión \n"));
                                    switch(op){
                                        case 1:verArticulos();
                                            break;
                                        case 2:agregarACarrito();
                                            break;
                                        case 3:carrito();
                                            break;
                                        case 4:
                                           historial();
                                            break;
                                        case 5:
                                            cambiarContraseña();
                                            break;
                                        case 6:
                                           
                                            break;
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=6 || op <1 || op>6);
                            
                        }catch(NumberFormatException nfe){
                            formatoIncorrecto=true;
                            JOptionPane.showMessageDialog(null,"La opción ingresada no"
                                    + " tiene el formato correcto, recuerde usar solo"
                                    + " números enteros ");
                        }
                    }while(formatoIncorrecto==true);
                    usuarioExistente=true;
                }
            }
            if(usuarioExistente==false){
                JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");
            }
        }while(usuarioExistente==false);
    }
    
    public void cambiarContraseña(){
          int cedula=0;
        String contraold="";
        String contranew="";
        String respuesta=""; 
        boolean formatoIncorrecto=false;
        boolean usuarioExistente=false;
        boolean respuestaCorrecta=false;
        String msgCed="";
        do{
            do{
                formatoIncorrecto=false;
                try{
                    cedula=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su cédula"));
                }catch(NumberFormatException nfe){
                    formatoIncorrecto=true;
                    JOptionPane.showMessageDialog(null, msgCed, "Login Cliente", JOptionPane.ERROR_MESSAGE);
                }
            }while(formatoIncorrecto==true);
            for(Usuario u:DiarioFacil.getLstUsuarios()){
                if(u.getCedula()==cedula && u instanceof Cliente){
                    contraold=JOptionPane.showInputDialog(null,"Ingrese su vieja contraseña");
                    respuesta=JOptionPane.showInputDialog(null, ((Cliente)u).getPreguntaSeguridad());
                    if(contraold.equals(u.getContraPersona()) && respuesta.equals(((Cliente)u).getRespuestaSeguridad())){
                        contranew=(JOptionPane.showInputDialog(null,"Ingrese su nueva contraseña \n"));
                        u.setContraPersona(contranew);
                        JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente \n");
                    }else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta, intentelo de nuevo.");
                    }
                    usuarioExistente=true;
                }
            }
        }while(usuarioExistente==false);
    }
    
    public static void validarContrasenna(String contrasenna)  throws DiarioContraseñaException{
        if(contrasenna.length()<4){
            throw new DiarioContraseñaException("La contraseña es muy corta, ingrese una más segura \n");
        }
    }

    public void verArticulos(){
    boolean formatoIncorrecto=false; 
    int op=0;
do{
                        formatoIncorrecto=false;
                        try{
                           if(client.isFrecuente()){
                            do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Ver productos \n"
                                        + "2) Ver combos  \n"
                                        + "3) Ver promociones  \n"
                                            +"4) Volver \n"));
                                    switch(op){
                                        case 1:verProductos();

                                            break;
                                        case 2:verCombos();
                                            break;
                                        case 3://ver promociones
                                            break;
                                        case 4:
                                          
                                            break;
                                        
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=4 || op <1 || op>4);   
                           }else{
                              do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Ver productos \n"
                                        + "2) Ver combos  \n"
                                        + "3) Volver  \n"));
                                    switch(op){
                                        case 1:verProductos();

                                            break;
                                        case 2:verCombos();
                                            break;
                                        case 3:
                                            break;
                                      
                                        
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=3 || op <1 || op>3); 
                           }
                                
                            
                        }catch(NumberFormatException nfe){
                            formatoIncorrecto=true;
                            JOptionPane.showMessageDialog(null,"La opción ingresada no"
                                    + " tiene el formato correcto, recuerde usar solo"
                                    + " números enteros ");
                        }
                    }while(formatoIncorrecto==true);    
}

    public void agregarACarrito(){
    boolean formatoIncorrecto=false;
    int op=0;
do{
                        formatoIncorrecto=false;
                        try{
                           if(client.isFrecuente()){
                               do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Agregar un producto al carrito \n"
                                        + "2) Agregar un combo al carrito  \n"
                                        + "3) Agregar una promoción al carrito  \n"
                                            +"4) Volver \n"));
                                    switch(op){
                                        case 1:annadirProductoAlCarrito();
                                            break;
                                        case 2:annadirComboAlCarrito();
                                            break;
                                        case 3://agregar promoción al carrito
                                            break;
                                        case 4:
                                          
                                            break;
                                        
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=4 || op <1 || op>4); 
                           }else{
                                do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Agregar un producto al carrito \n"
                                        + "2) Agregar un combo al carrtio  \n"
                                            +"3) Volver \n"));
                                    switch(op){
                                        case 1:annadirProductoAlCarrito();

                                            break;
                                        case 2:annadirComboAlCarrito();
                                            break;
                                        case 3:
                                            break;
                                       
                                        
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=3 || op <1 || op>3);
                           }
                               
                            
                        }catch(NumberFormatException nfe){
                            formatoIncorrecto=true;
                            JOptionPane.showMessageDialog(null,"La opción ingresada no"
                                    + " tiene el formato correcto, recuerde usar solo"
                                    + " números enteros ");
                        }
                    }while(formatoIncorrecto==true);    
}  

    public void verArticulosEnCarrito(){
        StringBuffer lista = new StringBuffer();
        int total=0;
        lista.append("");
           
            for(Factura f:DiarioFacil.getListaFacturas()){
                
                if(codFactura==0){
                    for(Orden o:f.getListaOrdenes()){
                        if(o.getProducto()!=null){
                            lista.append("Lista de productos \n");
                            for(Orden or:f.getListaOrdenes()){
                                lista.append("Producto: ");
                                lista.append(or.getProducto().nombreProd);
                                lista.append("\t Cantidad:");
                                lista.append(or.getCantidad());
                                lista.append("\t Subtotal:");
                                lista.append(or.getSubtotal());
                                total+=or.getSubtotal();
                                lista.append("\n");
                            }
                        }
                        if(o.getCombo()!=null){
                            lista.append("Lista de combos \n");
                            for(Orden or:f.getListaOrdenes()){
                                lista.append("Nombre de Combo: ");
                                lista.append(or.getCombo().getNombreCombo());
                                lista.append("\nProductos en el combo:\n");
                                for(Item it:or.getCombo().getListaItems()){
                                    lista.append(it.getProd().nombreProd+"\t");
                                }
                                lista.append("\t Subtotal:");
                                lista.append(or.getCombo().getPrecio());
                                total+=or.getCombo().getPrecio();
                                lista.append("\n");
                            }
                        }
                        lista.append("Total: "+total);
                    }

                }
              
               
            }
            
            if(lista.length()==0){
                lista.append("Su carrito se encuentra vacio");
            }
            JOptionPane.showMessageDialog(null,lista);
    }
    
    public void carrito(){
    boolean formatoIncorrecto=false;
    int op=0;
    
    do{
                        formatoIncorrecto=false;
                        try{
                           
                                do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Editar artículos\n"
                                        + "2) Ver artículos en carrito\n"
                                        + "3) Limpiar carrito \n"
                                            +"4) Completar compra  \n"
                                            +"5) Volver \n"));
                                    switch(op){
                                        case 1:editarCarrito();
                                            break;
                                        case 2:verArticulosEnCarrito();
                                            break;
                                        case 3://limpiar carrito
                                            break;
                                        case 4:
                                           //Completar compra
                                            break;
                                        case 5:
                                       
                                            break;
                                       
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=5 || op <1 || op>5);
                            
                        }catch(NumberFormatException nfe){
                            formatoIncorrecto=true;
                            JOptionPane.showMessageDialog(null,"La opción ingresada no"
                                    + " tiene el formato correcto, recuerde usar solo"
                                    + " números enteros ");
                        }
                    }while(formatoIncorrecto==true);
}

    public void editarCarrito(){
    boolean formatoIncorrecto=false;
    int op=0;
    
    do{
                        formatoIncorrecto=false;
                        try{
                           
                                do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Cambiar cantidades de un artículo\n"
                                        + "2) Remover un artículo del carrito\n"
                                            +"3) Volver \n"));
                                    switch(op){
                                        case 1://Cambiar cantidades de un artículo
                                            break;
                                        case 2://Remover un artículo del carrito
                                            break;
                                        case 3://Volver
                                            break;
                                       
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=3 || op <1 || op>3);
                            
                        }catch(NumberFormatException nfe){
                            formatoIncorrecto=true;
                            JOptionPane.showMessageDialog(null,"La opción ingresada no"
                                    + " tiene el formato correcto, recuerde usar solo"
                                    + " números enteros ");
                        }
                    }while(formatoIncorrecto==true);
}

    public void historial(){
    boolean formatoIncorrecto=false;
    int op=0;
    
    do{
                        formatoIncorrecto=false;
                        try{
                           
                                do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Ultima órden\n"
                                        + "2) Lista de facturas\n"
                                        + "3) Productos más frecuentemente adquiridos \n"
                                            +"4) Volver \n"));
                                    switch(op){
                                        case 1:ultimaOrden();
                                            break;
                                        case 2:listaFacturas();
                                            break;
                                        case 3://productos más frecuentemente adquiridos
                                            break;
                                        case 4:
                                      
                                            break;
                                       
                                       
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=4 || op <1 || op>4);
                            
                        }catch(NumberFormatException nfe){
                            formatoIncorrecto=true;
                            JOptionPane.showMessageDialog(null,"La opción ingresada no"
                                    + " tiene el formato correcto, recuerde usar solo"
                                    + " números enteros ");
                        }
                    }while(formatoIncorrecto==true);
}

    public void ultimaOrden(){
    boolean formatoIncorrecto=false;
    int op=0;
    
    do{
                        formatoIncorrecto=false;
                        try{
                           
                                do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Ver última órden\n"
                                        + "2) Agregar los productos de la última órden al carrito\n"
                                            +"3) Volver \n"));
                                    switch(op){
                                        case 1://ver última órden
                                            break;
                                        case 2://Agregar los productos de la última órden al carrito
                                            break;
                                        case 3:
                                            break;
                                       
                                       
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=3 || op <1 || op>3);
                            
                        }catch(NumberFormatException nfe){
                            formatoIncorrecto=true;
                            JOptionPane.showMessageDialog(null,"La opción ingresada no"
                                    + " tiene el formato correcto, recuerde usar solo"
                                    + " números enteros ");
                        }
                    }while(formatoIncorrecto==true);
}

    public void listaFacturas(){
      boolean formatoIncorrecto=false;
    int op=0;
    
    do{
                        formatoIncorrecto=false;
                        try{
                           
                                do{
                                    op=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese una opcion \n"
                                        + "1) Ver facturas\n"
                                        + "2) Ver detalle de factura\n"
                                            +"3) Volver \n"));
                                    switch(op){
                                        case 1://ver facturas
                                            break;
                                        case 2://ver detalle de factura
                                            break;
                                        case 3:
                                            break;
                                       
                                       
                                       
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción no válida");
                                            break;
                                    }
                                }while(op!=3 || op <1 || op>3);
                            
                        }catch(NumberFormatException nfe){
                            formatoIncorrecto=true;
                            JOptionPane.showMessageDialog(null,"La opción ingresada no"
                                    + " tiene el formato correcto, recuerde usar solo"
                                    + " números enteros ");
                        }
                    }while(formatoIncorrecto==true);
}



    //Aqui solo nos interesa que el usuario vea su propio historial
    //Por eso llamamos a la funcion del mismo nombre que requiere un parametro cedula,
    // pasandole el del usuario que esta loggeado
    @Override
    public void verHistorial(){
        StringBuffer historial = new StringBuffer();

        ListIterator<Factura> iteradorFacturas = client.getHistorialDeFacturas().listIterator(client.getHistorialDeFacturas().size());

        while(iteradorFacturas.hasPrevious()){
            historial.append(formatearFactura(iteradorFacturas.previous()));
        }

        JOptionPane.showMessageDialog(null,historial);

    }

    @Override
    public void verHistorialUsuario(int cedulaUsuario) {
        verHistorial();
    }

    @Override
    public void verHistorialFecha(Calendar fecha) {
        StringBuffer historial = new StringBuffer();
        ListIterator<Factura> iterador = client.getHistorialDeFacturas().listIterator(client.getHistorialDeFacturas().size());

        while(iterador.hasPrevious()){
            if(iterador.previous().getCalendar().equals(fecha)){
                historial.append(iterador.previous());
            }
        }

        JOptionPane.showConfirmDialog(null,historial);
    }

    @Override
    public void verHistorialFecha(Calendar fechaInicio, Calendar fechaFin) {
        StringBuffer historial = new StringBuffer();
        ListIterator<Factura> iterador = client.getHistorialDeFacturas().listIterator(client.getHistorialDeFacturas().size());
        Calendar fechaIterador = new GregorianCalendar();

        while(iterador.hasPrevious()){
            fechaIterador = iterador.previous().getCalendar();
            if(fechaIterador.after(fechaInicio) && fechaIterador.before(fechaFin)){
                historial.append(iterador.previous());
            }
        }

        JOptionPane.showConfirmDialog(null,historial);
    }

}


