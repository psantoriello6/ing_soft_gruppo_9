/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecauniversitaria;

/**
 *
 * @author chiara
 */


public class Libro {

    private String titolo;
    private int annoPubblicazione;
    private String codice;
    private int copieDisponibili;

    // COSTRUTTORE

    public Libro(String titolo, int annoPubblicazione, String codice, int copieDisponibili) {
        this.titolo = titolo;
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

}

