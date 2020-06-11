/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Date;
import modelo.*;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Usuario
 */
public class ControladorGraficos {
    
    public boolean generarGraficos(modelo.Grafico unGrafico){
            return true ;  // el estudiante se ha agregado con éxito
        }
    
    //ArrayList<Sismo> listaS = document;
    
    
    ArrayList<Sismo> arrayFilas = new ArrayList();
    ArrayList<Sismo> listaS;
    
    public static ArrayList<Sismo> listaSismos(){
        
        ArrayList<Sismo> listadeSismos = new ArrayList<Sismo>();
        
        boolean bandera = false;
        
        int r= (int) (Math.random()* ((20 - 0) + 1) + 0);
        int m = (int) (Math.random()* ((12 - 0) + 1) + 0);
        Date fecha = new Date(100+r, m, 1+r+1, r+4, r+40, r+20+1);
        Sismo sismoUno = new Sismo(fecha,22.4, modelo.TFalla.Tectónico_Subducción, "Playa Hermosa de Garabito", 2.2, 9.22, -84.85, "Playa Hermosa de Garabito", modelo.TLugar.Terrestre, modelo.TProvincia.Puntarenas);
        int r2= (int) (Math.random()* ((20 - 0) + 1) + 0);
        int m2 = (int) (Math.random()* ((12 - 0) + 1) + 0);
        Date fecha2 = new Date(100+r2, m2, 1+r2+1, r2+4, r2+40, r2+20+1);
        Sismo sismoDos = new Sismo(fecha2,8, modelo.TFalla.Tectónico_Subducción, "Corredores de Puntarenas, Puerto Armuelles, Golfito, Puerto Jimenez, El Valle Central: Alajuela, Heredia, Escazú", 4, 8.289, -82.8115, "Playa Hermosa de Garabito", modelo.TLugar.Terrestre, modelo.TProvincia.Puntarenas);
        int r3= (int) (Math.random()* ((20 - 0) + 1) + 0);
        int m3 = (int) (Math.random()* ((12 - 0) + 1) + 0);
        Date fecha3 = new Date(100+r3, m3, 1+r3+1, r3+4, r3+40, r3+20+1);
        Sismo sismoTres = new Sismo(fecha3,18, modelo.TFalla.Deformación_Interna, "Golfito, Puntarenas", 5.34, 8.6743, -82.8115, "Playa Hermosa de Garabito", modelo.TLugar.Terrestre, modelo.TProvincia.Puntarenas);
        int r4= (int) (Math.random()* ((20 - 0) + 1) + 0);
        int m4 = (int) (Math.random()* ((12 - 0) + 1) + 0);
        Date fecha4 = new Date(100+r4, m4, 1+r4+1, r+4, r4+40, r4+20+1);
        Sismo sismoCuatro = new Sismo(fecha4,40, modelo.TFalla.Tectónico_Subducción, "Zona sur, Valle Central, Limón", 3.24, 8.6743, -83.2457, "Playa Hermosa de Garabito", modelo.TLugar.Terrestre, modelo.TProvincia.Limon);
        int r5= (int) (Math.random()* ((20 - 0) + 1) + 0);
        int m5 = (int) (Math.random()* ((12 - 0) + 1) + 0);
        Date fecha5 = new Date(100+r5, m5, 1+r5+1, r5+4, r5+40, r5+20+1);
        Sismo sismoCinco = new Sismo(fecha5,59, modelo.TFalla.Deformación_Interna, "Las Juntas de Abangares, Nicoya, Santa Cruz, Nandayure, Cañas, Paquera, Miramar, Herradura Miramar, San Ramón, Palmares, Naranjo, Atenas, San Mateo, Turrucares.", 5.9, 10.2062, -85.0046, "Playa Hermosa de Garabito", modelo.TLugar.Terrestre, modelo.TProvincia.Guanacaste);
        int r6= (int) (Math.random()* ((20 - 0) + 1) + 0);
        int m6 = (int) (Math.random()* ((12 - 0) + 1) + 0);
        Date fecha6 = new Date(100+r6, m6, 1+r6+1, r6+4, r6+40, r6+20+1);
        Sismo sismoSeis = new Sismo(fecha6,11, modelo.TFalla.Fallamiento_Local, "El Carmen y Tres Ríos, Cartago", 4.2, 9.8232, -83.8497, "Playa Hermosa de Garabito", modelo.TLugar.Terrestre, modelo.TProvincia.Cartago);
        int r7= (int) (Math.random()* ((20 - 0) + 1) + 0);
        int m7 = (int) (Math.random()* ((12 - 0) + 1) + 0);
        Date fecha7 = new Date(2018, m7, 1+r7+1, r7+4, r7+40, r7+20+1);
        Sismo sismoSiete = new Sismo(fecha7,29, modelo.TFalla.Choque_Placas, "Fuerte en Punta Banco de Golfito, Puntarenas", 1.6, 8.3242, -83.1066, "Playa Hermosa de Garabito", modelo.TLugar.Marítimo, modelo.TProvincia.No_Aplica);

      
        //System.out.println(sismoUno);
        listadeSismos.add(sismoUno);
        listadeSismos.add(sismoDos);
        listadeSismos.add(sismoTres);
        listadeSismos.add(sismoCuatro);
        listadeSismos.add(sismoCinco);
        listadeSismos.add(sismoSeis);
        listadeSismos.add(sismoSiete);
        
        //System.out.println(listadeSismos.get(0));
        
        
        return listadeSismos;
        
    }
        
//    public static int graficoProvincia(){
//        int contadorNa = 0;
//        int contadorSj = 0;
//        int contadorAl = 0;
//        int contadorCa = 0;
//        int contadorPu = 0;
//        int contadorGu = 0;
//        int contadorLi = 0;
//        
//        for(int i = 0; i < listaSismos().size(); i++){
//            if(listaSismos().get(i).getProvincia() == modelo.TProvincia.No_Aplica){
//                contadorNa += 1;
//            } else if(listaSismos().get(i).getProvincia() == modelo.TProvincia.San_Jose) {
//                contadorSj += 1;
//            } else if(listaSismos().get(i).getProvincia() == modelo.TProvincia.Alajuela) {
//                contadorAl += 1;
//            } else if(listaSismos().get(i).getProvincia() == modelo.TProvincia.Cartago) {
//                contadorCa += 1;
//            } else if(listaSismos().get(i).getProvincia() == modelo.TProvincia.Heredia) {
//                contadorPu += 1;
//            } else if(listaSismos().get(i).getProvincia() == modelo.TProvincia.Guanacaste) {
//                contadorGu += 1;
//            } else {
//                contadorLi += 1;
//            }
//        }
//        
//        return contadorNa; contadorSj; contadorAl; contadorCa; contadorPu; contadorGu; contadorLi;
//    }
    
    public static int graficoProvincia(ArrayList<Sismo> lista, modelo.TProvincia provincia){
        int contador = 0;

        for(int i = 0; i < listaSismos().size(); i++){
            if(lista.get(i).getProvincia() == provincia){
                contador += 1;
            } else {
                contador += 0;
            }
        }
        return contador;
    }
    
    public static int graficoTOrigen(ArrayList<Sismo> lista, modelo.TFalla origen){
        int contador = 0;

        for(int i = 0; i < listaSismos().size(); i++){
            if(lista.get(i).getOrigenFalla() == origen){
                contador += 1;
            } else {
                contador += 0;
            }
        }
        return contador;
    }
    
     public static ArrayList listaSismoporAnno(ArrayList<Sismo> lista, int anno){
        ArrayList<Sismo> listaNueva = new ArrayList();
        
        for(int i = 0; i < lista.size(); i++){
            
            if (lista.get(i).getMomentoExacto().getYear() == anno){
                listaNueva.add(lista.get(i));
            } 
        }
       
        return listaNueva;
    }
    
    public static int graficoSismoPorMes(ArrayList<Sismo> lista, int mes){
        int contador = 0;
        
        for(int i = 0; i < lista.size(); i++){
            
            if (lista.get(i).getMomentoExacto().getMonth() == mes){
                contador += 1;
            } else {
                contador += 0;
            }
        }
        return contador;
    }
    
    public static int graficoMagnitud(ArrayList<Sismo> lista, String descripcion){
        int contador = 0;
        
        for(int i = 0; i < lista.size(); i++){
            double mag = lista.get(i).getMagnitud();
            if (mag < 2.0){
                if (descripcion.equals("Micro")){
                    contador += 1;
                } 
            } else if (mag >= 2.0 && mag < 2.9){
                if (descripcion.equals("Menor1")){
                    contador += 1;
                } 
            } else if (mag >= 3.0 && mag < 3.9){
                if (descripcion.equals("Menor2")){
                    contador += 1;
                } 
            } else if (mag >= 4.0 && mag < 4.9){
                if (descripcion.equals("Ligero")){
                    contador += 1;
                } 
            } else if (mag >= 5.0 && mag < 5.9){
                if (descripcion.equals("Moderado")){
                    contador += 1;
                } 
            } else if (mag >= 6.0 && mag < 6.9){
                if (descripcion.equals("Fuerte")){
                    contador += 1;
                } 
            } else if (mag >= 7.0 && mag < 7.9){
                if (descripcion.equals("Mayor")){
                    contador += 1;
                } 
            } else if (mag >= 8.0 && mag < 8.9){
                if (descripcion.equals("Gran1")){
                    contador += 1;
                } 
            } else if (mag >= 9.0 && mag < 9.9){
                if (descripcion.equals("Gran2")){
                    contador += 1;
                } 
            } else {
                if (descripcion.equals("Epico")){
                    contador += 1;
                }        
            }
        }
        return contador;
    }
    
     public static int rangoFecha(ArrayList<Sismo> lista, Date fecha, Date fecha2) {
         
         int contador = 0;
         
         for(int i = 0; i < lista.size(); i++){
             
             if (fecha.before(fecha2)){
                 if (lista.get(i).getMomentoExacto().after(fecha) && lista.get(i).getMomentoExacto().before(fecha2)){
                     contador += 1;
                 }
             } else {
                 if (lista.get(i).getMomentoExacto().after(fecha2) && lista.get(i).getMomentoExacto().before(fecha)){
                     contador += 1;
                 }
             }
             
         }
         return contador;
    }
    
     //VALOR MAYOR PARA GRAFICA DE BARRAS
    public static int MayorValor(int int_Na, int int_Sj, int int_Al, int int_Ca, int int_He, int int_Pu, int int_Gu, int int_Li){
        if(int_Na > int_Sj && int_Na > int_Al && int_Na > int_Ca && int_Na > int_He && int_Na > int_Pu && int_Na > int_Gu && int_Na > int_Li){
            return int_Na;
        } else if(int_Sj > int_Na && int_Sj > int_Al && int_Sj > int_Ca && int_Sj > int_He && int_Sj > int_Pu && int_Sj > int_Gu && int_Sj > int_Li){
            return int_Sj;
        }else if(int_Al > int_Na && int_Al > int_Sj && int_Al > int_Ca && int_Al > int_He && int_Al > int_Pu && int_Al > int_Gu && int_Al > int_Li){
            return int_Al;
        }else if(int_Ca > int_Na && int_Ca > int_Sj && int_Ca > int_Al && int_Ca > int_He && int_Ca > int_Pu && int_Ca > int_Gu && int_Ca > int_Li){
            return int_Ca;
        }else if(int_He > int_Na && int_He > int_Sj && int_He > int_Al && int_He > int_Ca && int_He > int_Pu && int_He > int_Gu && int_He > int_Li){
            return int_He;
        }else if(int_Pu > int_Na && int_Pu > int_Sj && int_Pu > int_Al && int_Pu > int_Ca && int_Pu > int_He && int_Pu > int_Gu && int_Pu > int_Li){
            return int_Pu;
        }else if(int_Gu > int_Na && int_Gu > int_Sj && int_Gu > int_Al && int_Gu > int_Ca && int_Gu > int_He && int_Gu > int_Pu && int_Gu > int_Li){
            return int_Gu;
        }else{
        return int_Li;
                }
    }
    
    
     //VALOR MAYOR PARA GRAFICA DE BARRAS
//    public static int MenorValor(int int_Na, int int_Sj, int int_Al, int int_Ca, int int_He, int int_Pu, int int_Gu, int int_Li){
//        if(int_Na < int_Sj && int_Na < int_Al && int_Na < int_Ca && int_Na < int_He && int_Na < int_Pu && int_Na < int_Gu && int_Na < int_Li){
//            return int_Na;
//        } else if(int_Sj < int_Na && int_Sj < int_Al && int_Sj < int_Ca && int_Sj < int_He && int_Sj < int_Pu && int_Sj < int_Gu && int_Sj < int_Li){
//            return int_Sj;
//        }else if(int_Al < int_Na && int_Al < int_Sj && int_Al < int_Ca && int_Al < int_He && int_Al < int_Pu && int_Al < int_Gu && int_Al < int_Li){
//            return int_Al;
//        }else if(int_Ca < int_Na && int_Ca < int_Sj && int_Ca < int_Al && int_Ca < int_He && int_Ca < int_Pu && int_Ca < int_Gu && int_Ca < int_Li){
//            return int_Ca;
//        }else if(int_He < int_Na && int_He < int_Sj && int_He < int_Al && int_He < int_Ca && int_He < int_Pu && int_He < int_Gu && int_He < int_Li){
//            return int_He;
//        }else if(int_Pu < int_Na && int_Pu < int_Sj && int_Pu < int_Al && int_Pu < int_Ca && int_Pu < int_He && int_Pu < int_Gu && int_Pu < int_Li){
//            return int_Pu;
//        }else if(int_Gu < int_Na && int_Gu < int_Sj && int_Gu < int_Al && int_Gu < int_Ca && int_Gu < int_He && int_Gu < int_Pu && int_Gu < int_Li){
//            return int_Gu;
//        }else{
//        return int_Li;
//                }
//    }
    
    public static int MayorMes(int int_enero, int int_febrero, int int_marzo, int int_abril, int int_mayo,int int_junio, int int_julio, int int_agosto,int int_setiembre, int int_octubre,int int_noviembre,int int_diciembre){
        if(int_enero > int_febrero && int_enero > int_marzo && int_enero > int_abril && int_enero > int_mayo && int_enero > int_junio && int_enero > int_julio && int_enero > int_agosto && int_enero > int_setiembre && int_enero > int_octubre && int_enero > int_noviembre && int_enero > int_diciembre){
            return int_enero;
        } else if(int_febrero > int_marzo && int_febrero > int_abril && int_febrero > int_mayo && int_febrero > int_junio && int_febrero > int_julio && int_febrero > int_agosto && int_febrero > int_setiembre && int_febrero > int_octubre && int_febrero > int_noviembre && int_febrero > int_diciembre ){
            return int_febrero;
        }else if(int_marzo > int_abril && int_marzo > int_mayo && int_marzo > int_junio && int_marzo > int_julio && int_marzo > int_agosto && int_marzo > int_setiembre && int_marzo > int_octubre && int_marzo > int_noviembre && int_marzo >  int_diciembre){
            return int_marzo;
        }else if(int_abril > int_mayo && int_abril > int_junio && int_abril > int_julio && int_abril > int_agosto && int_abril > int_setiembre && int_abril > int_octubre && int_abril > int_noviembre && int_abril > int_diciembre){
            return int_abril;
        }else if(int_mayo > int_junio && int_mayo > int_julio && int_mayo > int_agosto && int_mayo > int_setiembre && int_mayo > int_octubre && int_mayo > int_noviembre && int_mayo > int_diciembre){
            return int_mayo;
        }else if(int_junio > int_julio && int_junio > int_agosto && int_junio > int_setiembre && int_junio > int_octubre && int_junio > int_noviembre && int_junio > int_diciembre){
            return int_junio;
        }else if(int_julio > int_agosto && int_julio > int_setiembre && int_julio > int_octubre && int_julio > int_noviembre && int_julio > int_diciembre){
            return int_julio;
        }else if(int_agosto > int_setiembre && int_agosto > int_octubre && int_agosto > int_noviembre && int_agosto > int_diciembre){
            return int_agosto;
        }else if(int_setiembre > int_octubre && int_setiembre > int_noviembre && int_setiembre > int_diciembre){
            return int_setiembre;
        }else if(int_octubre > int_noviembre && int_octubre > int_diciembre){
            return int_octubre;
        }else if(int_noviembre > int_diciembre){
            return int_noviembre;
        }else {
        return int_diciembre;
                }
    }
    
    public static int MenorMes(int int_enero, int int_febrero, int int_marzo, int int_abril, int int_mayo,int int_junio, int int_julio, int int_agosto,int int_setiembre, int int_octubre,int int_noviembre,int int_diciembre){
        if(int_enero < int_febrero && int_enero < int_marzo && int_enero < int_abril && int_enero < int_mayo && int_enero < int_junio && int_enero < int_julio && int_enero < int_agosto && int_enero < int_setiembre && int_enero < int_octubre && int_enero < int_noviembre && int_enero < int_diciembre){
            return int_enero;
        } else if(int_febrero < int_marzo && int_febrero < int_abril && int_febrero < int_mayo && int_febrero < int_junio && int_febrero < int_julio && int_febrero < int_agosto && int_febrero < int_setiembre && int_febrero < int_octubre && int_febrero < int_noviembre && int_febrero < int_diciembre ){
            return int_febrero;
        }else if(int_marzo < int_abril && int_marzo < int_mayo && int_marzo < int_junio && int_marzo < int_julio && int_marzo < int_agosto && int_marzo < int_setiembre && int_marzo < int_octubre && int_marzo < int_noviembre && int_marzo < int_diciembre){
            return int_marzo;
        }else if(int_abril < int_mayo && int_abril < int_junio && int_abril < int_julio && int_abril < int_agosto && int_abril < int_setiembre && int_abril < int_octubre && int_abril < int_noviembre && int_abril < int_diciembre){
            return int_abril;
        }else if(int_mayo < int_junio && int_mayo < int_julio && int_mayo < int_agosto && int_mayo < int_setiembre && int_mayo < int_octubre && int_mayo < int_noviembre && int_mayo < int_diciembre){
            return int_mayo;
        }else if(int_junio < int_julio && int_junio < int_agosto && int_junio < int_setiembre && int_junio < int_octubre && int_junio < int_noviembre && int_junio < int_diciembre){
            return int_junio;
        }else if(int_julio < int_agosto && int_julio < int_setiembre && int_julio < int_octubre && int_julio < int_noviembre && int_julio < int_diciembre){
            return int_julio;
        }else if(int_agosto < int_setiembre && int_agosto < int_octubre && int_agosto < int_noviembre && int_agosto <int_diciembre){
            return int_agosto;
        }else if(int_setiembre < int_octubre && int_setiembre < int_noviembre && int_setiembre < int_diciembre){
            return int_setiembre;
        }else if(int_octubre < int_noviembre && int_octubre < int_diciembre){
            return int_octubre;
        }else if(int_noviembre < int_diciembre){
            return int_noviembre;
        }else {
        return int_diciembre;
                }
    }
    
   
    public static void generarGraficoH(){
        modelo.Grafico.paintHistograma();
    }
    
    public static void generarGraficoPastel(){
        modelo.Grafico.graficoPastel();
    }
    
    public static void generarGraficoBarras(Date fecha){
        modelo.Grafico.graficoBarras(fecha);
    }
    
    public static void generarGraficoTabMagnitud(){
        modelo.Grafico.graficoTabularMagnitud();
    }
    
    public static void generarGraficoTabFecha(Date fecha, Date fecha2){
        modelo.Grafico.graficoTabFecha(fecha, fecha2);
    }
    

 
}
