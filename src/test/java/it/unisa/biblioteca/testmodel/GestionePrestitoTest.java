/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.testmodel;

import it.unisa.biblioteca.model.GestioneEccezioni;
import it.unisa.biblioteca.model.GestioneLibro;
import it.unisa.biblioteca.model.GestionePrestito;
import it.unisa.biblioteca.model.Libro;
import it.unisa.biblioteca.model.Prestito;
import it.unisa.biblioteca.model.Utente;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestionePrestitoTest {
    
    public GestionePrestitoTest() {
    }
    
    
    //Mi serve per evitare di lavorare sugli stessti file binari che uso per l'applicazione (evito problemi di persistenza)
    @BeforeEach
    public void setUp() {
        System.out.println("--- SETUP TEST PRESTITI ---");
        
        //faccio una pulizia preventiva per eviate di avere file binari con elemneti di test precedenti
        new File("libri_TEST.dat").delete();
        new File("prestiti_TEST.dat").delete();
        new File("date_TEST.dat").delete();

        //Configuro GestioneLibro (il file verrà creato per la prima volta, vuoto)
        GestioneLibro.getInstance().reset();
        GestioneLibro.getInstance().setNomeFile("libri_TEST.dat");
        
        //Configuro GestioneLibro (il file verrà creato per la prima volta, vuoto)
        GestionePrestito.getInstance().reset();
        GestionePrestito.getInstance().setFilePathsForTest("prestiti_TEST.dat", "date_TEST.dat");
    }

    // TEST REGISTRAZIONE:

    @Test
    public void testRegistraPrestitoSuccesso() throws GestioneEccezioni {
        System.out.println("Test: Registrazione Prestito Valido");
        //Creo un utente e un libro
        Utente u = new Utente("Mario", "Rossi", 100, "m@r.it");
        Libro l = new Libro("Java Base", "Autore", "X", 2020, "AA0123", 5); // 5 copie
        
        //Inserisco il libro nella collezione (vuota)
        GestioneLibro.getInstance().inserisci(l);
        
        //creo un prestito con l'utente e il libro che ho creato
        Prestito p = new Prestito(u, l, LocalDate.now().plusDays(30));
        
        //aggiungo il prestito nella collezzione (vuota)
        GestionePrestito.getInstance().registraPrestito(p);
        
        // Verifico che sia presente solo un prestito nella collezzione:
        List<Prestito> elenco = GestionePrestito.getInstance().getElencoPrestitiCompleto();
        assertEquals(1, elenco.size());
        
        // Verifico che all'aggiunta del prestito siano effetivamente diminuite le copie disponibili del libro:
        assertEquals(4, l.getCopieDisponibili());
    }
    
    @Test
    public void testRegistraPrestitoSenzaCopie() throws GestioneEccezioni {
        System.out.println("Test: Prestito fallito per copie esaurite");
        //Creo un utente e un libro con 0 copie
        Utente u = new Utente("Mario", "Rossi", 100, "m@r.it");
        Libro l = new Libro("Libro Raro", "A", "B", 2000, "AA1234", 0); // 0 COPIE!
        
        //inserisco il libro nella collezione (vuota)
        GestioneLibro.getInstance().inserisci(l); 
        
        //creo un prestito con l'utente e il libro che ho creato
        Prestito p = new Prestito(u, l, LocalDate.now().plusDays(30));
        
        //Verifico che sia lanciatta un eccezione (non si può registare un orestito con un libro senza copie disponibili)
        assertThrows(GestioneEccezioni.class, () -> {
            GestionePrestito.getInstance().registraPrestito(p);
        });
    }

    @Test
    public void testRestituzionePrestito() throws GestioneEccezioni {
        System.out.println("Test: Restituzione Corretta");
        //creo un utente un libro
        Utente u = new Utente("Luigi", "Verdi", 200, "l@v.it");
        Libro l = new Libro("Storia", "A", "B", 1990, "AA9874", 1); 
        
        //inserisco il libro nella collezione (vuota)
        GestioneLibro.getInstance().inserisci(l);
        
        //creo un prestito con l'utente e il libro che ho creato
        Prestito p = new Prestito(u, l, LocalDate.now());
        
        // Inserisco il prestito nella collezione (vuota)
        GestionePrestito.getInstance().registraPrestito(p);
        //controllo che sia stato decrementata corretamnte il numero di copie disponibili
        assertEquals(0, l.getCopieDisponibili());
        
        // registro la restituzione del prestito
        GestionePrestito.getInstance().restituisciPrestito(p);
        
        // Controllo che non ci siano prestiti nella collezione e che le copie dei libri disponibili sia stata incrementata
        assertEquals(0, GestionePrestito.getInstance().getElencoPrestitiCompleto().size());
        assertEquals(1, l.getCopieDisponibili());
    }
    
    
    
    @Test
    public void testLimiteMassimoPrestiti() throws GestioneEccezioni {
        System.out.println("Test: Limite Max 3 Prestiti");
        //creo un utente
        Utente u = new Utente("Mario", "Bibliofilo", 100, "m@b.it");
        
        // Creo 4 libri
        Libro l1 = new Libro("L1", "A", "B", 2000, "AA1001", 5);
        Libro l2 = new Libro("L2", "A", "B", 2000, "AA1002", 5);
        Libro l3 = new Libro("L3", "A", "B", 2000, "AA1003", 5);
        Libro l4 = new Libro("L4", "A", "B", 2000, "AA1004", 5); 
        
        // inserisco i 4 libri nella collezione (vuota)
        GestioneLibro.getInstance().inserisci(l1);
        GestioneLibro.getInstance().inserisci(l2);
        GestioneLibro.getInstance().inserisci(l3);
        GestioneLibro.getInstance().inserisci(l4);
        
        // registro 3 prestiti allo stesso utente 
        GestionePrestito.getInstance().registraPrestito(new Prestito(u, l1, LocalDate.now()));
        GestionePrestito.getInstance().registraPrestito(new Prestito(u, l2, LocalDate.now()));
        GestionePrestito.getInstance().registraPrestito(new Prestito(u, l3, LocalDate.now()));
        
        // verifico che se provo a registrare il 4 prestito venga lanciata un eccezione
        GestioneEccezioni e = assertThrows(GestioneEccezioni.class, () -> {
            GestionePrestito.getInstance().registraPrestito(new Prestito(u, l4, LocalDate.now()));
        });
        
        //verifico che il messaggio di errore lanciato sia quello specifico per questa eccezione
        assertTrue(e.getMessage().contains("limite massimo"), "Deve segnalare il raggiungimento del limite");
    }
    
    
    
    
    
    // TEST PERSISTENZA:
    
    @Test
    public void testPersistenzaPrestiti() throws GestioneEccezioni {
        System.out.println("Test: Persistenza Prestiti");
        //creo utente e libro ed inserisco libro nella collezione (vuota)
        Utente u = new Utente("Persistenza", "User", 999, "p@u.it");
        Libro l = new Libro("Persistenza Book", "A", "B", 2022, "AB1475", 10);
        GestioneLibro.getInstance().inserisci(l);
        
        //creo un prestito con libro e utento e lo inserisco nella collezione (vuota)
        Prestito p = new Prestito(u, l, LocalDate.now().plusDays(15));
        GestionePrestito.getInstance().registraPrestito(p);
        
        // Tramite il metodo reset simulo una chiusra dell'applicazione (elimina salvatggi nella RAM)
        GestionePrestito.getInstance().reset();
        GestioneLibro.getInstance().reset();
        
        // Simulo una riapertura dell'applicazione con la configurazione in inout sui file binari
        GestioneLibro.getInstance().setNomeFile("libri_TEST.dat"); 
        GestionePrestito nuovaIstanza = GestionePrestito.getInstance();
        nuovaIstanza.setFilePathsForTest("prestiti_TEST.dat", "date_TEST.dat");
        
        //ottengo la lista di tutti i prestiti registrati
        List<Prestito> elenco = nuovaIstanza.getElencoPrestitiCompleto();
        
        //controllo che l'unico prestito che avevo registarto sia ci sia ancora non ostante la chiusra dell'applicazione
        assertEquals(1, elenco.size());
        
        //controllo che il libro presente nell'prestito sia effetivamente quello che avevo inserito 
        assertEquals("AB1475", elenco.get(0).getLibro().getCodice());
    }
}
