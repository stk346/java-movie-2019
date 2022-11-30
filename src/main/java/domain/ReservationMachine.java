package domain;

import jdk.vm.ci.meta.Local;

import java.time.LocalDateTime;

public class ReservationMachine {
    private MovieTickets movieTickets;

    public ReservationMachine() {
        this.movieTickets = new MovieTickets();
    }

    public void addMovieTicket(int id, String name, int price) {
        movieTickets.addTicket(new MovieTicket(id, name, price));
    }

    public void chooseSchedule(int id, int idx) {
        isTicketEmpty(id, idx);
        LocalDateTime selectedSchedule = MovieRepository.getMovie(id).selectMovieSchedule(idx);
        movieTickets.getTicket(id).addSchedule(selectedSchedule);
    }

    private void isTicketEmpty(int id, int idx) {
        Movie targetMovie = MovieRepository.getMovie(id);
        if (targetMovie.isEmpty(idx)) {
            throw new IllegalArgumentException("좌석 수가 없습니다.");
        }
    }
}
