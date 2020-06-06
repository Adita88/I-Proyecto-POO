/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.ZonedDateTime;

/**
 *
 * @author Usuario
 */
public class Sismo {
    
    /**
     * Variables de la clase Sismo
     */
    private ZonedDateTime fecha;
    private ZonedDateTime momentoExacto;
    private double profundidad;
    private TFalla origenFalla;
    private String detalle;
    private double magnitud;
    private double latitud;
    private double longitud;
    private String descripcionDetallada;
    private TLugar lugar;
    private TProvincia provincia;
    
    
    
}
