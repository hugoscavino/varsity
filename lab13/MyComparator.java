package com.hugo.lab13;

import java.util.Comparator;

public class MyComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie rhs, Movie lhs) {
        return lhs.getMovieName().compareTo(rhs.getMovieName());
    }
}
