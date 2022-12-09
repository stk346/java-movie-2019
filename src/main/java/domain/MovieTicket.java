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

    public SelectedMovie getMovie(int movieId) {
        for (SelectedMovie selectedMovie : movies) {
            if (selectedMovie.getId() == movieId) {
                return selectedMovie;
            }
        }
        throw new IllegalArgumentException("티켓에 해당 영화가 없습니다.");
    }

    public List<SelectedMovie> getMovies() {
        return movies;
    }
}
