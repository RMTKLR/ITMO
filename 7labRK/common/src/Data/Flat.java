package Data;

import java.io.Serializable;
import java.time.LocalDateTime;
public class Flat implements DataValidate,Comparable<Flat>, Serializable {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null

    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private Float area; //Максимальное значение поля: 681, Значение поля должно быть больше 0

    private int numberOfRooms; //Максимальное значение поля: 20, Значение поля должно быть больше 0

    private long numberOfBathrooms; //Значение поля должно быть больше 0

    private boolean New;

    private Transport transport; //Поле не может быть null

    private House house; //Поле может быть null
    private int userId;

    public Flat(String name, Coordinates coordinates, LocalDateTime creationDate, Float area, int numberOfRooms, long numberOfBathrooms, boolean New, Transport transport, House house){
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.New = New;
        this.transport = transport;
        this.house = house;
    }

    public Flat(int id, String name, Coordinates coordinates, LocalDateTime creationDate, Float area, int numberOfRooms, long numberOfBathrooms, boolean New, Transport transport, House house, int userId){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.New = New;
        this.transport = transport;
        this.house = house;
        this.userId = userId;
    }

    public boolean isNew() {
        return New;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public House getHouse() {
        return house;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public Float getArea() {
        return area;
    }

    public long getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public Transport getTransport() {
        return transport;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        Flat another = (Flat)obj;
        return (this.getId() == another.getId() &&
                this.getArea() == another.getArea() &&
                this.getNumberOfBathrooms() == another.getNumberOfBathrooms() &&
                this.getTransport() == another.getTransport() &&
                this.New == another.New &&
                this.creationDate == another.creationDate &&
                this.getHouse() == another.getHouse() &&
                this.getCoordinates() == this.getCoordinates()&&
                this.getName().equals(another.getName()) ? true : false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfBathrooms(long numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public void setNew(boolean aNew) {
        New = aNew;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return " { \n"+
                "   Id : "+id+"\n"+
                "   Name : "+name+"\n"+
                "   Coordinates : "+coordinates+"\n"+
                "   CreationDate : "+creationDate+"\n"+
                "   Area : "+area+"\n"+
                "   Number of rooms : "+numberOfRooms+"\n"+
                "   Number of bathrooms : "+numberOfBathrooms+"\n"+
                "   Flat status : "+New+"\n"+
                "   Transport : "+transport+"\n"+
                "   UserId: " + userId+"\n"+
                "   House : "+house+"\n"+
                " } ";
    }

    @Override
    public boolean dataValidate() {
        return id>0
                && !name.equals("")&&name != null
                && coordinates.dataValidate()
                && creationDate != null
                && (area<=681 && area>0)
                && (numberOfRooms <=20 && numberOfRooms >0 )
                && numberOfBathrooms>0
                && transport != null;
    }

    @Override
    public int compareTo(Flat o) {
        int result = 0 ;
        result = Integer.compare(this.getNumberOfRooms(),o.getNumberOfRooms());
        return result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
