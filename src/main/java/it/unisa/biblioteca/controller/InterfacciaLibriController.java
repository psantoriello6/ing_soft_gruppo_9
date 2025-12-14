/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import it.unisa.biblioteca.model.GestioneEccezioni;
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
import java.util.Set;
import java.util.TreeSet;
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
    
    private String tipoFiltro = null;
    
    
    public void initialize(){
        colonnaTitoloLibri.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colonnaNomeLibri.setCellValueFactory(new PropertyValueFactory<>("nomeAutore"));
        colonnaCognomeLibri.setCellValueFactory(new PropertyValueFactory<>("cognomeAutore"));
        colonnaAnnoLibri.setCellValueFactory(new PropertyValueFactory<>("annoPubblicazione"));
        colonnaCodiceLibri.setCellValueFactory(new PropertyValueFactory<>("codice"));
        colonnaCopieLibri.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));
      
        Set<Libro> listaCaricata = GestioneLibro.getInstance().caricaLibri("libri.dat");
        
        if(listaCaricata == null){
            listaCaricata = new TreeSet<>();
        
        }else{
            listaLibri = FXCollections.observableArrayList(listaCaricata);
            this.inizializzaTabella(listaLibri);
        }
        
        GestioneLibro.getInstance().setLibroSet(listaCaricata);
       
        
        btnHomeLibri.setOnAction(e -> ritornoHome("/it/unisa/biblioteca/view/InterfacciaHomeView.fxml"));
        btnNuoviLibri.setOnAction(e -> apriFinestraNuovoLibro());
        btnEliminaLibri.setOnAction(e-> handleEliminaLibro());
        btnModificaLibri.setOnAction(e -> apriFinestraModificaLibro());
        btnCercaLibri.setOnAction(e -> cercaLibro());
        
    }
    
    //metodo che aggiorna la lista osservabile
    public void inizializzaTabella(ObservableList<Libro> lista){
        if(listaLibri != null){
            tabellaLibri.setItems(listaLibri);  //aggiungo la lista osservabile nella TableView
        }
    
    }
    
    public void aggiornaTabella(){
        listaLibri.clear();  //prima rimuovo tutto
        listaLibri.addAll(GestioneLibro.getInstance().getSetLibro()); //inserisco nella lista osservabile tutti gli elementi del set di libri
        if(listaLibri != null){
            tabellaLibri.setItems(listaLibri);  //aggiungo la lista osservabile nella TableView
        }
    
    }
    private void apriFinestraNuovoLibro(){
        
        //carico la interfaccia NuovoLibro
        try{
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/NuovoLibroView.fxml"));
            Parent root = loader.load();
        
            NuovoLibroController controller = loader.getController();
            controller.setController(this);
        
            Stage stage  = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
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
    
    private void cercaLibro(){
        String testo = tfLibri.getText();
        
        if(testo == null || testo.trim().isEmpty()){
            if(this.tipoFiltro == null){
                this.mostraInformazione("Specifica un filtro di ricerca");
            }
            tabellaLibri.setItems(listaLibri);
            return;
        }
        
       
        //set di Libri in cui salvare il risultato della ricerca per titolo o per autore
        Set<Libro> risultato = null;
        
        //variabile di tipo Libro in cui salvare il risultato della ricerca per codice
        Libro risultatoCodice = null;
        
        
        
        
        String lowerCaseFilter = testo.toLowerCase();
        if(this.tipoFiltro == null){
            this.mostraInformazione("Specifica un filtro di ricerca");
            return;
        
        }else if(this.tipoFiltro.equals("TITOLO")){
            try{
                risultato = GestioneLibro.getInstance().ricercaLibroTitolo(lowerCaseFilter);
            }catch(GestioneEccezioni ex){
            
            }
        }else if(this.tipoFiltro.equals("AUTORE")){
            //poiché il metodo ricercaLibro per autore richiede nome e cognome, e il TextField restituisce una stringa unica, 
            //bisogna "spezzare" la stringa in due
            String parti[] = lowerCaseFilter.split(" ");
            if(parti.length >= 2){
                String nome = parti[0];
                String cognome = parti[1];
                
                try{
                    risultato = GestioneLibro.getInstance().ricercaLibroAutore(nome, cognome);
                }catch(GestioneEccezioni ex){
                
                }
            }
        
        
        
        }else if(this.tipoFiltro.equals("CODICE")){
            try{
                risultatoCodice = GestioneLibro.getInstance().ricercaLibroCodice(lowerCaseFilter);
            }catch(GestioneEccezioni ex){
            
            }
        }
        //aggiorno la table view
        if(risultato != null){
            //se il set di libri non è vuoto nel caso di ricerca per titolo o autore , creo una lista che contiene quel set di libri
            ObservableList<Libro> listaRisultatoTitoloAutore = FXCollections.observableArrayList(risultato);
            tabellaLibri.setItems(listaRisultatoTitoloAutore);
        }else if(risultatoCodice != null){
            //nel caso di ricerca per codice, creo una lista con SOLO quel libro, se è stato trovato
            ObservableList<Libro> listaRisultatoCodice = FXCollections.observableArrayList();
            listaRisultatoCodice.add(risultatoCodice);
            tabellaLibri.setItems(listaRisultatoCodice);
            
        }else{
            this.mostraInformazione("Nessun libro trovato");
        }
        
        
    
    }
    
    private void handleEliminaLibro(){
        Libro selezionato = tabellaLibri.getSelectionModel().getSelectedItem();
        if(selezionato != null){
            GestioneLibro.getInstance().elimina(selezionato);
            this.aggiornaTabella();
        }else{
            this.mostraErrore("Seleziona un libro da eliminare");
        }
    
    
    }
    
    private void apriFinestraModificaLibro(){
        
       //seleziono il libro da modificare
       Libro libroSelezionato = tabellaLibri.getSelectionModel().getSelectedItem();
       
       if(libroSelezionato == null){
           this.mostraErrore("Seleziona un libro da modificare");
           return;
       }else{
           
           //carico la finestra NuovoLibro
           try{
                FXMLLoader loader  = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/NuovoLibroView.fxml"));
                Parent root = loader.load();
        
                NuovoLibroController controller = loader.getController();
                controller.setController(this);
                controller.setLibroDaModificare(libroSelezionato);
        
                Stage stage  = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                //creo una finestra "figlia" che appare sopra la pagina principale 
                //(finestra "madre") e blocca l'interazione con essa fino a quando l'utente non compie un'azione.
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modifica libro");
                stage.showAndWait();
           
           }catch(IOException ex){
               ex.printStackTrace();
               this.mostraErrore("Impossibile aprire la finestra di modifica");
           
           
           }
       
       
       }
    
    
   }
    
    
    @FXML
    private void handleFiltroTitolo(){
        this.tipoFiltro = "TITOLO";
        filterLibri.setText("TITOLO");
    
    }
    
    @FXML
    private void handleFiltroAutore(){
        this.tipoFiltro = "AUTORE";
        filterLibri.setText("AUTORE");
    }
    
    @FXML
    private void handleFiltroCodice(){
        this.tipoFiltro = "CODICE";
        filterLibri.setText("CODICE");
    }
    
    
    public void mostraErrore(String messageError){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(messageError);
        alert.showAndWait();
    
    
    
    }
    
    public void mostraInformazione(String messageInfo){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(messageInfo);
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
    
    
    
    

