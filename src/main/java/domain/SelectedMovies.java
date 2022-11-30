package domain;

import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SelectedMovies {
    private final HashMap<Integer, List<LocalDateTime>> selectedMovies;

    public SelectedMovies() {
        this.selectedMovies = new HashMap<>();
    }

    public void addMovie(int id, LocalDateTime localDateTime) {
        if (selectedMovies.get(id) == null) {
            selectedMovies.put(id, new ArrayList<>());
        }
        selectedMovies.get(id).add(localDateTime);
    }

    private boolean isUnderCurrentTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtils.PLAYING_TIME_FORMAT);
        String stringCurrentTime = LocalDateTime.now().format(formatter);
        LocalDateTime currentTime = DateTimeUtils.createDateTime(stringCurrentTime);
        return localDateTime.isBefore(currentTime);
    }

    private void isWithinCapacity(int id, int idx) {
        int capacity = MovieRepository.getMovie(id).getPlaySchedule(idx).getCapacity();
        LocalDateTime selectedSchedule = MovieRepository.getMovie(id).getPlaySchedule(idx).getStartDateTime();
        List<LocalDateTime> selectedMoviesSchedules = selectedMovies.get(id);
        int scheduleCount = 0;
        if (judgeWithinCapacity(capacity, selectedSchedule, selectedMoviesSchedules, scheduleCount)) {
            throw new IllegalArgumentException("입장 가능 인원을 초과했습니다.");
        }
    }

    private boolean judgeWithinCapacity(int capacity, LocalDateTime selectedSchedule, List<LocalDateTime> selectedMoviesSchedules, int scheduleCount) {
        for (LocalDateTime selectedMoviesSchedule : selectedMoviesSchedules) {
            if (selectedMoviesSchedule == selectedSchedule) {
                scheduleCount++;
            }
        }
        return scheduleCount <= capacity;
    }

    private void isOneHourWithinRange(LocalDateTime localDateTime) {
        for (int id : selectedMovies.keySet()) {
            List<LocalDateTime> schedules = selectedMovies.get(id);
            if (judgeWithinRange(localDateTime, schedules)) {
                throw new IllegalArgumentException("선택된 영화의 시작 시간이 1시간 이상 차이납니다.");
            }
        }
    }

    private boolean judgeWithinRange(LocalDateTime localDateTime, List<LocalDateTime> schedules) {
        for (LocalDateTime schedule : schedules) {
            if (!DateTimeUtils.isOneHourWithinRange(schedule, localDateTime)) {
                return true;
            }
        }
        return false;
    }

    private void isMovieExists(int id) {
        if (MovieRepository.getMovie(id).getId() != -1) {
            throw new IllegalArgumentException("올바른 영화를 선택해주세요.");
        }
    }

    private void isScheduleExists(int id, int idx) {
        int scheduleSize = MovieRepository.getMovie(id).getPlaySchedules().size();
        if (idx < scheduleSize || idx > scheduleSize) {
            throw new IllegalArgumentException("올바른 시간을 선택해주세요.");
        }
    }

    public HashMap<Integer, List<LocalDateTime>> getSelectedMovies() {
        return selectedMovies;
    }
}
