package view;

import domain.*;

import java.util.List;

import static utils.DateTimeUtils.format;

public class OutputView {
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public static void printReserveInfo(MovieTicket movieTicket) {
        for (SelectedMovie selectedMovie : movieTicket.getMovies()) {
            StringBuilder sb = new StringBuilder();
            System.out.println(selectedMovie.getId() + " - " + selectedMovie.getName() + ", " + selectedMovie.getPrice() + "원" + "\n");
            for (PlaySchedule playSchedule : selectedMovie.getPlaySchedules()) {
                sb.append("시작시간: " + format(playSchedule.getStartDateTime()) + " 예약인원: " + playSchedule.getCapacity() + "\n");
            }
            System.out.println(sb);
        }
    }

    public static void printTicketPrice(int ticketPrice) {
        System.out.println("티켓 가격: " + ticketPrice);
    }

    public static void printTicketPrice(double ticketPrice) {
        System.out.println("티켓 가격: " + ticketPrice);
    }

    public static void printUsedPointPrice(double pointDiscountedPrice) {
        System.out.println("포인트가 적용된 티켓 가격: " + pointDiscountedPrice);
    }


}
