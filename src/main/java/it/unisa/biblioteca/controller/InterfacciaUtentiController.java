/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import it.unisa.biblioteca.model.Utente;
import it.unisa.biblioteca.model.GestioneUtente;
import it.unisa.biblioteca.model.GestioneEccezioni;
import java.util.List;

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
import javafx.scene.control.MenuItem;

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
    //variabile per il criterio di ricerca (di default è su cognome)
    private String criterioRicerca = "Cognome";
    
    //lista per costruire la TabelView
    private ObservableList<Utente> listaUtenti;
    
    @FXML
    public void initialize(){
        colonnaCognomeUtenti.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaNomeUtenti.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaMatricolaUtenti.setCellValueFactory(new PropertyValueFactory<>("matricola"));
        colonnaEmailUtenti.setCellValueFactory(new PropertyValueFactory<>("email"));
        //serve per rendere l'interfaccia full schermo
        tabellaUtenti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //continua a casa
        listaUtenti = FXCollections.observableArrayList();  //collegamento interfaccia observablelist (permette aggiornamento TabelView in automatico)
        aggiornaTabella(); 
        //da completare con i vari setOnAction
        btnNuovoUtenti.setOnAction(event -> apriFinestraNuovoUtente());
        btnEliminaUtenti.setOnAction(e ->handleEliminaUtente());
        btnHomeUtenti.setOnAction(e -> tornaAllaHome());
        btnModificaUtenti.setOnAction(e -> apriFinestraModificaUtente());
        
        //logica per la ricerca:
        MenuItem itemCognome = new MenuItem("Cognome");
        itemCognome.setOnAction(e -> {
            criterioRicerca = "Cognome";
            filterUtenti.setText("Filtro: Cognome");// Aggiorna l'etichetta visiva
            tfUtenti.setPromptText("Inserisci il cognome..."); 
            tfUtenti.clear();//pulisce il campo se ce qualcosa di vecchio.
        });

        MenuItem itemMatricola = new MenuItem("Matricola");
        itemMatricola.setOnAction(e -> {
            criterioRicerca = "Matricola";
            filterUtenti.setText("Filtro: Matricola");
            tfUtenti.setPromptText("Inserisci la matricola...");
            tfUtenti.clear();
        });

        // Aggiungo le voci al menu
        filterUtenti.getItems().clear();
        filterUtenti.getItems().addAll(itemCognome, itemMatricola);
        
        // Imposto il testo iniziale
        filterUtenti.setText("Filtro: Cognome");

        //setOncation per la ricerca:
        btnCercaUtenti.setOnAction(e -> handleCercaUtente());
    }
    
    //metodo che aggiorna la lista osservabile (da chiamare dopo operazioni di inserimento - modifica - elimina
    public void aggiornaTabella(){
        listaUtenti.clear(); //prima rimuovo tutto
        listaUtenti.addAll(GestioneUtente.getInstance().getTutti()); //prendo tutti gli utenti attualmente salvati
        if(listaUtenti != null){
            tabellaUtenti.setItems(listaUtenti); //metto gli elemnti della lista nella tabel view
        }
    }
    
    private void apriFinestraNuovoUtente(){
        try{
            //collego il file FXML della view al controller e faccio operazioni standard per impostare lo stage!
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/NuovoUtenteView.fxml"));
            Parent root = loader.load();
            //IMPORTANTE: chiamo metodo  setMainController() per passare al pop-up la reference a questo controller!
            NuovoUtenteController popupController = loader.getController();
            popupController.setMainController(this);
            //costruisco lo stage
            Stage stage = new Stage();
            stage.setTitle("Nuovo Utente");
            stage.initModality(Modality.APPLICATION_MODAL); //metodo per bloccare schermata di "sotto" e poter aggire solo sul pop-up
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait(); //metodo per bloccare flusso di codice fiche il pop-up è aperto
            
        }catch (IOException e) {
            e.printStackTrace();
            mostraErrore("Impossibile aprire la finestra Nuovo Utente.");
        }
    
    }
    
    //gestione dell azione elimina utente (DA RIVEDRE!!!!)
    private void handleEliminaUtente() {
        Utente selezionato = tabellaUtenti.getSelectionModel().getSelectedItem();
        if (selezionato != null) {
            GestioneUtente.getInstance().elimina(selezionato);
            aggiornaTabella();
        } else {
            mostraErrore("Seleziona un utente da eliminare.");
        }
    }
    
    //metodo per tornare alla schermata di Home
    private void tornaAllaHome() {
        try {
            //ottengo schermata della home e chiamo metodo load per caricarla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/InterfacciaHomeView.fxml"));
            Parent root = loader.load();
            
            //ottengo lo stage attuale dal bottone
            Stage stage = (Stage) btnHomeUtenti.getScene().getWindow();
            
            //cambio la solo la scena (non lo stage!)
            stage.setScene(new Scene(root));
            stage.setTitle("Biblioteca Universitaria - Home");
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            mostraErrore("Impossibile caricare la Home Page.");
        }
    }
    
    //metodo per gestire la ricerca dell'utente
    private void handleCercaUtente() {
        //ottengo testo immesso nella barra di ricerca
        String query = tfUtenti.getText().trim();
        
        //Se il campo è vuoto, RESETTO la tabella (mostro tutti);
        if (query.isEmpty()) {
            aggiornaTabella();
            return;
        }

        // elimino tutti gli elementi dalla lista 
        listaUtenti.clear();

        try {
            if ("Matricola".equals(criterioRicerca)) {
                // CASO 1: Ricerca per Matricola
                // Provo a convertire in numero
                int matricola = Integer.parseInt(query);
                // Chiamo il metdo in GestioneUtenti (Lancia eccezione se non trova nulla)
                Utente trovato = GestioneUtente.getInstance().cercaUtenteMatricola(matricola);
                // Se arrivo qui, l'ho trovato
                listaUtenti.add(trovato);
                
            } else {
                // Caso 2: Ricerca per Cognome
                // Restituisce una lista di utenti con quel cognome
                List<Utente> trovati = GestioneUtente.getInstance().cercaUtenteCognome(query);
                listaUtenti.addAll(trovati);
            } 
            // aggiungo nella tabel view solo gli utenti trovati
            tabellaUtenti.setItems(listaUtenti);

        } catch (NumberFormatException e) {
            mostraErrore("La matricola deve essere un numero intero.");
            aggiornaTabella(); // Ripristino la vista completa per comodità
        } catch (GestioneEccezioni e) {
            mostraErrore(e.getMessage()); // Es: "Utente non trovato!"
            aggiornaTabella(); // Ripristino la vista completa
        }
    }
    
    @FXML
    public void apriFinestraModificaUtente() {
        //controllo selezione riga della table view
        Utente selezionato = tabellaUtenti.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            mostraErrore("Seleziona un utente dalla tabella per modificarlo.");
            return;
        }
        try {
            // Carico il pop-up (lo stesso di Nuovo Utente)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/NuovoUtenteView.fxml"));
            Parent root = loader.load();
            //Prendo il controller del pop-up
            NuovoUtenteController popupController = loader.getController();
            popupController.setMainController(this);
            // passo l'utente da modificare (quello selezionato) al metodo del pop-up
            popupController.setUtenteDaModificare(selezionato);
            //Mostro la finestra
            Stage stage = new Stage();
            stage.setTitle("Modifica Utente");
            stage.initModality(Modality.APPLICATION_MODAL); // serve per bloccare le finestre di "sotto"
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
            mostraErrore("Impossibile aprire la finestra di modifica.");
        }
    }
    
    //Interfaccia apposita per mostare messaggi di errore (senza costruire file FXML!
    private void mostraErrore(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Operazione non riuscita");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
