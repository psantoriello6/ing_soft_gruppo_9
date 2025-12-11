/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca;

import it.unisa.biblioteca.model.GestioneUtente;
import it.unisa.biblioteca.model.Utente;
import it.unisa.biblioteca.model.GestioneEccezioni;
import it.unisa.biblioteca.model.GestioneLibro;
import it.unisa.biblioteca.model.Libro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @file Main.java
 * @author Gruppo 9
 * @brief Classe principale che avvia l'applicazione.
 *  Estende la classe Application di JavaFX. Inizializza la finestra principale (Stage), carica la prima schermata (Home) e permette interazioni con il bibliotecario.
 * @mainpage Documentazione Progetto Biblioteca Universitaria
 * @section intro Introduzione
 * Documentazione per il sistema di gestione per la Biblioteca Universitaria.
 * Il software permette al bibliotecario di gestire:
 * - Libri: Dati, Elenco, inserimento, ricerca e rimozione.
 * - Utenti: Dati anagarfici, inserimento, ricerca e rimozione.
 * - Prestiti: Registrazione prestiti e restituzioni.
 * 
 * @date 03 Dicembre, 2025
 */

public class Main extends Application {

   @Override
    public void start(Stage primaryStage) throws Exception {

        // Carico Interfaccia Home Page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/InterfacciaHomeView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Biblioteca Universitaria - Home");
        primaryStage.setScene(new Scene(root));
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
