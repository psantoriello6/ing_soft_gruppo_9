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
    
    
}
