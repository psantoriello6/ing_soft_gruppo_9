/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.aeroporto;

/**
 *
 * @author admin
 */
public class Aeromobile {
    private final String codice;
    private final int numeroSequenziale;
    private static int count=0;
    
    public Aeromobile(String codice) {
        this.codice=codice;
        this.numeroSequenziale=count++;
    }
    
    public String getCodice() {
        return codice;
    }
    
    public int getNumeroSequenziale() {
        return numeroSequenziale;
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Aeromobile n.").append(getNumeroSequenziale()).append(" - Codice= ").append(getCodice()).append("\n");
        return sb.toString();
    }
            
}
