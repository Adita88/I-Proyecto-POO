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
public class ControladorSismos {
    
    private ArrayList<Sismo> sismos ;
    
    public ArrayList <Sismo> getSismosOrdenadosFecha (){
        sismos.sort( (Sismo sis1, Sismo sis2) ->  sis1.getMomentoExacto().compareTo( sis2.getMomentoExacto() ) );
        return sismos;
    }
    
    public ArrayList <Sismo> getSismosOrdenadosFecha (ArrayList<Sismo> sismos){
        sismos.sort( (Sismo sis1, Sismo sis2) ->  sis1.getMomentoExacto().compareTo( sis2.getMomentoExacto() ) );
        return sismos;
    }
    
    public ArrayList <Sismo> getSismosOrdenadosMagnitud(){
        sismos.sort( (Sismo sis1, Sismo sis2) ->  
                (sis1.getMagnitud()< sis2.getMagnitud()) ? -1 : ((sis1.getMagnitud() == sis2.getMagnitud()) ? 0 : 1)  );
        return sismos;
    }
       
    
    public ArrayList <Sismo> getSismosOrdenadosMagnitud(ArrayList<Sismo> sismos){
        sismos.sort( (Sismo sis1, Sismo sis2) ->  
                (sis1.getMagnitud()< sis2.getMagnitud()) ? -1 : ((sis1.getMagnitud() == sis2.getMagnitud()) ? 0 : 1)  );
        return sismos;
    }
    
    public ArrayList<Sismo> getSismosIgualAMagnitud(double magnitud){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getMagnitud()==magnitud)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    public ArrayList<Sismo> getSismosMenorAMagnitud(double magnitud){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getMagnitud()<magnitud)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    public ArrayList<Sismo> getSismosMayorAMagnitud(double magnitud){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getMagnitud()>magnitud)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    public ArrayList<Sismo> getSismosOrigenFalla(TFalla origenFalla){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getOrigenFalla()==origenFalla)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    public ArrayList<Sismo> getSismosOrigenFalla(TProvincia provincia){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getProvincia()==provincia)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    public ArrayList<Sismo> getSismosOrigenFalla(TLugar lugar){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getLugar()==lugar)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    public ArrayList<Sismo> getSismosOrigenFalla(Date momentoExacto){
        ArrayList <Sismo> listaSismos= new ArrayList<>();
        for(int i=0; i<sismos.size(); i++)
            if(sismos.get(i).getMomentoExacto()==momentoExacto)
                listaSismos.add(sismos.get(i));
        return listaSismos;
    }
    
    
    
}
