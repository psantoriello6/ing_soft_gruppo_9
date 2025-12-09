/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.util.*;
/**
 * @file GestionePrestito.java
 * @author Gruppo 9
 * @brief Questa classe contiene l'implementazione della classe GestionePrestito
 * 
 * La classe GestionePrestito prevede un attributo che rappresenta la lista dei prestiti, realizzata 
 * mediante un'HashMap , un metodo per aggiungere un prestito e un metodo per registrare la restituzione di un prestito 
 * 
 * @date 3 dicembre 2025
 */

public class GestionePrestito {
    private Map<Utente, LinkedList<Libro>> prestitiAttivi;
    private static final int MAX_PRESTITI = 3;
    
    /**
     * @brief Costruttore della classe GestionePrestito
     * 
     * Il costruttore inizializza la collezione di prestiti utilizzando un'Hash Map
     * 
     */
     
    public GestionePrestito(){
        prestitiAttivi = new HashMap<>();
    
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
    
    public void registraPrestito(Prestito prestito) throws GestioneEccezioni{
        Utente utente = prestito.getUtente();
        Libro libro = prestito.getLibro();
        
        // Controllo disponibilità 
        if (!libro.hasCopieDisponibili()) { 
            throw new GestioneEccezioni("ERRORE: Il libro '" + libro.getTitolo() + "' non ha copie disponibili per il prestito.");
        }
        
        // Prende la lista di libri che l'utente ha già in prestito.
        LinkedList<Libro> libriUtente = prestitiAttivi.get(utente);
        
        // Controllo limite prestiti
        if (libriUtente != null && libriUtente.size() >= MAX_PRESTITI) {
            throw new GestioneEccezioni("ERRORE: L'utente " + utente.getNome() + " " + utente.getCognome() + 
                                            " ha raggiunto il limite massimo di " + MAX_PRESTITI + " libri in prestito.");
        }
        
        // Se l'utente non è ancora nella mappa, inizializza la lista.
        if (libriUtente == null) {
            libriUtente = new LinkedList<>();
            prestitiAttivi.put(utente, libriUtente);
        }
        
        // Aggiunge il libro alla lista dei prestiti dell'utente.
        libriUtente.add(libro);
        
        // Aggiorna le copie disponibili del libro.
        libro.decrementaCopie(); 
        
        System.out.println("La registrazione del prestito del libro '" + libro.getTitolo() + 
                           "' (Codice: " + libro.getCodice() + ") per l'utente con matricola: " + utente.getMatricola() + " è avvenuta con successo.");
    
    }
    
    /**
     * @brief Metodo che permette di registrare la restituzione di un libro in prestito 
     * @param prestito Il prestito di cui si vuole registrare la restituzione
     * 
     * @pre La lista dei prestiti attivi non deve essere vuota
     * 
     * @post La restituzione del prestito è stata registrata e il prestito è stato eliminato dalla lista dei prestiti attivi
     */
    public void restituisciPrestito(Prestito prestito) throws GestioneEccezioni{
        Utente utente = prestito.getUtente();
        Libro libro = prestito.getLibro();
        
        LinkedList<Libro> libriUtente = prestitiAttivi.get(utente);
        if (libriUtente == null || libriUtente.isEmpty()) {
            throw new GestioneEccezioni("ERRORE: L'utente " + utente.getNome() + " " + utente.getCognome() + " non ha alcun prestito attivo da restituire.");
        }
        
        boolean rimosso = libriUtente.remove(libro);
        
        if (!rimosso) {
            throw new GestioneEccezioni("ERRORE: Il libro '" + libro.getTitolo() + "' non risulta in prestito all'utente specificato.");
        }
        
        if (libriUtente.isEmpty()) {
            prestitiAttivi.remove(utente);
        }
        
        libro.incrementaCopie();
        
        System.out.println("La restituzione del libro '" + libro.getTitolo() + "' (Codice: " + libro.getCodice() + ") da parte dell'utente matricola: " + utente.getMatricola() + " è avvenuta con successo.");
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
