package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterDemo {

    public static void main(String[] args) {
        String filename = "C:\\Users\\hugo\\IdeaProjects\\LearnProgrammingInJavaLab\\solutions-src\\com\\learnprogramminginjava\\io\\out-pw.txt";

        writeInts(filename);
    }

    public static void writeInts( String filename ) {

        Movie[] list = new Movie[3];
        list[0] = new Movie("Wicked");
        list[1] = new Movie("Movie2");
        list[2] = new Movie("Dune");

        System.err.println(list[2]);


        try ( PrintWriter printWriter = new PrintWriter(
                new BufferedWriter(new FileWriter(filename))) ) {

            for (Movie i: list) {
                printWriter.println(i);
            }


        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
