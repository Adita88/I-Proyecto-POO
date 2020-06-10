/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public enum TProvincia {
    
    No_Aplica,
    San_Jose,
    Alajuela,
    Cartago,
    Heredia,
    Guanacaste,
    Puntarenas,
    Limon;

    @Override
    public String toString() {
        switch(this) {
            case No_Aplica: return "No Aplica";
            case San_Jose: return "San Jose";
            default: return this.name();
        }
    }
    
    
}
