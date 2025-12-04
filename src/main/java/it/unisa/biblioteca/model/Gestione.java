/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;

/**
 * @file Gestione.java
 * 
 * @brief Interfaccia generica utilizzata come modello dalle classi che gestiscono un tipo specifico di oggetto(Utente, Libro).
 * L'interfaccia è usata dalle classi GestioneUtente e GestioneLibro.
 * Presenta i metodi inserisci, modifica ed elimina utilizzati per la gestione delle collezioni.
 * 
 * @param <T> il parametro che verrà gestito dalle classi che la implementano.
 * 
 * @date 3 Dicembre 2025
 */

public interface Gestione<T> {
    
    /**
    * @brief Inserisce un nuovo elemento nella collezione.
    * Il metodo aggiunge alla collezione l'oggetto fornito come parametro. 
    * Gestisce eventuali controlli sull'unicità dei dati (Utente o Libro).
    *
    * @param elemento Oggetto di tipo T da inserire.
    */
    public void inserisci(T elemento);
    
    
    /**
    * @brief Modifica un elemento della collezione.
    * Il metodo modifica l'oggetto fornito come parametro. 
    * Gestisce eventuali controlli sull'unicità dei dati inseriti(Utente o Libro).
    *
    * @param elemento Oggetto di tipo T da modificare, contenente i dati aggiornati.
    */   
    public void modifica(T elemento);
    
    /**
    * @brief Elimina un elemento dalla collezione.
    * Il metodo elimina dalla collezione l'oggetto fornito come parametro. 
    *
    * @param elemento Oggetto di tipo T da eliminare.
    */      
    public void elimina(T elemento);
  
}
