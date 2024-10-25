package com.hugo.lab14;

import java.util.ArrayList;

public class Movie implements Comparable<Movie> {
    private int movieID;
    private String movieName;
    private int year;
    private String country;
    private ArrayList<String> genres;

    private Movie(int movieID){
        this.movieID = movieID;
    }

    // Overloaded constructor
    public Movie(int movieID, String movieName, int year, String country, ArrayList<String> genres ) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.year = year;
        this.country = country;
        this.genres = genres;
    }

    // Don't change this
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String g: genres) {
            sb.append(g + " ");
        }
        return movieID + " " + movieName + " " + year +
                " " + country + " " + sb.toString().strip();
    }

    public int getMovieID() { return movieID; }
    public String getMovieName() { return movieName; }
    public int getYear() { return year; }
    public String getCountry() { return country; }
    public ArrayList<String> getGenres() { return genres; }


    @Override
    public boolean equals(Object other) {

        boolean result;
        if((other == null) || (getClass() != other.getClass())){
            result = false;
        } // end if
        else{
            Movie otherMovie = (Movie)other;
            result = this.movieID == otherMovie.movieID &&
                    this.year == otherMovie.year &&
                    this.country.equals(otherMovie.country);
        } // end else

        return result;

    }

    /**
     * Compare on movieID
     * STEP 2 - Implement the compareTo interface
     * Compares this object with the specified object for order.
     * Returns a negative integer, zero, or a positive integer
     * as this object is less than,
     * equal to, or greater than the specified object.
     * @param rhs the object to be compared.
     * @return -1 if movieID < rhs.movieID, 1 if movieID > rhs.movieID, else 0 as they are equal
     */
    public int compareTo(Movie rhs){
        if (this.movieID < rhs.getMovieID()){
            return -1;
        } else if (this.movieID > rhs.getMovieID()){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Movie m1 = new Movie(1);
        Movie m2 = new Movie(1);

        if (m1.equals(m2)){
            System.out.println("m1.equals(m2)");
        }
    }
}
