/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

/**
 * @file GestioneUtente.java
 * @author Gruppo 9
 * @brief Implementazione dei metodi per la gestione della collezione di oggetti Utente.
 * Questa classe GestioneUtente utilizza la collezione dati TreeSet per memorizzare e gestire gli utenti garantendo ordinamento
 * (laclasse utente implementa l'interfaccia Comparable).
 * La classe implementa i metodi inserisci, modifica, elimina, cercaUtenteMatricola, cercaUtenteCognome e salvaUtente.
 * 
 * @date 3 Dicembre 2025
 */

public class GestioneUtente implements Gestione<Utente>{
    private Set<Utente> utenti;
    private static GestioneUtente instance = null; //attributo per ottenere lo stesso oggetto in classi diverse (pattern Singleton)
 
    /**
     * @brief Costruttore.
     * Inizializza la collezione creando un TreeSet.
     */
    
    //costruttore privato per seguire logica pattern Singleton
    private GestioneUtente(){       
        utenti=new TreeSet<Utente>();
    }
    
    //metodo per fornire un accesso pubblico alle altre classi per ottenere l'oggetto.
    public static GestioneUtente getInstance(){
        if(instance==null){
            instance=new GestioneUtente();
        }
        return instance;
    }
    
    //metodo per svuotare la memoria, utile per quando faremo i test!
    public void reset(){
        instance = null;
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
    public void inserisci(Utente utente) throws GestioneEccezioni{
        for(Utente u : utenti){
            if(u.getMatricola()== utente.getMatricola()){
                throw new GestioneEccezioni("Errore: la matricola " + utente.getMatricola() + " è gia stata usata!");
            }
        }
        
        if(!controlloEmail(utente)){
            throw new GestioneEccezioni("Errore: formato dell'email inserita non valido!");
        }
        
        utenti.add(utente);
    }
    
     /**
     * 
     * @brief Modifica utente.
     * Questo metodo permette la modifica di un utente presente nella collezione.
     * Se la matricola modificata è già associata ad un altro utente la modifica non è permessa.
     * 
     * @pre L'oggetto utente da modificare deve essere già presente nella collezione.
     * @post L'oggetto utente viene modificato e la collezione viene aggiornata.
     * 
     * @param utente Oggetto utente da modificare nella collezione TreeSet.
     *
     */
    
    //non possimao proprio permette la modifca della matricola se no non so come cercare l'utente vecchio nella collezione!
    @Override
    public void modifica(Utente utenteModificato) throws GestioneEccezioni{
        if(!controlloEmail(utenteModificato)){
            throw new GestioneEccezioni("Errore: formato dell'email inserita non valido!");
        }
        
        Utente utenteInMemoria = cercaUtenteMatricola(utenteModificato.getMatricola());
        utenti.remove(utenteInMemoria);
        
        //in caso l'utente che sto modificando ha un prestito attivo non posso cambiare la reference che ha, quindi modifico l'oggeto vecchio;
        //l'ho prima rimosso perchè abbiamo un treeSet ordinato sul Cognome-Nome se li cambio deve cambiare anche l'ordinamento (quindi lo riaggiungo dopo aver cambiato i campi);
        
        utenteInMemoria.setNome(utenteModificato.getNome());
        utenteInMemoria.setCognome(utenteModificato.getCognome());
        utenteInMemoria.setEmail(utenteModificato.getEmail());
        
        utenti.add(utenteInMemoria);
    
    }
    
     /**
     * 
     * @brief Elimina utente.
     * Questo metodo permette l'eliminazione di un utente presente nella collezione.
     * 
     * @pre L'oggetto utente da eliminare deve essere già presente nella collezione.
     * @post L'oggetto utente viene eliminato e la collezione viene aggiornata.
     * 
     * @param utente Oggetto utente da eliminare nella collezione TreeSet.
     *
     */
   
    @Override
    public void elimina(Utente utente){
        utenti.remove(utente);
    }
    
     /**
     * 
     * @brief Ricerca dell'utente tramite numero di matricola.
     * Questo metodo permette la ricerca di un utente presente nella collezione mediante numero di matricola univoco.
     * 
     * @pre La collezione deve presentare almeno un utente.
     * @post L'oggetto utente cercato viene restituito.
     * 
     * @param matricola numero intero matricola da utilizzare per la ricerca.
     * @return Oggetto utente corrispondente alla matricola inserita.
     *
     */
    
    public Utente cercaUtenteMatricola (int matricola) throws GestioneEccezioni{
        for(Utente u : utenti){
            if(matricola == u.getMatricola()){
                return u;
            }
        }
        throw new GestioneEccezioni("Errore: Utente non trovato!");
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
     * @return Lista di utenti corrispondente al cognome inserito.
     *
     */
    
    //ritono una lista perchè ci potrebbero essere più utenti con lo stesso cognome.
    public List<Utente> cercaUtenteCognome(String cognome) throws GestioneEccezioni {
        List<Utente> risultati = new ArrayList<>();
        for(Utente u : utenti){
            if(cognome.equalsIgnoreCase(u.getCognome())){
                risultati.add(u);
            }
        }
        if(risultati.isEmpty()){
            throw new GestioneEccezioni("Errore: Utente non trovato!");
        }
        return risultati;
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
    
    
    //metodo per il controllo del email con il formato nome.cognome@università.it
    private boolean controlloEmail(Utente u){
        String nomeValido = u.getNome().trim().toLowerCase();
        String cognomeValido = u.getCognome().trim().toLowerCase();
        String emailInserita = u.getEmail().trim().toLowerCase();
        String emailValida = nomeValido + "." + cognomeValido + "@università.it";  //in caso il "dominio" si può implementare come variabile globale!
        
        if(u.getEmail().isEmpty() || u.getNome().isEmpty() || u.getCognome().isEmpty()){
            return false;
        }
        
        return emailInserita.equals(emailValida);
    }
}

