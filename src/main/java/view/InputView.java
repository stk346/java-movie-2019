package view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        System.out.println("## 예약할 영화를 선택하세요.");
        return scanner.nextInt();
    }

    public static int inputMovieSchedule() {
        System.out.println("예매할 영화 시간을 선택하세요 (위에서부터 0번 순서입니다.)");
        return scanner.nextInt();
    }

    public static String whetherUsePoint() throws IllegalArgumentException {
        System.out.println("포인트를 사용하시겠습니까? (O, X로 입력해주세요.");
        String userInput = scanner.nextLine();
        if (!userInput.equals("X") && !userInput.equals("O")) {
            throw new IllegalArgumentException("O, X만 입력할 수 있습니다.");
        }
        return userInput;
    }

    public static int inputPoint() {
        System.out.println("사용할 포인트를 입력해주세요.");
        try {
            return scanner.nextInt();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("슷지민 입력해주세요.");
        }
    }
}
