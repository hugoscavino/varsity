package lab14;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Lab14Main {
    public ArrayList<Movie> movieList = new ArrayList<Movie>();
    private static Scanner keyboard = new Scanner(System.in);
    public static final int MOVIE_COUNT = 5; // Return this many movies in the searches

    public Map<String, Movie> byNameMap = new HashMap<>();
    public Map<Integer, List<Movie>> byYearMap = new HashMap<>();
    public Map<String, List<Movie>> byGenreMap = new HashMap<>();

    /*
      Partially complete
      Add your code where needed
    */
    public static void main(String[] args) {
        Lab14Main lab14Main = new Lab14Main();

        // START - Read the file from the relative location
        // https://stackoverflow.com/a/3844316/1042032
        URL url = Lab14Main.class.getResource("movies.tsv");
        // Should check for null
        File file = new File(url.getPath());
        lab14Main.readMovies(file.getAbsolutePath());
        // END

        // STEP 1
        // As a test: comment out this line after you're sure
        // that readMovies works
        // lab14Main.displayMovies(lab14Main.getList());

        int choice;
        do {
            choice = getMenuChoice();
            switch (choice) {
                // case 1 is done for you
                case 1:
                    lab14Main.sortBy("ID");
                    lab14Main.displayMovies(lab14Main.getList());
                    break;
                case 2:
                    lab14Main.sortBy("Name");
                    lab14Main.displayMovies(lab14Main.getList());
                    break;
                case 3:
                    // Your code here
                    lab14Main.sortBy("Year");
                    lab14Main.displayMovies(lab14Main.getList());
                    break;
                case 4:
                    // Your code here
                    lab14Main.sortBy("ReverseYear");
                    lab14Main.displayMovies(lab14Main.getList());
                    break;
                case 5:
                    System.out.print("Enter the movie name: ");
                    // Use nextLine() everywhere!
                    String name = keyboard.nextLine();
                    // Do something with name
                    System.out.println("Searching on name : " + name);
                    ArrayList<Movie> nameSearchMovies = lab14Main.searchByName(name);
                    lab14Main.displayMovies(nameSearchMovies);
                    break;
                case 6:
                    // Your code here
                    System.out.print("Enter the movie Year: ");
                    // Use nextLine() everywhere!
                    String year = keyboard.nextLine();
                    // Do something with name
                    System.out.println("Searching on year : " + year);
                    List<Movie> movies = lab14Main.searchByYear(Integer.parseInt(year));
                    lab14Main.displayMovies(movies);

                    break;
                case 7:
                    // Your code here
                    System.out.print("Enter the movie Genre: ");
                    // Use nextLine() everywhere!
                    String genre = keyboard.nextLine();
                    // Do something with name
                    System.out.println("Searching on Genre : " + genre);
                    ArrayList<Movie> genMovies = lab14Main.searchByGenre(genre);
                    lab14Main.displayMovies(genMovies);
                    break;
                case 8:
                    displayTotals(lab14Main);
                    System.out.println("Bye");
                    break;
            }
        } while (choice != 8);
    }

    /**
     * lab19.Movie totals
     * movieList size : 201
     * byNameMap size : 201
     * byYearMap size : 30
     * byGenreMap size: 19
     */
    private static void displayTotals(Lab14Main main) {
        System.out.println("lab19.Movie totals");
        System.out.println("movieList size : " + main.movieList.size());
        System.out.println("byNameMap size : " + main.byNameMap.size());
        System.out.println("byYearMap size : " + main.byYearMap.size());
        System.out.println("byGenreMap size : " + main.byGenreMap.size());
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
            create a lab19.Movie using toMovie
            add it to movieList
        */
        /*
        2. In the readMovies( ) method, along with populating
        the ArrayList of Movies, also populate byNameMap. The key is
        the lab19.Movie name and the value is the lab19.Movie. Do this in the
        same while loop. If there are duplicate names, just re-insert
        the movie – this will lose some data, but let's go with that.
         */
        if (fileInput != null) {
            while (fileInput.hasNext()) {

                String line = fileInput.nextLine();
                System.out.println(line);
                String[] strings = line.split("\t", -1);
                Movie aMovie = toMovie(strings);
                movieList.add(aMovie);

                // populate byNameMap
                byNameMap.put(aMovie.getMovieName(), aMovie);

                /*
                * 4. Again in readMovies( ), populate byYearMap,
                * still in the same while loop. The key is the lab19.Movie year and
                * the value is an ArrayList of lab19.Movie objects.
                *
                * * Do the following steps:
                * - if the year is already in byYearMap,
                *       get its value – this is an ArrayList – and
                *       add the current lab19.Movie object to that ArrayList.
                *       Because Maps return a reference to the ArrayList,
                *       you don't have to reinsert it (yep, it breaks encapsulation
                *       for the Map). In fact, see if you can do this in one line.
                *
                * - if the year is not yet in byYearMap,
                *       create a new ArrayList, add the current lab19.Movie object to it, and
                * insert this year and ArrayList into byYearMap.
                *
                * */
                Integer year = aMovie.getYear();
                if (byYearMap.containsKey(year)){
                    List<Movie> movies = byYearMap.get(year);
                    movies.add(aMovie);
                } else {
                    List<Movie> movies = new ArrayList<>(1);
                    movies.add(aMovie);
                    byYearMap.put(year, movies);
                }

                /*
                * 6. One more time: in readMovies( ), in the while loop,
                * populate byGenreMap. The key is the genre and the value is an
                * ArrayList of lab19.Movie objects. Do this the same way you populated byYearMap,
                * but with the following twist:
                * write a for
                * loop over the current movie's genres – remember, this is an ArrayList of genres.
                * For each genre in that list, follow the steps you used for byYearMap,
                * but using the current genre.
                 */
                ArrayList<String> genres = aMovie.getGenres();
                for (String genre : genres) {
                    if (byGenreMap.containsKey(genre)){
                        byGenreMap.get(genre).add(aMovie);
                    } else {
                        List<Movie> movies = new ArrayList<>(List.of(aMovie));
                        byGenreMap.put(genre, movies);
                    }
                }
            }
        } else {
            System.out.println("File not found.");
        }

    }

    public Movie toMovie(String[] str) {
        /*  Returns one lab19.Movie from the data in str
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
    private void displayMovies(List<Movie> list) {
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
                // Use lab19.Movie's built-in compareTo
                Collections.sort(movieList);
                break;
            case "Name":
                // Your code here
                Collections.sort(movieList, Comparator.comparing(Movie::getMovieName));
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
                movieList.sort(c);
                break;
        }
    }

    // Search for MOVIE_COUNT movies by name
    public ArrayList<Movie> searchByName(String name) {
        // Sort by id before searches for consistent results
        sortBy("ID");

        // List of results
        ArrayList<Movie> list = new ArrayList<>();

        // Count the # of matches
       // int count = 0;
        /*
        for (lab19.Movie m: movieList) {
            // Does m match on the name key?
            if (m.getMovieName().equals(name)) {
                // Yes, so add it to the result list
                list.add(m);
                count++;

                // Quit if we hit the maximum # of movies to return
                if (count == Lab14Main.MOVIE_COUNT) break;
            }
        }

        */
        /*
        3. Rewrite the searchByName( ) method to use byNameMap
        for the lookup instead of searching the movieList. create
        and return an ArrayList with just one movie, the one returned
        from byNameMap (so it doesn't break the other code).
         */
        // Test this to make sure it's working.

        Movie movie = byNameMap.get(name);
        list.add(movie);
        return list;

    }

    // Search for MOVIE_COUNT movies by year
    public List<Movie> searchByYear(int year) {
        // Sort by id before searches for consistent results
        sortBy("Year");

        // List of results

        // Fill up list with MOVIE_COUNT movies that match on year
        // Your code here
        // Count the # of matches
        /*
        int count = 0;
        for (lab19.Movie m: movieList) {
            // Does m match on the name key?
            if (m.getYear() == year) {
                // Yes, so add it to the result list
                list.add(m);
                count++;

                // Quit if we hit the maximum # of movies to return
                if (count == Lab14Main.MOVIE_COUNT) break;
            }
        }
        */
        /*
        * 5. Rewrite the searchByYear( ) method to use byYearMap
        * for the lookup instead of searching the movieList. Ignore the
        * MOVIE_COUNT limit –this time, return *all* of the movies for this year.
        * Test this to make sure it's working.
        */

        List<Movie> movies = byYearMap.get(year);
        return movies;
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
                if (count == Lab14Main.MOVIE_COUNT) break;
            }
        }
        return list;
    }

    // Breaks encapsulation, boo!
    public ArrayList<Movie> getList() { return movieList; }

}
