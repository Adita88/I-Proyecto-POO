/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Iterator;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Usuario
 */
public class ControladorArchivo {
    
    //private modelo.Archivo archivo;
    
    public boolean cargarArchivo(){
        
//        try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
//        //leer archivo
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//        //obtener la hoja que se va a leer
//            XSSFSheet sheet = workbook.getSheetAt(0);
//        //Obtener filas
//            Iterator<Row> rowIterator = sheet.iterator();
//            
//            Row row;
//            //Recorrer cada fila hasta llegar al final
//            while (rowIterator.hasNext()) {
//                row = rowIterator.next();
//                //Celdas por fila
//                Iterator<Cell> cellIterator = row.cellIterator();
//                Cell cell;
//                //Se recorre cada celda
//                while (cellIterator.hasNext()) {
//                    //Celda en especifico e imprimir
//                    cell = cellIterator.next();
//                    System.out.print(cell.getStringCellValue()+" | ");
//                }
//                System.out.println();
//            }
//    
//        } catch (Exception e) {
//            e.getMessage();
//        }
        return false;
       
        }
    
    public boolean guardar(modelo.Archivo unArchivo){
            return true ;  // el estudiante se ha agregado con éxito
        }
}
