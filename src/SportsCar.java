import java.time.LocalDate;

public class SportsCar extends Car {

    private int sportId;
    private Gear gear;
    private int hp;

    public SportsCar(int carId, String brand, String model, String fuelType,
                     String registrationNumber, LocalDate registrationYearMonth,
                     int drivinKm, int sportId, Gear gear, int hp) {
        super(carId, brand, model, fuelType, registrationNumber, registrationYearMonth, drivinKm);
        this.sportId = sportId;
        this.gear = gear;
        this.hp = hp;
    }

    public SportsCar(int sportId, Gear gear, int hp) {
        this.sportId = sportId;
        this.gear = gear;
        this.hp = hp;
    }

    public SportsCar() {

    }

    public int getSportId() {
        return sportId;
    }

    public Gear getGear() {
        return gear;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "SportsCar{" +
                "sportId=" + sportId +
                ", gear=" + gear +
                ", hp=" + hp +
                '}';
    }
}