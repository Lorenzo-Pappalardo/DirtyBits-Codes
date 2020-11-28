/**
 * Data una collezione di Album. Ogni album ha: un titolo, l’autore, l’anno di pubblicazione, il prezzo e la lista di canzoni. Eseguiamo le seguenti operazioni:
 * STEP 1 : Ordinare gli album in ordine alfabetico e contare quante canzoni sono presenti in ogni album.
 * STEP 2 : Trovare il numero totale delle canzoni eliminando i doppioni.
 * STEP 3: Trovare il numero totale di canzoni nell'album "The First Album" dell'anno 2001.
 * STEP 4: Supponiamo che un cliente acquisti tutti gli album pubblicati prima dell’anno 1990. Quanto spenderebbe?
 * STEP 5: Visualizzare i primi 3 titoli di canzoni diverse che iniziano con la lettera F e fanno parte di album di anni precedenti al 2000.
 * Scrivere il codice in stile imperativo e funzionale.
 * @author Gabriella Verga
 *
 */

package Functional_Programming_Exercises;

import java.util.*;
import java.util.function.Predicate;

public class Album implements Comparable<Album> {
   public static void main(String[] args) {
      List<Album> albums = new ArrayList<>();
      albums.add(new Album("Pink Floyd", "The Division Bell", 1994, 50, "Cluster One", "What Do You Want from Me",
            "Poles Apart", "Marooned", "A Great Day for Freedom", "Wearing the Inside Out", "Take It Back",
            "Coming Back to Life", "Keep Talking", "Lost for Words", "High Hopes"));

      albums.add(new Album("Queen", "A Kind of Magic", 1986, 45, "One Vision", "A Kind of Magic", "One Year of Love",
            "Pain Is So Close to Pleasure", "Friends Will Be Friends", "Who Wants to Live Forever", "Gimme the Prize",
            "Don't Lose Your Head", "Princes of the Universe", "A Kind of 'A Kind of Magic'",
            "Friends Will Be Friends Will Be Friends...", "Forever (Piano Version)"));

      albums.add(new Album("Madonna", "The First Album", 1983, 100, "Lucky Star", "Borderline", "Burning Up",
            "I Know It", "Holiday", "Think of Me", "Physical Attraction", "Everybody"));

      albums.add(
            new Album("Madonna", "The First Album", 2001, 82, "Lucky Star", "Borderline", "Burning Up", "I Know It",
                  "Holiday", "Think of Me", "Physical Attraction", "Everybody", "Burning Up", "Lucky Star - New Mix"));

      step1(albums);
      step3(albums);
      step4(albums);
   }

   private static void step1(List<Album> albums) {
      albums.stream().sorted(Comparator.comparing(Album::getTitle))
            .map(album -> album.title + ": " + album.getNumberSongs()).forEach(songs -> System.out.println(songs));
      Integer count = albums.stream().sorted(Comparator.comparing(Album::getTitle)).map(album -> album.getNumberSongs())
            .reduce(0, (a, b) -> a + b);
      System.out.println("Total songs: " + count);
   }

   private static void step3(List<Album> albums) {
      Predicate<Album> onlyMadeIn2001 = album -> (album.getTitle() == "The First Album") && (album.getYear() == 2001);
      Integer count = albums.stream().filter(onlyMadeIn2001).map(album -> album.getNumberSongs()).reduce(0,
            (a, b) -> a + b);
      System.out.println("The First Album, made in 2001, contains: " + count + " songs");
   }

   private static void step4(List<Album> albums) {
      Predicate<Album> before1990 = album -> album.getYear() < 1990;
      Double totalPrice = albums.stream().filter(before1990).mapToDouble(album -> album.getPrice()).sum();
      System.out.println("Total price: " + totalPrice);
   }

   /** final implica che le variabile a runtime non possono essere modificate **/
   private final String author;
   private final List<String> songs;
   private final int year;
   private final String title;
   private final double price;

   public Album(String author, String title, int year, double price, String... songs) {
      this.author = author;
      this.year = year;
      this.title = title;
      this.price = price;
      this.songs = Arrays.asList(songs);/** converto una array in List **/
   }

   public String getAuthor() {
      return author;
   }

   public List<String> getSongs() {
      return songs;
   }

   public int getYear() {
      return year;
   }

   public String getTitle() {
      return title;
   }

   public double getPrice() {
      return price;
   }

   public int getNumberSongs() {
      return songs.size();
   }

   @Override
   public int compareTo(Album o) {
      return this.title.compareTo(o.getTitle());
   }
}