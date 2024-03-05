import java.time.LocalDate;

public class SportCar extends Car {

    private int sportId;
    private Gear gear;
    private int hp;

    public SportCar(int carId, String brand, String model, String fuelType,
                    String registrationNumber, LocalDate registrationYearMonth,
                    int drivinKm, int sportId, Gear gear, int hp) {
        super(carId, brand, model, fuelType, registrationNumber, registrationYearMonth, drivinKm);
        this.sportId = sportId;
        this.gear = gear;
        this.hp = hp;
    }

    public SportCar() {

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
}
