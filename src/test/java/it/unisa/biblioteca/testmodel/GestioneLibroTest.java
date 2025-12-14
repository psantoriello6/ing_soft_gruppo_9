/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.testmodel;

import it.unisa.biblioteca.model.GestioneEccezioni;
import it.unisa.biblioteca.model.GestioneLibro;
import it.unisa.biblioteca.model.Libro;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 177748
 */
public class GestioneLibroTest {
    public GestioneLibroTest() {
    }
    
    //metodo che serve ad eseguire codice di inizializzazione prima di ogni singolo metodo di test.
    //In questo caso, il setUp serve ad evitare problemi con la persistenza dei dati su file
    @BeforeEach
    public void setUp() {
        //chiamata al metodo reset() per svuotare la memoria
        GestioneLibro.getInstance().reset();
        
        //creo un'istanza di gestioneLibro
        GestioneLibro gestione = GestioneLibro.getInstance();
        
        //i test scriveranno sul file libri_TEST.dat, lasciando intatto libri.dat
        gestione.setNomeFile("libri_TEST.dat");
        
       Set<Libro> collezioneLibri = new TreeSet<>(gestione.getSetLibro());
       Iterator<Libro> iter = collezioneLibri.iterator();
       //rimuovo gli elementi dal set 
       while(iter.hasNext()){
           iter.next();
           iter.remove();
       }
        
        
        
    }
    
    //funzione di "pulizia" eseguita dopo ogni singolo metodo di test per rilasciare risorse
    @AfterEach
    public void tearDown() {
        //dopo che un metodo di test è stato eseguito, svuoto la memoria per ritornare allo stato iniziale
        GestioneLibro.getInstance().reset();
    }

    /**
     * Testo of getInstance method, of class GestioneLibro.
     */
    @Test
    public void testGetInstance(){
        //creo un'istanza di GestioneLibro
        GestioneLibro primaIstanza = GestioneLibro.getInstance();
        
        //l'istanza creata non deve essere nulla
        assertNotNull(primaIstanza);
        
        //verifica dello stato: il set di libri deve essere inizializzato
        assertNotNull(primaIstanza.getSetLibro());
        
        //creo una seconda istanza
        GestioneLibro secondaIstanza = GestioneLibro.getInstance();
        
        //si verifica che i due riferimenti puntino allo stesso aggetto usando assertSame
        assertSame(primaIstanza, secondaIstanza);
    
    
    }

    /**
     * Test of inserisci method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testInserimentoCorretto() throws GestioneEccezioni {
        
        //creo il libro da inserire
        Libro lib1 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        
        //inserisco il libro nel set
        GestioneLibro.getInstance().inserisci(lib1);
        
        //verifico che il libro sia stato aggiunto
        assertTrue(GestioneLibro.getInstance().getSetLibro().contains(lib1));
        
       
    }
    
    /**
     * Second test of inserisci method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testInserimentoDuplicato() throws GestioneEccezioni{
        
        //creo il libro da inserire
        Libro lib1 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        
        //inserisco la prima volta il libro
        GestioneLibro.getInstance().inserisci(lib1);
        
        //se provo a reinserire nuovamente lo stesso libro, viene lanciata un'eccezione
        assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().inserisci(lib1));
    }

    /**
     * Third test of modifica method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testInserimentoCodiceNonValido() throws GestioneEccezioni {
        
         //creo un libro con un codice identificativo errato
         
         //caso 1: lunghezza diversa da 6
         Libro lib1 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO786", 3 );
         
         assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().inserisci(lib1));
        
         //caso 2: il codice inizia con un numero
         Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "3O7865", 3 );
         
          assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().inserisci(lib2));
         
          //caso 3: il codice ha tre lettere 
           Libro lib3 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "LOR786", 3 );
           
          assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().inserisci(lib3));  
           
          //caso 4: il codice ha una sola lettera
           Libro lib4 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "L93786", 3 );
           
          assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().inserisci(lib4)); 
          
          //caso 5: il codice contiene solo un numero
          Libro lib5 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "LOTRG6", 3 );
          
          assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().inserisci(lib5));
          
          //caso 6: il codice contiene due numeri
          Libro lib6 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "LOTR56", 3 );
          
          assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().inserisci(lib6));
          
          //caso 7: il codice contiene tre numeri
           Libro lib7 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "LOT756", 3 );
           
           assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().inserisci(lib7));
           
         
    }

    /**
     * First Test of elimina method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testEliminaFirst() throws GestioneEccezioni {
        
        Libro lib1 = new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP4532", 1 );
        
        GestioneLibro.getInstance().inserisci(lib1);
        
        //il libri non ha prestiti attivi: allora può essere rimosso
        GestioneLibro.getInstance().elimina(lib1);
        
        //verifica che il set di libri sia effettivamente vuoto, cioè che l'unico libro aggiunto sia stato eliminato
        assertEquals(0, GestioneLibro.getInstance().getSetLibro().size());
        
      
    }
    
    /**
     * Second Test of elimina method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    
    @Test
    public void testEliminaSecond() throws GestioneEccezioni{
        
        Libro lib1 = new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP4532", 1 );
        
        GestioneLibro.getInstance().inserisci(lib1);
       
        //incremento il numero di prestiti attivi del libro: ora il libro ha 1 prestito attivo
        lib1.incrementaPrestiti();
        
        //il libro non può essere rimosso
        
        assertTrue(GestioneLibro.getInstance().getSetLibro().contains(lib1));
        
    
    }

    /**
     * Test of ricercaLibroTitolo method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testRicercaLibroTitolo() throws GestioneEccezioni {
        
        //creo ed inserisco 3 libri
        Libro lib1 = new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP4532", 1 );
        Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        Libro lib3 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        GestioneLibro.getInstance().inserisci(lib1);
        GestioneLibro.getInstance().inserisci(lib2);
        GestioneLibro.getInstance().inserisci(lib3);
        
        //si effettua la ricerca per titolo per trovare un libro
        Libro libroTrovato = GestioneLibro.getInstance().ricercaLibroTitolo("1984");
        
        //controllo che ritorni qualcosa
        assertNotNull(libroTrovato);
        
        //controllo che ritorni effettivamente il libro che stavo cercando
        assertEquals("1984", libroTrovato.getTitolo());
        
        
        
       
    }

    /**
     * Test of ricercaLibroAutore method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testRicercaLibroAutore() throws GestioneEccezioni {
        //creo ed inserisco 3 libri
        Libro lib1 = new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP4532", 1 );
        Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        Libro lib3 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        GestioneLibro.getInstance().inserisci(lib1);
        GestioneLibro.getInstance().inserisci(lib2);
        GestioneLibro.getInstance().inserisci(lib3);
        
        //si effettua la ricerca per nome e cognome dell'autore per trovare un libro
        Libro libroTrovato = GestioneLibro.getInstance().ricercaLibroAutore("JRR", "Tolkien");
        
        //controllo che ritorni qualcosa
        assertNotNull(libroTrovato);
        
        //controllo che ritorni effettivamente il libro che stavo cercando
        assertEquals("JRR", libroTrovato.getNomeAutore());
        assertEquals("Tolkien", libroTrovato.getCognomeAutore());
        
     
    }

    /**
     * Test of ricercaLibroCodice method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testRicercaLibroCodice() throws GestioneEccezioni {
         //creo ed inserisco 3 libri
        Libro lib1 = new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP4532", 1 );
        Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        Libro lib3 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        GestioneLibro.getInstance().inserisci(lib1);
        GestioneLibro.getInstance().inserisci(lib2);
        GestioneLibro.getInstance().inserisci(lib3);
        
        //si effettua la ricerca per titolo per trovare un libro
        Libro libroTrovato = GestioneLibro.getInstance().ricercaLibroCodice("HP4532");
        
        //controllo che ritorni qualcosa
        assertNotNull(libroTrovato);
        
        //controllo che ritorni effettivamente il libro che stavo cercando
        assertEquals("HP4532", libroTrovato.getCodice());
        
    }
    
    /**
     * Primo test che segnala il fallimento di una ricerca per titolo
     */
    @Test
    public void testRicercaTitoloFallitaFirst(){
        //collezione vuota, ricerca fallita
        assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().ricercaLibroTitolo("Il Signore degli Anelli"));
    }
    
    /**
     * Secondo test che segnala il fallimento di una ricerca per titolo
     * @throws GestioneEccezioni
     */
    @Test
    public void testRicercaTitoloFallitaSecond() throws GestioneEccezioni{
        //creo ed inserisco tre libri
        Libro lib1 = new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP4532", 1 );
        Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        Libro lib3 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        GestioneLibro.getInstance().inserisci(lib1);
        GestioneLibro.getInstance().inserisci(lib2);
        GestioneLibro.getInstance().inserisci(lib3);
        
        //cerco un titolo non presente nella collezione
        assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().ricercaLibroTitolo("Il Piccolo Principe"));
        
    }
    
     /**
     * Primo Test che segnala il fallimento di una ricerca per autore
     * 
     */
    @Test
    public void testRicercaAutoreFallitaFirst(){
        //collezione vuota, ricerca fallita
        assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().ricercaLibroAutore("George", "Orwell"));
    }
    
    /**
     * Secondo Test che segnala il fallimento di una ricerca per autore
     * @throws GestioneEccezioni
     */
    @Test
    public void testRicercaAutoreFallitaSecond() throws GestioneEccezioni{
         //creo ed inserisco tre libri
        Libro lib1 = new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP4532", 1 );
        Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        Libro lib3 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        GestioneLibro.getInstance().inserisci(lib1);
        GestioneLibro.getInstance().inserisci(lib2);
        GestioneLibro.getInstance().inserisci(lib3);
        
        //cerco un autore non presente nella collezione
        assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().ricercaLibroAutore("Dante", "Alighieri"));
        
    }

     /**
     * Primo Test che segnala il fallimento di una ricerca per codice
     */
    @Test
    public void testRicercaCodiceFallitaFirst(){
        //collezione vuota, ricerca fallita
        assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().ricercaLibroCodice("HP4532"));
    }
    
    /**
     * Secondo Test che segnala il fallimento di una ricerca per autore
     * @throws GestioneEccezioni
     */
    @Test
    public void testRicercaCodiceFallitaSecond() throws GestioneEccezioni{
         //creo ed inserisco tre libri
        Libro lib1 = new Libro("Harry Potter e il prigioniero di Azkaban", "JK", "Rowling", 1999, "HP4532", 1 );
        Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        Libro lib3 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        GestioneLibro.getInstance().inserisci(lib1);
        GestioneLibro.getInstance().inserisci(lib2);
        GestioneLibro.getInstance().inserisci(lib3);
        
        //cerco un codice non presente nella collezione
        assertThrows(GestioneEccezioni.class, () -> GestioneLibro.getInstance().ricercaLibroCodice("PR5677"));
        
    }
    
    /**
     * Test of modifica method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    
    @Test
    public void testModifica() throws GestioneEccezioni{
        //creo un libro e lo inserisco nella collezione
        Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
        GestioneLibro.getInstance().inserisci(lib1);
        
        //creo un altro libro con campi modificati ma stesso codice
        Libro libroModificato = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 4 );
        
        //chiamata al metodo modifica
        GestioneLibro.getInstance().modifica(libroModificato);
        
        //si recupera l'unico libro della collezione mediante ricerca per titolo e si verifica se si sono modificati i campi
        Libro libroRecuperato = GestioneLibro.getInstance().ricercaLibroTitolo("Dune");
        assertEquals(4, libroRecuperato.getCopieDisponibili());
        
    
    
    }

    /**
     * Test of salvaLibri and caricaLibri methods, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testCaricaSalvaLibri() throws GestioneEccezioni {
       //creo ed inserisco due libri
       Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
       Libro lib2 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
       
       GestioneLibro.getInstance().inserisci(lib1);
       GestioneLibro.getInstance().inserisci(lib2);
       
       //salvo sul file di test
       GestioneLibro.getInstance().salvaLibri("libri_TEST.dat");
       
       //si svuota la memoria
       GestioneLibro.getInstance().reset();
       
       //carico i libri salvati
       Set<Libro> libriCaricati = GestioneLibro.getInstance().caricaLibri("libri_TEST.dat");
       
       assertNotNull(libriCaricati);
       assertEquals(2, libriCaricati.size());
       
    }

    /**
     * Test of getSetLibro method, of class GestioneLibro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testGetSetLibro() throws GestioneEccezioni {
         //creo ed inserisco due libri
       Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
       Libro lib2 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
       
       GestioneLibro.getInstance().inserisci(lib1);
       GestioneLibro.getInstance().inserisci(lib2);
       
       Set<Libro> setLibri = new TreeSet<>();
       setLibri.add(lib1);
       setLibri.add(lib2);
       
       assertEquals(setLibri, GestioneLibro.getInstance().getSetLibro());
       
       
       
    }

    /**
     * Test of setLibroSet method, of class GestioneLibro.
     */
    @Test
    public void testSetLibroSet() {
        
       //creo ed inserisco due libri
       Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
       Libro lib2 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
       
       Set<Libro> setLibri = new TreeSet<>();
       setLibri.add(lib1);
       setLibri.add(lib2);
       
       GestioneLibro.getInstance().setLibroSet(setLibri);
       assertEquals(setLibri, GestioneLibro.getInstance().getSetLibro());
       
       
       
        
        
        
    }
    
}
