package view;

import domain.AmountCalculator;
import domain.Movie;

import java.util.List;

public class OutputView {
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public static void printEnterScheduleMessage() {
        System.out.println("시간을 선택해주세요.");
    }

    public static void printMovieSchedule(Movie movie) {
        System.out.println(movie);
    }

    public static void showPrice(AmountCalculator amountCalculator) {
        System.out.println("티켓 가격은 " + amountCalculator.showPrice() + "입니다.");
    }
}

