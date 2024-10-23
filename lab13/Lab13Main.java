package com.hugo.lab13;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Lab13Main {
    public ArrayList<Movie> movieList = new ArrayList<Movie>();
    private static Scanner keyboard = new Scanner(System.in);
    public static final int MOVIE_COUNT = 5; // Return this many movies in the searches

    /*
      Partially complete
      Add your code where needed
    */
    public static void main(String[] args) {
        Lab13Main lab13main = new Lab13Main();

        // START - Read the file from the relative location
        // https://stackoverflow.com/a/3844316/1042032
        URL url = Lab13Main.class.getResource("movies.tsv");
        // Should check for null
        File file = new File(url.getPath());
        lab13main.readMovies(file.getAbsolutePath());
        // END

        // STEP 1
        // As a test: comment out this line after you're sure
        // that readMovies works
        // lab13main.displayMovies(lab13main.getList());

        int choice;
        do {
            choice = getMenuChoice();
            switch (choice) {
                // case 1 is done for you
                case 1:
                    lab13main.sortBy("ID");
                    lab13main.displayMovies(lab13main.getList());
                    break;
                case 2:
                    lab13main.sortBy("Name");
                    lab13main.displayMovies(lab13main.getList());
                    break;
                case 3:
                    // Your code here
                    lab13main.sortBy("Year");
                    lab13main.displayMovies(lab13main.getList());
                    break;
                case 4:
                    // Your code here
                    lab13main.sortBy("ReverseYear");
                    lab13main.displayMovies(lab13main.getList());
                    break;
                case 5:
                    System.out.print("Enter the movie name: ");
                    // Use nextLine() everywhere!
                    String name = keyboard.nextLine();
                    // Do something with name
                    System.out.println("Searching on name : " + name);
                    ArrayList<Movie> nameSearchMovies = lab13main.searchByName(name);
                    lab13main.displayMovies(nameSearchMovies);
                    break;
                case 6:
                    // Your code here
                    System.out.print("Enter the movie Year: ");
                    // Use nextLine() everywhere!
                    String year = keyboard.nextLine();
                    // Do something with name
                    System.out.println("Searching on year : " + year);
                    ArrayList<Movie> movies = lab13main.searchByYear(Integer.parseInt(year));
                    lab13main.displayMovies(movies);

                    break;
                case 7:
                    // Your code here
                    System.out.print("Enter the movie Genre: ");
                    // Use nextLine() everywhere!
                    String genre = keyboard.nextLine();
                    // Do something with name
                    System.out.println("Searching on Genre : " + genre);
                    ArrayList<Movie> genMovies = lab13main.searchByGenre(genre);
                    lab13main.displayMovies(genMovies);
                    break;
                case 8:
                    System.out.println("Bye");
                    break;
            }
        } while (choice != 8);
    }

    /*
       Don't change this method
    */
    public static int getMenuChoice() {
        System.out.println("1. Display by ID\n2. Display by name\n3. Display by year\n" +
                "4. Display by year in reverse\n5. Search by name\n6. Search by year\n" +
                "7. Search by Genre\n8. Quit");
        System.out.print("Enter your choice: ");
        int choice = 8;
        try {
            choice = Integer.parseInt(keyboard.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Choose 1..8");
        }
        if (choice < 1 || choice > 8) choice = 8;
        return choice;
    }

    public void readMovies(String filename) {
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /* Your code here
           While there are lines in the file
            read a line and split it on \t
            create a Movie using toMovie
            add it to movieList
        */
        // STEP 3
        while(fileInput.hasNext()){

            String line  = fileInput.nextLine();
            System.out.println(line);
            String[] strings = line.split("\t", -1);
            Movie aMovie = toMovie(strings);
            movieList.add(aMovie);

        }

    }

    public Movie toMovie(String[] str) {
        /*  Returns one Movie from the data in str
            Each line of str should contain one field
            Change last one to ArrayList<String> for genres
        */
        int movieID = Integer.parseInt(str[0].strip());
        String movieName = str[1].strip().replace("\"", "");
        int year = Integer.parseInt(str[2].strip());
        String country = str[3].strip().replace("\"", "");
        ArrayList<String> genres = new ArrayList<String>();
        // Start at position #4
        // Strip and add to the ArrayList of genres
        for (int i=4; i<str.length; i++) {
            genres.add(str[i].strip());
        }
        return new Movie(movieID, movieName, year, country, genres);
    }

    // Don't change this, even if you don't like my table spacing
    private void displayMovies(ArrayList<Movie> list) {
        if (list.size() == 0) {
            System.out.println("Nothing to display");
        } else {
            System.out.format("%7s %50s %5s %30s %6s\n", "ID", "Name", "Year", "Country", "Genres");
            for (Movie m: list) {
                System.out.format("%7s %50s %5d %30s ", m.getMovieID(), m.getMovieName(),
                        m.getYear(), m.getCountry());
                for (int i=0; i<m.getGenres().size(); i++) {
                    System.out.print(m.getGenres().get(i) + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    // Sort according to the field indicated by s
    public void sortBy(String s) {
        switch (s) {
            case "ID":
                // Use Movie's built-in compareTo
                Collections.sort(movieList);
                break;
            case "Name":
                // Your code here
                Collections.sort(movieList, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie lfh, Movie rhs) {
                        return lfh.getMovieName().compareTo(rhs.getMovieName());

                    }
                });
                break;
            case "Year":
                // Your code here
                Collections.sort(movieList, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie rhs, Movie lhs) {
                        return Integer.compare(lhs.getYear(), rhs.getYear());
                    }
                });
                break;
            case "ReverseYear":
                // Your code here
                Comparator<Movie> c = Collections.reverseOrder(new SortByYear());
                Collections.sort(movieList, c);
                break;
        }
    }

    // Search for MOVIE_COUNT movies by name
    public ArrayList<Movie> searchByName(String name) {
        // Sort by id before searches for consistent results
        sortBy("ID");

        // List of results
        ArrayList<Movie> list = new ArrayList<Movie>();

        // Count the # of matches
        int count = 0;
        for (Movie m: movieList) {
            // Does m match on the name key?
            if (m.getMovieName().equals(name)) {
                // Yes, so add it to the result list
                list.add(m);
                count++;

                // Quit if we hit the maximum # of movies to return
                if (count == Lab13Main.MOVIE_COUNT) break;
            }
        }
        return list;
    }

    // Search for MOVIE_COUNT movies by year
    public ArrayList<Movie> searchByYear(int year) {
        // Sort by id before searches for consistent results
        sortBy("Year");

        // List of results
        ArrayList<Movie> list = new ArrayList<Movie>();

        // Fill up list with MOVIE_COUNT movies that match on year
        // Your code here
        // Count the # of matches
        int count = 0;
        for (Movie m: movieList) {
            // Does m match on the name key?
            if (m.getYear() == year) {
                // Yes, so add it to the result list
                list.add(m);
                count++;

                // Quit if we hit the maximum # of movies to return
                if (count == Lab13Main.MOVIE_COUNT) break;
            }
        }

        return list;
    }

    // Search for MOVIE_COUNT movies by genre
    public ArrayList<Movie> searchByGenre(String genre) {
        // Sort by id before searches for consistent results
        sortBy("ID");

        // List of results
        ArrayList<Movie> list = new ArrayList<Movie>();

        // Count the # of matches
        int count = 0;
        for (Movie m: movieList) {
            if (m.getGenres().contains(genre)) {
                // Yes, so add it to the result list
                list.add(m);
                count++;

                // Quit if we hit the maximum # of movies to return
                if (count == Lab13Main.MOVIE_COUNT) break;
            }
        }
        return list;
    }

    // Breaks encapsulation, boo!
    public ArrayList<Movie> getList() { return movieList; }

}
