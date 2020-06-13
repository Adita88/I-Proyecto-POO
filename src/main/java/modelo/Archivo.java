/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


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
    
    Workbook book;
    
    public String Importar(File archivo, JTable tabla){
        
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        
        try{
            book = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = book.getSheetAt(0);
            Iterator FilaIterator = hoja.rowIterator();
            int IndiceFila= -1;
            
            while(FilaIterator.hasNext()){
                IndiceFila++;
                Row fila = (Row)FilaIterator.next();
                Iterator ColumnaIterator = fila.cellIterator();
                Object[]ListaColumna = new Object[9999];
                int IndiceColumna = -1;
                
                while(ColumnaIterator.hasNext()){
                    IndiceColumna ++;
                    Cell celda= (Cell)ColumnaIterator.next();
                    
                    if(IndiceFila == 0){
                        modelo.addColumn(celda.getStringCellValue());
                    } else {
                        if(celda != null){
                            switch (celda.getCellType()){
                                case Cell.CELL_TYPE_NUMERIC:
                                    ListaColumna[IndiceColumna] = (int)Math.round(celda.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    ListaColumna[IndiceColumna] = celda.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    ListaColumna[IndiceColumna] = celda.getBooleanCellValue();
                                    break;
                                default:
                                    ListaColumna[IndiceColumna] = celda.getDateCellValue();
                                    break;
                            }
                        }
                    }
                }
                
                if(IndiceFila != 0)modelo.addRow(ListaColumna);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" + "nombreArchivo=" + nombreArchivo 
                + ", rutaArchivo=" + rutaArchivo 
                + ", hoja=" + hoja + '}';
    }   
    
}
