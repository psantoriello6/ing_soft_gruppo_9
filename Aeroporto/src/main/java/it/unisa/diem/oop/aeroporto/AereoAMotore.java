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
public class AereoAMotore extends Aeromobile{
    private final int numRotori;
    
    public AereoAMotore(String codice, int numRotori) {
        super(codice);
        this.numRotori = numRotori;
    }
    
    public int getNumRotori() {
        return numRotori;
    }
    
    @Override
    public String toString() {
        super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append("Tipo = Aereo a motore - Numero rotori = ").append(getNumRotori()).append("\n");
        return sb.toString();
    }
    
}
