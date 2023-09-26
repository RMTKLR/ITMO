package input;

import Data.Coordinates;
import Data.Flat;
import Data.House;
import Data.Transport;
import client.Functions;
import connection.Request;

import java.time.LocalDateTime;
import java.util.Scanner;

public class InputData {
    private Scanner scanner;

    public InputData(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String readName() {
        String name;
        for (; ; ) {
            System.out.println("Enter the name of flat : ");
            name = scanner.nextLine();
            if (name.equals("") || name == null) {
                System.err.println("NO such name");
                continue;
            }
            return name;
        }
    }

    public Double readCoordinateX() {
        double x1;
        for (; ; ) {
            System.out.println("Enter coordinate x :");
            String x = scanner.nextLine();
            try {
                if (x.equals("") || x == null) {
                    System.err.println("can't be empty");
                    continue;
                }
                x1 = Double.parseDouble(x);
                if (x1 > 171) {
                    System.err.println("Must be less than 171");
                    continue;
                }
                return x1;
            } catch (NumberFormatException exception) {
                System.err.println("it should be an integer!!");
            }
        }
    }

    public Double readCoordinateY() {
        double y1;
        for (; ; ) {
            System.out.println("Enter coordinate y :");
            String y = scanner.nextLine();
            try {
                if (y.equals("") || y == null) {
                    System.err.println("can't be empty");
                    continue;
                }
                y1 = Double.parseDouble(y);
                if (y1 > 269) {
                    System.err.println("Must be less than 269");
                    continue;
                }
                return y1;
            } catch (NumberFormatException exception) {
                System.err.println("it should be an integer!! ");
            }
        }
    }

    public Coordinates readCoordinate() {
        return new Coordinates(readCoordinateX().longValue(), readCoordinateY().floatValue());
    }

    public LocalDateTime readLocalDateTime() {
        return LocalDateTime.now();
    }

    public Long readArea() {
        for (; ; ) {
            long area;
            System.out.println("Enter area : ");
            try {
                area = Long.parseLong(scanner.nextLine());
                if (area > 681 || area == 0) {
                    System.err.println("Invalid number :(  area>681||area==0");
                    continue;
                }
                return area;
            } catch (NumberFormatException exception) {
                System.err.println("it should be interger");
            }
        }
    }

    public int readNumberOfRooms() {
        for (; ; ) {
            int room;
            System.out.println("Enter number of rooms : ");
            try {
                room = Integer.parseInt(scanner.nextLine());
                if (room > 20 || room == 0) {
                    System.err.println("Invalid");
                    continue;
                }
                return room;
            } catch (NumberFormatException exception) {
                System.err.println("it should be integer ");
            }
        }
    }

    public long readNumberOfBathrooms() {
        for (; ; ) {
            long number;
            System.out.println("Enter number of bathrooms : ");
            try {
                number = Long.parseLong(scanner.nextLine());
                if (number <= 0) {
                    System.err.println("Can't be 0");
                    continue;
                }
                return number;
            } catch (NumberFormatException exception) {
                System.err.println("it should be integer!!");
            }
        }
    }

    public boolean readNew() {
        for (; ; ) {
            boolean New;
            System.out.println("Is this flat new (true,false) : ");
            String No = scanner.nextLine();
            try {
                if (!No.equals("true") && !No.equals("false")) {
                    System.err.println("should be true or false");
                    continue;
                }
                New = Boolean.parseBoolean(No);
                return New;
            } catch (IllegalArgumentException exception) {
                System.err.println("should true of false!!!");
            }
        }
    }

    public Transport readTransport() {
        for (; ; ) {
            System.out.println("Enter Transport (FEW,LITTLE,NORMAL,ENOUGH): ");
            String input = scanner.nextLine().toUpperCase();
            Transport n;
            try {
                if (input.equals("")) continue;
                n = Transport.valueOf(input);
                return n;
            } catch (IllegalArgumentException exception) {
                System.out.println("invalid input!! ");
            }
        }
    }

    public String readHouseName() {
        for (; ; ) {
            System.out.println("Enter house 's name : ");
            String name = scanner.nextLine();
            if (name.equals("") && name != null) {
                System.err.println("invalid input!");
                continue;
            }
            return name;
        }
    }

    public long readHouseYear() {
        for (; ; ) {
            System.out.println("Enter house 's year : ");
            String y = scanner.nextLine();
            long year;
            try {
                year = Long.parseLong(y);
                if (year <= 0) {
                    System.err.println("Must be greater than 0");
                    continue;
                }
                return year;
            } catch (NumberFormatException exception) {
                System.err.println("Invalid input");
            }
        }
    }

    public Integer readNumberOfFloors() {
        for (; ; ) {
            System.out.println("Enter number of floors : ");
            String number = scanner.nextLine();
            try {
                Integer n = Integer.parseInt(number);
                if (n <= 0) {
                    System.err.println("Must be greater than 0");
                    continue;
                }
                return n;
            } catch (NumberFormatException exception) {
                System.err.println("invalid input");
            }
        }
    }

    public House readHouse() {
        return new House(readHouseName(), readHouseYear(), readNumberOfFloors().longValue());
    }

    public Flat readFlat() {
        return new Flat(readName(), readCoordinate(), readLocalDateTime(), readArea().floatValue(), readNumberOfRooms(), readNumberOfBathrooms(), readNew(), readTransport(), readHouse());
    }

    public Request readCommand(String user, String pswd) {
        System.out.println("Enter a command (help to get help) : ");
        String command = scanner.nextLine();
      //  System.out.println("Executing command: " + command);
        String arr[] = new String[2];
        String args = "";
        Flat flat = null;
        House house = null;
        if (command.contains(" ")) {
            arr = command.split(" ", 2);
            command = arr[0];
            args = arr[1];
        }
        //if command add,update,isert, add_if_max adding username and password
        if (command.equals("add") || command.contains("update") || command.contains("insert_at") || command.equals("add_if_max")) {
            flat = readFlat();
        }
        if (command.equals("remove_any_by_house")) {
            house = readHouse();
        }
        //if command addUser adding username and password
        if (command.equals("addUser")){
            System.out.println("USER NAME:");
            user = scanner.nextLine();
            System.out.println("PASSWORD:");
            String pswd1 = scanner.nextLine();
            pswd = Functions.hashPasswordMD2(pswd1);
        }
        //if command login adding username and password
        if (command.equals("login")){
            System.out.println("USER NAME:");
            user = scanner.nextLine();
            System.out.println("PASSWORD:");
            String pswd1 = scanner.nextLine();
            pswd = Functions.hashPasswordMD2(pswd1);
        }

        Request request = new Request(command, args, flat, house, user, pswd);
        return request;
    }
}
