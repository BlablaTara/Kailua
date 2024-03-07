import java.time.LocalDate;

public class Contract extends Renter {

    private int contractId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double maxKm;
    private double kmStart;
    private String registrationNumber;

    public Contract(int rentId, String fullName, String adress, int zipcode, String city,
                    int mobile, int phone, String email, int driverLicenseNumber, LocalDate licenseDateOfIssue,
                    int contractId, LocalDate dateFrom, LocalDate dateTo, double maxKm, double kmStart,
                    String registrationNumber) {
        super(rentId, fullName, adress, zipcode, city, mobile, phone, email, driverLicenseNumber, licenseDateOfIssue);
        this.contractId = contractId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.maxKm = maxKm;
        this.kmStart = kmStart;
        this.registrationNumber = registrationNumber;
    }

    public Contract(String fullName, String adress, int zipcode, String city,
                    int driverLicenseNumber,
                    int contractId, LocalDate dateFrom, LocalDate dateTo, double maxKm, double kmStart,
                    String registrationNumber) {
        super(fullName, adress, zipcode, city, driverLicenseNumber);
        this.contractId = contractId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.maxKm = maxKm;
        this.kmStart = kmStart;
        this.registrationNumber = registrationNumber;
    }


    public Contract(int contractId, LocalDate dateFrom, LocalDate dateTo, double maxKm, double kmStart,
                    String registrationNumber) {
        this.contractId = contractId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.maxKm = maxKm;
        this.kmStart = kmStart;
        this.registrationNumber = registrationNumber;
    }

    public Contract() {

    }

    public int getContractId() { // TODO gettere og settere lavet
        return contractId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public double getMaxKm() {
        return maxKm;
    }

    public double getKmStart() {
        return kmStart;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return "Contract|| " +
                " contractId: " + contractId + " |" +
                " dateFrom: " + dateFrom + " |" +
                " dateTo: " + dateTo + " |" +
                " maxKm: " + maxKm + " |" +
                " kmStart: " + kmStart + " |" +
                " registrationNumber: " + registrationNumber;
    }


}
