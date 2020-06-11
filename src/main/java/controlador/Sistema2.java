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
        
        Sistema elSistema= new Sistema();
        
        //System.out.print(ControladorArchivo.CargarExcel());
//        String nombreArchivo = "tablaSismos.xlsx";
//        String [] data = {"29/02/2020", "09:56:02", "2.6", "4", "2.6 km ESE de Bijagua de Upala", "Fallamiento Local", "Bijagua de Upala", "10.7227"," -85.0358", "ver"};
//        ControladorArchivo.modificarExcel(nombreArchivo, data);
        //System.out.print(ControladorArchivo.CargarExcel());
        //System.out.print(ControladorArchivo.CargarExcelSismos());
        
        //System.out.print("\n");
        //System.out.print(data);
        
//        ArrayList<Sismo> listaS = ControladorArchivo.listaSismos();
//        System.out.print(ControladorArchivo.listaSismos() + "Lista");
//        
//        //Date fecha = new Date();
//        int r= (int) (Math.random()* ((20 - 0) + 1) + 0);
//        //System.out.print(r + "\n");
//        Date fecha = new Date(2000+r, 2, 1+r+1, r+4, r+40, r+20+1);
//        //System.out.print(fecha + "\n");
//        //fecha = 29/09/1988;
//        Sismo sismos = new Sismo(fecha,22.4, modelo.TFalla.Tectónico_Subducción, "Playa Hermosa de Garabito", 2.2, 9.22, -84.85, "Playa Hermosa de Garabito", modelo.TLugar.Terrestre, modelo.TProvincia.Limon);
//
//        System.out.print(listaS.add(sismos));
//        System.out.print(listaS.size());
//        System.out.print(listaS);
        //System.out.print(elSistema.getcSismos().getSismosOrdenadosFecha(listaS, true));
        
        //elSistema.getcSismos().getSismosOrdenadosMagnitud(listaS, true);
        
        System.out.println(ControladorGraficos.listaSismos());
    }
    
}
    


