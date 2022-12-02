package domain;

public class AmountCalculator {

    private final MovieTickets movieTickets;
    private double ticketPrice;

    public AmountCalculator(MovieTickets movieTickets) {
        this.movieTickets = movieTickets;
        this.ticketPrice = movieTickets.getTotalAmount();
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


    public void discountCash() {
        double discountAmount = ticketPrice * 0.02;
        ticketPrice = ticketPrice - discountAmount;
    }

    public void discountCard() {
        double discountAMount = ticketPrice * 0.05;
        ticketPrice =  ticketPrice - discountAMount;
    }
}
