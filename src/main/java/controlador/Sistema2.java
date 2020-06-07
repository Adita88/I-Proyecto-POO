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
//        ControladorArchivo javaPoiUtils = new ControladorArchivo();
//        ArrayList<String[]> arrayDatosExcel = javaPoiUtils.readExcelFileToArray(new File("C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\tablaSismos.xlsx" ));
//        int r = 0;
//        for (String[] next : arrayDatosExcel){
//            //System.out.print("Array Row: " + r++ + " -> ");
//            for(int c = 0; c < next.length; c++){
//                //System.out.print("[Column " + c + ":" + next + "] ");
//                System.out.print(next);
//            }
//            System.out.println();
//        }
        //System.out.println(archivos.cargarArchivo());
//        try (FileInputStream file = new FileInputStream(new File("C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\tablaSismos.xlsx" ))) {
//        //leer archivo
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//        //obtener la hoja que se va a leer
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            
//            int numFilas = sheet.getLastRowNum();
//            
//            for (int a = 0; a <= numFilas; a++){
//                
//                Row fila = sheet.getRow(a);
//                int numCol = fila.getLastCellNum();
//                
//                for (int b = 0; b < numCol; b++){
//                    
//                    Cell celda = fila.getCell(b);
//                    
//                    switch(celda.getCellTypeEnum().toString()){
//                        case  "Numeric":
//                            System.out.print(celda.getNumericCellValue() + " ");
//                            break;
//                            
//                        case  "String":
//                            System.out.print(celda.getNumericCellValue()+ " ");
//                            break;
//                            
//                        case  "Formula":
//                            System.out.print(celda.getCellFormula()+ " ");
//                            break;
//                    }
//                    
//                    System.out.println(" ");
//                    
//                }
//                
//            }
//        }


//        ArrayList<String> arrayDatos = new ArrayList<>();
//        try {
//            InputStream myFile = new FileInputStream(new File("C:\\Users\\Usuario\\Desktop\\primerProyectoPOO\\tablaSismos.xlsx"));
//            XSSFWorkbook wb = new XSSFWorkbook(myFile);
//            XSSFSheet sheet = wb.getSheetAt(0);
//
//            XSSFCell cell;
//            XSSFRow row;
//            String datos = new String();
//
//            System.out.println("Apunto de entrar a loops");
//
//            System.out.println("" + sheet.getLastRowNum());
//
//            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
//                row = sheet.getRow(i);
//                for (int j = 0; j < row.getLastCellNum(); j++) {
//                    cell = row.getCell(j);
//                    System.out.println("Valor: " + cell.toString());
//                    arrayDatos.add(datos);
//                }
//            }
//            System.out.println("Finalizado");
//            //return arrayDatos;
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println(e.getMessage());
//        }
    }
    
}
    


