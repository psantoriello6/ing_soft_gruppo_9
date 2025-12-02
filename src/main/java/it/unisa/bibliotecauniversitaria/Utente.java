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
        // se vogliamo ordinarli prima per cognome e poi per nome
        /*int c = this.getCognome().compareToIgnoreCase(u.getCognome());
        if(c!=0){
            return c;
        }
        return this.getNome().compareToIgnoreCase(u.getNome());*/
        return this.getCognome().compareToIgnoreCase(u.getCognome());
    }
    
    @Override
    public int hashCode(){
        return 31 * Integer.hashCode(matricola);
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        Utente u = (Utente) o;
        return this.matricola == u.matricola;
    }
    
}

