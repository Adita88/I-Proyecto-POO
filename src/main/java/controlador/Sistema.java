/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;

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
        //Sistema elSistema= new Sistema();
        System.out.println("LA HORA: " + new Date());
        /*for(int i=0;i<10;i++){
            int r= ((int) Math.random())%20;
            elSistema.getcSismos().addSismo(new Sismo(new Date(), i+r, TFalla.Choque_Placas, "s" , i, i +"VAMOS POR RONDA ", TLugar.Marítimo, TProvincia.No_Aplica) );
            
        }*/
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
