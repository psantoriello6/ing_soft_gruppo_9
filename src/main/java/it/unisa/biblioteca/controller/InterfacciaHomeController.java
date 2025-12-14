/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import it.unisa.biblioteca.model.GestioneLibro;
import it.unisa.biblioteca.model.Libro;
import java.io.IOException;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InterfacciaHomeController {
    
    @FXML private Button buttonGestioneLibri;
    @FXML private Button buttonGestioneUtenti;
    @FXML private Button buttonGestionePrestiti;
    
    @FXML
    public void initialize(){
        buttonGestioneLibri.setOnAction(e -> cambiaScena("/it/unisa/biblioteca/view/InterfacciaLibriView.fxml", "Gestione Libri"));
        buttonGestioneUtenti.setOnAction(e -> cambiaScena("/it/unisa/biblioteca/view/InterfacciaUtentiView.fxml", "Gestione Utenti"));
        buttonGestionePrestiti.setOnAction(e -> cambiaScena("/it/unisa/biblioteca/view/InterfacciaPrestitiView.fxml", "Gestione Prestiti"));
    }

    private void cambiaScena(String fxmlPath, String titolo) {
         Set<Libro> libriCaricati = GestioneLibro.getInstance().caricaLibri("libri.dat");
            
            if(libriCaricati != null){
                GestioneLibro.getInstance().getSetLibro().clear();
                
                GestioneLibro.getInstance().getSetLibro().addAll(libriCaricati);
            }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load(); //il load poi chiama in automatico il metodo inizialize specifico per ogni controller

            // Ottengo lo stage corrente (la finestra) da un bottone qualsiasi
            Stage stage = (Stage) buttonGestioneLibri.getScene().getWindow();
            
            stage.setScene(new Scene(root));
            stage.setTitle(titolo);
            stage.centerOnScreen();
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            mostraErrore("Impossibile caricare l'interfaccia: " + fxmlPath + "\nControlla che il file esista e il percorso sia corretto.");
        }
    }
    
    private void mostraErrore(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}
