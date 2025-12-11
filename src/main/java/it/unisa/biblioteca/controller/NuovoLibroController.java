/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import it.unisa.biblioteca.model.GestioneEccezioni;
import it.unisa.biblioteca.model.GestioneLibro;
import it.unisa.biblioteca.model.Libro;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pasqu
 */
public class NuovoLibroController{

    @FXML
    private TextField tfTitoloLibro;
    
    @FXML
    private TextField tfNomeAutoreLibro;
    
    @FXML
    private TextField tfCognomeAutoreLibro;
    
    @FXML
    private TextField tfCodiceLibro;
    
    @FXML
    private TextField tfAnnoLibro;
    
    @FXML
    private TextField tfCopieLibro;
    
    @FXML
    private Button btConfermaNuovoLibro;
    
    @FXML
    private Button btAnnullaNuovoLibro;
    
    private InterfacciaLibriController mainLibriController;
    
    //flag che mostra se si sta modificando un libro
    private boolean modalitaModifica = false;
    
    
    public void setLibroDaModificare(Libro l){
        this.modalitaModifica = true;
        
        //precompilo i campi modificabili con i dati vecchi
        tfTitoloLibro.setText(l.getTitolo());
        tfNomeAutoreLibro.setText(l.getNomeAutore());
        tfCognomeAutoreLibro.setText(l.getCognomeAutore());
        tfCodiceLibro.setText(l.getCodice());
        tfAnnoLibro.setText(String.valueOf(l.getAnnoPubblicazione()));
        tfCopieLibro.setText(String.valueOf(l.getCopieDisponibili()));
        
        
        //codice libro non modificabile
        tfCodiceLibro.setDisable(true);
        
        //cambio scritta sul pulsante
        btConfermaNuovoLibro.setText("Salva Modifiche");
    
    
    
    }
    @FXML
    public void initialize(){
        btConfermaNuovoLibro.setOnAction(e -> onConferma());
        //Recupera lo Stage (la finestra) attuale usando l'evento del pulsante Annulla e lo chiude
        btAnnullaNuovoLibro.setOnAction(e -> {((Stage)btAnnullaNuovoLibro.getScene().getWindow()).close();
                });
    }
    
    //metodo per collegarsi al controller principale
    public void setController(InterfacciaLibriController mainLibriController){
        this.mainLibriController = mainLibriController;
    
    }

    
    @FXML
    private void onConferma(){
        try{
            //recupero le stringhe dai Textfield
            String titolo = tfTitoloLibro.getText();
            String nomeAutore = tfNomeAutoreLibro.getText();
            String cognomeAutore = tfCognomeAutoreLibro.getText();
            String codiceLibro = tfCodiceLibro.getText();
            String annoLibroStr = tfAnnoLibro.getText();
            String copieLibroStr = tfCopieLibro.getText();
            
            
            //controllo che i vari campi del libro sono stati compilati
            if(titolo.isEmpty() || nomeAutore.isEmpty() || cognomeAutore.isEmpty() || codiceLibro.isEmpty() || annoLibroStr.isEmpty() || copieLibroStr.isEmpty()){
                this.mostraErrore("Tutti i campi devono essere compilati.");
                return;
            }
            
            //converto annoLibroStr in un numero intero
            int annoLibro = Integer.parseInt(annoLibroStr);
            
            //converto copieLibroStr in un numero intero
            int copieLibro = Integer.parseInt(copieLibroStr);
            
            //creo il libro da mettere in memoria
            Libro nuovoLibro = new Libro(titolo, nomeAutore, cognomeAutore, annoLibro, codiceLibro, copieLibro);
            if(this.modalitaModifica){
                //se la modalità modifica è abilitata, modifico il libro
                //modifico il libro della collezione
                GestioneLibro.getInstance().modifica(nuovoLibro);
                GestioneLibro.getInstance().salvaLibri("libri.dat");
                
                //mostra un messaggio che indica che il libro è stato inserito con successo
                this.mostraInformazione("Libro modificato e salvato su file con successo");
               
            }else{
                //se la modalità modifica è disabilitata, allora inserisco il libro
                 //inserisco il libro nella collezione
                GestioneLibro.getInstance().inserisci(nuovoLibro);
                GestioneLibro.getInstance().salvaLibri("libri.dat");
                //mostra un messaggio che indica che il libro è stato inserito con successo
                this.mostraInformazione("Libro inserito con successo");
            
            }
          
            //si controlla che la reference del controller "principale" sia stata passata e si aggiorna la table view
            if(mainLibriController != null){
                mainLibriController.aggiornaTabella();
            }
            
             //Recupera lo Stage (la finestra) attuale usando l'evento del pulsante Conferma e lo chiude
            ((Stage)btConfermaNuovoLibro.getScene().getWindow()).close();
        }catch(NumberFormatException ex){
            this.mostraErrore("L'anno di pubblicazione/il numero di copie disponibili del libro deve essere un numero intero");
        }catch(GestioneEccezioni ex){
            this.mostraErrore(ex.getMessage());
        }
    
    
       
    }
    
    
    private void mostraErrore(String messageError){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(messageError);
        alert.showAndWait();
    
    
    }
    
    private void mostraInformazione(String messageInfo){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(messageInfo);
        alert.showAndWait();    
    
    }
    
    
}
