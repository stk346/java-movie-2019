import domain.*;
import view.InputView;
import view.OutputView;

import java.time.LocalDateTime;
import java.util.List;

public class MovieReservationController {

    public void run() {
        boolean continueToReserve = true;
        MovieTicket movieTicket = new MovieTicket();
        while (continueToReserve) {
            List<Movie> movies = MovieRepository.getMovies();
            OutputView.printMovies(movies);


            int movieId = InputView.inputMovieId();
            Movie selected = MovieRepository.getMovie(movieId);

            int movieScheduleIdx = InputView.showEnterScheduleMessageAndGet();
            PlaySchedule selectedSchedule = selected.getPlaySchedule(movieScheduleIdx);
            LocalDateTime selectedTime = selectedSchedule.getStartDateTime();

            int selectedCapacity = InputView.showEnterCapacityMessageAndGet();
            selectedSchedule.decreaseCapacity(selectedCapacity);

            if (!movieTicket.isExist(movieId)) {
                SelectedMovie selectedMovie = new SelectedMovie(selected.getId(), selected.getName(), selected.getPrice());
                movieTicket.addTicket(selectedMovie);
            }
            SelectedMovie selectedMovie = movieTicket.getMovie(movieId);

            if (selectedMovie.isScheduleExist(selectedTime)) {
                selectedMovie.increaseReserveCount(selectedTime, selectedCapacity);
            }
            if (!selectedMovie.isScheduleExist(selectedTime)) {
                selectedMovie.addPlaySchedule(new PlaySchedule(selectedTime, selectedCapacity));
            }

            OutputView.printReserveInfo(movieTicket);
            continueToReserve = InputView.ifContinueToReserveMessageAndGet();
        }
        PriceCalculator priceCalculator = new PriceCalculator(movieTicket);
        int ticketPrice = priceCalculator.getTicketPrice();
        OutputView.printTicketPrice(ticketPrice);
        double point = InputView.showUsePointMessageAndGet();
        double pointDiscountedPrice = priceCalculator.usePoint(ticketPrice, point);
        OutputView.printUsedPointPrice(pointDiscountedPrice);
        String paymentMethod = InputView.askPaymentMethodAndGet();
        double discountedPrice = priceCalculator.getDiscountedPrice(pointDiscountedPrice, paymentMethod);
        OutputView.printTicketPrice(discountedPrice);
    }
}
