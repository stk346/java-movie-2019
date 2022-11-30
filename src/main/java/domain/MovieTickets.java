package domain;

import java.util.ArrayList;
import java.util.List;

public class MovieTickets {

    private List<MovieTicket> movieTickets = new ArrayList<>();

    public void addTicket(MovieTicket movieTicket) {
        movieTickets.add(movieTicket);
    }

    public MovieTicket getMovieTicket(int idx) {
        return movieTickets.get(idx);
    }

    public MovieTicket getTicket(int id) {
        for (MovieTicket movieTicket : movieTickets) {
            MovieTicket movieTicket1 = getMovieTicket(id, movieTicket);
            if (movieTicket1 != null) return movieTicket1;
        }
        throw new IllegalArgumentException("해당 티켓은 없습니다.");
    }

    private MovieTicket getMovieTicket(int id, MovieTicket movieTicket) {
        if (movieTicket.getId() == id) {
            return movieTicket;
        }
        return null;
    }
}
