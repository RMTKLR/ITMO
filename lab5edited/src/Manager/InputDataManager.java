package Manager;

import Manager.CollectionManager;
import data.Coordinates;
import data.Flat;
import data.House;
import data.Transport;
import java.time.LocalDateTime;
import java.util.Scanner;

public class InputDataManager {
    private Scanner scanner;
    private CollectionManager collectionManager;

    public InputDataManager(CollectionManager collectionManager){
        this.scanner = new Scanner(System.in);
        this.collectionManager = collectionManager;
    }

    public String readName(){
        String name;
        while(true){
            System.out.println("Enter the name of flat: ");
            name = scanner.nextLine();
            if (name.equals("") || name == null){
                System.err.println("No such name!!!");
                continue;
            }
            return name;
        }
    }

    public Double readCoordinateX(){
        double x1;
        for (;;){
            System.out.println("Enter coordinate X: ");
            String x = scanner.nextLine();
            try{
                if(x.equals("") || x == null){
                    System.out.println("can't be empty!!");
                    continue;
                }
                x1 = Double.parseDouble(x);
                if (x1 > 171) {
                    System.out.println("Must be less than 171!!");
                    continue;
                }
                return x1;
            }catch (NumberFormatException numberFormatException){
                System.out.println("it should be an integer!!");
            }
        }
    }

    public Double readCoordinateY(){
        Double y1;
        while (true){
            System.out.println("Enter coordinate Y: ");
            String y = scanner.nextLine();
            try{
                if (y.equals("") || y == null)  {
                    System.out.println("Can't be empty!!!");
                    continue;
                }
                y1 = Double.parseDouble(y);
                if (y1 > 269){
                    System.out.println("Must be less than 269!!");
                    continue;
                }
                return y1;
            }catch (NumberFormatException numberFormatException){
                System.out.println("It should be an integer!!");
            }
        }
    }

    public Coordinates readCoordinate () {return new Coordinates(readCoordinateX(), readCoordinateY());}
    public LocalDateTime readLocalDateTime () {return LocalDateTime.now();}

    public Long readArea(){
        for(;;){
            long area;
            System.out.println("Enter area : ");
            try{
                area = Long.parseLong(scanner.nextLine());
                if(area>681||area==0) {
                    System.err.println("Invalid number :(  area>681||area==0");
                    continue;
                }
                return area;
            }catch (NumberFormatException exception){
                System.err.println("it should be interger");
            }
        }
    }
    public int readNumberOfRooms(){
        for(;;){
            int room;
            System.out.println("Enter number of rooms : ");
            try{
                room = Integer.parseInt(scanner.nextLine());
                if(room>20||room==0){
                    System.err.println("Invalid");
                    continue;
                }
                return room;
            }catch (NumberFormatException exception){
                System.err.println("it should be integer ");
            }
        }
    }
    public long readNumberOfBathrooms(){
        for(;;){
            long number;
            System.out.println("Enter number of bathrooms : ");
            try{
                number = Long.parseLong(scanner.nextLine());
                if(number<=0){
                    System.err.println("Can't be 0");
                    continue;
                }
                return number;
            }catch (NumberFormatException exception){
                System.err.println("it should be integer!!");
            }
        }
    }
    public boolean readNew(){
        for(;;){
            boolean New;
            System.out.println("Is this flat new (true,false) : ");
            String No = scanner.nextLine();
            try{
                if(!No.equals("true")&&!No.equals("false")) {
                    System.err.println("should be true or false");
                    continue;
                }
                New = Boolean.parseBoolean(No);
                return New;
            }catch (IllegalArgumentException exception){
                System.err.println("should true of false!!!");
            }
        }
    }
    public Transport readTransport(){
        for(;;){
            System.out.println("Enter Transport (FEW,LITTLE,NORMAL,ENOUGH): ");
            String input = scanner.nextLine().toUpperCase();
            Transport n;
            try {
                if(input.equals(""))continue;
                n = Transport.valueOf(input);
                return n;
            }catch (IllegalArgumentException exception){
                System.out.println("invalid input!! ");
            }
        }
    }
    public String readHouseName(){
        for(;;){
            System.out.println("Enter house 's name : ");
            String name = scanner.nextLine();
            if(name.equals("") && name != null){
                System.err.println("invalid input!");
                continue;
            }
            return name;
        }
    }
    public long readHouseYear(){
        for(;;){
            System.out.println("Enter house 's year : ");
            String y = scanner.nextLine();
            long year;
            try{
                year = Long.parseLong(y);
                if(year<=0){
                    System.err.println("Must be greater than 0");
                    continue;
                }
                return year;
            }catch (NumberFormatException exception){
                System.err.println("Invalid input");
            }
        }
    }
    public Integer readNumberOfFloors(){
        for(;;){
            System.out.println("Enter number of floors : ");
            String number = scanner.nextLine();
            try{
                Integer n = Integer.parseInt(number);
                if(n<=0){
                    System.err.println("Must be greater than 0");
                    continue;
                }
                return n;
            }catch (NumberFormatException exception){
                System.err.println("invalid input");
            }
        }
    }
    public House readHouse(){
        return new House(readHouseName(),readHouseYear(),readNumberOfFloors());
    }
    public Flat readFlat(){
        return new Flat(readName(),readCoordinate(),readLocalDateTime(),readArea(),readNumberOfRooms(),readNumberOfBathrooms(),readNew(),readTransport(),readHouse());
    }
    public int readId() throws Exception {
        for (; ; ) {
            System.out.println("Enter id : ");
            String number = scanner.nextLine();
            try {
                int id = Integer.parseInt(number);
                if(!collectionManager.getIds().contains(id))throw new Exception("No such id");
                return id;
            } catch (NumberFormatException exception) {
            }
        }
    }
    public long readIndex(){
        for(;;){
            System.out.println("Enter index : ");
            String in = scanner.nextLine();
            try{
                long index = Long.parseLong(in);
                return index;
            }catch (NumberFormatException exception){
                System.err.println("Invalid input!!!");
            }
        }
    }
}

