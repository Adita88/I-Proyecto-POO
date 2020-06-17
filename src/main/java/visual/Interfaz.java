/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;


import controlador.ControladorArchivo;
import controlador.ControladorGraficos;
import controlador.Sistema;
import modelo.TProvincia;
import java.awt.*;
import javax.swing.JPanel;
import modelo.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
//import com.mapbox.api.isochrone;

/**
 *
 * @author David B
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        setLocationRelativeTo(null);
        ConfirmarSalida.setLocationRelativeTo(null);
        elSistema= new Sistema();
        asignarValoresListas();
        ocultarPanelesExceptoEste(Inicio);
        setModeloTablaTodosSismos();
        Grafico migrafico = new Grafico();
        setModelojTabporMag();
        setModelojTableSismoporFecha();
        
    }
    
    public Sistema elSistema;
    DefaultTableModel tabla1 = new DefaultTableModel();
    DefaultTableModel tabla2 = new DefaultTableModel();
    DefaultTableModel tabla3 = new DefaultTableModel();
    
    private void asignarValoresListas(){
        inputOrigenFallaLista_NuevoSismo.removeAllItems();
        inputLugarLista_NuevoSismo.removeAllItems();
        inputProvinciaLista_NuevoSismo.removeAllItems();
        
        //inputListaOrigen_RegistroSismos_B.removeAllItems();
        inputListaProvincia_RegistroSismos_A.removeAllItems();
        
        
        for(TFalla i : TFalla.values()){
            inputOrigenFallaLista_NuevoSismo.addItem(i.toString());
        }
        for(TLugar i : TLugar.values()){
            inputLugarLista_NuevoSismo.addItem(i.toString());
        }
        for(TProvincia i : TProvincia.values()){
            inputProvinciaLista_NuevoSismo.addItem(i.toString());
        }
        for(TFalla i : TFalla.values()){
            //inputListaOrigen_RegistroSismos_B.addItem(i.toString());
        }
        for(TProvincia i : TProvincia.values()){
            inputListaProvincia_RegistroSismos_A.addItem(i.toString());
        } 
    }
    
    
    private void ocultarPanelesExceptoEste(JPanel panel, boolean ... limpiarPanel){
        boolean flagLimpiarPanel = (limpiarPanel.length >= 1) ? limpiarPanel[0] : false;
        
        for(Component componente : Paneles.getComponents()) 
            if(componente instanceof JPanel)
                componente.setVisible(false);
        
        if(flagLimpiarPanel) limpiarVentana(panel);
        
        panel.setVisible(true);
    }
    
    private void limpiarVentana(JPanel panel){
        for(Component componente : panel.getComponents()){   
            if(componente instanceof JTextField){
                JTextField comp = (JTextField) componente;
                comp.setText("");
            }
            else if (componente instanceof JComboBox){
                JComboBox comp = (JComboBox) componente;
                comp.setSelectedIndex(0);
            }
            else if(componente instanceof JFormattedTextField){
                JFormattedTextField comp = (JFormattedTextField) componente;
                comp.setText("");
            }
        }
    }
    
    private int cerrarPrograma(){
        ConfirmarSalida.setVisible(true);
        Paneles.setVisible(false);
        return 0;
    }
    
    private void setModeloTablaTodosSismos(){
        String[] header = {"#","Fecha y hora",
            "Profundidad","Origen","Detalle Falla","Magnitud", "latitud",
            "longitud", "Localizacion", "Lugar","Provincia"};
        tabla1.setColumnIdentifiers(header);
        TablaTodosSismos.setModel(tabla1);
    }
    
    private void cargarValoresATablaSismos(ArrayList<Sismo> listaSismos){
        for(int i=0;i<tabla1.getRowCount();i++) tabla1.removeRow(i);
        Object[] datos= new Object[tabla1.getColumnCount()];
        int i=1;
        for (Sismo unSismo : listaSismos){
            datos[0]=i;
            datos[1]=  unSismo.getMomentoExacto().toString();
            datos[2]=  unSismo.getProfundidad();
            datos[3]=  unSismo.getOrigenFalla();
            datos[4]=  unSismo.getDetalleFalla();
            datos[5]=  unSismo.getMagnitud();
            datos[6]=  unSismo.getLatitud();
            datos[7]=  unSismo.getLongitud();
            datos[8]=  unSismo.getDescripcionDetallada();
            datos[9]=  unSismo.getLugar();
            datos[10]= unSismo.getProvincia();
            i++;
            tabla1.addRow(datos);
        }
        TablaTodosSismos.setModel(tabla1);
    }
    

    class FormatoDia extends MaskFormatter{

        public FormatoDia() throws ParseException{
            super ("##/##/##");
        }

        private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

        @Override
        public Object stringToValue(String text) throws ParseException{
            return formato.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException{
            if (value instanceof Date)
                return formato.format((Date)value);
            return formato.format(new Date());
        }
    }
    
    class FormatoHora extends MaskFormatter{

        public FormatoHora() throws ParseException{
            super ("##:##:##");
        }
        
        private SimpleDateFormat formato = new SimpleDateFormat("kk:mm:ss");

        @Override
        public Object stringToValue(String text) throws ParseException{
            return formato.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException{
            if (value instanceof Date)
                return formato.format((Date)value);
            return formato.format(new Date());
        }
    }
    
    class FormatoAnnio extends MaskFormatter{

        public FormatoAnnio() throws ParseException{
            super ("####");
        }

        private SimpleDateFormat formato = new SimpleDateFormat("yyyy");

        @Override
        public Object stringToValue(String text) throws ParseException{
            return formato.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException{
            if (value instanceof Date)
                return formato.format((Date)value);
            return formato.format(new Date());
        }
    }
    
    private JFormattedTextField getFormatedTextHora(){
        try{return new javax.swing.JFormattedTextField(new FormatoHora());
        }catch(ParseException error){
            return new javax.swing.JFormattedTextField();
        }
    }
    
    private JFormattedTextField getFormatedTextDia(){
        try{return new javax.swing.JFormattedTextField(new FormatoDia());
        }catch(ParseException error){
            return new javax.swing.JFormattedTextField();
        }
    }
    
    private JFormattedTextField getFormatedTextAnnio(){
        try{return new javax.swing.JFormattedTextField(new FormatoAnnio());
        }catch(ParseException error){
            return new javax.swing.JFormattedTextField();
        }
    }
    
    private void escribirDatos_DetalleSismo(Sismo unSismo){
        DetalleSismo_DescripcionDetallada.append(unSismo.getDescripcionDetallada());
        DetalleSismo_detalleFalla.setText(unSismo.getDetalleFalla());
        DetalleSismo_lugar.setText(unSismo.getLugar().toString());
        DetalleSismo_magnitud.setText(""+unSismo.getMagnitud());
        DetalleSismo_momentoExacto.setText(unSismo.getMomentoExacto().toString());
        DetalleSismo_profundidad.setText(""+unSismo.getProfundidad());
        DetalleSismo_provincia.setText(unSismo.getProvincia().toString());
        DetalleSismo_ubicacionExacta.setText("("+unSismo.getLatitud()+", "+unSismo.getLongitud()+")");
        DetalleSismo_origenFalla.setText(unSismo.getOrigenFalla().toString());
    }
    
    private void setModelojTabporMag(){
        String[] header = {"Magnitud ", "Descripción", "Cantidad"};
        tabla2.setColumnIdentifiers(header);
        jTabporMag.setModel(tabla2);
    }
    
    private void cargarValoresATablaGrafTabMAg(ArrayList lista){
        for(int i=0;i<tabla2.getRowCount();i++) tabla2.removeRow(i);
        Object[] datos= new Object[tabla2.getColumnCount()];
        
        int i=0;
        
            for (Object j : lista){
                if (i < 2){
                    datos[i]=j;
                    i++;
                } else {
                    datos[i]=j;
                    tabla2.addRow(datos); 
                    i = 0;
                }
        }
        jTabporMag.setModel(tabla2);
    }
    
    private void setModelojTableSismoporFecha(){
        String[] header = {"Fecha ", "Cantidad"};
        tabla3.setColumnIdentifiers(header);
        jTableSismoporFecha.setModel(tabla3);
    }
    
    private void cargarValoresARangoFechas(ArrayList lista){
        for(int i=0;i<tabla3.getRowCount();i++) tabla3.removeRow(i);
        Object[] datos= new Object[tabla3.getColumnCount()];
        
        int i=0;
        
            for (Object j : lista){
                if (i < 1){
                    datos[i]=j;
                    i++;
                } else {
                    datos[i]=j;
                    tabla3.addRow(datos); 
                    i = 0;
                }
        }
        jTableSismoporFecha.setModel(tabla3);
    }
    
    private boolean comprobarLatLong_NuevoSismo(){
        boolean banderaError=false;        
        try{
            String stringLong= inputLongitudFormated_NuevoSismo_Ubicacion.getText();
            double longitud= Double.parseDouble(stringLong);
            if(longitud< -180||longitud> 180) throw new NumberFormatException();
        } catch (NumberFormatException error){
            JOptionPane.showMessageDialog(MensajeError_NuevoSismo, "Longitud no valida.");
            banderaError=true;
        }
        
        try{
            String stringLat= inputLatitudFormated_NuevoSismo_Ubicacion.getText();
            double latitud= Double.parseDouble(stringLat);
            if(latitud< -90||latitud> 90) throw new NumberFormatException();
        } catch (NumberFormatException error){
            JOptionPane.showMessageDialog(MensajeError_NuevoSismo, "Latitud no valida.");
            banderaError=true;
        }
        return !banderaError;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConfirmarSalida = new javax.swing.JDialog();
        botonAceptar_ConfirmarSalida = new javax.swing.JButton();
        botonCancelar_ConfirmarSalida = new javax.swing.JButton();
        textConfirmarSalida_ConfirmarSalida = new javax.swing.JLabel();
        MensajeError_NuevoSismo = new javax.swing.JFrame();
        Paneles = new javax.swing.JLayeredPane();
        Inicio = new javax.swing.JPanel();
        botonNuevoSismo_Inicio = new javax.swing.JButton();
        botonRegistroSismos_Inicio = new javax.swing.JButton();
        textTitulo_Inicio = new javax.swing.JLabel();
        botonSalir_Inicio = new javax.swing.JButton();
        detalle_Inicio = new javax.swing.JTextArea();
        VentanaIntermedia = new javax.swing.JPanel();
        textTitulo_VentanaIntermedia = new javax.swing.JLabel();
        botonRegresar_VentanaIntermedia = new javax.swing.JButton();
        botonSalir_NuevoSismo_Ubicacion1 = new javax.swing.JButton();
        botonNuevoSismo_VentanaIntermedia = new javax.swing.JButton();
        panelScroll_VentanaInetermediaria = new javax.swing.JScrollPane();
        TablaTodosSismos = new javax.swing.JTable();
        botonNuevoSismo_VentanaIntermedia1 = new javax.swing.JButton();
        VentanaIntermedia_DetalleSismo = new javax.swing.JPanel();
        textTitulo_NuevoSismo1 = new javax.swing.JLabel();
        textMomentoExacto_NuevoSismo1 = new javax.swing.JLabel();
        textProfundidad_NuevoSismo1 = new javax.swing.JLabel();
        DetalleSismo_origenFalla = new javax.swing.JTextField();
        textOrigenFalla_NuevoSismo1 = new javax.swing.JLabel();
        textDetalleFalla_NuevoSismo1 = new javax.swing.JLabel();
        DetalleSismo_detalleFalla = new javax.swing.JTextField();
        textMagnitud_NuevoSismo1 = new javax.swing.JLabel();
        DetalleSismo_magnitud = new javax.swing.JTextField();
        textLugar_NuevoSismo1 = new javax.swing.JLabel();
        textProvincia_NuevoSismo1 = new javax.swing.JLabel();
        textUbicacion_NuevoSismo1 = new javax.swing.JLabel();
        textDescripcion_NuevoSismo1 = new javax.swing.JLabel();
        scrollPaneDescripcionDetallada_NuevoSismo1 = new javax.swing.JScrollPane();
        DetalleSismo_DescripcionDetallada = new javax.swing.JTextArea();
        botonRegresar_NuevoSismo1 = new javax.swing.JButton();
        botonSalir_NuevoSismo1 = new javax.swing.JButton();
        DetalleSismo_momentoExacto = new javax.swing.JTextField();
        DetalleSismo_lugar = new javax.swing.JTextField();
        DetalleSismo_provincia = new javax.swing.JTextField();
        DetalleSismo_ubicacionExacta = new javax.swing.JTextField();
        DetalleSismo_profundidad = new javax.swing.JTextField();
        NuevoSismo = new javax.swing.JPanel();
        textTitulo_NuevoSismo = new javax.swing.JLabel();
        textMomentoExacto_NuevoSismo = new javax.swing.JLabel();
        botonEscogerFecha_NuevoSismo = new javax.swing.JButton();
        textProfundidad_NuevoSismo = new javax.swing.JLabel();
        inputProfundidad_NuevoSismo = new javax.swing.JTextField();
        validacionProfundidad_NuevoSismo = new javax.swing.JLabel();
        textOrigenFalla_NuevoSismo = new javax.swing.JLabel();
        inputOrigenFallaLista_NuevoSismo = new javax.swing.JComboBox<>();
        textDetalleFalla_NuevoSismo = new javax.swing.JLabel();
        inputDetalleFalla_NuevoSismo = new javax.swing.JTextField();
        textMagnitud_NuevoSismo = new javax.swing.JLabel();
        inputMagnitud_NuevoSismo = new javax.swing.JTextField();
        validacionMagnitud_NuevoSismo = new javax.swing.JLabel();
        textLugar_NuevoSismo = new javax.swing.JLabel();
        inputLugarLista_NuevoSismo = new javax.swing.JComboBox<>();
        textProvincia_NuevoSismo = new javax.swing.JLabel();
        inputProvinciaLista_NuevoSismo = new javax.swing.JComboBox<>();
        textUbicacion_NuevoSismo = new javax.swing.JLabel();
        botonUbicacion_NuevoSismo = new javax.swing.JButton();
        textDescripcion_NuevoSismo = new javax.swing.JLabel();
        scrollPaneDescripcionDetallada_NuevoSismo = new javax.swing.JScrollPane();
        inputDescripcionDetallada_NuevoSismo = new javax.swing.JTextArea();
        botonAceptar_NuevoSismo = new javax.swing.JButton();
        botonRegresar_NuevoSismo = new javax.swing.JButton();
        botonSalir_NuevoSismo = new javax.swing.JButton();
        NuevoSismo_Fecha = new javax.swing.JPanel();
        textTitulo_NuevoSismo_Fecha = new javax.swing.JLabel();
        textMomentoExacto_NuevoSismo_Fecha = new javax.swing.JLabel();
        botonEscogerFecha_NuevoSismo_Fecha = new javax.swing.JButton();
        textDia_NuevoSismo_Fecha = new javax.swing.JLabel();
        inputDiaFormated_NuevoSismo_Fecha = getFormatedTextDia();
        textHora_NuevoSismo_Fecha = new javax.swing.JLabel();
        inputHoraFormated_NuevoSismo_Fecha = getFormatedTextHora();
        botonAceptar_NuevoSismo_Fecha = new javax.swing.JButton();
        botonSalir_NuevoSismo_Fecha = new javax.swing.JButton();
        NuevoSismo_UbicacionMapa = new javax.swing.JPanel();
        textTitulo_NuevoSismo_Ubicacion = new javax.swing.JLabel();
        textLatitud_NuevoSismo_Ubicacion = new javax.swing.JLabel();
        inputLatitudFormated_NuevoSismo_Ubicacion = new javax.swing.JFormattedTextField();
        textLongitud_NuevoSismo_Ubicacion = new javax.swing.JLabel();
        inputLongitudFormated_NuevoSismo_Ubicacion = new javax.swing.JFormattedTextField();
        botonAceptar_NuevoSismo_Ubicacion = new javax.swing.JButton();
        botonSalir_NuevoSismo_Ubicacion = new javax.swing.JButton();
        mapa_NuevoSismo_Ubicacion = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        RegistroSismos = new javax.swing.JPanel();
        textTitulo_RegistroSismos = new javax.swing.JLabel();
        botonAceptar_RegistroSismos = new javax.swing.JButton();
        botonSalir_RegistroSismos = new javax.swing.JButton();
        textA1_RegistroSismos = new javax.swing.JLabel();
        textA2_RegistroSismos = new javax.swing.JLabel();
        textA3_RegistroSismos = new javax.swing.JLabel();
        botonSismosMagnitudPorProvincia_RegistroSismos = new javax.swing.JButton();
        textB1_RegistroSismos = new javax.swing.JLabel();
        textB2_RegistroSismos = new javax.swing.JLabel();
        textB3_RegistroSismos = new javax.swing.JLabel();
        botonCantSismosPorOrigen_RegistroSismos = new javax.swing.JButton();
        textC1_RegistroSismos = new javax.swing.JLabel();
        textC2_RegistroSismos = new javax.swing.JLabel();
        textC3_RegistroSismos = new javax.swing.JLabel();
        botonSismosEntreFechas_RegistroSismos = new javax.swing.JButton();
        textD1_RegistroSismos = new javax.swing.JLabel();
        textD2_RegistroSismos = new javax.swing.JLabel();
        textD3_RegistroSismos = new javax.swing.JLabel();
        botonCantSismosPorMesEnAño_RegistroSismos = new javax.swing.JButton();
        textE1_RegistroSismos = new javax.swing.JLabel();
        textE2_RegistroSismos = new javax.swing.JLabel();
        textE3_RegistroSismos = new javax.swing.JLabel();
        botonClasificacionSismosPorMagnitud_RegistroSismos = new javax.swing.JButton();
        RegistroSismos_A = new javax.swing.JPanel();
        textTitulo_RegistroSismos_A = new javax.swing.JLabel();
        textProvincia_RegistroSismos_A = new javax.swing.JLabel();
        botonRegresar_RegistroSismos_A = new javax.swing.JButton();
        botonSalir_RegistroSismos_A = new javax.swing.JButton();
        panel_RegistroSismos_A = new javax.swing.JPanel();
        inputListaProvincia_RegistroSismos_A = new javax.swing.JComboBox<>();
        botonMostrar_RegistroSismos_A = new javax.swing.JButton();
        RegistroSismos_B = new javax.swing.JPanel();
        textTitulo_RegistroSismos_B = new javax.swing.JLabel();
        textProvincia_RegistroSismos_B = new javax.swing.JLabel();
        botonRegresar_RegistroSismos_B = new javax.swing.JButton();
        botonSalir_RegistroSismos_B = new javax.swing.JButton();
        panel_RegistroSismos_B = new javax.swing.JPanel();
        RegistroSismos_C = new javax.swing.JPanel();
        textTitulo_RegistroSismos_C = new javax.swing.JLabel();
        botonRegresar_RegistroSismos_C = new javax.swing.JButton();
        botonSalir_RegistroSismos_C = new javax.swing.JButton();
        panel_RegistroSismos_C = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSismoporFecha = new javax.swing.JTable();
        textDesde_RegistroSismos_C = new javax.swing.JLabel();
        inputDia1Formated_RegistroSismos_C = getFormatedTextDia();
        textHasta_RegistroSismos_C = new javax.swing.JLabel();
        inputDia2Formated_RegistroSismos_C = getFormatedTextDia();
        botonMostrar_RegistroSismos_C = new javax.swing.JButton();
        RegistroSismos_D = new javax.swing.JPanel();
        textTitulo_RegistroSismos_D = new javax.swing.JLabel();
        textAño_RegistroSismos_D = new javax.swing.JLabel();
        botonRegresar_RegistroSismos_D = new javax.swing.JButton();
        botonSalir_RegistroSismos_D = new javax.swing.JButton();
        panel_RegistroSismos_D = new javax.swing.JPanel();
        botonMostrar_RegistroSismos_D = new javax.swing.JButton();
        inputAñoFormated_RegistroSismos_D = getFormatedTextAnnio();
        RegistroSismos_E = new javax.swing.JPanel();
        textTitulo_RegistroSismos_E = new javax.swing.JLabel();
        botonRegresar_RegistroSismos_E = new javax.swing.JButton();
        botonSalir_RegistroSismos_E = new javax.swing.JButton();
        panel_RegistroSismos_E = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabporMag = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();

        ConfirmarSalida.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        ConfirmarSalida.setTitle("Confirmación de Salida");
        ConfirmarSalida.setAlwaysOnTop(true);
        ConfirmarSalida.setMinimumSize(new java.awt.Dimension(306, 157));
        ConfirmarSalida.setSize(new java.awt.Dimension(306, 157));
        ConfirmarSalida.setType(java.awt.Window.Type.POPUP);

        botonAceptar_ConfirmarSalida.setText("Aceptar");
        botonAceptar_ConfirmarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptar_ConfirmarSalidaActionPerformed(evt);
            }
        });

        botonCancelar_ConfirmarSalida.setText("Cancelar");
        botonCancelar_ConfirmarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelar_ConfirmarSalidaActionPerformed(evt);
            }
        });

        textConfirmarSalida_ConfirmarSalida.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textConfirmarSalida_ConfirmarSalida.setText("Desea terminar el programa?");

        javax.swing.GroupLayout ConfirmarSalidaLayout = new javax.swing.GroupLayout(ConfirmarSalida.getContentPane());
        ConfirmarSalida.getContentPane().setLayout(ConfirmarSalidaLayout);
        ConfirmarSalidaLayout.setHorizontalGroup(
            ConfirmarSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmarSalidaLayout.createSequentialGroup()
                .addGroup(ConfirmarSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConfirmarSalidaLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(botonAceptar_ConfirmarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(botonCancelar_ConfirmarSalida))
                    .addGroup(ConfirmarSalidaLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(textConfirmarSalida_ConfirmarSalida)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        ConfirmarSalidaLayout.setVerticalGroup(
            ConfirmarSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfirmarSalidaLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(textConfirmarSalida_ConfirmarSalida)
                .addGap(18, 18, 18)
                .addGroup(ConfirmarSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar_ConfirmarSalida)
                    .addComponent(botonCancelar_ConfirmarSalida))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout MensajeError_NuevoSismoLayout = new javax.swing.GroupLayout(MensajeError_NuevoSismo.getContentPane());
        MensajeError_NuevoSismo.getContentPane().setLayout(MensajeError_NuevoSismoLayout);
        MensajeError_NuevoSismoLayout.setHorizontalGroup(
            MensajeError_NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        MensajeError_NuevoSismoLayout.setVerticalGroup(
            MensajeError_NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Sismos");
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(750, 380));
        setName("Base"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Paneles.setBackground(new java.awt.Color(0, 0, 0));
        Paneles.setMaximumSize(new java.awt.Dimension(750, 380));
        Paneles.setMinimumSize(new java.awt.Dimension(750, 380));
        Paneles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Inicio.setBackground(new java.awt.Color(255, 255, 255));
        Inicio.setMaximumSize(new java.awt.Dimension(750, 510));
        Inicio.setMinimumSize(new java.awt.Dimension(750, 510));
        Inicio.setPreferredSize(new java.awt.Dimension(750, 510));

        botonNuevoSismo_Inicio.setBackground(new java.awt.Color(230, 57, 70));
        botonNuevoSismo_Inicio.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonNuevoSismo_Inicio.setForeground(new java.awt.Color(255, 255, 255));
        botonNuevoSismo_Inicio.setText("Registro de Sismos");
        botonNuevoSismo_Inicio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonNuevoSismo_Inicio.setBorderPainted(false);
        botonNuevoSismo_Inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonNuevoSismo_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoSismo_InicioActionPerformed(evt);
            }
        });

        botonRegistroSismos_Inicio.setBackground(new java.awt.Color(29, 53, 87));
        botonRegistroSismos_Inicio.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonRegistroSismos_Inicio.setForeground(new java.awt.Color(255, 255, 255));
        botonRegistroSismos_Inicio.setText("Analisis de Sismos");
        botonRegistroSismos_Inicio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonRegistroSismos_Inicio.setBorderPainted(false);
        botonRegistroSismos_Inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegistroSismos_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistroSismos_InicioActionPerformed(evt);
            }
        });

        textTitulo_Inicio.setBackground(new java.awt.Color(238, 108, 77));
        textTitulo_Inicio.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        textTitulo_Inicio.setForeground(new java.awt.Color(29, 53, 87));
        textTitulo_Inicio.setText("Inicio  ");

        botonSalir_Inicio.setBackground(new java.awt.Color(255, 0, 0));
        botonSalir_Inicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botonSalir_Inicio.setForeground(new java.awt.Color(255, 255, 255));
        botonSalir_Inicio.setText("Salir");
        botonSalir_Inicio.setBorderPainted(false);
        botonSalir_Inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSalir_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_InicioActionPerformed(evt);
            }
        });

        detalle_Inicio.setEditable(false);
        detalle_Inicio.setColumns(20);
        detalle_Inicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        detalle_Inicio.setForeground(new java.awt.Color(29, 53, 87));
        detalle_Inicio.setRows(5);
        detalle_Inicio.setText("En este sistema se pueden registrar\nnuevos sismos, ver el registro que\nse mantiene de estos y analizar los\nsismos almacenados");
        detalle_Inicio.setAutoscrolls(false);
        detalle_Inicio.setBorder(null);
        detalle_Inicio.setCaretColor(new java.awt.Color(61, 90, 128));
        detalle_Inicio.setFocusable(false);

        javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(Inicio);
        Inicio.setLayout(InicioLayout);
        InicioLayout.setHorizontalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTitulo_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detalle_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonNuevoSismo_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRegistroSismos_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(InicioLayout.createSequentialGroup()
                .addGap(650, 650, 650)
                .addComponent(botonSalir_Inicio))
        );
        InicioLayout.setVerticalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addComponent(textTitulo_Inicio)
                        .addGap(16, 16, 16)
                        .addComponent(detalle_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addComponent(botonNuevoSismo_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(botonRegistroSismos_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(botonSalir_Inicio)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        Paneles.add(Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        VentanaIntermedia.setBackground(new java.awt.Color(230, 57, 70));
        VentanaIntermedia.setMaximumSize(new java.awt.Dimension(750, 510));
        VentanaIntermedia.setMinimumSize(new java.awt.Dimension(750, 510));

        textTitulo_VentanaIntermedia.setBackground(new java.awt.Color(29, 53, 87));
        textTitulo_VentanaIntermedia.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        textTitulo_VentanaIntermedia.setForeground(new java.awt.Color(241, 250, 238));
        textTitulo_VentanaIntermedia.setText(" Nuevo Sismo - Definir Ubicación ");
        textTitulo_VentanaIntermedia.setOpaque(true);

        botonRegresar_VentanaIntermedia.setText("Regresar");
        botonRegresar_VentanaIntermedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar_VentanaIntermediaActionPerformed(evt);
            }
        });

        botonSalir_NuevoSismo_Ubicacion1.setText("Salir");
        botonSalir_NuevoSismo_Ubicacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_NuevoSismo_Ubicacion1ActionPerformed(evt);
            }
        });

        botonNuevoSismo_VentanaIntermedia.setBackground(new java.awt.Color(29, 53, 87));
        botonNuevoSismo_VentanaIntermedia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonNuevoSismo_VentanaIntermedia.setForeground(new java.awt.Color(255, 255, 255));
        botonNuevoSismo_VentanaIntermedia.setText("Nuevo Sismo");
        botonNuevoSismo_VentanaIntermedia.setBorderPainted(false);
        botonNuevoSismo_VentanaIntermedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoSismo_VentanaIntermediaActionPerformed(evt);
            }
        });

        TablaTodosSismos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelScroll_VentanaInetermediaria.setViewportView(TablaTodosSismos);

        botonNuevoSismo_VentanaIntermedia1.setBackground(new java.awt.Color(29, 53, 87));
        botonNuevoSismo_VentanaIntermedia1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonNuevoSismo_VentanaIntermedia1.setForeground(new java.awt.Color(255, 255, 255));
        botonNuevoSismo_VentanaIntermedia1.setText("Detalles de Sismo");
        botonNuevoSismo_VentanaIntermedia1.setBorderPainted(false);
        botonNuevoSismo_VentanaIntermedia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoSismo_VentanaIntermedia1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VentanaIntermediaLayout = new javax.swing.GroupLayout(VentanaIntermedia);
        VentanaIntermedia.setLayout(VentanaIntermediaLayout);
        VentanaIntermediaLayout.setHorizontalGroup(
            VentanaIntermediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaIntermediaLayout.createSequentialGroup()
                .addComponent(textTitulo_VentanaIntermedia, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(VentanaIntermediaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VentanaIntermediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaIntermediaLayout.createSequentialGroup()
                        .addComponent(botonNuevoSismo_VentanaIntermedia, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonNuevoSismo_VentanaIntermedia1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonRegresar_VentanaIntermedia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonSalir_NuevoSismo_Ubicacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(VentanaIntermediaLayout.createSequentialGroup()
                        .addComponent(panelScroll_VentanaInetermediaria, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE))))
        );
        VentanaIntermediaLayout.setVerticalGroup(
            VentanaIntermediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaIntermediaLayout.createSequentialGroup()
                .addComponent(textTitulo_VentanaIntermedia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelScroll_VentanaInetermediaria, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VentanaIntermediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevoSismo_VentanaIntermedia)
                    .addComponent(botonRegresar_VentanaIntermedia)
                    .addComponent(botonSalir_NuevoSismo_Ubicacion1)
                    .addComponent(botonNuevoSismo_VentanaIntermedia1))
                .addGap(141, 141, 141))
        );

        Paneles.add(VentanaIntermedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        VentanaIntermedia_DetalleSismo.setBackground(new java.awt.Color(230, 57, 70));
        VentanaIntermedia_DetalleSismo.setMaximumSize(new java.awt.Dimension(750, 510));
        VentanaIntermedia_DetalleSismo.setMinimumSize(new java.awt.Dimension(750, 510));
        VentanaIntermedia_DetalleSismo.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_NuevoSismo1.setBackground(new java.awt.Color(29, 53, 87));
        textTitulo_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        textTitulo_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textTitulo_NuevoSismo1.setText("  Detalle de Sismo  ");
        textTitulo_NuevoSismo1.setOpaque(true);

        textMomentoExacto_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textMomentoExacto_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textMomentoExacto_NuevoSismo1.setText("Momento Exacto:");

        textProfundidad_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textProfundidad_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textProfundidad_NuevoSismo1.setText("Profundidad:");

        DetalleSismo_origenFalla.setEditable(false);
        DetalleSismo_origenFalla.setFocusable(false);
        DetalleSismo_origenFalla.setMaximumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_origenFalla.setMinimumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_origenFalla.setName(""); // NOI18N
        DetalleSismo_origenFalla.setPreferredSize(new java.awt.Dimension(107, 23));
        DetalleSismo_origenFalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleSismo_origenFallaActionPerformed(evt);
            }
        });

        textOrigenFalla_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textOrigenFalla_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textOrigenFalla_NuevoSismo1.setText("Origen de falla:");

        textDetalleFalla_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textDetalleFalla_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textDetalleFalla_NuevoSismo1.setText("Detalle de Falla:");

        DetalleSismo_detalleFalla.setEditable(false);
        DetalleSismo_detalleFalla.setFocusable(false);
        DetalleSismo_detalleFalla.setMaximumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_detalleFalla.setMinimumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_detalleFalla.setPreferredSize(new java.awt.Dimension(107, 23));
        DetalleSismo_detalleFalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleSismo_detalleFallaActionPerformed(evt);
            }
        });

        textMagnitud_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textMagnitud_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textMagnitud_NuevoSismo1.setText("Magnitud:");

        DetalleSismo_magnitud.setEditable(false);
        DetalleSismo_magnitud.setFocusable(false);
        DetalleSismo_magnitud.setMaximumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_magnitud.setMinimumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_magnitud.setPreferredSize(new java.awt.Dimension(107, 23));
        DetalleSismo_magnitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleSismo_magnitudActionPerformed(evt);
            }
        });

        textLugar_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textLugar_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textLugar_NuevoSismo1.setText("Lugar:");

        textProvincia_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textProvincia_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textProvincia_NuevoSismo1.setText("Provincia:");

        textUbicacion_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textUbicacion_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textUbicacion_NuevoSismo1.setText("Ubicación Exacta:");

        textDescripcion_NuevoSismo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textDescripcion_NuevoSismo1.setForeground(new java.awt.Color(241, 250, 238));
        textDescripcion_NuevoSismo1.setText("Descripción:");

        scrollPaneDescripcionDetallada_NuevoSismo1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        DetalleSismo_DescripcionDetallada.setEditable(false);
        DetalleSismo_DescripcionDetallada.setColumns(20);
        DetalleSismo_DescripcionDetallada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        DetalleSismo_DescripcionDetallada.setRows(5);
        DetalleSismo_DescripcionDetallada.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DetalleSismo_DescripcionDetallada.setFocusable(false);
        scrollPaneDescripcionDetallada_NuevoSismo1.setViewportView(DetalleSismo_DescripcionDetallada);

        botonRegresar_NuevoSismo1.setText("Regresar");
        botonRegresar_NuevoSismo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegresar_NuevoSismo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar_NuevoSismo1ActionPerformed(evt);
            }
        });

        botonSalir_NuevoSismo1.setText("Salir");
        botonSalir_NuevoSismo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSalir_NuevoSismo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_NuevoSismo1ActionPerformed(evt);
            }
        });

        DetalleSismo_momentoExacto.setEditable(false);
        DetalleSismo_momentoExacto.setFocusable(false);
        DetalleSismo_momentoExacto.setMaximumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_momentoExacto.setMinimumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_momentoExacto.setName(""); // NOI18N
        DetalleSismo_momentoExacto.setPreferredSize(new java.awt.Dimension(107, 23));
        DetalleSismo_momentoExacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleSismo_momentoExactoActionPerformed(evt);
            }
        });

        DetalleSismo_lugar.setEditable(false);
        DetalleSismo_lugar.setFocusable(false);
        DetalleSismo_lugar.setMaximumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_lugar.setMinimumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_lugar.setName(""); // NOI18N
        DetalleSismo_lugar.setPreferredSize(new java.awt.Dimension(107, 23));
        DetalleSismo_lugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleSismo_lugarActionPerformed(evt);
            }
        });

        DetalleSismo_provincia.setEditable(false);
        DetalleSismo_provincia.setFocusable(false);
        DetalleSismo_provincia.setMaximumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_provincia.setMinimumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_provincia.setName(""); // NOI18N
        DetalleSismo_provincia.setPreferredSize(new java.awt.Dimension(107, 23));
        DetalleSismo_provincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleSismo_provinciaActionPerformed(evt);
            }
        });

        DetalleSismo_ubicacionExacta.setEditable(false);
        DetalleSismo_ubicacionExacta.setFocusable(false);
        DetalleSismo_ubicacionExacta.setMaximumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_ubicacionExacta.setMinimumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_ubicacionExacta.setName(""); // NOI18N
        DetalleSismo_ubicacionExacta.setPreferredSize(new java.awt.Dimension(107, 23));
        DetalleSismo_ubicacionExacta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleSismo_ubicacionExactaActionPerformed(evt);
            }
        });

        DetalleSismo_profundidad.setEditable(false);
        DetalleSismo_profundidad.setFocusable(false);
        DetalleSismo_profundidad.setMaximumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_profundidad.setMinimumSize(new java.awt.Dimension(107, 23));
        DetalleSismo_profundidad.setName(""); // NOI18N
        DetalleSismo_profundidad.setPreferredSize(new java.awt.Dimension(107, 23));
        DetalleSismo_profundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleSismo_profundidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VentanaIntermedia_DetalleSismoLayout = new javax.swing.GroupLayout(VentanaIntermedia_DetalleSismo);
        VentanaIntermedia_DetalleSismo.setLayout(VentanaIntermedia_DetalleSismoLayout);
        VentanaIntermedia_DetalleSismoLayout.setHorizontalGroup(
            VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                .addComponent(textTitulo_NuevoSismo1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonRegresar_NuevoSismo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir_NuevoSismo1))
                    .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                        .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textMomentoExacto_NuevoSismo1)
                                    .addComponent(textProfundidad_NuevoSismo1))
                                .addGap(32, 32, 32)
                                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DetalleSismo_momentoExacto, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DetalleSismo_profundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textOrigenFalla_NuevoSismo1)
                                    .addComponent(textDetalleFalla_NuevoSismo1)
                                    .addComponent(textMagnitud_NuevoSismo1))
                                .addGap(44, 44, 44)
                                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DetalleSismo_detalleFalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DetalleSismo_magnitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DetalleSismo_origenFalla, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                                .addComponent(textDescripcion_NuevoSismo1)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPaneDescripcionDetallada_NuevoSismo1))
                            .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                                .addComponent(textUbicacion_NuevoSismo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DetalleSismo_ubicacionExacta, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textProvincia_NuevoSismo1)
                                    .addComponent(textLugar_NuevoSismo1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DetalleSismo_provincia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DetalleSismo_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        VentanaIntermedia_DetalleSismoLayout.setVerticalGroup(
            VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                .addComponent(textTitulo_NuevoSismo1)
                .addGap(49, 49, 49)
                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textMomentoExacto_NuevoSismo1)
                    .addComponent(textLugar_NuevoSismo1)
                    .addComponent(DetalleSismo_momentoExacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DetalleSismo_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textProfundidad_NuevoSismo1)
                    .addComponent(textProvincia_NuevoSismo1)
                    .addComponent(DetalleSismo_provincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DetalleSismo_profundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                        .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textOrigenFalla_NuevoSismo1)
                            .addComponent(DetalleSismo_origenFalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textDetalleFalla_NuevoSismo1)
                            .addComponent(DetalleSismo_detalleFalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textDescripcion_NuevoSismo1))
                        .addGap(27, 27, 27)
                        .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textMagnitud_NuevoSismo1)
                            .addComponent(DetalleSismo_magnitud, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
                    .addGroup(VentanaIntermedia_DetalleSismoLayout.createSequentialGroup()
                        .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textUbicacion_NuevoSismo1)
                            .addComponent(DetalleSismo_ubicacionExacta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(scrollPaneDescripcionDetallada_NuevoSismo1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addGroup(VentanaIntermedia_DetalleSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRegresar_NuevoSismo1)
                    .addComponent(botonSalir_NuevoSismo1))
                .addGap(21, 21, 21))
        );

        Paneles.add(VentanaIntermedia_DetalleSismo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        NuevoSismo.setBackground(new java.awt.Color(230, 57, 70));
        NuevoSismo.setMaximumSize(new java.awt.Dimension(750, 510));
        NuevoSismo.setMinimumSize(new java.awt.Dimension(750, 510));
        NuevoSismo.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_NuevoSismo.setBackground(new java.awt.Color(29, 53, 87));
        textTitulo_NuevoSismo.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        textTitulo_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textTitulo_NuevoSismo.setText(" Nuevo Sismo  ");
        textTitulo_NuevoSismo.setOpaque(true);

        textMomentoExacto_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textMomentoExacto_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textMomentoExacto_NuevoSismo.setText("Momento Exacto:");

        botonEscogerFecha_NuevoSismo.setBackground(new java.awt.Color(241, 250, 238));
        botonEscogerFecha_NuevoSismo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonEscogerFecha_NuevoSismo.setForeground(new java.awt.Color(29, 53, 87));
        botonEscogerFecha_NuevoSismo.setText("Escoger fecha");
        botonEscogerFecha_NuevoSismo.setBorderPainted(false);
        botonEscogerFecha_NuevoSismo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEscogerFecha_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEscogerFecha_NuevoSismoActionPerformed(evt);
            }
        });

        textProfundidad_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textProfundidad_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textProfundidad_NuevoSismo.setText("Profundidad:");

        inputProfundidad_NuevoSismo.setMaximumSize(new java.awt.Dimension(107, 23));
        inputProfundidad_NuevoSismo.setMinimumSize(new java.awt.Dimension(107, 23));
        inputProfundidad_NuevoSismo.setName(""); // NOI18N
        inputProfundidad_NuevoSismo.setPreferredSize(new java.awt.Dimension(107, 23));
        inputProfundidad_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputProfundidad_NuevoSismoActionPerformed(evt);
            }
        });

        validacionProfundidad_NuevoSismo.setForeground(new java.awt.Color(255, 255, 0));

        textOrigenFalla_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textOrigenFalla_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textOrigenFalla_NuevoSismo.setText("Origen de falla:");

        inputOrigenFallaLista_NuevoSismo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputOrigenFallaLista_NuevoSismo.setMaximumSize(new java.awt.Dimension(107, 23));
        inputOrigenFallaLista_NuevoSismo.setMinimumSize(new java.awt.Dimension(107, 23));
        inputOrigenFallaLista_NuevoSismo.setName(""); // NOI18N
        inputOrigenFallaLista_NuevoSismo.setPreferredSize(new java.awt.Dimension(107, 23));

        textDetalleFalla_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textDetalleFalla_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textDetalleFalla_NuevoSismo.setText("Detalle de Falla:");

        inputDetalleFalla_NuevoSismo.setMaximumSize(new java.awt.Dimension(107, 23));
        inputDetalleFalla_NuevoSismo.setMinimumSize(new java.awt.Dimension(107, 23));
        inputDetalleFalla_NuevoSismo.setPreferredSize(new java.awt.Dimension(107, 23));
        inputDetalleFalla_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDetalleFalla_NuevoSismoActionPerformed(evt);
            }
        });

        textMagnitud_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textMagnitud_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textMagnitud_NuevoSismo.setText("Magnitud:");

        inputMagnitud_NuevoSismo.setMaximumSize(new java.awt.Dimension(107, 23));
        inputMagnitud_NuevoSismo.setMinimumSize(new java.awt.Dimension(107, 23));
        inputMagnitud_NuevoSismo.setPreferredSize(new java.awt.Dimension(107, 23));
        inputMagnitud_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputMagnitud_NuevoSismoActionPerformed(evt);
            }
        });

        validacionMagnitud_NuevoSismo.setForeground(new java.awt.Color(255, 255, 0));

        textLugar_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textLugar_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textLugar_NuevoSismo.setText("Lugar:");

        inputLugarLista_NuevoSismo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputLugarLista_NuevoSismo.setMaximumSize(new java.awt.Dimension(107, 23));
        inputLugarLista_NuevoSismo.setMinimumSize(new java.awt.Dimension(107, 23));
        inputLugarLista_NuevoSismo.setName(""); // NOI18N
        inputLugarLista_NuevoSismo.setPreferredSize(new java.awt.Dimension(107, 23));

        textProvincia_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textProvincia_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textProvincia_NuevoSismo.setText("Provincia:");

        inputProvinciaLista_NuevoSismo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputProvinciaLista_NuevoSismo.setMaximumSize(new java.awt.Dimension(107, 23));
        inputProvinciaLista_NuevoSismo.setMinimumSize(new java.awt.Dimension(107, 23));
        inputProvinciaLista_NuevoSismo.setName(""); // NOI18N
        inputProvinciaLista_NuevoSismo.setPreferredSize(new java.awt.Dimension(107, 23));
        inputProvinciaLista_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputProvinciaLista_NuevoSismoActionPerformed(evt);
            }
        });

        textUbicacion_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textUbicacion_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textUbicacion_NuevoSismo.setText("Ubicación Exacta:");

        botonUbicacion_NuevoSismo.setBackground(new java.awt.Color(241, 250, 238));
        botonUbicacion_NuevoSismo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonUbicacion_NuevoSismo.setForeground(new java.awt.Color(29, 53, 87));
        botonUbicacion_NuevoSismo.setText("Ubicación");
        botonUbicacion_NuevoSismo.setBorderPainted(false);
        botonUbicacion_NuevoSismo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonUbicacion_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonUbicacion_NuevoSismoActionPerformed(evt);
            }
        });

        textDescripcion_NuevoSismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textDescripcion_NuevoSismo.setForeground(new java.awt.Color(241, 250, 238));
        textDescripcion_NuevoSismo.setText("Descripción:");

        scrollPaneDescripcionDetallada_NuevoSismo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        inputDescripcionDetallada_NuevoSismo.setColumns(20);
        inputDescripcionDetallada_NuevoSismo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inputDescripcionDetallada_NuevoSismo.setRows(5);
        scrollPaneDescripcionDetallada_NuevoSismo.setViewportView(inputDescripcionDetallada_NuevoSismo);

        botonAceptar_NuevoSismo.setText("Aceptar");
        botonAceptar_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptar_NuevoSismoActionPerformed(evt);
            }
        });

        botonRegresar_NuevoSismo.setText("Regresar");
        botonRegresar_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar_NuevoSismoActionPerformed(evt);
            }
        });

        botonSalir_NuevoSismo.setText("Salir");
        botonSalir_NuevoSismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_NuevoSismoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NuevoSismoLayout = new javax.swing.GroupLayout(NuevoSismo);
        NuevoSismo.setLayout(NuevoSismoLayout);
        NuevoSismoLayout.setHorizontalGroup(
            NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textTitulo_NuevoSismo, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(NuevoSismoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevoSismoLayout.createSequentialGroup()
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textMomentoExacto_NuevoSismo)
                            .addComponent(textProfundidad_NuevoSismo))
                        .addGap(32, 32, 32)
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputProfundidad_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(botonEscogerFecha_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(NuevoSismoLayout.createSequentialGroup()
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textOrigenFalla_NuevoSismo)
                            .addComponent(textDetalleFalla_NuevoSismo)
                            .addComponent(textMagnitud_NuevoSismo))
                        .addGap(44, 44, 44)
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(validacionMagnitud_NuevoSismo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(validacionProfundidad_NuevoSismo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputDetalleFalla_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(inputOrigenFallaLista_NuevoSismo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputMagnitud_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(39, 39, 39)
                .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(NuevoSismoLayout.createSequentialGroup()
                        .addComponent(textDescripcion_NuevoSismo)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneDescripcionDetallada_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addGroup(NuevoSismoLayout.createSequentialGroup()
                        .addComponent(textUbicacion_NuevoSismo)
                        .addGap(18, 18, 18)
                        .addComponent(botonUbicacion_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevoSismoLayout.createSequentialGroup()
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textProvincia_NuevoSismo)
                            .addComponent(textLugar_NuevoSismo))
                        .addGap(71, 71, 71)
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputLugarLista_NuevoSismo, 0, 175, Short.MAX_VALUE)
                            .addComponent(inputProvinciaLista_NuevoSismo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevoSismoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonAceptar_NuevoSismo)
                .addGap(150, 150, 150)
                .addComponent(botonRegresar_NuevoSismo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonSalir_NuevoSismo)
                .addContainerGap())
        );
        NuevoSismoLayout.setVerticalGroup(
            NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevoSismoLayout.createSequentialGroup()
                .addComponent(textTitulo_NuevoSismo)
                .addGap(48, 48, 48)
                .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textMomentoExacto_NuevoSismo)
                    .addComponent(botonEscogerFecha_NuevoSismo)
                    .addComponent(textLugar_NuevoSismo)
                    .addComponent(inputLugarLista_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textProfundidad_NuevoSismo)
                    .addComponent(inputProfundidad_NuevoSismo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textProvincia_NuevoSismo)
                    .addComponent(inputProvinciaLista_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(validacionProfundidad_NuevoSismo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevoSismoLayout.createSequentialGroup()
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textOrigenFalla_NuevoSismo)
                            .addComponent(inputOrigenFallaLista_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textDetalleFalla_NuevoSismo)
                            .addComponent(inputDetalleFalla_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textDescripcion_NuevoSismo))
                        .addGap(27, 27, 27)
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textMagnitud_NuevoSismo)
                            .addComponent(inputMagnitud_NuevoSismo, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(validacionMagnitud_NuevoSismo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NuevoSismoLayout.createSequentialGroup()
                        .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonUbicacion_NuevoSismo)
                            .addComponent(textUbicacion_NuevoSismo))
                        .addGap(27, 27, 27)
                        .addComponent(scrollPaneDescripcionDetallada_NuevoSismo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addGroup(NuevoSismoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar_NuevoSismo)
                    .addComponent(botonRegresar_NuevoSismo)
                    .addComponent(botonSalir_NuevoSismo))
                .addGap(21, 21, 21))
        );

        Paneles.add(NuevoSismo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        NuevoSismo_Fecha.setBackground(new java.awt.Color(230, 57, 70));
        NuevoSismo_Fecha.setMaximumSize(new java.awt.Dimension(750, 510));
        NuevoSismo_Fecha.setMinimumSize(new java.awt.Dimension(750, 510));
        NuevoSismo_Fecha.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_NuevoSismo_Fecha.setBackground(new java.awt.Color(29, 53, 87));
        textTitulo_NuevoSismo_Fecha.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        textTitulo_NuevoSismo_Fecha.setForeground(new java.awt.Color(241, 250, 238));
        textTitulo_NuevoSismo_Fecha.setText(" Nuevo Sismo - Escoger Fecha ");
        textTitulo_NuevoSismo_Fecha.setOpaque(true);

        textMomentoExacto_NuevoSismo_Fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textMomentoExacto_NuevoSismo_Fecha.setForeground(new java.awt.Color(241, 250, 238));
        textMomentoExacto_NuevoSismo_Fecha.setText("Escoger este momento:");

        botonEscogerFecha_NuevoSismo_Fecha.setBackground(new java.awt.Color(241, 250, 238));
        botonEscogerFecha_NuevoSismo_Fecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonEscogerFecha_NuevoSismo_Fecha.setForeground(new java.awt.Color(29, 53, 87));
        botonEscogerFecha_NuevoSismo_Fecha.setText("Ahora");
        botonEscogerFecha_NuevoSismo_Fecha.setBorderPainted(false);
        botonEscogerFecha_NuevoSismo_Fecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEscogerFecha_NuevoSismo_Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEscogerFecha_NuevoSismo_FechaActionPerformed(evt);
            }
        });

        textDia_NuevoSismo_Fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textDia_NuevoSismo_Fecha.setForeground(new java.awt.Color(241, 250, 238));
        textDia_NuevoSismo_Fecha.setText("Día:");

        inputDiaFormated_NuevoSismo_Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDiaFormated_NuevoSismo_FechaActionPerformed(evt);
            }
        });

        textHora_NuevoSismo_Fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textHora_NuevoSismo_Fecha.setForeground(new java.awt.Color(241, 250, 238));
        textHora_NuevoSismo_Fecha.setText("Hora:");

        inputHoraFormated_NuevoSismo_Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputHoraFormated_NuevoSismo_FechaActionPerformed(evt);
            }
        });

        botonAceptar_NuevoSismo_Fecha.setText("Aceptar");
        botonAceptar_NuevoSismo_Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptar_NuevoSismo_FechaActionPerformed(evt);
            }
        });

        botonSalir_NuevoSismo_Fecha.setText("Salir");
        botonSalir_NuevoSismo_Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_NuevoSismo_FechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NuevoSismo_FechaLayout = new javax.swing.GroupLayout(NuevoSismo_Fecha);
        NuevoSismo_Fecha.setLayout(NuevoSismo_FechaLayout);
        NuevoSismo_FechaLayout.setHorizontalGroup(
            NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textTitulo_NuevoSismo_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(NuevoSismo_FechaLayout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addGroup(NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevoSismo_FechaLayout.createSequentialGroup()
                        .addGroup(NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textDia_NuevoSismo_Fecha)
                            .addGroup(NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(NuevoSismo_FechaLayout.createSequentialGroup()
                                    .addComponent(textMomentoExacto_NuevoSismo_Fecha)
                                    .addGap(32, 32, 32)
                                    .addComponent(botonEscogerFecha_NuevoSismo_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(NuevoSismo_FechaLayout.createSequentialGroup()
                                    .addComponent(inputDiaFormated_NuevoSismo_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textHora_NuevoSismo_Fecha)
                                        .addComponent(inputHoraFormated_NuevoSismo_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(NuevoSismo_FechaLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(botonAceptar_NuevoSismo_Fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonSalir_NuevoSismo_Fecha)
                        .addGap(31, 31, 31))))
        );
        NuevoSismo_FechaLayout.setVerticalGroup(
            NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevoSismo_FechaLayout.createSequentialGroup()
                .addComponent(textTitulo_NuevoSismo_Fecha)
                .addGap(45, 45, 45)
                .addGroup(NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textMomentoExacto_NuevoSismo_Fecha)
                    .addComponent(botonEscogerFecha_NuevoSismo_Fecha))
                .addGap(63, 63, 63)
                .addGroup(NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textDia_NuevoSismo_Fecha)
                    .addComponent(textHora_NuevoSismo_Fecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputDiaFormated_NuevoSismo_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputHoraFormated_NuevoSismo_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addGroup(NuevoSismo_FechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar_NuevoSismo_Fecha)
                    .addComponent(botonSalir_NuevoSismo_Fecha))
                .addGap(21, 21, 21))
        );

        Paneles.add(NuevoSismo_Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        NuevoSismo_UbicacionMapa.setBackground(new java.awt.Color(230, 57, 70));
        NuevoSismo_UbicacionMapa.setMaximumSize(new java.awt.Dimension(750, 510));
        NuevoSismo_UbicacionMapa.setMinimumSize(new java.awt.Dimension(750, 510));
        NuevoSismo_UbicacionMapa.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_NuevoSismo_Ubicacion.setBackground(new java.awt.Color(29, 53, 87));
        textTitulo_NuevoSismo_Ubicacion.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        textTitulo_NuevoSismo_Ubicacion.setForeground(new java.awt.Color(241, 250, 238));
        textTitulo_NuevoSismo_Ubicacion.setText(" Nuevo Sismo - Definir Ubicación ");
        textTitulo_NuevoSismo_Ubicacion.setOpaque(true);

        textLatitud_NuevoSismo_Ubicacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textLatitud_NuevoSismo_Ubicacion.setForeground(new java.awt.Color(241, 250, 238));
        textLatitud_NuevoSismo_Ubicacion.setText("Latitud:");

        try {
            MaskFormatter formatter = new MaskFormatter("*##.######");
            formatter.setValidCharacters("0123456789-+");
            inputLatitudFormated_NuevoSismo_Ubicacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formatter));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(MensajeError_NuevoSismo, "Latitud no valida.");
        }
        inputLatitudFormated_NuevoSismo_Ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputLatitudFormated_NuevoSismo_UbicacionActionPerformed(evt);
            }
        });

        textLongitud_NuevoSismo_Ubicacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textLongitud_NuevoSismo_Ubicacion.setForeground(new java.awt.Color(241, 250, 238));
        textLongitud_NuevoSismo_Ubicacion.setText("Longitud:");

        try {
            inputLongitudFormated_NuevoSismo_Ubicacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*###.######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputLongitudFormated_NuevoSismo_Ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputLongitudFormated_NuevoSismo_UbicacionActionPerformed(evt);
            }
        });

        botonAceptar_NuevoSismo_Ubicacion.setText("Aceptar");
        botonAceptar_NuevoSismo_Ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptar_NuevoSismo_UbicacionActionPerformed(evt);
            }
        });

        botonSalir_NuevoSismo_Ubicacion.setText("Salir");
        botonSalir_NuevoSismo_Ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_NuevoSismo_UbicacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mapa_NuevoSismo_UbicacionLayout = new javax.swing.GroupLayout(mapa_NuevoSismo_Ubicacion);
        mapa_NuevoSismo_Ubicacion.setLayout(mapa_NuevoSismo_UbicacionLayout);
        mapa_NuevoSismo_UbicacionLayout.setHorizontalGroup(
            mapa_NuevoSismo_UbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
        );
        mapa_NuevoSismo_UbicacionLayout.setVerticalGroup(
            mapa_NuevoSismo_UbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setText("Mostrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NuevoSismo_UbicacionMapaLayout = new javax.swing.GroupLayout(NuevoSismo_UbicacionMapa);
        NuevoSismo_UbicacionMapa.setLayout(NuevoSismo_UbicacionMapaLayout);
        NuevoSismo_UbicacionMapaLayout.setHorizontalGroup(
            NuevoSismo_UbicacionMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevoSismo_UbicacionMapaLayout.createSequentialGroup()
                .addGroup(NuevoSismo_UbicacionMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTitulo_NuevoSismo_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NuevoSismo_UbicacionMapaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(textLatitud_NuevoSismo_Ubicacion)
                        .addGap(2, 2, 2)
                        .addComponent(inputLatitudFormated_NuevoSismo_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textLongitud_NuevoSismo_Ubicacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputLongitudFormated_NuevoSismo_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevoSismo_UbicacionMapaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(NuevoSismo_UbicacionMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(NuevoSismo_UbicacionMapaLayout.createSequentialGroup()
                        .addComponent(mapa_NuevoSismo_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(NuevoSismo_UbicacionMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonAceptar_NuevoSismo_Ubicacion)
                            .addComponent(botonSalir_NuevoSismo_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );
        NuevoSismo_UbicacionMapaLayout.setVerticalGroup(
            NuevoSismo_UbicacionMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevoSismo_UbicacionMapaLayout.createSequentialGroup()
                .addComponent(textTitulo_NuevoSismo_Ubicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NuevoSismo_UbicacionMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textLatitud_NuevoSismo_Ubicacion)
                    .addComponent(textLongitud_NuevoSismo_Ubicacion)
                    .addComponent(inputLongitudFormated_NuevoSismo_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputLatitudFormated_NuevoSismo_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NuevoSismo_UbicacionMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevoSismo_UbicacionMapaLayout.createSequentialGroup()
                        .addGap(0, 353, Short.MAX_VALUE)
                        .addComponent(botonAceptar_NuevoSismo_Ubicacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir_NuevoSismo_Ubicacion))
                    .addComponent(mapa_NuevoSismo_Ubicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        Paneles.add(NuevoSismo_UbicacionMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        RegistroSismos.setBackground(new java.awt.Color(29, 53, 87));
        RegistroSismos.setMaximumSize(new java.awt.Dimension(750, 510));
        RegistroSismos.setMinimumSize(new java.awt.Dimension(750, 510));
        RegistroSismos.setName(""); // NOI18N
        RegistroSismos.setPreferredSize(new java.awt.Dimension(750, 510));
        RegistroSismos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textTitulo_RegistroSismos.setBackground(new java.awt.Color(230, 57, 70));
        textTitulo_RegistroSismos.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        textTitulo_RegistroSismos.setForeground(new java.awt.Color(241, 250, 238));
        textTitulo_RegistroSismos.setText(" Analisis de Sismos ");
        textTitulo_RegistroSismos.setOpaque(true);
        RegistroSismos.add(textTitulo_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        botonAceptar_RegistroSismos.setText("Regresar");
        botonAceptar_RegistroSismos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptar_RegistroSismosActionPerformed(evt);
            }
        });
        RegistroSismos.add(botonAceptar_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, -1, -1));

        botonSalir_RegistroSismos.setText("Salir");
        botonSalir_RegistroSismos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_RegistroSismosActionPerformed(evt);
            }
        });
        RegistroSismos.add(botonSalir_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 71, -1));

        textA1_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textA1_RegistroSismos.setForeground(new java.awt.Color(255, 255, 255));
        textA1_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textA1_RegistroSismos.setText("Sismos por rangos");
        textA1_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textA1_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textA1_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 120, -1));

        textA2_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textA2_RegistroSismos.setForeground(new java.awt.Color(255, 255, 255));
        textA2_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textA2_RegistroSismos.setText(" de magnitud para");
        textA2_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textA2_RegistroSismos.setFocusable(false);
        textA2_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textA2_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 120, -1));

        textA3_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textA3_RegistroSismos.setForeground(new java.awt.Color(255, 255, 255));
        textA3_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textA3_RegistroSismos.setText(" una provincia");
        textA3_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textA3_RegistroSismos.setFocusable(false);
        textA3_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textA3_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 120, -1));

        botonSismosMagnitudPorProvincia_RegistroSismos.setBackground(new java.awt.Color(188, 35, 43));
        botonSismosMagnitudPorProvincia_RegistroSismos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonSismosMagnitudPorProvincia_RegistroSismos.setBorderPainted(false);
        botonSismosMagnitudPorProvincia_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSismosMagnitudPorProvincia_RegistroSismos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSismosMagnitudPorProvincia_RegistroSismosActionPerformed(evt);
            }
        });
        RegistroSismos.add(botonSismosMagnitudPorProvincia_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 120, 240));

        textB1_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textB1_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textB1_RegistroSismos.setText("Cantidad de");
        textB1_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textB1_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textB1_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 120, -1));

        textB2_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textB2_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textB2_RegistroSismos.setText("de sismos por tipo");
        textB2_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textB2_RegistroSismos.setFocusable(false);
        textB2_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textB2_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 120, -1));

        textB3_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textB3_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textB3_RegistroSismos.setText(" una origen");
        textB3_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textB3_RegistroSismos.setFocusable(false);
        textB3_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textB3_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 120, -1));

        botonCantSismosPorOrigen_RegistroSismos.setBackground(new java.awt.Color(230, 57, 70));
        botonCantSismosPorOrigen_RegistroSismos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonCantSismosPorOrigen_RegistroSismos.setBorderPainted(false);
        botonCantSismosPorOrigen_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCantSismosPorOrigen_RegistroSismos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCantSismosPorOrigen_RegistroSismosActionPerformed(evt);
            }
        });
        RegistroSismos.add(botonCantSismosPorOrigen_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 120, 240));

        textC1_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textC1_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textC1_RegistroSismos.setText("en un rango");
        textC1_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textC1_RegistroSismos.setFocusable(false);
        textC1_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textC1_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 120, -1));

        textC2_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textC2_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textC2_RegistroSismos.setText("Sismos ocurridos");
        textC2_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textC2_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textC2_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 120, -1));

        textC3_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textC3_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textC3_RegistroSismos.setText("de fechas");
        textC3_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textC3_RegistroSismos.setFocusable(false);
        textC3_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textC3_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 120, -1));

        botonSismosEntreFechas_RegistroSismos.setBackground(new java.awt.Color(241, 250, 238));
        botonSismosEntreFechas_RegistroSismos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonSismosEntreFechas_RegistroSismos.setBorderPainted(false);
        botonSismosEntreFechas_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSismosEntreFechas_RegistroSismos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSismosEntreFechas_RegistroSismosActionPerformed(evt);
            }
        });
        RegistroSismos.add(botonSismosEntreFechas_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 120, 240));

        textD1_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textD1_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textD1_RegistroSismos.setText("sismos por mes en");
        textD1_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textD1_RegistroSismos.setFocusable(false);
        textD1_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textD1_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 120, -1));

        textD2_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textD2_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textD2_RegistroSismos.setText("Cantidad de");
        textD2_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textD2_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textD2_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 120, -1));

        textD3_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textD3_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textD3_RegistroSismos.setText("un año");
        textD3_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textD3_RegistroSismos.setFocusable(false);
        textD3_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textD3_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 120, -1));

        botonCantSismosPorMesEnAño_RegistroSismos.setBackground(new java.awt.Color(168, 218, 220));
        botonCantSismosPorMesEnAño_RegistroSismos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonCantSismosPorMesEnAño_RegistroSismos.setBorderPainted(false);
        botonCantSismosPorMesEnAño_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCantSismosPorMesEnAño_RegistroSismos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCantSismosPorMesEnAño_RegistroSismosActionPerformed(evt);
            }
        });
        RegistroSismos.add(botonCantSismosPorMesEnAño_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 120, 240));

        textE1_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textE1_RegistroSismos.setForeground(new java.awt.Color(255, 255, 255));
        textE1_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textE1_RegistroSismos.setText("de sismos por");
        textE1_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textE1_RegistroSismos.setFocusable(false);
        textE1_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textE1_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 120, -1));

        textE2_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textE2_RegistroSismos.setForeground(new java.awt.Color(255, 255, 255));
        textE2_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textE2_RegistroSismos.setText("Clasificación");
        textE2_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textE2_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textE2_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 120, -1));

        textE3_RegistroSismos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textE3_RegistroSismos.setForeground(new java.awt.Color(255, 255, 255));
        textE3_RegistroSismos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textE3_RegistroSismos.setText("magnitud");
        textE3_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textE3_RegistroSismos.setFocusable(false);
        textE3_RegistroSismos.setRequestFocusEnabled(false);
        RegistroSismos.add(textE3_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 120, -1));

        botonClasificacionSismosPorMagnitud_RegistroSismos.setBackground(new java.awt.Color(69, 123, 157));
        botonClasificacionSismosPorMagnitud_RegistroSismos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonClasificacionSismosPorMagnitud_RegistroSismos.setBorderPainted(false);
        botonClasificacionSismosPorMagnitud_RegistroSismos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonClasificacionSismosPorMagnitud_RegistroSismos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClasificacionSismosPorMagnitud_RegistroSismosActionPerformed(evt);
            }
        });
        RegistroSismos.add(botonClasificacionSismosPorMagnitud_RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 120, 240));

        Paneles.add(RegistroSismos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        RegistroSismos_A.setBackground(new java.awt.Color(29, 53, 87));
        RegistroSismos_A.setMaximumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_A.setMinimumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_A.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_RegistroSismos_A.setBackground(new java.awt.Color(188, 35, 43));
        textTitulo_RegistroSismos_A.setFont(new java.awt.Font("Tahoma", 3, 28)); // NOI18N
        textTitulo_RegistroSismos_A.setForeground(new java.awt.Color(241, 250, 238));
        textTitulo_RegistroSismos_A.setText(" Sismos por rangos de magnitud para una provincia ");
        textTitulo_RegistroSismos_A.setOpaque(true);

        textProvincia_RegistroSismos_A.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textProvincia_RegistroSismos_A.setForeground(new java.awt.Color(241, 250, 238));
        textProvincia_RegistroSismos_A.setText("Provincia:");

        botonRegresar_RegistroSismos_A.setText("Regresar");
        botonRegresar_RegistroSismos_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar_RegistroSismos_AActionPerformed(evt);
            }
        });

        botonSalir_RegistroSismos_A.setText("Salir");
        botonSalir_RegistroSismos_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_RegistroSismos_AActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_RegistroSismos_ALayout = new javax.swing.GroupLayout(panel_RegistroSismos_A);
        panel_RegistroSismos_A.setLayout(panel_RegistroSismos_ALayout);
        panel_RegistroSismos_ALayout.setHorizontalGroup(
            panel_RegistroSismos_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_RegistroSismos_ALayout.setVerticalGroup(
            panel_RegistroSismos_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );

        inputListaProvincia_RegistroSismos_A.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputListaProvincia_RegistroSismos_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputListaProvincia_RegistroSismos_AActionPerformed(evt);
            }
        });

        botonMostrar_RegistroSismos_A.setText("Mostrar");
        botonMostrar_RegistroSismos_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMostrar_RegistroSismos_AActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RegistroSismos_ALayout = new javax.swing.GroupLayout(RegistroSismos_A);
        RegistroSismos_A.setLayout(RegistroSismos_ALayout);
        RegistroSismos_ALayout.setHorizontalGroup(
            RegistroSismos_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroSismos_ALayout.createSequentialGroup()
                .addComponent(textTitulo_RegistroSismos_A, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(RegistroSismos_ALayout.createSequentialGroup()
                .addGroup(RegistroSismos_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RegistroSismos_ALayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_RegistroSismos_A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(RegistroSismos_ALayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(textProvincia_RegistroSismos_A)
                        .addGap(18, 18, 18)
                        .addComponent(inputListaProvincia_RegistroSismos_A, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonMostrar_RegistroSismos_A)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonRegresar_RegistroSismos_A)
                        .addGap(8, 8, 8)
                        .addComponent(botonSalir_RegistroSismos_A, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        RegistroSismos_ALayout.setVerticalGroup(
            RegistroSismos_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_ALayout.createSequentialGroup()
                .addComponent(textTitulo_RegistroSismos_A, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_RegistroSismos_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RegistroSismos_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textProvincia_RegistroSismos_A)
                        .addComponent(inputListaProvincia_RegistroSismos_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonMostrar_RegistroSismos_A))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonRegresar_RegistroSismos_A)
                        .addComponent(botonSalir_RegistroSismos_A)))
                .addGap(5, 5, 5))
        );

        Paneles.add(RegistroSismos_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        RegistroSismos_B.setBackground(new java.awt.Color(29, 53, 87));
        RegistroSismos_B.setMaximumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_B.setMinimumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_B.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_RegistroSismos_B.setBackground(new java.awt.Color(230, 57, 70));
        textTitulo_RegistroSismos_B.setFont(new java.awt.Font("Tahoma", 3, 28)); // NOI18N
        textTitulo_RegistroSismos_B.setForeground(new java.awt.Color(255, 255, 255));
        textTitulo_RegistroSismos_B.setText(" Cantidad de sismos por tipo de origen ");
        textTitulo_RegistroSismos_B.setOpaque(true);

        textProvincia_RegistroSismos_B.setBackground(new java.awt.Color(29, 53, 87));
        textProvincia_RegistroSismos_B.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textProvincia_RegistroSismos_B.setForeground(new java.awt.Color(29, 53, 87));
        textProvincia_RegistroSismos_B.setText("Origen:");

        botonRegresar_RegistroSismos_B.setText("Regresar");
        botonRegresar_RegistroSismos_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar_RegistroSismos_BActionPerformed(evt);
            }
        });

        botonSalir_RegistroSismos_B.setText("Salir");
        botonSalir_RegistroSismos_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_RegistroSismos_BActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_RegistroSismos_BLayout = new javax.swing.GroupLayout(panel_RegistroSismos_B);
        panel_RegistroSismos_B.setLayout(panel_RegistroSismos_BLayout);
        panel_RegistroSismos_BLayout.setHorizontalGroup(
            panel_RegistroSismos_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_RegistroSismos_BLayout.setVerticalGroup(
            panel_RegistroSismos_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RegistroSismos_BLayout = new javax.swing.GroupLayout(RegistroSismos_B);
        RegistroSismos_B.setLayout(RegistroSismos_BLayout);
        RegistroSismos_BLayout.setHorizontalGroup(
            RegistroSismos_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroSismos_BLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_RegistroSismos_B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(RegistroSismos_BLayout.createSequentialGroup()
                .addGroup(RegistroSismos_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTitulo_RegistroSismos_B, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RegistroSismos_BLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(textProvincia_RegistroSismos_B)
                        .addGap(487, 487, 487)
                        .addComponent(botonRegresar_RegistroSismos_B)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir_RegistroSismos_B, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RegistroSismos_BLayout.setVerticalGroup(
            RegistroSismos_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_BLayout.createSequentialGroup()
                .addComponent(textTitulo_RegistroSismos_B, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_RegistroSismos_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(RegistroSismos_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textProvincia_RegistroSismos_B, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonRegresar_RegistroSismos_B)
                        .addComponent(botonSalir_RegistroSismos_B)))
                .addGap(5, 5, 5))
        );

        Paneles.add(RegistroSismos_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        RegistroSismos_C.setBackground(new java.awt.Color(29, 53, 87));
        RegistroSismos_C.setMaximumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_C.setMinimumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_C.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_RegistroSismos_C.setBackground(new java.awt.Color(241, 250, 238));
        textTitulo_RegistroSismos_C.setFont(new java.awt.Font("Tahoma", 3, 28)); // NOI18N
        textTitulo_RegistroSismos_C.setText(" Sismos ocurridos en un rango de fechas ");
        textTitulo_RegistroSismos_C.setOpaque(true);

        botonRegresar_RegistroSismos_C.setText("Regresar");
        botonRegresar_RegistroSismos_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar_RegistroSismos_CActionPerformed(evt);
            }
        });

        botonSalir_RegistroSismos_C.setText("Salir");
        botonSalir_RegistroSismos_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_RegistroSismos_CActionPerformed(evt);
            }
        });

        jTableSismoporFecha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jTableSismoporFecha.setInheritsPopupMenu(true);
        jScrollPane2.setViewportView(jTableSismoporFecha);

        javax.swing.GroupLayout panel_RegistroSismos_CLayout = new javax.swing.GroupLayout(panel_RegistroSismos_C);
        panel_RegistroSismos_C.setLayout(panel_RegistroSismos_CLayout);
        panel_RegistroSismos_CLayout.setHorizontalGroup(
            panel_RegistroSismos_CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_RegistroSismos_CLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_RegistroSismos_CLayout.setVerticalGroup(
            panel_RegistroSismos_CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_RegistroSismos_CLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        textDesde_RegistroSismos_C.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textDesde_RegistroSismos_C.setForeground(new java.awt.Color(241, 250, 238));
        textDesde_RegistroSismos_C.setText("Desde");

        inputDia1Formated_RegistroSismos_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDia1Formated_RegistroSismos_CActionPerformed(evt);
            }
        });

        textHasta_RegistroSismos_C.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textHasta_RegistroSismos_C.setForeground(new java.awt.Color(241, 250, 238));
        textHasta_RegistroSismos_C.setText("hasta");

        botonMostrar_RegistroSismos_C.setText("Mostrar");
        botonMostrar_RegistroSismos_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMostrar_RegistroSismos_CActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RegistroSismos_CLayout = new javax.swing.GroupLayout(RegistroSismos_C);
        RegistroSismos_C.setLayout(RegistroSismos_CLayout);
        RegistroSismos_CLayout.setHorizontalGroup(
            RegistroSismos_CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroSismos_CLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_RegistroSismos_C, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(RegistroSismos_CLayout.createSequentialGroup()
                .addGroup(RegistroSismos_CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTitulo_RegistroSismos_C, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RegistroSismos_CLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(textDesde_RegistroSismos_C)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputDia1Formated_RegistroSismos_C, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textHasta_RegistroSismos_C)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputDia2Formated_RegistroSismos_C, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonMostrar_RegistroSismos_C)
                        .addGap(131, 131, 131)
                        .addComponent(botonRegresar_RegistroSismos_C)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir_RegistroSismos_C, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RegistroSismos_CLayout.setVerticalGroup(
            RegistroSismos_CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_CLayout.createSequentialGroup()
                .addComponent(textTitulo_RegistroSismos_C, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_RegistroSismos_C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RegistroSismos_CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRegresar_RegistroSismos_C)
                    .addComponent(botonSalir_RegistroSismos_C)
                    .addComponent(inputDia1Formated_RegistroSismos_C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textHasta_RegistroSismos_C)
                    .addComponent(textDesde_RegistroSismos_C)
                    .addComponent(inputDia2Formated_RegistroSismos_C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonMostrar_RegistroSismos_C))
                .addGap(5, 5, 5))
        );

        Paneles.add(RegistroSismos_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        RegistroSismos_D.setBackground(new java.awt.Color(29, 53, 87));
        RegistroSismos_D.setMaximumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_D.setMinimumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_D.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_RegistroSismos_D.setBackground(new java.awt.Color(168, 218, 220));
        textTitulo_RegistroSismos_D.setFont(new java.awt.Font("Tahoma", 3, 28)); // NOI18N
        textTitulo_RegistroSismos_D.setForeground(new java.awt.Color(255, 255, 255));
        textTitulo_RegistroSismos_D.setText(" Cantidad de sismos por mes en un año ");
        textTitulo_RegistroSismos_D.setOpaque(true);

        textAño_RegistroSismos_D.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textAño_RegistroSismos_D.setForeground(new java.awt.Color(241, 250, 238));
        textAño_RegistroSismos_D.setText("Año:");

        botonRegresar_RegistroSismos_D.setText("Regresar");
        botonRegresar_RegistroSismos_D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar_RegistroSismos_DActionPerformed(evt);
            }
        });

        botonSalir_RegistroSismos_D.setText("Salir");
        botonSalir_RegistroSismos_D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_RegistroSismos_DActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_RegistroSismos_DLayout = new javax.swing.GroupLayout(panel_RegistroSismos_D);
        panel_RegistroSismos_D.setLayout(panel_RegistroSismos_DLayout);
        panel_RegistroSismos_DLayout.setHorizontalGroup(
            panel_RegistroSismos_DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_RegistroSismos_DLayout.setVerticalGroup(
            panel_RegistroSismos_DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );

        botonMostrar_RegistroSismos_D.setText("Mostrar");
        botonMostrar_RegistroSismos_D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMostrar_RegistroSismos_DActionPerformed(evt);
            }
        });

        inputAñoFormated_RegistroSismos_D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputAñoFormated_RegistroSismos_DActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RegistroSismos_DLayout = new javax.swing.GroupLayout(RegistroSismos_D);
        RegistroSismos_D.setLayout(RegistroSismos_DLayout);
        RegistroSismos_DLayout.setHorizontalGroup(
            RegistroSismos_DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroSismos_DLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_RegistroSismos_D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(RegistroSismos_DLayout.createSequentialGroup()
                .addGroup(RegistroSismos_DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTitulo_RegistroSismos_D, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RegistroSismos_DLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(textAño_RegistroSismos_D)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputAñoFormated_RegistroSismos_D, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonMostrar_RegistroSismos_D)
                        .addGap(203, 203, 203)
                        .addComponent(botonRegresar_RegistroSismos_D)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir_RegistroSismos_D, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RegistroSismos_DLayout.setVerticalGroup(
            RegistroSismos_DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_DLayout.createSequentialGroup()
                .addComponent(textTitulo_RegistroSismos_D, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_RegistroSismos_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RegistroSismos_DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textAño_RegistroSismos_D)
                        .addComponent(botonMostrar_RegistroSismos_D)
                        .addComponent(inputAñoFormated_RegistroSismos_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonRegresar_RegistroSismos_D)
                        .addComponent(botonSalir_RegistroSismos_D)))
                .addGap(5, 5, 5))
        );

        Paneles.add(RegistroSismos_D, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        RegistroSismos_E.setBackground(new java.awt.Color(29, 53, 87));
        RegistroSismos_E.setMaximumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_E.setMinimumSize(new java.awt.Dimension(750, 510));
        RegistroSismos_E.setPreferredSize(new java.awt.Dimension(750, 510));

        textTitulo_RegistroSismos_E.setBackground(new java.awt.Color(69, 123, 157));
        textTitulo_RegistroSismos_E.setFont(new java.awt.Font("Tahoma", 3, 28)); // NOI18N
        textTitulo_RegistroSismos_E.setForeground(new java.awt.Color(255, 255, 255));
        textTitulo_RegistroSismos_E.setText(" Clasificación de sismos por magnitud ");
        textTitulo_RegistroSismos_E.setOpaque(true);

        botonRegresar_RegistroSismos_E.setText("Regresar");
        botonRegresar_RegistroSismos_E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar_RegistroSismos_EActionPerformed(evt);
            }
        });

        botonSalir_RegistroSismos_E.setText("Salir");
        botonSalir_RegistroSismos_E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalir_RegistroSismos_EActionPerformed(evt);
            }
        });

        jTabporMag.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(jTabporMag);

        javax.swing.GroupLayout panel_RegistroSismos_ELayout = new javax.swing.GroupLayout(panel_RegistroSismos_E);
        panel_RegistroSismos_E.setLayout(panel_RegistroSismos_ELayout);
        panel_RegistroSismos_ELayout.setHorizontalGroup(
            panel_RegistroSismos_ELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panel_RegistroSismos_ELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_RegistroSismos_ELayout.createSequentialGroup()
                    .addContainerGap(139, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(139, Short.MAX_VALUE)))
        );
        panel_RegistroSismos_ELayout.setVerticalGroup(
            panel_RegistroSismos_ELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
            .addGroup(panel_RegistroSismos_ELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_RegistroSismos_ELayout.createSequentialGroup()
                    .addContainerGap(12, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout RegistroSismos_ELayout = new javax.swing.GroupLayout(RegistroSismos_E);
        RegistroSismos_E.setLayout(RegistroSismos_ELayout);
        RegistroSismos_ELayout.setHorizontalGroup(
            RegistroSismos_ELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroSismos_ELayout.createSequentialGroup()
                .addComponent(textTitulo_RegistroSismos_E, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(RegistroSismos_ELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RegistroSismos_ELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_RegistroSismos_E, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(RegistroSismos_ELayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonRegresar_RegistroSismos_E)
                        .addGap(8, 8, 8)
                        .addComponent(botonSalir_RegistroSismos_E, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        RegistroSismos_ELayout.setVerticalGroup(
            RegistroSismos_ELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroSismos_ELayout.createSequentialGroup()
                .addComponent(textTitulo_RegistroSismos_E, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_RegistroSismos_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(RegistroSismos_ELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRegresar_RegistroSismos_E)
                    .addComponent(botonSalir_RegistroSismos_E))
                .addGap(5, 5, 5))
        );

        Paneles.add(RegistroSismos_E, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.setMaximumSize(new java.awt.Dimension(750, 380));
        jPanel6.setMinimumSize(new java.awt.Dimension(750, 380));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        Paneles.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(Paneles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoSismo_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoSismo_InicioActionPerformed
        ocultarPanelesExceptoEste(VentanaIntermedia,true);
        DefaultTableModel model= (DefaultTableModel) TablaTodosSismos.getModel();
        model.setRowCount(0);
        cargarValoresATablaSismos(controlador.ControladorArchivo.listaSismos());
    }//GEN-LAST:event_botonNuevoSismo_InicioActionPerformed

    private void botonRegistroSismos_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistroSismos_InicioActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos);
    }//GEN-LAST:event_botonRegistroSismos_InicioActionPerformed

    private void botonSalir_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_InicioActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_InicioActionPerformed

    private void inputMagnitud_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputMagnitud_NuevoSismoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputMagnitud_NuevoSismoActionPerformed

    private void inputDetalleFalla_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDetalleFalla_NuevoSismoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDetalleFalla_NuevoSismoActionPerformed

    private void botonAceptar_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptar_NuevoSismoActionPerformed
        String antiHexadecimal= "abcdefABCDEF";
        
        Date momentoExacto;
        double profundidad=0;
        TFalla origenFalla;
        String detalleFalla;
        double magnitud=0;
        double latitud = 0;
        double longitud = 0;
        String descripcionDetallada;
        TLugar lugar;
        TProvincia provincia;
        boolean banderaError=false;
        
        if(0==inputLugarLista_NuevoSismo.getSelectedIndex()) inputProvinciaLista_NuevoSismo.setSelectedIndex(0);
        if(0==inputProvinciaLista_NuevoSismo.getSelectedIndex()) inputLugarLista_NuevoSismo.setSelectedIndex(0);
        
        try{
            Date rawDia= (Date) inputDiaFormated_NuevoSismo_Fecha.getValue();
            Date rawHora= (Date) inputHoraFormated_NuevoSismo_Fecha.getValue();
            momentoExacto= new Date(
                    rawDia.getYear(), rawDia.getMonth(), rawDia.getDay(),
                    rawHora.getHours(), rawHora.getMinutes(), rawHora.getSeconds()
                    );
        }catch(NullPointerException error){
            momentoExacto= new Date();
            //banderaError=true;
        }
        
        try{
            String stringProf= inputProfundidad_NuevoSismo.getText();
            if(stringProf.contains(antiHexadecimal)) throw new NumberFormatException();
            profundidad= Double.parseDouble(stringProf);
            validacionProfundidad_NuevoSismo.setText("");
        } catch (NumberFormatException error){
            validacionProfundidad_NuevoSismo.setText("Dato invalido");
            banderaError=true;
        }
        
        origenFalla= TFalla.values()[inputOrigenFallaLista_NuevoSismo.getSelectedIndex()];
                
        detalleFalla= inputDetalleFalla_NuevoSismo.getText();
        if(detalleFalla == null) detalleFalla="";    
        
        try{
            String stringMag= inputMagnitud_NuevoSismo.getText();
            if(stringMag.contains(antiHexadecimal)) throw new NumberFormatException();
            magnitud= Double.parseDouble(stringMag);
            validacionMagnitud_NuevoSismo.setText("");
        } catch (NumberFormatException error){
            validacionMagnitud_NuevoSismo.setText("Dato invalido");
            banderaError=true;
        }
        
        try{
            String stringLong= inputLongitudFormated_NuevoSismo_Ubicacion.getText();
            longitud= Double.parseDouble(stringLong);;
        } catch (NumberFormatException error){
            JOptionPane.showMessageDialog(MensajeError_NuevoSismo, "Longitud no valida.");
            banderaError=true;
        }
        
        try{
            String stringLat= inputLatitudFormated_NuevoSismo_Ubicacion.getText();
            latitud= Double.parseDouble(stringLat);
        } catch (NumberFormatException error){
            JOptionPane.showMessageDialog(MensajeError_NuevoSismo, "Latitud no valida.");
            banderaError=true;
        }
        
        descripcionDetallada=inputDescripcionDetallada_NuevoSismo.getText();
        if (descripcionDetallada==null) descripcionDetallada="";
        
        lugar= TLugar.values()[inputLugarLista_NuevoSismo.getSelectedIndex()];
        
        provincia= TProvincia.values()[inputProvinciaLista_NuevoSismo.getSelectedIndex()];
        
//        System.out.println("Lat: "+ latitud+"\nLog: "+longitud);
        
        if(!banderaError){
            
        elSistema.nuevoSismo(momentoExacto, profundidad, origenFalla, detalleFalla, magnitud, latitud, longitud, descripcionDetallada, lugar, provincia);
//        System.out.println("Lat: "+ latitud+"\nLog: "+longitud);
        DefaultTableModel model= (DefaultTableModel) TablaTodosSismos.getModel();
        model.setRowCount(0);
        cargarValoresATablaSismos(controlador.ControladorArchivo.listaSismos());
        
        ocultarPanelesExceptoEste(VentanaIntermedia);
        }
    }//GEN-LAST:event_botonAceptar_NuevoSismoActionPerformed

    private void inputProfundidad_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputProfundidad_NuevoSismoActionPerformed

    }//GEN-LAST:event_inputProfundidad_NuevoSismoActionPerformed

    private void botonUbicacion_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonUbicacion_NuevoSismoActionPerformed
        ocultarPanelesExceptoEste(NuevoSismo_UbicacionMapa);
    }//GEN-LAST:event_botonUbicacion_NuevoSismoActionPerformed

    private void inputProvinciaLista_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputProvinciaLista_NuevoSismoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputProvinciaLista_NuevoSismoActionPerformed

    private void botonRegresar_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar_NuevoSismoActionPerformed
        ocultarPanelesExceptoEste(VentanaIntermedia);
        //cargarValoresATablaSismos(elSistema.getcSismos().getSismosOrdenadosFecha(true));
        TablaTodosSismos.removeAll();
        //cargarValoresATablaSismos(controlador.ControladorArchivo.listaSismos());
    }//GEN-LAST:event_botonRegresar_NuevoSismoActionPerformed

    private void botonAceptar_ConfirmarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptar_ConfirmarSalidaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_botonAceptar_ConfirmarSalidaActionPerformed

    private void botonSalir_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_NuevoSismoActionPerformed
        ConfirmarSalida.setVisible(true);
    }//GEN-LAST:event_botonSalir_NuevoSismoActionPerformed

    private void botonCancelar_ConfirmarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelar_ConfirmarSalidaActionPerformed
        ConfirmarSalida.setVisible(false);
        Paneles.setVisible(true);
    }//GEN-LAST:event_botonCancelar_ConfirmarSalidaActionPerformed

    private void botonAceptar_NuevoSismo_FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptar_NuevoSismo_FechaActionPerformed
        ocultarPanelesExceptoEste(NuevoSismo);
    }//GEN-LAST:event_botonAceptar_NuevoSismo_FechaActionPerformed

    private void botonSalir_NuevoSismo_FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_NuevoSismo_FechaActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_NuevoSismo_FechaActionPerformed

    private void botonEscogerFecha_NuevoSismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscogerFecha_NuevoSismoActionPerformed
        ocultarPanelesExceptoEste(NuevoSismo_Fecha);
    }//GEN-LAST:event_botonEscogerFecha_NuevoSismoActionPerformed

    private void botonEscogerFecha_NuevoSismo_FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEscogerFecha_NuevoSismo_FechaActionPerformed
        Date now= new Date();
        inputDiaFormated_NuevoSismo_Fecha.setValue(now);
        inputHoraFormated_NuevoSismo_Fecha.setValue(now);
    }//GEN-LAST:event_botonEscogerFecha_NuevoSismo_FechaActionPerformed

    private void inputDiaFormated_NuevoSismo_FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDiaFormated_NuevoSismo_FechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDiaFormated_NuevoSismo_FechaActionPerformed

    private void inputHoraFormated_NuevoSismo_FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputHoraFormated_NuevoSismo_FechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputHoraFormated_NuevoSismo_FechaActionPerformed

    private void botonAceptar_NuevoSismo_UbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptar_NuevoSismo_UbicacionActionPerformed
        if(comprobarLatLong_NuevoSismo()) ocultarPanelesExceptoEste(NuevoSismo);
    }//GEN-LAST:event_botonAceptar_NuevoSismo_UbicacionActionPerformed
    
    private void botonSalir_NuevoSismo_UbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_NuevoSismo_UbicacionActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_NuevoSismo_UbicacionActionPerformed

    private void botonAceptar_RegistroSismosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptar_RegistroSismosActionPerformed
        ocultarPanelesExceptoEste(Inicio);
    }//GEN-LAST:event_botonAceptar_RegistroSismosActionPerformed

    private void botonSalir_RegistroSismosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_RegistroSismosActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_RegistroSismosActionPerformed

    private void botonSismosMagnitudPorProvincia_RegistroSismosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSismosMagnitudPorProvincia_RegistroSismosActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos_A, true);
    }//GEN-LAST:event_botonSismosMagnitudPorProvincia_RegistroSismosActionPerformed

    private void botonCantSismosPorOrigen_RegistroSismosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCantSismosPorOrigen_RegistroSismosActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos_B, true);
        
        Grafico gPastel = new Grafico();
        
        //Variables cantidad de Sismos por Falla
        int int_ChoqueP = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Choque_Placas);
        int int_DefInt = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Deformación_Interna);
        int int_FallaLocal = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Fallamiento_Local);
        int int_SubPla = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Subduccion_Placas);
        int int_TFL = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Tectónico_Falla_Local);
        int int_TSub = ControladorGraficos.graficoTOrigen(ControladorArchivo.listaSismos(), modelo.TFalla.Tectónico_Subducción);

        int[] valores = {int_ChoqueP,int_DefInt,int_FallaLocal,int_SubPla,int_TFL,int_TSub};
        
        gPastel.crearGraficoPastel(valores, panel_RegistroSismos_B);
        System.out.println("Gráfico pastel creado");
    }//GEN-LAST:event_botonCantSismosPorOrigen_RegistroSismosActionPerformed

    private void botonRegresar_RegistroSismos_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar_RegistroSismos_AActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos);
    }//GEN-LAST:event_botonRegresar_RegistroSismos_AActionPerformed

    private void botonSalir_RegistroSismos_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_RegistroSismos_AActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_RegistroSismos_AActionPerformed

    private void botonRegresar_RegistroSismos_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar_RegistroSismos_BActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos);
    }//GEN-LAST:event_botonRegresar_RegistroSismos_BActionPerformed

    private void botonSalir_RegistroSismos_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_RegistroSismos_BActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_RegistroSismos_BActionPerformed

    private void botonRegresar_RegistroSismos_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar_RegistroSismos_CActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos);
    }//GEN-LAST:event_botonRegresar_RegistroSismos_CActionPerformed

    private void botonSalir_RegistroSismos_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_RegistroSismos_CActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_RegistroSismos_CActionPerformed

    private void inputDia1Formated_RegistroSismos_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDia1Formated_RegistroSismos_CActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDia1Formated_RegistroSismos_CActionPerformed

    private void botonMostrar_RegistroSismos_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMostrar_RegistroSismos_AActionPerformed
        inputListaProvincia_RegistroSismos_A.getSelectedIndex();
        
        Grafico graficoH = new Grafico();
        
        String detProvincia = (String)inputListaProvincia_RegistroSismos_A.getSelectedItem();
        TProvincia provincia = modelo.TProvincia.valueOf(detProvincia);
         
        ArrayList<Sismo> lista = ControladorArchivo.listaSismos();
        ArrayList<Sismo> listaSismosenProvincia = ControladorGraficos.listaSismoporProvincia(lista, provincia);
        
        int int_Micro = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Micro");
        int int_Menor1 = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Menor1");
        int int_Menor2 = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Menor2");
        int int_Ligero = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Ligero");
        int int_Moderado = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Moderado");
        int int_Fuerte = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Fuerte");
        int int_Mayor = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Mayor");
        int int_Gran1 = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Gran1");
        int int_Gran2 = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Gran2");
        int int_Epico = ControladorGraficos.graficoMagnitud(listaSismosenProvincia, "Epico");
       
        int[] histograma = {int_Micro,int_Menor1,int_Menor2,int_Ligero,int_Moderado,int_Fuerte,
            int_Mayor,int_Gran1,int_Gran2,int_Epico};
        
        graficoH.GraficoHistograma(histograma, panel_RegistroSismos_A, Color.BLACK);
        
    }//GEN-LAST:event_botonMostrar_RegistroSismos_AActionPerformed

    private void botonRegresar_RegistroSismos_DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar_RegistroSismos_DActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos);
        //limpiarVentana(panel_RegistroSismos_D);
        panel_RegistroSismos_D.removeAll();
        inputAñoFormated_RegistroSismos_D.setText("");
    }//GEN-LAST:event_botonRegresar_RegistroSismos_DActionPerformed

    private void botonSalir_RegistroSismos_DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_RegistroSismos_DActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_RegistroSismos_DActionPerformed

    private void botonRegresar_RegistroSismos_EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar_RegistroSismos_EActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos);
    }//GEN-LAST:event_botonRegresar_RegistroSismos_EActionPerformed

    private void botonSalir_RegistroSismos_EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_RegistroSismos_EActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_RegistroSismos_EActionPerformed

    private void botonSismosEntreFechas_RegistroSismosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSismosEntreFechas_RegistroSismosActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos_C, true);
    }//GEN-LAST:event_botonSismosEntreFechas_RegistroSismosActionPerformed

    private void botonCantSismosPorMesEnAño_RegistroSismosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCantSismosPorMesEnAño_RegistroSismosActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos_D, true);
    }//GEN-LAST:event_botonCantSismosPorMesEnAño_RegistroSismosActionPerformed

    private void botonClasificacionSismosPorMagnitud_RegistroSismosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonClasificacionSismosPorMagnitud_RegistroSismosActionPerformed
        ocultarPanelesExceptoEste(RegistroSismos_E, true);
        
            int int_Micro = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Micro");
            int int_Menor1 = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Menor1");
            int int_Menor2 = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Menor2");
            int int_Ligero = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Ligero");
            int int_Moderado = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Moderado");
            int int_Fuerte = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Fuerte");
            int int_Mayor = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Mayor");
            int int_Gran1 = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Gran1");
            int int_Gran2 = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Gran2");
            int int_Epico = ControladorGraficos.graficoMagnitud(ControladorArchivo.listaSismos(), "Epico");
            
            ArrayList lista = new ArrayList<>();
            
            lista.add("Menos de 2.0");
            lista.add("Micro");
            lista.add(int_Micro);
            
            lista.add("2.0 - 2.9");
            lista.add("Menor");
            lista.add(int_Menor1);
            
            lista.add("3.0 - 3.9");
            lista.add("Menor");
            lista.add(int_Menor2);
           
            lista.add("4.0 - 4.9");
            lista.add("Ligero");
            lista.add(int_Ligero);
           
            lista.add("5.0 - 5.9");
            lista.add("Moderado");
            lista.add(int_Moderado);
                    
            lista.add("6.0 - 6.9");
            lista.add("Fuerte");
            lista.add(int_Fuerte);
            
            lista.add("7.0 - 7.9");
            lista.add("Mayor");
            lista.add(int_Mayor);
           
            lista.add("8.0 - 8.9");
            lista.add("Gran");
            lista.add(int_Gran1);
           
            lista.add("9.0 - 9.9");
            lista.add("Gran");
            lista.add(int_Gran2);
           
            lista.add("+ de 10");
            lista.add("épico");
            lista.add(int_Epico);
            
            cargarValoresATablaGrafTabMAg(lista);
    }//GEN-LAST:event_botonClasificacionSismosPorMagnitud_RegistroSismosActionPerformed

    private void inputAñoFormated_RegistroSismos_DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputAñoFormated_RegistroSismos_DActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputAñoFormated_RegistroSismos_DActionPerformed

    private void inputListaProvincia_RegistroSismos_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputListaProvincia_RegistroSismos_AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputListaProvincia_RegistroSismos_AActionPerformed

    private void botonRegresar_VentanaIntermediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar_VentanaIntermediaActionPerformed
        ocultarPanelesExceptoEste(Inicio);
        TablaTodosSismos.clearSelection();
        TablaTodosSismos.removeAll();
    }//GEN-LAST:event_botonRegresar_VentanaIntermediaActionPerformed

    private void botonSalir_NuevoSismo_Ubicacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_NuevoSismo_Ubicacion1ActionPerformed
        cerrarPrograma();
    }//GEN-LAST:event_botonSalir_NuevoSismo_Ubicacion1ActionPerformed

    private void inputLongitudFormated_NuevoSismo_UbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputLongitudFormated_NuevoSismo_UbicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputLongitudFormated_NuevoSismo_UbicacionActionPerformed

    private void inputLatitudFormated_NuevoSismo_UbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputLatitudFormated_NuevoSismo_UbicacionActionPerformed

    }//GEN-LAST:event_inputLatitudFormated_NuevoSismo_UbicacionActionPerformed

    private void botonNuevoSismo_VentanaIntermediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoSismo_VentanaIntermediaActionPerformed
        ocultarPanelesExceptoEste(NuevoSismo_Fecha, true);
        ocultarPanelesExceptoEste(NuevoSismo_UbicacionMapa, true);
        ocultarPanelesExceptoEste(NuevoSismo, true);
    }//GEN-LAST:event_botonNuevoSismo_VentanaIntermediaActionPerformed

    private void botonNuevoSismo_VentanaIntermedia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoSismo_VentanaIntermedia1ActionPerformed
        if (TablaTodosSismos.getSelectedRow()!= -1){
        ocultarPanelesExceptoEste(VentanaIntermedia_DetalleSismo,true);
        try{
            escribirDatos_DetalleSismo(elSistema.getcSismos().getSismosOrdenadosFecha().get(TablaTodosSismos.getSelectedRow()));
        }catch(IndexOutOfBoundsException error){
        
        }
        
        //cargarValoresATablaSismos(controlador.ControladorArchivo.listaSismos());
        }
    }//GEN-LAST:event_botonNuevoSismo_VentanaIntermedia1ActionPerformed

    private void DetalleSismo_origenFallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleSismo_origenFallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleSismo_origenFallaActionPerformed

    private void DetalleSismo_detalleFallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleSismo_detalleFallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleSismo_detalleFallaActionPerformed

    private void DetalleSismo_magnitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleSismo_magnitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleSismo_magnitudActionPerformed

    private void botonRegresar_NuevoSismo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar_NuevoSismo1ActionPerformed
        cargarValoresATablaSismos(controlador.ControladorArchivo.listaSismos());
        ocultarPanelesExceptoEste(VentanaIntermedia);
    }//GEN-LAST:event_botonRegresar_NuevoSismo1ActionPerformed

    private void botonSalir_NuevoSismo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalir_NuevoSismo1ActionPerformed
       cerrarPrograma();
    }//GEN-LAST:event_botonSalir_NuevoSismo1ActionPerformed

    private void DetalleSismo_momentoExactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleSismo_momentoExactoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleSismo_momentoExactoActionPerformed

    private void DetalleSismo_lugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleSismo_lugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleSismo_lugarActionPerformed

    private void DetalleSismo_provinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleSismo_provinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleSismo_provinciaActionPerformed

    private void DetalleSismo_ubicacionExactaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleSismo_ubicacionExactaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleSismo_ubicacionExactaActionPerformed

    private void DetalleSismo_profundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleSismo_profundidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetalleSismo_profundidadActionPerformed

    private void botonMostrar_RegistroSismos_DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMostrar_RegistroSismos_DActionPerformed
        Grafico  migrafico = new Grafico ();
        Dimension d = panel_RegistroSismos_D.getSize();//toma el tamaño del contenedor
        //se crean los datos
        String elDatoInput = inputAñoFormated_RegistroSismos_D.getText();
        int anno = Integer.parseInt(elDatoInput);
        ArrayList<Sismo> lista = ControladorArchivo.listaSismos();
        ArrayList<Sismo> listaS = ControladorGraficos.listaSismoporAnno(lista, anno);
        //Variables por mes
        int int_enero = 0;
        int int_febrero = 0;
        int int_marzo = 0;
        int int_abril = 0;
        int int_mayo = 0;
        int int_junio = 0;
        int int_julio = 0;
        int int_agosto = 0;
        int int_setiembre = 0;
        int int_octubre = 0;
        int int_noviembre = 0;
        int int_diciembre = 0;
        
        if(listaS.isEmpty()){
            
            System.out.println("No hay sismos registrados en ese año");
            JLabel mensaje;
            mensaje = new JLabel("No hay sismos registrados en ese año");
            panel_RegistroSismos_D.add(mensaje);
            
        } else {
            int_enero = ControladorGraficos.graficoSismoPorMes(listaS, 0);
            int_febrero = ControladorGraficos.graficoSismoPorMes(listaS, 1);
            int_marzo = ControladorGraficos.graficoSismoPorMes(listaS, 2);
            int_abril = ControladorGraficos.graficoSismoPorMes(listaS, 3);
            int_mayo = ControladorGraficos.graficoSismoPorMes(listaS, 4);
            int_junio = ControladorGraficos.graficoSismoPorMes(listaS, 5);
            int_julio = ControladorGraficos.graficoSismoPorMes(listaS, 6);
            int_agosto = ControladorGraficos.graficoSismoPorMes(listaS, 7);
            int_setiembre = ControladorGraficos.graficoSismoPorMes(listaS, 8);
            int_octubre = ControladorGraficos.graficoSismoPorMes(listaS, 9);
            int_noviembre = ControladorGraficos.graficoSismoPorMes(listaS, 10);
            int_diciembre = ControladorGraficos.graficoSismoPorMes(listaS,11);
        }

        int[] valores = {int_enero,int_febrero,int_marzo,int_abril,int_mayo,int_junio,
            int_julio,int_agosto,int_setiembre,int_octubre,int_noviembre,int_diciembre};
        
        String[] arg1 = {"Enero","Febrero","Marzo","Abril","Maayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
        String[] arg2 = {"","","","","","","","","","","",""};
        //titulo, lateral 1, lateral 2
        String[] data = {"Grafico de Barras: Sismos por mes en: " + elDatoInput,"Meses","Cantidad"};
        //se crea el grafico
        migrafico.crear_grafico_de_barras(d, valores, arg2, arg1, data);
        //se crea un jlabel para colocar el grafico
        JLabel j = new JLabel();
        j.setBounds(0, 0, d.width, d.height);
        //se carga el grafico de memoria
        migrafico.cargar_grafico(j);
        //se añade al contenedor
        panel_RegistroSismos_D.add(j);
        this.repaint();
        
    }//GEN-LAST:event_botonMostrar_RegistroSismos_DActionPerformed

    private void botonMostrar_RegistroSismos_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMostrar_RegistroSismos_CActionPerformed
        try {
            SimpleDateFormat formato =new SimpleDateFormat("dd/MM/yy");
            Date fecha;
            Date fecha2;
            
            fecha = formato.parse(inputDia1Formated_RegistroSismos_C.getText());
            fecha2 = formato.parse(inputDia2Formated_RegistroSismos_C.getText());
            int int_sisFecha = ControladorGraficos.rangoFecha(ControladorArchivo.listaSismos(), fecha, fecha2);
            System.out.println(int_sisFecha);
            
            ArrayList lista =  new ArrayList();
            lista.add(fecha);
            lista.add(int_sisFecha);
            lista.add(fecha2);
            lista.add("");
            
            
            cargarValoresARangoFechas(lista);
            //grafTabFecha.graficoTabFecha(fecha, fecha2, panel_RegistroSismos_C);
        } catch (ParseException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_botonMostrar_RegistroSismos_CActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        comprobarLatLong_NuevoSismo();
    }//GEN-LAST:event_jButton1ActionPerformed
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
        
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ConfirmarSalida;
    private javax.swing.JTextArea DetalleSismo_DescripcionDetallada;
    private javax.swing.JTextField DetalleSismo_detalleFalla;
    private javax.swing.JTextField DetalleSismo_lugar;
    private javax.swing.JTextField DetalleSismo_magnitud;
    private javax.swing.JTextField DetalleSismo_momentoExacto;
    private javax.swing.JTextField DetalleSismo_origenFalla;
    private javax.swing.JTextField DetalleSismo_profundidad;
    private javax.swing.JTextField DetalleSismo_provincia;
    private javax.swing.JTextField DetalleSismo_ubicacionExacta;
    private javax.swing.JPanel Inicio;
    private javax.swing.JFrame MensajeError_NuevoSismo;
    private javax.swing.JPanel NuevoSismo;
    private javax.swing.JPanel NuevoSismo_Fecha;
    private javax.swing.JPanel NuevoSismo_UbicacionMapa;
    private javax.swing.JLayeredPane Paneles;
    private javax.swing.JPanel RegistroSismos;
    private javax.swing.JPanel RegistroSismos_A;
    private javax.swing.JPanel RegistroSismos_B;
    private javax.swing.JPanel RegistroSismos_C;
    private javax.swing.JPanel RegistroSismos_D;
    private javax.swing.JPanel RegistroSismos_E;
    private javax.swing.JTable TablaTodosSismos;
    private javax.swing.JPanel VentanaIntermedia;
    private javax.swing.JPanel VentanaIntermedia_DetalleSismo;
    private javax.swing.JButton botonAceptar_ConfirmarSalida;
    private javax.swing.JButton botonAceptar_NuevoSismo;
    private javax.swing.JButton botonAceptar_NuevoSismo_Fecha;
    private javax.swing.JButton botonAceptar_NuevoSismo_Ubicacion;
    private javax.swing.JButton botonAceptar_RegistroSismos;
    private javax.swing.JButton botonCancelar_ConfirmarSalida;
    private javax.swing.JButton botonCantSismosPorMesEnAño_RegistroSismos;
    private javax.swing.JButton botonCantSismosPorOrigen_RegistroSismos;
    private javax.swing.JButton botonClasificacionSismosPorMagnitud_RegistroSismos;
    private javax.swing.JButton botonEscogerFecha_NuevoSismo;
    private javax.swing.JButton botonEscogerFecha_NuevoSismo_Fecha;
    private javax.swing.JButton botonMostrar_RegistroSismos_A;
    private javax.swing.JButton botonMostrar_RegistroSismos_C;
    private javax.swing.JButton botonMostrar_RegistroSismos_D;
    private javax.swing.JButton botonNuevoSismo_Inicio;
    private javax.swing.JButton botonNuevoSismo_VentanaIntermedia;
    private javax.swing.JButton botonNuevoSismo_VentanaIntermedia1;
    private javax.swing.JButton botonRegistroSismos_Inicio;
    private javax.swing.JButton botonRegresar_NuevoSismo;
    private javax.swing.JButton botonRegresar_NuevoSismo1;
    private javax.swing.JButton botonRegresar_RegistroSismos_A;
    private javax.swing.JButton botonRegresar_RegistroSismos_B;
    private javax.swing.JButton botonRegresar_RegistroSismos_C;
    private javax.swing.JButton botonRegresar_RegistroSismos_D;
    private javax.swing.JButton botonRegresar_RegistroSismos_E;
    private javax.swing.JButton botonRegresar_VentanaIntermedia;
    private javax.swing.JButton botonSalir_Inicio;
    private javax.swing.JButton botonSalir_NuevoSismo;
    private javax.swing.JButton botonSalir_NuevoSismo1;
    private javax.swing.JButton botonSalir_NuevoSismo_Fecha;
    private javax.swing.JButton botonSalir_NuevoSismo_Ubicacion;
    private javax.swing.JButton botonSalir_NuevoSismo_Ubicacion1;
    private javax.swing.JButton botonSalir_RegistroSismos;
    private javax.swing.JButton botonSalir_RegistroSismos_A;
    private javax.swing.JButton botonSalir_RegistroSismos_B;
    private javax.swing.JButton botonSalir_RegistroSismos_C;
    private javax.swing.JButton botonSalir_RegistroSismos_D;
    private javax.swing.JButton botonSalir_RegistroSismos_E;
    private javax.swing.JButton botonSismosEntreFechas_RegistroSismos;
    private javax.swing.JButton botonSismosMagnitudPorProvincia_RegistroSismos;
    private javax.swing.JButton botonUbicacion_NuevoSismo;
    private javax.swing.JTextArea detalle_Inicio;
    private javax.swing.JFormattedTextField inputAñoFormated_RegistroSismos_D;
    private javax.swing.JTextArea inputDescripcionDetallada_NuevoSismo;
    private javax.swing.JTextField inputDetalleFalla_NuevoSismo;
    private javax.swing.JFormattedTextField inputDia1Formated_RegistroSismos_C;
    private javax.swing.JFormattedTextField inputDia2Formated_RegistroSismos_C;
    private javax.swing.JFormattedTextField inputDiaFormated_NuevoSismo_Fecha;
    private javax.swing.JFormattedTextField inputHoraFormated_NuevoSismo_Fecha;
    private javax.swing.JFormattedTextField inputLatitudFormated_NuevoSismo_Ubicacion;
    private javax.swing.JComboBox<String> inputListaProvincia_RegistroSismos_A;
    private javax.swing.JFormattedTextField inputLongitudFormated_NuevoSismo_Ubicacion;
    private javax.swing.JComboBox<String> inputLugarLista_NuevoSismo;
    private javax.swing.JTextField inputMagnitud_NuevoSismo;
    private javax.swing.JComboBox<String> inputOrigenFallaLista_NuevoSismo;
    private javax.swing.JTextField inputProfundidad_NuevoSismo;
    private javax.swing.JComboBox<String> inputProvinciaLista_NuevoSismo;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableSismoporFecha;
    private javax.swing.JTable jTabporMag;
    private javax.swing.JPanel mapa_NuevoSismo_Ubicacion;
    private javax.swing.JScrollPane panelScroll_VentanaInetermediaria;
    private javax.swing.JPanel panel_RegistroSismos_A;
    private javax.swing.JPanel panel_RegistroSismos_B;
    private javax.swing.JPanel panel_RegistroSismos_C;
    private javax.swing.JPanel panel_RegistroSismos_D;
    private javax.swing.JPanel panel_RegistroSismos_E;
    private javax.swing.JScrollPane scrollPaneDescripcionDetallada_NuevoSismo;
    private javax.swing.JScrollPane scrollPaneDescripcionDetallada_NuevoSismo1;
    private javax.swing.JLabel textA1_RegistroSismos;
    private javax.swing.JLabel textA2_RegistroSismos;
    private javax.swing.JLabel textA3_RegistroSismos;
    private javax.swing.JLabel textAño_RegistroSismos_D;
    private javax.swing.JLabel textB1_RegistroSismos;
    private javax.swing.JLabel textB2_RegistroSismos;
    private javax.swing.JLabel textB3_RegistroSismos;
    private javax.swing.JLabel textC1_RegistroSismos;
    private javax.swing.JLabel textC2_RegistroSismos;
    private javax.swing.JLabel textC3_RegistroSismos;
    private javax.swing.JLabel textConfirmarSalida_ConfirmarSalida;
    private javax.swing.JLabel textD1_RegistroSismos;
    private javax.swing.JLabel textD2_RegistroSismos;
    private javax.swing.JLabel textD3_RegistroSismos;
    private javax.swing.JLabel textDescripcion_NuevoSismo;
    private javax.swing.JLabel textDescripcion_NuevoSismo1;
    private javax.swing.JLabel textDesde_RegistroSismos_C;
    private javax.swing.JLabel textDetalleFalla_NuevoSismo;
    private javax.swing.JLabel textDetalleFalla_NuevoSismo1;
    private javax.swing.JLabel textDia_NuevoSismo_Fecha;
    private javax.swing.JLabel textE1_RegistroSismos;
    private javax.swing.JLabel textE2_RegistroSismos;
    private javax.swing.JLabel textE3_RegistroSismos;
    private javax.swing.JLabel textHasta_RegistroSismos_C;
    private javax.swing.JLabel textHora_NuevoSismo_Fecha;
    private javax.swing.JLabel textLatitud_NuevoSismo_Ubicacion;
    private javax.swing.JLabel textLongitud_NuevoSismo_Ubicacion;
    private javax.swing.JLabel textLugar_NuevoSismo;
    private javax.swing.JLabel textLugar_NuevoSismo1;
    private javax.swing.JLabel textMagnitud_NuevoSismo;
    private javax.swing.JLabel textMagnitud_NuevoSismo1;
    private javax.swing.JLabel textMomentoExacto_NuevoSismo;
    private javax.swing.JLabel textMomentoExacto_NuevoSismo1;
    private javax.swing.JLabel textMomentoExacto_NuevoSismo_Fecha;
    private javax.swing.JLabel textOrigenFalla_NuevoSismo;
    private javax.swing.JLabel textOrigenFalla_NuevoSismo1;
    private javax.swing.JLabel textProfundidad_NuevoSismo;
    private javax.swing.JLabel textProfundidad_NuevoSismo1;
    private javax.swing.JLabel textProvincia_NuevoSismo;
    private javax.swing.JLabel textProvincia_NuevoSismo1;
    private javax.swing.JLabel textProvincia_RegistroSismos_A;
    private javax.swing.JLabel textProvincia_RegistroSismos_B;
    private javax.swing.JLabel textTitulo_Inicio;
    private javax.swing.JLabel textTitulo_NuevoSismo;
    private javax.swing.JLabel textTitulo_NuevoSismo1;
    private javax.swing.JLabel textTitulo_NuevoSismo_Fecha;
    private javax.swing.JLabel textTitulo_NuevoSismo_Ubicacion;
    private javax.swing.JLabel textTitulo_RegistroSismos;
    private javax.swing.JLabel textTitulo_RegistroSismos_A;
    private javax.swing.JLabel textTitulo_RegistroSismos_B;
    private javax.swing.JLabel textTitulo_RegistroSismos_C;
    private javax.swing.JLabel textTitulo_RegistroSismos_D;
    private javax.swing.JLabel textTitulo_RegistroSismos_E;
    private javax.swing.JLabel textTitulo_VentanaIntermedia;
    private javax.swing.JLabel textUbicacion_NuevoSismo;
    private javax.swing.JLabel textUbicacion_NuevoSismo1;
    private javax.swing.JLabel validacionMagnitud_NuevoSismo;
    private javax.swing.JLabel validacionProfundidad_NuevoSismo;
    // End of variables declaration//GEN-END:variables


}

