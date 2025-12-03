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
 * @brief Questo file contiene l'implementazione dell'entità Prestito
 *
 * @author 177748
 */
public class Prestito implements Comparable<Prestito> {
    private Utente utente;
    private Libro libro;
    private LocalDate dataRestituzione;
    
    /**
     * @brief Costruttore della classe Prestito
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
     * @brief metodo setter per impostare la data di restituzione del prestito
     * 
     * @param dataRestituzione La data di restituzione del prestito
     */
    public void setDataRestituzione(LocalDate dataRestituzione){
        this.dataRestituzione = dataRestituzione;
    
    }
    
    /**
     * @brief metodo getter per restituire la data di restituzione del prestito
     *  
     * @return La data di restituzione del prestito
     */
     public LocalDate getDataRestituzione(){
        return dataRestituzione;
    
    }
    
    /**
     * 
     * @brief metodo setter per impostare l'utente 
     * 
     * @param utente L'utente che vuole effettuare il prestito
     */
    public void setUtente(Utente utente){
        this.utente = utente;
    }
    
    /**
     * @brief metodo getter per restituire l'utente
     * 
     * @return L'utente che vuole effettuare il prestito
     */
    
    public Utente getUtente(){
        return utente;
    }
    
    /**
     * 
     * @brief metodo setter per impostare il libro
     * 
     * @param libro Il libro che un utente vuole prendere in prestito
     */
    
    public void setLibro(Libro libro){
        this.libro = libro;
    }
    
    /**
     * 
     * @brief metodo getter per restituire il libro
     * 
     * @return Il libro che un utente vuole prendere in prestito
     */
    public Libro getLibro(){
        return libro;
    }
    
    
    /**
     * 
     * @brief metodo che permette di verificare se un prestito è in ritardo o meno
     * 
     * @param dataRestituzione La data di restituzione del prestito
     * @return Il valore booleano 'true' se la data in cui il prestito è restituito è oltre la data di restituzione previsto, altrimenti il valreo booleano 'false'
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
     *         se la data di restituzione del prestito corrente è precedente rispetto alla data di restituzione del prestito specificato come parametro; il valore intero '0' altrimenti
     */
    
    @Override
    public int compareTo(Prestito p){
        return -1;
    
    }
    
    
    
}
