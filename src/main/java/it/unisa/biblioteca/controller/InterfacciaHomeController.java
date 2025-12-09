/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * @file InterfacciaHomeController.java
 * @author Gruppo 9
 * @brief questo file contiene i metodi e la logica per il controllo dell'interfaccia Home.
 * 
 * Fa da tramite tra le interazione con il bibliotecario (nella view) e i dati (nel model).
 * 
 * @date 03 Dicembre, 2025
 */
public class InterfacciaHomeController {
    
    @FXML private Button buttonGestioneLibri;
    @FXML private Button buttonGestioneUtenti;
    @FXML private Button buttonGestionePrestiti;
    
    @FXML
    public void inizia(){
        buttonGestioneLibri.setOnAction(e -> cambiaScena("/it/unisa/biblioteca/view/InterfacciaLibriView.fxml", "Gestione Libri"));
        buttonGestioneUtenti.setOnAction(e -> cambiaScena("/it/unisa/biblioteca/view/InterfacciaUtentiView.fxml", "Gestione Utenti"));
        buttonGestionePrestiti.setOnAction(e -> cambiaScena("/it/unisa/biblioteca/view/InterfacciaPrestitiView.fxml", "Gestione Prestiti"));
    }

    private void cambiaScena(String fxmlPath, String sezione) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Object controller = loader.getController();
            if (controller instanceof InterfacciaUtentiController) {
                ((InterfacciaUtentiController) controller).inizia();
            } else if (controller instanceof InterfacciaLibriController) {
                ((InterfacciaLibriController) controller).inizia();
            } else if (controller instanceof InterfacciaPrestitiController) {
                ((InterfacciaPrestitiController) controller).inizia();
            }

            Stage stage = (Stage) buttonGestioneLibri.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(sezione);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostraErrore("Impossibile caricare l'interfaccia : " + fxmlPath);
        }
    }
    
    private void mostraErrore(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}
