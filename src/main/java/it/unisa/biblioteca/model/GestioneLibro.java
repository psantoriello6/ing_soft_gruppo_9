/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.io.BufferedInputStream;
import java.util.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

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
    private static GestioneLibro instance; //variabile privata e statica dello stesso tipo della classe
    /**
     * @brief Costruttore che inizializza il TreeSet di libri
     */
    
    //costruttore privato per aderire al desgin Pattern Singleton
    private GestioneLibro(){
        libri = new TreeSet<>();
        this.caricaLibri("libri.dat");
    }
    
    //metodo statico pubblico per restituire sempre la stessa istanza (unica) della classe stessa
    
    public static GestioneLibro getInstance(){
        if(instance == null){
            instance = new GestioneLibro();
        }
        
        return instance;
    
    }
    
    //metodo utile per i test: serve a svuotare la memoria
    public void reset(){
        instance = null;
    }
    /**
     * @brief Metodo che permette di inserire un libro nel TreeSet
     * @pre il libro che verrà inserito non dovrà essere già presente
     * @post il libro sarà inserito nel TreeSet
     * @param libro libro da inserire
     */
    @Override
    public void inserisci(Libro libro) throws GestioneEccezioni{
        if(libri.contains(libro)){
            throw new GestioneEccezioni("Inserimento del libro " + libro.getTitolo() + " fallito. Il codice identificativo " + libro.getCodice() + " è già esistente");
        }
        
       if(controllaCodice(libro.getCodice())){
           libri.add(libro);
       }else{
            throw new GestioneEccezioni("Inserimento del libro " + libro.getTitolo() + " fallito. Il codice identificativo " + libro.getCodice() + " non è valido");
        }
    
    }
    
    
    /**
     * @brief Metodo che permette di modificare un attributo di un libro nel TreeSet
     * @pre il libro che verrà inserito deve essere presente nel TreeSet
     * @post il libro avrà un o più attributi modificati
     * @param libroModificato libro modificato
     */
    @Override
    public void modifica(Libro libroModificato) throws GestioneEccezioni{
      
        //si ricerca il libro da modificare in base al codice
       Libro libroInMemoria = this.ricercaLibroCodice(libroModificato.getCodice());
       
       //si rimuove il libro trovato dalla lista
       libri.remove(libroInMemoria);
       
       
       //si modificano gli attributi sostituendoli con gli attributi di libroModificato
       libroInMemoria.setTitolo(libroModificato.getTitolo());
       libroInMemoria.setNomeAutore(libroModificato.getNomeAutore());
       libroInMemoria.setCognomeAutore(libroModificato.getCognomeAutore());
       libroInMemoria.setAnnoPubblicazione(libroModificato.getAnnoPubblicazione());
       libroInMemoria.setCopieDisponibili(libroModificato.getCopieDisponibili());
       
       
       //si riaggiunge nuovamente il libro
       libri.add(libroInMemoria);
       
       
}
    /**
     * @brief Metodo che permette di eliminare un libro nel TreeSet
     * @pre il libro che verrà inserito deve essere presente nel TreeSet
     * @post il libro sarà rimosso dal TreeSet
     * @param libro libro da eliminare
     */
    @Override
    public void elimina(Libro libro){
        if(libro.getPrestitiAttivi() > 0){
            try{
                throw new GestioneEccezioni("Eliminazione fallita. Il libro " + libro.getTitolo() + " ha attualmente prestiti attivi");
            
            }catch(GestioneEccezioni ex){
            
            }
        }else if(libro.getPrestitiAttivi() == 0){
             libri.remove(libro);
        }
        
    }
    /**
     * @brief Metodo che permette di ricercare un libro nel TreeSet mediante il titolo
     * @pre il titolo deve essere esistente
     * @post il valore di ritorno deve essere il libro che corrisponde alla ricerca
     * @param titolo attributo del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca
     */
    public Libro ricercaLibroTitolo(String titolo) throws GestioneEccezioni{
        Libro libroTrovato = null;
        for(Libro libro: libri){
            if(libro.getTitolo().equalsIgnoreCase(titolo)){
                libroTrovato = libro;
            }
        
        }
        
        if(libroTrovato == null){
            throw new GestioneEccezioni("Ricerca fallita: libro non trovato!");
        }
        
        return libroTrovato;
        
    
    
    }
    /**
     * @brief Metodo che permette di ricercare un libro nel TreeSet mediante il nome e cognome dell'autore
     * @pre il nome e cognome dell'autore devono essere esistenti
     * @post il valore di ritorno deve essere il libro che corrisponde alla ricerca
     * @param nomeAutore attributo del libro che viene usato per la ricerca
     * @param cognomeAutore attributo del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca
     */
    public Libro ricercaLibroAutore(String nomeAutore, String cognomeAutore) throws GestioneEccezioni{
        Libro libroTrovato = null;
        for(Libro libro: libri){
            if(libro.getNomeAutore().equalsIgnoreCase(nomeAutore) && libro.getCognomeAutore().equalsIgnoreCase(cognomeAutore)){
                libroTrovato = libro;
            }
        }
        
        if(libroTrovato == null){
            throw new GestioneEccezioni("Ricerca fallita: libro non trovato!");
        }
        
        return libroTrovato;
    
    }
    /**
     * @brief Metodo che permette di ricercare un libro nel TreeSet mediante il codice ISBN
     * @pre il codice deve essere esistente
     * @post il valore di ritorno deve essere il libro che corrisponde alla ricerca
     * @param codice attributo del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca
     */
    public Libro ricercaLibroCodice(String codice) throws GestioneEccezioni{
        Libro libroTrovato = null;
        for(Libro libro: libri){
            if(libro.getCodice().equalsIgnoreCase(codice)){
                libroTrovato = libro;
            
            }
        }
        
        if(libroTrovato == null){
            throw new GestioneEccezioni("Ricerca fallita: libro non trovato!");
        }
        
        return libroTrovato;
    
    }
    /**
     * @brief Metodo che permette di salvare tutti i libri del TreeSet nel file di testo
     * @post il sistema salverà la collection di libri su file
     * @param file il file di testo su cui vengono salvati tutti i libri presenti
     */
    public void salvaLibri(String file){
        
        try(ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            objOut.writeObject(libri);
            System.out.println("Dato salvati con successo sul file: ");
            for(Libro l : libri){
                System.out.println(l.toString());
            }
            
        }catch(IOException ex){
            System.err.println("Errore durante il salvataggio: " + ex.getMessage());
            ex.printStackTrace();
        }
    
    
    }
    
    public Set<Libro> caricaLibri(String file){
        Set<Libro> listaLibri = new TreeSet<>();
        File fileBinario = new File(file);
        if(!fileBinario.exists()){
            System.err.println("File binario non trovato");
            return null;
        }
        
        try(ObjectInputStream objIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            listaLibri = (Set<Libro>)objIn.readObject();
            
            System.out.println("Dati caricati con successo dal file");
            for(Libro l: listaLibri){
                System.out.println(l.toString());
            }
            
        
        }catch(Exception ex){
            System.err.println("Errore durante il caricamento da binario: " + ex.getMessage());
        }
        
        return listaLibri;
    }
    
    //metodo privato per controllare se il codice identificativo del libro è valido o meno
    private boolean controllaCodice(String codice){
        int lunghezzaCodice = codice.length();
        if(lunghezzaCodice == 6){
            for(int i = 0;  i < 2; i++){
                if(!Character.isLetter(codice.charAt(i))){
                    return false;
                }
            
            }
            
            for(int i = 2; i < lunghezzaCodice; i++){
                if(!Character.isDigit(codice.charAt(i))){
                    return false;
                }
            
            }
            
            return true;
        }
        
        return false;
    }
    
    public Set<Libro> getSetLibro(){
        return libri;
    }
    
}
