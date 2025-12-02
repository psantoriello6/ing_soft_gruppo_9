/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecauniversitaria;

/**
 *
 * @author admin
 */
public interface Gestione<T> {
    
    public void inserisci(T elemento);
    public void modifica(T elemento);
    public void elimina(T elemento);
  
}
