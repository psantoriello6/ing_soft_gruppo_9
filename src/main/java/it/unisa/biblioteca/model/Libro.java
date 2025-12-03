/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.io.*;
import java.util.*;
/**
 *
 * @author chiara
 */

public class Libro implements Comparable<Libro>, Serializable {

    private String titolo;
    private String nomeAutore;
    private String cognomeAutore;
    private int annoPubblicazione;
    private String codice;
    private int copieDisponibili;

    // COSTRUTTORE

    public Libro(String titolo, String nomeAutore, String cognomeAutore, int annoPubblicazione, String codice, int copieDisponibili) {
        this.titolo = titolo;
        this.nomeAutore = nomeAutore;
        this.cognomeAutore = cognomeAutore;
        this.annoPubblicazione = annoPubblicazione;
        this.codice = codice;
        this.copieDisponibili = copieDisponibili;
    }

    // GETTER E SETTER

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    
    public String getNomeAutore(){
        return nomeAutore;
    }
    
    public void setNomeAutore(String nomeAutore){
        this.nomeAutore = nomeAutore;
    
    }
    
    public String getCognomeAutore(){
        return cognomeAutore;
    }
    
    public void setCognomeAutore(String cognomeAutore){
        this.cognomeAutore = cognomeAutore;
    
    }
    
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public int getCopieDisponibili() {
        return copieDisponibili;
    }

    public void setCopieDisponibili(int copieDisponibili) {
        this.copieDisponibili = copieDisponibili;
    }

    // METODI 
    
    @Override
    public int compareTo(Libro l){
        return this.titolo.compareToIgnoreCase(l.titolo);
    
    }

    public boolean hasCopieDisponibili() {
        return copieDisponibili > 0;
    }

    public void decrementaCopie() {
        if (copieDisponibili > 0)
            copieDisponibili--;
    }

    public void incrementaCopie() {
        copieDisponibili++;
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(codice);
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        Libro l = (Libro) o;
        return Objects.equals(this.codice, l.codice);
    }

}

