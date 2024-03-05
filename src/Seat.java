public enum Seat {
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TENPLUS(10);

    private final int seat;


    Seat(int seat) {
        this.seat = seat;
    }

    public int getSeat() {
        return seat;
    }

}
