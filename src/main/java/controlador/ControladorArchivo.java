/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Usuario
 */
public class ControladorArchivo {
    
    private String nombreArchivo = "tablaSismos.xlsx";
    private String rutaArchivo = "C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\" + nombreArchivo;
    private String hoja = "Hoja1";
    
    //private ListIterator<> lista = new ListIterator<>();

    public ControladorArchivo() {
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
    
    public static ArrayList CargarExcel() {
        ArrayList<String> arrayDatos = new ArrayList<>();
        ArrayList<String> arrayFilas = new ArrayList<>();
        try {
            InputStream archivo = new FileInputStream(new File("C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\tablaSismos.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(archivo);
            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFCell celda;
            XSSFRow fila;
            String datos = new String();

            //System.out.println("Apunto de entrar a loops");

            //System.out.println("" + sheet.getLastRowNum());

            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                fila = sheet.getRow(i);
                for (int j = 0; j < fila.getLastCellNum(); j++) {
                    celda = fila.getCell(j);
                    //System.out.println("Valor: " + celda.toString());
                    arrayDatos.add(fila.getCell(j) + "|");
                }
                arrayFilas.add(arrayDatos + "\n");
                arrayDatos = new ArrayList<>();
            }
            //System.out.println("Finalizado");
            

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return arrayFilas;
    }
    
    public boolean guardar(modelo.Archivo unArchivo){
            return true ;  // el estudiante se ha agregado con éxito
        }
}
