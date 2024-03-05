import java.time.LocalDate;

public class Renter {

    private int rentId;
    private String fullName;
    private String adress;
    private int zipcode;
    private String city;
    private int mobile;
    private int phone;
    private String email;
    private int licenseNumber;
    private LocalDate licenseDateOfIssue;


    public Renter(int rentId, String fullName, String adress, int zipcode,
                  String city, int mobile, int phone, String email, int licenseNumber,
                  LocalDate licenseDateOfIssue) {
        this.rentId = rentId;
        this.fullName = fullName;
        this.adress = adress;
        this.zipcode = zipcode;
        this.city = city;
        this.mobile = mobile;
        this.phone = phone;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.licenseDateOfIssue = licenseDateOfIssue;
    }

    public Renter(String fullName, String adress, int zipcode,
                  String city, int mobile, int phone, String email, int licenseNumber,
                  LocalDate licenseDateOfIssue) {
        this.fullName = fullName;
        this.adress = adress;
        this.zipcode = zipcode;
        this.city = city;
        this.mobile = mobile;
        this.phone = phone;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.licenseDateOfIssue = licenseDateOfIssue;
    }

    public int getRentId() {
        return rentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAdress() {
        return adress;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public int getMobile() {
        return mobile;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public LocalDate getLicenseDateOfIssue() {
        return licenseDateOfIssue;
    }
}
