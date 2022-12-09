package domain;

import utils.DateTimeUtils;

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

    public void addPlaySchedule(PlaySchedule playSchedule) throws IllegalArgumentException {
        for (PlaySchedule Schedule : playSchedules) {
            LocalDateTime existingSchedule = Schedule.getStartDateTime();
            if (!DateTimeUtils.isOneHourWithinRange(existingSchedule, playSchedule.getStartDateTime())) {
                throw new IllegalArgumentException("예매한 영화와 시간 차이가 1시간 이내입니다.");
            }
        }
        playSchedules.add(playSchedule);
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
