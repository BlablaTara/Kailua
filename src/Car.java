import java.time.LocalDate;

public class Car {

    private int carId;
    private String brand;
    private String model;
    private String fuelType;
    private String registrationNumber;
    private LocalDate registrationYearMonth;
    private int drivinKm;

    public Car(int carId, String brand, String model, String fuelType,
               String registrationNumber, LocalDate registrationYearMonth, int drivinKm) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.registrationNumber = registrationNumber;
        this.registrationYearMonth = registrationYearMonth;
        this.drivinKm = drivinKm;
    }
    public Car(String brand, String model, String fuelType,
               String registrationNumber, LocalDate registrationYearMonth, int drivinKm) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.registrationNumber = registrationNumber;
        this.registrationYearMonth = registrationYearMonth;
        this.drivinKm = drivinKm;
    }

    public Car() {

    }

    public int getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public LocalDate getRegistrationYearMonth() {
        return registrationYearMonth;
    }

    public int getDrivinKm() {
        return drivinKm;
    }


    @Override
    public String toString() {
        return "Car|| " +
                " carId: " + carId + " |" +
                " brand: " + brand + " |" +
                " model: " + model  + " |" +
                " fuelType: " + fuelType  + " |" +
                " registrationNumber: " + registrationNumber  + " |" +
                " registrationYearMonth: " + registrationYearMonth + " |" +
                " drivinKm: " + drivinKm;
    }
}
