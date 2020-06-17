/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

//Import


import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;


/**
 * Clase Grafico
 * @author Usuario
 */
public class Grafico {
    
    private BufferedImage _image ;//para la imagen en memoria
    private JFreeChart grafico ;// el grafico
    private Dimension d;

    public Grafico() {
    }

    public Grafico(BufferedImage _image, JFreeChart grafico, Dimension d) {
        this._image = _image;
        this.grafico = grafico;
        this.d = d;
    }

    public void setImage(BufferedImage _image) {
        this._image = _image;
    }

    public void setGrafico(JFreeChart grafico) {
        this.grafico = grafico;
    }

    public void setD(Dimension d) {
        this.d = d;
    }

    public BufferedImage getImage() {
        return _image;
    }

    public JFreeChart getGrafico() {
        return grafico;
    }

    public Dimension getD() {
        return d;
    }
    
    
    
    /**
     * Crea el grafico de barras
     * @param d dimension del JPanel
     * @param v Arraylist de los valores a incluir en el grafico
     * @param arg1 Datos a mostrar en el gráfico
     * @param arg2 Datos a mostrar en el gráfico
     * @param data Datos a mostrar en el gráfico
     */
    public void crear_grafico_de_barras(Dimension d, int[] v, String[] arg1, String arg2[],String[] data){
        
        this.d= d;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //se llenan los datos
        for(int i=0; i<=v.length-1;i++){
            dataset.setValue(v[i], arg2[i], arg1[i]);
        }      

        //se crea el grafico
        grafico = ChartFactory.createBarChart3D(data[0], data[1], data[2], dataset, PlotOrientation.VERTICAL , true, true, false);

        //se coloca el grafico en memoria
        _image = grafico.createBufferedImage(this.d.width,this.d.height);

        System.err.println("grafico creado");    
    }
    
     /* carga la imagen que esta en memoria en el objeto jLabel */

    /**
     * Cargar la imagen del grafico de barras
     * @param lb JLabel a mostrar la imagen
     */
    public void cargar_grafico(JLabel lb){

        ImageIcon imagenFondo = new ImageIcon(_image); 
        lb.setIcon(imagenFondo);
        lb.repaint();
    } 
    
    /**
     * Guardar la imagen en memoria
     */
    public void Guardar(){

       JFileChooser fileChooser = new JFileChooser();
       int result = fileChooser.showSaveDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){

            try {

                //se obtiene la direccion donde se guardara la imagen
                String url = fileChooser.getSelectedFile().toString();
                //Se guarda la imagen
                ChartUtilities.saveChartAsJPEG(new File(url + ".jpg"), grafico, d.width, d.height);

            } catch (IOException ex) {

                Logger.getLogger(Grafico .class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
    
    /**
     * Crea el gráfico de histograma
     * @param histograma Arraylist de valores 
     * @param jPanelHistograma JPanel a mostrar el gráfico
     * @param colorBarras  Color de las barras
     */
    public void GraficoHistograma(int[] histograma,JPanel jPanelHistograma,Color colorBarras) {
       
        //Creamos el dataSet y añadimos el histograma
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String serie = "Cantidad de sismo por magnitud";
        for (int i = 0; i < histograma.length; i++){
            dataset.addValue(histograma[0], serie, "Menos de 2.0");
            dataset.addValue(histograma[1], serie, "2.0 - 2.9");
            dataset.addValue(histograma[2], serie, "3.0 - 3.9");
            dataset.addValue(histograma[3], serie, "4.0 - 4.9");
            dataset.addValue(histograma[4], serie, "5.0 - 5.9");
            dataset.addValue(histograma[5], serie, "6.0 - 6.9");
            dataset.addValue(histograma[6], serie, "7.0 - 7.9");
            dataset.addValue(histograma[7], serie, "8.0 - 8.9");
            dataset.addValue(histograma[8], serie, "9.0 - 9.9");
            dataset.addValue(histograma[9], serie, "+ de 10");
        }
        
        JFreeChart chart = ChartFactory.createBarChart("Sismos por rango de magnitud", "Magnitud", "Cantidad",
                                    dataset, PlotOrientation.VERTICAL, true, true, false);
        
        //Modificamos el diseño del chart
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, colorBarras);
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(new Color(214, 217, 223)); 
        jPanelHistograma.removeAll();
        jPanelHistograma.repaint();
        jPanelHistograma.setLayout(new java.awt.BorderLayout());
        jPanelHistograma.add(new ChartPanel(chart));
        jPanelHistograma.validate();  
    
    }
    
    /**
     * Crea el grafico Pastel
     * @param v valores a mostrar en el gráfico
     * @param grafPastel lugar a mostrar el Gráfico 
     */
    public void crearGraficoPastel(int[] v, JPanel grafPastel) {

        // Fuente de Datos
        DefaultPieDataset dataSetPie = new DefaultPieDataset(); 
        int porc0 = (v[0] * 100) / 6;
        int porc1 = (v[1] * 100) / 6;
        int porc2 = (v[2] * 100) / 6;
        int porc3 = (v[3] * 100) / 6;
        int porc4 = (v[4] * 100) / 6;
        int porc5 = (v[5] * 100) / 6;
        
        for (int i = 0; i < v.length; i++){
            
            System.out.println(v[i]);
            
            dataSetPie.setValue("Choque_Placas: " + String.valueOf(v[0]) + " (" + porc0 + " %)", v[i]);
            dataSetPie.setValue("Deformación_Interna: " + String.valueOf(v[1]) + " (" + porc1 + " %)", v[i]);
            dataSetPie.setValue("Fallamiento_Local: " + String.valueOf(v[2]) + " (" + porc2 + " %)", v[i]);
            dataSetPie.setValue("Subduccion_Placas: " + String.valueOf(v[3]) + " (" + porc3 + " %)", v[i]);
            dataSetPie.setValue("Tectónico_Falla_Local: " + String.valueOf(v[4]) + " (" + porc4 + " %)", v[i]); 
            dataSetPie.setValue("Tectónico_Subducción: " + String.valueOf(v[5]) + " (" + porc5 + " %)", v[i]); 
        }

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart3D("Gráfico cantidad de sismos por tipo de origen", dataSetPie, true, true, false); 
        PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
        pieplot3d.setDepthFactor(0.5); 
        pieplot3d.setStartAngle(290D); 
        pieplot3d.setDirection(Rotation.CLOCKWISE); 
        pieplot3d.setForegroundAlpha(0.5F);
        
        // Mostrar Grafico
        grafPastel.removeAll();
        grafPastel.repaint();
        grafPastel.setLayout(new java.awt.BorderLayout());
        grafPastel.add(new ChartPanel(chart));
        grafPastel.validate();

    }
    
    
    /**
     * Genera el gráfico Histograma
     * @param fecha
     * @param fecha2
     * @return 
     */
//    public static void paintH(Graphics g) {
//        
//        //Variables que indican la cantidad de Sismo por provincia
//        int int_Na = ControladorGraficos.graficoProvincia(ControladorArchivo.listaSismos(), TProvincia.No_Aplica);
//        int int_Sj = ControladorGraficos.graficoProvincia(ControladorArchivo.listaSismos(), TProvincia.San_Jose);
//        int int_Al = ControladorGraficos.graficoProvincia(ControladorArchivo.listaSismos(), TProvincia.Alajuela);
//        int int_Ca = ControladorGraficos.graficoProvincia(ControladorArchivo.listaSismos(), TProvincia.Cartago);
//        int int_He = ControladorGraficos.graficoProvincia(ControladorArchivo.listaSismos(), TProvincia.Heredia);
//        int int_Pu = ControladorGraficos.graficoProvincia(ControladorArchivo.listaSismos(), TProvincia.Puntarenas);
//        int int_Gu = ControladorGraficos.graficoProvincia(ControladorArchivo.listaSismos(), TProvincia.Guanacaste);
//        int int_Li = ControladorGraficos.graficoProvincia(ControladorArchivo.listaSismos(), TProvincia.Limon);
//
//        int valor_Mayor = ControladorGraficos.MayorValor(int_Na, int_Sj, int_Al, int_Ca, int_He, int_Pu, int_Gu, int_Li);
//        int total  = ControladorArchivo.listaSismos().size();
//        
//        //Determina el largo de las Barras
//        int largo_Na = int_Na * 450 / valor_Mayor;
//        int largo_Sj = int_Sj * 450 / valor_Mayor;
//        int largo_Al = int_Al * 450 / valor_Mayor;
//        int largo_Ca = int_Ca * 450 / valor_Mayor;
//        int largo_He = int_He * 450 / valor_Mayor;
//        int largo_Pu = int_Pu * 450 / valor_Mayor;
//        int largo_Gu = int_Gu * 450 / valor_Mayor;
//        int largo_Li = int_Li * 450 / valor_Mayor;
//        
//       
//        if (ControladorArchivo.listaSismos().size() >= 30){
//            g.drawString("35", 25, 170);
//            g.drawString("30", 25, 210);
//            g.drawString("25", 25, 250);
//            g.drawString("20", 25, 290);
//            g.drawString("15", 25, 330);
//            g.drawString("10", 25, 370);
//            g.drawString("5", 30, 410);
//            g.drawString("0", 30, 450);
//        } else if(ControladorArchivo.listaSismos().size() >= 25){
//            g.drawString("30", 25, 170);
//            g.drawString("25", 25, 230);
//            g.drawString("20", 25, 275);
//            g.drawString("15", 25, 315);
//            g.drawString("10", 25, 360);
//            g.drawString("5", 30, 405);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 20){
//            g.drawString("25", 25, 170);
//            g.drawString("20", 25, 230);
//            g.drawString("15", 25, 315);
//            g.drawString("10", 25, 360);
//            g.drawString("5", 30, 405);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 15){
//            g.drawString("20", 25, 170);
//            g.drawString("15", 25, 240);
//            g.drawString("10", 25, 310);
//            g.drawString("5", 30, 380);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 10){
//            g.drawString("15", 25, 170);
//            g.drawString("10", 25, 265);
//            g.drawString("5", 30, 355);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 5){
//            g.drawString("10", 25, 170);
//            g.drawString("5", 30, 310);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 0){
//            g.drawString("5", 30, 170);
//            g.drawString("0", 30, 450);
//        } else {
//            System.out.println("La lista esta vacía");
//        }
//
//        //Se dibuja cada Barra y se le indica un nombre y un número
//        g.setColor(new Color(0,0,0));
//        g.fillRect(75,450-largo_Na/total, 80, largo_Na/total);
//        g.drawString("N/A", 105 , 465);
//        g.drawString(String.valueOf(int_Na), 110, 480);
//
//        g.setColor(new Color(25,25,25));
//        g.fillRect(155,450 - largo_Sj/total, 80, largo_Sj/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("San José", 165, 465);
//        g.drawString(String.valueOf(int_Sj), 185, 480);
//
//        g.setColor(new Color(50,50,50));
//        g.fillRect(235, 450-largo_Al/total ,80, largo_Al/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Alajuela", 245, 465);
//        g.drawString(String.valueOf(int_Al), 260, 480);
//
//        g.setColor(new Color(75,75,75));
//        g.fillRect(315,450-largo_Ca/total,80, largo_Ca/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Cartago", 335, 465);
//        g.drawString(String.valueOf(int_Ca), 350, 480);
//
//        g.setColor(new Color(100,100,100));
//        g.fillRect(395,450-largo_He/total,80, largo_He/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Heredia", 405, 465);
//        g.drawString(String.valueOf(int_He), 420, 480);
//
//        g.setColor(new Color(125,125,125));
//        g.fillRect(475,450-largo_Pu/total,80, largo_Pu/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Puntarenas", 485, 465);
//        g.drawString(String.valueOf(int_Pu), 510, 480);
//
//        g.setColor(new Color(150,150,150));
//        g.fillRect(555,450-largo_Gu/total,80, largo_Gu/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Guanacaste", 560, 465);
//        g.drawString(String.valueOf(int_Gu), 590, 480);
//
//        g.setColor(new Color(200,200,200));
//        g.fillRect(635,450-largo_Li/total,80, largo_Li/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Limón", 660, 465);
//        g.drawString(String.valueOf(int_Li), 670, 480);
//
//        g.drawString("Cantidad de sismos por provincia", 260 , 505);
//
//    }
//          //Gráfico de pastel
//    
//    /**
//     * Genera Gráfico Pastel
//     */
//    public static void graficoPastel(){
//        
//        Graphics g = null;
//        
//        //Variables cantidad de Sismos por Falla
//        int int_ChoqueP = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Choque_Placas);
//        int int_DefInt = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Deformación_Interna);
//        int int_FallaLocal = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Fallamiento_Local);
//        int int_SubPla = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Subduccion_Placas);
//        int int_TFL = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Tectónico_Falla_Local);
//        int int_TSub = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Tectónico_Subducción);
//
//        int total_sismos = int_ChoqueP + int_DefInt + int_FallaLocal + int_SubPla + int_TFL + int_TSub;
//
//        int grados_ChoqueP = int_ChoqueP * 360 / total_sismos;
//        int grados_DefInt = int_DefInt * 360 / total_sismos;
//        int grados_FallaLocal = int_FallaLocal * 360 / total_sismos;
//        int grados_SubPla = int_SubPla * 360 / total_sismos;
//        int grados_TFL = int_TFL * 360 / total_sismos;
//        int grados_TSub = int_TSub * 360 / total_sismos;
//
//        //Se crea el gráfico
//        g.setColor(new Color(255, 0, 0));
//        g.fillArc(25, 80, 200, 200, 0, grados_ChoqueP);
//        g.fillRect(250, 120, 20, 20);
//        g.setColor(Color.BLACK);
//        g.drawString("Color rojo", 275, 135);
//
//        g.setColor(new Color(0, 255, 0));
//        g.fillArc(25, 80, 200, 200, grados_ChoqueP, grados_DefInt);
//        g.fillRect(250, 150, 20, 20);
//        g.setColor(Color.BLACK);
//        g.drawString("Color verde", 275, 165);
//
//        g.setColor(new Color(0, 0, 255));
//        g.fillArc(25, 80, 200, 200, grados_ChoqueP + grados_DefInt, grados_FallaLocal);
//        g.fillRect(250, 180, 20, 20);
//        g.setColor(Color.BLACK);
//        g.drawString("Color azul", 275, 195);
//
//        g.setColor(new Color(255, 255, 50));
//        g.fillArc(25, 80, 200, 200, grados_ChoqueP + grados_DefInt + grados_FallaLocal, grados_SubPla);
//        g.fillRect(250, 210, 20, 20);
//        g.setColor(Color.BLACK);
//        g.drawString("Color amarillo", 275, 225);
//
//        g.setColor(new Color(0, 0, 0));
//        g.fillArc(25, 80, 200, 200, grados_ChoqueP + grados_DefInt + grados_FallaLocal + grados_SubPla, grados_TFL);
//        g.fillRect(250, 240, 20, 20);
//        g.setColor(Color.BLACK);
//        g.drawString("Color negro", 275, 255);
//
//        g.setColor(new Color(56, 7, 52));
//        g.fillArc(25, 80, 200, 200, grados_ChoqueP + grados_DefInt + grados_FallaLocal + grados_SubPla + grados_TFL, grados_TSub);
//        g.fillRect(250, 270, 20, 20);
//        g.setColor(Color.BLACK);
//        g.drawString("Color morado", 275, 285);
//
//        }
//
//    
//    /**
//     * Genera el Gráfico de Barras con cantidad de Sismos por mes
//     * @param fecha 
//     */
//    public static void graficoBarras(Date fecha){
//           //Gráfico de Barras
//           
//        Graphics g = null;
//          
//        ArrayList<Sismo> lista = ControladorArchivo.listaSismos();
//        ArrayList<Sismo> listaS = ControladorGraficos.listaSismoporAnno(lista, fecha.getYear());
//        
//        //Variables por mes
//        int int_enero = 0;
//        int int_febrero = 0;
//        int int_marzo = 0;
//        int int_abril = 0;
//        int int_mayo = 0;
//        int int_junio = 0;
//        int int_julio = 0;
//        int int_agosto = 0;
//        int int_setiembre = 0;
//        int int_octubre = 0;
//        int int_noviembre = 0;
//        int int_diciembre = 0;
//        
//        
//        //Se determina la cantidad de Sismos por mes;
//        if(listaS.isEmpty()){
//            
//            System.out.println("No sismos registrados en ese año");
//            
//        } else {
//            int_enero = ControladorGraficos.graficoSismoPorMes(listaS, 0);
//            int_febrero = ControladorGraficos.graficoSismoPorMes(listaS, 1);
//            int_marzo = ControladorGraficos.graficoSismoPorMes(listaS, 2);
//            int_abril = ControladorGraficos.graficoSismoPorMes(listaS, 3);
//            int_mayo = ControladorGraficos.graficoSismoPorMes(listaS, 4);
//            int_junio = ControladorGraficos.graficoSismoPorMes(listaS, 5);
//            int_julio = ControladorGraficos.graficoSismoPorMes(listaS, 6);
//            int_agosto = ControladorGraficos.graficoSismoPorMes(listaS, 7);
//            int_setiembre = ControladorGraficos.graficoSismoPorMes(listaS, 8);
//            int_octubre = ControladorGraficos.graficoSismoPorMes(listaS, 9);
//            int_noviembre = ControladorGraficos.graficoSismoPorMes(listaS, 10);
//            int_diciembre = ControladorGraficos.graficoSismoPorMes(listaS,11);
//        }
//
//        
//        int mes_Mayor = ControladorGraficos.MayorMes(int_enero, int_febrero, int_marzo, int_abril, int_mayo,int_junio, int_julio,int_agosto,int_setiembre, int_octubre,int_noviembre,int_diciembre);
//        int mes_Menor = ControladorGraficos.MenorMes(int_enero, int_febrero, int_marzo, int_abril, int_mayo,int_junio, int_julio,int_agosto,int_setiembre, int_octubre,int_noviembre,int_diciembre);
//        int total  = ControladorArchivo.listaSismos().size();
//
//         //Determina el largo de las barras
//        int largo_enero = (int_enero * 450) / mes_Mayor;
//        int largo_febrero = int_febrero * 450 / mes_Mayor;
//        int largo_marzo = int_marzo * 450 / mes_Mayor;
//        int largo_abril = int_abril * 450 / mes_Mayor;
//        int largo_mayo = int_mayo * 450 / mes_Mayor;
//        int largo_junio = int_junio * 450 / mes_Mayor;
//        int largo_julio = int_julio * 450 / mes_Mayor;
//        int largo_agosto = int_agosto * 450 / mes_Mayor;
//        int largo_setiembre = int_setiembre * 450 / mes_Mayor;
//        int largo_octubre = int_octubre * 450 / mes_Mayor;
//        int largo_noviembre = int_noviembre * 450 / mes_Mayor;
//        int largo_diciembre = int_diciembre * 450 / mes_Mayor;
//                        
//        if (ControladorArchivo.listaSismos().size() >= 30){
//            g.drawString("35", 25, 170);
//            g.drawString("30", 25, 210);
//            g.drawString("25", 25, 250);
//            g.drawString("20", 25, 290);
//            g.drawString("15", 25, 330);
//            g.drawString("10", 25, 370);
//            g.drawString("5", 30, 410);
//            g.drawString("0", 30, 450);
//        } else if(ControladorArchivo.listaSismos().size() >= 25){
//            g.drawString("30", 25, 170);
//            g.drawString("25", 25, 230);
//            g.drawString("20", 25, 275);
//            g.drawString("15", 25, 315);
//            g.drawString("10", 25, 360);
//            g.drawString("5", 30, 405);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 20){
//            g.drawString("25", 25, 170);
//            g.drawString("20", 25, 230);
//            g.drawString("15", 25, 315);
//            g.drawString("10", 25, 360);
//            g.drawString("5", 30, 405);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 15){
//            g.drawString("20", 25, 170);
//            g.drawString("15", 25, 240);
//            g.drawString("10", 25, 310);
//            g.drawString("5", 30, 380);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 10){
//            g.drawString("15", 25, 170);
//            g.drawString("10", 25, 265);
//            g.drawString("5", 30, 355);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 5){
//            g.drawString("10", 25, 170);
//            g.drawString("5", 30, 310);
//            g.drawString("0", 30, 450);
//        }else if(ControladorArchivo.listaSismos().size() >= 0){
//            g.drawString("5", 30, 170);
//            g.drawString("0", 30, 450);
//        } else {
//            System.out.println("La lista esta vacía");
//        }
//    
//        //Se comienza la creación de las barras y se le asigna un nombre y un número
//        g.setColor(Color.BLACK);
//        g.fillRect(60,450-largo_enero/total, 40, largo_enero/total);
//        g.drawString("Enero", 65 , 465);
//        g.drawString(String.valueOf(int_enero), 75, 480);
//        
//        g.setColor(Color.GREEN);
//        g.fillRect(120,450 - largo_febrero/total, 40, largo_febrero/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Febrero", 120, 465);
//        g.drawString(String.valueOf(int_febrero), 135, 480);
//        
//        g.setColor(Color.BLUE);
//        g.fillRect(180, 450-largo_marzo/total ,40, largo_marzo/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Marzo", 185, 465);
//        g.drawString(String.valueOf(int_marzo), 195, 480);
//        
//        g.setColor(Color.PINK);
//        g.fillRect(240,450-largo_abril/total,40, largo_abril/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Abril", 250, 465);
//        g.drawString(String.valueOf(int_abril), 260, 480);
//        
//        g.setColor(Color.YELLOW);
//        g.fillRect(300,450-largo_mayo/total,40, largo_mayo/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Mayo", 305, 465);
//        g.drawString(String.valueOf(int_mayo), 315, 480);
//        
//        g.setColor(Color.RED);
//        g.fillRect(360,450-largo_junio/total,40, largo_junio/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Junio", 365, 465);
//        g.drawString(String.valueOf(int_junio), 375, 480);
//        
//        g.setColor(Color.GRAY);
//        g.fillRect(420,450-largo_julio/total,40, largo_julio/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Julio", 425, 465);
//        g.drawString(String.valueOf(int_julio), 435, 480);
//        
//        g.setColor(Color.ORANGE);
//        g.fillRect(480,450-largo_agosto/total,40, largo_agosto/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Agosto", 480, 465);
//        g.drawString(String.valueOf(int_agosto), 495, 480);
//        
//        g.setColor(Color.MAGENTA);
//        g.fillRect(540,450-largo_setiembre/total,40, largo_setiembre/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Setiembre", 530, 465);
//        g.drawString(String.valueOf(int_setiembre), 555, 480);
//        
//        g.setColor(Color.DARK_GRAY);
//        g.fillRect(600,450-largo_octubre/total,40, largo_octubre/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Octubre", 600, 465);
//        g.drawString(String.valueOf(int_octubre), 615, 480);
//        
//        g.setColor(Color.CYAN);
//        g.fillRect(660,450-largo_noviembre/total,40, largo_noviembre/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Noviembre", 650, 465);
//        g.drawString(String.valueOf(int_noviembre), 675, 480);
//        
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillRect(720,450-largo_diciembre/total,40, largo_diciembre/total);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Diciembre", 715, 465);
//        g.drawString(String.valueOf(int_diciembre), 735, 480);
//
//        g.drawString("Cantidad de sismos por mes en un año", 260 , 505);
//
//        }
//    
//    
//    /**
//     * Se crea el Gráfico Tabular para determinar cantidad de sismos por Magnitud
//     */
    
//    public void    paint(){
//        
//            Graphics g = null;
//
//                 //Gráfico Tabular Magnitud
//
//            //Variables que determinan la cantidad de sismos por tipo de magnitud
//            int int_Micro = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Micro");
//            int int_Menor1 = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Menor1");
//            int int_Menor2 = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Menor2");
//            int int_Ligero = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Ligero");
//            int int_Moderado = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Moderado");
//            int int_Fuerte = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Fuerte");
//            int int_Mayor = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Mayor");
//            int int_Gran1 = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Gran1");
//            int int_Gran2 = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Gran2");
//            int int_Epico = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Epico");
//
//
//            //Se crean la tabla y se les da un nombre y un número
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,100, 300, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Clasificación de sismos por magnitud ", 240, 120);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,130, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Magnitud ", 225, 150);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,130, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Descripción", 320, 150);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,130, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Cantidad", 425, 150);
//
//
//            //INFO
//            g.setColor(Color.BLACK);
//            g.drawRect(200,160, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Menos de 2.0", 210, 180);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,160, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Micro", 335, 180);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,160, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Micro), 440, 180);
//
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,190, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("2.0 - 2.9", 230, 210);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,190, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Menor", 335, 210);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,190, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Menor1), 440, 210);
//
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,220, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("3.0 - 3.9", 230, 240);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,220, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Menor", 335, 240);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,220, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Menor2), 440, 240);
//
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,250, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("4.0 - 4.9", 230, 270);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,250, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Ligero", 335, 270);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,250, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Ligero), 440, 270);
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,280, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("5.0 -5.9", 230, 300);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,280, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Moderado", 325, 300);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,280, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Moderado), 440, 300);
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,310, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("6.0 - 6.9", 230, 330);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,310, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Fuerte", 335, 330);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,310, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Fuerte), 440, 330);
//
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,340, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("7.0 - 7.9", 230, 360);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,340, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Mayor", 335, 360);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,340, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Mayor), 440, 360);
//
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,370, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("8.0 - 8.9", 230, 390);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,370, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Gran", 335, 390);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,370, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Gran1), 440, 390);
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,400, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("9.0 - 9.9", 230, 420);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,400, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Gran", 335, 420);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,400, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Gran2), 440, 420);
//
//
//            g.setColor(Color.BLACK);
//            g.drawRect(200,430, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("10.0 +", 240, 450);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(300,430, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString("Épico", 335, 450);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(400,430, 100, 30);
//            g.setColor(new Color(0,0,0));
//            g.drawString(String.valueOf(int_Epico), 440, 450);
//        
//        
//  
//    } 
//    
//    public void graficoTabularMagnitud(Dimension d){
//        paint();
//    }
////    
////    
////    /**
////     * Gráfico Tabular que indica la cantidad de Sismos en un rango de fechas determinado
////     * @param fecha
////     * @param fecha2 
////     */
//    public void graficoTabFecha(Date fecha, Date fecha2, JPanel panel){
//          //Gráfico Tabular por rango de Fecha
//          
//          Graphics g = null;
//          
//        int int_sisFecha = ControladorGraficos.rangoFecha(ControladorArchivo.listaSismos(), fecha, fecha2);
//          
//        //Se crea la tabla
//        g.setColor(Color.BLACK);
//        g.drawRect(200,100, 300, 30);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Clasificación de sismos por rango de fecha ", 240, 120);
//        
//        g.setColor(Color.BLACK);
//        g.drawRect(200,130, 200, 30);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Fecha ", 285, 150);
//        
//        g.setColor(Color.BLACK);
//        g.drawRect(400,130, 100, 30);
//        g.setColor(new Color(0,0,0));
//        g.drawString("Cantidad", 430, 150);
//        
//        
//        
//        
//        g.setColor(Color.BLACK);
//        g.drawRect(200,160, 200, 30);
//        g.setColor(new Color(0,0,0));
//        g.drawString(String.valueOf(fecha), 215, 180);
//        
//        g.setColor(Color.BLACK);
//        g.drawRect(200,190, 200, 30);
//        g.setColor(new Color(0,0,0));
//        g.drawString(String.valueOf(fecha2), 215, 210);
//        
//        g.setColor(Color.BLACK);
//        g.drawRect(400,160, 100, 60);
//        g.setColor(new Color(0,0,0));
//        g.drawString(String.valueOf(int_sisFecha), 445, 195);       
//        
//    }

}
//
