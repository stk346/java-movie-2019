import domain.*;
import view.InputView;
import view.OutputView;

import java.time.LocalDateTime;
import java.util.List;

public class MovieReservationController {

    public void run() {
        boolean continueToReserve = true;
        while (continueToReserve) {
            List<Movie> movies = MovieRepository.getMovies();
            OutputView.printMovies(movies);

            MovieTicket movieTicket = new MovieTicket();

            int movieId = InputView.inputMovieId();
            Movie selected = MovieRepository.getMovie(movieId);

            int movieScheduleIdx = InputView.showEnterScheduleMessageAndGet();
            LocalDateTime selectedTime = selected.getPlaySchedule(movieScheduleIdx).getStartDateTime();

            int selectedCapacity = InputView.showEnterCapacityMessageAndGet();
            selected.getPlaySchedule(movieScheduleIdx).decreaseCapacity(selectedCapacity);
            SelectedMovie selectedMovie = new SelectedMovie(selected);
            selectedMovie.addPlaySchedule(new PlaySchedule(selectedTime, selectedCapacity));

            movieTicket.addTicket(selectedMovie);

            OutputView.printReserveInfo(movieTicket);
        }

    }
}
