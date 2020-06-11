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
public enum TLugar {
    
    Marítimo, Terrestre;

    @Override
    public String toString() {
        return this.name();
    }
    
}
