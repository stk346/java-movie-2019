package domain;

import java.util.ArrayList;
import java.util.List;

public class MovieTicket {
    private final List<SelectedMovie> movies = new ArrayList<>();

    public void addTicket(SelectedMovie selectedMovie) {
        movies.add(selectedMovie);
    }

    public boolean isExist(int movieId) {
        for (SelectedMovie selectedMovie : movies) {
            if (selectedMovie.getId() == movieId) {
                return true;
            }
        }
        return false;
    }

    public List<SelectedMovie> getMovies() {
        return movies;
    }
}
