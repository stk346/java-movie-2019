package utils;

import domain.Movie;
import domain.MovieRepository;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    public void 영화_잘못선택하면_오류_발생하는지_테스트() {
        int movieId = 9;
        assertThatThrownBy(() -> {
            MovieRepository.getMovie(movieId);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 스케쥴_선택하면_capacity가_줄어드는지_테스트() {
        Movie targetMovie = MovieRepository.getMovie(1);
        int originalCapacity = targetMovie.getPlaySchedule(0).getCapacity();
        targetMovie.selectMovieSchedule(0);
        int afterSelectedScheduleCapacity = targetMovie.getPlaySchedule(0).getCapacity();
        assertThat(originalCapacity).isEqualTo(afterSelectedScheduleCapacity+1);
    }


}