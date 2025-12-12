/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.biblioteca.testmodel;


import it.unisa.biblioteca.model.GestioneEccezioni;
import it.unisa.biblioteca.model.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 177748
 */
public class LibroTest {
    
    
    @Test
    public void testConstructor(){
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        assertEquals("1984", lib1.getTitolo());
        assertEquals("George", lib1.getNomeAutore());
        assertEquals("Orwell", lib1.getCognomeAutore());
        assertEquals(1949, lib1.getAnnoPubblicazione());
        assertEquals("GO4532", lib1.getCodice());
        assertEquals(4, lib1.getCopieDisponibili());
        assertEquals(0, lib1.getPrestitiAttivi());
        
    }
    
    /**
     * Test of getTitolo method, of class Libro.
     */
    
    @Test
    public void testGetTitolo(){
        Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
        
        String titoloAtteso = "Dune";
        String titoloEffettivo = lib1.getTitolo();
        
        assertEquals(titoloAtteso, titoloEffettivo);
        
    }
    
    
    /**
     * Test of setTitolo method, of class Libro.
     */
    @Test
    public void testSetTitolo() {
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        lib1.setTitolo("Dune");
        assertEquals("Dune", lib1.getTitolo());
      
    }
    
    /**
     * Test of getNomeAutore method, of class Libro.
     */
     @Test
    public void testGetNomeAutore() {
       Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
        
       String nomeAtteso = "Frank";
       String nomeEffettivo = lib1.getNomeAutore();
       
       assertEquals(nomeAtteso, nomeEffettivo);
      
    }


    /**
     * Test of setNomeAutore method, of class Libro.
     */
    @Test
    public void testSetNomeAutore() {
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        lib1.setNomeAutore("Frank");
        assertEquals("Frank", lib1.getNomeAutore());
  
    }
    
    
    /**
     * Test of getCognomeAutore method, of class Libro.
     */
     @Test
    public void testGetCognomeAutore() {
        
         Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
        
       String cognomeAtteso = "Herbert";
       String cognomeEffettivo = lib1.getCognomeAutore();
       
       assertEquals(cognomeAtteso, cognomeEffettivo);
      
    }
    
    /**
     * Test of setCognomeAutore method, of class Libro.
     */
    @Test
    public void testSetCognomeAutore() {
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        lib1.setCognomeAutore("Herbert");
        assertEquals("Herbert", lib1.getCognomeAutore());
      
    }
    
    /**
     * Test of getAnnoPubblicazione method, of class Libro.
     */
    @Test
    public void testGetAnnoPubblicazione(){
        Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
        
        int annoAtteso = 1965;
        int annoEffettivo = lib1.getAnnoPubblicazione();
        
        assertEquals(annoAtteso, annoEffettivo);
    
    }


    /**
     * Test of setAnnoPubblicazione method, of class Libro.
     */
    @Test
    public void testSetAnnoPubblicazione() {
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        lib1.setAnnoPubblicazione(1965);
        assertEquals(1965, lib1.getAnnoPubblicazione());
      
    }
    
    /**
     * Test of getCodice method, of class Libro.
     */
    
    @Test
    public void testGetCodice(){
       Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
       
       String codiceAtteso = "DU2897";
       String codiceEffettivo = lib1.getCodice();
       
       assertEquals(codiceAtteso, codiceEffettivo);
    
    
    }

    /**
     * Test of setCodice method, of class Libro.
     */
    @Test
    public void testSetCodice() {
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        lib1.setCodice("DU2897");
        assertEquals("DU2897", lib1.getCodice());

    }
    
    /**
     * Test of getCopieDisponibili, of class Libro
     */
    @Test
    public void testGetCopieDisponibili(){
       Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
       
       int copieAtteso = 2;
       int copieEffettivo = lib1.getCopieDisponibili();
       
       assertEquals(copieAtteso, copieEffettivo);
    
    }

   
    /**
     * Test of setCopieDisponibili method, of class Libro.
     */
    
    @Test
    public void testSetCopieDisponibili() {
        
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        lib1.setCopieDisponibili(2);
        assertEquals(2, lib1.getCopieDisponibili());
       
    }
    
    /**
     * Test of getPrestitiAttivi method, of class Libro.
     */
    @Test
    public void testGetPrestitiAttivi(){
        Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
        
        int prestitiAtteso = 0;
        int prestitiEffettivo = lib1.getPrestitiAttivi();
        
        assertEquals(prestitiAtteso, prestitiEffettivo);
    
    
    
    }
    

    /**
     * Test of incrementaPrestiti method, of class Libro.
     */
    @Test
    public void testIncrementaPrestiti() {
        
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        lib1.incrementaPrestiti();
        
        assertEquals(1, lib1.getPrestitiAttivi());
        
    }

    /**
     * First Test of decrementaPrestiti method, of class Libro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testDecrementaPrestitiFirst() throws GestioneEccezioni {
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        lib1.incrementaPrestiti();
        
        try{
            lib1.decrementaPrestiti();
        
        }catch(GestioneEccezioni ex){
        
        }
        
        assertEquals(0, lib1.getPrestitiAttivi());
        
      
    }
    
    /**
     * Second Test of decrementaPrestiti method of class Libro
     * @throws GestioneEccezioni 
     */
    @Test
    public void testDecrementaPrestitiSecond() throws GestioneEccezioni {
        // Act & Assert: assertThrows
        // Sintassi: assertThrows(ClasseEccezioneAttesa.class, () -> { codice che lancia });
        
        Libro lib1 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 4 );
        
        GestioneEccezioni exc = assertThrows(GestioneEccezioni.class, () -> lib1.decrementaPrestiti());
        
        
    }

    /**
     * Test of compareTo method, of class Libro.
     */
    @Test
    public void testCompareTo() {
        
         Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
         Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
         
         
         //caso 1: Dune viene prima di Il Signore degli Anelli
         assertTrue(lib1.compareTo(lib2) < 0);
         
         //caso 2: Il Signore degli Anelli viene dopo Dune
         assertTrue(lib2.compareTo(lib1) > 0);
         
         //caso 3: stesso titolo, risultato atteso 0
         assertEquals(0, lib1.compareTo(lib1));
         
    }

    /**
     * Test of hasCopieDisponibili method, of class Libro.
     */
    @Test
    public void testHasCopieDisponibili() {
        
        //caso 1: copie disponibili è numero maggiore di 0
        Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
        
        assertTrue(lib1.hasCopieDisponibili());
        
        //caso 2: copie disponibili è un numero minore di 0
         Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", -4 );
         assertFalse(lib2.hasCopieDisponibili());
         
         //caso 3: copie disponibili è 0
         Libro lib3 = new Libro("1984", "George", "Orwell", 1949, "GO4532", 0 );
         assertFalse(lib3.hasCopieDisponibili());
         
    }

    /**
     * First Test of decrementaCopie method, of class Libro.
     * @throws GestioneEccezioni
     */
    @Test
    public void testDecrementaCopieFirst() throws GestioneEccezioni {
        Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
        
        try{
            lib2.decrementaCopie();
        
        }catch(GestioneEccezioni ex){
        
        }
        
        assertEquals(2, lib2.getCopieDisponibili());
    }
    
    /**
     * Second Test of decrementaCopie .method of class Libro
     * @throws GestioneEccezioni
     */
    
    @Test
    public void testDecrementaCopieSecond() throws GestioneEccezioni{
        
       Libro lib1 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", -4 );
       
       GestioneEccezioni exc = assertThrows(GestioneEccezioni.class, () -> lib1.decrementaCopie());
    
    
    
    }

    /**
     * Test of incrementaCopie method, of class Libro.
     */
    @Test
    public void testIncrementaCopie() {
        
        Libro lib1 = new Libro("Dune", "Frank", "Herbert", 1965, "DU2897", 2 );
        lib1.incrementaCopie();
        
        assertEquals(3, lib1.getCopieDisponibili());
        
    }

    /**
     * Test of equals method, of class Libro.
     */
    @Test
    public void testEquals() {
        
         //caso1: stesso codice e stesso titolo, i due libri devono risultare uguali
         Libro lib1 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 6 );
         Libro lib2 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 3 );
         assertTrue(lib1.equals(lib2));
         
         
         //caso2: stesso titolo, ma codice diverso, i due libri devono risultare diversi
         Libro lib3 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 6 );
         Libro lib4 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO9076", 3 );
         assertFalse(lib3.equals(lib4));
         
         //caso 3: riflessività : un oggetto deve essere uguale a se stesso
         assertTrue(lib1.equals(lib1));
         
         //caso 4: controllo con null
         assertFalse(lib1.equals((Libro)null));
         
         //cas0 5: controllo con tipo diverso
         assertFalse(lib1.equals("TO7865"));
         
    }

    /**
     * Test of toString method, of class Libro.
     */
    @Test
    public void testToString() {
        Libro lib1 = new Libro("Il Signore degli Anelli", "JRR", "Tolkien" , 1955, "TO7865", 6 );
        
        String stringaAttesa = "Titolo: " + lib1.getTitolo() + "\nNome Autore: " + lib1.getNomeAutore() + "\nCognome Autore: " + 
                          lib1.getCognomeAutore() + "\nAnno di pubblicazione: " + lib1.getAnnoPubblicazione() + "\nCopie Disponibili: " + lib1.getCopieDisponibili() + "\n\n";
        
        assertEquals(stringaAttesa, lib1.toString());
        
        
    }
    
}
