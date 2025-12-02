/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecauniversitaria;
import java.io.*;

/**
 *
 * @author angel
 */
public class Utente implements Serializable, Comparable<Utente> {
    private String nome;
    private String cognome;
    private int matricola;
    private String email;
    
    public Utente(String nome, String cognome, int matricola, String email){
        this.nome=nome;
        this.cognome=cognome;
        this.matricola=matricola;
        this.email=email;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome=nome;
    }
    
    public String getCognome(){
        return cognome;
    }
    
    public void setCognome(String cognome){
        this.cognome=cognome;
    }
    
    public int getMatricola(){
        return matricola;
    }
    
    public void setMatricola(int matricola){
        this.matricola=matricola;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    //se vogliamo utilizzare un treeSet (ordinato sul cognome)
    @Override
    public int compareTo(Utente u){
        if(this==u){
            return 0;
        }
        if (u==null){
            return 1;
        }
        return this.getCognome().compareToIgnoreCase(u.getCognome());
    }
    
}

