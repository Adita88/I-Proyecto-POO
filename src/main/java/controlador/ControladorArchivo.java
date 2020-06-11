/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

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

    @Override
    public String toString() {
        return "ControladorArchivo{" + "nombreArchivo=" + nombreArchivo 
                + ", rutaArchivo=" + rutaArchivo 
                + ", hoja=" + hoja + '}';
    }
    
    
    
    public static ArrayList CargarExcel() {
        ArrayList arrayDatos = new ArrayList();
        ArrayList<String> arrayFilas = new ArrayList();
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
                    arrayDatos.add(fila.getCell(j));
                    switch(celda.getCellType()){
                        case Cell.CELL_TYPE_NUMERIC:
                            if( DateUtil.isCellDateFormatted(celda) ){
                                System.out.println(celda.getDateCellValue());
                            }else{
                                System.out.println(celda.getNumericCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.println(celda.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.println(celda.getBooleanCellValue());
                            break;
                    }
                        
                    }
                    //System.out.println(fila.getCell(j));
                }
                //System.out.println(arrayDatos);
                arrayFilas.add(arrayDatos + "\n");
                //arrayDatos.get(0);
                //System.out.println(arrayDatos);
                //System.out.println(arrayFilas);
                arrayDatos = new ArrayList<>();
            
            //System.out.println("Finalizado");
            

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return arrayFilas;
    }
    
        public static ArrayList CargarExcelSismos() {
        List arrayDatos = new ArrayList();
        ArrayList<String> arrayFilas = new ArrayList();
        try {
            InputStream archivo = new FileInputStream(new File("C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\tablaSismos.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(archivo);
            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFCell celda;
            XSSFRow fila;
            String datos = new String();
            Sismo datoSismo = new Sismo();

            //System.out.println("Apunto de entrar a loops");

            //System.out.println("" + sheet.getLastRowNum());

            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                fila = sheet.getRow(i);
                for (int j = 0; j < fila.getLastCellNum(); j++) {
                    celda = fila.getCell(j);
                    //System.out.println("Valor: " + celda.toString());
                    arrayDatos.add(fila.getCell(j));
                    //System.out.println(fila.getCell(j));
                }
                
                //System.out.println(arrayDatos);
                int a = 0;
                var prueba = "";
                while(a < arrayDatos.size()){
                    //System.out.println(a);
                    var esDato = arrayDatos.get(a);
                    prueba += esDato + "|";
                    a ++; 
                }
                for (int x=0;x<prueba.length();x++){
                    //System.out.println("Caracter " + x + ": " + prueba.charAt(x));
                }
                //System.out.println(prueba);
                //datoSismo = Sismo(prueba);
                
                arrayFilas.add(prueba + "\n");
                System.out.println(prueba.charAt(i));
                //arrayDatos.get(0);
                //System.out.println(arrayDatos);
                //System.out.println(arrayFilas);
                arrayDatos = new ArrayList<>();
            }
            //System.out.println("Finalizado");
            

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return arrayFilas;
    }
    
    public static void modificarExcel(String nombreArchivo, String[] data) {
        try {
            InputStream archivo = new FileInputStream(new File(nombreArchivo));
            XSSFWorkbook libroViejo = new XSSFWorkbook(archivo);

            XSSFSheet hoja1 = libroViejo.getSheetAt(0);

            XSSFRow filaVieja;

            filaVieja = hoja1.createRow(hoja1.getLastRowNum() + 1);
            for (int i = 0; i < data.length; i++) {// Tantos loops como info en el arreglo
                XSSFCell cell = filaVieja.createCell(i);
                cell.setCellValue(data[i]);
            }

            FileOutputStream fos = null;
            File file;

            file = new File(nombreArchivo);
            fos = new FileOutputStream(file);

            libroViejo.write(fos);
            libroViejo.close();
            fos.close();

            System.out.println("Finalizado");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList listaSismos(){
        ArrayList lista;
        lista = CargarExcelSismos();
        Object lista2;
        Sismo dato = new Sismo();
        for(int i = 1; i < lista.size(); i++){
            //System.out.println(lista.size());
            lista2 = lista.get(i);
            //System.out.println(lista2);
           
 
            //lista2.add(dato(lista.get(i)));
            //dato = lista.get(i);
            
        }
//        System.out.println(lista + "lista");
//        //ArrayList<Sismo> lista2;
//        ArrayList<Sismo> lista2 = new ArrayList();
//        List lista3 = new ArrayList();
//        
//        System.out.println("1");
//
//        for(int i = 1; i < lista.size(); i++){
//            int j = 0;
//            System.out.println(lista.spliterator());
//            System.out.println(lista.get(i));
//            lista3.add(lista.get(i));
//            lista3.add(lista.get(i).);
//            System.out.println(lista3.get(j));
//            System.out.println(lista3 + "lista3");
//            for(int j = 0; j < lista.indexOf(i); j++){
//                System.out.println(lista.indexOf(i));
//                ArrayList<Sismo> unSismo = new ArrayList();
//                Sismo sismo = new Sismo();
//                ArrayList listaI = new ArrayList();
//                listaI = lista.get(i);
//                sismo.getMomentoExacto(listaI.get(i));
//                sismo.getProfundidad();
//                sismo.getOrigenFalla();
//                sismo.getDetalleFalla();
//                sismo.getMagnitud();
//                sismo.getLatitud();
//                sismo.getLongitud();
//                sismo.getDescripcionDetallada();
//                sismo.getLugar();
//                sismo.getProvincia();
//  
//            }
//            System.out.println("2");
//            System.out.println(lista.get(i));
//            
//            lista2.add(lista.get(i));
//            System.out.println("2.5");
//
//            //System.out.println("Arreglando lista");
//            System.out.println(lista.get(i));
//            
//
//        }
//        System.out.println("3");
        return lista;
    }

}
