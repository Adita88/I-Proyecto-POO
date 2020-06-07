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

    public ControladorSismos() {
        sismos= new ArrayList<>();
    }

    public ControladorSismos(ArrayList<Sismo> sismos) {
        this.sismos = sismos;
    }   
    
    public boolean addSismo(Sismo sismo){
        sismos.add(sismo);
        return true;
    }
    
    public boolean removeSismo(Sismo sismo){
        return false;
    }
    
    public ArrayList <Sismo> getSismosOrdenadosFecha (boolean ... ordenDescendente){
        boolean flag = (ordenDescendente.length >= 1) ? ordenDescendente[0] : false;
        sismos.sort( (Sismo sis1, Sismo sis2) ->  sis1.getMomentoExacto().compareTo( sis2.getMomentoExacto() ) );
        if (flag) reverse(sismos);
        return sismos;
    }
    
    public ArrayList <Sismo> getSismosOrdenadosFecha (ArrayList<Sismo> sismos1, boolean ... ordenDescendente) {
        boolean flag = (ordenDescendente.length >= 1) ? ordenDescendente[0] : false;
        ArrayList<Sismo> listaSismos= (ArrayList<Sismo>) sismos1.clone();
        listaSismos.sort( (Sismo sis1, Sismo sis2) -> 
                sis1.getMomentoExacto().compareTo( sis2.getMomentoExacto() ) );
        if (flag) reverse(listaSismos);
        return listaSismos;
    }
    
    public ArrayList <Sismo> getSismosOrdenadosMagnitud(boolean ... ordenDescendente){
        boolean flag = (ordenDescendente.length >= 1) ? ordenDescendente[0] : false;
        sismos.sort( (Sismo sis1, Sismo sis2) ->  
                (sis1.getMagnitud()< sis2.getMagnitud()) ? -1 : ((sis1.getMagnitud() == sis2.getMagnitud()) ? 0 : 1)  );
        if (flag) reverse(sismos);
        return sismos;
    }
       
    
    public ArrayList <Sismo> getSismosOrdenadosMagnitud(ArrayList<Sismo> sismos1, boolean ... ordenDescendente) {
        boolean flag = (ordenDescendente.length >= 1) ? ordenDescendente[0] : false;
        ArrayList<Sismo> listaSismos= (ArrayList<Sismo>) sismos1.clone();
        listaSismos.sort( (Sismo sis1, Sismo sis2) ->  
                (sis1.getMagnitud()< sis2.getMagnitud()) ? -1 : ((sis1.getMagnitud() == sis2.getMagnitud()) ? 0 : 1)  );
        if (flag) reverse(listaSismos);
        return listaSismos;
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

    public ArrayList<Sismo> getSismos() {
        return sismos;
    }

    public void setSismos(ArrayList<Sismo> sismos) {
        this.sismos = sismos;
    }

    @Override
    public String toString() {
        return "ControladorSismos{" + "sismos=" + sismos + '}';
    }
    
    
    
}
