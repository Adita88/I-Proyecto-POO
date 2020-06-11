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
public enum TFalla {
    
    Subduccion_Placas,
    Choque_Placas,
    Tectónico_Subducción,
    Deformación_Interna,
    Fallamiento_Local,
    Tectónico_Falla_Local;

    @Override
    public String toString() {
        switch(this) {
            case Subduccion_Placas: return "Subduccion de Placas";
            case Choque_Placas: return "Choque de Placas";
            case Tectónico_Subducción: return "Tectónico por Subducción";
            case Deformación_Interna: return "Deformación Interna";
            case Fallamiento_Local: return "Fallamiento Local";
            case Tectónico_Falla_Local: return "Tectónico por Falla Local";
            default: throw new IllegalArgumentException();
        }
    }
    
}
