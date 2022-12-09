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

            getMovieInfoAndAddTo(movieTicket);

            OutputView.printReserveInfo(movieTicket);
            continueToReserve = isContinueToReserve();
        }
        showAndCalculatePrice(movieTicket);
    }

    private void getMovieInfoAndAddTo(MovieTicket movieTicket) {
        try {
            Movie selectedMovie = getMovie();
            addReservedMovieInfo(movieTicket, selectedMovie);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            getMovieInfoAndAddTo(movieTicket);
        }
    }

    private boolean isContinueToReserve() {
        try {
            return InputView.ifContinueToReserveMessageAndGet();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isContinueToReserve();
        }
    }

    private void showAndCalculatePrice(MovieTicket movieTicket) {
        PriceCalculator priceCalculator = new PriceCalculator(movieTicket);
        int ticketPrice = priceCalculator.getTicketPrice();
        OutputView.printTicketPrice(ticketPrice);

        double pointDiscountedPrice = getPointDiscountedPrice(priceCalculator, ticketPrice);
        OutputView.printUsedPointPrice(pointDiscountedPrice);

        double discountedPrice = getDiscountedPrice(priceCalculator, pointDiscountedPrice);
        OutputView.printTicketPrice(discountedPrice);
    }

    private double getDiscountedPrice(PriceCalculator priceCalculator, double pointDiscountedPrice) {
        try {
            String paymentMethod = InputView.askPaymentMethodAndGet();
            return priceCalculator.getDiscountedPrice(pointDiscountedPrice, paymentMethod);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDiscountedPrice(priceCalculator, pointDiscountedPrice);
        }
    }

    private double getPointDiscountedPrice(PriceCalculator priceCalculator, int ticketPrice) {
        try {
            double point = InputView.showUsePointMessageAndGet();
            return priceCalculator.usePoint(ticketPrice, point);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPointDiscountedPrice(priceCalculator, ticketPrice);
        }

    }

    private void addReservedMovieInfo(MovieTicket movieTicket, Movie selected) throws IllegalArgumentException {

        PlaySchedule selectedSchedule = getPlaySchedule(selected);
        LocalDateTime selectedTime = selectedSchedule.getStartDateTime();

        int selectedCapacity = getSelectedCapacity(selectedSchedule);

        if (!movieTicket.isExist(selected.getId())) {
            SelectedMovie selectedMovie = new SelectedMovie(selected.getId(), selected.getName(), selected.getPrice());
            movieTicket.addTicket(selectedMovie);
        }
        SelectedMovie selectedMovie = movieTicket.getMovie(selected.getId());

        addSchedule(selectedTime, selectedCapacity, selectedMovie);
    }

    private int getSelectedCapacity(PlaySchedule selectedSchedule) {
        try {
            int selectedCapacity = InputView.showEnterCapacityMessageAndGet();
            selectedSchedule.decreaseCapacity(selectedCapacity);
            return selectedCapacity;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getSelectedCapacity(selectedSchedule);
        }
    }

    private PlaySchedule getPlaySchedule(Movie selected) {
        try {
            int movieScheduleIdx = InputView.showEnterScheduleMessageAndGet();
            return selected.getPlaySchedule(movieScheduleIdx);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPlaySchedule(selected);
        }
    }

    private void addSchedule(LocalDateTime selectedTime, int selectedCapacity, SelectedMovie selectedMovie) throws IllegalArgumentException {
        if (selectedMovie.isScheduleExist(selectedTime)) {
            selectedMovie.increaseReserveCount(selectedTime, selectedCapacity);
        }
        if (!selectedMovie.isScheduleExist(selectedTime)) {
            selectedMovie.addPlaySchedule(new PlaySchedule(selectedTime, selectedCapacity));
        }
    }

    private Movie getMovie() {
        try {
            int movieId = InputView.inputMovieId();
            return MovieRepository.getMovie(movieId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMovie();
        }
    }
}
