/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Sistema;
import controlador.ControladorArchivo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        Sistema elSistema = new Sistema();
        
        SimpleDateFormat formato =new SimpleDateFormat("dd-MM-uuuu HH:mm:ss");
        Date fecha = formato.parse("20-03-2019 13:03:45");
        String detalle1= "aasd";
        String detalle2 = "asdasd sdas sdas";
        Sismo sismos = new Sismo();
        sismos.setMomentoExacto(fecha);
        sismos.setProfundidad(3.6);
        sismos.setMagnitud(2.2);
        sismos.setOrigenFalla(TFalla.Choque_Placas);
        sismos.setDetalleFalla(detalle1);
        sismos.setMagnitud(3.9);
        sismos.setLatitud(8.456);
        sismos.setLongitud(-85.4556);
        sismos.setDescripcionDetallada(detalle2);
        sismos.setLugar(TLugar.Marítimo);
        sismos.setProvincia(TProvincia.Limon);
        
        System.out.println(sismos);
        System.out.println(sismos.toString().length());
        
        ControladorArchivo.modificarExcel("tablaSismos.xlsx", sismos);
        ArrayList<Sismo> lista = new ArrayList();
        lista = ControladorArchivo.listaSismos();
        System.out.println(lista);
        
    }
    
}
