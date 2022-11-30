package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private static final char NEW_LINE = '\n';

    private final int id;
    private final String name;
    private final int price;

    private List<PlaySchedule> playSchedules = new ArrayList<>();

    public Movie(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public LocalDateTime selectMovieSchedule(int idx) {
        PlaySchedule selectedSchedule = playSchedules.get(idx);
        playSchedules.get(idx).select();
        return selectedSchedule.getStartDateTime();
    }

    void addPlaySchedule(PlaySchedule playSchedule) {
        playSchedules.add(playSchedule);
    }

    public boolean isEmpty(int idx) {
        return playSchedules.get(idx).isCapacityEmpty();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public List<PlaySchedule> getPlaySchedules() {
        return playSchedules;
    }

    public PlaySchedule getPlaySchedule(int idx) throws IllegalArgumentException{
        try {
            return playSchedules.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("올바른 값을 입력해주세요.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlaySchedule playSchedule : playSchedules) {
            sb.append(playSchedule);
        }
        return id + " - " + name + ", " + price + "원" + NEW_LINE
                + sb.toString();
    }
}
