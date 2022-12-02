package domain;

import java.time.LocalDateTime;

import static utils.DateTimeUtils.format;

public class PlaySchedule {
    private final LocalDateTime startDateTime;
    private int capacity;

    public PlaySchedule(LocalDateTime startDateTime, int capacity) {
        this.startDateTime = startDateTime;
        this.capacity = capacity;
    }

    public boolean isCapacityEmpty() {
        return capacity == 0;
    }

    public void select() {
        if (capacity <= 0) {
            System.out.println("예약가능인원이 다 찼습니다.");
            return;
        }
        capacity--;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "시작시간: " + format(startDateTime) + " 예약가능인원: " + capacity + "\n";
    }
}
