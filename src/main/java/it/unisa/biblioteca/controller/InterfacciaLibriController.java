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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.MenuButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import java.io.IOException;
import javafx.stage.Modality;



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
    
    @FXML private Button btnHomeLibri;
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
    
    
    public void initialize(){
        colonnaTitoloLibri.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colonnaNomeLibri.setCellValueFactory(new PropertyValueFactory<>("nomeAutore"));
        colonnaCognomeLibri.setCellValueFactory(new PropertyValueFactory<>("cognomeAutore"));
        colonnaAnnoLibri.setCellValueFactory(new PropertyValueFactory<>("annoPubblicazione"));
        colonnaCodiceLibri.setCellValueFactory(new PropertyValueFactory<>("codice"));
        colonnaCopieLibri.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));
        
        listaLibri = FXCollections.observableArrayList();
        this.aggiornaTabella();
        
        
        btnNuoviLibri.setOnAction(e -> apriFinestraNuovoLibro());
        
        btnHomeLibri.setOnAction(e -> ritornoHome("/it/unisa/biblioteca/view/InterfacciaHomeView.fxml"));
    }
    
    //metodo che aggiorna la lista osservabile
    public void aggiornaTabella(){
        listaLibri.clear();  //prima rimuovo tutto
        listaLibri.addAll(GestioneLibro.getInstance().getSetLibro()); //inserisco nella lista osservabile tutti gli elementi del set di libri
        if(listaLibri != null){
            tabellaLibri.setItems(listaLibri);  //aggiungo la lista osservabile nella TableView
        }
    
    }
    
    
    public void apriFinestraNuovoLibro(){
        
        //carico la interfaccia NuovoLibro
        try{
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/NuovoLibroView.fxml"));
            Parent root = loader.load();
        
            NuovoLibroController controller = loader.getController();
            controller.setController(this);
        
            Stage stage  = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            //creo una finestra "figlia" che appare sopra la pagina principale 
            //(finestra "madre") e blocca l'interazione con essa fino a quando l'utente non compie un'azione.
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Aggiungi libro");
            stage.showAndWait();
        }catch(IOException ex){
            ex.printStackTrace();
            mostraErrore("Errore nel caricamento della finestra Aggiungi libro");
        
        }
    
    
    }
    
    
    public void mostraErrore(String messageError){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(messageError);
        alert.showAndWait();
    
    
    
    }
    
    private void ritornoHome(String fxmlPathHome) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPathHome));
            Parent root = loader.load(); //il load poi chiama in automatico il metodo inizialize specifico per ogni controller

            // Ottengo lo stage corrente (la finestra) da un bottone qualsiasi
            Stage stage = (Stage) btnHomeLibri.getScene().getWindow();
            
            stage.setScene(new Scene(root));
            stage.setTitle("Biblioteca Universitaria - Home");
            stage.centerOnScreen();
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            mostraErrore("Impossibile caricare l'interfaccia: " + fxmlPathHome + "\nControlla che il file esista e il percorso sia corretto.");
        }
    }
  }
    
    
    
    

