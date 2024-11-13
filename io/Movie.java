package com.hugo.io;

import java.io.Serializable;

public class Movie implements Serializable {

    private final String title;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    int rating = 0;

    public Movie(String title) {
        this.title = title;
    }



    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }
}
