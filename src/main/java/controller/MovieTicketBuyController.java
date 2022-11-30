package controller;

import domain.AmountCalculator;
import domain.Movie;
import domain.MovieRepository;
import domain.ReservationMachine;
import view.InputView;
import view.OutputView;

public class MovieTicketBuyController {

    private final ReservationMachine reservationMachine;
    private AmountCalculator amountCalculator;

    public MovieTicketBuyController() {
        this.reservationMachine = new ReservationMachine();
    }

    public Movie selectMovie() {
        int userInput = InputView.inputMovieId();
        try {
            return MovieRepository.getMovie(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return selectMovie();
        }
    }

    public void selectSchedule(int id) {
        int userInput = InputView.inputMovieSchedule();
        Movie targetMovie = MovieRepository.getMovie(id);
        try {
            reservationMachine.generateTicketInfo(targetMovie.getId(), targetMovie.getName(), targetMovie.getPrice());
            reservationMachine.buyTicket(id, userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            selectSchedule(id);
        }
    }

    public void generateAmountCalculator() {
        this.amountCalculator = reservationMachine.generateAmountCalculator();
    }

    public int showTicketPrice() {
        return amountCalculator.getTicketPrice();
    }

    private String whetherUsePoint() {
        try {
            return InputView.whetherUsePoint();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return whetherUsePoint();
        }
    }

    public void usePoint() {
        if (whetherUsePoint().equals("O")) {
            use();
            OutputView.showPrice(amountCalculator);
        }
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
}
