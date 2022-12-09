package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static utils.DateTimeUtils.format;

public class SelectedMovie {

    private final Movie movie;
    private List<PlaySchedule> playSchedules = new ArrayList<>();

    public SelectedMovie(int id, String name, int price) {
        this.movie = new Movie(id, name, price);
    }

    public void addPlaySchedule(PlaySchedule playSchedule) {
        playSchedules.add(playSchedule);
    }

    public void printReservedInfo() {
        StringBuilder sb = new StringBuilder();
        for (PlaySchedule playSchedule : playSchedules) {
            sb.append("시작시간: " + format(playSchedule.getStartDateTime()) + " 예약인원: " + playSchedule.getCapacity() + "\n");
        }
        System.out.println(getId() + " - " + getName() + ", " + getPrice() + "원" + "\n");
        System.out.println(sb);
    }

    public int getReservedCount() {
        int reservedCount = 0;
        for (PlaySchedule playSchedule : playSchedules) {
            reservedCount += playSchedule.getCapacity();
        }
        return reservedCount;
    }

    public boolean isScheduleExist(LocalDateTime schedule) {
        for (PlaySchedule playSchedule : playSchedules) {
            if (playSchedule.getStartDateTime().isEqual(schedule)) {
                return true;
            }
        }
        return false;
    }

    public void increaseReserveCount(LocalDateTime schedule, int count) {
        for (PlaySchedule playSchedule : playSchedules) {
            if (playSchedule.getStartDateTime().isEqual(schedule)); {
                playSchedule.increaseReservedCount(count);
                return;
            }
        }
        throw new IllegalArgumentException("해당 스케쥴이 없습니다.");
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
}
