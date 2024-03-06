

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    MySQLConnection mySqlConnection;
    Scanner scanner = new Scanner(System.in);

    public Menu() {
        mySqlConnection = new MySQLConnection();
    }

    public static void main(String[] args) {
        new Menu().run();
    }
    /* ************************************ MENU STUFF ************************************///TODO
// RUN *
// SHOW MAIN MENU *

    private void run() {
        boolean running = true;

        while (running) {
            MenuChoice menuChoice = showMainMenu();
            switch (menuChoice) {
                case CREATE_CAR -> createCar();
                case SHOW_ALL_CARS -> showAllCars();
                case DELETE_CAR -> deleteCar();
                case SEARCH_CAR -> searchCar();
                case UPDATE_CAR -> updateCar();
                case CREATE_RENTER -> createRenter();
                case SHOW_ALL_RENTERS -> showAllRenter();
                case DELETE_RENTER -> deleteRenter();
                case SEARCH_RENTER -> searchRenter();
                case CREATE_CONTRACT -> createContract();
                case QUIT -> running = false;
            }
        }
        mySqlConnection.closeConnection();
    }

    private MenuChoice showMainMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nMAIN MENU\n" +
                " 1. Create Car\n" +
                " 2. Show all Cars\n" +
                " 3. Delete Car\n" +
                " 4. Search Car\n" +
                " 5. Update Car\n" +
                "\n" +
                " 6. Create Renter\n" +
                " 7. Show all Renters\n" +
                " 8. Delete Renter\n" +
                " 9. Search Renter\n" +
                "10. Update Renter\n\n" +
                "11. Create contract" +
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
            case "11" -> menuChoice = MenuChoice.CREATE_CONTRACT;
            case "Q" -> menuChoice = MenuChoice.QUIT;
        }
        return menuChoice;
    }

    /* ************************************ CAR STUFF ************************************///TODO
// CREATE CAR *
// SHOW ALL CARS *
// PRINT CARS *
// DELETE CAR *
// SEARCH CAR *
// UPDATE CAR *
// USER TYPES CAR *
// USER TYPES LUXERY CAR *
// USER TYPES FAMILY CAR *
// USER TYPES SPORTS CAR *

    private void createCar() {
        Car car = userTypesCar();
        mySqlConnection.addCar(car);
        int car_id = mySqlConnection.getLatestCarId(); // lavet en ny metode i MySqlConnection klassen som returnerer det sidst oprettede car_id
        if (car_id == 0) {

        }
        else {
            car_id += 0;
        }
        System.out.println(car_id);
        System.out.println("Specify Car: \n" + // har lavet mulighederne her i stedet for i specifyCar(); i MySqlConnection
                "1. Luxury Car." +
                "2. Family Car." +
                "3. Sports Car. ");

        int inputChoice = scanner.nextInt();
        scanner.nextLine(); // ScannerBug
        LuxuryCar luxuryCar = new LuxuryCar(); // så man bliver sendt til den metode som passer til den valgte underklasse
        FamilyCar familyCar = new FamilyCar(); // same as above
        SportsCar sportsCar = new SportsCar();    // same same again
        switch (inputChoice) {

            case 1 ->  luxuryCar = (LuxuryCar) userTypesLuxuryCar(car_id); // IntelliJ ville have (luxeryCar) castet.
            case 2 -> familyCar = (FamilyCar) userTypesFamilyCar(car_id);
            case 3 -> sportsCar = (SportsCar) userTypesSportsCar(car_id);
        }
        if (inputChoice == 1) {
            mySqlConnection.specifyCar( luxuryCar, car_id, inputChoice); // vi sender inputChoice med så switchen automatisk udføres i specifyCar(); i MySqlConnection
        }
        else if (inputChoice == 2) {
            mySqlConnection.specifyCar(familyCar, car_id, inputChoice); // same
        }
        else if (inputChoice == 3) {
            mySqlConnection.specifyCar(sportsCar, car_id, inputChoice); // same
        }
        else {
            System.out.println("Invalid number");
        }
    }

    private void showAllCars() {
        ArrayList<Car> cars = mySqlConnection.getAllCars();
        printCars(cars);
    }


    private void printCars(ArrayList<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    private void deleteCar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDELETE CAR");
        System.out.println("Write the Car Id that you want to delete.");
        int userInput = scanner.nextInt();
        mySqlConnection.deleteCar(userInput);

    }

    private void searchCar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the car id: ");
        int searchCar = scanner.nextInt();
        scanner.nextLine();
        mySqlConnection.searchForCar(searchCar);
    }

    public void updateCar() {
        System.out.println("What do you want to change?\n1. Brand\n2. Model\n3. Fuel Type\n4. Registration Number\n5. Registration Year and Month\n 6. Drivin km");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Type in the Car id of the car you want to update: ");
        int car_id = scanner.nextInt();
        scanner.nextLine();
        switch (number) {
            case 1 -> mySqlConnection.updateCar(car_id, "brand");
            case 2 -> mySqlConnection.updateCar(car_id, "model");
            case 3 -> mySqlConnection.updateCar(car_id, "fuel_type");
            case 4 -> mySqlConnection.updateCar(car_id, "registration_number");
            case 5 -> mySqlConnection.updateCar(car_id, "registration_year_month");
            case 6 -> mySqlConnection.updateCar(car_id, "drivin_km");
            default -> updateCar();
        }

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
        System.out.print("Gear Type:\n1. AutomatGear\n2. ManueltGear\n3. Manuelt_4\n" +
                "4. Manuelt_5\n5. Manuelt_6\n6. Manuelt_7");
        int gearChoice = scanner.nextInt();
        Gear gear = null;
        scanner.nextLine(); //ScannerBug
        switch (gearChoice){
            case 1 -> gear = Gear.automatgear;
            case 2 -> gear = Gear.manueltgear;
            case 3 -> gear = Gear.manueltgear_4;
            case 4 -> gear = Gear.manueltgear_5;
            case 5 -> gear = Gear.manueltgear_6;
            case 6 -> gear = Gear.manueltgear_7;
        }

        System.out.print("Aircondition: (Type 'true' or 'false'"); // ændret 0/1 til true/false
        boolean aircon = scanner.nextBoolean();
        System.out.print("Speed Pilot: (Type 'true' or 'false'"); // samme ændring
        boolean speedPilot = scanner.nextBoolean();
        System.out.print("Leather Seats: (Type 'true' or 'false')"); // samme ændring
        boolean leatherSeats = scanner.nextBoolean();

        LuxuryCar luxuryCar = new LuxuryCar (carId, ccm, gear, aircon, speedPilot, leatherSeats);
        return luxuryCar;
    }

    private Car userTypesFamilyCar(int carId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n FAMILY CAR:");
        System.out.print("Gear Type:\n1. AutomatGear\n2. ManueltGear\n3. Manuelt_4\n" +
                "4. Manuelt_5\n5. Manuelt_6\n6. Manuelt_7");
        int gearChoice = scanner.nextInt();
        Gear gear = null;
        scanner.nextLine(); //ScannerBug
        switch (gearChoice){
            case 1 -> gear = Gear.automatgear;
            case 2 -> gear = Gear.manueltgear;
            case 3 -> gear = Gear.manueltgear_4;
            case 4 -> gear = Gear.manueltgear_5;
            case 5 -> gear = Gear.manueltgear_6;
            case 6 -> gear = Gear.manueltgear_7;
        }

        System.out.print("Aircondition: (Type 'true' or 'false'"); // ændret 0/1 til true/false
        boolean aircon = scanner.nextBoolean();
        System.out.print("Speed Pilot: (Type 'true' or 'false'"); // samme ændring
        boolean speedPilot = scanner.nextBoolean();
        System.out.print("Number of Seats:\n(1) - 7 seats\n(2) - 8 seats\n(3) - 9 seats\n" +
                "(4) - 10+ seats");
        int seatChoice = scanner.nextInt();
        Seat seat = null;
        scanner.nextLine(); //ScannerBug
        switch (seatChoice){
            case 1 -> seat = Seat.SEVEN;
            case 2 -> seat = Seat.EIGHT;
            case 3 -> seat = Seat.NINE;
            case 4 -> seat = Seat.TENPLUS;

        }

        FamilyCar familyCar = new FamilyCar (carId, gear, aircon, speedPilot, seat);
        return familyCar;
    }

    private Car userTypesSportsCar(int carId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n SPORTS CAR:");
        System.out.print("Gear Type:\n1. AutomatGear\n2. ManueltGear\n3. Manuelt_4\n" +
                "4. Manuelt_5\n5. Manuelt_6\n6. Manuelt_7");
        int gearChoice = scanner.nextInt();
        Gear gear = null;
        scanner.nextLine(); //ScannerBug
        switch (gearChoice){
            case 1 -> gear = Gear.automatgear;
            case 2 -> gear = Gear.manueltgear;
            case 3 -> gear = Gear.manueltgear_4;
            case 4 -> gear = Gear.manueltgear_5;
            case 5 -> gear = Gear.manueltgear_6;
            case 6 -> gear = Gear.manueltgear_7;
        }

        System.out.print("HP: ");
        int hp = scanner.nextInt();
        scanner.nextLine(); //ScannerBug

        SportsCar sportsCar = new SportsCar(carId, gear, hp);
        return sportsCar;
    }


    /* ************************************ RENTER STUFF ************************************///TODO
// CREATE RENTER *
// SHOW ALL RENTERS *
// PRINT RENTERS *
// DELETE RENTER *
// SEARCH RENTER *
// USER TYPES RENTER *

    private void createRenter() {
        Renter renter = userTypesRenter();
        mySqlConnection.addRenter(renter);
    }

    private void showAllRenter() {
        ArrayList<Renter> renters = mySqlConnection.getAllRenters();
        printRenters(renters);
    }

    private void printRenters(ArrayList<Renter> renters) {
        for (Renter r : renters) {
            System.out.println(r);
        }
    }

    private void deleteRenter(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDELETE RENTER");
        System.out.println("Write the Renter Id that you want to delete.");
        int userInput = scanner.nextInt();
        mySqlConnection.deleteRenter(userInput);

    }

    private void searchRenter(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the renter id: ");
        int searchRenter = scanner.nextInt(); //Taget herfra så man kan se hvilken renter man gerne vil lave en kontrakt på.
        scanner.nextLine(); //TODO
        mySqlConnection.searchForRenter(searchRenter);
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
        System.out.println("Driver License Number: ");
        int licenseNumber = scanner.nextInt();
        scanner.nextLine(); //ScannerBug
        System.out.println("License date of issue: (dd-MM-yyyy)");
        String licenseDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate licenseDateOfIssue =LocalDate.parse(licenseDate, formatter);

        Renter renter = new Renter(fullName, adress, zipcode, city, mobile, phone, email, licenseNumber, licenseDateOfIssue );
        return renter;
    }



    /* ************************************ CONTRACT STUFF ************************************///TODO
// CREATE CONTRACT *
// USER TYPES CONTRACT *

    private void createContract() {
        System.out.println("NEW CONTRACT: ");
        System.out.println("Write an already existing renter ID: ");
        int searchRenter = scanner.nextInt();
        scanner.nextLine();
        mySqlConnection.searchForRenter(searchRenter);
        System.out.println("Renter found. BLAVBLA");
        Contract contract = new Contract();

        // contract = userTypesContract(rentId);
        contract = (Contract) userTypesContract(searchRenter);

        mySqlConnection.addContract(contract, searchRenter);


      /* System.out.println("Create contract on an Existing renter.\n" +
                "Write renter id: ");
        int rentId = scanner.nextInt(); //TODO
        int getRenterFromId = mySqlConnection.getRenterWithId();

        System.out.println("Renter ID found: " + getRenterFromId);


        if (searchRenter == getRenterFromId){
            Contract contract = new Contract();

           // contract = userTypesContract(rentId);
            contract = (Contract) userTypesContract(rentId);

            mySqlConnection.addContract(contract, rentId);
        } else{
            System.out.println("Renter ID not found");

        }

       */

    }


    private Contract userTypesContract(int rentId) {
       /* System.out.println("NEW CONTRACT: ");
        System.out.println("Write an already existing renter ID: ");
        int searchRenter = scanner.nextInt();
        scanner.nextLine();
        mySqlConnection.searchForRenter(searchRenter);
        System.out.println("Renter found.");

        */
      /*  System.out.println("Name: ");
        String fullName = scanner.nextLine();
        System.out.println("Adress: ");
        String adress = scanner.nextLine();
        System.out.println("zipcode: ");
        int zipcode = scanner.nextInt();
        scanner.nextLine();
        System.out.println("City: ");
        String city = scanner.nextLine();
        System.out.println("Driver license number: ");
        int driverLicenseNumber = scanner.nextInt();
        scanner.nextLine();

       */
        System.out.println("Rent car - Date from: ");
        String carRentDateFrom = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFrom = LocalDate.parse(carRentDateFrom, formatter);
        System.out.println("Rent car - Date to: ");
        String carRentDateTo = scanner.nextLine();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTo = LocalDate.parse(carRentDateTo, formatter1);
        System.out.println("Max kilometers: ");
        int maxKm = scanner.nextInt();
        scanner.nextLine(); // ScannerBug
        System.out.println("Kilometers before start: ");
        int kmStart = scanner.nextInt();
        scanner.nextLine();// ScannerBug
        System.out.println("Registration number: ");
        String registrationNumber = scanner.nextLine();

        Contract contract = new Contract(rentId, dateFrom,
                dateTo, maxKm, kmStart, registrationNumber);
        return contract;

    }



}
