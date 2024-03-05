import java.time.LocalDate;

public class Contract extends Renter {

    private int contractId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int maxKm;
    private int kmStart;
    private String registrationNumber;

    public Contract(int rentId, String fullName, String adress, int zipcode, String city,
                    int mobile, int phone, String email, int licenseNumber, LocalDate licenseDateOfIssue,
                    int contractId, LocalDate dateFrom, LocalDate dateTo, int maxKm, int kmStart,
                    String registrationNumber) {
        super(rentId, fullName, adress, zipcode, city, mobile, phone, email, licenseNumber, licenseDateOfIssue);
        this.contractId = contractId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.maxKm = maxKm;
        this.kmStart = kmStart;
        this.registrationNumber = registrationNumber;
    }
}
