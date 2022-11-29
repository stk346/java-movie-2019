package utils;

import domain.MovieRepository;
import domain.PlaySchedule;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

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
        String movieName = MovieRepository.getMovie(0).getName();
        assertThat(movieName).isEqualTo("생일");
    }

    @Test
    public void 조회_테스트2() {
        String schedule = MovieRepository.getMovie(0).getPlaySchedules().get(0).getStartDateTime().toString();
        assertThat(schedule).isEqualTo("2019-04-16T12:00");
    }


}