package com.hugo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class IoDemo {

    public static void main(String[] args) throws IOException {

        Calendar.getInstance().getTime();
        final int EOF = -1;
        String filename = "C:\\Users\\hugo\\IdeaProjects\\LearnProgrammingInJavaLab\\solutions-src\\com\\learnprogramminginjava\\io\\out.txt";
        //try with resource
        try (
             FileInputStream in = new FileInputStream("C:\\Users\\hugo\\IdeaProjects\\LearnProgrammingInJavaLab\\solutions-src\\com\\learnprogramminginjava\\io\\in.txt");
             FileOutputStream out = new FileOutputStream(filename);
        )
        {
            System.out.println("Before : " + Calendar.getInstance().getTime());
            int c;
            while ((c = in.read()) != EOF) {
                //System.out.print((char) c);
                out.write(c);
            }
            System.out.println("After : " + Calendar.getInstance().getTime());
        }

        /*
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
        */
    }
}
