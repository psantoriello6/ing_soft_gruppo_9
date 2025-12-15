/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.testmodel;

import it.unisa.biblioteca.model.Utente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtenteTest {

    public UtenteTest() {
    }

    // TEST COSTRUTTORE E GETTER/SETTER:
    //nel test del costruttore implicitamente sto testtando ache tutti i getter
    @Test
    public void testCostruttore() {
        System.out.println("Test: Creazione Utente");
        //creo un utente (passandogli TUTTI i campi)
        Utente u = new Utente("Mario", "Rossi", 1001, "mario.rossi@università.it");
        
        //controllo che sia stato creato un utente con i campi specificati:
        assertEquals("Mario", u.getNome());
        assertEquals("Rossi", u.getCognome());
        assertEquals(1001, u.getMatricola());
        assertEquals("mario.rossi@università.it", u.getEmail());
    }

    @Test
    public void testSetter() {
        System.out.println("Test: Modifica dati (Setter)");
        //creo un utente (passandogli TUTTI i campi)
        Utente u = new Utente("Mario", "Rossi", 1001, "mario.rossi@università.it");
        
        //uso i set per cambiare i campi all'utente
        u.setNome("Luigi");
        u.setCognome("Verdi");
        u.setEmail("nuova@test.it");
        // Nota: tecnicamente non può mai avvenire il set della matricola (in quanto l'abbiamo considerato un campo univoco non modificabile)
        u.setMatricola(9999);
        
        //controllo che i campi sono stati cambiati correttamente
        assertEquals("Luigi", u.getNome());
        assertEquals("Verdi", u.getCognome());
        assertEquals("nuova@test.it", u.getEmail());
        assertEquals(9999, u.getMatricola());
    }

    
    // TEST UGUAGLIANZA (equals e hashCode):

    @Test
    public void testEquals() {
        System.out.println("Test: Equals (Basato solo su Matricola)");
        //creo una 3 utenti (uno con la stessa matricola ma nome diverso, un altro con stesso nome ma matricole diverse)
        Utente u1 = new Utente("Mario", "Rossi", 1001, "a@b.it");
        Utente u2 = new Utente("Luigi", "Verdi", 1001, "c@d.it"); // Nome diverso, STESSA matricola
        Utente u3 = new Utente("Mario", "Rossi", 1002, "a@b.it"); // Stesso nome, matricola DIVERSA

        // Verifico che la logica di ugualinza (per matricola) sia rispettata, mi aspetto TRUE al primo confronto e FALSE al secondo
        assertTrue(u1.equals(u2), "Due utenti con la stessa matricola devono essere uguali");
        assertFalse(u1.equals(u3), "Due utenti con matricola diversa devono essere diversi");
    }
    
    @Test
    public void testHashCode() {
        System.out.println("Test: HashCode");
        //creo 2 utenti con la stessa matricola
        Utente u1 = new Utente("Mario", "Rossi", 1001, "a@b.it");
        Utente u2 = new Utente("Luigi", "Verdi", 1001, "c@d.it");
        
        // controllo che 2 utenti con la stessa matricola abbiano lo stesso hashcode
        assertEquals(u1.hashCode(), u2.hashCode(), "Utenti uguali devono avere stesso hashcode");
    }

    
    // TEST ORDINAMENTO (compareTo):

    @Test
    public void testOrdinamento1() {
        System.out.println("Test: Ordinamento per Cognome");
        //creo 2 utenti con cognome diversi
        Utente u1 = new Utente("Mario", "Bianchi", 1, "a");
        Utente u2 = new Utente("Mario", "Verdi", 2, "b");

        // controllo che effettiavamente l'utente Bianchi venga prima di Verdi:
        assertTrue(u1.compareTo(u2) < 0);
        assertTrue(u2.compareTo(u1) > 0);
    }

    @Test
    public void testOrdinamento2() {
        System.out.println("Test: Ordinamento per Nome (a parità di cognome)");
        //creo 2 utenti con lo stesso cognome ma nomi diversi
        Utente u1 = new Utente("Anna", "Rossi", 1, "a");
        Utente u2 = new Utente("Mario", "Rossi", 2, "b");

        // controllo che l'utente Anna venga prima del utente Mario:
        assertTrue(u1.compareTo(u2) < 0);
    }

    @Test
    public void testOrdinamento3() {
        System.out.println("Test: Ordinamento per Matricola (Omonimi)");
        //Creo 2 utenti con stesso Nome e Cognome ma matricole diverse
        Utente u1 = new Utente("Mario", "Rossi", 100, "a");
        Utente u2 = new Utente("Mario", "Rossi", 200, "b");

        // Controllo che l'utente con la matricola 100 venga prima di quello con la matricola 200
        assertTrue(u1.compareTo(u2) < 0);
    }

    @Test
    public void testOrdinamento4() {
        System.out.println("Test: Ordinamento Maiuscole/Minuscole");
        // creo 2 utenti con setsso nome e cognome (uno scritto in maiuscolo l'atro in minuscolo)
        Utente u1 = new Utente("mario", "rossi", 1, "a"); // minuscolo
        Utente u2 = new Utente("MARIO", "ROSSI", 2, "b"); // MAIUSCOLO

        //controlo che funzioni il caseInsensitive (controllo avvenga l'ordinamento per matricola)
        assertTrue(u1.compareTo(u2) < 0);
        
        // provo a invertite le matricole (l'utente maisucolo sta volta deve venire prima di quello minuscolo), inverto il precedente controllo
        Utente u3 = new Utente("mario", "rossi", 500, "a");
        Utente u4 = new Utente("MARIO", "ROSSI", 100, "b");
        // controllo che l'ordinamento sia quello atteso e che non sia influenzato dal maisucolo-minuscolo
        assertTrue(u4.compareTo(u3) < 0);
    }
}
