/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import it.unisa.biblioteca.model.Utente;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton; 
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @file InterfacciaUtentiController.java
 * @author Gruppo 9
 * @brief questo file contiene i metodi e la logica per il controllo dell'interfaccia per la gestione degli Utenti.
 * 
 * Fa da tramite tra le interazione con il bibliotecario (nella view) e i dati (nel model).
 * 
 * @date 03 Dicembre, 2025
 */
public class InterfacciaUtentiController {
    
    //id per la TabelView
    @FXML private TableView<Utente> tabellaUtenti; 
    @FXML private TableColumn<Utente, String> colonnaCognomeUtenti;
    @FXML private TableColumn<Utente, String> colonnaNomeUtenti;
    @FXML private TableColumn<Utente, Integer> colonnaMatricolaUtenti;
    @FXML private TableColumn<Utente, String> colonnaEmailUtenti;
    //id per aggiungi - modifica - rimuovi
    @FXML private Button btnNuovoUtenti;
    @FXML private Button btnModificaUtenti;
    @FXML private Button btnEliminaUtenti;
    //id per la ricerca
    @FXML private TextField tfUtenti; 
    @FXML private MenuButton filterUtenti;
    @FXML private Button btnCercaUtenti;
    //id per tasto per tornare alla home
    @FXML private Button btnHomeUtenti;
    
    //lista per costruire la TabelView
    private ObservableList<Utente> listaUtenti;
    
    @FXML
    public void inizia(){
        colonnaCognomeUtenti.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaNomeUtenti.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaMatricolaUtenti.setCellValueFactory(new PropertyValueFactory<>("matricola"));
        colonnaEmailUtenti.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        //continua a casa
    }
}
