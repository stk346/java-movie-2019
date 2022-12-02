package controller;

import domain.AmountCalculator;
import domain.Movie;
import domain.MovieRepository;
import domain.ReservationMachine;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieTicketBuyingController {

    private final ReservationMachine reservationMachine;
    private AmountCalculator amountCalculator;

    public MovieTicketBuyingController() {
        this.reservationMachine = new ReservationMachine();
    }

    public void buyTicket() {
        boolean continueBuyTicket = true;
        while (continueBuyTicket) {
            List<Movie> movies = MovieRepository.getMovies();
            OutputView.printMovies(movies);
            Movie selectedMovie = selectMovie();
            OutputView.printMovieSchedule(selectedMovie);
            selectSchedule(selectedMovie);
            continueBuyTicket = InputView.isQuitBuyingTicket();
        }
        this.amountCalculator = reservationMachine.generateAmountCalculator();
        OutputView.showPrice(amountCalculator);
        usePoint();
        doDiscount();
    }

    public Movie selectMovie() {
        try {
            int userInput = InputView.inputMovieId();
            return MovieRepository.getMovie(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return selectMovie();
        }
    }

    public void selectSchedule(Movie movie) {
        int userInput = InputView.inputMovieSchedule();
        Movie targetMovie = MovieRepository.getMovie(movie.getId());
        try {
            reservationMachine.generateTicketInfo(targetMovie.getId(), targetMovie.getName(), targetMovie.getPrice());
            reservationMachine.buyTicket(movie.getId(), userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            selectSchedule(movie);
        }
        OutputView.printMovieSchedule(movie);
    }

    public void usePoint() {
        use();
        OutputView.showPrice(amountCalculator);
    }

    private void use() {
        try {
            int pointToUse = InputView.inputPoint();
            amountCalculator.usePoint(pointToUse);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            usePoint();
        }
    }

    private String getMethodOfPayment() {
        try {
            return InputView.inputToUseCashOrCard();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMethodOfPayment();
        }
    }

    public void doDiscount() {
        if (getMethodOfPayment().equals("현금")) {
            amountCalculator.discountCash();
            OutputView.showPrice(amountCalculator);
            return;
        }
        amountCalculator.discountCard();
        OutputView.showPrice(amountCalculator);
    }
}
