

package it.unisa.biblioteca.model;
import java.io.*;
import java.util.*;

/**
 * @file Libro.java
 * @author Gruppo 9
 * @brief La classe Libro permette di rappresentare un libro.
 * 
 * Ogni libro ha i seguenti dati: titolo, nome e cognome Autore, anno di publicazione, codice idetificativo, e numero di copie disponibili.
 * Nella classe sono presenti i metodi per la corretta gestione dei dati e per l'implementazione in una collezione ordinata.
 * 
 * @date 03 Dicembre, 2025
 */

public class Libro implements Comparable<Libro>, Serializable {

    /**
     * @brief Variabile di istanza privata che rappresenta il titolo del libro
     */
    
    private String titolo;
    
     /**
     * @brief Variabile di istanza privata che rappresenta il nome dell'autore del libro
     */
    private String nomeAutore;
    
     /**
     * @brief Variabile di istanza privata che rappresenta il cognome dell'autore del libro
     */
    
    private String cognomeAutore;
    
     /**
     * @brief Variabile di istanza privata che rappresenta l'anno di pubblicazione del libro
     */
    private int annoPubblicazione;
    
     /**
     * @brief Variabile di istanza privata che rappresenta il codice identificativo del libro
     */
    private String codice;
    
     /**
     * @brief Variabile di istanza privata che rappresenta il numero di copie disponibili del libro
     */
    private int copieDisponibili;
    
     /**
     * @brief Variabile di istanza privata che rappresenta il numero di prestiti attualmente attivi di un libro
     */
    private int prestitiAttivi;

    /**
     * @brief Costruttore che inizializza i dati di un libro.
     * 
     * Libro() è il costruttore di questa classe, assegna i valori passati come parametri agli attributi.
     * 
     * @param titolo titolo del libro.
     * @param nomeAutore nome dell'autore del libro.
     * @param cognomeAutore cognome dell'autore del libro.
     * @param annoPubblicazione l'anno di pubblicazione del libro.
     * @param codice il codice identificativo del libro.
     * @param copieDisponibili numero di copie disponibili.
     */

    public Libro(String titolo, String nomeAutore, String cognomeAutore, int annoPubblicazione, String codice, int copieDisponibili) {
        this.titolo = titolo;
        this.nomeAutore = nomeAutore;
        this.cognomeAutore = cognomeAutore;
        this.annoPubblicazione = annoPubblicazione;
        this.codice = codice;
        this.copieDisponibili = copieDisponibili;
        this.prestitiAttivi = 0;
    }

    /**
     * @brief Metodo che permette di ottenre l'attributo titolo.
     * @return titolo del libro.
     */

    public String getTitolo() {
        return titolo;
    }

    /**
     * @brief Metodo che permette di modificare l'attributo titolo.
     * @param titolo titolo del libro.
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @brief Metodo che permette di ottenere l'attributo nomeAutore.
     * @return nome dell'autore del libro.
     */
    public String getNomeAutore(){
        return nomeAutore;
    }
    
    /**
     * @brief Metodo che permette di modificare l'attributo nomeAutore.
     * @param nomeAutore nome dell'autore del libro.
     */
    public void setNomeAutore(String nomeAutore){
        this.nomeAutore = nomeAutore;
    
    }
    
    /**
     * @brief Metodo che permette di ottenere l'attributo cognomeAutore.
     * @return cognome dell'autore del libro.
     */
    public String getCognomeAutore(){
        return cognomeAutore;
    }
    /**
     * @brief Metodo che permette di modificare l'attributo cognomeAutore.
     * @param cognomeAutore cognome dell'autore del libro.
     */
    public void setCognomeAutore(String cognomeAutore){
        this.cognomeAutore = cognomeAutore;
    
    }
    
    /**
     * @brief Metodo che permette di ottenere l'attributo annoPubblicazione.
     * @return l'anno di pubblicazione del libro.
     */
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    /**
     * @brief Metodo che permette di modificare l'attributo annoPubblicazione
     * @param annoPubblicazione l'anno di pubblicazione del libro.
     */
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    /**
     * @brief Metodo che permette di ottenere l'attributo codice.
     * @return il codice identificativo del libro.
     */
    public String getCodice() {
        return codice;
    }
    
    /**
     * @brief Metodo che permette di modificare l'attributo codice.
     * 
     * Il metodo consente la modifica dell'attributo solo se il codice rispetta il formato standard,
     * in caso contrario lancia un eccezione.
     * 
     * @pre il codice deve essere coerente con il formato standard CCDDDD.
     * @post l'attributo codice viene modificato.
     * 
     * @param codice  codice identificativo del libro.
     */
    public void setCodice(String codice){
        this.codice = codice;
    }
    
    /**
     * @brief Metodo che permette di ottenere l'attributo copieDisponibili.
     * @return numero di copie disponibili.
     */
    public int getCopieDisponibili() {
        return copieDisponibili;
    }
    
    /**
     * @brief Metod che permette di  modificare l'attributo copieDisponibili.
     * @param copieDisponibili numero di copie disponibili.
     */
    public void setCopieDisponibili(int copieDisponibili) {
        this.copieDisponibili = copieDisponibili;
    }
    
    /**
     * @brief Metodo che permette di ottenere l'attributo prestiti attivi
     * @return numero di prestiti attivi
     */

    public int getPrestitiAttivi(){
        return prestitiAttivi;
    }
    
    /**
     * @brief Metodo che permette di incrementare il numero di prestiti attivi
     * 
     */
    
    public void incrementaPrestiti(){
        this.prestitiAttivi++;
    }
    
    /**
     * @brief Metodo che permette di decrementare il numero di prestiti attivi
     * @throws GestioneEccezioni 
     */
    
    public void decrementaPrestiti() throws GestioneEccezioni{
        if (this.prestitiAttivi > 0){
            this.prestitiAttivi--;
        } else {
            throw new GestioneEccezioni("Non sono presenti prestiti attivi. Impossibile decrementare il numero di prestiti.");
        }
    }
    
    /**
     * @brief Metodo che permette di gestire l'ordinamento nella collezione sul titolo in ordine alfabetico.
     * @param l libro da comparare.
     * @return 0 se i titoli sono uguali, minore di 0 se il titolo corrente viene prima nel ordine alfabetico rispetto a quello passato come parametro, 
     *         maggiore di 0 se viene dopo rispetto a quello passato come parametro.
     */
    
    @Override
    public int compareTo(Libro l){
        return this.titolo.compareToIgnoreCase(l.titolo);
    
    }

    /**
     * @brief Metodo che permette di controllare se ci sono copie disponibili
     * @return true se ci sono copie disponibili, false se non ci sono copie disponibili.
     */
    public boolean hasCopieDisponibili() {
        return copieDisponibili > 0;
    }

    /**
     * @brief Metodo che diminuisce di 1 il numero di copie disponibili.
     * @throws GestioneEccezioni
     */
    public void decrementaCopie() throws GestioneEccezioni {
        if (copieDisponibili > 0)
            copieDisponibili--;
        else {
            throw new GestioneEccezioni("Non sono presenti copie disponibili/Hai inserito un numero di copie negativo. Impossibile decrementare il numero di copie.");
        }
    }

    /**
     * @brief Metodo che incrementa di 1 il numero di copie disponibili.
     */
    public void incrementaCopie() {
        copieDisponibili++;
    }
    
    /**
     * @brief Metodo che stabilisce una relazione di uguaglianza tra due libri.
     *      Il metodo in particolare stabilisce che due libri sono uguali se hanno lo stesso codice identificativo.
     * 
     * @param o un oggetto di tipo Libro da confrontare.
     * @return il valore booleano 'true' se gli oggetti hanno gli stessi codici identificativi, il valore booleano 'false' se hanno codici identificativi diversi.
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        Libro l = (Libro) o;
        return Objects.equals(this.codice, l.codice);
    }
    
    /**
     * @brief Metodo che permette di stampare su terminale le informazioni relative ai vari libri (utile nel caricamento da file binario e nel salvataggio su file binario)
     * @return una stringa che rappresenta le informazioni relative ai vari libri (tiolo, noe e cognome autore, anno di pubblicazione, codice e copie disponibili )
     */
    
    @Override
    public String toString(){
        return "Titolo: " + titolo + "\nNome Autore: " + nomeAutore + "\nCognome Autore: " + cognomeAutore + "\nAnno di pubblicazione: " + annoPubblicazione + "\nCodice: " + codice + "\nCopie Disponibili: " + copieDisponibili +"\n\n";
    
    }

}

