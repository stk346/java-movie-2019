package view;

import domain.Movie;
import domain.MovieTicket;
import domain.SelectedMovie;

import java.util.List;

public class OutputView {
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public static void printReserveInfo(MovieTicket movieTicket) {
        for (SelectedMovie selectedMovie : movieTicket.getMovies()) {
            selectedMovie.printReservedInfo();
        }
    }
}
