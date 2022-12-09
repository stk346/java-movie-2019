package domain;

import java.util.ArrayList;
import java.util.List;

import static utils.DateTimeUtils.format;

public class SelectedMovie {

    private final Movie movie;
    private List<PlaySchedule> playSchedules = new ArrayList<>();

    public SelectedMovie(Movie movie) {
        this.movie = movie;
    }

    public void addPlaySchedule(PlaySchedule playSchedule) {
        playSchedules.add(playSchedule);
    }

    public int getId() {
        return movie.getId();
    }

    public String getName() {
        return movie.getName();
    }

    public int getPrice() {
        return movie.getPrice();
    }

    public List<PlaySchedule> getPlaySchedules() {
        return playSchedules;
    }

    public void printReservedInfo() {
        StringBuilder sb = new StringBuilder();
        for (PlaySchedule playSchedule : playSchedules) {
            sb.append("시작시간: " + format(playSchedule.getStartDateTime()) + " 예약인원: " + playSchedule.getCapacity() + "\n");
        }
        System.out.println(getId() + " - " + getName() + ", " + getPrice() + "원" + "\n");
        System.out.println(sb);
    }
}
