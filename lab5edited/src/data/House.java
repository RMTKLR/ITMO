package data;

import jdk.jfr.DataAmount;

@DataAmount
public class House implements DataValidate,Comparable<House>{
    private String name; //Поле не может быть null
    private long year; //Значение поля должно быть больше 0
    private Integer numberOfFloors; //Поле может быть null, Значение поля должно быть больше 0
    public House(String name,long year,Integer numberOfFloors){
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfFloors() {
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
}
