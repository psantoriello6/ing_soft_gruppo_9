/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecauniversitaria;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author angel
 */
public class GestioneUtente {
    private Set<Utente> utenti;
    
    public GestioneUtente(){
        utenti=new TreeSet<Utente>();
    }
    
    public void inserisciUtente(Utente utente){
    
    
    }
    
    public void modificaUtente(Utente utente){
    
    
    }
    
    //riorda i throws
    public Utente cercaUtenteMatricola(String matricola){
        return null;
    }
    
    //riorda i throws
    public Utente cercaUtenteCognome(String cognome){
        return null;
    }
    
    public void removeUtente(Utente u){
        
    }
    
    //metodo per slavare INTERA struttura su file
    public void salvaUtenti(String file){
        
    }
    
    
    
}
