/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 * Clase Archivo
 * @author Usuario
 * 
 */
public class Archivo {
    
    public String nombreArchivo = "tablaSismos.xlsx";
    public String rutaArchivo = "C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\" + nombreArchivo;
    public String hoja = "Hoja1";

    public Archivo() {
    }

    public Archivo(String nombreArchivo, String rutaArchivo, String hoja) {
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.hoja = hoja;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void setHoja(String hoja) {
        this.hoja = hoja;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public String getHoja() {
        return hoja;
    }
   

    @Override
    public String toString() {
        return "{" + "nombreArchivo=" + nombreArchivo 
                + ", rutaArchivo=" + rutaArchivo 
                + ", hoja=" + hoja + '}';
    }   
    
}
