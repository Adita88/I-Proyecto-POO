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
    ControladorSismos cSismos;

    public Sistema() {
        this.cArchivos = new ControladorArchivo();
        this.cGraficos = new ControladorGraficos();
        this.cSismos = new ControladorSismos();
    }

    public Sistema(ControladorArchivo cArchivos, ControladorGraficos cGraficos, ControladorSismos cSismos) {
        this.cArchivos = cArchivos;
        this.cGraficos = cGraficos;
        this.cSismos = cSismos;
    }

    
    public void nuevoSismo(Date momentoExacto, double profundidad, TFalla origenFalla, String detalleFalla, double magnitud, double latitud, double longitud, String descripcionDetallada, TLugar lugar, TProvincia provincia){
        System.out.println("Lat: "+ latitud+"\nLog: "+longitud);
        cSismos.addSismo(new Sismo(momentoExacto, profundidad, origenFalla, detalleFalla, magnitud, latitud, longitud, descripcionDetallada, lugar, provincia));
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

    public ControladorSismos getcSismos() {
        return cSismos;
    }

    public void setcSismos(ControladorSismos cSismos) {
        this.cSismos = cSismos;
    }
}
