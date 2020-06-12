/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import modelo.*;

/**
 *
 * @author Usuario
 */
public class Sistema {

    ControladorArchivo cArchivos;
    ControladorGraficos cGraficos;
    ControladorMapas cMapas;
    ControladorSismos cSismos;
    
    
    
    
    public static void main(String[] args) {
        Sistema elSistema= new Sistema();
        for(int i=0;i<3;i++){
            int r= (int) (Math.random()* ((20 - 0) + 1) + 0);
            Date fecha = new Date(2000+r, 1+i, 1+r+i, r+4, r+40, r+20+i);
            Date fecha2 = new Date(1990+r+i, 1+i+r, 1+r+i+3, r+4, r+40, r+20+i);
            elSistema.getcSismos().addSismo(new Sismo(fecha, 0, TFalla.Choque_Placas, "Cocos y Caribe", r+i*r, i,
                                                      i, "Todo bien", TLugar.Marítimo, TProvincia.No_Aplica));
            elSistema.getcSismos().addSismo(new Sismo(new Date(), 0, TFalla.Subduccion_Placas, "Playa", r*r,i,
                                                      i, "Todo mal", TLugar.Terrestre, TProvincia.Guanacaste));
            elSistema.getcSismos().addSismo(new Sismo(fecha2, 0, TFalla.Tectónico_Subducción, "City", i*r,i,
                                                      i, "Todo normal", TLugar.Terrestre, TProvincia.San_Jose));
        }
        
        System.out.println(elSistema.getcSismos().getSismos());
        System.out.println("\n\n\nGET SISMOS FECHA:\n"+elSistema.getcSismos().getSismosOrdenadosFecha());
        System.out.println("\n\n\nGET SISMOS MAGNITUD:\n"+elSistema.getcSismos().getSismosOrdenadosMagnitud((elSistema.getcSismos().getSismos()),true));
        System.out.println("\n\n\n\nGET SISMOS FECHA:\n"+elSistema.getcSismos().getSismos());
        
                
    }

    public Sistema() {
        this.cArchivos = new ControladorArchivo();
        this.cGraficos = new ControladorGraficos();
        this.cMapas = new ControladorMapas();
        this.cSismos = new ControladorSismos();
    }

    public Sistema(ControladorArchivo cArchivos, ControladorGraficos cGraficos, ControladorMapas cMapas, ControladorSismos cSismos) {
        this.cArchivos = cArchivos;
        this.cGraficos = cGraficos;
        this.cMapas = cMapas;
        this.cSismos = cSismos;
    }

    public ControladorArchivo getcArchivos() {
        return cArchivos;
    }

    public void setcArchivos(ControladorArchivo cArchivos) {
        this.cArchivos = cArchivos;
    }

    public ControladorGraficos getcGraficos() {
        return cGraficos;
    }

    public void setcGraficos(ControladorGraficos cGraficos) {
        this.cGraficos = cGraficos;
    }

    public ControladorMapas getcMapas() {
        return cMapas;
    }

    public void setcMapas(ControladorMapas cMapas) {
        this.cMapas = cMapas;
    }

    public ControladorSismos getcSismos() {
        return cSismos;
    }

    public void setcSismos(ControladorSismos cSismos) {
        this.cSismos = cSismos;
    }
}
