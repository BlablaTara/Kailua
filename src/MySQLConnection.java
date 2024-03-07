import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MySQLConnection {
    String database = "jdbc:mysql://localhost:3306/kailua_car_rental";
    String username = "XXX";
    String password = "XXX";
    private Connection connection = null;

    public MySQLConnection() {
        createConnection();
    }

    private void createConnection() {
        if (connection != null)
            return; // If connection already created, just return that to ensure singleton

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(database, username, password);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
        connection = null;
    }


    /* ************************************ CAR STUFF ************************************/ //TODO
    // ADD CAR
    // SPECIFY CAR
    // GET ALL CARS
    // GET LUXURY CAR
    // DELETE CAR
    // SEARCH FOR CAR
    // UPDATE CAR
    // GET LATEST CAR ID
    // CREATE LUXURY CAR
    // CREATE FAMILY CAR
    // CREATE SPORTS CAR

    public void addCar(Car car) {
        try {
            String query = "INSERT INTO car (brand, model, fuel_type, registration_number, registration_year_month, drivin_km) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getFuelType());
            preparedStatement.setString(4, car.getRegistrationNumber());
            preparedStatement.setDate(5, Date.valueOf(car.getRegistrationYearMonth()));
            preparedStatement.setDouble(6, car.getDrivinKm());

            preparedStatement.executeUpdate();

            System.out.println("Car added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void specifyCar(Car carType, int car_id, int inputChoice){ // fjernet 'sout' herfra og sat den i createCar(); i Menu
        switch (inputChoice) {

            case 1 -> createLuxuryCar(carType, car_id);
            case 2 -> createFamilyCar(carType, car_id);
            case 3 -> createSportsCar(carType, car_id);
        }

    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM car;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int carId = rs.getInt("car_id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                String fuelType = rs.getString("fuel_type");
                String registrationNumber = rs.getString("registration_number");
                LocalDate registrationYearMonth = rs.getDate("registration_year_month").toLocalDate();
                double drivinKm = rs.getDouble("drivin_km");
                Car car = new Car (carId, brand, model, fuelType, registrationNumber, registrationYearMonth, drivinKm);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public ArrayList<LuxuryCar> getLuxuryCar() {
        ArrayList<LuxuryCar> luxuryCars = new ArrayList<>();
        String query = "SELECT l.lux_id, c.brand, c.model, c.fuel_type, c.registration_number, c.registration_year_month, c.drivin_km, \n" +
                "\t\tl.ccm, l.gear, l.aricondition, l.speedpilot, l.leatherseats\n" +
                "FROM Car c JOIN Luxery l\n" +
                "WHERE c.car_id = l.car_id;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int luxId = rs.getInt("lux_id");

                String brand = rs.getString("brand");
                String model = rs.getString("model");
                String fuelType = rs.getString("fuel_type");
                String registrationNumber = rs.getString("registration_number");
                LocalDate registrationYearMonth = rs.getDate("registration_year_month").toLocalDate();
                double drivinKm = rs.getInt("drivin_km");
                double ccm = rs.getInt("ccm");
                String gearString = rs.getString("gear");
                Gear gear = Gear.valueOf(gearString);
                boolean aircon = rs.getBoolean("aricondition");
                boolean speedPilot = rs.getBoolean("speedpilot");
                boolean leatherSeats = rs.getBoolean("leatherseats");

                LuxuryCar luxuryCar = new LuxuryCar(brand, model, fuelType, registrationNumber, registrationYearMonth,
                        drivinKm, luxId, ccm, gear, aircon, speedPilot, leatherSeats);

                luxuryCars.add(luxuryCar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return luxuryCars;
    }



    public void deleteCar(int car_id) {
        String query = "DELETE FROM car WHERE car_id = ?;";

        try {
            PreparedStatement pS = connection.prepareStatement(query);
            pS.setInt(1, car_id);
            pS.executeUpdate();

            System.out.println("Car deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchForCar(int carId) {
        String searchQuery = "SELECT * FROM car WHERE car_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setInt(1, carId);


            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    carId = rs.getInt("car_id");
                    String brand = rs.getString("brand");
                    String model = rs.getString("model");
                    String fuelType = rs.getString("fuel_type");
                    String registrationnumber = rs.getString("registration_number");
                    LocalDate registrationYearMonth = rs.getDate("registration_year_month").toLocalDate();
                    double drivinKm = rs.getDouble("drivin_km");
                    Car car = new Car(carId, brand, model, fuelType, registrationnumber, registrationYearMonth, drivinKm);

                    System.out.println("Here is the car you have searched for: \n" + car);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCar(int car_id, String update) {
        Scanner scanner = new Scanner(System.in);
        if (update.equalsIgnoreCase("brand")) {
            try {
                System.out.println("Enter the new brand name: ");
                String newBrand = scanner.nextLine();

                String query = "UPDATE car SET brand = ? WHERE car_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newBrand);
                preparedStatement.setInt(2, car_id);

                preparedStatement.executeUpdate();
                System.out.println("Car updated successfully!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (update.equalsIgnoreCase("model")) {
            try {
                System.out.println("Enter the new model: ");
                String newModel = scanner.nextLine();

                String query = "UPDATE car SET model = ? WHERE car_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newModel);
                preparedStatement.setInt(2, car_id);
                preparedStatement.executeUpdate();
                System.out.println("Car updated successfully");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (update.equalsIgnoreCase("fuel_type")) {
            try {
                System.out.println("Enter the new fuel type: ");
                String newFuelType = scanner.nextLine();
                String query = "UPDATE car SET fuel_type = ? WHERE car_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newFuelType);
                preparedStatement.setInt(2, car_id);
                preparedStatement.executeUpdate();
                System.out.println("Car updated successfully");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        else if (update.equalsIgnoreCase("registration_number")) {
            try {
                System.out.println("Enter a new registration number: ");
                String newRegistrationNumber = scanner.nextLine();
                String query = "UPDATE car SET registration_number = ? WHERE car_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newRegistrationNumber);
                preparedStatement.setInt(2, car_id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        else if (update.equalsIgnoreCase("registration_yeah_month")) {
            try {
                System.out.println("Enter the new date of registration year and month: ");

                String newRegistrationYearMonthStr = scanner.nextLine();
                LocalDate newRegistrationYearMonth = LocalDate.parse(newRegistrationYearMonthStr);
                String query = "UPDATE car SET registration_year_month = ? WHERE car_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setObject(1, newRegistrationYearMonth);
                preparedStatement.setInt(2, car_id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (update.equalsIgnoreCase("drivin_km")) {
            try {
                System.out.println("Enter the new driving km: ");
                double newDrivinKm = scanner.nextDouble();
                String query = "UPDATE car SET drivin_km = ? WHERE car_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDouble(1, newDrivinKm);
                preparedStatement.setInt(2, car_id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Invalid input try again.");
        }
    }

    public int getLatestCarId() { // Ny metode som bruges i createCar(); i Menu så vi kan få det sidste Car_id.
        try {
            String query = "SELECT car_id FROM car ORDER BY car_id DESC LIMIT 1";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    return resultSet.getInt("car_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }


    private void createLuxuryCar(Car carType, int carId) {
        LuxuryCar luxuryCar = (LuxuryCar) carType;
        try {
            String query = "INSERT INTO Luxery (car_id, ccm, gear, aricondition, speedpilot, leatherseats) VALUES (?, ?, ?, ?, ?, ?)"; // A'RI'CONDITION SKAL RETTES I SQL. speed_pilot er rettet til speedpilot
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, carId);
            preparedStatement.setDouble(2, luxuryCar.getCcm());
            preparedStatement.setString(3, luxuryCar.getGear().name());
            preparedStatement.setBoolean(4, luxuryCar.isAircondition());
            preparedStatement.setBoolean(5, luxuryCar.isSpeedPilot());
            preparedStatement.setBoolean(6, luxuryCar.isLeatherSeats());

            preparedStatement.executeUpdate();

            System.out.println("Luxury Car added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void createFamilyCar(Car carType, int carId) {
        FamilyCar familyCar = (FamilyCar) carType;
        try {
            String query = "INSERT INTO Family (car_id, gear, aricondition, speedpilot, seats) VALUES (?, ?, ?, ?, ?)"; // A'RI'CONDITION SKAL RETTES I SQL. speed_pilot er rettet til speedpilot
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, carId);
            preparedStatement.setString(2, familyCar.getGear().name());
            preparedStatement.setBoolean(3, familyCar.isAircondition());
            preparedStatement.setBoolean(4, familyCar.isSpeedPilot());
            preparedStatement.setString(5, familyCar.getSeat().name());

            preparedStatement.executeUpdate();

            System.out.println("Family Car added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createSportsCar(Car carType, int carId) {
        SportsCar sportsCar = (SportsCar) carType;
        try {
            String query = "INSERT INTO Sport (car_id, gear, hp) VALUES (?, ?, ?)"; // A'RI'CONDITION SKAL RETTES I SQL. speed_pilot er rettet til speedpilot
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, carId);
            preparedStatement.setString(2, sportsCar.getGear().name());
            preparedStatement.setDouble(3, sportsCar.getHp());

            preparedStatement.executeUpdate();

            System.out.println("Sports Car added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /* ************************************ RENTER STUFF ************************************///TODO
    // ADD RENTER *
    // GET ALL RENTERS *
    // DELETE RENTER *
    // SEARCH FOR RENTER *

    public void addRenter(Renter renter) {
        try {
            String query = "INSERT INTO renter (fullname, adress, zipcode, city, mobil, phone, e_mail, driver_license_number, license_date_of_issue ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, renter.getFullName());
            preparedStatement.setString(2, renter.getAdress());
            preparedStatement.setInt(3, renter.getZipcode());
            preparedStatement.setString(4, renter.getCity());
            preparedStatement.setInt(5, renter.getMobile());
            preparedStatement.setInt(6, renter.getPhone());
            preparedStatement.setString(7, renter.getEmail());
            preparedStatement.setInt(8, renter.getDriverLicenseNumber());
            preparedStatement.setDate(9, Date.valueOf(renter.getLicenseDateOfIssue()));

            preparedStatement.executeUpdate();

            System.out.println("Renter added successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
    }

    public ArrayList<Renter> getAllRenters() {
        ArrayList<Renter> renters = new ArrayList<>();
        String query = "SELECT * FROM renter;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int rentId = rs.getInt("rent_id");
                String fullName = rs.getString("fullname");
                String adress = rs.getString("adress");
                int zipcode = rs.getInt("zipcode");
                String city = rs.getString("city");
                int mobile = rs.getInt("mobil");
                int phone = rs.getInt("phone");
                String email = rs.getString("e_mail");
                int driverLicenseNumber = rs.getInt("driver_license_number");
                LocalDate licenseDateOfIssue = rs.getDate("license_date_of_issue").toLocalDate();
                Renter renter = new Renter(rentId, fullName, adress, zipcode, city, mobile, phone, email, driverLicenseNumber, licenseDateOfIssue );
                renters.add(renter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return renters;
    }


    public void deleteRenter(int renter_id){
        String query = "DELETE FROM Renter WHERE renter_id = ?;";

        try {
            PreparedStatement pS = connection.prepareStatement(query);
            pS.setInt(1, renter_id);
            pS.executeUpdate();

            System.out.println("Renter deleted successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
    }

    public void searchForRenter(int rentId) {
        String searchQuery = "SELECT * FROM renter WHERE rent_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setInt(1, rentId);


            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    rentId = rs.getInt("rent_id");
                    String fullName = rs.getString("fullname");
                    String adress = rs.getString("adress");
                    int zipcode = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    int mobile = rs.getInt("mobil");
                    int phone = rs.getInt("phone");
                    String email = rs.getString("e_mail");
                    int driverLicenseNumber = rs.getInt("driver_license_number");
                    LocalDate licenseDateOfIssue = rs.getDate("license_date_of_issue").toLocalDate();
                    Renter renter = new Renter(rentId, fullName, adress, zipcode, city, mobile, phone, email, driverLicenseNumber, licenseDateOfIssue );

                    System.out.println("Here is the renter you have searched for: \n" + renter);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



/* ************************************ CONTRACT STUFF ************************************///TODO
// ADD CONTRACT *
// GET FULL CONTRACTS *


    public void addContract (Renter renter, int rentId) {
        Contract contract = (Contract) renter;
        try {
            String query = "INSERT INTO Contract (rent_id, date_from, date_to, max_km, km_start, registration_number) VALUES (?, ?, ?, ?, ?, ?)"; //TODO
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rentId);
            preparedStatement.setObject(2, contract.getDateFrom());
            preparedStatement.setObject(3, contract.getDateTo());
            preparedStatement.setDouble(4, contract.getMaxKm());
            preparedStatement.setDouble(5, contract.getKmStart());
            preparedStatement.setString(6, contract.getRegistrationNumber());

            preparedStatement.executeUpdate();

            System.out.println("Contract successfully created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<Contract> getFullContracts() {
        ArrayList<Contract> fullContracs = new ArrayList<>();
        String query = "SELECT c.contract_id, r.fullname, r.adress, r.zipcode, r.city, r.driver_license_number, \n" +
                "\t\t c.date_from, c.date_to, c.max_km, c.km_start, c.registration_number\n" +
                "FROM Renter r JOIN Contract c \n" +
                "WHERE r.rent_id = c.rent_id;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int contractId = rs.getInt("contract_id");

                String fullName = rs.getString("fullname");
                String adress = rs.getString("adress");
                int zipcode = rs.getInt("zipcode");
                String city = rs.getString("city");
                int driverLicenseNumber = rs.getInt("driver_license_number");

                LocalDate dateFrom = rs.getDate("date_from").toLocalDate();
                LocalDate dateTo = rs.getDate("date_to").toLocalDate();
                double maxKm= rs.getInt("max_km");
                double kmStart = rs.getInt("km_start");
                String registrationNumber = rs.getString("registration_number");

                Contract contract = new Contract(fullName, adress, zipcode, city, driverLicenseNumber, contractId,
                        dateFrom, dateTo, maxKm, kmStart, registrationNumber);

                fullContracs.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullContracs;
    }


}
