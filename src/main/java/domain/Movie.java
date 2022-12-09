package domain;

import jdk.vm.ci.meta.Local;

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

    void addPlaySchedule(PlaySchedule playSchedule) {
        playSchedules.add(playSchedule);
    }

    public PlaySchedule getPlaySchedule(int scheduleIdx) {
        LocalDateTime targetTime = playSchedules.get(scheduleIdx).getStartDateTime();
//        if (targetTime.isBefore(LocalDateTime.now())) {
//            throw new IllegalArgumentException("상영중인 영화입니다.");
//        }
        try {
            return playSchedules.get(scheduleIdx);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("올바른 스케줄을 선택해주세요.");
        }
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
