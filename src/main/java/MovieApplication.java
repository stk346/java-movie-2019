import controller.MovieTicketBuyingController;
import domain.Movie;
import domain.MovieRepository;
import domain.MovieTickets;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieApplication {
    public static void main(String[] args) {
//        List<Movie> movies = MovieRepository.getMovies();
//        OutputView.printMovies(movies);
//
//        int movieId = InputView.inputMovieId();
//
//        // TODO 구현 진행
//        OutputView.printMovieSchedule(MovieRepository.getMovie(movieId));
        MovieTicketBuyingController movieTicketBuyingController = new MovieTicketBuyingController();
        movieTicketBuyingController.buyTicket();
    }
}
