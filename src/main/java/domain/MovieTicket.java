package domain;


import jdk.vm.ci.meta.Local;
import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MovieTicket {

    private final int id;
    private final String name;
    private final int price;

    private List<LocalDateTime> selectedSchedule;

    public MovieTicket(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.selectedSchedule = new ArrayList<>();
    }

    public void addSchedule(LocalDateTime localDateTime) {
//        isPastSchedule(localDateTime);
        isOneHourWithinRange(localDateTime);
        selectedSchedule.add(localDateTime);
    }

//    private void isPastSchedule(LocalDateTime localDateTime) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtils.PLAYING_TIME_FORMAT);
//        String stringCurrentTime = LocalDateTime.now().format(formatter);
//        LocalDateTime currentTime = DateTimeUtils.createDateTime(stringCurrentTime);
//        if (localDateTime.isBefore(currentTime)) {
//            throw new IllegalArgumentException("해당 영화는 이미 상영중입니다.");
//        }
//    }

    private void isOneHourWithinRange(LocalDateTime localDateTime) {
        for (LocalDateTime existingSchedule : selectedSchedule) {
            isInRange(localDateTime, existingSchedule);
        }
    }

    private void isInRange(LocalDateTime localDateTime, LocalDateTime existingSchedule) {
        if (!DateTimeUtils.isOneHourWithinRange(existingSchedule, localDateTime)) {
            throw new IllegalArgumentException("영화 시작 시간이 1시간 이상 차이납니다.");
        }
    }

    public int getTotalAmount() {
        return price * selectedSchedule.size();
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
}
