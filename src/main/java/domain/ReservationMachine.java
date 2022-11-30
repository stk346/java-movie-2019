package domain;


import java.time.LocalDateTime;

public class ReservationMachine {
    private final MovieTickets movieTickets;

    public ReservationMachine() {
        this.movieTickets = new MovieTickets();
    }

    public void generateTicketInfo(int id, String name, int price) {
        if (!movieTickets.isMovieInfoExist(id)) {
            movieTickets.addTicket(new MovieTicket(id, name, price));
        }
    }

    public void buyTicket(int id, int idx) {
        Movie targetMovie = MovieRepository.getMovie(id);
        LocalDateTime selectedTime = targetMovie.selectMovieSchedule(idx);
        movieTickets.getMovieTicket(id).addSchedule(selectedTime);
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

    public AmountCalculator generateAmountCalculator() {
        return new AmountCalculator(movieTickets);
    }
}
