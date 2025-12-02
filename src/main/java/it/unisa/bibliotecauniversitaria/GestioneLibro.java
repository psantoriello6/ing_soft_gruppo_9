/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecauniversitaria;
import java.util.*;
/**
 *
 * @author 177748
 */
public class GestioneLibro implements Gestione<Libro> {
    private Set<Libro> libri;
    
    public GestioneLibro(){
        libri = new TreeSet<>();
    }
    
    @Override
    public void inserisci(Libro libro){
    
    
    }
    
    @Override
    public void modifica(Libro libro){
    
    
    }
    
    @Override
    public void elimina(Libro libro){
    
    
    }
    
    public Libro ricercaLibro(String titolo){
        return null;
    
    
    }
    
    public Libro ricercaLibro(String nomeAutore, String cognomeAutore){
        return null;
    
    }
    
    public Libro ricercaLibro(int codice){
        return null;
    
    }
    
    public void salvaUtenti(String file){
    
    
    }
    
    
}
