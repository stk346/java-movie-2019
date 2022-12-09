import domain.Movie;
import domain.MovieRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieApplication {
    public static void main(String[] args) {
        // TODO 구현 진행
        MovieReservationController movieReservationController = new MovieReservationController();
        movieReservationController.run();
    }
}
