/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author angel
 */

/**
 * @file GestioneUtente.java
 * 
 * @brief Implementazione dei metodi per la gestione della collezione di oggetti Utente.
 * Questa classe GestioneUtente utilizza la collezione dati TreeSet per memorizzare e gestire gli utenti garantendo ordinamento
 * (laclasse utente implementa l'interfaccia Comparable).
 * La classe implementa i metodi inserisci, modifica, elimina, cercaUtenteMatricola, cercaUtenteCognome e salvaUtente.
 * 
 * @date 3 Dicembre 2025
 */

public class GestioneUtente implements Gestione<Utente>{
    private Set<Utente> utenti;
 
    /**
     * @brief Costruttore.
     * Inizializza la collezione creando un TreeSet.
     */
    
    public GestioneUtente(){
        utenti=new TreeSet<Utente>();
    }
    
    /**
     * 
     * @brief Inserimento utente.
     * Questo metodo permette l'inserimento di un utente nella collezione.
     * Se la matricola inserita è già associata ad un altro utente l'inserimento non è permesso.
     * 
     * @pre L'oggetto utente da inserire non deve essere già presente nella collezione.
     * @post L'oggetto utente viene inserito e la collezione viene aggiornata.
     * 
     * @param utente Oggetto utente da inserire nella collezione TreeSet.
     *
     */
    
    @Override
    public void inserisci(Utente utente){
    
    
    }
    
     /**
     * 
     * @brief Modifica utente.
     * Questo metodo permette la modifica di un utente presente nella collezione.
     * Se la matricola modificata è già associata ad un altro utente la modifica non è permessa.
     * 
     * @pre L'oggetto utente da modificare deve essere già presente nella condizione.
     * @post L'oggetto utente viene modificato e la collezione viene aggiornata.
     * 
     * @param utente Oggetto utente da modificare nella collezione TreeSet.
     *
     */
    
    @Override
    public void modifica(Utente utente){
    
    
    }
    
     /**
     * 
     * @brief Elimina utente.
     * Questo metodo permette l'eliminazione di un utente presente nella collezione.
     * 
     * @pre L'oggetto utente da eliminare deve essere già presente nella condizione.
     * @post L'oggetto utente viene eliminato e la collezione viene aggiornata.
     * 
     * @param utente Oggetto utente da eliminare nella collezione TreeSet.
     *
     */
   
    @Override
    public void elimina(Utente utente){
    
    
    }
    
     /**
     * 
     * @brief Ricerca dell'utente tramite numero di matricola.
     * Questo metodo permette la ricerca di un utente presente nella collezione mediante numero di matricola univoco.
     * 
     * @pre La collezione deve presentare almeno un utente.
     * @post L'oggetto utente cercato viene restituito.
     * 
     * @param matricola Stringa matricola da utilizzare per la ricerca.
     * @return Oggetto utente corrispondente alla matricola inserita.
     *
     */
    
    //riorda i throws
    public Utente cercaUtenteMatricola(String matricola){
        return null;
    }
    
     /**
     * 
     * @brief Ricerca dell'utente tramite cognome.
     * Questo metodo permette la ricerca di un utente presente nella collezione mediante il suo cognome.
     * 
     * @pre La collezione deve presentare almeno un utente.
     * @post L'oggetto utente cercato viene restituito.
     * 
     * @param cognome Stringa cognome da utilizzare per la ricerca.
     * @return Oggetto utente corrispondente al cognome inserito.
     *
     */
    //riorda i throws
    public Utente cercaUtenteCognome(String cognome){
        return null;
    }
    
    /**
     * 
     * @brief Salvataggio utenti su file.
     * Questo metodo permette il salvataggio della collezione di utenti su file.
     * 
     * @pre I dati relativi agli utenti sono stati inseriti nella collezione.
     * @post I dati relativi agli utenti sono esportati su file.
     * 
     * @param file Stringa nome del file da utilizzare per l'esportazione dei dati.
     *
     */
    
    //metodo per slavare INTERA struttura su file
    public void salvaUtenti(String file){
        
    }
    
}
