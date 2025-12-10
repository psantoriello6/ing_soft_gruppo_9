/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import it.unisa.biblioteca.model.GestioneEccezioni;
import it.unisa.biblioteca.model.GestioneUtente;
import it.unisa.biblioteca.model.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author angel
 */

public class NuovoUtenteController {
    @FXML private TextField tfNome;
    @FXML private TextField tfCognome;
    @FXML private TextField tfMatricola;
    @FXML private TextField tfEmail;
    @FXML private Button btConferma;
    @FXML private Button btAnnulla;
    
    private InterfacciaUtentiController mainController;
    private boolean modalitaModifica = false; // Flag per capire se stiamo modificando
    
    public void setMainController(InterfacciaUtentiController mainController) {
        this.mainController = mainController;
    }

   
    // Serve per riempire i campi quando clicchi "Modifica"
    public void setUtenteDaModificare(Utente u) {
        this.modalitaModifica = true; // Attivo la modalità modifica
        
        //Precompilo i campi con i dati vecchi
        tfNome.setText(u.getNome());
        tfCognome.setText(u.getCognome());
        tfMatricola.setText(String.valueOf(u.getMatricola()));
        tfEmail.setText(u.getEmail());
        
        // campo matricola non modificabile
        tfMatricola.setDisable(true); 
        
        // cambio scritta sul bottone
        btConferma.setText("Salva Modifiche");
    }
    
    @FXML 
    public void initialize(){
        btConferma.setOnAction(e -> handleConferma());
        btAnnulla.setOnAction(e -> ((Stage) btAnnulla.getScene().getWindow()).close());
    }
    
    private void handleConferma(){
        try{
            // Lettura dati
            String nome = tfNome.getText();
            String cognome = tfCognome.getText();
            String email = tfEmail.getText();
            String matricolaStr = tfMatricola.getText();
            
            if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || matricolaStr.isEmpty()) {
                mostraErrore("Errore: Compila tutti i campi!");
                return;
            }
            
            int matricola = Integer.parseInt(matricolaStr);
            
            // Creo l'oggetto con i dati (nuovi o vecchi che siano)
            Utente utenteInput = new Utente(nome, cognome, matricola, email);
            if (modalitaModifica) {
                // Se sono in modifica, chiamo il metodo MODIFICA del Model
                GestioneUtente.getInstance().modifica(utenteInput);
                mostraSuccesso("Utente modificato con successo!");
            } else {
                // Se sono in inserimento, chiamo il metodo INSERISCI del Model
                GestioneUtente.getInstance().inserisci(utenteInput);
                mostraSuccesso("Utente inserito con successo!");
            }

            // Aggiorno la tabella principale
            if (mainController != null) {
                mainController.aggiornaTabella();
            }
            
            // Chiudo la finestra
            ((Stage) btConferma.getScene().getWindow()).close();

        } catch (NumberFormatException e){
            mostraErrore("La matricola deve essere un numero intero.");
        } catch (GestioneEccezioni e){
            mostraErrore(e.getMessage()); // Qui catturiamo errori come email non valida
        }
    }
    
    private void mostraErrore(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
    
    private void mostraSuccesso(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successo");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
