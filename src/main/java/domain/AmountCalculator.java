package domain;

public class AmountCalculator {

    private final MovieTickets movieTickets;
    private double ticketPrice;

    public AmountCalculator(MovieTickets movieTickets) {
        this.movieTickets = movieTickets;
    }

    public int getTicketPrice() {
        this.ticketPrice = movieTickets.getTotalAmount();
        return movieTickets.getTotalAmount();
    }

    public double showPrice() {
        return ticketPrice;
    }

    public double usePoint(int point) {
        validatePoint(point);
        ticketPrice = ticketPrice - point;
        return ticketPrice;
    }

    private void validatePoint(int point) {
        if (point <= 0) {
            throw new IllegalArgumentException("올바른 값을 입력해주세요.");
        }
        if (point > movieTickets.getTotalAmount()) {
            throw new IllegalArgumentException("포인트는 총 금액보다 작아야합니다.");
        }
    }

    public double discountCash(int amount) {
        double discountAmount = amount * 0.02;
        return amount - discountAmount;
    }

    public double discountCard(int amount) {
        double discountAMount = amount * 0.05;
        return amount - discountAMount;
    }
}
