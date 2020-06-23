package Functional_Programming_Exercises;

import java.util.*;

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
      this.songs = Arrays.asList(songs); /** converto una array in List **/
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