/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import modelo.*;

/**
 *
 * @author Usuario
 */
public class Sistema2 {
    
    ControladorArchivo cArchivos;
    
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
    
    private static ControladorArchivo archivos = new ControladorArchivo();
    public static void main(String[] args) {
        
        System.out.print(ControladorArchivo.CargarExcel());
        String nombreArchivo = "tablaSismos.xlsx";
        String [] data = {"29/02/2020", "09:56:02", "2.6", "4", "2.6 km ESE de Bijagua de Upala", "Fallamiento Local", "Bijagua de Upala", "10.7227"," -85.0358", "ver"};
        //data = new ArrayList<>();
        //data.add("29-feb.-2020, 09:56:02, 2.6, 4, 2.6 km ESE de Bijagua de Upala, Fallamiento Local, Bijagua de Upala, 10.7227, -85.0358, ver");
        //data = {"29-feb.-2020", "09:56:02", "2.6", "4", "2.6 km ESE de Bijagua de Upala", "Fallamiento Local", "Bijagua de Upala", "10.7227"," -85.0358", "ver"}
        ControladorArchivo.modificarExcel(nombreArchivo, data);
        System.out.print(ControladorArchivo.CargarExcel());
        System.out.print("\n");
        System.out.print(data);
    }
    
}
    


