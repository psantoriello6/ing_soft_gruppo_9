/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.util.*;
import java.time.LocalDate;
/**
 *
 * @author 177748
 */
public class Prestito {
    private Set<Utente> utente;
    private Set<Libro> libriInPrestito;
    private LocalDate dataInizioPrestito;
    private boolean ritardoPrestito;
    
    public Prestito(){
        utente = new TreeSet<>();
        libriInPrestito = new TreeSet<>();
        
    }
    
    
    public void setDataInizioPrestito(LocalDate dataInizioPrestito){
        this.dataInizioPrestito = dataInizioPrestito;
    
    }
    
    public LocalDate getDataInizioPrestito(){
        return dataInizioPrestito;
    }
    
    
}
