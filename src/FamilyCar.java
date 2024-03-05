import java.time.LocalDate;

public class FamilyCar extends Car{

    private int famId;
    private Gear gear;
    private boolean aircondition;
    private boolean speedPilot;
    private Seat seat;

    public FamilyCar(int carId, String brand, String model, String fuelType, String registrationNumber,
                     LocalDate registrationYearMonth, int drivinKm, int famId, Gear gear,
                     boolean aircondition, boolean speedPilot, Seat seat) {
        super(carId, brand, model, fuelType, registrationNumber, registrationYearMonth, drivinKm);
        this.famId = famId;
        this.gear = gear;
        this.aircondition = aircondition;
        this.speedPilot = speedPilot;
        this.seat = seat;
    }

    public FamilyCar() {

    }

    public int getFamId() {
        return famId;
    }

    public Gear getGear() {
        return gear;
    }

    public boolean isAircondition() {
        return aircondition;
    }

    public boolean isSpeedPilot() {
        return speedPilot;
    }

    public Seat getSeat() {
        return seat;
    }
}
