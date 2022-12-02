package view;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        System.out.println("## 예약할 영화를 선택하세요.");
        try {
            String stringUserInput = scanner.nextLine();
            return Integer.parseInt(stringUserInput);
        } catch (NumberFormatException e) {
            System.out.println("올바른 값을 입력해주세요.");
            return inputMovieId();
        }
    }

    public static int inputMovieSchedule() {
        System.out.println("예매할 영화 시간을 선택하세요 (위에서부터 0번 순서입니다.)");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }

    public static boolean isQuitBuyingTicket() {
        System.out.println("영화 구매를 종료하시겠습니까? (O, X만 입력 가능합니다.)");
        String userInput = scanner.nextLine();
        try {
             validateUserInputs(userInput);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isQuitBuyingTicket();
        }
        return userInput.equals("X");
    }

    private static void  validateUserInputs(String userInput) {
        if (!userInput.equals("X") && !userInput.equals("O")) {
            throw new IllegalArgumentException("O, X만 입력할 수 있습니다.");
        }
    }

    public static int inputPoint() {
        System.out.println("사용할 포인트를 입력해주세요.");
        try {
            String stringUserInput = scanner.nextLine();
            return Integer.parseInt(stringUserInput);
        } catch (NoSuchElementException e) {
            throw new NumberFormatException("슷지민 입력해주세요.");
        }
    }

    public static String inputToUseCashOrCard() {
        System.out.println("현금과 카드 중 무엇으로 결제하시겠습니까? \n" +
                            "현금, 카드를 입력해주세요.");
        String userInput = scanner.nextLine();
        if (!userInput.equals("현금") && !userInput.equals("카드")) {
            throw new IllegalArgumentException("현금과 카드만 입력할 수 있습니다.");
        }
        return userInput;
    }
}
