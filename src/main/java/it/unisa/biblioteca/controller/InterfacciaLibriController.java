/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import it.unisa.biblioteca.model.GestioneLibro;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import it.unisa.biblioteca.model.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.MenuButton;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * @file InterfacciaLibriController.java
 * @author Gruppo 9
 * @brief questo file contiene i metodi e la logica per il controllo dell'interfaccia per la gestione dei libri.
 * 
 * Fa da tramite tra le interazione con il bibliotecario (nella view) e i dati (nel model).
 * 
 * @date 03 Dicembre, 2025
 */
public class InterfacciaLibriController {
    
    @FXML
    private TableView<Libro> tabellaLibri;
    @FXML
    private TableColumn<Libro, String> colonnaTitoloLibri;
    @FXML
    private TableColumn<Libro, String> colonnaNomeLibri;
    @FXML
    private TableColumn<Libro, String> colonnaCognomeLibri;
    @FXML
    private TableColumn<Libro, Integer> colonnaAnnoLibri;
    @FXML
    private TableColumn<Libro, String> colonnaCodiceLibri;
    @FXML
    private TableColumn<Libro, Integer> colonnaCopieLibri;
    
    @FXML
    private Button btnNuoviLibri;
    
    @FXML
    private Button btnModificaLibri;
    
    @FXML 
    private Button btnEliminaLibri;
    
    @FXML
    private TextField tfLibri;
    
    @FXML
    private MenuButton filterLibri;
    
    @FXML
    private Button btnCercaLibri;
    
    //lista per costruire la TableView
    private ObservableList<Libro> listaLibri;
    
    
    public void inizializza(){
        colonnaTitoloLibri.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colonnaNomeLibri.setCellValueFactory(new PropertyValueFactory<>("nomeAutore"));
        colonnaCognomeLibri.setCellValueFactory(new PropertyValueFactory<>("cognomeAutore"));
        colonnaAnnoLibri.setCellValueFactory(new PropertyValueFactory<>("annoPubblicazione"));
        colonnaCodiceLibri.setCellValueFactory(new PropertyValueFactory<>("codice"));
        colonnaCopieLibri.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));
        
        listaLibri = FXCollections.observableArrayList();
        this.aggiornaTabella();
    
    }
    
    //metodo che aggiorna la lista osservabile
    public void aggiornaTabella(){
        listaLibri.clear();  //prima rimuovo tutto
        listaLibri.addAll(GestioneLibro.getInstance().getSetLibro()); //inserisco nella lista osservabile tutti gli elementi del set di libri
        if(listaLibri != null){
            tabellaLibri.setItems(listaLibri);  //aggiungo la lista osservabile nella TableView
        }
    
    }
    
    
    
    
    
}
