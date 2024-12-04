package io;

import java.io.*;

public class ObjectStream {
    public static void main(String[] args) {

        String filename = "C:\\Users\\hugo\\IdeaProjects\\LearnProgrammingInJavaLab\\solutions-src\\com\\learnprogramminginjava\\io\\movie.bin";

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            Movie movie = new Movie("Dune");
            movie.setRating(1);
            oos.writeObject(movie);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try ( ObjectInputStream ois = new ObjectInputStream( new
                FileInputStream(filename))) {

            Movie loadedMovie = (Movie)ois.readObject();
            System.out.println(loadedMovie.getRating());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
