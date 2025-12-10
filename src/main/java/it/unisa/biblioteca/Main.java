/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca;

import it.unisa.biblioteca.model.GestioneUtente;
import it.unisa.biblioteca.model.Utente;
import it.unisa.biblioteca.model.GestioneEccezioni;

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
        //da completare
        // 1. INSERIMENTO DATI DI PROVA (Opzionale, ma utile per testare!)
        // Inserisco manualmente degli utenti per vedere se appaiono nella tabella all'avvio.
        System.out.println("Caricamento dati di prova...");
        try {
            GestioneUtente.getInstance().inserisci(new Utente("Mario", "Rossi", 12345, "mario.rossi@università.it"));
            GestioneUtente.getInstance().inserisci(new Utente("Luigi", "Verdi", 67890, "luigi.verdi@università.it"));
        } catch (GestioneEccezioni e) {
            System.err.println("Errore inserimento dati prova: " + e.getMessage());
        }

        // 2. CARICAMENTO DELL'INTERFACCIA GRAFICA
        // Carico il file FXML della schermata principale (la lista utenti)
        // NOTA: Assicurati che il percorso "/it/unisa/..." sia corretto. Se il file è nella cartella view, è ok.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/biblioteca/view/InterfacciaUtentiView.fxml"));
        Parent root = loader.load();
        
        // 3. IMPOSTAZIONI DELLA FINESTRA (STAGE)
        primaryStage.setTitle("Gestione Biblioteca - Sezione Utenti");
        primaryStage.setScene(new Scene(root));
        //primaryStage.setResizable(false); // Blocca il ridimensionamento se vuoi mantenere il layout fisso
        
        // 4. MOSTRA LA FINESTRA
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
