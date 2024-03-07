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
    private int driverLicenseNumber;
    private LocalDate licenseDateOfIssue;


    public Renter(int rentId, String fullName, String adress, int zipcode,
                  String city, int mobile, int phone, String email, int driverLicenseNumber,
                  LocalDate licenseDateOfIssue) {
        this.rentId = rentId;
        this.fullName = fullName;
        this.adress = adress;
        this.zipcode = zipcode;
        this.city = city;
        this.mobile = mobile;
        this.phone = phone;
        this.email = email;
        this.driverLicenseNumber = driverLicenseNumber;
        this.licenseDateOfIssue = licenseDateOfIssue;
    }

    public Renter(String fullName, String adress, int zipcode,
                  String city, int mobile, int phone, String email, int driverLicenseNumber,
                  LocalDate licenseDateOfIssue) {
        this.fullName = fullName;
        this.adress = adress;
        this.zipcode = zipcode;
        this.city = city;
        this.mobile = mobile;
        this.phone = phone;
        this.email = email;
        this.driverLicenseNumber = driverLicenseNumber;
        this.licenseDateOfIssue = licenseDateOfIssue;
    }

    public Renter(String fullName, String adress, int zipcode,
                  String city, int driverLicenseNumber) {
        this.fullName = fullName;
        this.adress = adress;
        this.zipcode = zipcode;
        this.city = city;
        this.driverLicenseNumber = driverLicenseNumber;
    }


    public Renter() {
    }

    public Renter(int rentId, String fullName, String adress, int zipcode, String city, int driverLicenseNumber) {

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

    public int getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public LocalDate getLicenseDateOfIssue() {
        return licenseDateOfIssue;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "rentId=" + rentId +
                ", fullName='" + fullName + '\'' +
                ", adress='" + adress + '\'' +
                ", zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", mobile=" + mobile +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", driverLicenseNumber=" + driverLicenseNumber +
                ", licenseDateOfIssue=" + licenseDateOfIssue +
                '}';
    }
}
