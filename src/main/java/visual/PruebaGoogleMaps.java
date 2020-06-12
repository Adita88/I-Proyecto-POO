/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PruebaGoogleMaps {
    public static void main(String[] args) {
        generarMapa();
    }
    
    private static void generarMapa(){
        JFrame ventanaPrueba = new JFrame("Google Maps");

        try {
            String key;
            key= "AIzaSyBJwVIsMCF6QDAts_u0r-DfB8OycuYFk6w" ; //Clave obtenida para conectarse a API Google
            
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                    + "center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap"
                    +"&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers="+
                    "color:green%7Clabel:G%7C40.711614,-74.012318"
                    +"&markers=color:red%7Clabel:C%7C40.718217,-73.998284"
                    +"&key="+key;
            
            String archivoDestino = "imagen.jpg";
            
            URL url = new URL(imageUrl);
            
            InputStream entradaDatos = url.openStream(); //Recibe datos de maps
            OutputStream salidaDatos= new FileOutputStream(archivoDestino); //Los escribirá en archivo de destino

            byte[] data = new byte[2048];
            int tamDatos;

            while ((tamDatos = entradaDatos.read(data)) != -1) {
                salidaDatos.write(data, 0, tamDatos);//Escribe Datos recibidos en el archivo
            }

            entradaDatos.close();// cierra
            salidaDatos.close();//Cierra
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        ventanaPrueba.add(new JLabel(new ImageIcon((new ImageIcon("imagen.jpg"))
                .getImage().getScaledInstance(630, 600,java.awt.Image.SCALE_SMOOTH))));

        ventanaPrueba.setVisible(true);
        ventanaPrueba.pack();
    }
    
}
