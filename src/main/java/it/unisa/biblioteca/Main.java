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
        
        // 1. CARICAMENTO DATI DI PROVA (Opzionale: Utile per trovare la tabella già popolata quando ci arrivi)
        try {
            GestioneUtente.getInstance().inserisci(new Utente("Mario", "Rossi", 12345, "mario.rossi@università.it"));
            GestioneUtente.getInstance().inserisci(new Utente("Luigi", "Verdi", 67890, "luigi.verdi@università.it"));
        } catch (GestioneEccezioni e) {
            // Ignora duplicati all'avvio
        }
        
        try{
            GestioneLibro.getInstance().inserisci(new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP3478", 2));
            GestioneLibro.getInstance().inserisci(new Libro("Il Signore degli Anelli", "JRR", "Tolkien", 1955, "LO1245", 2));
            GestioneLibro.getInstance().inserisci(new Libro("1984", "George", "Orwell", 1949, "GO2369", 3));
            GestioneLibro.getInstance().inserisci(new Libro("Dune", "Frank", "Herbert", 1965, "DU4312", 1));
        
        }catch(GestioneEccezioni e){
        
        }
        // 2. CARICAMENTO HOME PAGE
        // Assicurati che il nome del file FXML sia esattamente questo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/InterfacciaHomeView.fxml"));
        Parent root = loader.load();
        
        // 3. MOSTRA FINESTRA
        primaryStage.setTitle("Biblioteca Universitaria - Home");
        primaryStage.setScene(new Scene(root));
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
