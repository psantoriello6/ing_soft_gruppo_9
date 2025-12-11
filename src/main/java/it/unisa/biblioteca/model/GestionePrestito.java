/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
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
    private Map<String, LocalDate> scadenzePrestiti;
    private static final int MAX_PRESTITI = 3;
    private static final String PRESTITI_FILE = "prestiti.dat";
    private static final String DATE_FILE = "date.dat";
    private static GestionePrestito instance = null; //attributo per ottenere lo stesso oggetto in classi diverse (pattern Singleton)
    
    /**
     * @brief Costruttore della classe GestionePrestito
     * 
     * Il costruttore inizializza la collezione di prestiti utilizzando un'Hash Map
     * 
     */
     
    private GestionePrestito(){
        prestitiAttivi = new HashMap<>();
        scadenzePrestiti = new HashMap<>();
        caricaPrestiti();
        caricaDate();
    }
    
    public static GestionePrestito getInstance(){
        if(instance==null){
            instance=new GestionePrestito();
        }
        return instance;
    }
    
    public void reset(){
        instance = null;
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
        
        if (libriUtente != null && libriUtente.contains(libro)) {
            throw new GestioneEccezioni("ERRORE: L'utente ha già in prestito il libro '" + libro.getTitolo() + "'. Non è possibile prenderne un'altra copia.");
        }
        
        // Se l'utente non è ancora nella mappa, inizializza la lista.
        if (libriUtente == null) {
            libriUtente = new LinkedList<>();
            prestitiAttivi.put(utente, libriUtente);
        }
        
        // Aggiunge il libro alla lista dei prestiti dell'utente.
        libriUtente.add(libro);
        
        String chiave = generaChiave(utente, libro);
        scadenzePrestiti.put(chiave, prestito.getDataRestituzione());
        
        // Aggiorna le copie disponibili del libro.
        libro.decrementaCopie();
        libro.incrementaPrestiti();
        
        salvaPrestiti();
        salvaDate();
        
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
        libro.decrementaPrestiti();
        
        String chiave = generaChiave(utente, libro);
        scadenzePrestiti.remove(chiave);
        
        salvaPrestiti();
        salvaDate();
        
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
    public void salvaPrestiti(){
    
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PRESTITI_FILE))){
            out.writeObject(prestitiAttivi);
            System.out.println("Dati Salvati corretamente in: " + PRESTITI_FILE);
        }catch(IOException e){
            System.err.println("Errore dutrante il caricamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
        
    public void salvaDate(){
    
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATE_FILE))){
            out.writeObject(scadenzePrestiti);
            System.out.println("Dati Salvati corretamente in: " + DATE_FILE);
        }catch(IOException e){
            System.err.println("Errore dutrante il caricamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void caricaPrestiti(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(PRESTITI_FILE)) ){
            this.prestitiAttivi = (Map<Utente, LinkedList<Libro>>)in.readObject();
            System.out.println("Dati caricati: " + prestitiAttivi.size() + " utenti.");
        }catch(FileNotFoundException e){
            System.out.println("File Dati non trovato, Creazione nuova struttura dati!");
            this.prestitiAttivi= new HashMap<>();
        }catch(IOException | ClassNotFoundException e){
            System.err.println("Errore dutrante il caricamento: " + e.getMessage());
            this.prestitiAttivi= new HashMap<>();
        }
    }
    
    public void caricaDate(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATE_FILE)) ){
            this.scadenzePrestiti = (Map<String, LocalDate>)in.readObject();
            System.out.println("Dati caricati: " + prestitiAttivi.size() + " utenti.");
        }catch(FileNotFoundException e){
            System.out.println("File Dati non trovato, Creazione nuova struttura dati!");
            this.scadenzePrestiti= new HashMap<>();
        }catch(IOException | ClassNotFoundException e){
            System.err.println("Errore dutrante il caricamento: " + e.getMessage());
            this.scadenzePrestiti= new HashMap<>();
        }
    }
    
    
    private String generaChiave(Utente u, Libro l) {
        return u.getMatricola() + "_" + l.getCodice();
    }
    
    public List<Prestito> getElencoPrestitiCompleto() {
        List<Prestito> elencoCompleto = new ArrayList<>();
        
        for (Map.Entry<Utente, LinkedList<Libro>> entry : prestitiAttivi.entrySet()) {
            Utente utente = entry.getKey();
            LinkedList<Libro> libriInPrestito = entry.getValue();
            
            for (Libro libro : libriInPrestito) {
                String chiaveUnivoca = utente.getMatricola() + "_" + libro.getCodice();
                LocalDate dataScadenza = scadenzePrestiti.get(chiaveUnivoca);
                
                Prestito p = new Prestito(utente, libro, dataScadenza);
                elencoCompleto.add(p);
            }
        }
        return elencoCompleto;
    }
}
