/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.controller;

import it.unisa.biblioteca.model.Utente;
import it.unisa.biblioteca.model.Libro; 
import it.unisa.biblioteca.model.Prestito; 
import it.unisa.biblioteca.model.GestioneUtente; 
import it.unisa.biblioteca.model.GestioneLibro; 
import it.unisa.biblioteca.model.GestionePrestito; 
import it.unisa.biblioteca.model.GestioneEccezioni; 
import java.awt.Color;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.util.List;
import java.time.LocalDate; 
import java.util.Set;
import java.util.TreeSet;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @file InterfacciaPrestitiController.java
 * @author Gruppo 9
 * @brief questo file contiene i metodi e la logica per il controllo dell'interfaccia per la gestione dei prestiti.
 * 
 * Fa da tramite tra le interazione con il bibliotecario (nella view) e i dati (nel model).
 * 
 * @date 03 Dicembre, 2025
 */
public class InterfacciaPrestitiController {
    
    //RITORNO HOME
    @FXML private Button btnHomePrestiti;
    
    // RICERCA UTENTE
    @FXML private TextField tfRicercaUtente;
    @FXML private MenuButton menuFiltroUtente;
    @FXML private Button btnCercaUtente;
    @FXML private Label lblUtenteTrovato;

    
    // RICERCA LIBRO 
    @FXML private TextField tfRicercaLibro;
    @FXML private MenuButton menuFiltroLibro;
    @FXML private Button btnCercaLibro;
    @FXML private Label lblLibroTrovato;
    
    // REGISTRAZIONE PRESTITO
    @FXML private Button btnRegistraPrestito;
    
    //TABELLA PRESTITI ATTIVI
    @FXML private Button btnConfermaRestituzionePrestiti;
    
    @FXML private TableView<Prestito> tabellaPrestiti;
    
    @FXML private TableColumn<Prestito, Integer> colonnaMatricolaPrestiti;
    @FXML private TableColumn<Prestito, String> colonnaCognomePrestiti;
    @FXML private TableColumn<Prestito, String> colonnaTitoloPrestiti;
    @FXML private TableColumn<Prestito, String> colonnaCodicePrestiti;
    @FXML private TableColumn<Prestito, LocalDate> colonnaDataPrestiti;
    
    private ObservableList<Prestito> listaPrestiti;
    
    // OGGETTI TROVATI 
    private Utente utenteSelezionato = null;
    private Libro libroSelezionato = null;
    
    
    // filtri attivi
    private String filtroUtente = null;
    private String filtroLibro = null;
    
    @FXML
    public void initialize() {

        //FILTRI UTENTE
        MenuItem fMatricola = new MenuItem("Matricola");
        fMatricola.setOnAction(e -> {
            filtroUtente = "matricola";
            menuFiltroUtente.setText("Matricola");
        });

        MenuItem fCognome = new MenuItem("Cognome");
        fCognome.setOnAction(e -> {
            filtroUtente = "cognome";
            menuFiltroUtente.setText("Cognome");
        });

        menuFiltroUtente.getItems().addAll(fMatricola, fCognome);
        
        //FILTRI LIBRO
        MenuItem fCodice = new MenuItem("Codice");
        fCodice.setOnAction(e -> {
            filtroLibro = "codice";
            menuFiltroLibro.setText("Codice");
        });

        MenuItem fTitolo = new MenuItem("Titolo");
        fTitolo.setOnAction(e -> {
            filtroLibro = "titolo";
            menuFiltroLibro.setText("Titolo");
        });

        MenuItem fAutore = new MenuItem("Autore");
        fAutore.setOnAction(e -> {
            filtroLibro = "autore";
            menuFiltroLibro.setText("Autore");
        });

        menuFiltroLibro.getItems().addAll(fCodice, fTitolo, fAutore);
        
        //RESTITUISCI PRESTITO
        listaPrestiti = FXCollections.observableArrayList();
        
        colonnaMatricolaPrestiti.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getUtente().getMatricola()));
            
        colonnaCognomePrestiti.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUtente().getCognome()));
            
        colonnaTitoloPrestiti.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getLibro().getTitolo()));
            
        colonnaCodicePrestiti.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getLibro().getCodice()));
            
        colonnaDataPrestiti.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getDataRestituzione()));
        
        tabellaPrestiti.setItems(listaPrestiti);
        
        colonnaDataPrestiti.setCellFactory(column -> {
    return new TableCell<Prestito, LocalDate>() {
        @Override
        protected void updateItem(LocalDate data, boolean empty) {
            super.updateItem(data, empty);

            // 1. Pulizia della cella se è vuota
            if (data == null || empty) {
                setText(null);
                setStyle(""); // Rimuove stili precedenti
            } else {
                // 2. Imposta il testo (la data)
                setText(data.toString());

                // 3. Recupera l'oggetto Prestito di questa riga
                Prestito prestitoCorrente = getTableView().getItems().get(getIndex());

                // 4. Controllo Ritardo
                // Se ritardoPrestito restituisce TRUE quando è in ritardo (come da codice caricato):
                if (prestitoCorrente.ritardoPrestito(data)) {
                    // STILE PER IL RITARDO: Rosso e Grassetto
                    setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                } else {
                    // STILE NORMALE: Nero (o default)
                    setStyle("-fx-text-fill: black;");
                }
                }
        }
    };
        });
        
        //BUTTON
        btnCercaUtente.setOnAction(e -> cercaUtente());
        btnCercaLibro.setOnAction(e -> cercaLibro());
        btnRegistraPrestito.setOnAction(e -> registraPrestito());
        
        if (btnConfermaRestituzionePrestiti != null) {
            btnConfermaRestituzionePrestiti.setOnAction(e -> azioneRestituisciPrestito());
        }
        
        btnHomePrestiti.setOnAction(e -> ritornoHome("/it/unisa/biblioteca/view/InterfacciaHomeView.fxml"));
        
        aggiornaTabella();
    }
    
    private void cercaUtente() {
        String input = tfRicercaUtente.getText().trim();

        lblUtenteTrovato.setText("");
        utenteSelezionato = null;
        String messaggioDiAvviso = null;

        if (input.isEmpty()) {
            lblUtenteTrovato.setText("⚠ Inserire un valore di ricerca.");
            return;
        }
        if (filtroUtente == null) {
            mostraErrore("⚠ Selezionare un filtro di ricerca per l'utente.");
            return;
        }

        try {
            switch (filtroUtente) {

            case "matricola":
                int m = Integer.parseInt(input);
                utenteSelezionato = GestioneUtente.getInstance().cercaUtenteMatricola(m);
                break;

            case "cognome":
                List<Utente> risultati = GestioneUtente.getInstance().cercaUtenteCognome(input);
                if (risultati.size() > 1) { 
                    messaggioDiAvviso = "⚠ Trovati " + risultati.size() + " utenti con questo cognome. Selezionato il primo in lista. Ricercare per Matricola per essere precisi.\n";
                }
                utenteSelezionato = risultati.get(0);  // prendi il primo utente trovato
                break;
            }

         if (messaggioDiAvviso != null) {
            lblUtenteTrovato.setText(messaggioDiAvviso + "Utente selezionato: " +
                utenteSelezionato.getNome() + " " +
                utenteSelezionato.getCognome() +
                " (Matricola: " + utenteSelezionato.getMatricola() + ")");
            
        } else {
            // Se NON c'è avviso (cioè matricola o cognome singolo), stampiamo solo il successo standard
            lblUtenteTrovato.setText(
                "✔ Utente trovato: " +
                utenteSelezionato.getNome() + " " +
                utenteSelezionato.getCognome() +
                " (Matricola: " + utenteSelezionato.getMatricola() + ")"
            );
        }

        } catch (NumberFormatException e) {
            lblUtenteTrovato.setText("La matricola deve essere un numero.");

        } catch (GestioneEccezioni e) {
            // Qui entri se il Model NON trova l'utente
            lblUtenteTrovato.setText(e.getMessage());
        }
    }
    
    private void cercaLibro() {
        String input = tfRicercaLibro.getText().trim();
        String messaggioDiAvviso = null;
        
        lblLibroTrovato.setText("");
        libroSelezionato = null;

        if (input.isEmpty()) {
            lblLibroTrovato.setText("⚠ Inserire un valore di ricerca.");
            return;
        }
        
        if (filtroLibro == null) {
            mostraErrore("⚠ Selezionare un filtro di ricerca per il libro.");
            return;
        }
        
        try {
            switch (filtroLibro) {

                case "codice":
                    // Chiamata al metodo che cerca per codice (assumiamo Codice sia String)
                    libroSelezionato = GestioneLibro.getInstance().ricercaLibroCodice(input);
                    break;

                case "titolo":
                    // Chiamata al metodo che cerca per titolo
                    Set<Libro> setLibriFirst = GestioneLibro.getInstance().ricercaLibroTitolo(input);
                    TreeSet<Libro> risultatiFirst = (TreeSet)setLibriFirst;
                    if (risultatiFirst.size() > 1) { 
                        messaggioDiAvviso = "⚠ Trovati " + risultatiFirst.size() + " libri con questo titolo. Selezionato il primo in lista. Ricercare per Codice per essere precisi.\n";
                    }
                    libroSelezionato = risultatiFirst.first();
                    //libriSelezionati = GestioneLibro.getInstance().ricercaLibroTitolo(input);
                    break;

                case "autore":
                    // L'input viene suddiviso in nome e cognome
                    String[] partiAutore = input.split(" ", 2); 
                    if (partiAutore.length < 2) {
                        lblLibroTrovato.setText("⚠ Per Autore, inserire Nome e Cognome separati da spazio.");
                        return;
                    }
                    String nomeAutore = partiAutore[0];
                    String cognomeAutore = partiAutore[1];
                    
                    // Chiamata al metodo che cerca per autore
                    Set<Libro> setLibriSecond = GestioneLibro.getInstance().ricercaLibroAutore(nomeAutore, cognomeAutore);
                    TreeSet<Libro> risultatiSecond = (TreeSet)setLibriSecond;
                    if (risultatiSecond.size() > 1) { 
                        messaggioDiAvviso = "⚠ Trovati " + risultatiSecond.size() + " libri con questo autore. Selezionato il primo in lista. Ricercare per Codice per essere precisi.\n";
                    }
                    libroSelezionato = risultatiSecond.first();
                    //libriSelezionati = GestioneLibro.getInstance().ricercaLibroAutore(nomeAutore, cognomeAutore);
                    break;
            }

            // Se siamo qui → libro trovato
            if (messaggioDiAvviso != null) {
            lblLibroTrovato.setText(messaggioDiAvviso + "Libro selezionato: " +
                libroSelezionato.getTitolo() + " " +
                libroSelezionato.getNomeAutore() +
                " " + libroSelezionato.getCognomeAutore() +
                " (Codice: " + libroSelezionato.getCodice() + ")");
            }else{
             lblLibroTrovato.setText(
                "✔ Libro trovato: " +
                libroSelezionato.getTitolo() + " - " +
                libroSelezionato.getNomeAutore() + " " + libroSelezionato.getCognomeAutore() +
                " (Codice: " + libroSelezionato.getCodice() + ")"
            );
        
         }

        } catch (GestioneEccezioni e) {
            // Qui entri se il Model NON trova il libro o se c'è un altro errore di gestione
            lblLibroTrovato.setText(e.getMessage());
            
        } catch (Exception e) {
             // Catch-all per problemi inattesi o metodi mancanti nel Model
            lblLibroTrovato.setText("Errore imprevisto durante la ricerca.");
            System.err.println("Errore in cercaLibro: " + e.getMessage());
        }
    }
    
    
    //REGISTRA PRESTITO

    private void registraPrestito() {
        if (utenteSelezionato == null) {
           mostraErrore("Prima selezionare un utente.");
           return;
        }

        if (libroSelezionato == null) {
            mostraErrore("Prima selezionare un libro.");
            return;
        }
        
        LocalDate dataRestituzionePrevista = LocalDate.now().plusDays(90);
        Prestito nuovoPrestito = new Prestito(utenteSelezionato, libroSelezionato, dataRestituzionePrevista);
        
        try {
            GestionePrestito.getInstance().registraPrestito(nuovoPrestito);
            //per slavare anche le modifiche copie su libro
            GestioneLibro.getInstance().modifica(libroSelezionato);
            mostraInfo("Prestito registrato con successo! Data di restituzione prevista: " + dataRestituzionePrevista);

            utenteSelezionato = null;
            libroSelezionato = null;
            lblUtenteTrovato.setText("");
            lblLibroTrovato.setText("");

        } catch (GestioneEccezioni e) {
        
        mostraErrore("Impossibile registrare il prestito: " + e.getMessage());

    } catch (Exception e) {
        mostraErrore("Errore imprevisto durante la registrazione del prestito.");
        System.err.println("Errore in registraPrestito del Controller: " + e.getMessage());
    }
        aggiornaTabella();
    }
    
    //RESTITUISCI PRESTITO
    
    public void aggiornaTabella(){
        listaPrestiti.clear(); //prima rimuovo tutto
        listaPrestiti.addAll(GestionePrestito.getInstance().getElencoPrestitiCompleto()); //prendo tutti i prestiti attualmente salvati
        if(listaPrestiti != null){
            tabellaPrestiti.setItems(listaPrestiti); //metto gli elemnti della lista nella tabel view
        }
    }
    
    private void azioneRestituisciPrestito() {
        // 1. Ottieni l'elemento selezionato nella tabella
        Prestito prestitoSelezionato = tabellaPrestiti.getSelectionModel().getSelectedItem();
        
        if (prestitoSelezionato == null) {
            mostraErrore("Seleziona un prestito dalla tabella per restituirlo.");
            return;
        }
        
        try {
            // 2. Chiama il Model per la logica di business (rimuove dalla mappa, incrementa copie, ecc.)
            GestionePrestito.getInstance().restituisciPrestito(prestitoSelezionato);
            //per salvare le modifiche anche sul libri.dat
            Libro libroRestituito = prestitoSelezionato.getLibro();
            GestioneLibro.getInstance().modifica(libroRestituito);
            // 3. Aggiorna la Vista (Rimuovi dalla tabella)
            listaPrestiti.remove(prestitoSelezionato);
            
            aggiornaTabella();
            
            mostraInfo("Libro restituito con successo.");
            
        } catch (GestioneEccezioni e) {
            mostraErrore("Errore restituzione: " + e.getMessage());
        }
    }
    
    
    //UTILITIES PER MESSAGGI
    private void mostraErrore(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(msg);
        a.show();
    }

    private void mostraInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.show();
    }

    private void ritornoHome(String fxmlPathHome) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPathHome));
            Parent root = loader.load(); //il load poi chiama in automatico il metodo inizialize specifico per ogni controller

            // Ottengo lo stage corrente (la finestra) da un bottone qualsiasi
            Stage stage = (Stage) btnHomePrestiti.getScene().getWindow();
            
            stage.setScene(new Scene(root));
            stage.setTitle("Biblioteca Universitaria - Home");
            stage.centerOnScreen();
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            mostraErrore("Impossibile caricare l'interfaccia: " + fxmlPathHome + "\nControlla che il file esista e il percorso sia corretto.");
        }
    }
}
