package Data;

import jdk.jfr.DataAmount;

import java.io.Serializable;

@DataAmount
public class House implements DataValidate,Comparable<House>, Serializable {

    private int id;
    private String name; //Поле не может быть null
    private long year; //Значение поля должно быть больше 0
    private Long numberOfFloors; //Поле может быть null, Значение поля должно быть больше 0
    public House(String name,long year,Long numberOfFloors){
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
    }

    public House(int id, String name,long year,Long numberOfFloors){
        this.id = id;
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
    }

    public String getName() {
        return name;
    }

    public Long getNumberOfFloors() {
        return numberOfFloors;
    }

    public long getYear() {
        return year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        House another = (House)obj;
        return this.getName().equals(another.getName())
                && this.getYear() == another.getYear()
                && this.getNumberOfFloors() == another.getNumberOfFloors();
    }

    @Override
    public String toString() {
        return " { \n"+
                "   Name : "+name+"\n"+
                "   Year : "+year+"\n"+
                "   Number of Floors : "+numberOfFloors+"\n"+
                "   id: " + this.id+"\n"+
                " } ";
    }

    @Override
    public boolean dataValidate() {
        return name != null
                && year >0
                && numberOfFloors >0;
    }

    @Override
    public int compareTo(House o) {
        int result;
        result = Long.compare(this.getYear(),o.getYear());
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
