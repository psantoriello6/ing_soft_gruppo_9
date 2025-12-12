/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.model;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author pasqu
 */
public class PrestitoTest {
    private Prestito prestito;
    private Utente utente;
    private Libro libro;
    private LocalDate dataOggi;
    
    public PrestitoTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }
    
    @BeforeEach
    public void setUp() {
        dataOggi = LocalDate.now();
        utente = new Utente("Francesco", "Totti", 101010, "francesco.totti@università.it");
        libro = new Libro("1984", "George", "Orwell", 1949, "GO9213", 2);
        prestito = new Prestito(utente, libro, dataOggi);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of constructor method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test Costruttore: Verifica inizializzazione corretta")
    void testCostruttore() {
        assertEquals(utente, prestito.getUtente(), "L'utente non è stato inizializzato correttamente dal costruttore");
        assertEquals(libro, prestito.getLibro(), "Il libro non è stato inizializzato correttamente dal costruttore");
        assertEquals(dataOggi, prestito.getDataRestituzione(), "La data non è stata inizializzata correttamente dal costruttore");
    }

    /**
     * Test of setDataRestituzione method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test setDataRestituzione(): Verifica modifica valore")
    void testSetDataRestituzione() {
        LocalDate nuovaData = dataOggi.plusDays(30);
        
        prestito.setDataRestituzione(nuovaData);

        assertEquals(nuovaData, prestito.getDataRestituzione(), "Il setter deve aggiornare la data");
        assertNotEquals(dataOggi, prestito.getDataRestituzione(), "La data non dovrebbe più essere quella iniziale");
    }

    /**
     * Test of getDataRestituzione method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test getDataRestituzione(): Verifica valore iniziale")
    void testGetDataRestituzione() {
        assertEquals(dataOggi, prestito.getDataRestituzione(), "Il getter deve restituire la data iniziale");
    }
    /**
     * Test of setUtente method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test setUtente(): Verifica modifica valore")
    void testSetUtente() {
        Utente nuovoUtente = new Utente("Andrea", "Pirlo", 212121, "andrea.pirlo@università.it");
        
        prestito.setUtente(nuovoUtente);

        assertEquals(nuovoUtente, prestito.getUtente(), "Il setter deve aggiornare l'utente");
        assertNotEquals(utente, prestito.getUtente(), "L'utente non dovrebbe più essere quello iniziale");
    }

    /**
     * Test of getUtente method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test getUtente(): Verifica valore iniziale")
    void testGetUtente() {
        assertNotNull(prestito.getUtente(), "L'utente non dovrebbe essere null");
        assertEquals(utente, prestito.getUtente(), "Il getter deve restituire l'utente iniziale");
    }

    /**
     * Test of setLibro method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test setLibro(): Verifica modifica valore")
    void testSetLibro() {
        Libro nuovoLibro = new Libro("Database Systems", "Paolo", "Atzeni", 2018, "DB2018", 3);
        
        prestito.setLibro(nuovoLibro);

        assertEquals(nuovoLibro, prestito.getLibro(), "Il setter deve aggiornare il libro");
        assertNotEquals(libro, prestito.getLibro(), "Il libro non dovrebbe più essere quello iniziale");
    }

    /**
     * Test of getLibro method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test getLibro(): Verifica valore iniziale")
    void testGetLibro() {
        assertNotNull(prestito.getLibro(), "Il libro non dovrebbe essere null");
        assertEquals(libro, prestito.getLibro(), "Il getter deve restituire il libro iniziale");
    }

    /**
     * Test of ritardoPrestito method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test ritardoPrestito: Verifica calcolo ritardo")
    void testRitardoPrestito() {
        LocalDate dataPassata = LocalDate.now().minusDays(1);     
        assertTrue(prestito.ritardoPrestito(dataPassata), "Dovrebbe essere in ritardo con data passata");
        
        LocalDate dataFutura = LocalDate.now().plusDays(1);
        assertFalse(prestito.ritardoPrestito(dataFutura), "Non dovrebbe essere in ritardo con data futura");
    }

    /**
     * Test of compareTo method, of class Prestito.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test compareTo: Verifica ordinamento")
    void testCompareTo() {
        LocalDate prima = LocalDate.of(2025, 12, 12);
        LocalDate dopo = LocalDate.of(2025, 12, 19);
        
        Prestito p1 = new Prestito(utente, libro, prima);
        Prestito p2 = new Prestito(utente, libro, dopo);

        assertTrue(p1.compareTo(p2) < 0);
    }
    
}
