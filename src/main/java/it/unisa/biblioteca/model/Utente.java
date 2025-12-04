/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;
import java.io.*;

/**
 *
 * @author angel
 */
/**
 * @file Utente.java
 * @brief Si occupa di definire tutti gli attributi della classe Utente con i setter e getter.
 * Sono specificati inoltre i metodi equals e hashCode che definiscono eventuali duplicati e il metodo compareTo che determina l'ordinamento da tener conto.
 * @date 03/12/2025
 * @author pasqu
 */
public class Utente implements Serializable, Comparable<Utente> {
    private String nome;
    private String cognome;
    private int matricola;
    private String email;
    /**
     * @brief Costruttore che inizializza un utente
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param matricola matricola dell'utente
     * @param email email dell'utente
     */
    public Utente(String nome, String cognome, int matricola, String email){
        this.nome=nome;
        this.cognome=cognome;
        this.matricola=matricola;
        this.email=email;
    }
    /**
     * @brief Metodo che restituisce il nome dell'utente in questione
     * @return 
     */
    public String getNome(){
        return nome;
    }
    /**
     * @brief Metodo che inserisce il nome dell'utente in questione
     * @param nome 
     */
    public void setNome(String nome){
        this.nome=nome;
    }
    /**
     * @brief Metodo che restituisce il cognome dell'utente in questione
     * @return 
     */
    public String getCognome(){
        return cognome;
    }
    /**
     * @brief Metodo che inserisce il cognome dell'utente in questione
     * @param cognome 
     */
    public void setCognome(String cognome){
        this.cognome=cognome;
    }
    /**
     * @brief Metodo che restituisce la matricola dell'utente in questione
     * @return 
     */
    public int getMatricola(){
        return matricola;
    }
    /**
     * @brief Metodo che inserisce la matricola dell'utente in questione
     * @param matricola 
     */
    public void setMatricola(int matricola){
        this.matricola=matricola;
    }
    /**
     * @brief Metodo che restituisce l'email dell'utente in questione
     * @return 
     */
    public String getEmail(){
        return email;
    }
    /**
     * @brief Metodo che permette l'inserimento della email
     * Se la email inserita non rispetta il formato standard verrà mostrata un'eccezione
     * @param email 
     */
    public void setEmail(String email){
        this.email=email;
    }
    /**
     * @brief Metodo che determina la logica di ordinamento del TreeSet (ordinamento naturale basato prima sul cognome e poi sul nome).
     * @param u l'utente da confrontare con questo
     * @return 
     */
    
    @Override
    public int compareTo(Utente u){
        if(this==u){
            return 0;
        }
        if (u==null){
            return 1;
        }
        int c = this.getCognome().compareToIgnoreCase(u.getCognome());
        if(c!=0){
            return c;
        }
        return this.getNome().compareToIgnoreCase(u.getNome());
    }
    /**
     * @brief Metodo che determina quale logica viene utilizzata per la generazione degli hashcode
     * @return 
     */
    @Override
    public int hashCode(){
        return 31 * Integer.hashCode(matricola);
    }
    /**
     * @brief Metodo che verifica se due questo oggetto è uguale all'oggetto specificato come parametro
     * @param o l'oggetto da confrontare con questo
     * @return 
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        Utente u = (Utente) o;
        return this.matricola == u.matricola;
    }
    
}

