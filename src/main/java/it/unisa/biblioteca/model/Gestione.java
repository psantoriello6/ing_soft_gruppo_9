/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;

/**
 * @file Gestione.java
 * 
 * @brief Interfaccia generica utilizzata come modello dalle classi che gestiscono un tipo specifico di oggetto(Utente, Libro, Prestito)
 * L'interfaccia è usata dalle classi GestioneUtente, GestioneLibro e GestionePrestito.
 * Presenta i metodi inserisci, modifica ed elimina utilizzati per la gestione delle collezioni.
 * 
 * @param <T> il parametro che verrà gestito dalle classi che la implementano.
 * 
 * @date 3 Dicembre 2025
 */

public interface Gestione<T> {
    
    public void inserisci(T elemento);
    public void modifica(T elemento);
    public void elimina(T elemento);
  
}
