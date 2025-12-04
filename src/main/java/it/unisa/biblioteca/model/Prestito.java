/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.util.*;
import java.time.LocalDate;
/**
 * @file Prestito.java
 * @brief Questa classe contiene l'implementazione dell'entità Prestito
 *
 * La classe Prestito possiede i seguenti attributi: un attributo di tipo Utente, un attrbuto di tipo Libro e un attributo di tipo LocalDate per la data di restituzione del prestito.
 * La classe prevede metodi setter e getter, un metodo per evidenziare il ritardo di un prestito e un metodo per ordinare i prestiti per data di restituzione prevista
 * 
 * @date 3 Dicembre 2025
 */
public class Prestito implements Comparable<Prestito> {
    private Utente utente;
    private Libro libro;
    private LocalDate dataRestituzione;
    
    /**
     * @brief Costruttore che inizializza un Prestito
     * 
     * @param utente L'oggetto di tipo Utente che rappresenta l'utente di cui si vuole aggiungere un prestito
     * @param libro L'oggetto di tipo Libro che rappresenta il libro che l'utente vuole prendere in prestito
     * @param dataRestituzione  La data di restituzione del prestito
     */
    public Prestito(Utente utente, Libro libro, LocalDate dataRestituzione){
        this.utente = utente;
        this.libro = libro;
        this.dataRestituzione = dataRestituzione;
        
    }
    
    /**
     * 
     * @brief Metodo setter che permette di impostare la data di restituzione del prestito
     * 
     * @param dataRestituzione La data di restituzione del prestito
     */
    public void setDataRestituzione(LocalDate dataRestituzione){
        this.dataRestituzione = dataRestituzione;
    
    }
    
    /**
     * @brief Metodo getter che restituisce la data di restituzione del prestito
     *  
     * @return La data di restituzione del prestito
     */
     public LocalDate getDataRestituzione(){
        return dataRestituzione;
    
    }
    
    /**
     * 
     * @brief Metodo setter che permette di impostare un utente 
     * 
     * @param utente L'utente che vuole effettuare il prestito
     */
    public void setUtente(Utente utente){
        this.utente = utente;
    }
    
    /**
     * @brief Metodo getter che restituisce l'utente
     * 
     * @return L'utente che vuole effettuare il prestito
     */
    
    public Utente getUtente(){
        return utente;
    }
    
    /**
     * 
     * @brief Metodo setter che permette di impostare un libro
     * 
     * @param libro Il libro che un utente vuole prendere in prestito
     */
    
    public void setLibro(Libro libro){
        this.libro = libro;
    }
    
    /**
     * 
     * @brief Metodo getter che restituisce un libro
     * 
     * @return Il libro che un utente vuole prendere in prestito
     */
    public Libro getLibro(){
        return libro;
    }
    
    
    /**
     * 
     * @brief Metodo che permette di verificare se un prestito è in ritardo o meno
     * 
     * @param dataRestituzione La data di restituzione del prestito
     * @return Il valore booleano 'true' se la data in cui il prestito è restituito è oltre la data di restituzione prevista, altrimenti il valreo booleano 'false'
     * 
     */
    public boolean ritardoPrestito(LocalDate dataRestituzione){
        return false;
    
    }
    
    /**
     * 
     * @brief Metodo che permette di ordinare i prestiti per data di restituzione prevista
     * 
     * @param p Il prestito che si vuole ordinare
     * @return Il valore intero '1' se la data di restituzione del prestito corrente è successiva rispetto alla data di restituzione del prestito specificato come parametro; il valore intero '-1' 
     *         se la data di restituzione del prestito corrente è precedente rispetto alla data di restituzione del prestito specificato come parametro; il valore intero '0' se le due date sono uguali
     */
    
    @Override
    public int compareTo(Prestito p){
        return this.dataRestituzione.compareTo(p.dataRestituzione);
    
    }
    
    
    
}
