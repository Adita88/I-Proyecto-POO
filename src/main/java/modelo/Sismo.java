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

    public Sismo() {
    }

    public Sismo(ZonedDateTime fecha, ZonedDateTime momentoExacto, double profundidad, TFalla origenFalla, String detalle, double magnitud, double latitud, double longitud, String descripcionDetallada, TLugar lugar, TProvincia provincia) {
        this.fecha = fecha;
        this.momentoExacto = momentoExacto;
        this.profundidad = profundidad;
        this.origenFalla = origenFalla;
        this.detalle = detalle;
        this.magnitud = magnitud;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcionDetallada = descripcionDetallada;
        this.lugar = lugar;
        this.provincia = provincia;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public ZonedDateTime getMomentoExacto() {
        return momentoExacto;
    }

    public void setMomentoExacto(ZonedDateTime momentoExacto) {
        this.momentoExacto = momentoExacto;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public TFalla getOrigenFalla() {
        return origenFalla;
    }

    public void setOrigenFalla(TFalla origenFalla) {
        this.origenFalla = origenFalla;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcionDetallada() {
        return descripcionDetallada;
    }

    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    public TLugar getLugar() {
        return lugar;
    }

    public void setLugar(TLugar lugar) {
        this.lugar = lugar;
    }

    public TProvincia getProvincia() {
        return provincia;
    }

    public void setProvincia(TProvincia provincia) {
        this.provincia = provincia;
    }
            
}
