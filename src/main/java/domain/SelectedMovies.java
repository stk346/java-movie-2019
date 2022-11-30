package domain;

import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SelectedMovies {
    private final HashMap<Integer, List<LocalDateTime>> selectedMovies = new HashMap<>();

    public void addMovie(int id, LocalDateTime localDateTime) {
        if (selectedMovies.get(id) == null) {
            selectedMovies.put(id, new ArrayList<>());
        }
        selectedMovies.get(id).add(localDateTime);
    }

    public HashMap<Integer, List<LocalDateTime>> getSelectedMovies() {
        return selectedMovies;
    }

    private boolean isUnderCurrentTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtils.PLAYING_TIME_FORMAT);
        String stringCurrentTime = LocalDateTime.now().format(formatter);
        LocalDateTime currentTime = DateTimeUtils.createDateTime(stringCurrentTime);
        return localDateTime.isBefore(currentTime);
    }

    private boolean isWithinCapacity(int id, int idx) {
        int capacity = MovieRepository.getMovie(id).getPlaySchedule(idx).getCapacity();
        LocalDateTime selectedSchedule = MovieRepository.getMovie(id).getPlaySchedule(idx).getStartDateTime();
        List<LocalDateTime> selectedMoviesSchedules = selectedMovies.get(id);
        int scheduleCount = 0;
        for (LocalDateTime selectedMoviesSchedule : selectedMoviesSchedules) {
            if (selectedMoviesSchedule == selectedSchedule) {
                scheduleCount++;
            }
        }
        return scheduleCount <= capacity;
    }

    private boolean isOneHourWithinRange(LocalDateTime localDateTime) {
        for (int id : selectedMovies.keySet()) {
            List<LocalDateTime> schedules = selectedMovies.get(id);
            for (LocalDateTime schedule : schedules) {
                if (!DateTimeUtils.isOneHourWithinRange(schedule, localDateTime)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isMovieExists(int id) {
        return MovieRepository.getMovie(id).getId() != -1;
    }
}
