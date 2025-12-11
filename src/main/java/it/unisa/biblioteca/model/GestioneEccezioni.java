/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;


/**
 * @file GestioneEccezioni.java
 * @author Gruppo 9
 * @brief Questa classe contiene l'implementazione della classe GestioneEccezione
 * 
 * La classe permette, in caso di errori di inserimento dati verificatisi nelle varie classi del Model, di lanciare un'eccezione stampando 
 * un messaggio di errore appropriato.
 * 
 * @date 3 dicembre 2025
 * 
 */
public class GestioneEccezioni extends Exception {
    
    /**
     * @brief Costruttore della classe Gestione Eccezioni
     *  
     * Il costruttore della classe GestioneEccezioni prende come parametro e stampa una stringa che rappresenta un messaggio di errore
     * 
     * @param errorMessage Il messaggio di errore che si vuole stampare
     */
    public GestioneEccezioni(String errorMessage){
        super(errorMessage);
    }
    
    public GestioneEccezioni(){
        super();
    }
    
    
}
