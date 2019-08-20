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
    int cedula=0;
    Cliente client;

    private StringBuffer getProductos(){
        StringBuffer lista = new StringBuffer();
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
        return lista;
    }


    public void verProductos(){
        StringBuffer productos = getProductos();
        mostrar(productos,"Productos");
   }



private StringBuffer getCombos(){
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
    return lista;
}


public void verCombos(){
        StringBuffer combos = getCombos();
        mostrar(combos);
}

private void anhadirProductoAlCarrito(){
        int codigo = validarCodigoProducto();
        Producto producto = getProductoConCodigo(codigo);
        int cantidad = validarCantidadProducto(producto);
        descontarDelStock(codigo,cantidad);
        double subtotal = producto.getPrecio() * cantidad;
        Orden orden = new Orden(producto,cantidad,subtotal);
        client.agregarOrdenACarrito(orden);
        mostrar("La Orden ha sido agregada con exito");
}

private void descontarDelStock(int codigo, int cantidad){
        for(Categoria categoria: DiarioFacil.getListaCategorias()){
            for(Producto producto: categoria.getListaProductos()){
                if(producto.codProducto==codigo){
                    int stock = producto.getStockActual() - cantidad;
                    producto.setStockActual(stock);
                }
            }
        }
}

private Producto getProductoConCodigo(int codigo){
        for(Categoria categoria : DiarioFacil.getListaCategorias()){
            for(Producto producto: categoria.getListaProductos()){
                if(producto.getCodProducto()==codigo){
                    return producto;
                }
            }
        }
        return null;
}


private int validarCantidadProducto(Producto producto){
        int cantidad = -1;
        while(cantidad<1) {
            cantidad = inputInt("Ingrese la cantidad de "+ producto.getNombreProd()+" que desea agregar al carrito", "Cantidad", "la cantidad debe ser un numero");
        }

    if(cantidad>producto.stockActual){
        mostrarError("Solo se pudieron agregar "+producto.stockActual,"Stock Insuficiente");
        cantidad = producto.stockActual;
    }
        return cantidad;
}


    private int validarCodigoProducto(){
        int codigo = inputInt("Ingrese el Codigo del Producto","Añadir Producto","El codigo debe ser un numero");
        for(Categoria categoria: DiarioFacil.getListaCategorias()){
            for(Producto producto: categoria.getListaProductos()){
                if(codigo==producto.codProducto){
                    if(producto.stockActual==0){
                        mostrarError("Lo sentimos este producto se encuentra agotado","Producto no disponible");
                    }else{
                        return codigo;
                    }
                }
            }
        }
        mostrarError("Lo sentimos no se pudo encontrar su producto","Producto no encontrado");
        return validarCodigoProducto();
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

                                for(Item it:comb.getListaItems()){
                                    if(it.getCantidad()==0){
                                        JOptionPane.showMessageDialog(null,"Hay articulos en el combo que no están disponibles");
                                    }else{
                                        confirm=Integer.parseInt(JOptionPane.showInputDialog
                                        (null,"Combo: "+comb.getNombreCombo()+" Precio: "+comb.getPrecio()+"\n"
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
                                    }
                                }



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
        if(o.getProducto().equals(null)){
            ordenFormateada.append("Nombre: "+o.getProducto().getNombreProd());
            ordenFormateada.append("\t Cantidad:");
            ordenFormateada.append(o.getCantidad());
            ordenFormateada.append("\t Precio:");
            double precio = ajustarPrecio(o.getProducto(), this.client.isFrecuente());
            ordenFormateada.append(precio); // Aqui mejor usar el getPrecio();
            ordenFormateada.append("\t Subtotal:");
            ordenFormateada.append(o.getSubtotal());
            ordenFormateada.append("\n");
        }
        if(o.getCombo().equals(null)){
            ordenFormateada.append("Combo: " + o.getCombo().getNombreCombo());
            ordenFormateada.append("\t Precio:");
            ordenFormateada.append(o.getCombo().getPrecio());
        }

        // DEPENDIENDO DE SI EL CLIENTE ES FRECUENTE O NO,
        // MOSTRAMOS LOS PRECIOS CON DESCUENTO
        double precio = ajustarPrecio(o.getProducto(), this.client.isFrecuente());

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
    

    /*

##     ## ######## ##    ## ##     ##  ######
###   ### ##       ###   ## ##     ## ##    ##
#### #### ##       ####  ## ##     ## ##
## ### ## ######   ## ## ## ##     ##  ######
##     ## ##       ##  #### ##     ##       ##
##     ## ##       ##   ### ##     ## ##    ##
##     ## ######## ##    ##  #######   ######

     */

    public void menu(){
        String[] opciones ={
                "Crear Cuenta",
                "Ingresar a una cuenta ya creada",
                "Volver"
        };

        int op = validarSwitchCase(opciones);

        switchMenu(op);
    }

    public void switchMenu(int op){
        switch(op){
            case 0:
                signUp();
                break;
            case 1:
                login2();
                break;
            case 2:
                Tester.menuPrincipal();
                break;
        }
    }

    public void signUp(){
        DiarioFacil df = new DiarioFacil();
        int op=0;
        String nombre="";
        String contra="";
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
                cedulaIncorrecta=false;
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
                }catch(DiarioContrasennaException dce){
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

    private void login2(){
        cedula = validarCedula();
        String contra = getContra();
        intentarLogin(contra);
        mainLoop();
    }

    private void mainLoop(){
        String[] opciones = {
                "Ver artículos",
                "Agregar artículos al carrito",
                "Carrito",
                "Historial",
                "Cambiar contraseña",
                "Cerrar sesión"
        };
        int opcion = validarSwitchCase(opciones);
        switchMainLoop(opcion);
    }

    private void switchMainLoop(int opciones){
       switch(opciones){
            case 0:
                menuArticulos();
                break;
            case 1:
                agregarACarrito();
                break;
            case 2:
                carrito();
                break;
            case 3:
                historial();
                break;
            case 4:
                cambiarContrasenna();
                break;
            case 5:
                client = null;
                Tester.menuPrincipal();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
                break;
        }
    }


    private void menuArticulos(){
        String[] articulosNormales = {"Ver Productos","Ver Combos","Volver"};
        String[] articulosFrecuentes = {"Ver Productos","Ver Combos","Ver Promociones","Volver"};
        int opcion;
        if(client.isFrecuente()){
            opcion = validarSwitchCase(articulosFrecuentes);
            switchClientesFrecuentes(opcion);
        }else{
            opcion = validarSwitchCase(articulosNormales);
            swtichClientesNormales(opcion);
        }
    }

    private void swtichClientesNormales(int opcion){
        switch(opcion){
            case 0:
                verProductos();
                break;
            case 1:
                verCombos();
                break;
            case 2:
                break;
        }
        mainLoop();
    }

    private void switchClientesFrecuentes(int opcion){
        switch (opcion){
            case 0:
                verProductos();
                break;
            case 1:
                verCombos();
                break;
            case 2:
                verPromociones();
                break;
            case 3:
                break;
        }
        mainLoop();
    }




    private void intentarLogin(String contra){
        if(loginValido(contra)){
            StringBuffer mensaje = new StringBuffer();
            mensaje.append("Bienvendio a DiarioFacil "+client.getNombrePersona());
            mostrar(mensaje,"Login Exitoso");
        }else{
            mostrar("Login Fallido");
            Tester.menuPrincipal();
        }
    }

    private boolean loginValido(String contra){
        for(Usuario usuario : DiarioFacil.getLstUsuarios()){
            if(usuario.getCedula()==cedula && usuario.getContraPersona().equals(contra)){
                client = (Cliente) usuario;
                return true;
            }
        }
        return false;
    }

    private String getContra(){
        String contra = "";
        while(contra.equals("")){
            contra = inputString("Ingrese su contraseña","Contraseña");
        }
        return contra;
    }

    private String inputString(Object mensaje, String title){
        String input = JOptionPane.showInputDialog(null,mensaje,title,JOptionPane.INFORMATION_MESSAGE);
        return input;
    }

    private int validarCedula(){
       int cedula = 0;
       while(cedula<1){
           cedula = inputInt("Ingrese su Cedula","Ingrese su Cedula","Utilize unicamente valores numericos (0-9)");
       }
       return cedula;
    }
    
    public void login(){
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
                                            cambiarContrasenna();
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
                menu();
            }
        }while(usuarioExistente==false);
    }
    
    public void cambiarContrasenna(){
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
    
    public static void validarContrasenna(String contrasenna)  throws DiarioContrasennaException{
        if(contrasenna.length()<4){
            throw new DiarioContrasennaException("La contraseña es muy corta, ingrese una más segura \n");
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
                                        case 1:
                                            verProductos();
                                            break;
                                        case 2:
                                            verCombos();
                                            break;
                                        case 3:
                                            verPromociones();
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

    public void verPromociones(){
        boolean enPromocion = false;
        StringBuffer listauno = new StringBuffer();
        listauno.append("Productos con promoción \n");
        
        StringBuffer listados = new StringBuffer();
        listados.append("Lista de productos \n");

        for(Promocion promocion : DiarioFacil.listaPromociones){
            for(Item item : promocion.getListaItems()){
                
                for(Categoria c:DiarioFacil.getListaCategorias()){
                    for(Producto p:c.getListaProductos()){
                        if(promocion.isActivo()==true && item.getProd().codProducto ==p.getCodProducto()){
                            listados.append("Código: ");
                            listados.append(p.getCodProducto());
                            listados.append("\t nombre:");
                            listados.append(p.getNombreProd());
                            listados.append("\t Precio:");
                            listados.append(p.getPrecio());
                            listados.append("\t Stock actual:");
                            if(p.getStockActual()==0){
                               listados.append("Agotado");
                            }else{
                               listados.append(p.getStockActual());
                            }
                            listados.append("\n");   
                        }
                    }
                }
            }
        }
        
        
           
           
        /*for(Categoria c:DiarioFacil.getListaCategorias()){
            for(Producto p:c.getListaProductos()){
                listados.append("Código: ");
                listados.append(p.getCodProducto());
                listados.append("\t nombre:");
                listados.append(p.getNombreProd());
                listados.append("\t Precio:");
                listados.append(p.getPrecio());
                listados.append("\t Stock actual:");
                if(p.getStockActual()==0){
                    listados.append("Agotado");
                }else{
                    listados.append(p.getStockActual());
                }
                listados.append("\n");
                 
                 
               }
              
               
           }*/
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


    private StringBuffer getArticulosEnCarrito() {
        StringBuffer lista = new StringBuffer();
        int i = 0;
        for (Orden o : client.getCarrito().getListaOrdenes()) {
            lista.append(i+") ");
            lista.append("Producto: ");
            lista.append(o.getProducto().nombreProd);
            lista.append("\t Cantidad:");
            lista.append(o.getCantidad());
            lista.append("\t Subtotal:");
            lista.append(o.getSubtotal());
            lista.append("\n");
            i++;
        }
        return lista;
    }

        public void verArticulosEnCarrito(){
            StringBuffer lista = new StringBuffer();
            int total=0;
            lista.append("");
            for(Orden o: client.getCarrito().getListaOrdenes()){
                    if(o.getProducto()!=null){
                            lista.append("Producto: ");
                            lista.append(o.getProducto().nombreProd);
                            lista.append("\t Cantidad:");
                            lista.append(o.getCantidad());
                            lista.append("\t Subtotal:");
                            lista.append(o.getSubtotal());
                            total+=o.getSubtotal();
                            lista.append("\n");
                        }
                    if(o.getCombo()!=null){
                        lista.append("Nombre de Combo: ");
                        lista.append(o.getCombo().getNombreCombo());
                        lista.append("\nProductos en el combo:\n");
                        for(Item it:o.getCombo().getListaItems()){
                            lista.append(it.getProd().nombreProd+"\t");
                        }
                        lista.append("\t Subtotal:");
                        lista.append(o.getCombo().getPrecio());
                        total+=o.getCombo().getPrecio();
                        lista.append("\n");
                    }
            }
            if(lista.length()==0){
                mostrar("El Carrito Esta Vacio");
            }else {
                lista.append("Total: " + total);
                mostrar(lista,"Carrito");
            }
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
                                        case 1:
                                            editarCarrito();
                                            break;
                                        case 2:
                                            verArticulosEnCarrito();
                                            break;
                                        case 3:
                                            client.limpiarCarrito();
                                            break;
                                        case 4:
                                            client.completarCarrito();
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



    private void camibarCantidadesArticulo(){
        StringBuffer articulos = new StringBuffer();
        articulos.append("Ingrese el numero del articulo que desea cambiar\n\n");
        articulos.append(getArticulosEnCarrito());
        int size = client.getCarrito().getListaOrdenes().size()-1;
        int seleccion = -1;
        while(seleccion<0 || seleccion > size) {
               seleccion = inputInt(articulos,"Cambiar Cantidad");
        }
        client.cambiarCantidad(seleccion);

    }

    private void removerArticuloDelCarrito(){

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
                                        case 1:
                                            camibarCantidadesArticulo();
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

    /*
    ,o888888o.    8 8888      88  8 8888
   8888     `88.  8 8888      88  8 8888
,8 8888       `8. 8 8888      88  8 8888
88 8888           8 8888      88  8 8888
88 8888           8 8888      88  8 8888
88 8888           8 8888      88  8 8888
88 8888   8888888 8 8888      88  8 8888
`8 8888       .8' ` 8888     ,8P  8 8888
   8888     ,88'    8888   ,d8P   8 8888
    `8888888P'       `Y88888P'    8 8888

    ESTOS METODOS SE ENCARGAN DE VALIDAR INPUTS VALIDOS
    PARA LOS SWITCH CASES DE LOS MENUS, Y UTILIZAN
    JOPTION PANES PARA MOSTRARLE INFROMACION AL USUARIO

    */
    private int validarSwitchCase(String[] opciones){
        int i = -1;
        while(i<0 || i >= opciones.length){
            i = inputInt(formatearOpciones(opciones));
        }
        return i;
    }


    /**
     * ACEPTA INPUT DEL USUARIO Y LO CONVIERTE A UN INT
     * @param mensaje PARA MOSTRAR USUARIO
     * @return
     */
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

    /** ACEPTA UN INPUT DE UN USUARIOO TOMA UN BufferString de Parametro
     * VALIDA EL INPUT HASTA QUE SEA VALIDO
     * @param mensaje SE LE MUESTRA AL USUARIO
     * @return UN INT
     */
    private int inputInt(StringBuffer mensaje){
        int input = 0;
        try{
            input = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Por Favor Ingrese un numero Entero","Formato Incorrecto",JOptionPane.ERROR_MESSAGE);
            return inputInt(mensaje);
        }

        return input;
    }

    private int inputInt(StringBuffer mensaje, String titulo){
        int input = 0;
        try{
            input = Integer.parseInt(JOptionPane.showInputDialog(null,mensaje,titulo,JOptionPane.PLAIN_MESSAGE));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Por Favor Ingrese un numero Entero","Formato Incorrecto",JOptionPane.ERROR_MESSAGE);
            return inputInt(mensaje,titulo);
        }

        return input;
    }

    private int inputInt(Object mensaje, String titulo, String mensajeError){
        int input = 0;
        try{
            input = Integer.parseInt(JOptionPane.showInputDialog(null,mensaje,titulo,JOptionPane.PLAIN_MESSAGE));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,mensajeError,"Formato Incorrecto",JOptionPane.ERROR_MESSAGE);
            return inputInt(mensaje, titulo,mensajeError);
        }

        return input;

    }


    private StringBuffer formatearOpciones(String[] opciones){
        return formatearOpciones("",opciones);
    }

    private StringBuffer formatearOpciones(String titulo, String[] opciones){
        StringBuffer formateado = new StringBuffer();
        formateado.append("\t\t"+titulo+"\t\t\n");
        int i = 0;
        for(String opcion: opciones){
            formateado.append(i + ") " + opcion + "\n");
            i++;
        }
        return formateado;

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
                                        case 1:
                                            verUltimaOrden2();
                                            break;
                                        case 2:repetirUlimaCompra();
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

    public void agregarUltimaOrden(){
        boolean formatoIncorrecto=false, status=false;
        int codFactura=1, op=0, cant=0, confirm=0;
        boolean vacio=false;
        
        StringBuffer historial = new StringBuffer();
        
        ListIterator<Factura> iterador = client.getHistorialDeFacturas().listIterator(client.getHistorialDeFacturas().size());

        while(iterador.hasNext()){
            if(cedula == iterador.next().getCliente().cedula){
                iterador.next().getListaOrdenes();
            }
        }
        
        if(historial.toString().equals("")){
            vacio=true;
        }
        
        if(vacio==false){
            for(Factura fact : DiarioFacil.listaFacturas){
                for(Orden ord : fact.listaOrdenes){
                    for(Categoria cat: DiarioFacil.listaCategorias){
                        for(Producto prod: cat.getListaProductos()){
                            if(fact.getCliente().cedula==cedula){
                                annadirUltProductosAlCarrito(ord.getProducto().codProducto, ord.getCantidad());
                            }else{
                                JOptionPane.showMessageDialog(null, "No ha realizado compras");
                            }
                        }
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay compras en el historial");
        }
    }


    public void repetirUlimaCompra(){
        client.repetirOrden();
        mostrar("La Compra fue repetida con Exito");
    }


    public void annadirUltProductosAlCarrito(int cod, int cant) {
        boolean formatoIncorrecto = false, status = false;
        Producto productoEnviado;
        int codigoFactura = 1, confirm = 0;
        double montoTotal = 0;
        do {
            formatoIncorrecto = false;

            try {
                for (Categoria c : DiarioFacil.getListaCategorias()) {
                    for (Producto p : c.getListaProductos()) {
                        if (cod == p.codProducto) {
                            if (p.stockActual == 0) {
                                JOptionPane.showMessageDialog(null, "Ese producto se encuentra agotado");
                                formatoIncorrecto = true;
                            } else {
                                do {
                                    formatoIncorrecto = false;
                                    try {
                                        if (cant > p.stockActual) {
                                            JOptionPane.showMessageDialog(null, "Se lograron añadir solo: " + p.stockActual + " unidades");
                                            cant = p.stockActual;
                                        }
                                        do {
                                            formatoIncorrecto = false;
                                            try {
                                                confirm = Integer.parseInt(JOptionPane.showInputDialog(null, "Producto: " + p.nombreProd + " Cantidad: " + cant + " Precio: " + cant * p.precio + "\n"
                                                        + "¿Está seguro de querer añadir esta cantidad al carrito?\n"
                                                        + "(No=0 Si=1)"));

                                                if (confirm == 1) {
                                                    productoEnviado = p;
                                                    montoTotal = cant * p.precio;
                                                    if (DiarioFacil.listaFacturas.isEmpty()) {
                                                        Factura f = new Factura(client, codigoFactura);
                                                        Orden o = new Orden(productoEnviado, cant, montoTotal);
                                                        f.agregarOrdenes(o);
                                                        DiarioFacil.agregarFactura(f);
                                                    } else {
                                                        for (Factura f : DiarioFacil.listaFacturas) {
                                                            for (Orden ord : f.listaOrdenes) {
                                                                if (ord.getProducto() == p) {
                                                                    status = true;
                                                                }
                                                            }
                                                            if (f.getCodFactura() == codigoFactura && status == true) {
                                                                for (Orden ord : f.listaOrdenes) {
                                                                    if (ord.getProducto() == p) {
                                                                        ord.setCantidad(ord.getCantidad() + cant);
                                                                        ord.setSubtotal(p.precio * ord.getCantidad());
                                                                    }
                                                                }
                                                            } else {
                                                                Orden o = new Orden(productoEnviado, cant, montoTotal);
                                                                f.agregarOrdenes(o);
                                                            }
                                                        }
                                                    }

                                                }
                                                JOptionPane.showMessageDialog(null, "Su orden ha sido añadida con éxito");

                                            } catch (NumberFormatException nfe) {
                                                formatoIncorrecto = true;
                                                JOptionPane.showMessageDialog(null, "La opción ingresada no tiene el formato correcto, recuerde usar solo números");
                                            }
                                        } while (confirm < 0 || confirm > 1 || formatoIncorrecto == true);

                                    } catch (NumberFormatException nfe) {
                                        formatoIncorrecto = true;
                                        JOptionPane.showMessageDialog(null, "La opción ingresada no tiene el formato correcto, recuerde usar solo números");
                                    }

                                } while (cant <= 0 || formatoIncorrecto == true);
                            }
                        }
                    }
                }

            } catch (NumberFormatException nfe) {
                formatoIncorrecto = true;
                JOptionPane.showMessageDialog(null, "La opción ingresada no tiene el formato correcto"
                        + ", recuerde usar solo números");
            }
        } while (cod < 0 || formatoIncorrecto == true);
    }

    //Aqui solo nos interesa que el usuario vea su propio historial
    //Por eso llamamos a la funcion del mismo nombre que requiere un parametro cedula,
    // pasandole el del usuario que esta loggeado

    public void verUltimaOrden2(){
        StringBuffer ultimaOrden = new StringBuffer();
        int size = client.getCantidadCompras();
        if(size==0){
            mostrar("No hay compras en el historial");
        }
        Factura ultima = client.getHistorialDeFacturas().get(size);
        ultimaOrden.append(ultima.toString());
        mostrar(ultimaOrden);
    }

    private void mostrar(StringBuffer stringBuffer){
        JOptionPane.showMessageDialog(null,stringBuffer);
    }


    private void mostrar(StringBuffer stringBuffer, String titulo){
        JOptionPane.showMessageDialog(null,stringBuffer,titulo,JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrar(String string){
        JOptionPane.showMessageDialog(null,string);
    }

    private void mostrarError(String string, String titulo){
        JOptionPane.showMessageDialog(null,string,titulo,JOptionPane.ERROR_MESSAGE);
    }


     public void verUltimaOrden(){
        boolean vacio=false;
        
        StringBuffer historial = new StringBuffer();
        
        ListIterator<Factura> iterador = client.getHistorialDeFacturas().listIterator(client.getHistorialDeFacturas().size());

        while(iterador.hasNext()){
            if(cedula == iterador.next().getCliente().cedula){
                iterador.next().getListaOrdenes();
            }
        }
        
        if(historial.toString().equals("")){
            vacio=true;
        }
        
        if(vacio==false){
            JOptionPane.showMessageDialog(null, historial);
        }else{
            JOptionPane.showMessageDialog(null, "No hay compras en el historial");
        }
    }
    
    @Override
    public void verHistorial(){
        StringBuffer historial = new StringBuffer();
        historial.append("Historial De Facturas");

        ListIterator<Factura> iteradorFacturas = client.getHistorialDeFacturas().listIterator(client.getCantidadCompras());

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
        int cantidad = client.getCantidadCompras();
        ListIterator<Factura> iterador = client.getHistorialDeFacturas().listIterator(cantidad);
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


