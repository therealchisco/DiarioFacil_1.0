/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioFacil;


import javax.swing.JOptionPane;
import java.util.*;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author Roberto
 */
public class BackOffice implements IHistorial{
    Administrador admin;
    Combos combo;
    int consecutivo=1;
    int comboModificando=0;

    public void iniciarSesion(){
                           int user=0;
                           int opcion=0;
                           String contra="";
                           boolean usuarioEncontrado=false;
                           boolean formatoincorrecto=false;
                           String msgCed="la cédula no tiene el formato correcto,recuerde usar solo números";
                           do{
                           do{
                           formatoincorrecto=false;
                           try{
                           user=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cédula"));
                           }catch(NumberFormatException nfe){
                           formatoincorrecto=true;
                           JOptionPane.showMessageDialog(null,msgCed,"Login Administrador",JOptionPane.ERROR_MESSAGE);
                           }


                           }while(formatoincorrecto==true);

                           contra=JOptionPane.showInputDialog(null,"Ingrese la contraseña");


                           for(Usuario u:DiarioFacil.getLstUsuarios()){
                           if( u.getCedula()==user && u.getContraPersona().equals(contra)&& u instanceof Administrador){

                           admin=((Administrador)u);
                           JOptionPane.showMessageDialog(null,"Bienvenido, "+admin.getNombrePersona());

                           do{

                           formatoincorrecto=false;

                           try{
                           opcion=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la opción"+"\n"
                           +"1.Mantenimiento de proveedores \n"+
                           "2.Mantenimiento de categorías \n"+
                           "3.Mantenimiento de productos \n"+
                           "4.Mantenimiento de administradores \n"+
                           "5.Mantenimiento de clientes \n"+
                           "6.Realizar un pedido \n"+
                           "7.Combos \n"+
                           "8.Promociones \n"+
                           "9.Facturación \n"+
                           "10.Cerrar sesión"));


                           switch(opcion){
                           case 1:manejoProveedores();
                           break;

                           case 2:manejoCategorias();
                           break;

                           case 3:manejoProductos();
                           break;

                           case 4:mantenimientoAdmin();
                           break;

                           case 5: mantenimientoClientes();
                           break;

                           case 6: //realizar pedidos
                           break;

                           case 7:manejarCombos();
                           break;

                           case 8://promociones
                           break;

                           case 9://facturación(lista de facturas)
                           break;

                           case 10:Tester.menuPrincipal();
                           break;


                           }
                           }catch(NumberFormatException nfe){
                           formatoincorrecto=true;
                           }

                           }while(opcion!=10 ||formatoincorrecto==true); //este valor hay que cambiarlo, para poner el volver
                           //además, se debe agregar un rango



                           usuarioEncontrado=true;
                           }


                           }
                           if(usuarioEncontrado==false){
                           JOptionPane.showMessageDialog(null,"usuario o contraseña incorrecta");
                           }

                           }while(usuarioEncontrado==false);



                           }

    public void manejoProveedores(){
                               int opcion=0;
                               boolean inputInvalido=false;
                               do{
                               inputInvalido=false;

                               try{
                               opcion=Integer.parseInt(JOptionPane.showInputDialog(null,
                               "Ingrese la opción \n"+"1.Nuevo proveedor \n"+"2. Ver lista de proveedores \n"+
                               "3.Modificar proveedor \n"+"4.Borrar proveedor \n"+
                               "5.Volver"));
                               }catch(NumberFormatException nfe){
                               inputInvalido=true;
                               }


                               }while(opcion>5 || opcion<1 || inputInvalido==true);



                               switch(opcion){
                               case 1:
                               nuevoProveedor();
                               break;
                               case 2:
                               listaProveedores();

                               break;
                               case 3:
                               modificarProveedor();
                               break;

                               case 4:
                               borrarProveedor();
                               case 5:
                               break;
                               //Volver(se deja vacío para que termine el método)
                               }
                               }

    public void nuevoProveedor(){
                            int codigo=0;
                            boolean formatoCorrecto=true;
                            String nombre="";
                            String correo="";
                            boolean codigoYaExiste=false;
                            do{
                            formatoCorrecto=true;
                            codigoYaExiste=false;
                            try{
                            codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de proveedor"));
                            }catch(NumberFormatException nfe){
                            formatoCorrecto=false;
                            }
                            for(Proveedor p:DiarioFacil.getListaProveedores()){
                            if(codigo==p.getCodigo()){
                            codigoYaExiste=true;
                            JOptionPane.showMessageDialog(null,"El código ya existe");
                            }
                            }

                            }while(formatoCorrecto==false || codigoYaExiste==true);

                            nombre=JOptionPane.showInputDialog(null,"Ingrese el nombre del proveedor");

                            correo=JOptionPane.showInputDialog(null,"Ingrese el correo del proveedor");

                            Proveedor p=new Proveedor(codigo,nombre,correo);

                            DiarioFacil.agregarProveedor(p);


                            }

    public void listaProveedores(){
                              StringBuffer lista = new StringBuffer();
                              lista.append("Lista de proveedores \n");

                              for(Proveedor p:DiarioFacil.getListaProveedores()){
                              lista.append("Código: ");
                              lista.append(p.getCodigo());
                              lista.append("\t Nombre: ");
                              lista.append(p.getNombre());
                              lista.append("\t Correo: ");
                              lista.append(p.getCorreo());
                              lista.append("\n");
                              }
                              JOptionPane.showMessageDialog(null,lista);
                              }

    public void modificarProveedor(){
                                int codigo=0;
                                boolean codigoExiste=false;
                                boolean formatoCorrecto=true;
                                int opcion=0;
                                boolean formatoCorrectoOpcion=true;
                                do{
                                formatoCorrecto=true;
                                codigoExiste=false;
                                try{
                                codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de proveedor"));
                                }catch(NumberFormatException nfe){
                                formatoCorrecto=false;
                                JOptionPane.showMessageDialog(null,"el código no tiene el formato correcto");
                                }
                                for(Proveedor p:DiarioFacil.getListaProveedores()){
                                if(codigo==p.getCodigo()){

                                codigoExiste=true;
                                do{
                                try{
                                opcion=Integer.parseInt(JOptionPane.showInputDialog(null,"1.Modificar nombre"
                                + "\n 2.Modificar correo \n 3.Volver"));
                                }catch(NumberFormatException nfe){
                                formatoCorrectoOpcion=false;
                                JOptionPane.showMessageDialog(null,"la opción no tiene el formato correcto");
                                }


                                }while(formatoCorrectoOpcion=false || opcion>3 || opcion<1);

                                switch(opcion){
                                case 1:
                                String nombreAnterior="";
                                String nombre=JOptionPane.showInputDialog(null,"Ingrese el nuevo nombre para "+p.getNombre());
                                nombreAnterior=p.getNombre();
                                p.setNombre(nombre);
                                JOptionPane.showMessageDialog(null,"El nombre del proveedor ha pasado de:"+nombreAnterior+
                                " a "+p.getNombre());
                                break;

                                case 2:
                                String correo=JOptionPane.showInputDialog(null,"Ingrese el nuevo correo de "+p.getNombre());
                                p.setCorreo(correo);
                                JOptionPane.showMessageDialog(null,"El correo de "+p.getNombre()+
                                " ha sido actualizado");
                                break;
                                case 3:
                                break;
                                }


                                }
                                }

                                if(codigoExiste==false){
                                JOptionPane.showMessageDialog(null,"El código ingresado no existe");
                                }
                                }while(formatoCorrecto==false || codigoExiste==false);

                                }

    public void borrarProveedor(){
                             int codigo=0;
                             boolean formatoCorrecto=true;
                             String nombre="";
                             boolean proveedorExiste=false;
                             do{
                             do{
                             try{
                             codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de proveedor a borrar"));
                             }catch(NumberFormatException nfe){
                             formatoCorrecto=false;
                             JOptionPane.showMessageDialog(null,"el código no tiene el formato correcto");
                             }
                             }while(formatoCorrecto==false);

                             for(Iterator<Proveedor> p=DiarioFacil.getListaProveedores().iterator();
                             p.hasNext();){
                             Proveedor prov=p.next();
                             if(prov.getCodigo()==codigo){
                             proveedorExiste=true;
                             nombre=prov.getNombre();
                             p.remove();
                             JOptionPane.showMessageDialog(null,"El proveedor "+nombre+" ha sido "
                             + "eliminado");
                             }
                             }
                             if(proveedorExiste==false){
                             JOptionPane.showMessageDialog(null,"El proveedor ingresado no existe");
                             }

                             }while(proveedorExiste==false);
                             }

    public void manejoCategorias(){
                              int opcion=0;

                              boolean formatoCorrecto=true;

                              do{

                              try{

                              opcion=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la opcion \n"+
                              "1.Nueva categoría \n"+"2.Ver categorías \n"+
                              "3.Borrar categoría \n"+"4.Volver"));

                              }catch(NumberFormatException nfe){

                              formatoCorrecto=false;

                              JOptionPane.showMessageDialog(null,"la opción ingresada no tiene el formato correcto");

                              }
                              }while(formatoCorrecto==false ||opcion<1 ||opcion>4);

                              switch(opcion){
                              case 1: nuevaCategoria();

                              break;
                              case 2: verCategorias();
                              break;
                              case 3: borrarCategoria();
                              break;
                              case 4:
                              break;
                              }
                              }

    public void nuevaCategoria(){

                            int codigo=0;
                            boolean formatoCorrecto=true;
                            String nombre="";
                            String correo="";
                            boolean codigoYaExiste=false;

                            do{

                            formatoCorrecto=true;
                            codigoYaExiste=false;

                            try{

                            codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de categoría"));

                            }catch(NumberFormatException nfe){
                            formatoCorrecto=false;

                            }

                            for(Categoria c:DiarioFacil.getListaCategorias()){
                            if(codigo==c.getCodCategoria()){

                            codigoYaExiste=true;
                            JOptionPane.showMessageDialog(null,"El código ya existe");
                            }
                            }

                            }while(formatoCorrecto==false || codigoYaExiste==true);

                            nombre=JOptionPane.showInputDialog(null,"Ingrese el nombre de la categoría");

                            Categoria cat=new Categoria(codigo,nombre);

                            DiarioFacil.agregarCategoria(cat);


                            }

    public void verCategorias(){
                           StringBuffer lista = new StringBuffer();
                           lista.append("Lista de categorías \n");


                           for(Categoria c:DiarioFacil.getListaCategorias()){

                           lista.append("Código: ");
                           lista.append(c.getCodCategoria());
                           lista.append("\t Nombre: ");
                           lista.append(c.getNombreCategoria());
                           lista.append("\n");

                           }
                           JOptionPane.showMessageDialog(null,lista);
                           }

    public void borrarCategoria(){
                             int codigo=0;
                             boolean formatoCorrecto=true;
                             String nombre="";
                             boolean categoriaExiste=false;
                             do{
                             do{
                             try{

                             codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de categoría a borrar"));

                             }catch(NumberFormatException nfe){

                             formatoCorrecto=false;
                             JOptionPane.showMessageDialog(null,"el código no tiene el formato correcto");

                             }
                             }while(formatoCorrecto==false);

                             for(Iterator<Categoria> c=DiarioFacil.getListaCategorias().iterator();
                             c.hasNext();){
                             Categoria cat=c.next();

                             if(cat.getCodCategoria()==codigo){
                             categoriaExiste=true;
                             nombre=cat.getNombreCategoria();
                             c.remove();
                             JOptionPane.showMessageDialog(null,"La categoría "+nombre+" ha sido "
                             + "eliminada");
                             }
                             }
                             if(categoriaExiste==false){
                             JOptionPane.showMessageDialog(null,"la categoría ingreada no existe");
                             }

                             }while(categoriaExiste==false);
                             }

    public void manejoProductos(){
                             int opcion=0;

                             boolean formatoCorrecto=true;

                             do{

                             try{

                             opcion=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la opcion \n"+
                             "1.Nuevo producto \n"+
                             "2.Ver productos \n"+
                             "3.Ver productos por categoría \n"+
                             "4.modificar productos \n"+
                             "5.borrar productos \n"+
                             "6.Volver"));

                             }catch(NumberFormatException nfe){

                             formatoCorrecto=false;

                             JOptionPane.showMessageDialog(null,"la opción ingresada no tiene el formato correcto");

                             }
                             }while(formatoCorrecto==false ||opcion<1 ||opcion>6);

                             switch(opcion){

                             case 1: agregarProducto();
                             break;

                             case 2: verProductos();
                             break;

                             case 3: verProductosPorCategoria();
                             break;

                             case 4: modificarProductos();

                             break;
                             case 5:borrarProducto();
                             break;
                             case 6:
                             break;

                             }
                             }

    public void agregarProducto(){

                             int codigo=0;
                             boolean formatoCorrecto=true;
                             String nombre="";
                             String correo="";
                             boolean codigoYaExiste=false;
                             boolean categoriaExiste=false;
                             int categoria=0;
                             int proveedor=0;
                             boolean proveedorExiste=false;
                             int stockMinimo=0;
                             int stockActual=0;
                             boolean sActMenorQueMin=false;
                             double precio=0;
                             boolean precioNegativo=false;
                             boolean stockNegativo=false;

                             do{

                             formatoCorrecto=true;
                             codigoYaExiste=false;

                             try{

                             codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de producto"));

                             }catch(NumberFormatException nfe){
                             formatoCorrecto=false;
                             JOptionPane.showMessageDialog(null,"el formato del código de producto no es "
                             + "correcto, recuerde usar solo números");

                             }

                             for(Categoria c:DiarioFacil.getListaCategorias()){
                             for(Producto p:c.getListaProductos()){
                             if(p.getCodProducto()==codigo){
                             codigoYaExiste=true;
                             JOptionPane.showMessageDialog(null,"El código ya existe");
                             }
                             }

                             }

                             }while(formatoCorrecto==false || codigoYaExiste==true);

                             nombre=JOptionPane.showInputDialog(null,"Ingrese el nombre del producto");

                             do{
                             formatoCorrecto=true;
                             categoriaExiste=false;
                             try{

                             categoria=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el código de categoría"));

                             }catch(NumberFormatException nfe){

                             formatoCorrecto=false;
                             JOptionPane.showMessageDialog(null,"El formato del código no es correcto,"
                             + "recuerde usar solo números");

                             }
                             for(Categoria c:DiarioFacil.getListaCategorias()){
                             if(c.getCodCategoria()==categoria){
                             categoriaExiste=true;
                             }
                             }

                             if(categoriaExiste==false){
                             JOptionPane.showMessageDialog(null,"La categoría ingresada no existe");
                             }

                             }while(categoriaExiste==false ||formatoCorrecto==false);



                             do{
                             formatoCorrecto=true;
                             proveedorExiste=false;
                             try{

                             proveedor=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el código de proveedor"));

                             }catch(NumberFormatException nfe){

                             formatoCorrecto=false;
                             JOptionPane.showMessageDialog(null,"El formato del código no es correcto,"
                             + "recuerde usar solo números");

                             }
                             for(Proveedor p:DiarioFacil.getListaProveedores()){
                             if(p.getCodigo()==proveedor){
                             proveedorExiste=true;
                             }
                             }

                             if(proveedorExiste==false){
                             JOptionPane.showMessageDialog(null,"El proveedor ingresado no existe");
                             }

                             }while(proveedorExiste==false ||formatoCorrecto==false);


                             do{
                             formatoCorrecto=true;
                             stockNegativo=false;
                             try{

                             stockMinimo=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el stock mínimo"));

                             }catch(NumberFormatException nfe){

                             formatoCorrecto=false;
                             JOptionPane.showMessageDialog(null,"El formato del stock mínimo no es correcto,"
                             + "recuerde usar solo números enteros");

                             }
                             if(stockMinimo<1){
                             stockNegativo=true;
                             JOptionPane.showMessageDialog(null,"El stock no puede ser inferior a 1");
                             }
                             }while(formatoCorrecto==false || stockNegativo==true);



                             do{
                             sActMenorQueMin=false;
                             formatoCorrecto=true;
                             stockNegativo=false;
                             try{

                             stockActual=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el stock actual"));

                             }catch(NumberFormatException nfe){

                             formatoCorrecto=false;
                             JOptionPane.showMessageDialog(null,"El formato del stock actual no es correcto,"
                             + "recuerde usar solo números enteros");

                             }

                             if(stockActual<stockMinimo){
                             JOptionPane.showMessageDialog(null,"El stock actual no puede ser menor al mínimo");
                             sActMenorQueMin=true;
                             }

                             if(stockActual<1){
                             stockNegativo=true;
                             JOptionPane.showMessageDialog(null,"El stock no puede ser inferior a 1");
                             }
                             }while(formatoCorrecto==false ||sActMenorQueMin==true || stockNegativo==true);



                             do{
                             formatoCorrecto=true;
                             precioNegativo=false;


                             try{
                             precio=Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el precio del producto"));

                             }catch(NumberFormatException nfe){
                             formatoCorrecto=false;
                             }


                             if(precio<1){
                             JOptionPane.showMessageDialog(null,"El precio no puede ser cero o negativo");
                             precioNegativo=true;
                             }
                             } while(precioNegativo==true ||formatoCorrecto==false);

                             for(Categoria c:DiarioFacil.getListaCategorias()){
                             if(c.getCodCategoria()==categoria){

                             Producto prod=new Producto(proveedor,codigo,nombre,stockMinimo,stockActual,precio);
                             c.agregarProducto(prod);
                             }

                             }

                             }

    public void verProductos(){
                          StringBuffer lista = new StringBuffer();
                          lista.append("Lista de productos \n");


                          for(Categoria c:DiarioFacil.getListaCategorias()){
                          for(Producto p:c.getListaProductos()){
                          lista.append("Código: ");
                          lista.append(p.getCodProducto());
                          lista.append("\t nombre:");
                          lista.append(p.getNombreProd());

                          for(Proveedor prov:DiarioFacil.getListaProveedores()){
                          if(prov.getCodigo()==p.getCodProveedor()){
                          lista.append("\t Proveedor:");
                          lista.append(prov.getNombre());

                          }
                          }
                          lista.append("\t Categoría:");
                          lista.append(c.getNombreCategoria());
                          lista.append("\t Precio:");
                          lista.append(p.getPrecio());
                          lista.append("\t Stock minimo:");
                          lista.append(p.getStockMinimo());
                          lista.append("\t Stock actual:");
                          lista.append(p.getStockActual());
                          lista.append("\n");


                          }


                          }
                          JOptionPane.showMessageDialog(null,lista);

                          }

    public void verProductosPorCategoria(){
                                      boolean formatoCorrecto=true;
                                      boolean categoriaExiste=false;
                                      StringBuffer lista = new StringBuffer();
                                      int categoria=0;

                                      do{
                                      formatoCorrecto=true;
                                      categoriaExiste=false;
                                      try{

                                      categoria=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el código de categoría"));

                                      }catch(NumberFormatException nfe){

                                      formatoCorrecto=false;
                                      JOptionPane.showMessageDialog(null,"El formato del código no es correcto,"
                                      + "recuerde usar solo números");

                                      }
                                      for(Categoria c:DiarioFacil.getListaCategorias()){
                                      if(c.getCodCategoria()==categoria){

                                      lista.append("lista de productos de la categoria ");
                                      lista.append(c.getNombreCategoria());
                                      lista.append("\n \n");
                                      categoriaExiste=true;

                                      for(Producto p:c.getListaProductos()){
                                      lista.append("Código: ");
                                      lista.append(p.getCodProducto());
                                      lista.append("\t nombre:");
                                      lista.append(p.getNombreProd());

                                      for(Proveedor prov:DiarioFacil.getListaProveedores()){
                                      if(prov.getCodigo()==p.getCodProveedor()){
                                      lista.append("\t Proveedor:");
                                      lista.append(prov.getNombre());

                                      }
                                      }

                                      lista.append("\t Precio:");
                                      lista.append(p.getPrecio());
                                      lista.append("\t Stock minimo:");
                                      lista.append(p.getStockMinimo());
                                      lista.append("\t Stock actual:");
                                      lista.append(p.getStockActual());
                                      lista.append("\n");


                                      }
                                      }
                                      }

                                      if(categoriaExiste==false){
                                      JOptionPane.showMessageDialog(null,"La categoría ingresada no existe");
                                      }

                                      }while(categoriaExiste==false ||formatoCorrecto==false);

                                      JOptionPane.showMessageDialog(null,lista);

                                      }

    public void modificarProductos(){
                                int codigo=0;
                                boolean productoExiste=false;
                                boolean formatoCorrecto=true;
                                int opcion=0;
                                boolean formatoCorrectoOpcion=true;
                                boolean proveedorExiste=false;
                                int proveedor=0;
                                boolean precioNegativo=false;
                                double precio=0;
                                int stockMinimo=0;
                                boolean stockNegativo=false;
                                do{
                                formatoCorrecto=true;
                                productoExiste=false;
                                try{
                                codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código del producto"));
                                }catch(NumberFormatException nfe){
                                formatoCorrecto=false;
                                JOptionPane.showMessageDialog(null,"el código no tiene el formato correcto");
                                }

                                for(Categoria c:DiarioFacil.getListaCategorias()){
                                for(Producto p:c.getListaProductos()){

                                if(codigo==p.getCodProducto()){
                                productoExiste=true;
                                do{
                                try{
                                opcion=Integer.parseInt(JOptionPane.showInputDialog(null,"Modificar el producto "
                                +p.getNombreProd()+"\n"
                                +"1.Modificar proveedor"
                                + "\n 2.Modificar precio \n 3.Modificar stock mínimo"+
                                "\n 4.volver"));
                                }catch(NumberFormatException nfe){
                                formatoCorrectoOpcion=false;
                                JOptionPane.showMessageDialog(null,"la opción no tiene el formato correcto");
                                }


                                }while(formatoCorrectoOpcion=false || opcion>4 || opcion<1);

                                switch(opcion){
                                case 1:
                                do{
                                formatoCorrecto=true;
                                proveedorExiste=false;
                                try{

                                proveedor=Integer.parseInt(JOptionPane.showInputDialog
                                (null, "Ingrese el nuevo código de proveedor"));

                                }catch(NumberFormatException nfe){

                                formatoCorrecto=false;
                                JOptionPane.showMessageDialog(null,"El formato del código"
                                + " no es correcto,"
                                + "recuerde usar solo números");

                                }
                                for(Proveedor pr:DiarioFacil.getListaProveedores()){
                                if(pr.getCodigo()==proveedor){
                                proveedorExiste=true;
                                }
                                }

                                if(proveedorExiste==false){
                                JOptionPane.showMessageDialog
                                (null,"El proveedor ingresado no existe");
                                }

                                }while(proveedorExiste==false ||formatoCorrecto==false);

                                p.setCodProveedor(proveedor);

                                JOptionPane.showMessageDialog(null,"se ha actualizado"
                                + " el código de proveedor");

                                break;

                                case 2:
                                do{
                                formatoCorrecto=true;
                                precioNegativo=false;


                                try{
                                precio=Double.parseDouble
                                (JOptionPane.showInputDialog
                                (null,"Ingrese el nuevo precio del producto"));

                                }catch(NumberFormatException nfe){
                                formatoCorrecto=false;
                                }


                                if(precio<1){
                                JOptionPane.showMessageDialog
                                (null,"El precio no puede ser cero o negativo");
                                precioNegativo=true;
                                }

                                } while(precioNegativo==true ||formatoCorrecto==false);

                                p.setPrecio(precio);

                                JOptionPane.showMessageDialog
                                (null,"Se ha actualizado el precio");

                                break;

                                case 3:
                                do{
                                formatoCorrecto=true;
                                stockNegativo=false;
                                try{

                                stockMinimo=Integer.parseInt
                                (JOptionPane.showInputDialog
                                (null,"Ingrese el nuevo stock mínimo"));

                                }catch(NumberFormatException nfe){

                                formatoCorrecto=false;
                                JOptionPane.showMessageDialog
                                (null,"El formato del stock mínimo no es correcto,"
                                + "recuerde usar solo números enteros");

                                }
                                if(stockMinimo<1){
                                stockNegativo=true;
                                JOptionPane.showMessageDialog
                                (null,"El stock no puede ser inferior a 1");
                                }
                                }while(formatoCorrecto==false || stockNegativo==true);

                                p.setStockMinimo(stockMinimo);
                                JOptionPane.showMessageDialog(null,"El stock ha sido actualizado");
                                //actualizar
                                break;

                                case 4:
                                break;

                                }

                                }
                                }
                                }



                                if(productoExiste==false){
                                JOptionPane.showMessageDialog(null,"El producto ingresado no existe");
                                }
                                }while(formatoCorrecto==false || productoExiste==false);

                                }

    public void borrarProducto(){

                            int codigo=0;
                            boolean formatoCorrecto=true;
                            String nombre="";
                            boolean productoExiste=false;

                            do{
                            do{
                            formatoCorrecto=true;
                            try{

                            codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de producto a borrar"));

                            }catch(NumberFormatException nfe){

                            formatoCorrecto=false;
                            JOptionPane.showMessageDialog(null,"el código no tiene el formato correcto");

                            }
                            }while(formatoCorrecto==false);

                            for(Categoria cat:DiarioFacil.getListaCategorias()){
                            for(Iterator<Producto> produ=cat.getListaProductos().iterator();
                            produ.hasNext();){

                            Producto p=produ.next();
                            if(p.getCodProducto()==codigo){

                            productoExiste=true;
                            nombre=p.getNombreProd();
                            produ.remove();
                            JOptionPane.showMessageDialog(null,"El producto "+nombre+
                            " ha sido eliminado");

                            }
                            }
                            }

                            if(productoExiste==false){
                            JOptionPane.showMessageDialog(null,"El producto no existe");
                            }
                            }while(productoExiste==false);
                            }

    public void mantenimientoAdmin(){
                                int opcion=0;

                                boolean formatoCorrecto=true;

                                do{
                                formatoCorrecto=true;
                                try{

                                opcion=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la opcion \n"+
                                "1.Nuevo administrador \n"+
                                "2.Ver lista de administradores \n"+
                                "3.Modificar mi contraseña \n"+
                                "4.borrar mi cuenta \n"+
                                "5.volver "));

                                }catch(NumberFormatException nfe){

                                formatoCorrecto=false;

                                JOptionPane.showMessageDialog(null,"la opción ingresada no tiene el formato correcto");

                                }
                                }while(formatoCorrecto==false ||opcion<1 ||opcion>5);

                                switch(opcion){

                                case 1: agregarAdmin();
                                break;

                                case 2: listaAdmins();
                                break;

                                case 3: cambiarMiContrasenna();
                                break;

                                case 4: borrarMiCuenta();
                                break;
                                case 5:
                                break;


                                }
                                }

    public void agregarAdmin(){
                          int cedula=0;
                          boolean formatoCorrecto=true;
                          String nombre="";
                          String contra="";
                          boolean cedulaYaExiste=false;
                          do{
                          formatoCorrecto=true;
                          cedulaYaExiste=false;

                          try{

                          cedula=Integer.parseInt(JOptionPane.showInputDialog
                          (null, "Ingrese la cédula del administrador"));

                          }catch(NumberFormatException nfe){
                          formatoCorrecto=false;
                          }
                          for(Usuario u:DiarioFacil.getLstUsuarios()){
                          if(cedula==u.getCedula()){
                          cedulaYaExiste=true;
                          JOptionPane.showMessageDialog
                          (null,"La cédula ingresada ya se encuentra registrada");
                          }
                          }


                          }while(formatoCorrecto==false || cedulaYaExiste==true);

                          nombre=JOptionPane.showInputDialog(null,"Ingrese el nombre del administrador");

                          contra=JOptionPane.showInputDialog(null,"Ingrese la contraseña del administrador");

                          Usuario us=new Administrador(nombre,contra,cedula);
                          DiarioFacil.agregarUsuario(us);



                          }

    public void listaAdmins(){
                         StringBuffer lista = new StringBuffer();
                         lista.append("Lista de administradores \n");

                         for(Usuario u:DiarioFacil.getLstUsuarios()){
                         if(u instanceof Administrador){
                         lista.append("Nombre: ");
                         lista.append(u.getNombrePersona());
                         lista.append("\t Cédula: ");
                         lista.append(u.getCedula());
                         lista.append("\n");


                         }
                         }

                         JOptionPane.showMessageDialog(null,lista);

                         }

    public void cambiarMiContrasenna(){
                                  String contraAnterior="";
                                  String contraNueva="";
                                  String confirmacion="";
                                  boolean coincide=false;
                                  do{
                                  coincide=false;

                                  contraAnterior=JOptionPane.showInputDialog(null,"Ingrese la contraseña actual");

                                  if(admin.getContraPersona().equals(contraAnterior)){
                                  coincide=true;
                                  }else{
                                  JOptionPane.showMessageDialog(null,"contraseña incorrecta");
                                  }

                                  }while(coincide==false);

                                  do{
                                  coincide=false;

                                  contraNueva=JOptionPane.showInputDialog(null,"Ingrese la nueva contraseña");
                                  confirmacion=JOptionPane.showInputDialog(null,"Ingrese la nueva contraseña nuevamente");

                                  if(contraNueva.equals(confirmacion)){
                                  coincide=true;
                                  }else{
                                  JOptionPane.showMessageDialog(null,"las contraseñas no coinciden");
                                  }

                                  }while(coincide==false);

                                  for(Usuario u:DiarioFacil.getLstUsuarios()){
                                  if(u.getCedula()==admin.getCedula()){
                                  u.setContraPersona(contraNueva);
                                  }
                                  }
                                  JOptionPane.showMessageDialog(null,"Se ha actualizado la contraseña");

                                  }

    public void borrarMiCuenta(){
                            String respuesta="";
                            do{
                            respuesta=JOptionPane.showInputDialog
                            (null,"Está a punto de borrar su cuenta, ¿desea continuar?"
                            + "\n"+
                            "(Responda usando s/n) ");
                            }while(!respuesta.equals("s") && !respuesta.equals("n"));

                            if(respuesta.equals("s")){
                            for(Iterator<Usuario> u=DiarioFacil.getLstUsuarios().iterator();u.hasNext();){
                            Usuario user=u.next();
                            if(user.getCedula()==admin.getCedula()){
                            u.remove();
                            }
                            }

                            Tester.menuPrincipal();
                            }



                            }

    public void mantenimientoClientes(){
        int opcion=0;

        boolean formatoCorrecto=true;

        do{
            formatoCorrecto=true;
            try{

                opcion=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la opción \n"+
                        "1.Ver lista de clientes \n"+
                        "2.Dar o revocar condición de frecuente \n"+
                        "3.Borrar cliente \n"+
                        "4.Volver \n"));

            }catch(NumberFormatException nfe){

                formatoCorrecto=false;

                JOptionPane.showMessageDialog(null,"la opción ingresada no tiene el formato correcto");

            }
        }while(formatoCorrecto==false ||opcion<1 ||opcion>4);

        switch(opcion){

            case 1: listaClientes();
                break;

            case 2: controlarFrecuente();
                break;

            case 3: borrarCliente();
                break;

            case 4:
                break;



        }

    }

    public void listaClientes(){
                           StringBuffer lista = new StringBuffer();
                           lista.append("Lista de clientes \n");

                           for(Usuario u:DiarioFacil.getLstUsuarios()){
                           if(u instanceof Cliente){
                           lista.append("Nombre: ");
                           lista.append(u.getNombrePersona());
                           lista.append("\t Cédula: ");
                           lista.append(u.getCedula());
                           lista.append("\t Frecuente: ");
                           if(((Cliente) u).isFrecuente()){
                           lista.append("Sí");
                           }else{
                           lista.append("No");
                           }
                           lista.append("\n");


                           }
                           }

                           JOptionPane.showMessageDialog(null,lista);
                           }

    public void controlarFrecuente(){
                                int opcion=0;

                                boolean formatoCorrecto=true;

                                do{
                                formatoCorrecto=true;
                                try{

                                opcion=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la opción \n"+
                                "1.Convertir cliente en frecuente \n"+
                                "2.Revocar condición de frecuente \n"+
                                "3.Volver "));

                                }catch(NumberFormatException nfe){

                                formatoCorrecto=false;

                                JOptionPane.showMessageDialog(null,"la opción ingresada no tiene el formato correcto");

                                }
                                }while(formatoCorrecto==false ||opcion<1 ||opcion>3);

                                switch(opcion){
                                case 1: volverFrecuente();
                                break;
                                case 2: quitarFrecuente();
                                break;
                                case 3:
                                break;
                                }
                                }

    public void volverFrecuente(){
                             int cedula=0;
                             boolean clienteExiste=false;
                             boolean formatoCorrecto=true;
                             do{


                             do{
                             formatoCorrecto=true;
                             try{

                             cedula=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cédula \n"));

                             }catch(NumberFormatException nfe){

                             formatoCorrecto=false;

                             JOptionPane.showMessageDialog(null,"la cedula ingresada no tiene el formato correcto,"
                             + "recuerde usar solo números");

                             }
                             }while(formatoCorrecto==false);

                             for(Usuario u:DiarioFacil.getLstUsuarios()){
                             if(u instanceof Cliente && cedula==u.getCedula()){
                             clienteExiste=true;
                             if(((Cliente) u).isFrecuente()){
                             JOptionPane.showMessageDialog(null,"El cliente ya es uno frecuente");
                             }else{
                             ((Cliente) u).setFrecuente(true);
                             JOptionPane.showMessageDialog
                             (null,"Tipo de cliente de " +u.getNombrePersona()+" ahora es frecuente" );
                             }
                             }
                             }
                             if(clienteExiste==false){
                             JOptionPane.showMessageDialog(null,"El cliente ingresado no existe");
                             }

                             }while(clienteExiste==false);
                             }

    public void quitarFrecuente(){
                             int cedula=0;
                             boolean clienteExiste=false;
                             boolean formatoCorrecto=true;
                             do{


                             do{
                             formatoCorrecto=true;
                             try{

                             cedula=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cédula \n"));

                             }catch(NumberFormatException nfe){

                             formatoCorrecto=false;

                             JOptionPane.showMessageDialog(null,"la cedula ingresada no tiene el formato correcto,"
                             + "recuerde usar solo números");

                             }
                             }while(formatoCorrecto==false);

                             for(Usuario u:DiarioFacil.getLstUsuarios()){
                             if(u instanceof Cliente && cedula==u.getCedula()){
                             clienteExiste=true;
                             if(((Cliente) u).isFrecuente()){

                             ((Cliente) u).setFrecuente(false);
                             JOptionPane.showMessageDialog
                             (null,"El cliente " +u.getNombrePersona()+" ya no es frecuente" );
                             }else{

                             JOptionPane.showMessageDialog
                             (null,"El cliente no es frecuente" );
                             }
                             }
                             }
                             if(clienteExiste==false){
                             JOptionPane.showMessageDialog(null,"El cliente ingresado no existe");
                             }

                             }while(clienteExiste==false);
                             }

    public void borrarCliente(){
                           int cedula=0;
                           String nombre="";
                           boolean formatoCorrecto=true;
                           boolean clienteExiste=false;
                           do{
                           do{
                           formatoCorrecto=true;
                           try{

                           cedula=Integer.parseInt(JOptionPane.showInputDialog
                           (null, "Ingrese la cédula del cliente a borrar \n"));

                           }catch(NumberFormatException nfe){

                           formatoCorrecto=false;

                           JOptionPane.showMessageDialog(null,"la cedula ingresada no tiene el formato correcto,"
                           + "recuerde usar solo números");

                           }
                           }while(formatoCorrecto==false);




                           for(Iterator<Usuario> u=DiarioFacil.getLstUsuarios().iterator();u.hasNext();){
                           Usuario user=u.next();
                           if(user.getCedula()==cedula && user instanceof Cliente ){
                           clienteExiste=true;
                           nombre=user.getNombrePersona();
                           u.remove();
                           JOptionPane.showMessageDialog(null,"El cliente "+nombre
                           +" ha sido removido del sistema");
                           }
                           }
                           if(clienteExiste==false){
                           JOptionPane.showMessageDialog(null,"El cliente ingresado no existe");
                           }

                           }while(clienteExiste==false);

                           }

    public void manejarCombos(){
                           int opcion=0;
                           boolean inputInvalido=false;
                           do{
                           inputInvalido=false;

                           try{
                           opcion=Integer.parseInt(JOptionPane.showInputDialog(null,
                           "Ingrese la opción \n"+"1.Nuevo combo \n"+"2. Ver combos \n"+
                           "3.Ver productos por combo \n"+"4.modificar combo \n"+
                           "5.borrar combo \n"+"6.Volver"));
                           }catch(NumberFormatException nfe){
                           inputInvalido=true;
                           }


                           }while(opcion>6 || opcion<1 || inputInvalido==true);



                           switch(opcion){
                           case 1:
                           nuevoCombo();
                           break;
                           case 2:
                           verCombos();

                           break;
                           case 3:
                           verProductosPorCombo();
                           break;

                           case 4:
                           modificarCombo();
                           break;

                           case 5:borrarCombo();
                           break;

                           case 6: //volver
                           break;
                           }
                           }

    public void nuevoCombo(){
                        int codigo=0;
                        boolean formatoCorrecto=true;
                        String nombre="";
                        consecutivo=1;
                        boolean codigoYaExiste=false;
                        do{
                        formatoCorrecto=true;
                        codigoYaExiste=false;
                        try{
                        codigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de combo"));
                        }catch(NumberFormatException nfe){
                        formatoCorrecto=false;
                        JOptionPane.showMessageDialog(null,"El formato del código no es correcto"
                        + "recuerde usar solo números enteros");
                        }

                        for(Combos com:DiarioFacil.getListaCombos()){
                        if(codigo==com.getCodCombo()){
                        codigoYaExiste=true;
                        JOptionPane.showMessageDialog(null,"El código ya existe");
                        }

                        }
                        if(codigoYaExiste==false){
                        nombre=JOptionPane.showInputDialog(null,"Ingrese el nombre del combo");
                        }


                        }while(formatoCorrecto==false || codigoYaExiste==true);

                        Combos c2=new Combos();
                        combo=c2;

                        combo.setNombreCombo(nombre);
                        combo.setCodCombo(codigo);

                        int opcion=0;
                        boolean inputInvalido=false;
                        do{
                        do{
                        inputInvalido=false;

                        try{
                        opcion=Integer.parseInt(JOptionPane.showInputDialog(null,
                        "1.Agregar producto al combo \n"+"2. Ver productos en el combo \n"+
                        "3.quitar producto del combo \n"+"4.completar combo \n"+
                        "5.cancelar combo "));
                        }catch(NumberFormatException nfe){
                        inputInvalido=true;
                        JOptionPane.showMessageDialog(null,"la opción ingresada no tiene el formato correcto"
                        + "recuerde usar solo números enteros ");
                        }


                        }while(opcion>5 || opcion<1 || inputInvalido==true);


                        switch(opcion){
                        case 1:

                        agregarACombo();
                        break;

                        case 2:

                        verProductosEnCombo();
                        break;

                        case 3:
                        quitarProductoDeComboActual();
                        break;

                        case 4:
                        completarCombo();
                        break;
                        case 5:
                        break;

                        }

                        }while(opcion!=4 && opcion!=5);

                        }

    public void agregarACombo(){
                           int cod=0;
                           boolean prodExiste=false;
                           boolean formatoIncorrecto=false;
                           boolean formatoCorrectoCantidad=true;
                           boolean productoRepetido=false;
                           int cantidad=0;
                           do{
                           formatoIncorrecto=false;
                           try{
                           cod=Integer.parseInt
                           (JOptionPane.showInputDialog(null,"Ingrese el código de producto"));
                           }catch(NumberFormatException nfe){
                           formatoIncorrecto=true;
                           JOptionPane.showMessageDialog
                           (null,"El formato del código no es correcto, recuerde usar solo numeros enteros ");
                           }
                           for(Categoria c:DiarioFacil.getListaCategorias()){
                           for(Producto p:c.getListaProductos()){
                           if(cod==p.getCodProducto()){
                           prodExiste=true;

                           do{
                           formatoCorrectoCantidad=true;
                           try{
                           cantidad=Integer.parseInt
                           (JOptionPane.showInputDialog(null,"Ingrese la cantidad de productos"));

                           }catch(NumberFormatException nfe){
                           formatoCorrectoCantidad=false;
                           JOptionPane.showMessageDialog
                           (null,"El formato de la cantidad no es el correcto, "
                           + "recuerde usar solo números enteros positivos ");
                           }

                           if(cantidad<1){
                           JOptionPane.showMessageDialog
                           (null,"El formato de la cantidad no es el correcto, "
                           + "recuerde usar solo números enteros positivos ");
                           }

                           }while(formatoCorrectoCantidad==false || cantidad<1);

                           for(Item i:combo.getListaItems()){
                           if(i.getProd().getCodProducto()==cod){
                           productoRepetido=true;
                           cantidad=cantidad+i.getCantidad();
                           i.setCantidad(cantidad);
                           }
                           }
                           if(productoRepetido==false){
                           Item it=new Item(consecutivo,p,cantidad);
                           combo.agregarItem(it);
                           }


                           }
                           }
                           }

                           if(prodExiste==false){
                           JOptionPane.showMessageDialog
                           (null,"El producto ingresado no existe");
                           }

                           }while(formatoIncorrecto==true || prodExiste==false);


                           consecutivo++;

                           }

    public void verProductosEnCombo(){
                                 StringBuffer lista = new StringBuffer();
                                 lista.append("Productos actualmente en el combo "+combo.getNombreCombo()+"\n");

                                 for(Item i:combo.getListaItems()){
                                 lista.append("Consecutivo:");
                                 lista.append(i.getConsecutivo());
                                 lista.append("\t Producto: ");
                                 lista.append(i.getProd().getNombreProd());
                                 lista.append("\t Cantidad:");
                                 lista.append(i.getCantidad());
                                 lista.append("\n");

                                 }

                                 JOptionPane.showMessageDialog(null,lista);
                                 }

    public void quitarProductoDeComboActual(){
                                         int consecutivo=0;
                                         String nombre="";
                                         boolean formatoCorrecto=true;
                                         boolean itemExiste=false;
                                         do{
                                         do{
                                         formatoCorrecto=true;
                                         try{

                                         consecutivo=Integer.parseInt(JOptionPane.showInputDialog
                                         (null, "Ingrese el consecutivo del ítem a borrar \n"));

                                         }catch(NumberFormatException nfe){

                                         formatoCorrecto=false;

                                         JOptionPane.showMessageDialog(null,"el consecutivo ingresado no tiene el formato correcto,"
                                         + "recuerde usar solo números enteros");

                                         }
                                         }while(formatoCorrecto==false);

                                         for(Iterator<Item> it=combo.getListaItems().iterator();it.hasNext();){
                                         Item item=it.next();
                                         if(item.getConsecutivo()==consecutivo){
                                         itemExiste=true;
                                         nombre=item.getProd().getNombreProd();
                                         it.remove();
                                         JOptionPane.showMessageDialog
                                         (null,"El ítem "+nombre+" ha sido removido del combo");
                                         }
                                         }






                                         if(itemExiste==false){
                                         JOptionPane.showMessageDialog
                                         (null,"El consecutivo ingresado no coincide con ningún item del combo");
                                         }

                                         }while(itemExiste==false);

                                         }

    public void completarCombo(){
                            double precio=0;
                            boolean formatoCorrecto=true;

                            do{
                            formatoCorrecto=true;
                            try{
                            precio=Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el precio del combo"));
                            }catch(NumberFormatException nfe){
                            formatoCorrecto=false;
                            JOptionPane.showMessageDialog
                            (null,"El formato del precio ingresado no es correcto,"+
                            "recuerde usar solo números");
                            }

                            if(precio<1){
                            JOptionPane.showMessageDialog(null,"El precio no puede ser negativo");
                            }

                            }while(formatoCorrecto==false ||precio<1);
                            combo.setPrecio(precio);
                            DiarioFacil.agregarCombo(combo);

                            JOptionPane.showMessageDialog(null,"El combo ha sido registrado con éxito");


                            }

    public void verCombos(){
                       StringBuffer lista = new StringBuffer();
                       lista.append("Lista de combos \n");

                       for(Combos c:DiarioFacil.getListaCombos()){
                       lista.append("Código:");
                       lista.append(c.getCodCombo());
                       lista.append("\t Nombre:");
                       lista.append(c.getNombreCombo());
                       lista.append("\t Precio:");
                       lista.append(c.getPrecio());
                       lista.append("\t Activado:");
                       if(c.isActivo()){
                       lista.append("Sí");
                       }else{
                       lista.append("no");
                       }

                       lista.append("\n");

                       }



                       JOptionPane.showMessageDialog(null,lista);
                       }

    public void verProductosPorCombo(){
                                  int codigo=0;
                                  boolean formatoCorrecto=true;
                                  boolean comboExiste=false;
                                  StringBuffer lista = new StringBuffer();
                                  do{
                                  do{
                                  formatoCorrecto=true;
                                  try{
                                  codigo=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese código del combo"));
                                  }catch(NumberFormatException nfe){
                                  formatoCorrecto=false;
                                  JOptionPane.showMessageDialog
                                  (null,"El formato del código no es el correcto,"+
                                  "recuerde usar solo números enteros");
                                  }


                                  }while(formatoCorrecto==false );




                                  for(Combos c:DiarioFacil.getListaCombos()){
                                  if(c.getCodCombo()==codigo){
                                  lista.append("Lista de productos del combo "+c.getNombreCombo()+"\n");
                                  comboExiste=true;

                                  for(Item i:c.getListaItems()){
                                  lista.append("Consecutivo:");
                                  lista.append(i.getConsecutivo());
                                  lista.append("\t Producto: ");
                                  lista.append(i.getProd().getNombreProd());
                                  lista.append("\t Cantidad:");
                                  lista.append(i.getCantidad());
                                  lista.append("\n");
                                  }
                                  }
                                  }

                                  if(comboExiste==false){
                                  JOptionPane.showMessageDialog(null,"El combo ingresado no existe");
                                  }

                                  }while(comboExiste==false);
                                  JOptionPane.showMessageDialog(null,lista);
                                  }

    public void modificarCombo(){
                            int codigo=0;
                            boolean formatoCorrecto=true;
                            boolean comboExiste=false;
                            do{
                            do{
                            formatoCorrecto=true;
                            try{
                            codigo=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese código del combo a modificar"));
                            }catch(NumberFormatException nfe){
                            formatoCorrecto=false;
                            JOptionPane.showMessageDialog
                            (null,"El formato del código no es el correcto,"+
                            "recuerde usar solo números enteros");
                            }


                            }while(formatoCorrecto==false );

                            for(Combos c:DiarioFacil.getListaCombos()){
                            if(c.getCodCombo()==codigo){
                            comboExiste=true;
                            }
                            }
                            if(comboExiste==false){
                            JOptionPane.showMessageDialog(null,"El combo ingresado no existe");
                            }

                            }while(comboExiste==false);

                            comboModificando=codigo;

                            int opcion=0;
                            boolean inputInvalido=false;

                            do{
                            inputInvalido=false;

                            try{
                            opcion=Integer.parseInt(JOptionPane.showInputDialog(null,
                            "1.Agregar producto al combo \n"+
                            "2.Borrar producto del combo \n"+
                            "3. Cambiar cantidades de un producto \n"+
                            "4.Cambiar nombre del combo \n"+
                            "5.Cambiar precio del combo \n "+
                            "6.Activar/desactivar el combo \n"+
                            "7.Volver"));
                            }catch(NumberFormatException nfe){
                            inputInvalido=true;
                            JOptionPane.showMessageDialog(null,"la opción ingresada no tiene el formato correcto"
                            + "recuerde usar solo números enteros ");
                            }


                            }while(opcion>7 || opcion<1 || inputInvalido==true);


                            switch(opcion){
                            case 1:

                            agregarProductoComboModificar();
                            break;

                            case 2:

                            borrarProductoComboModificar();
                            break;

                            case 3:
                            cambiarCantProdComboModificando();
                            break;

                            case 4:
                            cambiarNombreComboModificando();
                            break;
                            case 5:
                            cambiarPrecioComboModificando();
                            break;

                            case 6:activarDesactivarCombo();
                            break;

                            case 7://volver
                            break;

                            }



                            }

    public void agregarProductoComboModificar(){
                                           int cod=0;
                                           boolean prodExiste=false;
                                           boolean formatoIncorrecto=false;
                                           boolean formatoCorrectoCantidad=true;
                                           boolean productoRepetido=false;
                                           int cantidad=0;
                                           int consecutivo=0;
                                           do{
                                           formatoIncorrecto=false;
                                           try{
                                           cod=Integer.parseInt
                                           (JOptionPane.showInputDialog(null,"Ingrese el código de producto"));
                                           }catch(NumberFormatException nfe){
                                           formatoIncorrecto=true;
                                           JOptionPane.showMessageDialog
                                           (null,"El formato del código no es correcto, recuerde usar solo numeros enteros ");
                                           }
                                           for(Categoria c:DiarioFacil.getListaCategorias()){
                                           for(Producto p:c.getListaProductos()){
                                           if(cod==p.getCodProducto()){
                                           prodExiste=true;

                                           do{
                                           formatoCorrectoCantidad=true;
                                           try{
                                           cantidad=Integer.parseInt
                                           (JOptionPane.showInputDialog(null,"Ingrese la cantidad de productos"));

                                           }catch(NumberFormatException nfe){
                                           formatoCorrectoCantidad=false;
                                           JOptionPane.showMessageDialog
                                           (null,"El formato de la cantidad no es el correcto, "
                                           + "recuerde usar solo números enteros positivos ");
                                           }

                                           if(cantidad<1){
                                           JOptionPane.showMessageDialog
                                           (null,"El formato de la cantidad no es el correcto, "
                                           + "recuerde usar solo números enteros positivos ");
                                           }

                                           }while(formatoCorrectoCantidad==false || cantidad<1);

                                           for(Combos com:DiarioFacil.getListaCombos()){

                                           if(com.getCodCombo()==comboModificando){

                                           for(Item i:com.getListaItems()){


                                           if(i.getProd().getCodProducto()==cod){
                                           productoRepetido=true;
                                           cantidad=cantidad+i.getCantidad();
                                           i.setCantidad(cantidad);
                                           }
                                           consecutivo=com.getListaItems().size()-1;


                                           }
                                           if(productoRepetido==false){
                                           Item it=new Item(consecutivo,p,cantidad);
                                           com.agregarItem(it);
                                           }

                                           }
                                           }




                                           }
                                           }
                                           }

                                           if(prodExiste==false){
                                           JOptionPane.showMessageDialog
                                           (null,"El producto ingresado no existe");
                                           }

                                           }while(formatoIncorrecto==true || prodExiste==false);
                                           }

    public void borrarProductoComboModificar(){
                                          int consecutivo=0;
                                          String nombre="";
                                          boolean formatoCorrecto=true;
                                          boolean itemExiste=false;
                                          do{
                                          do{
                                          formatoCorrecto=true;
                                          try{

                                          consecutivo=Integer.parseInt(JOptionPane.showInputDialog
                                          (null, "Ingrese el consecutivo del ítem a borrar \n"));

                                          }catch(NumberFormatException nfe){

                                          formatoCorrecto=false;

                                          JOptionPane.showMessageDialog(null,"el consecutivo ingresado no tiene el formato correcto,"
                                          + "recuerde usar solo números enteros");

                                          }
                                          }while(formatoCorrecto==false);
                                          for(Combos com:DiarioFacil.getListaCombos()){
                                          if(com.getCodCombo()==comboModificando){
                                          for(Iterator<Item> it=com.getListaItems().iterator();it.hasNext();){
                                          Item item=it.next();
                                          if(item.getConsecutivo()==consecutivo){
                                          itemExiste=true;
                                          nombre=item.getProd().getNombreProd();
                                          it.remove();
                                          JOptionPane.showMessageDialog
                                          (null,"El ítem "+nombre+" ha sido removido del combo");
                                          }
                                          }
                                          }
                                          }







                                          if(itemExiste==false){
                                          JOptionPane.showMessageDialog
                                          (null,"El consecutivo ingresado no coincide con ningún item del combo");
                                          }

                                          }while(itemExiste==false);
                                          }

    public void cambiarCantProdComboModificando(){
                                             int consecutivo=0;
                                             String nombre="";
                                             boolean formatoCorrecto=true;
                                             boolean itemExiste=false;
                                             int cantidad=0;
                                             boolean cantidadIncorrecta=false;
                                             String textoCantidad="";
                                             do{
                                             do{
                                             formatoCorrecto=true;
                                             try{

                                             consecutivo=Integer.parseInt(JOptionPane.showInputDialog
                                             (null, "Ingrese el consecutivo del ítem al que quiere cambiar su cantidad \n"));

                                             }catch(NumberFormatException nfe){

                                             formatoCorrecto=false;

                                             JOptionPane.showMessageDialog(null,"el consecutivo ingresado no tiene el formato correcto,"
                                             + "recuerde usar solo números enteros");

                                             }
                                             }while(formatoCorrecto==false);


                                             for(Combos com:DiarioFacil.getListaCombos()){
                                             if(com.getCodCombo()==comboModificando){
                                             for(Iterator<Item> it=com.getListaItems().iterator();it.hasNext();){
                                             Item item=it.next();
                                             if(item.getConsecutivo()==consecutivo){
                                             itemExiste=true;
                                             nombre=item.getProd().getNombreProd();

                                             textoCantidad="Ingrese la nueva cantidad del ítem "+nombre;
                                             do{
                                             cantidadIncorrecta=false;
                                             try{
                                             cantidad=Integer.parseInt(JOptionPane.showInputDialog
                                             (null,textoCantidad));
                                             } catch(NumberFormatException nfe){
                                             cantidadIncorrecta=true;
                                             JOptionPane.showMessageDialog
                                             (null,"El formato de la cantidad no es correcto, "
                                             + "recuerde usar solo numeros enteros positivos");

                                             }
                                             if(cantidad<1){
                                             JOptionPane.showMessageDialog
                                             (null,"El formato de la cantidad no es correcto, "
                                             + "recuerde usar solo numeros enteros positivos");
                                             }

                                             }while(cantidadIncorrecta==true);

                                             item.setCantidad(cantidad);
                                             JOptionPane.showMessageDialog
                                             (null,"La cantidad del item "+nombre+" ha sido modificada");

                                             }
                                             }
                                             }
                                             }







                                             if(itemExiste==false){
                                             JOptionPane.showMessageDialog
                                             (null,"El consecutivo ingresado no coincide con ningún item del combo");
                                             }

                                             }while(itemExiste==false);


                                             }

    public void cambiarNombreComboModificando(){

                                           String nombre="";
                                           String nuevoNombre="";



                                           for(Combos com:DiarioFacil.getListaCombos()){
                                           if(com.getCodCombo()==comboModificando){


                                           nombre=com.getNombreCombo();

                                           nuevoNombre=JOptionPane.showInputDialog
                                           (null,"Ingrese el nuevo nombre del combo "+ nombre);

                                           com.setNombreCombo(nuevoNombre);

                                           JOptionPane.showMessageDialog
                                           (null,"El nombre del combo a pasado de "+nombre+
                                           " a "+com.getNombreCombo() );
                                           }
                                           }









                                           }

    public void cambiarPrecioComboModificando(){
                                           String nombre="";

                                           double precio=0;
                                           boolean formatoCorrecto=true;

                                           for(Combos com:DiarioFacil.getListaCombos()){
                                           if(com.getCodCombo()==comboModificando){


                                           nombre=com.getNombreCombo();

                                           do{
                                           try{
                                           precio=Double.parseDouble(JOptionPane.showInputDialog
                                           (null,"Ingrese el nuevo precio del combo "+nombre));
                                           }catch(NumberFormatException nfe){
                                           formatoCorrecto=false;
                                           JOptionPane.showMessageDialog
                                           (null,"El formato del precio es incorrecto,"+
                                           "recuerde usar solo números positivos");

                                           if(precio<1){
                                           JOptionPane.showMessageDialog
                                           (null,"El formato del precio es incorrecto,"+
                                           "recuerde usar solo números positivos");
                                           }
                                           }
                                           }while(formatoCorrecto==false || precio<1);


                                           com.setPrecio(precio);


                                           JOptionPane.showMessageDialog
                                           (null,"El precio del combo "+nombre+
                                           " ahora es "+com.getPrecio() );
                                           }
                                           }

                                           }

    public void activarDesactivarCombo(){
                                    int opcion=0;
                                    boolean inputInvalido=false;

                                    do{
                                    inputInvalido=false;

                                    try{
                                    opcion=Integer.parseInt(JOptionPane.showInputDialog(null,
                                    "1.Activar Combo \n"+
                                    "2.Desactivar combo \n"+
                                    "3. Volver \n"));
                                    }catch(NumberFormatException nfe){
                                    inputInvalido=true;
                                    JOptionPane.showMessageDialog(null,"la opción ingresada no tiene el formato correcto"
                                    + "recuerde usar solo números enteros ");
                                    }


                                    }while(opcion>3 || opcion<1 || inputInvalido==true);


                                    switch(opcion){
                                    case 1:

                                    activarCombo();
                                    break;

                                    case 2:

                                    desactivarCombo();
                                    break;

                                    case 3:
                                    break;


                                    }
                                    }

    public void activarCombo(){
                          for(Combos c:DiarioFacil.getListaCombos()){
                          if(c.getCodCombo()==comboModificando ){
                          if(c.isActivo()){
                          JOptionPane.showMessageDialog(null,"El combo ya estaba activado");

                          }else{
                          c.setActivo(true);
                          JOptionPane.showMessageDialog(null, "El combo "+c.getNombreCombo()+
                          " ha sido activado");
                          }
                          }
                          }
                          }

    public void desactivarCombo(){
                             for(Combos c:DiarioFacil.getListaCombos()){
                             if(c.getCodCombo()==comboModificando ){
                             if(c.isActivo()){
                             c.setActivo(false);
                             JOptionPane.showMessageDialog(null, "El combo "+c.getNombreCombo()+
                             " ha sido desactivado");

                             }else{
                             JOptionPane.showMessageDialog(null,"El combo ya estaba desactivado");


                             }
                             }
                             }
                             }

    public void borrarCombo(){
                         int codigo=0;
                         boolean formatoCorrecto=true;
                         boolean comboExiste=false;
                         do{
                         do{
                         formatoCorrecto=true;
                         try{
                         codigo=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese código del combo a borrar"));
                         }catch(NumberFormatException nfe){
                         formatoCorrecto=false;
                         JOptionPane.showMessageDialog
                         (null,"El formato del código no es el correcto,"+
                         "recuerde usar solo números enteros");
                         }


                         }while(formatoCorrecto==false );


                         for(Iterator<Combos> comb=DiarioFacil.getListaCombos().iterator();comb.hasNext();){
                         Combos com=comb.next();
                         if(com.getCodCombo()==codigo){
                         comboExiste=true;
                         JOptionPane.showMessageDialog
                         (null,"El combo "+com.getNombreCombo()+" ha sido removido del"
                         + " sistema ");
                         comb.remove();

                         }

                         }

                         if(comboExiste==false){
                         JOptionPane.showMessageDialog(null,"El combo ingresado no existe");
                         }

                         }while(comboExiste==false);
                         }


    /*_____                _     _
     / ____|              | |   (_)
    | |     __ _ _ __ ___ | |__  _  ___  ___
    | |    / _` | '_ ` _ \| '_ \| |/ _ \/ __|
    | |___| (_| | | | | | | |_) | | (_) \__ \
     \_____\__,_|_| |_| |_|_.__/|_|\___/|___/*/

    /** FORMATEA TODAS LAS PROMOCIONES ACTIVAS EN EL SISTEMA PARA PODER IMPRIMIRLAS LUEGO
     * @return StringBuffer CON TODAS LAS PROMOCIONES ACTIVAS
     */
    private StringBuffer getPromociones(){
        StringBuffer promociones= new StringBuffer();
        int i = 0;
        for(Promocion promocion: DiarioFacil.getListaPromociones()){
            promociones.append(promocion.toString());
            i++;
        }
        return promociones;
    }

    private void verPromociones(){
        StringBuffer promociones = getPromociones();
        mostrar(promociones);
    }

    private StringBuffer getPromocionesActivas(){
        StringBuffer promocionesActivas= new StringBuffer();
        int i =0;
        for(Promocion promocion: DiarioFacil.getListaPromociones()){
            if(promocion.isActivo()) {
                promocionesActivas.append(i+") "+promocion.toString());
            }
            i++;
        }
        return promocionesActivas;
    }

    private void verPromocionesActivas(){
        StringBuffer promocionesActivas = getPromocionesActivas();
        mostrar(promocionesActivas);
    }


    private StringBuffer getPromocionesInactivas(){
        StringBuffer promocionesInactivas= new StringBuffer();
        int i = 0;
        for(Promocion promocion: DiarioFacil.getListaPromociones()){
            if(!promocion.isActivo()) {
                promocionesInactivas.append(i +") " + promocion.toString());
            }
            i++;
        }
        return promocionesInactivas;
    }

    private void verPromocionesInactivas(){
        StringBuffer promocionesInactivas = getPromocionesInactivas();
        mostrar(promocionesInactivas);
    }


    private StringBuffer formatearOpciones(String[] opciones){
        StringBuffer formateado = new StringBuffer();
        int i = 0;
        for(String opcion: opciones){
            formateado.append(i + ") " + opcion + "\n");
            i++;
        }
        return formateado;
    }

    private void verMenuParaModificarPromocion(){
        String[] opciones = {"Eliminar Promocion","Activar/Desactivar Promocion","Cambiar Descuento De Promocion"};
        int input = -1;
        while(input<0 && input>2) {
            input = inputInt(formatearOpciones(opciones));
        }
        switch (input){
            case 0:
                eliminarPromocion();
                break;
            case 1:
                activarDesactivar();
                break;
            case 2:
                cambiarDescuento();
                break;
        }
    }

    private void eliminarPromocion(){
        boolean repetir = true;
        while(repetir) {
            StringBuffer insturcciones = new StringBuffer();
            insturcciones.append("Seleccione el numero de la Promocion que desea eliminar:\n\n");
            insturcciones.append(getPromociones());
            int seleccion = -1;
            while (seleccion < 0 && seleccion >= DiarioFacil.getListaPromociones().size()) {
                seleccion = inputInt(insturcciones);
            }
            DiarioFacil.eliminarPromocion(seleccion);
            repetir = repetir("Seguir Eliminando Promociones");
        }
    }

    private void activarDesactivar(){
        boolean repetir = true;
        while(repetir) {
            StringBuffer promociones = seleccionarPromociones();
            StringBuffer instrucciones = new StringBuffer();

            instrucciones.append("Seleccione el numero de la Promocion:\n\n");
            instrucciones.append(promociones);

            int posicion = -1;

            while (posicion < 0 || posicion >= DiarioFacil.listaPromociones.size()) {
                posicion = inputInt(instrucciones);
            }

            DiarioFacil.cambiarActivoInactivo(posicion);
            repetir = repetir("Seguir Activando/Desactivando Promociones");
        }
    }

    private void cambiarDescuento(){
        boolean repetir = true;
        while(repetir) {
            StringBuffer promociones = seleccionarPromociones();
            StringBuffer instrucciones = new StringBuffer();

            instrucciones.append("Seleccione el numero de la Promocion:\n\n");
            instrucciones.append(promociones);

            int posicion = -1;

            while (posicion < 0 || posicion >= DiarioFacil.listaPromociones.size()) {
                posicion = inputInt(instrucciones);
            }

            int descuento = validarDescuento();

            DiarioFacil.ajustarDescuento(posicion, descuento);

            repetir = repetir("Seguir Cambiando Descuentos");
        }
    }

    private boolean repetir(String mensaje){
        String[] opciones = {mensaje,"Salir Al Menu"};
        int i = validarSwitchCase(opciones);
        return (i == 0);//0 = Repetir
    }

    private int validarDescuento(){
        int descuento = -1;
        while(descuento<0 || descuento >99){
            if(descuento==100){
                mostrar("Regalar productos es malo para el negocio!!");
            }
            descuento=inputInt("Ingrese un porcentaje (0-99) para el nuevo descuento");
        }
        return descuento;
    }

    private StringBuffer seleccionarPromociones(){
        StringBuffer promociones = new StringBuffer();
        String[] opciones = {"Ver Todas las Promociones","Ver Pronociones Activas", "Ver Promociones Inactivas"};
        int seleccion = validarSwitchCase(opciones);

        switch(seleccion){
            case 0:
                promociones = getPromociones();
                break;
            case 1:
                promociones = getPromocionesActivas();
                break;
            case 2:
                promociones = getPromocionesInactivas();
                break;
        }

        return promociones;
    }


    private int validarSwitchCase(String[] opciones){
        int i = -1;
        while(i<0 || i >= opciones.length){
            i = inputInt(formatearOpciones(opciones));
        }
        return i;
    }

    private void seleccionarPromocion(){

    }

    private void mostrar(StringBuffer stringBuffer){
        JOptionPane.showMessageDialog(null,stringBuffer);
    }

    private void mostrar(String string){
        JOptionPane.showMessageDialog(null,string);
    }

    /**
     * Muestra todas las facturas registradas en el sistema
     */
    @Override
    public void verHistorial() {
        StringBuffer historial = generarHistorial();
        mostrar(historial);
    }

    /**
     *
     * @param cedulaUsuario
     */
    @Override
    public void verHistorialUsuario(int cedulaUsuario) {
        StringBuffer historial = generarHistorialUsuario(cedulaUsuario);
        mostrar(historial);
    }

    /**
     *
     * @param fecha
     */
    @Override
    public void verHistorialFecha(Calendar fecha) {
        StringBuffer historial = generearHistorialFecha(fecha);
        mostrar(historial);
    }

    /**
     *
     * @param fechaInicio
     * @param fechaFin
     */
    @Override
    public void verHistorialFecha(Calendar fechaInicio, Calendar fechaFin) {
        StringBuffer historial = generearHistorialRango(fechaInicio,fechaFin);
        JOptionPane.showMessageDialog(null,historial);
    }

    /*
                    _            _
                   | |          | |
     _ __ ___   ___| |_ ___   __| | ___  ___
    | '_ ` _ \ / _ \ __/ _ \ / _` |/ _ \/ __|
    | | | | | |  __/ || (_) | (_| | (_) \__ \
    |_| |_| |_|\___|\__\___/ \__,_|\___/|___/
                           _             _
                          | |           | |
      __ _ _   _ _   _  __| | __ _ _ __ | |_ ___  ___
     / _` | | | | | | |/ _` |/ _` | '_ \| __/ _ \/ __|
    | (_| | |_| | |_| | (_| | (_| | | | | ||  __/\__ \
     \__,_|\__, |\__,_|\__,_|\__,_|_| |_|\__\___||___/
            __/ |
           |___/ */


    /**
     * TOMA UNA LISTA DE FACTURAS, ITERA CADA ELEMENTO Y LO FORMATEA
     * EXTRALLENDO LA INFROMACION SIGUIENTE: (Nr de Factura, Cliente, Fecha)
     * @param listaFacturas LA LISTA DE FACTURAS QUE SERA FORMATEADA
     * @return STRING BUFFER CON LOS DETALLES DEL HISTORIAL
     */
    private StringBuffer generarHistorial(List<Factura> listaFacturas){
        StringBuffer historial = new StringBuffer();
        historial.append("Historial\n");
        for(Factura factura: listaFacturas){
            historial.append(factura.toString()+"\n");
        }
        return historial;
    }

    /** GENERA UN StringBuffer CON LA INFORMACION DE TODAS LAS FACTURAS EN EL SISTEMA
     * LLAMA AL METODO DEL MISMO NOMBRE, CON EL PARAMETRO: "DiarioFacil.getListaFacturas()"
     * @return TODAS EL HISTORIAL DE FACTURAS FORMATEADO
     */
    private StringBuffer generarHistorial(){
        return generarHistorial(DiarioFacil.getListaFacturas());
    }

    /**
     * GENERA  TODO EL HISTORIAL DE UN CLIENTE FORMATEADO
     * @param cedulaUsuario ID DEL CLEINTE BUSCADO
     * @return StringBuffer TODO EL HISTORIAL
     */
    private StringBuffer generarHistorialUsuario(int cedulaUsuario){
        Usuario usuario = encontrarUsuario(cedulaUsuario);
        Cliente cliente = (Cliente) usuario;
        return generarHistorial(cliente.getHistorialDeFacturas());
    }

    /**
     *  GENERA TODO EL HISTORIAL DE COMPRAS REALIZADAS EN UNA FECHA ESPECIFICA
     * @param fecha FECHA QUE APARECE EN LAS FACTURAS
     * @return TEXTO FORMATEADO
     */
    private StringBuffer generearHistorialFecha(Calendar fecha){
        return generarHistorial(filtrarPorFecha(fecha));
    }

    /**
     *  GENERA TODO EL HISTORIAL DE COMPRAS REALIZADAS EN UN RANGO DE FECHAS ESPECIFICA
     * @param fechaInicio INICIO DEL RANGO
     * @param fechaFin FIN DEL RANGO
     * @return TEXTO FORMATEADO
     */
    private StringBuffer generearHistorialRango(Calendar fechaInicio, Calendar fechaFin){
        return generarHistorial(filtrarPorFecha(fechaInicio,fechaFin));
    }

    /**
     * GENERA UNA COPIA DE FACTURAS CON LA MISMA FECHA ESPECIFICADA
     * @param fecha FECHA QUE SE REALIZARON LAS FACTURAS
     * @return Lista DE FACTURAS CON FECHA = fecha
     */
    private List<Factura> filtrarPorFecha(Calendar fecha){
        List<Factura> listaFiltrada = new ArrayList<>();
        for(Factura factura: DiarioFacil.getListaFacturas()){
            if(factura.getCalendar().equals(fecha)){
                listaFiltrada.add(factura);
            }
        }
        return listaFiltrada;
    }

    /**
     * ENCUENTRA TODAS LAS FACTURAS REALIZADAS DENTRO DE UN RANGO DE FECHAS DE INICO Y FIN
     * @param fechaInicio INICIO DEL RANGO
     * @param fechaFin FIN DEL RANGO
     * @return LISTA DE TODAS LAS FACTURAS DENTRO DEL RANGO DE FECHAS
     */
    private List<Factura> filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin){
        List<Factura> listaFiltrada = new ArrayList<>();
        Calendar fecha;

        for(Factura factura : DiarioFacil.getListaFacturas()){
            fecha = factura.getCalendar();
            if (enRangoDeFechas(fecha,fechaInicio,fechaFin)){
                listaFiltrada.add(factura);
            }
        }
        return listaFiltrada;
    }

    /**
     * RETORNA SI UNA FECHA ESTA DENTRO DE UN RANGO
     * @param fecha FECHA BUSCADA
     * @param fechaInicio FECHA DE INICIO DEL RANGO
     * @param fechaFin FECHA DE FIN DEL RANGO
     * @return RESULTADO (Booleano)
     */
    private boolean enRangoDeFechas(Calendar fecha,Calendar fechaInicio, Calendar fechaFin){
        return (fecha.after(fechaInicio) && fecha.before(fechaFin));
    }

    /**
     * BUSCA EN LA LISTA DE DIARIOFACIL POR UN USUARIO QUE TENGA EL NUMERO DE CEDULA ESPECIFICA
     * @param cedulaUsuario cedula buscada
     * @return UN OBJECTO TIPO USUARIO DEL LISTADO EN DIARIOFACIL
     */
    private Usuario encontrarUsuario(int cedulaUsuario){
        int posicion = 0;
        int cedulaActual = 0;
        boolean encontrado = false;
        List<Usuario> usuarios = DiarioFacil.getLstUsuarios();
        while(!encontrado){
            for (int i = 0; i < usuarios.size(); i++) {
                cedulaActual = usuarios.get(i).getCedula();
                if (cedulaActual == cedulaUsuario) {
                    posicion = i;
                    encontrado = true;
                    break;
                }
            }
            if(!encontrado){
                cedulaUsuario = inputInt("El Usuario con cedula "+cedulaUsuario+" no fue encontrado" +
                        "por favor ingrese un nuevo numero de cedula");
            }
        }
        return usuarios.get(posicion);
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
            JOptionPane.showMessageDialog(null,"Por Favor Ingrese un numero Entero");
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
            JOptionPane.showMessageDialog(null,"Por Favor Ingrese un numero Entero");
            return inputInt(mensaje);
        }

        return input;
    }





}
