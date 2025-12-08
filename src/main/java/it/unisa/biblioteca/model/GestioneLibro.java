/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
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
    }
    
    //metodo statico pubblico per restituire sempre la stessa istanza (unica) della classe stessa
    public static GestioneLibro getInstance(){
        if(instance == null){
            instance = new GestioneLibro();
        }
        
        return instance;
    
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
            throw new GestioneEccezioni("Inserimento del libro fallito. Codice identificativo già esistente");
        }
        
       if(controllaCodice(libro.getCodice())){
           libri.add(libro);
       }else{
            throw new GestioneEccezioni("Inserimento del libro fallito. Codice identificativo non valido");
        }
    
    }
    
    
    /**
     * @brief Metodo che permette di modificare un attributo di un libro nel TreeSet
     * @pre il libro che verrà inserito deve essere presente nel TreeSet
     * @post il libro avrà un o più attributi modificati
     * @param libro libro da modificare
     */
    @Override
    public void modifica(Libro libro) throws GestioneEccezioni{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       //poiché si modifica anche il titolo e la lista di libri è ordinata per titolo, si rimuova prima l'elemento da modificare
       this.elimina(libro);
       
       String titolo = null;
       String nomeAutore = null;
       String cognomeAutore = null;
       int annoDiPubblicazione = 0;
       String codice = null;
       int copieDisponibili = 0;
       //si modificano gli attributi
       try{
            System.out.println("Inserisci il titolo: ");
            titolo = br.readLine();
            System.out.println("Inserisci il nome dell'autore ");
            nomeAutore = br.readLine();
            System.out.println("Inserisci il cognome dell'autore:  ");
            cognomeAutore = br.readLine();
            System.out.println("Inserisci l'anno di pubblicazione: ");
            annoDiPubblicazione = Integer.parseInt(br.readLine());
            System.out.println("Inserisci il codice identificativo:  ");
            codice = br.readLine();
            System.out.println("Inserisci il numero di copie disponibili: ");
            copieDisponibili = Integer.parseInt(br.readLine());
            
       }catch(IOException ex){
           ex.printStackTrace();
       }
       libro.setTitolo(titolo);
       libro.setNomeAutore(nomeAutore);
       libro.setCognomeAutore(cognomeAutore);
       libro.setAnnoPubblicazione(annoDiPubblicazione);
       if(controllaCodice(codice) == false){
           throw new GestioneEccezioni("Modifica fallita: codice identificativo non valido");
       }else if(this.ricercaLibroCodice(codice) != null){
           throw new GestioneEccezioni("Modifica fallita: codice identificativo già esistente");
       }else{
           libro.setCodice(codice);
       }
           
       libro.setCopieDisponibili(copieDisponibili);
        
       //poi si inserisce nuovamente il libro
       this.inserisci(libro);
        
       
       
       
        
    
    
    }
    /**
     * @brief Metodo che permette di eliminare un libro nel TreeSet
     * @pre il libro che verrà inserito deve essere presente nel TreeSet
     * @post il libro sarà rimosso dal TreeSet
     * @param libro libro da eliminare
     */
    @Override
    public void elimina(Libro libro){
        libri.remove(libro);
    
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
            if(libro.getTitolo().equals(titolo)){
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
            if(libro.getNomeAutore().equals(nomeAutore) && libro.getCognomeAutore().equals(cognomeAutore)){
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
            if(libro.getCodice().equals(codice)){
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
    
}
