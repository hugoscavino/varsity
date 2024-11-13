package com.hugo.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class BufferedDemo {
    public static <BufferedReader> void main(String[] args) {

        String filename = "C:\\Users\\hugo\\IdeaProjects\\LearnProgrammingInJavaLab\\solutions-src\\com\\learnprogramminginjava\\io\\in.txt";
        String s = read(filename);
    }

    public static String read( String filename ) {

        StringBuilder sb = new StringBuilder();

        try {
            FileReader fr = new FileReader(filename);
            System.out.println("Before : " + Calendar.getInstance().getTime());

            try (BufferedReader input = new BufferedReader(fr)) {
                String s;
                while ((s = input.readLine()) != null) {
                    sb.append(s + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("After : " + Calendar.getInstance().getTime());

        return sb.toString();
    }
}
