/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.AWTEventListener;
import java.io.*;
import visual.Interfaz2;
import modelo.pruebaArchivo;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Usuario
 */
public class pruebaControlador implements ActionListener{
    
    pruebaArchivo ModeloEx = new pruebaArchivo();
    Interfaz2 VistaEx = new Interfaz2();
    JFileChooser seleccionaArchivo = new JFileChooser();
    File archivo;
    int contador = 0;
    
    public pruebaControlador(Interfaz2 VistaEx, pruebaArchivo ModeloEx){
        this.VistaEx = VistaEx;
        this.ModeloEx = ModeloEx;
        this.VistaEx.btnImportar.addActionListener(this);
        this.VistaEx.btnExportar.addActionListener(this);
        VistaEx.setVisible(true);
        VistaEx.setLocationRelativeTo(null);
    }

    public void AgregarFiltro(){
        seleccionaArchivo.setFileFilter(new FileNameExtensionFilter("Excel ('.xls)", "xls"));
        seleccionaArchivo.setFileFilter(new FileNameExtensionFilter("Excel ('.xlsx)", "xlsx"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        contador ++;
        if(contador == 1 )AgregarFiltro();
        
        if(e.getSource()==VistaEx.btnImportar){
            if(seleccionaArchivo.showDialog(null, "Seleccionar Archivo")==JFileChooser.APPROVE_OPTION){
                archivo = seleccionaArchivo.getSelectedFile();
                
                if(seleccionaArchivo.getName(archivo).endsWith("xls")){
                    
                    JOptionPane.showMessageDialog(null, ModeloEx.Importar(archivo, VistaEx.datosExcel));
                } else if(archivo.getName().endsWith("xlsx")){
                    ;
                    JOptionPane.showMessageDialog(null, ModeloEx.Importar(archivo, VistaEx.datosExcel));
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Seleccionar formato valido");
                }
            }
            
            
        }
        
        if(e.getSource()==VistaEx.btnExportar){
            if(seleccionaArchivo.showDialog(null, "Seleccionar Archivo")==JFileChooser.APPROVE_OPTION){
                archivo= seleccionaArchivo.getSelectedFile();
                if(seleccionaArchivo.getName(archivo).endsWith("xls")){
                    JOptionPane.showMessageDialog(null, ModeloEx.Exportar(archivo, VistaEx.datosExcel));
                } else if (archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, ModeloEx.Exportar(archivo, VistaEx.datosExcel));
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccionar formato valido");
                }
            }
            
            
        }
    }
    
    
    
    
    
}
