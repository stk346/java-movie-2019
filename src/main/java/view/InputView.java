package view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        System.out.println("## 예약할 영화를 선택하세요.");
        try {
            return scanner.nextInt();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("올바른 값을 입력해주세요.");
        }
    }

    public static int movieSchedule() {
        System.out.println("## 시간을 선택해주세요.");
        try {
            return scanner.nextInt();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("올바른 시간을 입력해주세요.");
        }
    }
}
