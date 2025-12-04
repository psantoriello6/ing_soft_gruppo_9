/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.util.*;
/**
 * @file GestionePrestito.java
 * @brief Questa classe contiene l'implementazione della classe GestionePrestito
 * 
 * La classe GestionePrestito prevede un attributo che rappresenta la lista dei prestiti, realizzata 
 * mediante un'HashMap , un metodo per aggiungere un prestito e un metodo per registrare la restituzione di un prestito 
 * 
 * @date 3 dicembre 2025
 */

public class GestionePrestito {
    private Map<Utente, LinkedList<Libro>> prestito;
    
    /**
     * @brief Costruttore della classe GestionePrestito
     * 
     * Il costruttore inizializza la collezione di prestiti utilizzando un'Hash Map
     * 
     */
     
    public GestionePrestito(){
        prestito = new HashMap<>();
    
    }
    
    /**
     * @brief Metodo che permette di registrare un prestito
     * @param prestito Il prestito che si vuole registrare
     * 
     * @pre Il libro che si vuole prestare deve avere almento una copia disponibile
     * @pre L'utente deve avere al massimo due libri in prestito contemporaneamente
     * 
     * @post Il prestito effettuato è stato registrato ed  inserito nella lista dei prestiti attivi
     */
    
    public void registraPrestito(Prestito prestito){
    
    
    }
    
    /**
     * @brief Metodo che permette di registrare la restituzione di un libro in prestito 
     * @param prestito Il prestito di cui si vuole registrare la restituzione
     * 
     * @pre La lista dei prestiti attivi non deve essere vuota
     * 
     * @post La restituzione del prestito è stata registrata e il prestito è stato eliminato dalla lista dei prestiti attivi
     */
    public void restituisciPrestito(Prestito prestito){
     
    }
    
    /**
     * @brief Metodo che permette di esportare su un file i dati relativi ad un presitio
     * @param file Il nome del file su cui devono essere salvati i dati di un prestito
     * 
     * @pre I dati relativi ad un prestito sono stati inseriti nella collezione
     * 
     * @post I dati relativi ad un prestito sono stati esportati sul file
     *  
     */
    public void salvaPrestiti(String file){
    
    
    }
}
