/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
        // TODO code application logic here
    }

    public Sistema() {
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
