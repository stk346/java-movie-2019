package domain;

public class PriceCalculator {
    MovieTicket movieTicket;

    public PriceCalculator(MovieTicket movieTicket) {
        this.movieTicket = movieTicket;
    }

    public double getDiscountedPrice(double ticketPrice, String cashOrCard) {
        if (cashOrCard.equals("현금")) {
            return ticketPrice * 0.98;
        }
        if (cashOrCard.equals("카드")) {
            return ticketPrice * 0.95;
        }
        throw new IllegalArgumentException("현금, 카드만 입력할 수 있습니다.");
    }

    public double usePoint(int ticketPrice, double point) {
        if (point < 0 || point > ticketPrice) {
            throw new IllegalArgumentException("올바른 포인트를 입력해주세요.");
        }
        return ticketPrice - point;
    }

    public int getTicketPrice() {
        int totalPrice = 0;
        for (SelectedMovie selectedMovie : movieTicket.getMovies()) {
            totalPrice += selectedMovie.getPrice() * selectedMovie.getReservedCount();
        }
        return totalPrice;
    }
}
