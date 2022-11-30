package controller;

import domain.MovieRepository;
import domain.SelectedMovies;
import view.InputView;

import java.time.LocalDateTime;

public class MovieController {
    private SelectedMovies selectedMovies;

    public MovieController() {
        this.selectedMovies = new SelectedMovies();
    }

    public int getMovieId() {
        try {
            return InputView.inputMovieId();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMovieId();
        }
    }

    public int getMovieSchedule() {
        try {
            return InputView.movieSchedule();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMovieSchedule();
        }
    }

    public LocalDateTime getMovieSchedule(int id, int idx) {
        return MovieRepository.getMovie(id).getPlaySchedule(idx).getStartDateTime();
    }

    public int getCapacity(int id, int idx) {
        return MovieRepository.getMovie(id).getPlaySchedule(idx).getCapacity();
    }

    public int getPrice(int id, int idx) {
        return MovieRepository.getMovie(id).getPrice();
    }
}
