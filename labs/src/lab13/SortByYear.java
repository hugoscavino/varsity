package lab13;

import java.util.Comparator;

public class SortByYear implements Comparator<Movie>
{

    @Override
    public int compare(Movie rhs, Movie lhs) {
        return Integer.compare(lhs.getYear(), rhs.getYear());
    }
}
