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
    
    private InterfacciaUtentiController controllerUtentiPrincipale;
    
    //metodo per collegarsi al controller "principale" e aggiornare la tabella.
    public void setMainController(InterfacciaUtentiController mainController) {
        controllerUtentiPrincipale = mainController;
    }
    
    @FXML 
    public void inizia(){
        //gestione degli eventi
        btConferma.setOnAction( e -> handleConferma());
        btAnnulla.setOnAction(e -> {
            ((Stage) btAnnulla.getScene().getWindow()).close();
        });
    }
    
    private void handleConferma(){
        try{
            //prelevo stringhe dai textfield
            String nome = tfNome.getText();
            String cognome = tfCognome.getText();
            String email = tfEmail.getText();
            String matricolaStr = tfMatricola.getText();
            //controllo che tutti i campi sono stati compilati
            if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || matricolaStr.isEmpty()) {
                mostraErrore("Erorre: Compila tutti i campi!");
                return;
            }
            // converto la stringa matricola in un intero (questo metodo lancia eccezione se non è possiibile)
            int matricola = Integer.parseInt(matricolaStr);
            //creo l'utente da mettere in memoria
            Utente nuovoUtente = new Utente(nome,cognome,matricola,email);
            //uso il metodo inserisci per inserire l'utente nella collezione
            GestioneUtente.getInstance().inserisci(nuovoUtente);
            //se non vengono catturate eccezioni mostro messaggio di successo
            mostraSuccesso("Utente inserito correttamente!");
            //controllo che ho passato reference del cotroller principale e chiedo di aggiornare la table view
            if (controllerUtentiPrincipale != null) {
                controllerUtentiPrincipale.aggiornaTabella();
            }
            //risalgo la gerarchia per ottenere (con il casting) lo stage su cui posso usare il metodo .close()
            ((Stage) btConferma.getScene().getWindow()).close();
        } catch (NumberFormatException e){
            mostraErrore("La matricola deve essere un numero intero."); //getsione eccezione della conversione!
        } catch (GestioneEccezioni e){
            mostraErrore(e.getMessage()); //gestione eccezioni del model!
        }
        
    }
    
    //metodo che usa un interfaccia fatta a posta per mostrare errori!
    private void mostraErrore(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Errore inserimento!");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    //metodo con la stessa logica di quello di sopra ma che mostra se l'operazione ha avuto successo!
    private void mostraSuccesso(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successo");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
