package it.unisa.biblioteca.testmodel;

import it.unisa.biblioteca.model.GestioneEccezioni;
import it.unisa.biblioteca.model.GestioneUtente;
import it.unisa.biblioteca.model.Utente;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestioneUtenteTest {
    
    public GestioneUtenteTest() {
    }
    
    //mi serve per eviatre problemi con persistenza dei dati sui file
    @BeforeEach
    public void setUp() {
        System.out.println("--- INIZIO SETUP ---");
        // Reset Singleton
        GestioneUtente.getInstance().reset(); 
        
        // prendo istanza
        GestioneUtente gestione = GestioneUtente.getInstance();
        
        // Copia per iterare e cancellare
        if (!gestione.getTutti().isEmpty()) {
            // pezzoto per eliminare utenti tramite un for each (se no avrei dovuto usare un iteratore)
            Utente[] utentiSporchi = gestione.getTutti().toArray(new Utente[0]);
            for (Utente u : utentiSporchi) {
                gestione.elimina(u);
            }
        }
        assertEquals(0, gestione.getTutti().size(), "Il setup ha fallito: la lista non è vuota!");
    }
    
    @AfterEach
    public void tearDown() {
        GestioneUtente.getInstance().reset();
    }

    // TEST INSERIMENTO:

    @Test
    public void testInserimentoCorretto() throws Exception {
        System.out.println("Test: Inserimento Normale");
        //creo l'utente da inserie (collezione inizialmente vuota)
        Utente u = new Utente("Mario", "Rossi", 1001, "mario.rossi@università.it");
        GestioneUtente.getInstance().inserisci(u);
        //controllo che ci sia solo un lemento nella collezione
        assertEquals(1, GestioneUtente.getInstance().getTutti().size());
        //controllo che l'utent epresente sia uguale a quello inserito
        Utente trovato = GestioneUtente.getInstance().cercaUtenteMatricola(1001);
        assertNotNull(trovato);
        assertEquals("Mario", trovato.getNome());
    }
    
    @Test
    public void testInserimentoMatricolaDuplicata() throws GestioneEccezioni {
        System.out.println("Test: Inserimento Matricola Duplicata");
        //creo 2 utenti diversi ma con la stessa matricola
        Utente u1 = new Utente("Mario", "Rossi", 1001, "mario.rossi@università.it");
        Utente u2 = new Utente("Luigi", "Verdi", 1001, "luigi.verdi@università.it"); //duplicato
        //inserisco il primo utente (collezione inizialmente vuota)
        GestioneUtente.getInstance().inserisci(u1);

        //quando vado a inserire il secondo utente (collezione ha gia l'utente di prima) mi aspetto un eccezione
        GestioneEccezioni exception = assertThrows(GestioneEccezioni.class, () -> {
            GestioneUtente.getInstance().inserisci(u2);
        });
        //controlo che il messaggio di errore sia quello specifico della matricola:
        assertTrue(exception.getMessage().contains("matricola"), "Il messaggio d'errore dovrebbe menzionare la matricola");
    }

    //TEST CONTROLLO EMAIL:
    //il test del email del formato giusto è implicitamente fatto nel inserimento corretto

    @Test
    public void testEmailFormatoSbagliato() {
        System.out.println("Test: Email Formato Errato (Dominio sbagliato)");
        // Creo un utente con un email con un farmato sbagliato (cambio il dominio)
        Utente u = new Utente("Mario", "Rossi", 1002, "mario.rossi@gmail.com");
        
        //quando vado a inserire l'utente (collezione vuota) mi aspetto lanci un eccezzione
        GestioneEccezioni e = assertThrows(GestioneEccezioni.class, () -> {
            GestioneUtente.getInstance().inserisci(u);
        });
        //controlo che il messaggio di errore sia quello specifico dell formato email non valido:
        assertTrue(e.getMessage().contains("formato"), "Deve segnalare errore formato");
    }

    @Test
    public void testEmailNomeSbagliato() {
        System.out.println("Test: Email non corrisponde al nome");
        // Creo un utente con un nome ma poi nel email ne metto un altro
        Utente u = new Utente("Mario", "Rossi", 1003, "luigi.rossi@università.it");
        
        //quando vado a inserire l'utente (collezione vuota) mi aspetto lanci un eccezzione
        assertThrows(GestioneEccezioni.class, () -> {
            GestioneUtente.getInstance().inserisci(u);
        });
    }

    @Test
    public void testEmailDuplicata() throws GestioneEccezioni {
        System.out.println("Test: Email Duplicata su Matricola Diversa");
        // Creo 2 utenti con stesso nome e cognome ma matricole diverse, gli passo la stessa email (mi aspetto un eccezione)
        Utente u1 = new Utente("Anna", "Verdi", 2001, "anna.verdi@università.it");
        Utente u2 = new Utente("Anna", "Verdi", 2002, "anna.verdi@università.it");

        //inserisco il primo utente (non mi aspetto errori)
        GestioneUtente.getInstance().inserisci(u1);
        
        //quando vado a inserire l'utente (collezione con l'utente di prima inserito) mi aspetto lanci un eccezzione
        GestioneEccezioni e = assertThrows(GestioneEccezioni.class, () -> {
            GestioneUtente.getInstance().inserisci(u2);
        });
        
        //controlo che il messaggio di errore sia quello specifico dell email già in uso:
        assertTrue(e.getMessage().contains("in uso"), "Deve dire che l'email è in uso");
    }
    
    @Test
    public void testEmailConNumeri() throws GestioneEccezioni {
        System.out.println("Test: Email con numero finale (omonimia)");
        // creo un utente con un email con il numero dopo il cognome (lo uso quando ci sono omonimi)
        Utente u = new Utente("Mario", "Rossi", 1004, "mario.rossi1@università.it");
        
        //inserisco l'utente (collezione vuota) e non mi apsetto errori
        GestioneUtente.getInstance().inserisci(u);
        
        //controllo che ci sia solo lui
        assertEquals(1, GestioneUtente.getInstance().getTutti().size());
    }

    // TEST RICERCA:

    @Test
    public void testRicercaMatricolaSuccesso() throws GestioneEccezioni {
        //creo ed inserisco 3 utenti con matricole diverse(collezione vuota)
        Utente u = new Utente("Luca", "Bianchi", 3001, "luca.bianchi@università.it");
        Utente u1 = new Utente("Mario", "Rossi", 1004, "mario.rossi1@università.it");
        Utente u2 = new Utente("Anna", "Verdi", 2002, "anna.verdi@università.it");
        GestioneUtente.getInstance().inserisci(u);
        GestioneUtente.getInstance().inserisci(u1);
        GestioneUtente.getInstance().inserisci(u2);
        
        //effetua la ricerca per matricola per ritrovare quel utente
        Utente trovato = GestioneUtente.getInstance().cercaUtenteMatricola(3001);
        
        //controllo che ritorni qualcosa
        assertNotNull(trovato);
        
        //controllo che ritorni l'utente che effetivamente stavo cercando:
        assertEquals("Luca", trovato.getNome());
    }

    @Test
    public void testRicercaMatricolaFallimento1()throws GestioneEccezioni {
        //in caso non inserisco niente la collezione rimane vuouta e mi aspetto un fallimento nella ricerca
        assertThrows(GestioneEccezioni.class, () -> {
            GestioneUtente.getInstance().cercaUtenteMatricola(9999);
        });
    }
    
    @Test
    public void testRicercaMatricolaFallimento2()throws GestioneEccezioni {
        //creo ed inserisco 3 utenti con matricole diverse(collezione vuota)
        Utente u = new Utente("Luca", "Bianchi", 3001, "luca.bianchi@università.it");
        Utente u1 = new Utente("Mario", "Rossi", 1004, "mario.rossi1@università.it");
        Utente u2 = new Utente("Anna", "Verdi", 2002, "anna.verdi@università.it");
        GestioneUtente.getInstance().inserisci(u);
        GestioneUtente.getInstance().inserisci(u1);
        GestioneUtente.getInstance().inserisci(u2);
        
        //provo a ricercare un utente con una matricola non presente (mi aspetto un eccezione)
        assertThrows(GestioneEccezioni.class, () -> {
            GestioneUtente.getInstance().cercaUtenteMatricola(9999);
        });
    }
    
    @Test
    public void testRicercaCognomeFallimento() throws GestioneEccezioni {
    // creo ed inserisco un utente (collezione vuota)
    GestioneUtente.getInstance().inserisci(new Utente("Mario", "Bianchi", 9001, "mario.bianchi@università.it"));
    
    // faccio una ricerca per cognome passando un cognome che non c'è nella collezione, mi aspetto un eccezione
    GestioneEccezioni e = assertThrows(GestioneEccezioni.class, () -> {
            GestioneUtente.getInstance().cercaUtenteCognome("Rossi");
        });
    
    // controllo che il messagggio di errore sia quello specifico di quessto caso
    assertTrue(e.getMessage().contains("Utente non trovato"), "Deve dire che l'utente non è stato trovato");
    }

    @Test
    public void testRicercaCognomeMultiplo() throws GestioneEccezioni {
        System.out.println("Test: Ricerca Cognome (Risultati Multipli)");
        //creo ed inserisco 3 utenti con matricole diverse ma 2 cognomi uguali (collezione vuota)
        Utente u1 = new Utente("Paolo", "Neri", 4001, "paolo.neri@università.it");
        Utente u2 = new Utente("Gino", "Neri", 4002, "gino.neri@università.it");
        Utente u3 = new Utente("Ugo", "Viola", 4003, "ugo.viola@università.it");

        GestioneUtente.getInstance().inserisci(u1);
        GestioneUtente.getInstance().inserisci(u2);
        GestioneUtente.getInstance().inserisci(u3);
        
        //ricerco utenti per cognome (mi aspetto che trovi entrambi gli utenti con lo stesso cognome)
        List<Utente> risultati = GestioneUtente.getInstance().cercaUtenteCognome("Neri");
        
        //controllo che trova entrambi gli utenti:
        assertEquals(2, risultati.size(), "Dovrebbe trovare 2 utenti con cognome Neri");
    }

    //TEST MODIFICA:
    //non posso testare il caso in cui si modifica la matricola poichè abbiamo reso il text field non modificabile (è un vincilo che abbiamo aggiunto)
    @Test
    public void testModificaUtente() throws GestioneEccezioni {
        System.out.println("Test: Modifica Utente");
        //creo utente e lo inserisco nella collezione (collezione vuota)
        Utente originale = new Utente("Sara", "Gialli", 5001, "sara.gialli@università.it");
        GestioneUtente.getInstance().inserisci(originale);

        // creo un altro utente con alcuni campi modificati ma STESSA MATRICOLA 
        Utente modificato = new Utente("Sara", "Verdi", 5001, "sara.verdi@università.it"); // Cambiato cognome e email
        
        //chiamo operazione di modifica per modificare l'utente che è presente nella collezione con i campi di quello modificato (LA MATRICOLA RESTA UGUALE)
        GestioneUtente.getInstance().modifica(modificato);

        // recupero l'unico utente nella collezione e verifico se si sono modificati i campi:
        Utente recuperato = GestioneUtente.getInstance().cercaUtenteMatricola(5001);
        assertEquals("Verdi", recuperato.getCognome());
        assertEquals("sara.verdi@università.it", recuperato.getEmail());
    }
    

    //TEST ELIMINAZIONE:

    @Test
    public void testEliminaUtente() throws GestioneEccezioni {
        System.out.println("Test: Elimina Utente");
        //creao e aggiungo un utente alla collezione (Collezione vuota)
        Utente u = new Utente("Dario", "Fo", 6001, "dario.fo@università.it");
        GestioneUtente.getInstance().inserisci(u);
        
        //controllo per sicurezza che è stato aggiunto corretamente (non servirebbe in quanto ho gia fatto i test per l'inserimento)
        assertEquals(1, GestioneUtente.getInstance().getTutti().size());
        
        //elimino l'utente dalla mia collezione
        GestioneUtente.getInstance().elimina(u);
        
        //controllo che non ci sia più nessun utente nella collezione
        assertEquals(0, GestioneUtente.getInstance().getTutti().size());
    }
    

    //TEST PERSISTENZA (simulo una chiusura e riapertura dell'applicazione):
    //la logica di slavare e caricare i dati dal file è chiamata direttamente dentro i metodi inserisci-modifica-elimina
    @Test
    public void testPersistenzaDati() throws GestioneEccezioni {
        System.out.println("Test: Persistenza su File");
        
        // Inserisco un utente
        Utente u = new Utente("Persistenza", "Test", 7001, "persistenza.test@università.it");
        GestioneUtente.getInstance().inserisci(u);
        
        // Simulo la chiusura dell'app (il metodo reset mi fa perdere la reference all'istanza di gestioneUtenti, quindi è come se mi scollegassi dalla RAM)
        GestioneUtente.getInstance().reset(); 
        
        // Simulo il riavvio (con il metodo get istance ottengo la reference (poichè prima l avevo rimossa) a gestioenUtenti e controllo che i dati siano stati ricaricati dal file)
        GestioneUtente nuovaIstanza = GestioneUtente.getInstance();
        
        // Verifico che i dati sianao stati caricati dal file corretamente (l'unico utente che avevo inserito ci deve essere ancora)
        assertEquals(1, nuovaIstanza.getTutti().size());
        Utente trovato = nuovaIstanza.cercaUtenteMatricola(7001);
        assertEquals("Persistenza", trovato.getNome());
    }
}
