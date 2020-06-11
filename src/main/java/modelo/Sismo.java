/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Sismo {
    
    /**
     * Variables de la clase Sismo
     */
    private Date momentoExacto;
    private double profundidad;
    private TFalla origenFalla;
    private String detalleFalla;
    private double magnitud;
    private double latitud;
    private double longitud;
    private String descripcionDetallada;
    private TLugar lugar;
    private TProvincia provincia;

    public Sismo() {
    }
    

    public Sismo(Date momentoExacto, double profundidad, TFalla origenFalla, String detalleFalla, double magnitud, double latitud, double longitud, String descripcionDetallada, TLugar lugar, TProvincia provincia) {

        this.momentoExacto = momentoExacto;
        this.profundidad = profundidad;
        this.origenFalla = origenFalla;
        this.detalleFalla = detalleFalla;
        this.magnitud = magnitud;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcionDetallada = descripcionDetallada;
        this.lugar = lugar;
        this.provincia = provincia;
    }


    public Date getMomentoExacto() {
        return momentoExacto;
    }

    public void setMomentoExacto(Date momentoExacto) {
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

    public String getDetalleFalla() {
        return detalleFalla;
    }

    public void setDetalleFalla(String detalleFalla) {
        this.detalleFalla = detalleFalla;
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

    @Override
    public String toString() {
        return "\nSismo{" + "momentoExacto: " + momentoExacto + ", profundidad: " + profundidad +
                ", origenFalla: " + origenFalla + ", detalleFalla: " + detalleFalla +
                ", magnitud: " + magnitud + ", latitud: " + latitud + ", longitud: " + longitud +
                ", descripcionDetallada: " + descripcionDetallada + ", lugar: " + lugar +
                ", provincia: " + provincia + '}';
    }
      
    
}
