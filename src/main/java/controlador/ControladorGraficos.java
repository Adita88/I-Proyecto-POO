/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Date;
import modelo.*;

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
    
    /**
     * Método indica cuantos sismos hay por provincia
     * @param lista
     * @param provincia
     * @return 
     */
    public static int graficoProvincia(ArrayList<Sismo> lista, modelo.TProvincia provincia){
        int contador = 0;

        for(int i = 0; i < ControladorArchivo.listaSismos().size(); i++){
            if(lista.get(i).getProvincia() == provincia){
                contador += 1;
            } else {
                contador += 0;
            }
        }
        return contador;
    }
    
    /**
     * Método indica cuantos sismos hay por Origen
     * @param lista
     * @param origen
     * @return 
     */
    public static int graficoTOrigen(ArrayList<Sismo> lista, modelo.TFalla origen){
        int contador = 0;

        for(int i = 0; i < ControladorArchivo.listaSismos().size(); i++){
            if(lista.get(i).getOrigenFalla() == origen){
                contador += 1;
            } else {
                contador += 0;
            }
        }
        return contador;
    }
    
    /**
     * Método indica cuantos sismos hay por año
     * @param lista
     * @param anno
     * @return 
     */
     public static ArrayList listaSismoporAnno(ArrayList<Sismo> lista, int anno){
        ArrayList<Sismo> listaNueva = new ArrayList();
        
        for(int i = 0; i < lista.size(); i++){
            
            if (lista.get(i).getMomentoExacto().getYear() == anno){
                listaNueva.add(lista.get(i));
            } 
        }
       
        return listaNueva;
    }
    
    /**
     * Método indica cuantos sismos hay por mes
     * @param lista
     * @param mes
     * @return 
     */
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
    
    /**
     * Método indica cuantos sismos hay por magnitud
     * @param lista
     * @param descripcion
     * @return 
     */
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
    
    
    /**
     * Método indica cuantos sismos hay en un rango de fechas
     * @param lista
     * @param fecha
     * @param fecha2
     * @return 
     */
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
    /**
     * Determina en cual provincia han habido más sismos
     * @param int_Na
     * @param int_Sj
     * @param int_Al
     * @param int_Ca
     * @param int_He
     * @param int_Pu
     * @param int_Gu
     * @param int_Li
     * @return 
     */
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
    
    
    /**
     * Determina en cual mes ha habido más sismos
     * @param int_enero
     * @param int_febrero
     * @param int_marzo
     * @param int_abril
     * @param int_mayo
     * @param int_junio
     * @param int_julio
     * @param int_agosto
     * @param int_setiembre
     * @param int_octubre
     * @param int_noviembre
     * @param int_diciembre
     * @return 
     */
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
    
    /**
     * Determina cual mes tuvo menos sismos
     * @param int_enero
     * @param int_febrero
     * @param int_marzo
     * @param int_abril
     * @param int_mayo
     * @param int_junio
     * @param int_julio
     * @param int_agosto
     * @param int_setiembre
     * @param int_octubre
     * @param int_noviembre
     * @param int_diciembre
     * @return 
     */
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

    /**
     * LLama de la clase Graficos al gráfico Histograma
     */
    public static void generarGraficoH(){
        modelo.Grafico.paintHistograma();
    }
    
    /**
     * LLama de la clase Graficos al gráfico Pastel
     */
    public static void generarGraficoPastel(){
        modelo.Grafico.graficoPastel();
    }
    
    /**
     * LLama de la clase Graficos al gráfico de Barras por fecha
     * @param fecha 
     */
    public static void generarGraficoBarras(Date fecha){
        modelo.Grafico.graficoBarras(fecha);
    }
    
    /**
     * LLama de la clase Graficos al gráfico Magnitud
     */
    public static void generarGraficoTabMagnitud(){
        modelo.Grafico.graficoTabularMagnitud();
    }
    
    
    /**
     * LLama de la clase Graficos al gráfico por rango de fechas
     * @param fecha
     * @param fecha2 
     */
    public static void generarGraficoTabFecha(Date fecha, Date fecha2){
        modelo.Grafico.graficoTabFecha(fecha, fecha2);
    }

 
}
