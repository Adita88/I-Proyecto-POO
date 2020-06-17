/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;


import modelo.*;
import modelo.TFalla;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Clase Controlador Archivo
 * @author Usuario
 */
public class ControladorArchivo{
    
    private String nombreArchivo = "tablaSismos.xlsx";
    private String rutaArchivo = "C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\" + nombreArchivo;
    private String hoja = "Hoja1";
    File archivo;
    int contador = 0;
    
    //private ListIterator<> lista = new ListIterator<>();

    public ControladorArchivo() {
    }
    
/**
 * Nombre de archivo
 * @param nombreArchivo  nombre de archivo
 */   
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * ruta archivo
     * @param rutaArchivo  ruta del archivo de Excel
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
/**
 * hoja
 * @param hoja Hoja de Excel
 */
    public void setHoja(String hoja) {
        this.hoja = hoja;
    }
    
    /**
     * getnombreArchivo
     * @return nombreArchivo: String
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    
    /**
     * rutaArchivo
     * @return ruta archivo
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * hoja
     * @return  hoja
     */
    public String getHoja() {
        return hoja;
    }

   
    @Override
    public String toString() {
        return "ControladorArchivo{" + "nombreArchivo=" + nombreArchivo 
                + ", rutaArchivo=" + rutaArchivo 
                + ", hoja=" + hoja + '}';
    }
    
    /**
     * Método para cargar el archivo de excel y generar lista de sismos
     * @return Arraylist con sismos
     */
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

            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                fila = sheet.getRow(i);
                for (int j = 0; j < fila.getLastCellNum(); j++) {
                    
                    arrayDatos.add(fila.getCell(j));
                }
                arrayFilas.add(arrayDatos + "\n");
                arrayDatos = new ArrayList<>();
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return arrayFilas;
    }
    
    
    /**
     * Método para modificar el archivo de Excel
     * @param nombreArchivo nombre del archivo de Excel
     * @param sismo un sismo
     */
    public static void modificarExcel(String nombreArchivo, Sismo sismo) {
        SimpleDateFormat formato =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            InputStream archivo = new FileInputStream(new File(nombreArchivo));
            XSSFWorkbook libroViejo = new XSSFWorkbook(archivo);

            XSSFSheet hoja1 = libroViejo.getSheetAt(0);

            XSSFRow filaVieja;

            filaVieja = hoja1.createRow(hoja1.getLastRowNum() + 1);
            for (int i = 0; i < sismo.toString().length(); i++) {// Tantos loops como info en el arreglo
                XSSFCell cell = filaVieja.createCell(i);
                if (i == 0){
                    String momento = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(sismo.getMomentoExacto());
                    cell.setCellValue((String) (momento));
                } else if (i == 1){
                    cell.setCellValue((Double) sismo.getProfundidad());
                } else if (i == 2){
                    cell.setCellValue((String) sismo.getOrigenFalla().toString());
                } else if (i == 3){
                    cell.setCellValue((String) sismo.getDetalleFalla());
                } else if (i == 4){
                    cell.setCellValue((Double) sismo.getMagnitud());
                } else if (i == 5){
                    cell.setCellValue((Double) sismo.getLatitud());
                } else if (i == 6){
                    cell.setCellValue((Double) sismo.getLongitud());
                } else if (i == 7){
                    cell.setCellValue((String) sismo.getDescripcionDetallada());
                } else if (i == 8){
                    cell.setCellValue((String) sismo.getLugar().toString());
                } else if (i == 9){
                    cell.setCellValue((String) sismo.getProvincia().toString());
                }
                
            }
            FileOutputStream fos = null;
            File file;

            file = new File("C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\tablaSismos.xlsx");
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
   
    /**
     * Metodo que recibe la lista del archivo de excel y genera ArrayList sismos
     * @return arraylist con sismos
     */
    public static ArrayList listaSismos(){
        List<Object> lista= new ArrayList<Object>();
        lista = CargarExcel();
        try{
        lista.remove(lista.get(0));
        }catch (IndexOutOfBoundsException error){   
        }
        
        Sismo unSismo = new Sismo();
        ArrayList<Sismo> listaSismos = new ArrayList();
        
        Date fecha = new Date();
        double profundidad = 0.0;
        modelo.TFalla origenFalla = null;
        String detalleFalla = "";
        double magnitud = 0.0;
        double latitud = 0.0;
        double longitud= 0.0;
        String descripcionDetallada = "";
        TLugar lugar = null;
        TProvincia provincia = null;
        //SimpleDateFormat formato =new SimpleDateFormat("EEEE MMMM d HH:mm:ss z yyyy");
        SimpleDateFormat formato =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        for(Object obj: lista){
            
            String[] elementos = obj.toString().split(",");
            for (String elemento : elementos) {
                if (elemento.equals(elementos[0])) {
                    String dato = "";
                    for (int j = 1; j < elemento.length(); j++) {
                        dato += elemento.charAt(j);
                    }
                    try {
                        fecha = formato.parse(dato);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorArchivo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (elemento.equals(elementos[1])) {
                    profundidad = Double.parseDouble(elemento);
                } else if (elemento.equals(elementos[2])) {
                    String dato = "";
                    for (int j = 1; j < elemento.length(); j++) {
                        dato += elemento.charAt(j);
                    }
                    origenFalla = TFalla.valueOf(dato);
                } else if (elemento.equals(elementos[3])) {
                    detalleFalla = elemento;
                } else if (elemento.equals(elementos[4])) {
                    magnitud = Double.parseDouble(elemento);
                } else if (elemento.equals(elementos[5])) {
                    latitud = Double.parseDouble(elemento);
                } else if (elemento.equals(elementos[6])) {
                    longitud = Double.parseDouble(elemento);
                } else if (elemento.equals(elementos[7])) {
                    descripcionDetallada = elemento;
                } else if (elemento.equals(elementos[8])) {
                    String dato = "";
                    for (int j = 1; j < elemento.length(); j++) {
                        dato += elemento.charAt(j);
                    }
                    lugar = TLugar.valueOf(dato);
                } else if (elemento.equals(elementos[9])) {
                    String dato = "";
                    for (int j = 1; j < elemento.length(); j++) {
                        dato += elemento.charAt(j);
                    }
                    if(dato.contains("]\n")){
                        String dato2 = "";
                        for (int j = 0; j < dato.length()-2; j++) {
                            dato2 += dato.charAt(j);
                        }
                        provincia = TProvincia.valueOf(dato2);
                    }else{
                    provincia = TProvincia.valueOf(dato);
                }}
                  
            }
            unSismo.setMomentoExacto(fecha);
            unSismo.setProfundidad(profundidad);
            unSismo.setOrigenFalla(origenFalla);
            unSismo.setDetalleFalla(detalleFalla);
            unSismo.setMagnitud(magnitud);
            unSismo.setLatitud(latitud);
            unSismo.setLongitud(longitud);
            unSismo.setDescripcionDetallada(descripcionDetallada);
            unSismo.setLugar(lugar);
            unSismo.setProvincia(provincia);
            listaSismos.add(unSismo);
            unSismo = new Sismo();
        }
        return listaSismos;
    }
  
 }
    

