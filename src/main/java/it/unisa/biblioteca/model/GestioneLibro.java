/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.util.*;
/**
 * @file GestioneLibro.java
 * @author Gruppo 9
 * @brief Gestione di una collezione (TreeSet) di libri che si occupa di inserimento, modifica, eliminazione, ricerca e salvataggio su un file di testo
 * 
 * @date 03/12/2025
 * @author pasqu
 */
public class GestioneLibro implements Gestione<Libro> {
    private Set<Libro> libri;
    /**
     * @brief Costruttore che inizializza il TreeSet di libri
     */
    public GestioneLibro(){
        libri = new TreeSet<>();
    }
    /**
     * @brief Metodo che permette di inserire un libro nel TreeSet
     * @pre il libro che verrà inserito non dovrà essere già presente
     * @post il libro sarà inserito nel TreeSet
     * @param libro libro da inserire
     */
    @Override
    public void inserisci(Libro libro){
    
    
    }
    /**
     * @brief Metodo che permette di modificare un attributo di un libro nel TreeSet
     * @pre il libro che verrà inserito deve essere presente nel TreeSet
     * @post il libro avrà un o più attributi modificati
     * @param libro libro da modificare
     */
    @Override
    public void modifica(Libro libro){
    
    
    }
    /**
     * @brief Metodo che permette di eliminare un libro nel TreeSet
     * @pre il libro che verrà inserito deve essere presente nel TreeSet
     * @post il libro sarà rimosso dal TreeSet
     * @param libro libro da eliminare
     */
    @Override
    public void elimina(Libro libro){
    
    
    }
    /**
     * @brief Metodo che permette di ricercare un libro nel TreeSet mediante il titolo
     * @pre il titolo deve essere esistente
     * @post il valore di ritorno deve essere il libro che corrisponde alla ricerca
     * @param titolo attributo del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca
     */
    public Libro ricercaLibro(String titolo){
        return null;
    
    
    }
    /**
     * @brief Metodo che permette di ricercare un libro nel TreeSet mediante il nome e cognome dell'autore
     * @pre il nome e cognome dell'autore devono essere esistenti
     * @post il valore di ritorno deve essere il libro che corrisponde alla ricerca
     * @param nomeAutore attributo del libro che viene usato per la ricerca
     * @param cognomeAutore attributo del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca
     */
    public Libro ricercaLibro(String nomeAutore, String cognomeAutore){
        return null;
    
    }
    /**
     * @brief Metodo che permette di ricercare un libro nel TreeSet mediante il codice ISBN
     * @pre il codice deve essere esistente
     * @post il valore di ritorno deve essere il libro che corrisponde alla ricerca
     * @param codice attributo del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca
     */
    public Libro ricercaLibro(int codice){
        return null;
    
    }
    /**
     * @brief Metodo che permette di salvare tutti i libri del TreeSet nel file di testo
     * @post il sistema salverà la collection di libri su file
     * @param file il file di testo su cui vengono salvati tutti i libri presenti
     */
    public void salvaLibri(String file){
    
    
    }
    
    
}
