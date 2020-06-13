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
public class Sistema2 {
    
    ControladorArchivo cArchivos;
    ControladorSismos cSismos;
    ControladorGraficos cGraficos;
    
    public Sistema2() {
    }

    public Sistema2(ControladorArchivo cArchivos) {
        this.cArchivos = cArchivos;
    }

    public ControladorArchivo getcArchivos() {
        return cArchivos;
    }
    
    public void setcArchivos(ControladorArchivo cArchivos) {
        this.cArchivos = cArchivos;
    }

  

    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) {
        
        Sistema2 elSistema= new Sistema2();
   
    }
    
}
    


