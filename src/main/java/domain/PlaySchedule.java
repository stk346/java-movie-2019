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

    public void decreaseCapacity(int selectedCapacity) {
        if (capacity - selectedCapacity < 0) {
            throw new IllegalArgumentException("예매 가능 인원을 초과했습니다.");
        }
        capacity = capacity - selectedCapacity;
    }

    public void increaseReservedCount(int count) {
        capacity = capacity + count;
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
