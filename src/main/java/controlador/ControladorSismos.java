/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador; 

import java.util.ArrayList;
import static java.util.Collections.reverse;
import java.util.Date;
import modelo.*;

/**
 *
 * @author Usuario
 */
public class ControladorSismos {
    
    
    private ArrayList<Sismo> sismos ;

    /**
     * Constructor básico
     */
    public ControladorSismos() {
        sismos= new ArrayList<>();
    }
    /**
     * Constructor sobrecargado
     * @param sismos 
     */
    public ControladorSismos(ArrayList<Sismo> sismos) {
        this.sismos = sismos;
    }   
    
    /**
     * Agrega sismo que Recibe
     * @param sismo
     * @return 
     */
    public boolean addSismo(Sismo sismo){
        sismos.add(sismo);
        return true;
    }
    
    
    /**
     * Elimina Sismo de la lista guardada
     * @param sismo
     * @return 
     */
    public boolean removeSismo(Sismo sismo){
        return false;
    }
    
    
    /**
     * Ordena sismos guardados por Fecha, puede ser en
     * orden ascendente (opcional false) o descendente (true)
     * @param ordenDescendente
     * @return 
     */
    public ArrayList <Sismo> getSismosOrdenadosFecha (boolean ... ordenDescendente){
        boolean flag = (ordenDescendente.length >= 1) ? ordenDescendente[0] : false;
        sismos.sort( (Sismo sis1, Sismo sis2) ->  sis1.getMomentoExacto().compareTo( sis2.getMomentoExacto() ) );
        if (flag) reverse(sismos);
        return sismos;
    }
    
    /**
     * Recibe lista de Sismos y la devuelve ordenada por Fecha, puede ser en
     * orden ascendente (opcional false) o descendente (true)
     * @param sismos1
     * @param ordenDescendente
     * @return 
     */
    public ArrayList <Sismo> getSismosOrdenadosFecha (ArrayList<Sismo> sismos1, boolean ... ordenDescendente) {
        boolean flag = (ordenDescendente.length >= 1) ? ordenDescendente[0] : false;
        ArrayList<Sismo> listaSismos= (ArrayList<Sismo>) sismos1.clone();
        listaSismos.sort( (Sismo sis1, Sismo sis2) -> 
                sis1.getMomentoExacto().compareTo( sis2.getMomentoExacto() ) );
        if (flag) reverse(listaSismos);
        return listaSismos;
    }
    
    /**
     * Ordena sismos guardados por Magitud, puede ser en
     * orden ascendente (opcional false) o descendente (true)
     * @param ordenDescendente
     * @return 
     */
    public ArrayList <Sismo> getSismosOrdenadosMagnitud(boolean ... ordenDescendente){
        boolean flag = (ordenDescendente.length >= 1) ? ordenDescendente[0] : false;
        sismos.sort( (Sismo sis1, Sismo sis2) ->  
                (sis1.getMagnitud()< sis2.getMagnitud()) ? -1 : ((sis1.getMagnitud() == sis2.getMagnitud()) ? 0 : 1)  );
        if (flag) reverse(sismos);
        return sismos;
    }
       
    /**
     * Recibe sismos y los devuelve ordenados por Magitud, puede ser en
     * orden ascendente (opcional false) o descendente (true)
     * 
     * @param sismos1
     * @param ordenDescendente Opcional
     * @return 
     */
    public ArrayList <Sismo> getSismosOrdenadosMagnitud(ArrayList<Sismo> sismos1, boolean ... ordenDescendente) {
        boolean flag = (ordenDescendente.length >= 1) ? ordenDescendente[0] : false;
        ArrayList<Sismo> listaSismos= (ArrayList<Sismo>) sismos1.clone();
        listaSismos.sort( (Sismo sis1, Sismo sis2) ->  
                (sis1.getMagnitud()< sis2.getMagnitud()) ? -1 : ((sis1.getMagnitud() == sis2.getMagnitud()) ? 0 : 1)  );
        if (flag) reverse(listaSismos);
        return listaSismos;
    }
    
    
    /**
     * Obtiene Sismos Iguales a Magnitud
     * @param magnitud
     * @return 
     */
    public ArrayList<Sismo> getSismosIgualAMagnitud(double magnitud){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getMagnitud()==magnitud)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    
    /**
     * Obtiene Simsos Menores A Magnitud
     * @param magnitud
     * @return 
     */
    public ArrayList<Sismo> getSismosMenorAMagnitud(double magnitud){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getMagnitud()<magnitud)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    
    /**
     * Obtiene Sismos mayores a Magnitud
     * @param magnitud
     * @return 
     */
    public ArrayList<Sismo> getSismosMayorAMagnitud(double magnitud){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getMagnitud()>magnitud)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    /**
     * Obtiene Sismos por Origen de Falla
     * @param origenFalla
     * @return 
     */
    public ArrayList<Sismo> getSismosOrigenFalla(TFalla origenFalla){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getOrigenFalla()==origenFalla)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    /**
     * Obtiene sismos por provincia
     * @param provincia
     * @return 
     */
    public ArrayList<Sismo> getSismosProvincia(TProvincia provincia){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getProvincia()==provincia)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    /**
     * Obtiene Sismos por lugar donde sucedio (maritimo, terrestre)
     * @param lugar
     * @return 
     */
    public ArrayList<Sismo> getSismosLugar(TLugar lugar){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getLugar()==lugar)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    /**
     * Obtiene sismos de una fecha en específico
     * @param fecha
     * @return 
     */
    public ArrayList<Sismo> getSismosOrigenFalla(Date fecha){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getMomentoExacto().equals(fecha))
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    /**
     * Obtiene sismos que se encuentren entre dos fechas dadas
     * @param FechaInicio
     * @param FechaFinal
     * @return 
     */
    public ArrayList <Sismo> getSismosEntreFechas(Date FechaInicio, Date FechaFinal){
        ArrayList <Sismo> copiaSismos= new ArrayList<>();
        for(Sismo unSismo: sismos){
            if(unSismo.getMomentoExacto().after(FechaInicio) &&
                    unSismo.getMomentoExacto().before(FechaFinal))
                copiaSismos.add(unSismo);
        }
        return copiaSismos;
    }

    /**
     * Get común
     * @return 
     */
    public ArrayList<Sismo> getSismos() {
        return sismos;
    }

    /**
     * Set común
     * @param sismos 
     */
    public void setSismos(ArrayList<Sismo> sismos) {
        this.sismos = sismos;
    }

    /**
     * To String Común
     * @return 
     */
    @Override
    public String toString() {
        return "ControladorSismos{" + "sismos=" + sismos + '}';
    }
    
    
    
}
