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
 * @brief La classe GestioneLibro permette di gestire una collezione di libri
 * 
 *  In particolare, la classe contiene dei metodi che si occupano di inserire un libro all'interno della collezione , modificare un libro, eliminare un libro dalla collezione e ricercare un libro in base a 
 *  specifici parametri di ricerca; inoltre la classe contiene metodi di utilità per il caricamento e il salvataggio della collezione dei libri su un file binario 
 * @date 03/12/2025
 */
public class GestioneLibro implements Gestione<Libro> {
    
    /**
     * @brief Variabile d'istanza privata che rappresenta un set di libri
     */
    private Set<Libro> libri;
    
    /**
     * @brief Variabile d'istanza privata e statica che rappresenta l'istanza della classe GestioneLibro
     */
    private static GestioneLibro instance;
    
    /**
     * @brief Variabile di istanza privata che rappresenta il nome del file binario in cui salvare e da cui caricare informazioni relative ai vari libri
     */
    private String nomeFile = "libri.dat";  
    
    /**
     * @brief Costruttore privato della classe GestioneLibro
     *  Il costruttore aderisce al design pattern Singleton e permette di inizializzare il set di libri e di caricare dal file binario l'eventuale collezione di libri, se presente
     *  
     */
    private GestioneLibro(){
        libri = new TreeSet<>();
        this.caricaLibri(nomeFile);
    }
    
    /**
     * @brief Metodo statico pubblico della classe GestioneLibro
     *  Il metodo aderisce al design pattern Singleton e permette di restituire sempre la stessa (ed unica) istanza della classe GestioneLibro
     * 
     * @return L'istanza (unica) della classe GestioneLibro
     */
    
    public static GestioneLibro getInstance(){
        if(instance == null){
            instance = new GestioneLibro();
        }
        
        return instance;
    
    }
    
    /**
     * @brief Metodo di utilità che permette di "svuotare le memoria" reinizializzando l'istanza della classe GestioneLibro a null
     *  Questo metodo è utilizzato nei test per rilasciare le risorse utilizzate dopo aver eseguito ogni singolo metod di test
     */
    public void reset(){
        instance = null;
    }
    
    /**
     * @brief Metodo che permette di inserire un libro nel TreeSet
     * @pre il libro che verrà inserito non dovrà essere già presente
     * @post il libro sarà inserito nel TreeSet
     * @param libro libro da inserire
     * @throws GestioneEccezioni
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
       this.salvaLibri(nomeFile);
    
    }
    
    /**
     * @brief Metodo privato che permette di controllare se il codice identificativo del libro è valido o meno
     * @param codice il codice identificativo del libro
     * @return il valore booleano 'true' se il codice identificativo del libro è valido, il valore booleano 'false' se invece il codice identificativo non è valido
     */
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
    
    
    /**
     * @brief Metodo che permette di modificare un attributo di un libro nel TreeSet
     * @pre il libro che verrà inserito deve essere presente nel TreeSet
     * @post il libro avrà un o più attributi modificati
     * @param libroModificato libro modificato
     * @throws GestioneEccezioni
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
       this.salvaLibri(nomeFile);
       
       
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
             this.salvaLibri(nomeFile);
        }
        
    }
    
    /**
     * @brief Metodo che permette di ricercare un libro nel TreeSet mediante il titolo
     * @pre il titolo deve essere esistente
     * @post il valore di ritorno deve essere il libro che corrisponde alla ricerca
     * @param titolo titolo del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca per titolo
     * @throws GestioneEccezioni
     */
    public Libro ricercaLibroTitolo(String titolo) throws GestioneEccezioni{
        Libro libroTrovato = null;
        for(Libro libro: libri){
            if(libro.getTitolo().toLowerCase().contains(titolo.toLowerCase())){
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
     * @param nomeAutore nome dell'autore del libro che viene usato per la ricerca
     * @param cognomeAutore cognome dell'autore del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca per autore
     * @throws GestioneEccezioni
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
     * @brief Metodo che permette di ricercare un libro nel TreeSet mediante il codice identificativo
     * @pre il codice deve essere esistente
     * @post il valore di ritorno deve essere il libro che corrisponde alla ricerca
     * @param codice codice del libro che viene usato per la ricerca
     * @return il libro che corrisponde alla ricerca per codice
     * @throws GestioneEccezioni
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
     * @brief Metodo di utilità che permette di cambiare file binario durante i test
     * @param nuovoFile il nome del file di test da usare
     */
    public void setNomeFile(String nuovoFile){
        this.nomeFile = nuovoFile;
        this.libri.clear();
        this.caricaLibri(nomeFile);
    
    }
    
    /**
     * @brief Metodo che permette di salvare tutti i libri del TreeSet in un file binario con estensione .dat
     * @post il sistema salverà la collection di libri su file
     * @param file il file binario su cui vengono salvati tutti i libri presenti
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
    
    /**
     * @brief Metodo che permette di caricare da un file binario con estensione .dat tutti i libri della collezione 
     * @post la collezione di libri è stato caricata dal file e stampata su terminale
     * @param file il file binario da cui caricare le informazioni
     * @return la collezione di libri caricata dal file binario
     */
    
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
            //listaLibri = new TreeSet<>();
        }
        
        return listaLibri;
    }
    
    /**
     * @brief Metodo di utilità che permette di ottenere l'attributo che rappresenta la collezione (set) di libri
     * @return la collezione di libri
     */
    
    public Set<Libro> getSetLibro(){
        return libri;
    }
    
    /**
     * @brief Metodo di utilità che permette di modificare l'attributo che rappresenta la collezione (set) di lobri
     * @param libriSet un set di libri
     */
    
    public void setLibroSet(Set<Libro> libriSet){
        this.libri = libriSet;
    }
    
}
