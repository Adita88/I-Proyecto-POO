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
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;




public class pruebas extends JFrame{

    private void crearGraficoPastel(String titulo, defaultpiedataset, true, true, false); {

        // Fuente de Datos
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset(); 
        defaultpiedataset.setValue("Programacion", new Double(41.200000000000003D)); 
        defaultpiedataset.setValue("Electronica", new Double(11D)); 
        defaultpiedataset.setValue("Hacking", new Double(19.5D)); 
        defaultpiedataset.setValue("SEO", new Double(30.5D)); 
        defaultpiedataset.setValue("Redes", new Double(2.0D)); 

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart3D("Tematicas Blog", defaultpiedataset, true, true, false); 
        PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
        pieplot3d.setDepthFactor(0.5); 
        pieplot3d.setStartAngle(290D); 
        pieplot3d.setDirection(Rotation.CLOCKWISE); 
        pieplot3d.setForegroundAlpha(0.5F); 
        
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }
    
    public static void main(String args[]){
        new Ventana().setVisible(true);
    }
}


} 