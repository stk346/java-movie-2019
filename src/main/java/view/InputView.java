package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        System.out.println("## 예약할 영화를 선택하세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("숫자만 입력할 수 있습니다.");
            return inputMovieId();
        }
    }

    public static int showEnterScheduleMessageAndGet() {
        System.out.println("예매할 스케쥴을 선택해주세요. (위에서 부터 0번 순입니다.)");
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("숫자만 입력할 수 있습니다.");
            return showEnterScheduleMessageAndGet();
        }
    }

    public static int showEnterCapacityMessageAndGet() {
        System.out.println("예매할 인원 수를 입력해주세요.");
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("숫자만 입력할 수 있습니다.");
            return showEnterCapacityMessageAndGet();
        }
    }

    public static boolean ifContinueToReserveMessageAndGet() {
        System.out.println("영화 예매를 계속하시겠습니까? (y, n으로 입력해주세요.)");
        String userInput = scanner.nextLine();
        if (!userInput.equals("y") && !userInput.equals("n")) {
            throw new IllegalArgumentException("y, n으로 입력해주세요.");
        }
        if (userInput.equals("y")) {
            return true;
        }
        return false;
    }

    public static double showUsePointMessageAndGet() {
        System.out.println("얼마의 포인트를 사용하시겠습니까?");
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("숫자만 입력할 수 있습니다.");
            return showUsePointMessageAndGet();
        }
    }

    public static String askPaymentMethodAndGet() {
        System.out.println("현금과 카드 중 무엇으로 결제하시겠습니까? (현금, 카드만 입력할 수 있습니다.)");
        return scanner.nextLine();
    }
}
