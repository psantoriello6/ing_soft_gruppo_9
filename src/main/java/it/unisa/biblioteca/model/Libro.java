
/**
 * @file Libro.java
 * @brief il file contiene la rappresentazione di un libro.
 * 
 * Ogni libro ha i seguenti dati: titolo, nome e cognome Autore, anno di publicazione, codice idetificativo, e numero di copie disponibili.
 * Nel file sono presenti i metodi per la corretta gestione dei dati e per l'implemtazione in una collezione ordinata.
 * 
 * @date 03 Dicembre, 2025
 */
package it.unisa.biblioteca.model;
import java.io.*;
import java.util.*;


public class Libro implements Comparable<Libro>, Serializable {

    private String titolo;
    private String nomeAutore;
    private String cognomeAutore;
    private int annoPubblicazione;
    private String codice;
    private int copieDisponibili;

    /**
     * @brief inizializza i dati di un libro.
     * 
     * Libro() è il costruttore di questa classe, assegna i valori passati come parametri agli attributi.
     * 
     * @param titolo titolo del libro.
     * @param nomeAutore nome dell'autore del libro.
     * @param cognomeAutore cognome dell'autore del libro.
     * @param annoPubblicazione l'anno di pubblicazione del libro.
     * @param codice il codice identificativo del libro.
     * @param copieDisponibili numero di copie disponibili.
     */

    public Libro(String titolo, String nomeAutore, String cognomeAutore, int annoPubblicazione, String codice, int copieDisponibili) {
        this.titolo = titolo;
        this.nomeAutore = nomeAutore;
        this.cognomeAutore = cognomeAutore;
        this.annoPubblicazione = annoPubblicazione;
        this.codice = codice;
        this.copieDisponibili = copieDisponibili;
    }

    /**
     * @brief metodo per ottenre l'attributo titolo.
     * @return titolo del libro.
     */

    public String getTitolo() {
        return titolo;
    }

    /**
     * @brief metodo che permette di modificare l'attributo titolo.
     * @param titolo titolo del libro.
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @brief metodo che permette di ottenere l'attributo nomeAutore.
     * @return nome dell'autore del libro.
     */
    public String getNomeAutore(){
        return nomeAutore;
    }
    
    /**
     * @brief metodo per modificare l'attributo nomeAutore.
     * @param nomeAutore nome dell'autore del libro.
     */
    public void setNomeAutore(String nomeAutore){
        this.nomeAutore = nomeAutore;
    
    }
    
    /**
     * @brief metodo per ottenere l'attributo cognomeAutore.
     * @return cognome dell'autore del libro.
     */
    public String getCognomeAutore(){
        return cognomeAutore;
    }
    /**
     * @brief metodo per modificare l'attributo cognomeAutore.
     * @param cognomeAutore cognome dell'autore del libro.
     */
    public void setCognomeAutore(String cognomeAutore){
        this.cognomeAutore = cognomeAutore;
    
    }
    
    /**
     * @brief metodo per ottenere l'attributo annoPubblicazione.
     * @return l'anno di pubblicazione del libro.
     */
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    /**
     * @brief metodo per modificare l'attributo annoPubblicazione
     * @param annoPubblicazione l'anno di pubblicazione del libro.
     */
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    /**
     * @brief metodo per ottenere l'attributo codice.
     * @return il codice identificativo del libro.
     */
    public String getCodice() {
        return codice;
    }
    
    /**
     * @brief metodo per modificare l'attributo codice.
     * 
     * Il metodo consente la modifica dell'attributo solo se il codice rispetta il formato standard,
     * in caso contrario lancia un eccezione.
     * 
     * @pre il codice deve essere coerente con il formato standard CCDDDD.
     * @post l'attributo codice viene modificato.
     * 
     * @param codice  codice identificativo del libro.
     */
    public void setCodice(String codice) {
        //da modificare.
        this.codice = codice;
    }
    
    /**
     * @brief metodo per ottenere l'attributo copieDisponibili.
     * @return numero di copie disponibili.
     */
    public int getCopieDisponibili() {
        return copieDisponibili;
    }
    
    /**
     * @brief metodo per modificare l'attributo copieDisponibili.
     * @param copieDisponibili numero di copie disponibili.
     */
    public void setCopieDisponibili(int copieDisponibili) {
        this.copieDisponibili = copieDisponibili;
    }


    
    /**
     * @brief metodo per gestire l'ordinamento nella collezione sul titolo in ordine alfabetico.
     * @param l libro da comparare.
     * @return 0 se i titoli sono uguali, minore di 0 se il titolo corrente viene prima nel ordine alfabetico, maggiore di 0 se viene dopo.
     */
    @Override
    public int compareTo(Libro l){
        return this.titolo.compareToIgnoreCase(l.titolo);
    
    }

    /**
     * @brief metodo per controllare se ci sono copie disponibili
     * @return true se ci sono copie disponibili, false se non ci sono copie disponibili.
     */
    public boolean hasCopieDisponibili() {
        return copieDisponibili > 0;
    }

    /**
     * @brief metodo per diminuire di 1 il numero di copie disponibili.
     */
    public void decrementaCopie() {
        if (copieDisponibili > 0)
            copieDisponibili--;
    }

    /**
     * @brief metodo per incrementare di 1 il numero di copie disponibili.
     */
    public void incrementaCopie() {
        copieDisponibili++;
    }
    
    /**
     * @brief metodo per gestire l'ordinamento nella collezione sul titolo in ordine alfabetico. 
     * @param o libro da comparare.
     * @return true se l'oggetto è uguale a quello passato nel parametro(se hanno gli stessi codici), false se è diverso(se hanno codici diversi).
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        Libro l = (Libro) o;
        return Objects.equals(this.codice, l.codice);
    }

}

