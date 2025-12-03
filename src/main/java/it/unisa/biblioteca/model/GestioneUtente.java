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
public class GestioneUtente implements Gestione<Utente>{
    private Set<Utente> utenti;
    
    public GestioneUtente(){
        utenti=new TreeSet<Utente>();
    }
    
    @Override
    public void inserisci(Utente utente){
    
    
    }
    
    @Override
    public void modifica(Utente utente){
    
    
    }
    
    @Override
    public void elimina(Utente utente){
    
    
    }
    
    //riorda i throws
    public Utente cercaUtenteMatricola(String matricola){
        return null;
    }
    
    //riorda i throws
    public Utente cercaUtenteCognome(String cognome){
        return null;
    }
    
    //metodo per slavare INTERA struttura su file
    public void salvaUtenti(String file){
        
    }
    
    
    
}
