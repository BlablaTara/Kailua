

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    MySQLConnection mySqlConnection;

    public Menu() {
        mySqlConnection = new MySQLConnection();
    }

    public static void main(String[] args) {
        new Menu().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            MenuChoice menuChoice = showMainMenu();
            switch (menuChoice) {
                case CREATE_CAR -> createCar();
                case SHOW_ALL_CARS -> showAllCars();
                case DELETE_CAR -> deleteCar();
                case SEARCH_CAR -> searchCar();
                case CREATE_RENTER -> createRenter();
                case SHOW_ALL_RENTERS -> showAllRenter();
                case DELETE_RENTER -> deleteRenter();
                case SEARCH_RENTER -> searchRenter();
                case QUIT -> running = false;
            }
        }
        mySqlConnection.closeConnection();
    }

    private void showAllCars() {
        ArrayList<Car> cars = mySqlConnection.getAllCars();
        printCars(cars);
    }
    private void showAllRenter() {
        ArrayList<Renter> renters = mySqlConnection.getAllRenters();
        printRenters(renters);
    }

    private void printCars(ArrayList<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }
    private void printRenters(ArrayList<Renter> renters) {
        for (Renter r : renters) {
            System.out.println(r);
        }
    }

    private void createCar() {
        Car car = userTypesCar();
        mySqlConnection.addCar(car);
        /*mySqlConnection.specifyCar(int Car.); TODO

         */
    }
    private void createRenter() {
        Renter renter = userTypesRenter();
        mySqlConnection.addRenter(renter);
    }

    private void deleteCar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDELETE CAR");
        System.out.println("Write the Car Id that you want to delete.");
        int userInput = scanner.nextInt();
        mySqlConnection.deleteCar(userInput);

    }
    private void deleteRenter(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDELETE RENTER");
        System.out.println("Write the Renter Id that you want to delete.");
        int userInput = scanner.nextInt();
        mySqlConnection.deleteRenter(userInput);

    }

    private void searchCar(){

    }

    private void searchRenter(){


    }

    private Car userTypesCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nINSERT NEW CAR");
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Fuel type: ");
        String fuelType = scanner.nextLine();
        System.out.print("Registration Number (Plate Number XX 12345): ");
        String registrationNumber = scanner.nextLine();
        System.out.println("Registration of the car: (dd-MM-yyyy)");
        String dateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate registrationYearMonth =LocalDate.parse(dateString, formatter);
        System.out.println("Km drivin: ");
        int drivinKm = scanner.nextInt();
        scanner.nextLine(); //ScannerBug

        Car car = new Car (brand, model, fuelType, registrationNumber, registrationYearMonth, drivinKm);
        return car;
    }

    private Car userTypesLuxuryCar(int carId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n LUXURY CAR:");
        System.out.print("Motor Cubic CM: ");
        int ccm = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.print("Gear Type: ");
        int gearChoice = scanner.nextInt();
        Gear gear = null;
        scanner.nextLine(); //ScannerBug
        switch (gearChoice){
            case 1 -> gear = Gear.AUTOMATGEAR;
            case 2 -> gear = Gear.MANUELTGEAR;
            case 3 -> gear = Gear.MANUELT_4;
            case 4 -> gear = Gear.MANUELT_5;
            case 5 -> gear = Gear.MANUELT_6;
            case 6 -> gear = Gear.MANUELT_7;
        }

        System.out.print("Aircondition: (1 = Yes/ 0 = No)");
        boolean aircon = scanner.nextBoolean();
        System.out.print("Speed Pilot: (1 = Yes/ 0 = No)");
        boolean speedPilot = scanner.nextBoolean();
        System.out.print("Leather Seats: (1 = Yes/ 0 = No)");
        boolean leatherSeats = scanner.nextBoolean();

        LuxuryCar luxuryCar = new LuxuryCar (carId, ccm, gear, aircon, speedPilot, leatherSeats);
        return luxuryCar;
    }

    private Renter userTypesRenter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCREATE NEW RENTER");
        System.out.print("Full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Adress: ");
        String adress = scanner.nextLine();
        System.out.println("Zipcode: ");
        int zipcode = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.println("Mobile Number: ");
        int mobile = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.println("Phone Number: ");
        int phone = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.println("License Number: ");
        int licenseNumber = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.println("License date of issue: (dd-MM-yyyy)");
        String licenseDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate licenseDateOfIssue =LocalDate.parse(licenseDate, formatter);

        Renter renter = new Renter(fullName, adress, zipcode, city, mobile, phone, email, licenseNumber, licenseDateOfIssue );
        return renter;
    }

   /* private Renter userTypesRenter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCREATE NEW RENTER");
        System.out.print("Full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Adress: ");
        String adress = scanner.nextLine();
        System.out.println("Zipcode: ");
        int zipcode = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.println("Rental Date Start: (dd-MM-yyyy)");
        String dateStart = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFrom =LocalDate.parse(dateStart, formatter);
        System.out.println("Rental Date End: (dd-MM-yyyy)");
        String dateEnd = scanner.nextLine();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTo =LocalDate.parse(dateEnd, formatter1);

        System.out.println("Max km to be driven: ");
        int maxKm = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.println("Km registreted at start: ");
        int kmStart = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.println("Registration Number: ");
        int registrationNumber = scanner.nextInt();
        scanner.nextLine(); //ScannerBug

        Contract contract = new Contract(fullName, adress, zipcode, city, mobile, phone, email, licenseNumber, licenseDateOfIssue );
        return contract;
    }

    */


    private MenuChoice showMainMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nMAIN MENU\n" +
                "1. Create Car\n" +
                "2. Show all Cars\n" +
                "3. Delete Car\n" +
                "4. Search Car\n" +
                "5. Update Car\n" +
                "\n" +
                "6. Create Renter\n" +
                "7. Show all Renters\n" +
                "8. Delete Renter\n" +
                "9. Search Renter\n" +
                "10. Update Renter\n" +
                "Q. Quit\n");

        String choice = in.nextLine().toLowerCase();
        MenuChoice menuChoice = null;
        switch (choice) {
            case "1" -> menuChoice = MenuChoice.CREATE_CAR;
            case "2" -> menuChoice = MenuChoice.SHOW_ALL_CARS;
            case "3" -> menuChoice = MenuChoice.DELETE_CAR;
            case "4" -> menuChoice = MenuChoice.SEARCH_CAR;
            case "5" -> menuChoice = MenuChoice.UPDATE_CAR;

            case "6" -> menuChoice = MenuChoice.CREATE_RENTER;
            case "7" -> menuChoice = MenuChoice.SHOW_ALL_RENTERS;
            case "8" -> menuChoice = MenuChoice.DELETE_RENTER;
            case "9" -> menuChoice = MenuChoice.SEARCH_RENTER;
            case "10" -> menuChoice = MenuChoice.UPDATE_RENTER;
            case "Q" -> menuChoice = MenuChoice.QUIT;
        }
        return menuChoice;
    }
}
