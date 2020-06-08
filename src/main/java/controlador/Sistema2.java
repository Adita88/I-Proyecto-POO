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
    }
    
}
    


