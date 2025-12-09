/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pasqu
 */
public class NuovoLibroController implements Initializable {

    @FXML
    private TextField tfTitoloLibro;
    @FXML
    private TextField tfNomeLibro;
    @FXML
    private TextField tfCognomeLibro;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
