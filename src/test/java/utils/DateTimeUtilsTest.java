package utils;

import domain.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeUtilsTest {
    @Test
    public void createDateTime() {
        LocalDateTime dateTime = DateTimeUtils.createDateTime("2019-04-16 11:23");
        assertThat(dateTime.getYear()).isEqualTo(2019);
        assertThat(dateTime.getMonthValue()).isEqualTo(4);
        assertThat(dateTime.getDayOfMonth()).isEqualTo(16);
        assertThat(dateTime.getHour()).isEqualTo(11);
        assertThat(dateTime.getMinute()).isEqualTo(23);
    }

    @Test
    public void isOneHourWithinRange() {
        LocalDateTime dateTime = DateTimeUtils.createDateTime("2019-04-16 11:23");
        LocalDateTime afterDateTime = DateTimeUtils.createDateTime("2019-04-16 12:22");
        assertThat(DateTimeUtils.isOneHourWithinRange(dateTime, afterDateTime)).isTrue();

        LocalDateTime beforeDateTime = DateTimeUtils.createDateTime("2019-04-16 10:24");
        assertThat(DateTimeUtils.isOneHourWithinRange(dateTime, beforeDateTime)).isTrue();
    }

    @Test
    public void isOneHourWithoutRange() {
        LocalDateTime dateTime = DateTimeUtils.createDateTime("2019-04-16 11:23");
        LocalDateTime afterDateTime = DateTimeUtils.createDateTime("2019-04-16 12:24");
        assertThat(DateTimeUtils.isOneHourWithinRange(dateTime, afterDateTime)).isFalse();

        LocalDateTime beforeDateTime = DateTimeUtils.createDateTime("2019-04-16 10:22");
        assertThat(DateTimeUtils.isOneHourWithinRange(dateTime, beforeDateTime)).isFalse();
    }

    @Test
    public void 조회_테스트1() {
        String movieName = MovieRepository.getMovie(1).getName();
        assertThat(movieName).isEqualTo("생일");
    }

    @Test
    public void 조회_테스트2() {
        String schedule = MovieRepository.getMovie(1).getPlaySchedules().get(0).getStartDateTime().toString();
        assertThat(schedule).isEqualTo("2019-04-16T12:00");
    }

    @Test
    public void 영화추가테스트() {
        SelectedMovies selectedMovies = new SelectedMovies();
        int movieId = MovieRepository.getMovie(1).getId();
        LocalDateTime movieTime = MovieRepository.getMovie(1).getPlaySchedule(0).getStartDateTime();
        selectedMovies.addMovie(movieId, movieTime);
        HashMap<Integer, List<LocalDateTime>> movieInfo = selectedMovies.getSelectedMovies();
        assertThat(movieInfo.get(movieId).toString()).isEqualTo("[2019-04-16T12:00]");
    }

    @Test
    public void 시간테스트() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtils.PLAYING_TIME_FORMAT);
        String stringCurrentTime = LocalDateTime.now().format(formatter);
        LocalDateTime currentTime = DateTimeUtils.createDateTime(stringCurrentTime);
        System.out.println(currentTime);
    }

    @Test
    public void 오류테스트() {
        System.out.println(MovieRepository.getMovie(2).getPlaySchedule(9));
    }
}